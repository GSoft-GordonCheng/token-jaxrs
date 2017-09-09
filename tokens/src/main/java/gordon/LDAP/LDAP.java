package gordon.LDAP;

import java.util.ArrayList;

/**
* @author Gordon
*/
public class LDAP {
	private static ArrayList<Identification> items = new ArrayList<Identification>();
	public static ADUser login(String id, String pwd) {
		if (items.size()<=0) {
			items.add(new AD1());
			items.add(new AD2());
			items.add(new AD3());
			// Add Others...
		}
		ADUser usr = null;
		for (int i = 0; i <items.size(); i++) {
			Identification AD = items.get(i);
			usr = AD.authenticate(id, pwd);
			if (null != usr) {
				// 優先登入即可停止
				break;
			}
		}
		return usr;
	}
}
