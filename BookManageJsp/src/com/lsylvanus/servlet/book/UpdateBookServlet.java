package com.lsylvanus.servlet.book;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.impl.BookDAOImpl;
import com.lsylvanus.model.Book;
import com.lsylvanus.servlet.BaseServlet;

/**
 * 更新Book Servlet 继承于 BaseServlet
 * @author Lsylvanus
 *
 */
public class UpdateBookServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		PrintWriter out = response.getWriter();
		try{
			BookDAOImpl bookDao = new BookDAOImpl();
			Book book = (Book) bookDao.getObject(Book.class.getName(),Integer.parseInt(request.getParameter("id")));
			book.setName(request.getParameter("name"));
			book.setCardNumber(Integer.valueOf(request.getParameter("cardNumber")).intValue());
			book.setAStrongerStock(Integer.parseInt(request.getParameter("stock")));
			book.setAStrongerStock(Integer.parseInt(request.getParameter("AStrongerStock")));
			book.setClassify(request.getParameter("classify"));
			boolean flah = bookDao.update(book);
			if(flah==true){
				out.print("修改成功");
			} else{
				out.print("修改失败");
			}
		} catch (Exception e) {
			out.print("书籍ID只能输入数字,请重新输入");
		}
	}
}
