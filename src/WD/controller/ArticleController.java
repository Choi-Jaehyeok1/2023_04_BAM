package WD.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.jave.WD.dto.Article;
import com.KoreaIT.jave.WD.util.Util;

public class ArticleController extends Controller{

	private List<Article> articles;
	private Scanner sc;
	private int lastArticleId;
	private String cmd;

	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
		this.lastArticleId = 0;
	}
	
	public void doAction(String cmd, String Keyword) {
		
		this.cmd = cmd;
		
		switch(Keyword) {
		
		case "write":
			if(Controller.loginedMember == null) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			doWrite();
			break;
			
		case "list":
			showList();
			break;

		case "detail":
			if(Controller.loginedMember == null) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			showDetail();
			break;
			
		case "modify":
			if(Controller.loginedMember == null) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			doModify();
			break;
			
		case "delete":
			if(Controller.loginedMember == null) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			doDelete();
			break;
			
			
		default :
			System.out.println("명령어를 확인해주세요.");
			break;
		}
	}
	
	
	private void doWrite() {
		
		int id = lastArticleId + 1;
		lastArticleId = id;
		System.out.printf("== 게시물 작성 ==\n");
		String regDate = Util.getDateTime();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, title, body, Controller.loginedMember.id);

		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);

	}

	private void showList() {

		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}

		List<Article> printArticles = articles;

		String serchKeyword = cmd.substring("article list".length()).trim();

		if (serchKeyword.length() > 0) {

			printArticles = new ArrayList<>();

			for (Article article : articles) {

				if (article.title.contains(serchKeyword)) {
					printArticles.add(article);
					break;
				}
			}

			if (printArticles.size() == 0) {
				System.out.println("검색어가 없습니다.");
				return;
			}

		}

		System.out.printf("== 게시물 리스트 ==\n");
		System.out.println("번호	|	제목	|	등록날짜	|	등록자");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			System.out.printf("%d	|	%s	|	%s	|	%s	\n", article.id, article.title, article.regDate, article.loginidId);
		}

	}

	private void showDetail() {

		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
			return;
		}

		System.out.printf("== 상세 게시물 ==\n");
		System.out.printf("번 호  : %d\n", foundArticle.id);
		System.out.printf("작성일 : %s\n", foundArticle.regDate);
		System.out.printf("제 목  : %s\n", foundArticle.title);
		System.out.printf("내 용  : %s\n", foundArticle.body);
		System.out.printf("작성자 : %d\n", foundArticle.loginidId);
		System.out.printf("==== E N D ====\n");

	}

	private void doModify() {

		String[] cmdBits = cmd.split(" ");

		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = getArticleById(id);

		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
			return;
		}
		
		if(foundArticle.loginidId != Controller.loginedMember.id) {
			System.out.println("수정 권한을 가지고 있지 않습니다.");
			return;
		}

		System.out.printf("== 수정 사항 ==\n");

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

	}

	private void doDelete() {

		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
			return;
		}
		
		if(foundArticle.loginidId != Controller.loginedMember.id) {
			System.out.println("수정 권한을 가지고 있지 않습니다.");
			return;
		}
		
		articles.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다.", id);

	}

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	public void makeTestData() {

		System.out.println("테스트용 게시물 데이터 5개 생성");

		for (int i = 1; i < 6; i++) {
			int id = lastArticleId + 1;
			lastArticleId = id;

			String title = "제목" + i;
			String body = "내용" + i;
			int loginidId = 1;

			Article article = new Article(id, Util.getDateTime(), title, body, loginidId);
			articles.add(article);
		}
	}


}
