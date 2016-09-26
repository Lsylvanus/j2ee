package com.lsylvanus.servlet.logo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.impl.LogoDAOImpl;
import com.lsylvanus.model.Logn;
import com.lsylvanus.servlet.BaseServlet;

public class LogoChecking extends BaseServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logn logo = null;
		PrintWriter out = response.getWriter();
		if(request.getSession().getAttribute("logo")!=null){
		  	LogoDAOImpl logoDao = new LogoDAOImpl();
			logo = (Logn) logoDao.logoChecking((Logn) request.getSession().getAttribute("logo"));
		}
		log.debug(request.getSession().getAttribute("logo"));
		log.debug(request.getSession().getAttribute("name"));
		if(logo!=null){
			String name = request.getSession().getAttribute("name").toString();
			out.print("y"+name);
		} else {
			out.print("n");
		}
	}
}