package com.lsylvanus.servlet.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IStudentDAO;
import com.lsylvanus.dao.impl.StudentDAOImpl;
import com.lsylvanus.model.Student;
import com.lsylvanus.servlet.BaseServlet;

/**
 * ������Ϣ��֤servlet �̳�BaseServlet
 * @author Lsylvanus
 *
 */
public class StudentCheckingServlet extends BaseServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		PrintWriter out = response.getWriter();
		IStudentDAO studentDao = new StudentDAOImpl();
		try{
			Student student = studentDao.queryObjectByNumber(Integer.parseInt(request.getParameter("value")));
			if(student==null){
				out.print("");
			} else {
				out.print("�����ѱ�ע��");
			}
		} catch(NumberFormatException e){
			log.error(e);
			out.print("����ֻ��Ϊ��ֵ,�������ַ������");
		} catch (Exception e) {
			log.error(e);
			out.print("ϵͳ����,��֪ͨ����Ա");
		}
	}
}