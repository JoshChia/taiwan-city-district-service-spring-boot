package com.example.taiwancityservice.exception;

import com.example.taiwancityservice.dto.response.ResponseMessageHeader;
import com.example.taiwancityservice.dto.response.ResponseMessageTemplate;
import com.example.taiwancityservice.enums.ReturnCode;
import com.example.taiwancityservice.requestscope.RequestScopedContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ECIPResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final RequestScopedContext<?> requestScopedContext;

	@ExceptionHandler(ECIPException.class)
	public ResponseEntity<ResponseMessageTemplate<?>> exception(ECIPException ex) {

		ResponseMessageTemplate<HashMap<Object,Object>> responseMessageTemplate = new ResponseMessageTemplate<>(
				requestScopedContext.getRequestMessageTemplate(), new HashMap<Object,Object>());

  
		// set header data
		ResponseMessageHeader header = responseMessageTemplate.getResponseMessageHeader();
		header.setReturnCode(ex.getReturnCode().getCode());
		String msg = ex.getReturnCode().getMessage();
		if (StringUtils.isNotBlank(ex.getMsg())) {
			msg = ex.getMsg();
		}
		header.setReturnMsg(msg);

		return new ResponseEntity<ResponseMessageTemplate<?>>(responseMessageTemplate, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessageTemplate<?>> exception(Exception ex) {

		log.error("Unknown Error Happened, " + ex.getMessage());
		
		ResponseMessageTemplate<HashMap<Object,Object>> responseMessageTemplate = new ResponseMessageTemplate<>(
				requestScopedContext.getRequestMessageTemplate(), new HashMap<Object,Object>());

 

		// set header data
		ResponseMessageHeader header = responseMessageTemplate.getResponseMessageHeader();
		header.setReturnCode(ReturnCode.UNKNOWN_ERROR.getCode());

		if (StringUtils.isNotBlank(ex.getMessage())) {
			header.setReturnMsg(ex.getMessage());
		} else {
			header.setReturnMsg(ReturnCode.UNKNOWN_ERROR.getMessage());
		}

		return new ResponseEntity<ResponseMessageTemplate<?>>(responseMessageTemplate, HttpStatus.OK);
	}

	private ResponseMessageTemplate<?> handleBindingResultExceptionInternal(BindingResult bindingResult) {
 
		
		ResponseMessageTemplate<HashMap<Object,Object>> responseMessageTemplate = new ResponseMessageTemplate<>(
				requestScopedContext.getRequestMessageTemplate(), new HashMap<Object,Object>());


		// set header data
		ResponseMessageHeader header = responseMessageTemplate.getResponseMessageHeader();
		header.setReturnCode(ReturnCode.FIELD_FORMAT_ERROR.getCode());
		Optional<FieldError> first = bindingResult.getFieldErrors().stream().findFirst();
		if (first.isPresent()) {
			header.setReturnMsg(first.get().getDefaultMessage());
		} else {
			header.setReturnMsg(ReturnCode.UNKNOWN_ERROR.getMessage());
		}

		return responseMessageTemplate;
	}

	private ResponseMessageTemplate<?> handleHttpMessageNotReadableInternal(HttpMessageNotReadableException ex) {
 
		
		ResponseMessageTemplate<HashMap<Object,Object>> responseMessageTemplate = new ResponseMessageTemplate<>(
				requestScopedContext.getRequestMessageTemplate(), new HashMap<Object,Object>());
 

		// set header data
		ResponseMessageHeader header = responseMessageTemplate.getResponseMessageHeader();
		header.setReturnCode(ReturnCode.JSON_FORMAT_ERROR.getCode());
		header.setReturnMsg(ReturnCode.JSON_FORMAT_ERROR.getMessage());

		return responseMessageTemplate;
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		return new ResponseEntity<>(this.handleBindingResultExceptionInternal(ex.getBindingResult()), HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(this.handleBindingResultExceptionInternal(ex.getBindingResult()), HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(this.handleHttpMessageNotReadableInternal(ex), HttpStatus.OK);
	}
}
