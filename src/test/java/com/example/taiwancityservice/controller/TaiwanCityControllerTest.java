package com.example.taiwancityservice.controller;

import com.example.taiwancityservice.dto.response.*;
import com.example.taiwancityservice.enums.ReturnCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("dev")
class TaiwanCityControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void queryTaiwanCityTest() throws IOException {
//
        String url = "http://192.168.1.30:8302/taiwan-city/query";

        String requestJson = IOUtils.toString(
                this.getClass().getClassLoader().getResourceAsStream("TaiwanCityQueryInput.json"),"UTF8"
        );

        log.info(requestJson);

        ResponseEntity<ResponseMessageTemplate<?>> response = getResponse(url, requestJson);


        TaiwanCityQueryRes taiwanCityQueryRes =  convertResponseToDto(response, TaiwanCityQueryRes.class);

        Assertions.assertEquals(ReturnCode.SUCCESS.getCode(),
                response.getBody().getResponseMessageHeader().getReturnCode(),"ReturnCode not exprected");
        Assertions.assertEquals("台北市", taiwanCityQueryRes.getTaiwanCityList().get(0).getCity(), "City not expected!");

    }

    @Test
    void createTaiwanCity() throws Exception{

        String url = "http://192.168.1.30:8302/taiwan-city/create";

        String requestJson = IOUtils.toString(
                this.getClass().getClassLoader().getResourceAsStream("TaiwanCityCreateInput.json"),"UTF8"
        );

        log.info(requestJson);

        ResponseEntity<ResponseMessageTemplate<?>> response = getResponse(url, requestJson);


        TaiwanCityCreateRes taiwanCityCreateRes  =  convertResponseToDto(response, TaiwanCityCreateRes.class);

        Assertions.assertEquals(ReturnCode.SUCCESS.getCode(),
                response.getBody().getResponseMessageHeader().getReturnCode(),"ReturnCode not exprected");
        assertThat(response.getBody().getResponseMessageHeader().getReturnCode()).isIn(ReturnCode.SUCCESS.getCode(),
                ReturnCode.DB_INSERT_ERROR.getCode());

    }

    @Test
    void updateTaiwanCity() throws Exception {
        String url = "http://192.168.1.30:8302/taiwan-city/update";

        String requestJson = IOUtils.toString(
                this.getClass().getClassLoader().getResourceAsStream("TaiwanCityUpdateInput.json"),"UTF8"
        );

        log.info(requestJson);

        ResponseEntity<ResponseMessageTemplate<?>> response = getResponse(url, requestJson);


        convertResponseToDto(response, TaiwanCityUpdateRes.class);

        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful(), "ReturnCode not expected!");
        Assertions.assertEquals(ReturnCode.SUCCESS.getCode(),
                response.getBody().getResponseMessageHeader().getReturnCode(), "ReturnCode not expected!");

    }

    @Test
    void deleteTaiwanCity() throws Exception {
        String url = "http://192.168.1.30:8302/taiwan-city/delete";

        String requestJson = IOUtils.toString(
                this.getClass().getClassLoader().getResourceAsStream("TaiwanCityDeleteInput.json"),"UTF8"
        );

        log.info(requestJson);

        ResponseEntity<ResponseMessageTemplate<?>> response = getResponse(url, requestJson);


        convertResponseToDto(response, TaiwanCityDeleteRes.class);

        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful(), "ReturnCode not expected!");
        Assertions.assertEquals(ReturnCode.SUCCESS.getCode(),
                response.getBody().getResponseMessageHeader().getReturnCode(), "ReturnCode not expected!");

    }

    private ResponseEntity<ResponseMessageTemplate<?>> getResponse(String url, String requestJson) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        ResponseEntity<ResponseMessageTemplate<?>> response = restTemplate.exchange(url, HttpMethod.POST, request,
                new ParameterizedTypeReference<>() {
                });

        return response;
    }

    private <T> T convertResponseToDto(ResponseEntity<ResponseMessageTemplate<?>> response, Class<T> clazz) {

        ObjectMapper objMapper = new ObjectMapper();
        objMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        log.info("respose",response.getBody());
        try {

            String jsonStr = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody());

            log.info("response JSON:" + jsonStr);
        }

        catch (IOException e) {
            log.error("JSON資料轉換錯誤," + e.getMessage());
        }
        // 轉換ResponseMessageBody成java bean
        T responseObject = objMapper.convertValue(response.getBody().getResponseMessageBody(), clazz);
        return responseObject;
    }
}