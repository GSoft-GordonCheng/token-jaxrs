package gordon.tokens.agents;

import java.io.Serializable;

/**
* @author Gordon
*/
public class AgentDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String title;
	
	private String color;
	
	private Boolean isDynamic = false;
	
	public AgentDTO() {
		
	}
	
	public AgentDTO(String id, String title, String color, Boolean isDynamic) {
		this.id = id;
		this.title = title;
		this.color = color;
		this.isDynamic = null==isDynamic ? false : isDynamic;
	}
	
	public void setId(String value) {
		this.id = value;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	public void setBanner(String value) {
		this.color = value;
	}
	public void setRrlRef(String value) {
		this.isDynamic = null==isDynamic ? false : isDynamic;
	}	
	public String getId() {
		return this.id ;
	}
	public String getTitle() {
		return this.title;
	}
	public String getBanner() {
		return this.color;
	}
	public Boolean getRrlRef() {
		return this.isDynamic;
	}
}
