package com.codapay.api;

import com.codapay.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@RestController
public class RequestController {
    @Autowired
    private SimulationService simulationService;

    @PostMapping("/application")
    public ApiPayload request(@RequestBody ApiPayload payload) {
        Integer errorRate = simulationService.getErrorRate();

        Random randI = new Random();
        Integer randomNum = randI.nextInt(100);

        /**
         * random number generate >= error rate means it's successful
         * e.g. if error rate is 0, any generated number between 1 - 100 will exceed 0
         *  hence it will be 100% success rate
         */
        if(randomNum >= errorRate) {
            // successful
            return payload;
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Simulated error");
        }
    }
}
