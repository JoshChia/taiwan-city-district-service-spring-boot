package com.example.taiwancityservice.controller;

import com.example.taiwancityservice.dto.request.*;
import com.example.taiwancityservice.dto.response.*;
import com.example.taiwancityservice.service.TaiwanCityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@Slf4j
public class TaiwanCityController {

    @Autowired
    private TaiwanCityService taiwanCityService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/query")
    public ResponseMessageTemplate<TaiwanCityQueryRes> queryTaiwanCity(
            @RequestBody RequestMessageTemplate<TaiwanCityQueryReq> requestMessageTemplate){

        TaiwanCityQueryReq taiwanCityQueryReq = requestMessageTemplate.getRequestMessageBody();
        TaiwanCityQueryRes taiwanCityQueryRes = taiwanCityService.queryTaiwanCity(taiwanCityQueryReq);

        return new ResponseMessageTemplate<>(requestMessageTemplate, taiwanCityQueryRes);
    }

    @PostMapping("/query1")
    public ResponseMessageTemplate<TaiwanCityQueryRes> queryTaiwanCity1(
            @RequestBody RequestMessageTemplate<TaiwanCityQueryReq> requestMessageTemplate){


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("{\n" +
                "  \"Header\": {\n" +
                "    \"UserId\": \"user1@gmail.com\",\n" +
                "    \"EnterpriseId\": \"70827406\",\n" +
                "    \"RequestFrom\": \"ECIP01-UI-01-001\",\n" +
                "    \"RequestTimestamp\": \"2018-08-19 10:33:13\",\n" +
                "    \"ApiCallingFlow\": \"ECIP01-UI-01-001;\"\n" +
                "  },\n" +
                "  \"Body\": {\n" +
                "    \"City\":\"台北市\",\n" +
                "    \"District\":\"士林區\"\n" +
                "  }\n" +
                "}", headers);
        String url = "http://192.168.1.30:8302/taiwan-city/query";
        log.info(request.toString());
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<String>() {
        });

        log.info(resp.toString());
        return null;
    }

    @PostMapping("/create")
    public ResponseMessageTemplate<TaiwanCityCreateRes> createTaiwanCity(
            @Valid  @RequestBody RequestMessageTemplate<TaiwanCityCreateReq> requestMessageTemplate){

        TaiwanCityCreateReq taiwanCityCreateReq = requestMessageTemplate.getRequestMessageBody();
        TaiwanCityCreateRes taiwanCityCreateRes = taiwanCityService.createTaiwanCity(taiwanCityCreateReq);

        return new ResponseMessageTemplate<>(requestMessageTemplate, taiwanCityCreateRes);
    }

    @PostMapping("/update")
    public ResponseMessageTemplate<TaiwanCityUpdateRes> updateTaiwanCity(
            @Valid @RequestBody RequestMessageTemplate<TaiwanCityUpdateReq> requestMessageTemplate){

        TaiwanCityUpdateReq taiwanCityUpdateReq = requestMessageTemplate.getRequestMessageBody();
        TaiwanCityUpdateRes taiwanCityUpdateRes = taiwanCityService.updateTaiwanCity(taiwanCityUpdateReq);

        return new ResponseMessageTemplate<>(requestMessageTemplate, taiwanCityUpdateRes);
    }

    @PostMapping("/delete")
    public ResponseMessageTemplate<TaiwanCityDeleteRes> deleteTaiwanCity(
            @Valid @RequestBody RequestMessageTemplate<TaiwanCityDeleteReq> requestMessageTemplate){

        TaiwanCityDeleteReq taiwanCityDeleteReq = requestMessageTemplate.getRequestMessageBody();
        TaiwanCityDeleteRes taiwanCityDeleteRes = taiwanCityService.deleteTaiwanCity(taiwanCityDeleteReq);
        return new ResponseMessageTemplate<>(requestMessageTemplate, taiwanCityDeleteRes);
    }

}
