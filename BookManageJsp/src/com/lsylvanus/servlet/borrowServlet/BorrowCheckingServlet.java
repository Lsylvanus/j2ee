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
 * 借阅书籍验证 servlet 继承BaseServlet
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
					out.print("未找到该学生");
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
							out.print("借阅时间不能大于或等于归还时间");
						}
					} catch (ParseException e) {
						log.error(e);
						out.print("非法数据,请重新输入");
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
						out.print(student.getName()+"同学已经借阅"+book.getName());
					}
				}
			}else{
				out.print("非法传入参数");
			}
		} catch (NumberFormatException e) {
			log.error(e);
			out.print("卡号只能为数值,不能以字符串组成");
		} catch (Exception e) {
			log.error(e);
			out.print("系统错误,请通知管理员");
		}
	}

}
