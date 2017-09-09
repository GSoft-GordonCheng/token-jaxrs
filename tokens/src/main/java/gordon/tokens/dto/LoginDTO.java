package gordon.tokens.dto;

import java.io.Serializable;

/**
* @author Gordon
*/
public class LoginDTO implements Serializable {

	private String id;
	private String pwd;
	private static final long serialVersionUID = 1L;
	
	public LoginDTO() {
		// Avoid 415 Unsupported Media Type	
	}
	public LoginDTO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	public String getId() {
		return this.id;
	}
	public String getPwd() {
		return this.pwd;
	}
	public void setId(String value) {
		this.id = value;
	}
	public void setPwd(String value) {
		this.pwd = value;
	}
}

