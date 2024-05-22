package com.autofix.msrepairs.services;

import com.autofix.msrepairs.entities.DiscountRegClientEntity;
import com.autofix.msrepairs.repositories.DiscountRegClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountRegClientService {
    @Autowired
    DiscountRegClientRepository discountRegClientRepository;

    public List<DiscountRegClientEntity> getDiscountRegClients(){
        return discountRegClientRepository.findAll();
    }

    public Float getDiscountByCategoryAndEngine(String category, String engine){
        return discountRegClientRepository.findDiscountRCByCategoryAndEngine(category, engine);
    }

}
