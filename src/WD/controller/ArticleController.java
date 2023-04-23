package WD.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.jave.WD.dto.Article;
import com.KoreaIT.jave.WD.util.Util;

import WD.Service.ArticleService;

public class ArticleController extends Controller {

	private Scanner sc;
	private String cmd;
	private ArticleService articleService;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articleService = new ArticleService();
	}

	public void doAction(String cmd, String Keyword) {

		this.cmd = cmd;

		switch (Keyword) {

		case "write":
			doWrite();
			break;

		case "list":
			showList();
			break;

		case "detail":
			showDetail();
			break;

		case "modify":
			doModify();
			break;

		case "delete":
			doDelete();
			break;

		default:
			System.out.println("명령어를 확인해주세요.");
			break;
		}
	}

	private void doWrite() {

		int id = articleService.setArticleId();
		
		System.out.printf("== 게시물 작성 ==\n");
		String regDate = Util.getDateTime();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, title, body, Controller.loginedMember.id);

		articleService.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);

	}

	private void showList() {


		String serchKeyword = cmd.substring("article list".length()).trim();
		
		List<Article> printArticles = articleService.getArticle(serchKeyword);

		if (printArticles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
		}

		System.out.printf("== 게시물 리스트 ==\n");
		System.out.println("번호	|	제목	|	등록날짜	|	등록자");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			System.out.printf("%d	|	%s	|	%s	|	%s	\n", article.id, article.title, article.regDate,
					article.loginidId);
		}

	}

	
	
	
	
	private void showDetail() {

		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

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

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
			return;
		}

		if (foundArticle.loginidId != Controller.loginedMember.id) {
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

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
			return;
		}

		if (foundArticle.loginidId != Controller.loginedMember.id) {
			System.out.println("수정 권한을 가지고 있지 않습니다.");
			return;
		}

		
		
		
		articleService.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다.", id);

	}

	
	public void makeTestData() {

		System.out.println("테스트용 게시물 데이터 5개 생성");

		articleService.makeTestData();

	}
}
