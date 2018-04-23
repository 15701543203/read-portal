package com.web.read.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.CookieUtils;
import com.web.read.common.utils.HttpClientUtil;
import com.web.read.common.utils.JsonUtils;
import com.web.read.portal.bean.CartItem;
import com.web.read.portal.bean.TbItem;
import com.web.read.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	@Override
	public ReadResult addCartItem(long itemId, int num,
			HttpServletRequest request, HttpServletResponse response) {
		// 取商品信息
		CartItem cartItem = null;
		// 取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 判断购物车商品列表中是否存在此商品
		for (CartItem cItem : itemList) {
			// 如果存在此商品
			if (cItem.getId() == itemId) {
				// 增加商品数量
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			// 根据商品id查询商品基本信息。
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + "/" + itemId);
			// 把json转换成java对象
			ReadResult egoResult = ReadResult.formatToPojo(json, TbItem.class);
			if (egoResult.getStatus() == 200) {
				TbItem item = (TbItem) egoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			// 添加到购物车列表
			itemList.add(cartItem);
		}
		// 把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "EGO_CART", JsonUtils.objectToJson(itemList), true);

		return ReadResult.ok();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}

	
	/**
	 * 从cookie中取商品列表
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "EGO_CART", true);
		if (cartJson == null) {
			return new ArrayList<>();
		}
		// 把json转换成商品列表
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public ReadResult updateCartNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		for (CartItem ci : itemList) {
			if (ci.getId() == itemId) {
				ci.setNum(num);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "EGO_CART", JsonUtils.objectToJson(itemList));
		return ReadResult.ok();
	}

	@Override
	public ReadResult deleteCartNum(long itemId, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				itemList.remove(itemId);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "EGO_CART", JsonUtils.objectToJson(itemList));
		return ReadResult.ok();
	}

}
