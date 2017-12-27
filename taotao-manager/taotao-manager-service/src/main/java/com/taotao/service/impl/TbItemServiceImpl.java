package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.IDUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.dao.mapper.TbItemDescMapper;
import com.taotao.dao.mapper.TbItemMapper;
import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemDesc;
import com.taotao.entity.TbItemExample;
import com.taotao.entity.TbItemExample.Criteria;
import com.taotao.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	private TbItemDescMapper itemDescMapper;

	@Override
	public TbItem getItemById(long id) {

		// return itemMapper.selectByPrimaryKey(id);

		TbItemExample example = new TbItemExample();
		// 添加查询条件
		Criteria criteria = example.createCriteria();

		criteria.andIdEqualTo(id);

		List<TbItem> list = itemMapper.selectByExample(example);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	@Override
	public List<TbItem> findAll(TbItemExample example) {
		List<TbItem> list = itemMapper.selectByExample(example);

		if (list != null)
			return list;
		else
			return null;
	}

	/**
	 * 查询商品并分页
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		
		List<TbItem> list =itemMapper.selectByExample(example);
		//创建返回值对象
		EUDataGridResult eu = new EUDataGridResult();
		eu.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		eu.setTotal(pageInfo.getTotal());
		
		return eu;
	}

	@Override
	public TaotaoResult createItem(TbItem item,String desc) throws Exception {

		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte)1);//商品的状态 1正常，2下架 3删除
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入到数据库
		itemMapper.insert(item);
		//添加商品描述信息
		TaotaoResult result = insertItemDesc(itemId,desc);
		//如果返回值不是200 则抛出异常
		if(result.getStatus()!=200){
				throw new Exception();
		}
		
		
		return TaotaoResult.ok(); 
		
	}

	/**
	 * 
	 * @param 添加商品描述
	 * @return
	 */
	private TaotaoResult insertItemDesc(Long itemId,String desc){
		TbItemDesc itemDesc  = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		
		return TaotaoResult.ok();
	}
	
	
}
