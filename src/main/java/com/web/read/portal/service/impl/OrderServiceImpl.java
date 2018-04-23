package com.web.read.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.HttpClientUtil;
import com.web.read.common.utils.JsonUtils;
import com.web.read.portal.bean.Order;
import com.web.read.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;

	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;

	@Override
	public String createOrder(Order order) {
		// 调用read-order的服务提交订单
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));

		ReadResult result = ReadResult.format(json);
		if (result.getStatus() == 200) {
			Long orderId = (Long) result.getData();
			return orderId.toString();
		}
		return "";
	}

}
