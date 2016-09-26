package com.lsylvanus.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.lsylvanus.dao.IBaseDAO;
import com.lsylvanus.model.BaseModel;
import com.lsylvanus.model.Book;
import com.lsylvanus.model.Student;

/**
 * 实现类:实现接口IBaseDAO
 */
public abstract class BaseDaoImpl implements IBaseDAO {

	public SqlSession sesson = null;
	
	public boolean delete(BaseModel model) {
		String delete = model.getClass().getName() + ".delete";
		log.debug(delete);
		try {
			getSesson().delete(delete,model.getId());
			getSesson().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public BaseModel getObject(String model,int id) {
		String selectById = model + ".selectById";
		log.debug(selectById);
		List list = getSesson().selectList(selectById,id);
		getSesson().commit();
		if(list.size()>0){
			if(list.get(0) instanceof Student){
				return (Student)list.get(0);
			}else{
				return (Book)list.get(0);
			}
		} else{
			return null;
		}
	}

	public boolean save(BaseModel model) {
		try {
			/*
			 * model.getClass()利用java的多态特性.获取当前传递参数的真实类型,格式 :包.类名
			 * 例如:com.lsylvanus.model.Student,
			 * 由于ibatis中的id名称相同必须通过命名空间明确知道那个xml文件中的sql
			 */
			String add = model.getClass().getName() + ".add";
			log.debug(add);
			getSesson().insert(add,model); 
			getSesson().commit();
			return true;
		} catch (Exception e){
			log.error(e);
			return false;
		}
	}

	public boolean update(BaseModel model) {
		String updateBook = model.getClass().getName()+".update";
		try {
			getSesson().update(updateBook,model);
			getSesson().commit();
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	
	/**
	 * 构建时获取jdbc
	 */
	public SqlSession getSesson(){
		if(sesson==null){
			try {
				String resource = IBATIS_CONFIG;
				InputStream inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				
				sesson = sqlSessionFactory.openSession();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sesson;
	}
}
