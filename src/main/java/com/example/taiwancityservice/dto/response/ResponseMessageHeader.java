package com.example.taiwancityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseMessageHeader {

	@Schema(description = "使用者ID", required = true, example = "user1@gmail.com")
	@JsonProperty("UserId")
	private String userId; 
	
 
	@Schema(description = "企業統編", required = false, example = "70827406")
	@JsonProperty("EnterpriseId")
	private String enterpriseId; 
	

	@Schema(description = "處理結果代碼", required = true, example = "0000")
	@JsonProperty("ReturnCode")
	private String returnCode;

	@Schema(description = "處理結果訊息", required = true, example = "查詢成功")
	@JsonProperty("ReturnMsg")
	private String returnMsg;

	@Schema(description = "API呼叫流程紀錄", required = true, example = "ECIP01-UI-01-001;/v1/enterprise-data/query;")
	@JsonProperty("ApiCallingFlow")
	private String apiCallingFlow;

	@Schema(description = "API回應的時間戳", required = true, example = "2018-08-19 10:33:13")
	@JsonProperty("ResponseTimestamp")
	private String responseTimestamp;

}
