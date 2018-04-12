package com.web.read.portal.servcie.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.read.common.bean.ReadResult;
import com.web.read.common.utils.HttpClientUtil;
import com.web.read.common.utils.JsonUtils;
import com.web.read.portal.bean.TbItem;
import com.web.read.portal.bean.TbItemDesc;
import com.web.read.portal.bean.TbItemParamItem;
import com.web.read.portal.servcie.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;

	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;

	@Override
	public TbItem getItemById(Long itemId) {

		try {
			// 调用rest的服务查询商品基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + "/" + itemId);
			if (!StringUtils.isBlank(json)) {
				ReadResult readResult = ReadResult.formatToPojo(json, TbItem.class);
				if (readResult.getStatus() == 200) {
					TbItem item = (TbItem) readResult.getData();
					return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getItemDescById(Long itemId) {

		System.out.println(itemId);

		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + "/" + itemId);

			ReadResult readResult = ReadResult.formatToPojo(json, TbItemDesc.class);

			if (readResult.getStatus() == 200) {
				TbItemDesc itemDesc = (TbItemDesc) readResult.getData();
				// 获取商品描述
				String desc = itemDesc.getItemDesc();
				return desc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getItemParamById(Long itemId) {
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + "/" + itemId);

			ReadResult readResult = ReadResult.formatToPojo(json, TbItemParamItem.class);
			if (readResult.getStatus() == 200) {
				TbItemParamItem itemParamItem = (TbItemParamItem) readResult.getData();
				String paramData = itemParamItem.getParamData();

				// 生成html
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
				StringBuilder sb = new StringBuilder();
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
				sb.append("    <tbody>\n");
				for (Map map1 : jsonList) {
					sb.append("    <tr>\n");
					sb.append("        <th class=\"tdTitle\" colspan=\"2\">" + map1.get("group") + "</th>\n");
					sb.append("    </tr>\n");

					List<Map> list2 = (List<Map>) map1.get("params");
					for (Map map2 : list2) {
						sb.append("    <tr>\n");
						sb.append("        <td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
						sb.append("        <td>" + map2.get("v") + "</td>\n");
						sb.append("    </tr>\n");
					}
				}
				sb.append("    </tbody>\n");
				sb.append("</table>");
				// 返回html片段
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}
