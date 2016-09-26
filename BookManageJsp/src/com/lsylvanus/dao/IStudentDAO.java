package com.lsylvanus.dao;

import java.util.List;

import com.lsylvanus.model.Student;

/**
 * �ӿ�:ѧ��Dao
 */
public interface IStudentDAO extends IBaseDAO {

	/**
	 * ��ȡ����ѧ������
	 */
	public List<Student> queryObjectList();
	
	/**
	 * ��ȡָ�����ŵ�ѧ������
	 * @param cardNumber ѧ������
	 * @return һ��ѧ������
	 */
	public Student queryObjectByNumber(int cardNumber);
	
	/**
	 * ��ȡ��ʾ��ϢList
	 * @return ����һ��jsonList
	 */
	public List likeStudent(int cardNumber);
}