package com.web.read.portal;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientDoGetWithParam {
	@Test
	public void doGetWithParam() throws URISyntaxException, ClientProtocolException, IOException{
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
	
		//创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
		
		uriBuilder.addParameter("query", "花千骨");
		HttpGet get = new HttpGet(uriBuilder.build());
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		
		//获取相应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.err.println("statusCode:"+statusCode);
		
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		response.close();
		httpClient.close();
	}

}
