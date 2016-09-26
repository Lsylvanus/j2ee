package com.lsylvanus.dao;

import java.util.List;

import com.lsylvanus.model.Student;

/**
 * 接口:学生Dao
 */
public interface IStudentDAO extends IBaseDAO {

	/**
	 * 获取所有学生对象
	 */
	public List<Student> queryObjectList();
	
	/**
	 * 获取指定卡号的学生对象
	 * @param cardNumber 学生卡号
	 * @return 一个学生对象
	 */
	public Student queryObjectByNumber(int cardNumber);
	
	/**
	 * 获取提示信息List
	 * @return 返回一个jsonList
	 */
	public List likeStudent(int cardNumber);
}