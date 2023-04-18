package com.KoreaIT.jave.WD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.jave.WD.dto.Article;
import com.KoreaIT.jave.WD.dto.Member;
import com.KoreaIT.jave.WD.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;

	private int lastArticleId;
	private int lastMemberNo;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
		lastArticleId = 0;
		lastMemberNo = 0;

	}

	public void run() {

		System.out.println("==프로그램 시작==");

		makeTestData();
		makeTestId();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("member join")) {

				int no = lastMemberNo + 1;
				lastMemberNo = no;

				String id;

				while (true) {
					System.out.printf("로그인 아이디 : ");
					String id1 = sc.nextLine();

					if (getMemberById(id1) == false) {

						System.out.printf("중복된 아이디 입니다.\n");

						continue;
					}

					System.out.printf("사용가능한 아이디 입니다.\n");

					id = id1;

					break;

				}

				String pw;

				while (true) {

					System.out.printf("로그인 비밀번호 : ");
					String pw1 = sc.nextLine();
					System.out.printf("로그인 비밀번호 확인 : ");
					String pw2 = sc.nextLine();

					if (pw1 != pw2) {
						System.out.printf("비밀번호를 확인해주시기 바랍니다.\n");
						continue;

					}

					System.out.printf("사용가능한 비밀번호 입니다.\n");
					
					pw = pw1;

					break;
				}

				System.out.printf("이 름 : ");
				String name = sc.nextLine();

				Member member = new Member(no, id, pw, name);

				members.add(member);

				System.out.printf("%s님 회원 가입을 축하드립니다.\n", name);

			} else if (cmd.equals("article write")) {

				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("== 게시물 작성 ==\n");
				String regDate = Util.getDateTime();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);

				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);

			} else if (cmd.startsWith("article list")) {

				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
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
						continue;
					}

				}

				System.out.printf("== 게시물 리스트 ==\n");
				System.out.println("번호	|	제목	|	등록날짜	");
				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);
					System.out.printf("%d	|	%s	|	%s	\n", article.id, article.title, article.regDate);
				}

			} else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
					continue;
				}

				System.out.printf("== 상세 게시물 ==\n");
				System.out.printf("번 호  : %d\n", foundArticle.id);
				System.out.printf("작성일 : %s\n", foundArticle.regDate);
				System.out.printf("제 목  : %s\n", foundArticle.title);
				System.out.printf("내 용  : %s\n", foundArticle.body);
				System.out.printf("==== E N D ====\n");

			} else if (cmd.startsWith("article modify ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
					continue;
				}

				System.out.printf("== 수정 사항 ==\n");

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

			} else if (cmd.startsWith("article delete ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
					continue;
				}
				articles.remove(foundArticle);
				System.out.printf("%d번 게시물이 삭제되었습니다.", id);

			} else {
				System.out.println("존재하지 않는 명령어 입니다.");

			}

		}

		sc.close();

		System.out.println("==프로그램 끝==");

	}

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	private boolean getMemberById(String id) {

		for (Member member : members) {
			if (member.id.equals(id)) {
				return false;
			}
		}
		return true;
	}

	private void makeTestData() {

		System.out.println("테스트용 게시물 데이터 5개 생성");

		for (int i = 1; i < 6; i++) {
			int id = lastArticleId + 1; 
			lastArticleId = id;

			String title = "제목" + i;
			String body = "내용" + i;

			Article article = new Article(id, Util.getDateTime(), title, body);
			articles.add(article);
		}
	}

	private void makeTestId() {

		System.out.println("테스트용 Member를 5개 생성");

		for (int i = 1; i < 6; i++) {
			int no = lastMemberNo + 1;
			lastMemberNo = no;

			String id = "제목" + i*1;
			String pw = "내용" + i*2;
			String name = "내용" + i*3;

			Member member = new Member(no, id, pw, name);
			members.add(member);
		}
	}
	
	
}