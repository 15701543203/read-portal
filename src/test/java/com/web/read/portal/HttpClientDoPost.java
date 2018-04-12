package com.web.read.portal;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientDoPost {
	@Test
	public void doPost() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
	
		//创建一个post对象
		HttpPost post = new HttpPost("http://www.baidu.com");
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
		
	}
}
