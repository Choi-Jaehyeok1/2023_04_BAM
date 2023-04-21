package com.KoreaIT.jave.WD;

import java.util.Scanner;

import WD.controller.ArticleController;
import WD.controller.Controller;
import WD.controller.MemberController;

public class App {

	public void run() {

		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}
			String[] cmdBits = cmd.split(" ");

			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}

			String Keyname = cmdBits[0];
			String Keyword = cmdBits[1];

			Controller controller = null;

			if (Keyname.equals("member")) {
				controller = memberController;

			} else if (Keyname.equals("article")) {
				controller = articleController;

			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}

			String actionName = Keyname + "/" + Keyword;

			switch (actionName) {
			case "article/write":
			case "article/modify":
			case "article/delete":
			case "member/logout":

				if (Controller.loginedMember == null) {
					System.out.println("로그인 중이 아닙니다.");
					continue;
				}
				break;

			case "member/join":
			case "member/login":

				if (Controller.loginedMember != null) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}
				break;
			}

			controller.doAction(cmd, Keyword);

		}

		sc.close();

		System.out.println("==프로그램 끝==");

	}

}