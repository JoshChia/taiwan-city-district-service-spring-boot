package com.example.taiwancityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaiwanCityQueryRes {
    @JsonProperty("TaiwanCityCount")
    private int taiwanCityCount;

    @JsonProperty("TaiwanCityList")
    private List<TaiwanCityDTO> taiwanCityList = new ArrayList<>();

    @Data
    public static class TaiwanCityDTO{

        @JsonProperty("City")
        private String city;

        @JsonProperty("District")
        private  String district;

    }
}
