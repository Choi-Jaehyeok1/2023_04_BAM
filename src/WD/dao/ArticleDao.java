package WD.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.jave.WD.dto.Article;
import com.KoreaIT.jave.WD.util.Util;

public class ArticleDao extends dao {
	private List<Article> articles;
	private int lastArticleId;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
		this.lastArticleId = 0;
	}

	public int setArticleId() {
		int id = lastArticleId + 1;
		lastArticleId = id;
		return id;
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;

	}
	
	
	
	public void remove(Article foundArticle) {
		remove(foundArticle);
	}
	
	
	
	
	
	
	
	public Article getArticleById(int id) {
		
		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}
	
	
	public void makeTestData() {

		for (int i = 1; i < 6; i++) {
			int id = setArticleId();

			String title = "제목" + i;
			String body = "내용" + i;
			int loginidId = 1;

			Article article = new Article(id, Util.getDateTime(), title, body, loginidId);
			articles.add(article);
		}
		
	}
	
	
	
}
