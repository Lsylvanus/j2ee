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
 * ɾ��ѧ��servlet �̳�BaseServlet
 * @author Lsylvanus
 *
 */
public class DeleteStudentServlet extends BaseServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		IStudentDAO studentDao = new StudentDAOImpl();
		Student student = (Student) studentDao.getObject(Student.class.getName(),Integer.parseInt(request.getParameter("id")));
		boolean flah =  studentDao.delete(student);
		PrintWriter out = response.getWriter();
		if(flah==true){
			out.print("ɾ���ɹ�");
		} else{
			out.print("ɾ��ʧ��");
		}
	}
}
