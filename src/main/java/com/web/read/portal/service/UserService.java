package com.web.read.portal.service;

import com.web.read.portal.bean.TbUser;

public interface UserService {

	/**
	 * 使用token去中查询用户
	 * Description: 如果已经登录返回TbUser,没有登录返回null
	 * @param token
	 * @return
	 */
	TbUser getUserByToken(String token);
	
}
