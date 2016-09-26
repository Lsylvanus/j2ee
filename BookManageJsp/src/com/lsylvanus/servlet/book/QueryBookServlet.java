package com.lsylvanus.servlet.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IBookDAO;
import com.lsylvanus.dao.impl.BookDAOImpl;
import com.lsylvanus.servlet.BaseServlet;

import net.sf.json.JSONArray;

/**
 * ²éÑ¯Book Servlet ¼Ì³ÐÓÚ BaseServlet
 * @author lsylvanus
 *
 */
@SuppressWarnings("serial")
public class QueryBookServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		IBookDAO bookDao = new BookDAOImpl();
		List map = bookDao.queryObjectList();
		JSONArray jsonObject = JSONArray.fromObject(map);
		request.setAttribute("books",jsonObject);
		log.debug(request.getAttribute("name"+"\n\n\n"));
		if(request.getSession().getAttribute("logo")==null){
			request.getRequestDispatcher("../book_student.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("../book_manage.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
