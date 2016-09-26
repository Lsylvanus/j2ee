package com.lsylvanus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lsylvanus.dao.IBookDAO;
import com.lsylvanus.model.BaseModel;
import com.lsylvanus.model.Book;

/**
 * 实现类:实现接口IBookDAO,继承于BaseDaoImpl
 */
public class BookDAOImpl extends BaseDaoImpl implements IBookDAO {

	@SuppressWarnings("unchecked")
	public Book queryObjectByNumber(int cardNumber) {
		List list = getSesson().selectList(Book.class.getName()+".selectByNumber",cardNumber);
		getSesson().commit();
		if(list.size()>0){
			return (Book)list.get(0);
		} else{
			return null;
		}
	}
	
	public Book queryObjectByName(String name) {
		List list = getSesson().selectList(Book.class.getName()+".selectByName",name);
		getSesson().commit();
		if(list.size()>0){
			return (Book)list.get(0);
		} else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List queryObjectList() {
		List list = getSesson().selectList("com.lsylvanus.model.Book.selectAll");
		List arrayList = new ArrayList();
		for (int i=list.size()-1;i>=0;i--) {
			String value = "";
			HashMap map = new HashMap();
			Book book = (Book)list.get(i);
			map.put("id", book.getId());
			map.put("name", book.getName());
			map.put("stock", book.getStock());
			if(book.getStock()>=book.getCardNumber()){
				value = "不可借阅";
			} else {
				value = "可借阅";
			}
			map.put("checking", value);
			map.put("cardNumber", book.getCardNumber());
			map.put("AStrongerStock", book.getAStrongerStock());
			map.put("classify", book.getClassify());
			arrayList.add(map);
		}
		return arrayList;
	}
}
