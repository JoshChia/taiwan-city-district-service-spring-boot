package com.example.taiwancityservice.requestscope;

import com.example.taiwancityservice.dto.request.RequestMessageTemplate;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@Component
@RequestScope
public class RequestScopedContext<T> {
	
	 private RequestMessageTemplate<T> requestMessageTemplate; 
}
