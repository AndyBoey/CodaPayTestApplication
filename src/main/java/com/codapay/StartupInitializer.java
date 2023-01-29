package com.codapay;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StartupInitializer {
    @Autowired
    private RouterConfiguration routerConfiguration;

    @Autowired
    private ServerConfiguration serverConfiguration;

    @EventListener(ApplicationReadyEvent.class)
    public void startupInit() {
        System.out.println("Initiailization complete");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String serverAddr = "http://" + serverConfiguration.getHost()
                + ":"
                + serverConfiguration.getPort()
                + "/application";

        JSONObject registerJSONObject = new JSONObject();
        registerJSONObject.put("ipAddr", serverAddr);
        registerJSONObject.put("instanceId", serverConfiguration.getInstanceId());

        HttpEntity<String> request =
                new HttpEntity<String>(registerJSONObject.toString(), headers);

        String routerRegisterAddr = "http://" + routerConfiguration.getHost()
                + ":"
                + routerConfiguration.getPort()
                + "/route/register";

        System.out.println("registering to " + routerRegisterAddr);

        String response = restTemplate.postForObject(routerRegisterAddr, request, String.class);
        System.out.println("Register to router: " + response);
    }

    public void exitHook() {
        String routerRemoveAddr = "http://" + routerConfiguration.getHost()
                + ":"
                + routerConfiguration.getPort()
                + "/route/" + serverConfiguration.getInstanceId();

        System.out.println("detaching from router..");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(routerRemoveAddr);
    }
}

