package com.taotao.service;

import java.util.List;

import com.taotao.entity.TbItemCat;

public interface TbItemCatService {

	public List<TbItemCat> getItemCatList(Long parentId);
	
	
	
}


