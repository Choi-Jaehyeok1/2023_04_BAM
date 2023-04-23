package WD.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.jave.WD.dto.Article;
import com.KoreaIT.jave.WD.util.Util;

public class ArticleDao extends dao {
	private List<Article> articles;

	public ArticleDao() {
		this.articles = new ArrayList<>();
	}

	public List<Article> getArticles(String searchKeyword) {
		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);

			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			return printArticles;
		}

		return articles;
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

			String title = "제목" + i;
			String body = "내용" + i;
			int loginidId = 1;

			Article article = new Article(setLastId(), Util.getDateTime(), title, body, loginidId);
			articles.add(article);
		}

	}

}
