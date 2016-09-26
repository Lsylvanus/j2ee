package com.lsylvanus.servlet.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lsylvanus.dao.IStudentDAO;
import com.lsylvanus.dao.impl.StudentDAOImpl;
import com.lsylvanus.model.Student;
import com.lsylvanus.servlet.BaseServlet;
import com.lsylvanus.servlet.student.UpdateStudentServlet;

/**
 * ����ѧ��servlet �̳�BaseServlet
 * @author Lsylvanus
 *
 */
public class UpdateStudentServlet extends BaseServlet {

	public static final Logger log = Logger.getLogger(UpdateStudentServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			IStudentDAO studentDao = new StudentDAOImpl();
			Student student = (Student) studentDao.queryObjectByNumber(Integer.parseInt(request.getParameter("id")));
			if(student==null){
				student = (Student) studentDao.getObject(Student.class.getName(),Integer.parseInt(request.getParameter("id")));
				student.setDepartment(request.getParameter("department"));
				student.setName(request.getParameter("name"));
				student.setCardNumber(Integer.valueOf(request.getParameter("cardNumber")).intValue());
				boolean flah = studentDao.update(student);
				if(flah==true){
					out.print("�޸ĳɹ�");
				} else{
					out.print("�޸�ʧ��");
				}
			} else{
				out.print("�����ѱ�ע��");
			}
		} catch (NumberFormatException e) {
			log.error(e);
			out.print("�鼮IDֻ����������,����������");
		}
	}

}