package gordon.LDAP;
/**
* @author Gordon
*/
public class AD1 implements Identification {

	@Override
	public ADUser authenticate(String id, String pwd) {
		ADUser usr = null;
		String test = this.getClass().getName();
		// TODO : 驗證
		if (test.indexOf(id)>-1) {
			// 登入成功後，取得AD帳戶名稱
			usr = new ADUser(id, this.getClass().getName());
		}
		return usr;
	}
}
