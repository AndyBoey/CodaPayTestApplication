package com.codapay.api;

import com.codapay.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("simulation")
public class SimulationController {
    @Autowired
    private SimulationService simulationService;

    @PostMapping("/error-rate")
    public String setErrorRate(@RequestBody String errorRate) {
        Integer newErrorRate = Integer.parseInt(errorRate);
        if(newErrorRate <= 0)
            newErrorRate = 0;
        else if(newErrorRate >= 100)
            newErrorRate = 100;

        simulationService.setErrorRate(newErrorRate);

        return String.valueOf(newErrorRate) + "%";
    }

    @GetMapping("/error-rate")
    public String getErrorRate() {
        return String.valueOf(simulationService.getErrorRate()) + "%";
    }
}
