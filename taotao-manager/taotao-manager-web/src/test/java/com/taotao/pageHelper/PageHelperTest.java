package com.taotao.pageHelper;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.mapper.TbItemMapper;
import com.taotao.entity.TbItem;
import com.taotao.entity.TbItemExample;

public class PageHelperTest {

	@Test
	public void test() {
		//创建spring容器
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-*.xml");

		//从Spring容器中获取Mapper的代理对象
		TbItemMapper mapper = context.getBean(TbItemMapper.class);
		//执行查询并且分页
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(1, 10);
		//获取商品列表
		List<TbItem> listitem = mapper.selectByExample(example);
		
		for(TbItem tb:listitem){
			System.out.println(tb);
		}
		
		PageInfo<TbItem> info = new PageInfo<>(listitem);
		long total =info.getTotal();
		System.out.println("总共商品"+total);
	}

}
