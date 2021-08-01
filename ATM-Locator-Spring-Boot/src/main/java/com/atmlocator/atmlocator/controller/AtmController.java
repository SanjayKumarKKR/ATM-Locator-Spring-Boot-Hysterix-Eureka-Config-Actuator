package com.atmlocator.atmlocator.controller;

import com.atmlocator.atmlocator.model.ATMLocation;
import com.atmlocator.atmlocator.model.ApiResponseObject;
import com.atmlocator.atmlocator.service.ATMLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class AtmController {
    @Autowired
    private ATMLocatorService atmLocatorService;

    @RequestMapping(value = "/locations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ApiResponseObject getATMLocationfromING() throws Exception {
        ApiResponseObject<ATMLocation> responseObject= new ApiResponseObject<>();
        responseObject.setList(atmLocatorService.getAtmLocations());
        return responseObject;
    }

    @RequestMapping(value = "/locations/{city}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ApiResponseObject getATMLocationByCity(@PathVariable("city") String city) throws Exception {
        ApiResponseObject<ATMLocation> responseObject= new ApiResponseObject<>();
        responseObject.setList(atmLocatorService.getAtmLocationsByCity(city));
        return responseObject;
    }

    @RequestMapping(value = "/postalCode/{postalCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ApiResponseObject getATMLocationByPostalCode(@PathVariable("postalCode") String postalCode) throws Exception {
        ApiResponseObject<ATMLocation> responseObject= new ApiResponseObject<>();
        responseObject.setList(atmLocatorService.getAtmLocationsByPostalCode(postalCode));
        return responseObject;
    }



}
