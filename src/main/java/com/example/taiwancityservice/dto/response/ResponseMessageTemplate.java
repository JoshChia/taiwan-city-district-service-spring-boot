package com.example.taiwancityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.example.taiwancityservice.dto.request.RequestMessageTemplate;
import com.example.taiwancityservice.enums.ReturnCode;
import com.example.taiwancityservice.util.DateUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Header", "Body" })
@Data
public class ResponseMessageTemplate<T> {

	@JsonProperty("Header")
	@Schema(required = true)
	private ResponseMessageHeader responseMessageHeader;

	@JsonProperty("Body")
	@Schema(required = true)
	private T responseMessageBody;

	ResponseMessageTemplate() {
	}

	public ResponseMessageTemplate(RequestMessageTemplate<?> requestMessageTemplate, T responseMessageBody) {

		// init responseMessageHeader
		responseMessageHeader = new ResponseMessageHeader();

		if (requestMessageTemplate != null) {
			responseMessageHeader.setUserId(requestMessageTemplate.getRequestMessageHeader().getUserId()); 
			responseMessageHeader.setEnterpriseId(requestMessageTemplate.getRequestMessageHeader().getEnterpriseId());

			responseMessageHeader
					.setApiCallingFlow(requestMessageTemplate.getRequestMessageHeader().getApiCallingFlow());
		}
		responseMessageHeader.setReturnCode(ReturnCode.SUCCESS.getCode());
		responseMessageHeader.setReturnMsg(ReturnCode.SUCCESS.getMessage());
		responseMessageHeader.setResponseTimestamp(DateUtil.formatDateTime(DateUtil.dateTimeFormatter, LocalDateTime.now()));

		this.responseMessageBody = responseMessageBody;
	}

}
