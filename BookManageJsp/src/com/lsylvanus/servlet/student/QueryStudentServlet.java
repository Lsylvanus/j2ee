package com.lsylvanus.servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IStudentDAO;
import com.lsylvanus.dao.impl.StudentDAOImpl;
import com.lsylvanus.servlet.BaseServlet;

import net.sf.json.JSONArray;

/**
 * ²éÑ¯Ñ§Éúservlet ¼Ì³ÐBaseServlet
 * @author Lsylvanus
 *
 */
public class QueryStudentServlet extends BaseServlet {

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		IStudentDAO studentDao = new StudentDAOImpl();
		List map = studentDao.queryObjectList();
		JSONArray jsonObj = JSONArray.fromObject(map);
		request.setAttribute("students",jsonObj);
		if(request.getAttribute("error")==null){
			request.setAttribute("error","0");
		}
		request.getRequestDispatcher("/studnet_manage.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}