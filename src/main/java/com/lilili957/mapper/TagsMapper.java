package com.lilili957.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilili957 on 2020/07/06
 */
@Mapper
//@Repository
public interface TagsMapper {

	int deleteTagsById(Long id);

	int saveTags(@Param("tags") String[] tags);

	List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);

	int saveTags2ArticleTags(@Param("tagIds") List<Long> tagIds, @Param("aid") Long aid);
}
