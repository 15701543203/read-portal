package com.web.read.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.read.portal.bean.ItemInfo;
import com.web.read.portal.bean.TbItem;
import com.web.read.portal.servcie.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

		
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		System.out.println("read-portal>ItemServiceImpl>getItemById>itemId>>>>"+itemId);
		TbItem item = itemService.getItemById(itemId);
		ItemInfo info = new ItemInfo();
		if (item != null) {
			info.setBarcode(item.getBarcode());
			info.setCid(item.getCid());
			info.setCreated(item.getCreated());
			info.setId(item.getId());
			info.setImage(item.getImage());
			info.setNum(item.getNum());
			info.setPrice(item.getPrice());
			info.setSellPoint(item.getSellPoint());
			info.setStatus(item.getStatus());
			info.setTitle(item.getTitle());
			info.setUpdated(item.getUpdated());
		}
		model.addAttribute("item", info);
		return "item";
	}
	
	
	@RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String string = itemService.getItemDescById(itemId);
		return string;
	}

	@RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String string = itemService.getItemParamById(itemId);
		return string;
	}

}