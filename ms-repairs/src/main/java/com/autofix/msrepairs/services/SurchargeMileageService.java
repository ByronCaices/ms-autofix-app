package com.autofix.msrepairs.services;

import com.autofix.msrepairs.entities.SurchargeMileageEntity;
import com.autofix.msrepairs.repositories.SurchargeMileageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurchargeMileageService {
    @Autowired
    SurchargeMileageRepository surchargeMileageRepository;

    public List<SurchargeMileageEntity> listSurchargesMileages(){
        return surchargeMileageRepository.findAll();
    }

    public float getSurchargeMileageByCategoryAndBodywork(String category, String bodywork){
        return surchargeMileageRepository.getSurchargeMileageByCategoryAndBodywork(category, bodywork);
    }
}
