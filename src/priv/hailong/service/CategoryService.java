package priv.hailong.service;

import java.util.List;

import priv.hailong.pojo.Category;

public interface CategoryService {

	/**
	 * 返回分类列表
	 * @return
	 */
	List<Category> list();

	/**
	 * 通过id获取对应的数据
	 * @param id
	 * @return
	 */
	Category get(Integer id);

	/**
	 * 更新分类
	 * @param category
	 * @return
	 */
	void update(Category category);
	
	/*
	 * 用于简单测试
	 */
	Category get();

	/**
	 * 增加分类
	 * @param c
	 */
	void add(Category category);
	
	/**
	 * 用id删除分类
	 * @param id
	 */
	void delete(int id);
	
}
