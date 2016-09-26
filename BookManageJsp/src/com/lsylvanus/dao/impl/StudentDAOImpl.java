package com.lsylvanus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lsylvanus.dao.IStudentDAO;
import com.lsylvanus.model.Student;

/*
 * 实现类:学生类Dao  实现:IStudentDAO 继承BaseDaoImpl
 */
public class StudentDAOImpl extends BaseDaoImpl implements IStudentDAO {

	public Student queryObjectByNumber(int cardNumber) {
		List<?> list = getSesson().selectList(Student.class.getName()+".selectByNumber",cardNumber);
		getSesson().commit();
		if(list.size()>0){
			return (Student)list.get(0);
		} else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List queryObjectList() {
		List list = getSesson().selectList(Student.class.getName()+".selectAll");
		List arrayList = new ArrayList();
		for (int i=list.size()-1;i>=0;i--) {
			HashMap map = new HashMap();
			Student student = (Student)list.get(i);
			map.put("id", student.getId());
			map.put("name", student.getName());
			map.put("department", student.getDepartment());
			map.put("cardNumber", student.getCardNumber());
			arrayList.add(map);
		}
		return arrayList;
	}
	
	public List likeStudent(int cardNumber){
		Student student = new Student();
		student.setCardNumber(cardNumber);
		List list = getSesson().selectList(Student.class.getName()+".selectLike",student);
		List arrayList = new ArrayList();
		for (int i=list.size()-1;i>=0;i--) {
			Map map = new HashMap();
			student = (Student)list.get(i);
			map.put("id","400");
			map.put("text",student.getCardNumber()+"<span style='float:right'>" + student.getName()+"</span>");
			arrayList.add(map);
		}
		return arrayList;
	}
}
