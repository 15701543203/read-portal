package com.web.read.portal.bean;

import java.util.List;

import com.web.read.bean.TbOrder;
import com.web.read.bean.TbOrderItem;
import com.web.read.bean.TbOrderShipping;

public class Order extends TbOrder {
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}
