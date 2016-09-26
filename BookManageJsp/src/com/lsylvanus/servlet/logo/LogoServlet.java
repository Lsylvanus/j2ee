package com.lsylvanus.servlet.logo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.ILogoDAO;
import com.lsylvanus.dao.impl.LogoDAOImpl;
import com.lsylvanus.model.Logn;
import com.lsylvanus.servlet.BaseServlet;

/**
 * µÇÂ½servlet ¼Ì³ÐBaseServlet
 * @author Lsylvanus
 *
 */
public class LogoServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
		super.doGet(request, response);
	}

	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
		super.doPost(request, response);
		try{
		Logn logn = new Logn();
		log.debug(request.getParameter("logo"));
		logn.setLognNumber(request.getParameter("logo"));
		logn.setPassword(request.getParameter("password"));
		ILogoDAO logoDao = new LogoDAOImpl();
		logn = logoDao.logoChecking(logn);
		Cookie cookie = new Cookie("logo",request.getParameter("logo"));
		Cookie password = new Cookie("password",request.getParameter("password"));
		response.addCookie(cookie);
		response.addCookie(password);
		request.getSession().setAttribute("logo", logn);
		log.debug(logn.getName());
		request.getSession().setAttribute("name", logn.getName());
		cookie.setMaxAge(7*24*60);
		password.setMaxAge(7*24*60);
		if(logn!=null){
			request.setAttribute("lognChecking", "4");
			request.setAttribute("name",logn.getName());
			log.debug(logn.getName());
			request.getRequestDispatcher("../index.jsp").forward(request, response);
		} else {
			request.setAttribute("value", "ÓÃ»§Ãû»òÃÜÂë´íÎó");
			request.getRequestDispatcher("../logo.jsp").forward(request, response);
		}
		}catch (Exception e) {
			request.getRequestDispatcher("../logo.jsp").forward(request, response);
		}
	}
}
