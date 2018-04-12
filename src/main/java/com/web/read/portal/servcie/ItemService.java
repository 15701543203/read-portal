package com.web.read.portal.servcie;

import com.web.read.portal.bean.TbItem;

public interface ItemService {

	/**
	 * 使用httpclient调用rest中的方法获取商品的基本信息
	 * Description: 
	 * @param itemID
	 * @return
	 */
	TbItem getItemById(Long itemId);
	
	/**
	 * 商品描述信息
	 * Description: 通过httpClient调用rest服务获取商品描述信息
	 * @param itemId
	 * @return
	 */
	String getItemDescById(Long itemId);
	
	/**
	 * 商品参数信息
	 * Description: 通过httpClient调用rest服务获取商品参数信息
	 * @param itemId
	 * @return
	 */
	String getItemParamById(Long itemId);
}
