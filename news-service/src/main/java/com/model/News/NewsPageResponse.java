package com.model.News;

public class NewsPageResponse {

	private News[] newsList;

	public NewsPageResponse(News[] newsList) {
		super();
		this.newsList = newsList;
	}

	public News[] getNewsList() {
		return newsList;
	}
	
	
}
