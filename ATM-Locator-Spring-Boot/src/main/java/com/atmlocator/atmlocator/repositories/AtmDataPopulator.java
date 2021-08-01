package com.atmlocator.atmlocator.repositories;

import com.atmlocator.atmlocator.model.ATMLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RefreshScope
public class AtmDataPopulator {
    private Logger atmDataPopulatorLogger = LoggerFactory.getLogger(AtmDataPopulator.class);

    @Value("${atm.api}")
    private String atmAPI;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDataFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    public List<ATMLocation> getData() throws Exception {
        String response = restTemplate.getForObject(atmAPI, String.class);
        atmDataPopulatorLogger.debug("GARBAGE IN RESPONSE:" + "\n\n" + response.substring(0, 5) + "\n\n");
        String toBeParsed = response.substring(6, response.length());
        atmDataPopulatorLogger.debug("TO BE PARSED RESPONSE:" + "\n\n" + toBeParsed + "\n\n");
        ObjectMapper objectMapper = new ObjectMapper();
        ATMLocation[] atmLocations = objectMapper.readValue(toBeParsed, ATMLocation[].class);
        atmDataPopulatorLogger.debug("PARSED RESPONSE:" + "\n\n" + atmLocations.toString() + "\n\n");
        return Arrays.asList(atmLocations);
    }

    public List<ATMLocation> getDataFallBack() throws Exception {
        return new ArrayList<>();
    }
}
