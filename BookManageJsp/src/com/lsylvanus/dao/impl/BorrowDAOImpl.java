package com.lsylvanus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lsylvanus.dao.IBorrowDAO;
import com.lsylvanus.model.BaseModel;
import com.lsylvanus.model.Book;
import com.lsylvanus.model.BorrowBook;
import com.lsylvanus.model.Student;

/**
 * 实现类:借阅Dao 实现IBorrowDAO,继承BaseBrrowDaoImpl;
 */
public class BorrowDAOImpl extends BaseDaoImpl implements IBorrowDAO {

	public Book getMode(String bookName) {
		Book book = new Book();
		book.setName(bookName);
		List<Book> list = getSesson().selectList("selectByName",book);
		getSesson().commit();
		log.debug(list.size()+"\t\t\t\t\t\t\t\t");
		if(list.size()>0){
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/*public BorrowBook queryObjectByNumber(int cardNumber) {
		List<BorrowBook> list = getSesson().selectList("selectBorrowByNumber",cardNumber);
		log.debug(list.size()+"\n\n\n\n\n");
		getSesson().commit();
		if(list.size()>0){
			return list.get(0);
		} else {
			return null;
		}
	}*/
	
	public List queryObjectByNumberList(int cardNumber) {
		List list = getSesson().selectList("selectBorrowByNumber",cardNumber);
		log.debug(list.size());
		getSesson().commit();
		if(list.size()>0){
			return list;
		} else {
			return null;
		}
	}
	
	public boolean returnBook(int id) {
		List list = getSesson().selectList("selectReturnBook", id);
		if(list.size()>0){
			return true;
		}
		return false;
	}

	public boolean saveReturn(BaseModel model,Book book) {
		try{
			getSesson().insert("returnBook", (BorrowBook)model);
			book.setStock(book.getStock()-1);
			getSesson().insert(Book.class.getName()+".updateBook", book);
			getSesson().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}

	public boolean saveBorrow(BaseModel model,Book book) {
		try{
			getSesson().insert("borrowBook", (BorrowBook)model);
			book.setStock(book.getStock()+1);
			getSesson().insert(Book.class.getName()+".updateBook", book);
			getSesson().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List queryObj() {
		List list = getSesson().selectList("selectBorrowAll");
		List arrayList = new ArrayList();
		for (int i=list.size()-1;i>=0;i--) {
			HashMap map = new HashMap();
			BorrowBook borrowBook = (BorrowBook)list.get(i);
			map.put("id", borrowBook.getId());
			BaseDaoImpl bookDao = new BookDAOImpl();
			BaseDaoImpl studentDao = new StudentDAOImpl();
			Student student = (Student) studentDao.getObject(Student.class.getName(),borrowBook.getStudentId());
			Book book = (Book) bookDao.getObject(Book.class.getName(),borrowBook.getBookId());
			if(student==null||book==null){
				continue;
			}
			map.put("studentName", student.getName());
			map.put("bookID", book.getName());
			map.put("studentID", student.getCardNumber());
			
			borrowBook.setBorrowBeginTime(borrowBook.getBorrowBeginTime().substring(0,10));
			borrowBook.setBorrowEndtime(borrowBook.getBorrowEndtime().substring(0,10));
			map.put("beginTIme", borrowBook.getBorrowBeginTime());
			map.put("endTime", borrowBook.getBorrowEndtime());
			if(returnBook(borrowBook.getId())==true){
				map.put("returnBook", "是");
			} else {
				map.put("returnBook", "否");
			}
			arrayList.add(map);
		}
		return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public BorrowBook selectBorrowById(BaseModel model){
		String selectBorrowById = model.getClass().getName()+".selectBorrowById";
		log.debug(selectBorrowById);
		List list = getSesson().selectList(selectBorrowById, (BorrowBook)model);
		if(list.size()>0){
			return (BorrowBook)list.get(list.size()-1);
		} else {
			return null;
		}
	}

	public List queryObjectList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean queryObjectBook(int id){
		List list = getSesson().selectList(BorrowBook.class.getName()+".checkingBorrowBook",id);
		if(list.size()>0){
			for(int i = 0;i<list.size();i++) {
				id = ((BorrowBook) list.get(i)).getId();
				List retuenBook = getSesson().selectList(BorrowBook.class.getName()+".selectReturnBook",id);
				if(retuenBook.size()<=0) {
					return true;
				}
			}
		}else{
			return false;
		}
		return false;
	}
}
