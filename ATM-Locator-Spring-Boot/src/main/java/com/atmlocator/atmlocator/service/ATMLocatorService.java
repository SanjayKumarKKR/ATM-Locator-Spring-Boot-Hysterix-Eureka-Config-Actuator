package com.atmlocator.atmlocator.service;

import com.atmlocator.atmlocator.model.ATMLocation;
import com.atmlocator.atmlocator.repositories.AtmDataPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RefreshScope
public class ATMLocatorService {
    @Autowired
    private AtmDataPopulator atmDataPopulator;

    public List<ATMLocation> getAtmLocations() throws Exception {
        return atmDataPopulator.getData();
    }

    public List<ATMLocation> getAtmLocationsByCity(String city) throws Exception {
        List<ATMLocation> atmLocations = atmDataPopulator.getData();
        List<ATMLocation> filteredAtmLocations = atmLocations.stream().filter(
            atmLocation -> atmLocation.getAddress().getCity().equals(city)
        ).collect(Collectors.toList());
        return filteredAtmLocations;
    }

    public List<ATMLocation> getAtmLocationsByPostalCode(String postalCode) throws Exception {
        postalCode = postalCode.replaceAll("%20"," ");
        List<ATMLocation> atmLocations = atmDataPopulator.getData();
        String finalPostalCode = postalCode;
        List<ATMLocation> filteredAtmLocations = atmLocations.stream().filter(
                atmLocation -> atmLocation.getAddress().getPostalcode().equals(finalPostalCode)
        ).collect(Collectors.toList());
        return filteredAtmLocations;
    }

}
