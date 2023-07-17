package com.ke.mytest.event;

import lombok.Data;

/**
 * @author Zhang Xudong
 * @date 2023/7/13 12:25
 */
@Data
public class RegisterSuccessEvent {
	private String userName;

	public RegisterSuccessEvent(String userName) {
		this.userName = userName;
	}
}
