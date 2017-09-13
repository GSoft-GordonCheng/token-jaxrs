package gordon.tokens.apis;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import gordon.tokens.agents.AgentDTO;
import gordon.tokens.agents.AgentListDTO;

/**
* @author Gordon
*/

@Path("/agents")
public class Agents {
	
	@GET
	@Path("/")
	public Response getAgents(
	) {
		AgentListDTO list = new AgentListDTO();

		AgentDTO af = new AgentDTO("AF", "AF", "#000000", true);
		AgentDTO ecs = new AgentDTO("ECS", "ECS", "#0000FF", true);
		list.add(af);
		list.add(ecs);
		//
		return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new Gson().toJson(list)).encoding("UTF-8")
                .build();	
	} 
}
