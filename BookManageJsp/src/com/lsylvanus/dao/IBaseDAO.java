package com.lsylvanus.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.lsylvanus.dao.impl.BaseDaoImpl;
import com.lsylvanus.model.BaseModel;

/**
 * DAO�����ӿ�  Լ���̳���IBaseDAO�Ľӿ�
 */
public interface IBaseDAO {
	
	/**
	 * mybati�����ļ�
	 */
	public String IBATIS_CONFIG = "mybatis-config.xml";

	/**
	 * Ӧ��log4j,���ڵ���
	 */
	public Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	/**
	 * ����jdbc����
	 */
	public SqlSession sesson = null;
	
	/**
	 * ��ȡjdbc����
	 * @return jdbc����
	 */
	public SqlSession getSesson();
	/**
	 * ����
	 * @param model �����ֵ����model����
	 * @return �Ƿ�ɹ� true:�ɹ�  false:ʧ��
	 */
	public boolean save(BaseModel model);
	
	/**
	 * ����
	 * @param model �����ֵ����model����
	 * @return �Ƿ�ɹ� true:�ɹ�  false:ʧ��
	 */
	public boolean update(BaseModel model);
	
	/**
	 * ��ѯ
	 * @return ��ѯ��� true:�ɹ�  false:ʧ��
	 */
	@SuppressWarnings("unchecked")
	public List queryObjectList();
	
	/**
	 * ɾ��
	 * @param model ��������
	 * @return �Ƿ�ɹ� true:�ɹ�  false:ʧ��
	 */
	public boolean delete(BaseModel model);
	
	/**
	 * ��ȡmodel����
	 * @param model ���·��
	 * @param id ��ȡ�����ID
	 * @return ��ȡ��ǰ����
	 */
	public BaseModel getObject(String model,int id);
}
