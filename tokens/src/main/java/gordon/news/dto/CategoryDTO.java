package gordon.news.dto;

import java.io.Serializable;

/**
* @author Gordon
*/
public class CategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String banner;
	private String urlRef;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(String id, String title, String banner, String urlRef) {
		this.id = id;
		this.title = title;
		this.banner = banner;
		this.urlRef = urlRef;
	}
	
	public void setId(String value) {
		this.id = value;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	public void setBanner(String value) {
		this.banner = value;
	}
	public void setRrlRef(String value) {
		this.urlRef = value;
	}
	public String getId() {
		return this.id ;
	}
	public String getTitle() {
		return this.title;
	}
	public String getBanner() {
		return this.banner;
	}
	public String getRrlRef() {
		return this.urlRef;
	}

}
