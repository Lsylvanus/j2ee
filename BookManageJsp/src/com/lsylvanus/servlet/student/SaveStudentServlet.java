package com.lsylvanus.servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsylvanus.dao.IStudentDAO;
import com.lsylvanus.dao.impl.StudentDAOImpl;
import com.lsylvanus.model.Student;
import com.lsylvanus.servlet.BaseServlet;

import net.sf.json.JSONArray;

/**
 * ����ѧ��servlet �̳�BaseServlet
 * @author Lsylvanus
 *
 */
@SuppressWarnings("serial")
public class SaveStudentServlet extends BaseServlet {
	
	private static final Integer Integer = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
		Student student = new Student();
		student.setName(request.getParameter("name"));
		try {
			int cardNumber = java.lang.Integer.valueOf(request.getParameter("cardNumber")).intValue();
			student.setCardNumber(cardNumber);
			student.setDepartment(request.getParameter("department"));
			log.debug("x"+request.getParameter("department"));
			IStudentDAO studentDao = new StudentDAOImpl();
			studentDao.save(student);
			List<Student> map = studentDao.queryObjectList();
			JSONArray jsonObject = JSONArray.fromObject(map);
			request.setAttribute("students",jsonObject);
			request.setAttribute("value","�޸ĳɹ�");
		} catch (NumberFormatException e) {
			log.error(e);
			request.setAttribute("error","11");
			request.setAttribute("value","����ֻ�����ַ���");
		} catch (Exception e) {
			log.error(e);
			request.setAttribute("error","11");
			request.setAttribute("value","ϵͳ����");
		} 
		request.getRequestDispatcher("/student/QueryStudentServlet").forward(request, response);
	}
}