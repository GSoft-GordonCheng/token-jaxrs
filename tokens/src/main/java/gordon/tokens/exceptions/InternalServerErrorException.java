package gordon.tokens.exceptions;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import gordon.tokens.dto.ErrorListDTO;
import gordon.tokens.dto.ErrorItemDTO;

/**
* @author Gordon
*/
public class InternalServerErrorException extends WebApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InternalServerErrorException(){
		this(new ErrorListDTO(new ErrorItemDTO("SE9999", "unknown")));
	}
	public InternalServerErrorException(ErrorListDTO dto){
		super(Response.status(INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new Gson().toJson(dto)).encoding("UTF-8")
                .build());		
	}
}