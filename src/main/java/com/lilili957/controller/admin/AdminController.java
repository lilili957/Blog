package com.lilili957.controller.admin;

import com.lilili957.bean.Article;
import com.lilili957.bean.RespBean;
import com.lilili957.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 超级管理员专属Controller
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	private ArticleService articleService;

	public AdminController(ArticleService articleService){
		this.articleService = articleService;
	}

	@GetMapping("/article/all")
	public Map<String, Object> getArticleByStateByAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
		List<Article> articles = articleService.getArticleByState(-2, page, count, keywords);
		Map<String, Object> map = new HashMap<>();
		map.put("articles", articles);

		return map;
	}

	@PutMapping("/article/dustbin")
	public RespBean updateArticleState(Long[] aids, Integer state){
		if (articleService.updateArticleState(aids, state) == aids.length) {
			return new RespBean("sucess", "删除成功！");
		} else {
			return new RespBean("error", "删除失败！");
		}
	}

}
