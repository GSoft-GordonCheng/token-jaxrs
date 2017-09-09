package gordon.tokens.dto;

import java.io.Serializable;

/**
* @author Gordon
*/
public class VerifyDTO implements Serializable {
	private String owner;
	private String token;

	private static final long serialVersionUID = 1L;
	
	public VerifyDTO() {
	
	}
	public VerifyDTO(String owner) {
		this.owner = owner;
	}
	public String getId() {
		return this.owner;
	}
	public void setId(String value) {
		this.owner = value;
	}
}
