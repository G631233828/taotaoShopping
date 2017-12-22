package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.entity.TbItemCat;
import com.taotao.service.TbItemCatService;

@Controller
@RequestMapping("/item/cat")
public class TbItemCatController {

		@Autowired
		private TbItemCatService tbItemCatService;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@ResponseBody
		@RequestMapping("/list")
		public List categoryList(@RequestParam(value="id",defaultValue="0")Long parentId){
			List list = new ArrayList();
			//查询数据库
			 List<TbItemCat> tblist = tbItemCatService.getItemCatList(parentId);
			//遍历list
			 for(TbItemCat tb: tblist){
				 Map node = new HashMap();
				 node.put("id", tb.getId());
				 node.put("text", tb.getName());
				 //如果是父节点的话设置成关闭状态，如果是子节点就是open状态
				 node.put("state",tb.getIsParent()?"closed":"open");
				 list.add(node);
			 }
			
			return list;
		}
	
}
