package com.example.taiwancityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaiwanCityDeleteReq {

    @JsonProperty("City")
    private String City;

    @JsonProperty("District")
    private String district;
}
