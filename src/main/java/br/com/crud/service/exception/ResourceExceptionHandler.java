package br.com.crud.service.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ValidationError.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFoundException,
			HttpServletRequest httpServletRequest) {
		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Não encontrado", objectNotFoundException.getMessage(), httpServletRequest.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

	}
}