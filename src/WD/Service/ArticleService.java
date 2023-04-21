package WD.Service;

import com.KoreaIT.jave.WD.dto.Article;

import WD.dao.ArticleDao;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = new ArticleDao();
	}

	public int setArticleId() {
		return articleDao.setArticleId();
	}

	public void add(Article article) {
		articleDao.add(article);
		
	}
	
	
	
	
	
	
	
	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}
	
	
	
	
	
	public Article getArticleById(int id) {

		return articleDao.getArticleById(id);

	}
	
	
	
	public void makeTestData() {

		System.out.println("테스트용 게시물 데이터 5개 생성");
		
		articleDao.makeTestData();

	}
	
}
