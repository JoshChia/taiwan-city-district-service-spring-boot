package com.example.taiwancityservice.requestscope;

import com.example.taiwancityservice.dto.request.RequestMessageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ECIPRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {
  private final com.example.taiwancityservice.requestscope.RequestScopedContext requestScopedContext;

  @Override
  public boolean supports(MethodParameter methodParameter,
      Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object afterBodyRead(Object body,
      HttpInputMessage inputMessage,
      MethodParameter parameter,
      Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {  
    requestScopedContext.setRequestMessageTemplate((RequestMessageTemplate) body);
    return body;
  }

}
