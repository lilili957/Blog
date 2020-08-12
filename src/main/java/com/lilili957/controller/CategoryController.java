package com.lilili957.controller;

import com.lilili957.bean.Category;
import com.lilili957.bean.RespBean;
import com.lilili957.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilili957 on 2020/07/05
 * 超级管理员专属的controller
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService){
		this.categoryService = categoryService;
	}

	@GetMapping("/all")
	public List<Category> getAllCategory(){
		return categoryService.getAllCategories();
	}

	@DeleteMapping("/{ids}")
	public RespBean deleteCategoryByIds(@PathVariable("ids") String ids){
		boolean result = categoryService.deleteCategoryByIds(ids);
		if (result){
			return new RespBean("success", "删除成功!");
		} else{
			return new RespBean("error", "删除失败！");
		}
	}

	@PutMapping("/")
	public RespBean updateCate(Category category){
		int result = categoryService.updateCategoryById(category);
		if (result == 1) {
			return new RespBean("success", "修改成功");
		} else {
			return new RespBean("error", "修改失败");
		}
	}

	@PostMapping("/")
	public RespBean addNewCate(Category category){
		if ("".equals(category.getCateName()) || category.getCateName() == null){
			return new RespBean("error", "请输入栏目名称！");
		}

		int result = categoryService.addCategory(category);
		if (result == 1) {
			return new RespBean("success", "添加成功！");
		} else {
			return new RespBean("error", "添加失败!");
		}
	}
}
