package com.autofix.msprices.services;

import com.autofix.msprices.repositories.PriceRepository;
import com.autofix.msprices.entities.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    @Autowired
    PriceRepository priceRepository;

    public List<PriceEntity> getPrices(){
        return priceRepository.findAll();
    }

    public Integer getPriceByRepairTypeAndEngine(String engine,Integer repair_type){
        return priceRepository.findPriceEntityByEngineAndRepairType(engine, repair_type);
    }
}