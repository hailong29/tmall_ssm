package priv.hailong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.hailong.dao.CategoryMapper;
import priv.hailong.pojo.Category;
import priv.hailong.pojo.CategoryExample;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;
	
	
	@Override
	public List<Category> list() {
		CategoryExample example = new CategoryExample();
		List<Category> categories = categoryMapper.selectByExample(example);		
		return categories;
	}

	@Override
	public Category get(Integer id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Category category) {
		categoryMapper.updateByPrimaryKey(category);
	}

	@Override
	public Category get() {
		return categoryMapper.selectByPrimaryKey(60);
	}

	@Override
	public void add(Category category) {
		categoryMapper.insert(category);
	}

	@Override
	public void delete(int id) {
		categoryMapper.deleteByPrimaryKey(id);
		
	}

	
}
