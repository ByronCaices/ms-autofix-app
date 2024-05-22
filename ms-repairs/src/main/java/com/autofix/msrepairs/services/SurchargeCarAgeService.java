package com.autofix.msrepairs.services;

import com.autofix.msrepairs.entities.SurchargeCarAgeEntity;
import com.autofix.msrepairs.repositories.SurchargeCarAgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurchargeCarAgeService {
    @Autowired
    SurchargeCarAgeRepository surchargeCarAgeRepository;

    public List<SurchargeCarAgeEntity> listSurchargesCarAges() {
        return surchargeCarAgeRepository.findAll();
    }

    public float getSurchargeByCategoryAndBodywork(String category, String bodywork) {
        return surchargeCarAgeRepository.getSurchargeByCategoryAndBodywork(category, bodywork);
    }



}
