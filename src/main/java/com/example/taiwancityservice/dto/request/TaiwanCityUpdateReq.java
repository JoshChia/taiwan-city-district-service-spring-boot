package com.example.taiwancityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class TaiwanCityUpdateReq {

    @NotBlank(message = "City欄位不捯為空")
    @JsonProperty("City")
    private String city;

    @NotBlank(message = "District欄位不得為空")
    @JsonProperty("District")
    private String district;

    @NotBlank(message = "Old City欄位不得為空")
    @JsonProperty("OldCity")
    private String oldCity;

    @NotBlank(message = "Old District欄位不得為空")
    @JsonProperty("OldDistrict")
    private String oldDistrict;

    @NotBlank(message = "Ser No欄位不得為空")
    @JsonProperty("SerNo")
    private String SerNo;

}
