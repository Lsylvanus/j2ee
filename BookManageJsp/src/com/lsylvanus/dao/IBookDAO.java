package com.lsylvanus.dao;

import java.util.List;

import com.lsylvanus.model.Book;

/**
 * �ӿ�:�鱾Dao
 */
public interface IBookDAO extends IBaseDAO {

	/**
	 * ��ѯ�鼮
	 * @param cardNumber �鼮���
	 * @return book����
	 */
	public Book queryObjectByNumber(int cardNumber);
	
	/**
	 * ��ѯ�����鼮
	 */
	@SuppressWarnings("unchecked")
	public List queryObjectList();
	
	/**
	 * ��ѯ�鼮
	 * @param name �鼮����
	 * @return book����
	 */
	public Book queryObjectByName(String name);
}
