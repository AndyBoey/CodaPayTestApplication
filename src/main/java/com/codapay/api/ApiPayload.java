package com.codapay.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiPayload {
    private String game;
    private String gamerID;
    private Integer points;
}
