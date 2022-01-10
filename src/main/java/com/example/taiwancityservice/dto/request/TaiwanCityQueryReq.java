package com.example.taiwancityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaiwanCityQueryReq {

    @JsonProperty("City")
    private String city;

    @JsonProperty("District")
    private String district;

}
