package com.autofix.msrepairs.services;

import com.autofix.msrepairs.entities.DiscountBonusEntity;
import com.autofix.msrepairs.repositories.DiscountBonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountBonusService {
    @Autowired
    DiscountBonusRepository discountBonusRepository;

    public List<DiscountBonusEntity> getDiscountBonus(){
        return discountBonusRepository.findAll();
    }

    public DiscountBonusEntity saveDiscountBonus(DiscountBonusEntity discountBonus){
        return discountBonusRepository.save(discountBonus);
    }

    public DiscountBonusEntity getByBrand(String brand) {
        return discountBonusRepository.findByBrand(brand);
    }

    public DiscountBonusEntity getDiscountBonusById(Long id) {
        return discountBonusRepository.findById(id).orElse(null);
    }

    public int getBonusByBrand(String brand) {
        Integer bonus = discountBonusRepository.findBonusByBrand(brand);
        return Optional.ofNullable(bonus).orElse(0);
    }

    public int getStockByBrand(String brand) {
        return discountBonusRepository.findStockByBrand(brand);
    }

    public void decreaseStockByBrand(String brand) {
        DiscountBonusEntity discountBonus = this.getByBrand(brand);
        discountBonus.setStock(discountBonus.getStock() - 1);
        discountBonusRepository.save(discountBonus);
    }

    public void deleteDiscountBonusByBrand(Long id) {
        discountBonusRepository.deleteById(id);
    }



}
















