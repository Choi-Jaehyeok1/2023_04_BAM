package com.KoreaIT.jave.WD.dto;

public class Article {
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int loginidId;

	public Article(int id, String regDate, String title, String body, int loginidId) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.loginidId = loginidId;

	}

}
