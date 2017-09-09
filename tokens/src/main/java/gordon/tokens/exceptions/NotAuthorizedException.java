package gordon.tokens.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
/**
* @author Gordon
*/
public class NotAuthorizedException extends WebApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAuthorizedException(){
		super(Response.status(UNAUTHORIZED)
                .type(MediaType.TEXT_PLAIN_TYPE)
                .entity(UNAUTHORIZED.toString())
                .build());		
	}
}
