package com.lsylvanus.servlet.book;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.servlet.BaseServlet;
import com.lsylvanus.dao.IBookDAO;
import com.lsylvanus.dao.impl.BookDAOImpl;
import com.lsylvanus.model.Book;

/**
 * Servlet implementation class bookServlet
 */
@WebServlet("/bookServlet")
public class BookCheckingServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCheckingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		super.doPost(request, response);
		PrintWriter out = response.getWriter();
		try{
			IBookDAO bookDao = new BookDAOImpl();
			log.debug(request.getParameter("title"));
			if(request.getParameter("title").equals("cardNumber")){
				try{
					Book book = bookDao.queryObjectByNumber(Integer.parseInt(request.getParameter("value")));
					if(book==null){
						out.print("");
					} else {
						out.print("�鼮����ѱ�ע��");
					}
				} catch(NumberFormatException e){
					log.error(e);
					if(!request.getParameter("value").equals("")){
						out.print("�鼮����ֻ��Ϊ��ֵ,�������ַ������");
					}
				} catch (Exception e) {
					log.error(e);
					out.print("ϵͳ����,��֪ͨ����Ա");
				}
			} else if(request.getParameter("title").equals("bookName")){
				Book book = bookDao.queryObjectByName(request.getParameter("value"));
				if(book==null){
					out.print("");
				} else {
					out.print("�鼮�����ѱ�ע��");
				}
			} else {
				try{
					Integer.parseInt(request.getParameter("value"));
					out.print("");
				} catch(NumberFormatException e){
					log.error(e);
					if(!request.getParameter("value").equals("")){
						out.print("�鼮����ֻ��Ϊ��ֵ,�������ַ������");
					} else{
						out.print("�ɹ�");
					}
				}
			}
		} catch (Exception e) {
			log.error(e);
			out.print("ϵͳ����,��֪ͨ����Ա");
		}
	}

}
