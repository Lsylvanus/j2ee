package com.lsylvanus.servlet.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IBookDAO;
import com.lsylvanus.dao.impl.BookDAOImpl;
import com.lsylvanus.model.Book;
import com.lsylvanus.servlet.BaseServlet;

/**
 * ����Book Servlet �̳��� BaseServlet
 * @author lsylvanus
 *
 */
public class SaveBookServlet extends BaseServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		request.setAttribute("value", Integer.valueOf(request.getParameter("value")).intValue());
		log.debug(request.getAttribute("value"));
		try{
			IBookDAO bookDao = new BookDAOImpl();
			Book book = new Book();
			book.setName(request.getParameter("bookName"));
			book.setCardNumber(Integer.valueOf(request.getParameter("cardNumber")).intValue());
			book.setStock(0);
			book.setAStrongerStock(Integer.parseInt(request.getParameter("bookNumber")));
			book.setClassify(request.getParameter("classify"));
			bookDao.save(book);
		} catch (Exception e) {
			log.error(e);
			request.setAttribute("error","11");
			request.setAttribute("value","�鼮IDֻ�����ַ���");
		}
		request.getRequestDispatcher("/book/QueryBookServlet").forward(request, response);
	}
}