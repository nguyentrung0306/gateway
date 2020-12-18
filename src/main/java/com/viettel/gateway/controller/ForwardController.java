package com.viettel.gateway.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/forward")
public class ForwardController {

    @GetMapping("/service/GET/{apiCode}")
    public ResponseEntity<?> forwardGet(@PathVariable(name = "apiCode") String apiCode, @RequestBody HashMap<String, Object> body) {
        /*TODO Query DB theo apiCode de lay tt Service con*/
        StringBuilder childService = new StringBuilder("http://localhost:8087/customer/getCustomer");
        HashMap<String, Object> param = (HashMap<String, Object>) body.get("parameter");;

        int i= 0;
        for(Map.Entry<String, Object> entry : param.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            childService.append((i == 0 ? "?" : "&") + key + "=" + value);
            i++;
        }

        System.out.println("---------------------------------------");
        System.out.println(childService.toString());
        System.out.println("---------------------------------------");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.getForEntity(childService.toString(), Object.class);

        Object obj = response.getBody();
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/service/POST/{apiCode}")
    public ResponseEntity<?> forwardPost(@PathVariable(name = "apiCode") String apiCode, @RequestBody HashMap<String, Object> body) {
        /*TODO Query DB theo apiCode de lay tt Service con*/
        StringBuilder childService = new StringBuilder("http://localhost:8087/customer/getCustomer");
        HashMap<String, Object> param = (HashMap<String, Object>) body.get("parameter");;
        HashMap<String, Object> rawData = (HashMap<String, Object>) body.get("rawData");;

        int i= 0;
        for(Map.Entry<String, Object> entry : param.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            childService.append((i == 0 ? "?" : "&") + key + "=" + value);
            i++;
        }

        System.out.println("---------------------------------------");
        System.out.println(childService.toString());
        System.out.println("---------------------------------------");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<HashMap<String, Object>> authenRequest = new HttpEntity<>(rawData);
        ResponseEntity<Object> response = restTemplate.postForEntity(childService.toString(), authenRequest, Object.class);

        Object obj = response.getBody();
        return ResponseEntity.ok(obj);
    }
}

