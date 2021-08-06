package com.microservices.userservice.service;

import com.microservices.userservice.entity.ATMLocation;
import com.microservices.userservice.entity.ApiResponseObject;
import com.microservices.userservice.entity.UserEntity;
import com.microservices.userservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResponseObjectRepository responseObjectRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ATMLocationRepository atmLocationRepository;
    @Autowired
    private GeoRepository geoRepository;
    @Autowired
    private HourRepository hourRepository;
    @Autowired
    private OpeningHoursRepository openingHoursRepository;

    @Autowired
    private RestTemplate restTemplate;

    public UserEntity saveUser(UserEntity userEntity) {
        try {
            String city = userEntity.getCity();
            ApiResponseObject apiResponseObject = restTemplate.getForObject("http://ATM-LOCATOR/locations/" + city, ApiResponseObject.class);
            userEntity.setApiResponseObject(apiResponseObject);
            for(ATMLocation atmLocation: userEntity.getApiResponseObject().getList()){
                geoRepository.save(atmLocation.getAddress().getGeoLocation());
                addressRepository.save(atmLocation.getAddress());
                atmLocation.getOpeningHours().stream().forEach(openingHours -> openingHours.getHours().stream().forEach(hour -> hourRepository.save(hour)));
                atmLocation.getOpeningHours().stream().forEach(openingHours -> openingHoursRepository.save(openingHours));
                atmLocationRepository.save(atmLocation);
            }
            responseObjectRepository.save(apiResponseObject);
            UserEntity userEntity1 = userRepository.saveAndFlush(userEntity);
            return userEntity1;
        } catch (Exception e) {
        e.printStackTrace();
    }
        UserEntity userEntity1 = userRepository.saveAndFlush(userEntity);
        return userEntity1;
    }


    public UserEntity findUserById(Long userId) {
        if(!userRepository.findById(userId).isPresent()){
            return new UserEntity();
        }
        return userRepository.findById(userId).get();
    }
    public List<UserEntity> GetALlUsers() {
        return userRepository.findAll();
    }
}
