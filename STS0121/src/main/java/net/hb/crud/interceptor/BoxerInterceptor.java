package net.hb.crud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.hb.crud.BoxerDTO;


public class BoxerInterceptor implements HandlerInterceptor{
	private static final Logger logger=LoggerFactory.getLogger(BoxerInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
    logger.info("=======================afterCompletion start========================");
		logger.info("=======================afterCompletion end=========================");

	}//end
			
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	  logger.info("=======================postHandle start======================");
		logger.info("=======================postHandle end=========================");	
	}//end

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//세션을 찾아온다
		HttpSession session = request.getSession();
		BoxerDTO boxer = (BoxerDTO)session.getAttribute("LOGIN");
		
		//로그인이 안된 상태에서 요청을 햇을 때 로그인 한 후 요청한 페이지로 이동하도록 하기 위해서 요청한 주소를 세션에 저장한 후 로그인이 성공했을 때 그 주소로 이동하도록 하면 된다.
		String uri = request.getRequestURI();
        
       	 	//프로젝트 이름까지 찾아오기때문에 주소만  http://127.0.0.1:8081/order/delete.do
       	 	int idx = uri.lastIndexOf("/");
		    uri = uri.substring(idx+1);
		
       		//파라미터 찾아오기
		String query = request.getQueryString();
		
		//파라미터가 없으면 요청 주소는 그대로 
		if(query==null || query.equals("null")){
			query="";
		}
		else{
			query="?"+query;
		}
		uri = uri + query;
		// 세션에 uri 저장 
		session.setAttribute("dest",uri);
		boolean adminCheck = false;
		//로그인이 안된 경우 로그인 페이지로 이동
		if(boxer!=null){
			if(boxer.getUsergrade()!=1) {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().append("<script>"
						+ "alert('관리자 권한이 없습니다.');"
			        		 + "window.history.back();"
						  + "</script>").flush();
			}
			else {
				adminCheck=true;
			}
			
		}
		else {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append("<script>"
							+ "alert('로그인이필요합니다.');"
				        		 + "location.href='login';"
							  + "</script>").flush();
			
		}
		return adminCheck;
	}
}//class END