package gordon.tokens.dto;
/**
* @author Gordon
*/
public class ErrorFieldDTO extends ErrorItemDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String field;
	public ErrorFieldDTO() {
		super();
	}
	public ErrorFieldDTO(String code, String msg) {
		super(code, msg);
	}
	public ErrorFieldDTO(String code, String msg, String field) {
		super(code, msg);
		this.field = field;
	}
	public String getField() {
		return this.field;
	}
	public void setField(String value) {
		this.field = value;
	}
	
}
