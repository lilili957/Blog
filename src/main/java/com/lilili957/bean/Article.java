package com.lilili957.bean;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Article {
	private Long id;
	private String title;
	private String htmlContent;
	private String mdContent;
	private String summary;
	private Long cid;
	private Long uid;
	private Timestamp publishDate;
	private Integer state;
	private Integer pageView;
	private Timestamp editTime;
	private String[] dynamicTags;
	private String nickname;
	private String cateName;
	private List<Tags> tags;
	private String stateStr;

}

