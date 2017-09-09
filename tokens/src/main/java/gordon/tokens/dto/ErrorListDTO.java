package gordon.tokens.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
* @author Gordon
*/
public class ErrorListDTO extends ArrayList<ErrorItemDTO>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ErrorListDTO() {
		super();
	}
	public ErrorListDTO(ErrorItemDTO item) {
		this.add(item);
	}

}
