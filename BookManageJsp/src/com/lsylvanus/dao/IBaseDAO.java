package com.lsylvanus.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.lsylvanus.dao.impl.BaseDaoImpl;
import com.lsylvanus.model.BaseModel;

/**
 * DAO基本接口  约束继承于IBaseDAO的接口
 */
public interface IBaseDAO {
	
	/**
	 * mybati配置文件
	 */
	public String IBATIS_CONFIG = "mybatis-config.xml";

	/**
	 * 应用log4j,便于调试
	 */
	public Logger log = Logger.getLogger(BaseDaoImpl.class);
	
	/**
	 * 定义jdbc对象
	 */
	public SqlSession sesson = null;
	
	/**
	 * 获取jdbc对象
	 * @return jdbc对象
	 */
	public SqlSession getSesson();
	/**
	 * 保存
	 * @param model 保存的值放入model传入
	 * @return 是否成功 true:成功  false:失败
	 */
	public boolean save(BaseModel model);
	
	/**
	 * 更新
	 * @param model 保存的值放入model传入
	 * @return 是否成功 true:成功  false:失败
	 */
	public boolean update(BaseModel model);
	
	/**
	 * 查询
	 * @return 查询结果 true:成功  false:失败
	 */
	@SuppressWarnings("unchecked")
	public List queryObjectList();
	
	/**
	 * 删除
	 * @param model 对象引用
	 * @return 是否成功 true:成功  false:失败
	 */
	public boolean delete(BaseModel model);
	
	/**
	 * 获取model对象
	 * @param model 类的路径
	 * @param id 获取的类的ID
	 * @return 获取当前对象
	 */
	public BaseModel getObject(String model,int id);
}
