package com.codapay;

import com.codapay.api.ApiPayload;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StartupInitializer {
    @Value("${router.address}")
    private String routerAddr;

    @EventListener(ApplicationReadyEvent.class)
    public void startupInit() {
        System.out.println("Initiailization complete");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject registerJSONObject = new JSONObject();
        registerJSONObject.put("ipAddr", "http://localhost:8081/application");
        registerJSONObject.put("instanceId", "15");

        HttpEntity<String> request =
                new HttpEntity<String>(registerJSONObject.toString(), headers);

        String routerRegisterAddr = routerAddr + "/route/register";

        System.out.println("registering to " + routerRegisterAddr);

        String response = restTemplate.postForObject(routerRegisterAddr, request, String.class);
        System.out.println("Register to router: " + response);
    }
}

