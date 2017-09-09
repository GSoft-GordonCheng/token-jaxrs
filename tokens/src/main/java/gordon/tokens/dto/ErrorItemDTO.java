package gordon.tokens.dto;

import java.io.Serializable;

/**
* @author Gordon
*/
public class ErrorItemDTO implements Serializable {
	private String code;
	private String message;
	private static final long serialVersionUID = 1L;
	
	public ErrorItemDTO() {
	
	}
	public ErrorItemDTO(String code, String msg) {
		this.code = code;
		this.message = msg;
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(String value) {
		this.code = value;
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String value) {
		this.message = value;
	}
}