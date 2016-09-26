package com.lsylvanus.dao.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.lsylvanus.dao.IBorrowDAO;

/*
 *  µœ÷¿‡:ΩË‘ƒDao
 */
public abstract class BaseBrrowDaoImpl implements IBorrowDAO {

	public static final Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	public SqlSession sesson = null;
	
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
