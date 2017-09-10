package gordon.tokens.exceptions;

import gordon.tokens.dto.ErrorItemDTO;
import gordon.tokens.dto.ErrorListDTO;

/**
* @author Gordon
*/
public class InternalJedisError extends InternalServerErrorException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public InternalJedisError(){
			super(new ErrorListDTO(new ErrorItemDTO("SE9999", "InternalJedisError")));
		}
}