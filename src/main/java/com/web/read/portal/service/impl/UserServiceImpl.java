package com.web.read.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.HttpClientUtil;
import com.web.read.portal.bean.TbUser;
import com.web.read.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

//	@Value("${SSO_BASE_URL}")
//	public String SSO_BASE_URL;
//	
//	@Value("${SSO_PAGE_LOGIN}")
//	public String SSO_PAGE_LOGIN;
//	
//	@Value("${SSO_USER_TOKEN}")
//	private String SSO_USER_TOKEN;
//	
//	@Override
//	public TbUser getUserByToken(String token) {
//		try {
//			//调用sso使用token查询用户信息
//			String json = HttpClientUtil.doGet(SSO_BASE_URL+ SSO_USER_TOKEN + token);
//			//装换成ReadResult
//			ReadResult result = ReadResult.formatToPojo(json, TbUser.class);
//			
//			System.out.println(result);
//			
//			if (result.getStatus()==200) {
//				TbUser user = (TbUser) result.getData();
//				return user;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;

	@Override
	public TbUser getUserByToken(String token) {
		try {
			// 调用sso系统的服务，根据token取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + "/" + token);
//			System.err.println("json's value = " + json);
			// 把json转换成EgoREsult
			ReadResult result = ReadResult.formatToPojo(json, TbUser.class);
			
//			System.err.println("result = " + result);
			
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
