package com.lti.dto;

import java.io.Serializable;

public class UserCompKey implements Serializable {

	int user_id;
	String email;

	public UserCompKey() {
	}

	public UserCompKey(int user_id, String email) {
		this.user_id = user_id;
		this.email = email;
	}

}
