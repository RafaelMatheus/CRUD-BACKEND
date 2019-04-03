package br.com.crud.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ValidationError extends RuntimeException{
	
		private static final long serialVersionUID = 1L;
		
		public ValidationError(String arg) {
			super(arg);
		}
		public ValidationError(String arg, Throwable cause) {
			super(arg, cause);
		}

}
