package com.lilili957.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by lilili957 on 2020/07/08
 */
@Component
public class DataStatisticsComponent {

	private final ArticleService articleService;

	@Autowired
	public DataStatisticsComponent(ArticleService articleService){
		this.articleService = articleService;
	}

	//每天执行一次
	@Scheduled(cron = "1 0 0 * * ?")
	public void pvStatisticsPerDay(){
		articleService.pvStatisticsPerDay();
	}
}
