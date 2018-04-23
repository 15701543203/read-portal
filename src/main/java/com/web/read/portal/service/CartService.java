package com.web.read.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.read.common.bean.ReadResult;
import com.web.read.portal.bean.CartItem;

public interface CartService {
	/**
	 * 
	 * Description: 将用户选择的商品添加到购物车
	 * 
	 * @param itemId 商品编号
	 * @param num 商品数量
	 * @param request 获取cookie信息
	 * @param response 将信息写倒cookie中
	 * @return
	 */
	ReadResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 购物车商品清单
	 * Description:
	 * 
	 * @param request 
	 * @param response
	 * @return
	 */
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 更新购物车商品数量 
	 * Description: +1
	 * @param itemId
	 * @param mun
	 * @param request
	 * @param response
	 * @return
	 */
	ReadResult updateCartNum(long itemId,int num ,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 从购物车删除指定商品
	 * Description: -1
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	ReadResult deleteCartNum(long itemId,HttpServletRequest request,HttpServletResponse response);
	
}
