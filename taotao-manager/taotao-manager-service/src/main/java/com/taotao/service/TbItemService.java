package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemExample;

public interface TbItemService {
	TbItem getItemById(long id);
	
	
	public List<TbItem> findAll(TbItemExample example);
	


	EUDataGridResult getItemList(int page, int rows);
	
	
	TaotaoResult createItem(TbItem item,String desc) throws Exception;
	
	
		
}
