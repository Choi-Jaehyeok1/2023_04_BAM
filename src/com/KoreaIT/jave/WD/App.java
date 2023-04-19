package com.KoreaIT.jave.WD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.jave.WD.dto.Article;
import com.KoreaIT.jave.WD.dto.Member;

import WD.controller.ArticleController;
import WD.controller.Controller;
import WD.controller.MemberController;

public class App {
	private List<Article> articles;
	private List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();

	}

	public void run() {

		System.out.println("==프로그램 시작==");

		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		
		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				break;
			}
			String[] cmdBits = cmd.split(" ");
			
			if(cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
			
			String Keyname = cmdBits[0];
			String Keyword = cmdBits[1];

			Controller controller = null;
			
			if(Keyname.equals("member")) {
				controller = memberController;
				
			} else if(Keyname.equals("article")){
				controller = articleController;
				
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			controller.doAction(cmd, Keyword);
			
		}

		sc.close();

		System.out.println("==프로그램 끝==");

	}
	
	
}