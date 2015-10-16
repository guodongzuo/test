package com.jisheng.update;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UpdateService service = new UpdateService();
		String method = request.getParameter("method");
		if("query".equals(method)){
			String order = request.getParameter("order");
			Map<String, String> map = service.queryData(order);
			if(null==map){
				request.setAttribute("message", "定单" + order + "下没有找到数据");
				request.setAttribute("order", order);
			}else{
				request.setAttribute("order", map.get("order"));
				request.setAttribute("phone", map.get("mobile"));
				request.setAttribute("name", map.get("business_name"));
				request.setAttribute("balance", map.get("balance"));
			}
			RequestDispatcher req = request.getRequestDispatcher("/index.jsp");
			req.forward(request, response);
		}else if("update".equals(method)){
			String order = request.getParameter("order");
			String phone = request.getParameter("phone");
			String balance = request.getParameter("balance");
			if(service.updateDate(phone, balance)){
				request.setAttribute("message", "数据修改成功");
			}else{
				request.setAttribute("message", "数据修改失败");
			}
			RequestDispatcher req = request.getRequestDispatcher("/UpdateServlet?method=query&order="+order);
			req.forward(request, response);
		}
		
	}

}
