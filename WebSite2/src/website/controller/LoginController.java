package website.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import website.model.AccountModel;

@Controller
public class LoginController {
	
	private AccountModel accountModel;
	private static final String usrregex ="^([a-zA-Z])([A-Za-z0-9_\\.]){5,11}$";
	private static final String passwdregex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[~!@#$%^&*()_+])[0-9A-Za-z~!@#$%^&*()_+]{8,}$"; // (8~)英文/數字/特殊符號
	private static final String unregex = "^[\\u4e00-\\u9fa5_a-zA-Z]{1,30}$"; // (1~30) 中文/英文/_
	private static final String birthregex = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$"; // (YYYY/MM/DD)
	private static final String phoneregex = "^09[0-9]{8}$"; // 09xxxxxxxx
	
	@Resource(name = "accountModel")
	public void setAccountModel(AccountModel accountModel) {
		this.accountModel = accountModel;
	}

	// 處理登入動作
	@RequestMapping("/login.do")
	public ModelAndView handleLogin(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		Map<String, Object> loginError = new HashMap<String, Object>();
		req.setAttribute("loginError", loginError);

		try {
			req.setCharacterEncoding("UTF-8");
			String name = req.getParameter("userName");
			if (name== null || (name.trim()).length() == 0) {
				loginError.put("userNameError","請輸入帳號");
			}
			
			String password = req.getParameter("password");
			if (password == null || (password.trim()).length() == 0) {
				loginError.put("passwordError","請輸入密碼");
			}
			//如果帳號或密碼沒輸入就跳回login.jsp
			if (!loginError.isEmpty()) {
				return new ModelAndView("login",loginError);
			}

			// 檢核使用者登入帳密
			String md5pswd = accountModel.getMD5(password);
			Map<String, Object> resultMap = accountModel.checkAccount(name, md5pswd);
			// 請實做：根據登入是否成功，回傳相對應ModelView
			if (resultMap != null) {
				HttpSession session = req.getSession();
				session.setAttribute("name", resultMap.get("name"));
				// Authorise設1
				session.setAttribute("Authorise", 1);
				return new ModelAndView("index");
			} else
				return new ModelAndView("login", "errMsg", "帳號或密碼錯誤");
			
		} catch (Exception e) {
			
			return new ModelAndView("login", "errMsg", e.getMessage());
		}
	}

	@RequestMapping("/industryList.do")
    public ModelAndView doIndustry(HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        return new ModelAndView("industryList");
    }

    @RequestMapping("/logout.do")
	public ModelAndView handleLogout(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
    	//把session的Authorise移除
		req.getSession().removeAttribute("Authorise");
		//把session的name移除
		req.getSession().removeAttribute("name");
		return new ModelAndView("login");
	}
    
    @RequestMapping("/nologin.do")
	public ModelAndView goto404(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		return new ModelAndView("404");
	}
    
    @RequestMapping("/register.do")
	public ModelAndView registerNewAccount(HttpServletRequest req,
			HttpServletResponse res)  {
    	
		Map<String, Object> errorMsgs = new HashMap<String, Object>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			req.setCharacterEncoding("UTF-8");
			String account = req.getParameter("account");
			if (!account.matches(usrregex)) {
				errorMsgs.put("usrError", "(6~12)英文開頭、後面接英文/數字/_/.");
			}			
			else if(accountModel.checkUser(account) != null) {
				errorMsgs.put("usrError", "此帳號已有人用");
			}
			
			String password = req.getParameter("pswd");
			if (!password.matches(passwdregex)) {
				errorMsgs.put("passwdError", "(8~12)英文/數字/@");
			}

			String pswdconfirm = req.getParameter("pswdconfirm");
			if (!pswdconfirm.equals(password)) {
				errorMsgs.put("pswdconfirmError", "輸入要跟密碼一樣");
			}
			String username = req.getParameter("username");
			if (!username.matches(unregex)) {
				errorMsgs.put("unError", "(1~30) 中文/英文/_");
			}

			String birthday = req.getParameter("birthday");
			Date sqlDate = null;
			if (!birthday.matches(birthregex)) {
				errorMsgs.put("birthError", "(YYYY/MM/DD)");
			} else {
				// birthday字串轉成sql.Date
				String pattern = "yyyy-MM-dd";
				java.util.Date parseDate = new SimpleDateFormat(pattern).parse(birthday);
				sqlDate = new Date(parseDate.getTime());
			}

			String phone = req.getParameter("phone");
			if (!phone.matches(phoneregex)) {
				errorMsgs.put("phoneError", "09xxxxxxxx");
			}
			
			//	若errorMsgs不為空，返回login.jsp
			if (!errorMsgs.isEmpty()) {
				return new ModelAndView("login");
			}
			
			String md5pswd = accountModel.getMD5(password);
			
			accountModel.insertAccount(account, md5pswd, username, sqlDate, phone);
			//如果前端有這個cookie代表註冊成功，會有alert視窗
			Cookie mycookie = new Cookie("registered","success");
			res.addCookie(mycookie);
			return new ModelAndView("redirect:/");
		} catch (Exception e) {
			return new ModelAndView("login", "errMsg", e);
		}
	}
}
