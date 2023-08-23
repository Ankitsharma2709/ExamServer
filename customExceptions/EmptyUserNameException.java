package com.exam.customExceptions;

import org.springframework.stereotype.Component;

@Component
public class EmptyUserNameException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public EmptyUserNameException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public EmptyUserNameException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmptyUserNameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public EmptyUserNameException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public EmptyUserNameException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public EmptyUserNameException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
