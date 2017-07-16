package website.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface AccountDao {
	 Map<String, Object> getAccountByName(String name, String password) throws Exception;
	 int insertAccount(String account, String password,String username,Date birthday,String phone) throws Exception;
	 Map<String, Object> getAccountByUser(String name) throws Exception;
}
