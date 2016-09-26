package com.lsylvanus.servlet.borrowServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.lsylvanus.dao.impl.BookDAOImpl;
import com.lsylvanus.dao.impl.BorrowDAOImpl;
import com.lsylvanus.dao.impl.StudentDAOImpl;
import com.lsylvanus.model.Book;
import com.lsylvanus.model.BorrowBook;
import com.lsylvanus.model.Student;
import com.lsylvanus.servlet.BaseServlet;

/**
 * 归还书籍验证 servlet 继承BaseServlet
 * @author Lsylvanus
 *
 */
public class ReturnCheckingServlet extends BaseServlet {

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		PrintWriter out = response.getWriter();
//		if(request.getParameter("title").equals("学生卡号")){
			StudentDAOImpl studentDao = new StudentDAOImpl();
			try{
				log.debug(request.getParameter("value")+"\n\n\n\n\n");
				Student student = studentDao.queryObjectByNumber(Integer.parseInt(request.getParameter("value")));
				if(student==null){
					Map map = new HashMap();
					map.put("error", "未找到该学生");
					JSONArray ja = JSONArray.fromObject(map);
					out.print(ja);
				} else {
					BorrowDAOImpl borrowDao =new BorrowDAOImpl();
					BookDAOImpl bookDao = new BookDAOImpl();
					List JSON = new ArrayList();
					List list = borrowDao.queryObjectByNumberList(student.getId());
					if(list == null){
						Map map = new HashMap();
						map.put("error", "学生未借阅过书籍");
						JSONArray ja = JSONArray.fromObject(map);
						out.print(ja);
					} else{
						for(int i=0;i<list.size();i++){
							Map map = new HashMap();
							map.put("error", "400");
							BorrowBook borrow = (BorrowBook) list.get(i);
							boolean falg = borrowDao.returnBook(borrow.getId());
							if(falg == true){
								list.remove(i);
								continue;
							} else{
								map.put("id", borrow.getId());
								map.put("text",((Book) bookDao.getObject(Book.class.getName(),borrow.getBookId())).getName());
							}
							JSON.add(map);
						}
						JSONArray ja = JSONArray.fromObject(JSON);
						out.print(ja);
						log.debug(ja);
					}
				}
			}catch (NumberFormatException e) {
				log.error(e);
				Map map = new HashMap();
				map.put("error", "输入错误");
				JSONArray ja = JSONArray.fromObject(map);
				out.print(ja);
			} catch (Exception e) {
				log.error(e);
				Map map = new HashMap();
				map.put("error", "系统错误");
				JSONArray ja = JSONArray.fromObject(map);
				out.print(ja);
			}
//		}
	}

}
