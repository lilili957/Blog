package com.lilili957.service;

import com.lilili957.bean.Category;
import com.lilili957.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CategoryService {

	private final CategoryMapper categoryMapper;

	@Autowired
	public CategoryService(CategoryMapper categoryMapper){
		this.categoryMapper = categoryMapper;
	}

	public List<Category> getAllCategories(){
		return categoryMapper.getAllCategories();
	}

	public boolean deleteCategoryByIds(String ids){
		String[] split = ids.split(",");
		int result = categoryMapper.deleteCategoryByIds(split);
		return result == split.length;
	}

	public int updateCategoryById(Category category){
		return categoryMapper.updateCategoryById(category);

	}

	public int addCategory(Category category){
		category.setDate(new Timestamp(System.currentTimeMillis()));
		return categoryMapper.addCategory(category);
	}

}
