package com.lsylvanus.dao.impl;

import java.util.List;

import com.lsylvanus.dao.ILogoDAO;
import com.lsylvanus.model.Logn;

/**
 * µÇÂ½ÑéÖ¤dao ¼Ì³ÐÓÚBaseDaoImpl;
 */
public class LogoDAOImpl extends BaseDaoImpl implements ILogoDAO {

	@SuppressWarnings("unchecked")
	public Logn logoChecking(Logn model) {
		List list = null;
		if(model.getPassword().equals("")){
			list = getSesson().selectList("lognCheckingByLognName", (Logn)model);
		}else{
			list = getSesson().selectList("lognChecking", (Logn)model);
		}
		if(list.size()>0){
			Logn  logo = (Logn) list.get(0);
			log.debug(logo.getName());
			return logo;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List queryObjectList() {
		return null;
	}
}
