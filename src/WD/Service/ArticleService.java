package WD.Service;

import java.util.List;

import com.KoreaIT.jave.WD.dto.Article;

import WD.Container.Container;
import WD.dao.ArticleDao;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public int setLastId() {
		return articleDao.setLastId();
	}

	public void add(Article article) {
		articleDao.add(article);
		
	}
	
	public List<Article> getArticle(String SearchKeyword) {

		return articleDao.getArticles(SearchKeyword);

	}
	
	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}
	
	
	public Article getArticleById(int id) {

		return articleDao.getArticleById(id);

	}

	
	public void makeTestData() {
		
		articleDao.makeTestData();

	}
	
}
