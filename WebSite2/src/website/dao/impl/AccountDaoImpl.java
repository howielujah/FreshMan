package website.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import website.common.ParseXml;
import website.dao.AccountDao;

@Service("accountDao")
public class AccountDaoImpl implements AccountDao{
	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	//注入jdbcTemplate
	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	//取得user資料，可以自行編寫修改，請使用JdbcTemplate做操作
	@Override
	public Map<String, Object> getAccountByName(String name, String password) throws Exception {
		//String sql = "select * from account where account=? and password=?";
		String sql = ParseXml.getSqlByName("Account.checkAccount");
		Map<String, Object> results = null;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, name, password);
		if(list.size() >0) {
			results =list.get(0);
		}
		return results;
	}

	@Override
	public int insertAccount(String account, String password, String username, Date birthday, String phone)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = ParseXml.getSqlByName("Account.insertAccount");

        List<Object> condition = new ArrayList<Object>();
        condition.add(account);
        condition.add(password);
        condition.add(username);
        condition.add(birthday);
        condition.add(phone);
        int num = jdbcTemplate.update(sql, condition.toArray());
        return num;
	}

	@Override
	public Map<String, Object> getAccountByUser(String name) throws Exception {
		// TODO Auto-generated method stub
		String sql = ParseXml.getSqlByName("Account.checkUser");
		Map<String, Object> results = null;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, name);
		if(list.size() >0) {
			results =list.get(0);
		}
		return results;
	}
}
