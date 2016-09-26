package com.lsylvanus.servlet.borrowServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IBorrowDAO;
import com.lsylvanus.dao.IStudentDAO;
import com.lsylvanus.dao.impl.BorrowDAOImpl;
import com.lsylvanus.dao.impl.StudentDAOImpl;
import com.lsylvanus.model.Book;
import com.lsylvanus.model.BorrowBook;
import com.lsylvanus.model.Student;
import com.lsylvanus.servlet.BaseServlet;

/**
 * 归还书籍servlet 继承BaseServlet
 * @author Lsylvanus
 *
 */
public class ReturnBookServlet extends BaseServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
		try{
			IBorrowDAO borrowDao = new BorrowDAOImpl();
			BorrowBook borrowBook = new BorrowBook();
			Book book = borrowDao.getMode(request.getParameter("bookID"));
			IStudentDAO studentDao = new StudentDAOImpl();
			Student student = studentDao.queryObjectByNumber(Integer.parseInt(request.getParameter("studentID")));
			borrowBook.setBookId(book.getId());
			borrowBook.setStudentId(student.getId());
			BorrowBook books = borrowDao.selectBorrowById(borrowBook);
			borrowBook.setId(books.getId());
			borrowBook.setBorrowEndtime(request.getParameter("endTime"));
			boolean flag = borrowDao.saveReturn(borrowBook,book);
			if(flag!=true){
				request.setAttribute("error", "7");
				request.setAttribute("value", "sql出错,请通知管理员");
			} else {
				borrowDao.delete(borrowBook);
			}
			request.getRequestDispatcher("/borrow/QueryBorrow").forward(request, response);
		
		} catch (NumberFormatException e) {
			request.setAttribute("error", "6");
			request.setAttribute("value", "卡号输入字符,请重新输入");
			request.getRequestDispatcher("/borrow/QueryBorrow").forward(request, response);
		}
	}
}
