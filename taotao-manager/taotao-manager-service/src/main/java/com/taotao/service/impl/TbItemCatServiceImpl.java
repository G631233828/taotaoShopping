package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.dao.mapper.TbItemCatMapper;
import com.taotao.entity.TbItemCat;
import com.taotao.entity.TbItemCatExample;
import com.taotao.entity.TbItemCatExample.Criteria;
import com.taotao.service.TbItemCatService;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		
		//设置查询条件
		Criteria criteria = example.createCriteria();
		
		//根据parentId查询子节点
		criteria.andParentIdEqualTo(parentId);
		
		//返回子节点列表
		
		List<TbItemCat>  list = itemCatMapper.selectByExample(example);
		
		return list;
	}

}
