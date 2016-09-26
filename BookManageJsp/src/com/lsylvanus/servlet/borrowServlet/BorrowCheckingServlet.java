package com.lsylvanus.servlet.borrowServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IBookDAO;
import com.lsylvanus.dao.IBorrowDAO;
import com.lsylvanus.dao.impl.BookDAOImpl;
import com.lsylvanus.dao.impl.BorrowDAOImpl;
import com.lsylvanus.dao.impl.StudentDAOImpl;
import com.lsylvanus.model.Book;
import com.lsylvanus.model.BorrowBook;
import com.lsylvanus.model.Student;
import com.lsylvanus.servlet.BaseServlet;

/**
 * �����鼮��֤ servlet �̳�BaseServlet
 * @author Lsylvanus
 *
 */
@SuppressWarnings("serial")
public class BorrowCheckingServlet extends BaseServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		PrintWriter out = response.getWriter();
		log.debug(request.getParameter("title"));
		try{
			if(request.getParameter("title").equals("cardNumber")||request.getParameter("title").equals("studentID")){
				StudentDAOImpl studentDao = new StudentDAOImpl();
				Student student = studentDao.queryObjectByNumber(Integer.parseInt(request.getParameter("value")));
				if(student==null){
					out.print("δ�ҵ���ѧ��");
				} else {
					out.print("");
				}
			} else if(request.getParameter("title").equals("timeBegin")||request.getParameter("title").equals("timeEnd")) {
				if(request.getParameter("value").equals("")||request.getParameter("valueEnd").equals("")){
					out.print("");
				} else {
					log.debug(request.getParameter("value"));
					log.debug(request.getParameter("valueEnd"));
					String dateStr = request.getParameter("value");
					String dateStrb = request.getParameter("valueEnd");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date date = sdf.parse(dateStr);
						Date dateb = sdf.parse(dateStrb);
						if((dateb.getTime()-date.getTime())/(1000*60*60*24)>0){
							out.print("");
						} else{
							out.print("����ʱ�䲻�ܴ��ڻ���ڹ黹ʱ��");
						}
					} catch (ParseException e) {
						log.error(e);
						out.print("�Ƿ�����,����������");
					}
				}
			} else if(request.getParameter("title").equals("bookName")){
				StudentDAOImpl studentDao = new StudentDAOImpl();
				log.debug(request.getParameter("value"));
				Student student = studentDao.queryObjectByNumber(Integer.parseInt(request.getParameter("value1")));
				IBookDAO bookDao = new BookDAOImpl();
				Book book = bookDao.queryObjectByName(request.getParameter("value"));
				IBorrowDAO borrowDao = new BorrowDAOImpl();
				BorrowBook borrow = new BorrowBook();
				borrow.setBookId(book.getId());
				borrow.setStudentId(student.getId());
				BorrowBook borrowChecking = borrowDao.selectBorrowById(borrow);
				if(borrowChecking !=null){
					boolean flag = borrowDao.returnBook(borrowChecking.getId());
					if(flag != true){
						out.print(student.getName()+"ͬѧ�Ѿ�����"+book.getName());
					}
				}
			}else{
				out.print("�Ƿ��������");
			}
		} catch (NumberFormatException e) {
			log.error(e);
			out.print("����ֻ��Ϊ��ֵ,�������ַ������");
		} catch (Exception e) {
			log.error(e);
			out.print("ϵͳ����,��֪ͨ����Ա");
		}
	}

}
