package com.lsylvanus.dao;

import java.util.List;

import com.lsylvanus.model.Book;

/**
 * 接口:书本Dao
 */
public interface IBookDAO extends IBaseDAO {

	/**
	 * 查询书籍
	 * @param cardNumber 书籍编号
	 * @return book对象
	 */
	public Book queryObjectByNumber(int cardNumber);
	
	/**
	 * 查询所有书籍
	 */
	@SuppressWarnings("unchecked")
	public List queryObjectList();
	
	/**
	 * 查询书籍
	 * @param name 书籍名称
	 * @return book对象
	 */
	public Book queryObjectByName(String name);
}
