package com.example.taiwancityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Header", "Body" })
@Data 

public class RequestMessageTemplate<T> {

	@Valid
	@JsonProperty("Header")
	@NotNull(message = "未傳入Header，請確認")
	@Schema(required = true)
	private RequestMessageHeader requestMessageHeader;

	@Valid
	@JsonProperty("Body")
	@NotNull(message = "未傳入Body，請確認")
	@Schema(required = true)
	private T requestMessageBody;

}
