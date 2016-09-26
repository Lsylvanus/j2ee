package com.lsylvanus.dao;

import com.lsylvanus.model.Logn;

/**
 * 控制登陆的接口   继承于IBaseDAO
 * @author Administrator
 *
 */
public interface ILogoDAO extends IBaseDAO {

	/**
	 * 登陆验证
	 * @param model 登陆
	 * @return 返回对象
	 */
	public Logn logoChecking(Logn model);
}
