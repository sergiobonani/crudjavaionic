package com.sergiobonani.crudjavaionic.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message){
		errors.add(new FieldMessage(fieldName, message));
	}

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}
}
