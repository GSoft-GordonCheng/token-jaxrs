package gordon.tokens.dto;

import java.io.Serializable;

/**
* @author Gordon
*/
public class TokenDTO implements Serializable {
	private String token;
	//
	private static final long serialVersionUID = 1L;
	
	public TokenDTO() {
	
	}
	public TokenDTO(String token) {
		this.token = token;
	}
	/*
	public TokenDTO(String token, String key) {
		this.token = token;
		//this.key = key;
	}*/
	public String getToken() {
		return this.token;
	}
	public void setToken(String value) {
		this.token = value;
	}
	/*
	public String getKey() {
		return this.key;
	}
	public void setKey(String value) {
		this.key = value;
	}*/
}
