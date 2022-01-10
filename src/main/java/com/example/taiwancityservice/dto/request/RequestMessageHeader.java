package com.example.taiwancityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RequestMessageHeader {

	@NotBlank(message = "UserId為必輸入欄位")
	@Schema(description = "使用者ID", required = true, example = "user1@gmail.com")
	@JsonProperty("UserId")
	@Size(max=64,message="UserId欄位長度過長")
	@Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$", message = "UserId欄位格式錯誤")
	private String userId;
	
	
	@NotBlank(message="EnterpriseId欄位不得為空")   
	@Schema(description = "企業統編", required = false, example = "70827406")
	@JsonProperty("EnterpriseId")
	@Size(min = 8, max = 8, message = "EnterpriseId欄位長度錯誤")
	private String enterpriseId;
	

	@NotBlank(message = "RequestFrom為必輸入欄位")
	@Schema(description = "Request來源", required = true, example = "ECIP01-UI-01-001")
	@JsonProperty("RequestFrom")
	@Size(max=32,message="RequestFrom欄位長度過長")
	private String requestFrom;

	@NotBlank(message = "RequestTimestamp為必輸入欄位")
	@Schema(description = "來源端Request的時間戳", required = true, example = "2018-08-19 10:33:13")
	@JsonProperty("RequestTimestamp")
	@Size(min = 19, max = 19, message = "RequestTimestamp欄位長度錯誤")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "RequestTimestamp欄位格式錯誤")
	private String requestTimestamp; 
	
	@NotBlank(message = "ApiCallingFlow為必輸入欄位")
	@Schema(description = "API呼叫流程紀錄", required = true, example = "ECIP01-UI-01-001;")
	@JsonProperty("ApiCallingFlow")
	@Size(max=256,message="ApiCallingFlow欄位長度過長")
	private String apiCallingFlow;  
	
	public void addApiCallingURL(String url) {
		apiCallingFlow+=url+";"; 
	}
	
	
}
