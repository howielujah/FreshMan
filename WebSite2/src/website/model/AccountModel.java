package website.model;

import java.sql.Date;
import java.util.Map;

public interface AccountModel {
	
	 Map<String, Object> checkAccount(String name, String password) throws Exception;
	 int insertAccount(String account, String password,String username,Date birthday,String phone) throws Exception;
	 Map<String, Object> checkUser(String name) throws Exception;
	 String getMD5(String str);
}
