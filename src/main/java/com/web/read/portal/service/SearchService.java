package com.web.read.portal.service;

import com.web.read.portal.bean.SearchResult;

public interface SearchService {

	/**
	 * 
	 * Description: 
	 * @param queryString
	 * @param page
	 * @return
	 */
	SearchResult search(String queryString,int page);
}
