package com.lsylvanus.servlet.book;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IBookDAO;
import com.lsylvanus.dao.IBorrowDAO;
import com.lsylvanus.dao.impl.BookDAOImpl;
import com.lsylvanus.dao.impl.BorrowDAOImpl;
import com.lsylvanus.model.Book;
import com.lsylvanus.model.BorrowBook;
import com.lsylvanus.servlet.BaseServlet;

/**
 * É¾³ýBook Servlet ¼Ì³ÐÓÚ BaseServlet
 * @author lsylvanus
 *
 */
public class DeleteBookServlet extends BaseServlet {
	
	
 
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		PrintWriter out = response.getWriter();
		IBookDAO bookDao = new BookDAOImpl();
		IBorrowDAO borrow = new BorrowDAOImpl();
		String[] id = request.getParameter("id").split("and");
		for(int i = 0;i < id.length;i++){
			Book book = (Book) bookDao.getObject(Book.class.getName(),Integer.parseInt(id[i]));
			int bookId = book.getId();
			boolean flag = borrow.queryObjectBook(bookId);
			if(flag==true){
				out.print("×¢Òâ:´ËÊéÒÑ½èÔÄ,É¾³ý»áµ¼ÖÂ¿â´æ¼õÉÙ,ÊÇ·ñ¼ÌÐø");
				break;
			}
		}
		out.print("");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		PrintWriter out = response.getWriter();
		IBookDAO bookDao = new BookDAOImpl();
		IBorrowDAO borrow = new BorrowDAOImpl();
		String[] id = request.getParameter("id").split("and");
		for(int i = 0;i < id.length;i++){
			Book book = (Book) bookDao.getObject(Book.class.getName(),Integer.parseInt(id[i]));
			BorrowBook borrowBook = new BorrowBook();
			borrowBook.setId(Integer.parseInt(id[i]));
			boolean flah = borrow.delete(borrowBook);
			if(flah==true){
				
			}
			boolean flag = bookDao.delete(book);
			if(flag==true){
				out.print("É¾³ý³É¹¦");
			} else{
				out.print("É¾³ýÊ§°Ü");
			}
		}		
	}

}