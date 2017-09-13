package gordon.tokens.apis;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import gordon.tokens.dto.ErrorDTO;
import gordon.tokens.dto.ErrorListDTO;
import gordon.tokens.exceptions.BadRequestException;
import gordon.tokens.exceptions.InternalServerErrorException;

/**
* @author Gordon
*/

@Path("/errors")
public class Errors {

	/**
	 * sample
	 */
	@GET
	@Path("/{count}/")
	public Response throwException(
			@DefaultValue("5") @PathParam("count") int count
	) {
		int cnt = count < 0 ? 5 : count;
		if (cnt > 5) {
			throw new BadRequestException("CE0008", "Input is invalid.", "count");
		}
		ErrorListDTO list = new ErrorListDTO();
		for (int i=0; i<cnt; i++) {
			String code = String.format("CE00%d", i+1);
			String msg = String.format("Message...%d", i+1);
			String field = String.format("%d", i+1); 
			ErrorDTO e1 = new ErrorDTO(code, msg, field);
			list.add(e1);
		}
		throw new InternalServerErrorException(list);
		//return null;
	}
}
