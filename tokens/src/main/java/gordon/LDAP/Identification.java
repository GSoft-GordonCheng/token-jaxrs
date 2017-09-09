package gordon.LDAP;
/**
* @author Gordon
*/
public interface Identification {
	public ADUser authenticate(String id, String pwd);
}
