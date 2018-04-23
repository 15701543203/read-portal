package com.web.read.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.read.portal.bean.CartItem;
import com.web.read.portal.bean.Order;
import com.web.read.portal.service.CartService;
import com.web.read.portal.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("protal-8082 showOrderCart");
		// 获取购物车商品列表
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	
	
	@RequestMapping("/create")
	public String createOrder(Order order, Model model) {
		System.out.println("protal-8082 createOrder");
		
		String orderId = orderService.createOrder(order);
		model.addAttribute("orderId", orderId);
		model.addAttribute("payment", order.getPayment());
		model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
		return "success";
	}

}
