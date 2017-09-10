package gordon.tokens.apis;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;

import gordon.LDAP.LDAP;
import gordon.LDAP.ADUser;
import gordon.tokens.core.Token;
import gordon.tokens.core.XToken;
import gordon.tokens.dto.ErrorListDTO;
import gordon.tokens.dto.ErrorFieldDTO;
import gordon.tokens.dto.ErrorItemDTO;
import gordon.tokens.dto.LoginDTO;
import gordon.tokens.dto.TokenDTO;
import gordon.tokens.dto.VerifyDTO;
import gordon.tokens.exceptions.InternalJedisError;
import gordon.tokens.exceptions.InternalServerErrorException;
import gordon.tokens.exceptions.NotAuthorizedException;
import redis.clients.jedis.exceptions.JedisDataException;

/**
* @author Gordon
*/
@Path("/")
public class SSO {
	
	@GET
	@Path("/{token}")
	public VerifyDTO verify(
			@DefaultValue("") @PathParam("token") String token
	) {
		/*	@Encoded
		{
		    "key": "nTv4MY+RnXk=",
		    "token": "U1v5jJmBP08="
		}
		// 反查
		// 1. 驗證令牌(取得密鑰)
		// 2. 反解令牌
		// 3. 延長令牌時效.AND.更新
		*/
		// System.out.println(token);
		VerifyDTO dto = null;
		try
		{
			String value = Token.findByToken(token);
			if (null != value && value.length() > 0) {
				String owner = XToken.decode(token, value);
				dto = new VerifyDTO(owner);
				//
				// 自動延期
				//
			}
		} catch (JedisDataException e) {
			throw new InternalJedisError();			
		} catch (Exception e) {
			throw new InternalServerErrorException();
		}
		if (null != dto) {
			return dto;
		} else {
			throw new NotAuthorizedException();			
		}
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public TokenDTO login(
			LoginDTO user
	) {
		TokenDTO dto = null;
		try {
			// 登入
			ADUser usr = LDAP.login(user.getId(), user.getPwd());
			if (null != usr) {
				// 產生令牌
				Token token = new XToken(user.getId()); // new JWTToken(user.getId());
				// 私鑰
				String key = token.generate();
				// 令牌
				String value = token.getKeyString();
				// 回存私鑰+令牌
				token.save(key, value);
				// 
				dto = new TokenDTO(key, value);	
			}
		} catch (JedisDataException e) {
			e.printStackTrace(); // write to log file
			throw new InternalJedisError();
		} catch (Exception e ) {
			e.printStackTrace(); // write to log file
			throw new InternalServerErrorException();
		}
		if (null != dto) {
			return dto;
		} else {
			throw new NotAuthorizedException();			
		}		
	}	
	
	@DELETE
	@Path("/{token}")
	public Response logout(
		@DefaultValue("") @Encoded @PathParam("token") String token
	) {
		// 登出/撤銷令牌	
		try {
			Token.deleteByToken(token);
		} catch (JedisDataException e) {
			throw new InternalJedisError();
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException();
		}
		return Response.noContent().build();
	}
}
