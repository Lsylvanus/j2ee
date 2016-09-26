package com.lsylvanus.servlet.borrowServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.lsylvanus.dao.IBookDAO;
import com.lsylvanus.dao.IBorrowDAO;
import com.lsylvanus.dao.impl.BookDAOImpl;
import com.lsylvanus.dao.impl.BorrowDAOImpl;
import com.lsylvanus.servlet.BaseServlet;

/**
 * 查询书籍 servlet 继承BaseServlet
 * @author 石超
 *
 */
@SuppressWarnings("serial")
public class QueryBorrowServlet extends BaseServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		IBorrowDAO borrowDao = new BorrowDAOImpl();
		List list = borrowDao.queryObj();
		IBookDAO bookDao = new BookDAOImpl();
		List bookList = bookDao.queryObjectList();
		List bookNames = new ArrayList();
		for(int i=0;i<bookList.size();i++){
			Map map = (Map) bookList.get(i);
			Map bookName = new HashMap();
			if(Integer.parseInt(map.get("stock").toString()) >= Integer.parseInt(map.get("AStrongerStock").toString())){
				continue;
			}
			bookName.put("text", map.get("name"));
			bookName.put("id", map.get("id"));
			bookNames.add(bookName);
		}
		JSONArray bookJson = JSONArray.fromObject(bookNames);
		JSONArray jsonObj = JSONArray.fromObject(list);
		log.debug(jsonObj);
		request.setAttribute("borrow",jsonObj);
		request.setAttribute("books",bookJson);
		request.setAttribute("error", "0");
		log.debug(request.getParameter("values"));
		if(Integer.valueOf(request.getParameter("values")).intValue()==1){
			request.getRequestDispatcher("../book_borrow.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("../book_return.jsp").forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		doGet(request, response);
	}
	
	
}
