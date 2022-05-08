package com.dawson.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dawson.model.Cy_goods;
import com.dawson.mapper.Cy_goodsMapper;
import com.dawson.mapper.IP_LocationMapper;


@Controller
@RequestMapping("/index") // 访问路径仅作演示，可以根据实际需要调整
public class HomeController {

	@Autowired
	private IP_LocationMapper iP_LocationMapper; 
	
	@Autowired
	private Cy_goodsMapper cy_goodsMapper;
	
	/**
	 * 
	 * @param request
	 * @return json格式返回最近有更新及符合给定适用地域的商品列表， “Cy_goods”表示商品实体类
	 */
	@GetMapping
	@ResponseBody
	public List<Cy_goods> welcomePush(HttpServletRequest request) {
		String requestIp = request.getRemoteAddr();
		String regexRequestIp = requestIp.substring(0, 8) + "*"; // 适配前8位ip字符
		// 数据库查找获得ip对应区域
		String location = iP_LocationMapper.searchRequestLocationByIP(regexRequestIp);
		
		// 根据location查找需推送的商品信息
		List<Cy_goods> suitableGoods = cy_goodsMapper.goodsListProvide(location);
		
		return suitableGoods;
	}
	
}
