package com.example.taiwancityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TaiwanCityCreateReq {

    @NotBlank(message = "City欄位不得為空")
    @JsonProperty("City")
    private String city;

    @NotBlank(message = "District")
    @JsonProperty("District")
    private String district;

    @NotBlank(message = "SerNo 欄位不得為空")
    @JsonProperty("SerNo")
    private String serNo;

}
