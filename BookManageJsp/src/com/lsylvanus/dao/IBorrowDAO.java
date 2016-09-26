package com.lsylvanus.dao;

import java.util.List;

import com.lsylvanus.model.BaseModel;
import com.lsylvanus.model.Book;
import com.lsylvanus.model.BorrowBook;

/**
 * 接口:借阅dao 约束借阅书籍
 */
public interface IBorrowDAO extends IBaseDAO {
	
	/**
	 * 保存归还书籍
	 * @param model 保存的对象
	 * @return 保存是否成功 true:成功 false:失败
	 */
	public boolean saveReturn(BaseModel model,Book book);
	
	/**
	 * 保存借阅书籍
	 * @param model 保存的对象
	 * @return 保存是否成功 true:成功 false:失败
	 */
	public boolean saveBorrow(BaseModel model,Book book);
	
	/**
	 * 获取书籍对象
	 * @param bookName 书籍名称
	 * @return 返回一个Book对象
	 */
	public Book getMode(String bookName);
	
	/**
	 * 获取当前
	 * @param cardNumber 学生卡号
	 * @return 返回一个list集合,存储多个借阅书籍object
	 */
	public List queryObjectByNumberList(int cardNumber);
	
	/**
	 * 获取归还书籍
	 * @param id 借阅书籍的ID
	 * @return 是否成功 true:成功 false:失败
	 */
	public boolean returnBook(int id);
	
	/**
	 * 查询所有借阅的书籍
	 */
	public List queryObj();
	
	/**
	 * 查询借阅书籍
	 * @param model 查询对象
	 * @return 借阅书籍对象object
	 */
	public BorrowBook selectBorrowById(BaseModel model);
	

	/**
	 * 查询书本是否被借阅
	 * @param id 书籍id
	 * @return
	 */
	public boolean queryObjectBook(int id);
}
