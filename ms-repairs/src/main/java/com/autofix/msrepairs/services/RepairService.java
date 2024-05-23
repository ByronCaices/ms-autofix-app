package com.autofix.msrepairs.services;

import com.autofix.msrepairs.clients.CarsFeignClient;
import com.autofix.msrepairs.clients.PricesFeignClient;
import com.autofix.msrepairs.entities.RepairEntity;
import com.autofix.msrepairs.models.CarEntity;
import com.autofix.msrepairs.models.updtMileageModel;
import com.autofix.msrepairs.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class RepairService {
    private final PricesFeignClient priceService;
    private final DiscountRegClientService discountRegClientService;
    private final DiscountBonusService discountBonusService;
    private final SurchargeCarAgeService surchargeCarAgeService;
    private final SurchargeMileageService surchargeCarMileageService;
    private final CarsFeignClient carsFeignClient;
    private final RepairRepository repairRepository;

    @Autowired
    public RepairService(PricesFeignClient priceService, DiscountRegClientService discountRegClientService,
                         DiscountBonusService discountBonusService, SurchargeCarAgeService surchargeCarAgeService,
                         SurchargeMileageService surchargeCarMileageService,
                         CarsFeignClient carsFeignClient, RepairRepository repairRepository) {
        this.priceService = priceService;
        this.discountRegClientService = discountRegClientService;
        this.discountBonusService = discountBonusService;
        this.surchargeCarAgeService = surchargeCarAgeService;
        this.surchargeCarMileageService = surchargeCarMileageService;
        this.carsFeignClient = carsFeignClient;
        this.repairRepository = repairRepository;
    }

    public ArrayList<RepairEntity> getRepairs() {
        return (ArrayList<RepairEntity>) repairRepository.findAllOrderByCheckinDate();
    }

    public RepairEntity updateRepair(RepairEntity repair) {
        //repair = this.addSurchPickupDelay(id);
        return repairRepository.save(repair);
    }

    public RepairEntity getById(Long id) {
        return repairRepository.findById(id).orElse(null);
    }

    public List<Object[]> getRepairTypeAmounts() {
        return repairRepository.getRepairTypeAmountsByBodywork();
    }

    public List<Object[]> getRepairTypeAmountsByEngine() {
        return repairRepository.getRepairTypeAmountsByEngine();
    }

    public List<Object[]> getAverageRepairTimeByBrand() {
        return repairRepository.getAverageRepairTimeByBrand();
    }

    public RepairEntity saveRepair(RepairEntity repair) {

        // This method adds to the just created repair entity the missing data about
        // prices and discounts
        // except for discount pickup delay, who needs to be added after the repair is done
        // except for discount bonus, who needs to be added after the repair is done
        // finished (in the addSurchPickupDelay method)
        // with the total amount (IVA included)
        this.addCarData(repair);
        // ###### ADD REPAIR PRICE : depends on category (number of checkins in last 12
        // months) and engine
        float repairPrice = priceService.getPriceByRepairTypeAndEngine(repair.getEngine(), repair.getRepairType())* 1000;
        // float repairPrice = repairPriceInt;
        repair.setRepairPrice(repairPrice);

        // ###### ADD DISCOUNT REG CLIENT : depends on category (number of checkins in
        // last 12 months) and engine
        Integer carVisits = repairRepository.countByPlateLastYear(repair.getPlate());
        String categoryRC;
        Float disc_reg_client = 0.0f;
        if (carVisits > 0) {
            if (carVisits >= 1 && carVisits <= 2) {
                categoryRC = "A";
            } else if (carVisits >= 3 && carVisits <= 5) {
                categoryRC = "B";
            } else if (carVisits >= 6 && carVisits < 10) {
                categoryRC = "C";
            } else {
                categoryRC = "D";
            }
            disc_reg_client = discountRegClientService.getDiscountByCategoryAndEngine(categoryRC, repair.getEngine());
        }
        repair.setDiscRegClient(repairPrice * disc_reg_client);

        // ###### Add MON-THU discount : 10% discount if checkin is between 9 AM and 12
        // PM from Monday to Thursday

        //Date date = repair.getCheckinDate(); // Tu objeto Date
        LocalDateTime dateTime = repair.getCheckinDate()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        boolean isWithinRange = false;

        // Obtenemos el día de la semana y la hora
        DayOfWeek day = dateTime.getDayOfWeek();
        int hour = dateTime.getHour();

        // Verificamos si es lunes a jueves
        if (day == DayOfWeek.MONDAY || day == DayOfWeek.TUESDAY || day == DayOfWeek.WEDNESDAY
                || day == DayOfWeek.THURSDAY) {
            // Verificamos si la hora está entre las 9 AM y las 12 PM (exclusivo)
            if (hour >= 9 && hour < 12) {
                isWithinRange = true;
            }
        }

        if (isWithinRange) {
            repair.setDiscMonThu(repairPrice * 0.1f);
        } else {
            repair.setDiscMonThu(repairPrice * 0.0f);
        }

        // ###### ADD DISCOUNT BONUS : depends on car brand
        /*
        System.out.println("BBBBBBBBBBB brand: " + repair.getBrand());
        if (discountBonusService.getStockByBrand(repair.getBrand()) <= 0) {
            System.out.println("No hay stock de descuento por marca");
            repair.setDiscBonus(0.0f);
        } else {
            float disc_bonus = discountBonusService.getBonusByBrand(repair.getBrand());
            if (disc_bonus != 0) {
                discountBonusService.decreaseStockByBrand(repair.getBrand());
            }
            repair.setDiscBonus(disc_bonus); // not a percentage
        }

         */
        // ###### ADD SURCHARGE CAR AGE : depends on car age and bodywork

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int carYear = carsFeignClient.getCarByPlate(repair.getPlate()).getYear();
        int carAge = currentYear - carYear;
        String categoryCA;
        if (carAge >= 0 && carAge <= 5) {
            categoryCA = "A";
        } else if (carAge > 5 && carAge <= 10) {
            categoryCA = "B";
        } else if (carAge > 10 && carAge <= 15) {
            categoryCA = "C";
        } else {
            categoryCA = "D";
        }
        float surchargeCarAge = surchargeCarAgeService.getSurchargeByCategoryAndBodywork(categoryCA,
                repair.getBodywork());
        repair.setSurchCarage(repairPrice * surchargeCarAge);

        // ###### ADD SURCHARGE CAR MILEAGE : depends on car mileage and bodywork
        String categoryMA;
        Long carMileage = repair.getMileage(); // Mileage value comes from frontend and next its updated in the car
                                               // entity

        carsFeignClient.updateMileage(new updtMileageModel(repair.getPlate(), repair.getMileage())); // Update car mileage on car table
        if (carMileage >= 0 && carMileage <= 5000) {
            categoryMA = "A";
        } else if (carMileage > 5000 && carMileage <= 12000) {
            categoryMA = "B";
        } else if (carMileage > 12000 && carMileage <= 25000) {
            categoryMA = "C";
        } else if (carMileage > 25000 && carMileage <= 40000) {
            categoryMA = "D";
        } else {
            categoryMA = "E";
        }
        System.out.println("CCCCCCCCCC categoryMA: " + categoryMA);
        float surchargeCarMileage = surchargeCarMileageService.getSurchargeMileageByCategoryAndBodywork(categoryMA,
                repair.getBodywork());
        repair.setSurchMileage(repairPrice * surchargeCarMileage);

        // ###### Add surcharge pickup delay (this is added when the car owner picks up
        // the car after the finish date)
        repair.setSurchDelay(0.0f);
        repair.setIva(0.0f);

        // ###### Add partial total amount (without surcharge pickup delay and IVA)
        float fpartialTotal = (repairPrice - repair.getDiscRegClient()
                - repair.getDiscMonThu() + repair.getSurchCarage()
                + repair.getSurchMileage());

        float iva = fpartialTotal * 0.19f;
        repair.setIva(iva);

        fpartialTotal += iva;

        repair.setTotalAmount(fpartialTotal);

        return repairRepository.save(repair);
    }

    public void addCarData(RepairEntity repair) {
        CarEntity car = carsFeignClient.getCarByPlate(repair.getPlate());
        //usar localdatetime
        repair.setCheckinDate(LocalDateTime.now());
        repair.setBrand(car.getBrand());
        repair.setBodywork(car.getBodywork());
        repair.setEngine(car.getEngine());
        repair.setRepairCode(repair.getPlate() + repair.getMileage());
    }

    public RepairEntity addSurchPickupDelay(Long id) {

        RepairEntity repair = this.getById(id);
        float disc_bonus = 0.0f;
        /*
        System.out.println("BBBBBBBBBBB brand: " + repair.getBrand());
        if (discountBonusService.getStockByBrand(repair.getBrand()) <= 0) {
            System.out.println("No hay stock de descuento por marca");
            //repair.setDiscBonus(0.0f);
            //disc_bonus = 0.0f;
        } else {
            disc_bonus = discountBonusService.getBonusByBrand(repair.getBrand());
            if (disc_bonus != 0) {
                discountBonusService.decreaseStockByBrand(repair.getBrand());
            }
            //repair.setDiscBonus(disc_bonus); // not a percentage
        }

         */

        float repairPrice = repair.getRepairPrice();
        float disc_reg_client = repair.getDiscRegClient();
        //float disc_bonus = repair.getDiscBonus();
        float disc_mon_thu = repair.getDiscMonThu();
        float surchargeCarAge = repair.getSurchCarage();
        float surchargeCarMileage = repair.getSurchMileage();

        // ###### ADD SURCHARGE PICKUP DELAY : depends on finish date and checkout date
        LocalDateTime finishDateTime = repair.getFinishDate().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime checkoutDateTime = repair.getCheckoutDate().atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        // For each day of delay add 5% to the total amount
        long days = ChronoUnit.DAYS.between(finishDateTime, checkoutDateTime);
        float surchargeDelay = 0.05f * days * repairPrice;
        repair.setSurchDelay(surchargeDelay);

        //float partialTotal = (repairPrice - disc_reg_client - disc_bonus - disc_mon_thu
        //        + surchargeCarAge + surchargeCarMileage + surchargeDelay);

        float partialTotal = (repairPrice - disc_reg_client - disc_mon_thu
                + surchargeCarAge + surchargeCarMileage + surchargeDelay);

        // Add IVA
        float iva = partialTotal * 0.19f;
        repair.setIva(iva);

        // Add total amount (IVA included)
        partialTotal += iva;

        // int finalTotal = (int) partialTotal;
        repair.setTotalAmount(partialTotal);

        // close order
        //orderService.saveOrder(new OrderEntity(repair.getRepairCode(), repair.getPlate(), repair.getEngine(),
        //        repair.getBodywork(), disc_bonus, repair.getTotalAmount()));

        return repairRepository.save(repair);
    }

    /*
    public void closeRepairOrder(Long id){
        RepairEntity repair = this.getById(id);
        float disc_bonus = 0.0f;
        System.out.println("BBBBBBBBBBB brand: " + repair.getBrand());
        if (discountBonusService.getStockByBrand(repair.getBrand()) <= 0) {
            System.out.println("No hay stock de descuento por marca");
            //repair.setDiscBonus(0.0f);
            //disc_bonus = 0.0f;
        } else {
            disc_bonus = discountBonusService.getBonusByBrand(repair.getBrand());
            if (disc_bonus != 0) {
                discountBonusService.decreaseStockByBrand(repair.getBrand());
            }
            //repair.setDiscBonus(disc_bonus); // not a percentage
        }

        // Obtener suma total by repaircode
        float totalAmount = repairRepository.sumTotalAmountByRepairCode(repair.getRepairCode());

        orderService.saveOrder(new OrderEntity(repair.getRepairCode(), repair.getPlate(), repair.getEngine(),
                repair.getBodywork(), disc_bonus, totalAmount));
    }
    */


    public void deleteRepair(Long id) {
        repairRepository.deleteById(id);
    }

    public List<RepairEntity> getRepairsByRepairCode(String repair_code) {
        return repairRepository.findByRepairCode(repair_code);
    }


    public float sumTotalAmountByRepairCode(String repair_code) {
        return repairRepository.sumTotalAmountByRepairCode(repair_code);
    }

}
