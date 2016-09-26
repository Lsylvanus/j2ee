package com.lsylvanus.servlet.logo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.impl.LogoDAOImpl;
import com.lsylvanus.model.Logn;
import com.lsylvanus.servlet.BaseServlet;

public class LognChecking extends BaseServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		PrintWriter out = response.getWriter();
		Logn logo = null;
		log.debug(request.getParameter("value"));
		log.debug(request.getParameter("valuePassword"));
		if(!request.getParameter("value").equals("")&&!request.getParameter("valuePassword").equals("")){
			LogoDAOImpl logoDao = new LogoDAOImpl();
			Logn logn = new Logn();
			logn.setLognNumber(request.getParameter("value"));
			logn.setPassword(request.getParameter("valuePassword"));
		  	logo = (Logn) logoDao.logoChecking(logn);
		  	if(logo==null) {
				out.print("用户名或密码错误");
			} else {
				out.print("");
			}
		} else if(!request.getParameter("value").equals("")){
		  	LogoDAOImpl logoDao = new LogoDAOImpl();
		  	Logn logn = new Logn();
			logn.setLognNumber(request.getParameter("value"));
			logn.setPassword(request.getParameter("valuePassword"));
		  	logo = (Logn) logoDao.logoChecking(logn);
		  	if(logo==null) {
				out.print("用户名未注册");
			} 
		}
	}
}