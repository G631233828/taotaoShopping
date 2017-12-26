package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemExample;
import com.taotao.service.TbItemService;


@Controller
public class TbItemController {

	@Autowired
	private TbItemService tbItemService;

	
	/**
	 * 打开首页
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	
	
	   @RequestMapping("/{page}")
	    public String showpage(@PathVariable String page)
	    {
	        return page;
	        
	    }
	
	
	@RequestMapping("item/{itemId}")
	@ResponseBody
	public TbItem findItem(@PathVariable Long itemId) {
		TbItem item = tbItemService.getItemById(itemId);

		return item;
	}
	
	@ResponseBody
	@RequestMapping("item/list")
	public EUDataGridResult findallItems(@RequestParam(value="rows")int rows,
			@RequestParam(value="page")int page){
		EUDataGridResult  list= tbItemService.getItemList(page, rows);
		
		return list;
	}
	
	
	
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item){
		
		TaotaoResult result =tbItemService.createItem(item);
		
		return result;
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/test")
	public void test(@RequestParam(value="rows")int rows,@RequestParam(value="page")int page){
		//执行查询，并分页
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list =tbItemService.findAll(example);
		System.out.println(list.size());
		//获取商品信息
		for(TbItem tb:list){
			System.out.println(tb.getTitle());
		}
		//获取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有"+total);
	}
}
