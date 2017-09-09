package gordon.LDAP;
/**
* @author Gordon
*/
public class ADUser {
	String id;
	String name;
	public ADUser(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
}
