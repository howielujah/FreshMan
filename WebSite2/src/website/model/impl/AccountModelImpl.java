package website.model.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import website.dao.AccountDao;
import website.model.AccountModel;

@Service("accountModel")
public class AccountModelImpl implements AccountModel{
	
	private AccountDao accountDao;
	
	@Resource(name = "accountDao")
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	// 檢查使用者登入帳號密碼
	@Override
	public Map<String, Object> checkAccount(String name, String password) throws Exception {
		//如果查不到該帳戶回傳null
		return accountDao.getAccountByName(name, password);
	}

	@Override
	public int insertAccount(String account, String password, String username, Date birthday, String phone)
			throws Exception {
		// TODO Auto-generated method stub
		return accountDao.insertAccount(account, password, username, birthday, phone);
	}
	// 檢查使用者登入帳號
	@Override
	public Map<String, Object> checkUser(String name) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.getAccountByUser(name);
	}

	@Override
	public String getMD5(String str) {
		// TODO Auto-generated method stub
		String md5str = str;
		try {
			/*java.security.MessageDigest類用於為應用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
			 	簡單點說就是用於生成雜湊值。*/
			
			// 生成一個MD5加密計算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			// 計算md5函數，該物件通過使用 update方法處理資料
	        md.update(str.getBytes());
	        byte[]b = md.digest();
	        // digest()最後確定返回md5 hash值，返回值為byte陣列，一個byte陣列裡的元素表示一個數字
	        // BigInteger函數則將byte陣列轉換成16位hex值，用字串來表示；得到字串形式的hash值
	        md5str = new BigInteger(1, b).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5str;
	}
}
