package com.codapay.service;

import org.springframework.stereotype.Service;

@Service
public class SimulationServiceImpl implements SimulationService {
    /** errorRate will be between 0 - 100
     * 0 denotes no error
     * 100 denotes error on every API call
     *
     * This is for dynamic testing of the router
     */

    private Integer errorRate = 0;

    @Override
    public Integer getErrorRate() {
        return errorRate;
    }

    @Override
    public void setErrorRate(Integer errorRate) {
        this.errorRate = errorRate;
    }
}
