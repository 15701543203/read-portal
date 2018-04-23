package com.web.read.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.HttpClientUtil;
import com.web.read.portal.bean.SearchResult;
import com.web.read.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String queryString, int page) {
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page+"");
		
		try {
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL,param);
			
			ReadResult readResult = ReadResult.formatToPojo(json, SearchResult.class);
			
			if (readResult.getStatus()==200) {
				SearchResult result = (SearchResult) readResult.getData();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
