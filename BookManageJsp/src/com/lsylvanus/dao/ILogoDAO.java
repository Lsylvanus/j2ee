package com.lsylvanus.dao;

import com.lsylvanus.model.Logn;

/**
 * ���Ƶ�½�Ľӿ�   �̳���IBaseDAO
 * @author Administrator
 *
 */
public interface ILogoDAO extends IBaseDAO {

	/**
	 * ��½��֤
	 * @param model ��½
	 * @return ���ض���
	 */
	public Logn logoChecking(Logn model);
}
