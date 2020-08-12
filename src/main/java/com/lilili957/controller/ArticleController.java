package com.lilili957.controller;

import com.lilili957.bean.Article;
import com.lilili957.bean.RespBean;
import com.lilili957.service.ArticleService;
import com.lilili957.utils.Util;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private final ArticleService articleService;

	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@PostMapping("/")
	public RespBean addNewArticle(Article article) {
		int result = articleService.addNewArticle(article);
		if (result == 1) {
			return new RespBean("success", article.getId() + "");
		} else {
			return new RespBean("error", article.getState() == 0 ? "文章保存失败" : "文章发表失败");
		}
	}

	/**
	 * 上传图片
	 *
	 * @return 返回值作为图片的地址
	 */
	@PostMapping("uploading")
	public RespBean uploadImg(HttpServletRequest reg, MultipartFile image) {
		StringBuffer url = new StringBuffer();
		String filePath = "/blogimg" + sdf.format(new Date());
		String imgFolderPath = reg.getServletContext().getRealPath(filePath);
		File imgFolder = new File(imgFolderPath);
		if (imgFolder.exists()) {
			imgFolder.mkdir();
		}
		url.append(reg.getScheme())
				.append("://")
				.append(reg.getServerName())
				.append(":")
				.append(reg.getServerPort())
				.append(reg.getContextPath())
				.append(filePath);
		String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
		try {
			IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
			url.append("/").append(imgName);
			return new RespBean("success", url.toString());
		} catch(IOException e){
			e.printStackTrace();
		}

		return new RespBean("error", "上传失败");
	}


	@GetMapping("/all")
	public Map<String, Object> getArticleByState(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "1") Integer count, String keywords){
		int totalCount = articleService.getArticleCountByState(state, Util.getCurrentUser().getId(),keywords);
		List<Article> articles = articleService.getArticleByState(state, page, count, keywords);
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		map.put("articles", articles);

		return map;
	}

	@PutMapping("/dustbin")
	public RespBean updateArticleState(Long[] aids, Integer state){
		if (articleService.updateArticleState(aids, state) == aids.length){
			return new RespBean("success", "删除成功！");
		}

		return new RespBean("error", "删除失败！");
	}

	@PutMapping
	public RespBean restoreArticle(Integer articleId){
		if (articleService.restoreArticle(articleId) == 1){
			return new RespBean("success", "还原成功");
		}

		return new RespBean("error", "还原失败");
	}

	@RequestMapping("/dataStatistics")
	public Map<String, Object> dataStatistics(){
		Map<String, Object> map = new HashMap<>();
		List<String> categories = articleService.getCategories();
		List<Integer> dataStatistics = articleService.getDataStatistics();
		map.put("categories", categories);
		map.put("dataStatistics", dataStatistics);

		return map;
	}

	@GetMapping("/{id}")
	public Article getArticleById(@PathVariable("id") Long id) {
//		Map<String, Article> map = new HashMap<>();   //
//		List<Article> list = new ArrayList<>();
		Article article = articleService.getArticleById(id);
//		map.put("article", article);
//		list.add(article);

		return article;
	}
}
