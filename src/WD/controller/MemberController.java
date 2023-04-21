package WD.controller;

import java.util.Scanner;

import com.KoreaIT.jave.WD.dto.Member;
import com.KoreaIT.jave.WD.util.Util;

import WD.Service.MemberService;

public class MemberController extends Controller {

	private Scanner sc;
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.memberService = new MemberService();

	}

	public void doAction(String cmd, String Keyword) {

		switch (Keyword) {

		case "join":
			doJoin();
			break;

		case "login":
			doLogin();
			break;

		case "logout":
			doLogout();
			break;

		default:
			System.out.println("명령어를 확인해주세요.");
			break;
		}

	}

	private void doJoin() {

		int id = memberService.setMemberId();

		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (memberService.getMemberById(loginId) == false) {

				System.out.printf("중복된 아이디 입니다.\n");

				continue;
			}

			System.out.printf("사용가능한 아이디 입니다.\n");

			break;

		}

		String loginPw = null;

		while (true) {

			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			String loginPwChk = sc.nextLine();

			if (loginPw.equals(loginPwChk) == false) {
				System.out.printf("비밀번호를 확인해주시기 바랍니다.\n");
				continue;

			}

			System.out.printf("사용가능한 비밀번호 입니다.\n");

			break;
		}

		System.out.printf("이 름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, Util.getDate(), loginId, loginPw, name);

		memberService.add(member);

		System.out.printf("%s님 회원 가입을 축하드립니다.\n", name);
	}

	private void doLogin() {

		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("일치하는 회원이 없습니다.");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}

		Controller.loginedMember = member;

		System.out.printf("%s님 환영합니다.\n", Controller.loginedMember.loginId);

	}

	private void doLogout() {

		Controller.loginedMember = null;

		System.out.println("로그아웃 되었습니다.");

	}

	public void makeTestData() {

		System.out.println("테스트용 Member를 3개 생성");

		memberService.makeTestData();
	}

}
