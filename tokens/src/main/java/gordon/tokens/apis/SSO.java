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
import gordon.tokens.exceptions.InternalServerErrorException;
import gordon.tokens.exceptions.NotAuthorizedException;

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
		/*
		{
		    "key": "nTv4MY+RnXk=",
		    "token": "U1v5jJmBP08="
		}
		// 反查
		// 1. 驗證令牌(取得密鑰)
		// 2. 反解令牌
		// 3. 延長令牌時效.AND.更新
		
		*/
		String key = Token.findByToken(token);
		if (key.length() > 0) {
			try {
				String owner = XToken.decode(token, key);
				VerifyDTO dto = new VerifyDTO(owner);
				return dto;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		throw new NotAuthorizedException();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public TokenDTO login(
			LoginDTO user
	) {
		// 登入
		ADUser usr = LDAP.login(user.getId(), user.getPwd());
		if (null != usr) {
			//Token token = new JWTToken(user.getId());
			Token token = new XToken(user.getId());
			// 產生令牌
			try {
				String tokenString = token.generate();
				String keyString = token.getKeyString();

				System.out.println(tokenString);
				System.out.println(keyString);
				//System.out.println(XToken.decode(tokenString, "999"));
				// 回存令牌
				// token.save();
				// 返回令牌
				return new TokenDTO(tokenString, keyString);	
			} catch (Exception e ) {
				e.printStackTrace();
				/*
				 * 測試錯誤碼DTO約定格式
				 */
				ErrorListDTO dto = new ErrorListDTO();
				ErrorItemDTO e1 = new ErrorItemDTO("CE8888", "create token error");
				ErrorFieldDTO e2 = new ErrorFieldDTO("CE5555", "create token error", "title");
				dto.add(e1);
				dto.add(e2);
				
				throw new InternalServerErrorException(dto);
			}
		}
		throw new NotAuthorizedException();
	}	
	
	@DELETE
	@Path("/{token}")
	public Response logout(
		@DefaultValue("") @PathParam("token") String token
	) {
		// 登出/撤銷令牌	
		try {
			Token.deleteByToken(token);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Response.noContent().build();
	}
}
