package com.qsp.student_management_system_updated.util;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String message;
	private int status;
	private T data;

}
