package com.codapay.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @PostMapping("/application")
    public ApiPayload request(@RequestBody ApiPayload payload) {
        return payload;
    }
}
