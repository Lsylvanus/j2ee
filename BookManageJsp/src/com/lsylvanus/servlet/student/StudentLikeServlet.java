package com.lsylvanus.servlet.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IStudentDAO;
import com.lsylvanus.dao.impl.StudentDAOImpl;
import com.lsylvanus.servlet.BaseServlet;

import net.sf.json.JSONArray;

public class StudentLikeServlet extends BaseServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		IStudentDAO studentDao = new StudentDAOImpl();
		PrintWriter out = response.getWriter();
		try{
			log.debug(request.getParameter("value"));
			List list = studentDao.likeStudent(Integer.valueOf(request.getParameter("value")).intValue());
			JSONArray json = new JSONArray().fromObject(list);
			out.print(json);
			log.debug(json);
		} catch(NumberFormatException e){
			log.error(e);
			Map map = new HashMap();
			map.put("id", "只支持数值");
			JSONArray json = new JSONArray().fromObject(map);
			out.print(json);
		}
	}

}