package com.dawson.mapper;

import java.util.List;

import com.dawson.model.Cy_goods;

public interface Cy_goodsMapper {

	/**
	 * 
	 * @param location 通过IP查询到的客户端地址
	 * @return 返回最近有更新及符合给定适用地域的商品列表， “Cy_goods”表示商品实体类
	 */
	public List<Cy_goods> goodsListProvide(String location);
}
