package com.lilili957.mapper;

import com.lilili957.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lilili957 on 2020/07/05
 */
@Mapper
//@Repository
public interface ArticleMapper {

	int addNewArticle(Article article);

	int updateArticle(Article article);

	List<Article> getArticleByState(@Param("state") Integer state, @Param("start") Integer start,
									@Param("count") Integer count, @Param("uid") Long uid, @Param("keywords") String keywords);

	int getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid,
							   @Param("keywords") String keywords);

	int updateArticleState(@Param("aids") Long adis[], @Param("state") Integer state);

	int updateArticleStateById(@Param("articleId:") Integer articleId, @Param("state") Integer state);

	int deleteArticleById(@Param("aids") Long[] aids);

	Article getArticleById(Long aid);

	void pvIncrement(Long aid);

	//INSERT INTO pv(countDate, pv, uid) SELECT NOW(), SUM(pageView), uid from article GROUP by uid
	void pvStatisticsPerDay();

	List<String> getCategories(Long id);

	List<Integer> getDataStatistics(Long uid);
}
