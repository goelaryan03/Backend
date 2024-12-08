package com.chatApp.backend.exception;

public class MethodArgumentNotValidException extends Exception {
	public MethodArgumentNotValidException(String message){
		super(message);}

	public Object getBindingResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
