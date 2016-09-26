package com.lsylvanus.dao;

import java.util.List;

import com.lsylvanus.model.BaseModel;
import com.lsylvanus.model.Book;
import com.lsylvanus.model.BorrowBook;

/**
 * �ӿ�:����dao Լ�������鼮
 */
public interface IBorrowDAO extends IBaseDAO {
	
	/**
	 * ����黹�鼮
	 * @param model ����Ķ���
	 * @return �����Ƿ�ɹ� true:�ɹ� false:ʧ��
	 */
	public boolean saveReturn(BaseModel model,Book book);
	
	/**
	 * ��������鼮
	 * @param model ����Ķ���
	 * @return �����Ƿ�ɹ� true:�ɹ� false:ʧ��
	 */
	public boolean saveBorrow(BaseModel model,Book book);
	
	/**
	 * ��ȡ�鼮����
	 * @param bookName �鼮����
	 * @return ����һ��Book����
	 */
	public Book getMode(String bookName);
	
	/**
	 * ��ȡ��ǰ
	 * @param cardNumber ѧ������
	 * @return ����һ��list����,�洢��������鼮object
	 */
	public List queryObjectByNumberList(int cardNumber);
	
	/**
	 * ��ȡ�黹�鼮
	 * @param id �����鼮��ID
	 * @return �Ƿ�ɹ� true:�ɹ� false:ʧ��
	 */
	public boolean returnBook(int id);
	
	/**
	 * ��ѯ���н��ĵ��鼮
	 */
	public List queryObj();
	
	/**
	 * ��ѯ�����鼮
	 * @param model ��ѯ����
	 * @return �����鼮����object
	 */
	public BorrowBook selectBorrowById(BaseModel model);
	

	/**
	 * ��ѯ�鱾�Ƿ񱻽���
	 * @param id �鼮id
	 * @return
	 */
	public boolean queryObjectBook(int id);
}
