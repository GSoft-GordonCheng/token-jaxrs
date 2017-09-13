package gordon.tokens.apis;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import gordon.news.dto.CategoriesDTO;
import gordon.news.dto.CategoryDTO;
import gordon.news.dto.NewsDTO;
import gordon.news.dto.NewsListDTO;

/**
* @author Gordon
*/

@Path("/news")
public class News {

	@GET
	@Path("/categories")
	public Response getCategories(
	) {
		CategoriesDTO dto = new CategoriesDTO();
		CategoryDTO c1 = new CategoryDTO("ALL","全部","","");
		CategoryDTO c2 = new CategoryDTO("EIP","企業公告","","");
		dto.add(c1);
		dto.add(c2);
		// return dto;
		return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new Gson().toJson(dto)).encoding("UTF-8")
                .build();			
	}
	
	@GET
	@Path("/home")
	public Response getNewsByHome(
	) {
		NewsListDTO dto = new NewsListDTO();
		for (int i=0; i<5; i++) {
			NewsDTO news = new NewsDTO(String.format("home-%d", i+1));
			dto.add(news);
		}
		//return dto;
		return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new Gson().toJson(dto)).encoding("UTF-8")
                .build();					
	}	

	@GET
	@Path("/{category}/")
	public Response getNewsBycategory(
			@DefaultValue("") @PathParam("category") String category
	) {
		if ("".equals(category)) {
			throw new BadRequestException("category is null");
		}
		NewsListDTO dto = new NewsListDTO();
		for (int i=0; i<5; i++) {
			NewsDTO news = new NewsDTO(String.format("%s-%d", category, i+1));
			dto.add(news);
		}
		return Response.ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new Gson().toJson(dto)).encoding("UTF-8")
                .build();					

	}	
}
