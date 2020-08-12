package com.lilili957.mapper;

import com.lilili957.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilili957 on 2020/07/05
 */
@Mapper
//@Repository
public interface CategoryMapper {

	List<Category> getAllCategories();

	int deleteCategoryByIds(@Param("ids") String[] ids);

	int updateCategoryById(Category category);

	int addCategory(Category category);
}
