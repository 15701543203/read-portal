package com.web.read.portal.service;

import com.web.read.portal.bean.Order;

public interface OrderService {
	/**
	 * 接收用户提交的表单数据
	 * Description: 已经封装成Order对象了，调用read-order提供的下单服务获取订单号
	 * @param order
	 * @return
	 */
	String createOrder(Order order);
}
