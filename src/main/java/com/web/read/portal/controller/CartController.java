package com.web.read.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.read.common.bean.ReadResult;
import com.web.read.portal.bean.CartItem;
import com.web.read.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/add/{itemId}")
	public String addCartitem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") int num,
			HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("potral-8082<<CartController>>添加到购物车 [" + itemId+"]");
		
		ReadResult result = cartService.addCartItem(itemId, num, request, response);
		return "cartSuccess";
	}

//	@RequestMapping("/cart")
//	public String showCartItem(HttpServletRequest request, HttpServletResponse response, Model model) {
//
//		System.out.println("potral-8082<<CartController>>展示购物车中的内容");
//
//		List<CartItem> itemList = cartService.getCartItemList(request, response);
//		model.addAttribute("cart", itemList);
//		return "cart";
//	}

	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("potral-8082<<CartController>>展示购物车中的内容");
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}
	
	@RequestMapping(value = "/update/num/{itemId}/{num}")
	@ResponseBody
	public String updateCartItemNum(@PathVariable long itemId, @RequestParam(defaultValue = "1") int num,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("potral-8082<<CartController>>[增加/减少]购物车中产品的数量 ["+itemId+"]");

		ReadResult result = cartService.updateCartNum(itemId, num, request, response);
		return "ok";
	}

	@RequestMapping(value = "/delete/{itemId}")
	String deleteCartItem(@PathVariable long itemId, HttpServletRequest request, HttpServletResponse response) {

		System.out.println("potral-8082<<CartController>>删除添加到购物车中的内容 ["+itemId+"]");

		cartService.deleteCartNum(itemId, request, response);
		return "reditect:/cart/cart.html";
	}

}
