package gordon.news.dto;

import java.io.Serializable;

/**
* @author Gordon
*/
public class NewsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String subject;
	
	public NewsDTO() {
		
	}
	
	public NewsDTO(String subject) {
		this.subject = subject;
	}
	
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(String value) {
		this.subject = value;
	}
}
