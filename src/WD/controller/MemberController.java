package WD.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.jave.WD.dto.Member;
import com.KoreaIT.jave.WD.util.Util;

public class MemberController {

	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.lastMemberId = 0;
	
}

	public void doJoin() {
		int id = lastMemberId + 1;
		lastMemberId = id;

		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (getMemberById(loginId) == false) {

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

		Member member = new Member(id, Util.getDate(),loginId, loginPw, name);

		members.add(member);

		System.out.printf("%s님 회원 가입을 축하드립니다.\n", name);
	}
	private boolean getMemberById(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}
	
	public void makeTestId() {

		System.out.println("테스트용 Member를 3개 생성");

		for (int i = 1; i < 4; i++) {
			int id = lastMemberId + 1;
			lastMemberId = id;

			String loginId = "" + i;
			String loginPw = "" + i*2;
			String name = "" + i*3;

			Member member = new Member(id, Util.getDate(), loginId, loginPw, name);
			members.add(member);
		}
	}
	
	
}
	