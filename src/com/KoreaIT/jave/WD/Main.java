package com.KoreaIT.jave.WD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.jave.WD.dto.Article;
import com.KoreaIT.jave.WD.util.Util;




public class Main {
	public static void main(String[] args) {

		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();

		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("article write")) {

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

			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}

				System.out.printf("== 게시물 리스트 ==\n");
				System.out.println("번호	|	제목	|	등록날짜	");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s	|	%s	\n", article.id, article.title, article.regDate);
				}

			} else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
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

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
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

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
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
}
