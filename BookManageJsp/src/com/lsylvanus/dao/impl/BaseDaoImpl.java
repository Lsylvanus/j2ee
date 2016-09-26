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
 * ʵ����:ʵ�ֽӿ�IBaseDAO
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
			 * model.getClass()����java�Ķ�̬����.��ȡ��ǰ���ݲ�������ʵ����,��ʽ :��.����
			 * ����:com.lsylvanus.model.Student,
			 * ����ibatis�е�id������ͬ����ͨ�������ռ���ȷ֪���Ǹ�xml�ļ��е�sql
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
	 * ����ʱ��ȡjdbc
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
