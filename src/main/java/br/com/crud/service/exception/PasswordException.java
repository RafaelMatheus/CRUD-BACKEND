package br.com.crud.service.exception;

public class PasswordException extends RuntimeException{
	
		private static final long serialVersionUID = 1L;
		
		public PasswordException(String arg) {
			super(arg);
		}
		public PasswordException(String arg, Throwable cause) {
			super(arg, cause);
		}

}
