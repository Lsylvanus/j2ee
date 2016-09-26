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
 * �����鼮 servlet �̳�BaseServlet
 * @author Lsylvanus
 *
 */
public class BorrowServlet extends BaseServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		request.setAttribute("page", "1");
		try{
			IBorrowDAO borrowDao = new BorrowDAOImpl();
			Book book = borrowDao.getMode(request.getParameter("bookName"));
			IStudentDAO studentDao = new StudentDAOImpl();
			log.debug(request.getParameter("cardNumber"));
			Student student = studentDao.queryObjectByNumber(Integer.parseInt(request.getParameter("cardNumber")));
			if(book==null||student==null){
				request.setAttribute("error", "1");
				request.setAttribute("value", "δ���ҵ��鱾��ѧ��");
				log.debug("�ߵ���");
				request.getRequestDispatcher("/borrow/QueryBorrow").forward(request, response);
				return;
			}else{
				BorrowBook borrowBook = new BorrowBook();
				borrowBook.setBookId(book.getId());
				borrowBook.setStudentId(student.getId());
				borrowBook.setBorrowBeginTime(request.getParameter("timeBegin"));
				borrowBook.setBorrowEndtime(request.getParameter("timeEnd"));
				boolean flag = borrowDao.saveBorrow(borrowBook,book);
				
				if(flag!=true){
					request.setAttribute("error", "3");
					request.setAttribute("value", "sql����,��֪ͨ����Ա");
				}
				request.getRequestDispatcher("/borrow/QueryBorrow").forward(request, response);
			}
		} catch (NumberFormatException e) {
			request.setAttribute("error", "2");
			request.setAttribute("value", "���������ַ�,����������");
			request.getRequestDispatcher("/borrow/QueryBorrow").forward(request, response);
		}
	}
}
