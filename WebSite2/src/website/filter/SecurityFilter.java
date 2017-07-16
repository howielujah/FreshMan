package website.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest)req;
    	HttpServletResponse response = (HttpServletResponse)res;
    	HttpSession session = request.getSession();
    	
    	//如果他沒登入且他打算做的事不是登入或是註冊則跳到404.jsp
    	if(session.getAttribute("Authorise")==null && !request.getRequestURI().equals("/Website/login.do")
    			&& !request.getRequestURI().equals("/Website/register.do")) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("nologin.do");
    		dispatcher.forward(request, response);
    		return;
    	}else {
    		//FilterChain的doFilter(request, response)方法，會將程式控制權交給後續過濾器
    		filterChain.doFilter(req, res); 
    	}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
