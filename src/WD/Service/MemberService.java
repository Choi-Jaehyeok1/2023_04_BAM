package WD.Service;

import com.KoreaIT.jave.WD.dto.Member;

import WD.dao.MemberDao;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = new MemberDao();
	}

	public int setMemberId() {
		return memberDao.setMemberId();
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public Member getMemberByLoginId(String loginId) {

		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean getMemberById(String loginId) {

		return memberDao.getMemberById(loginId);
	}

	public void makeTestData() {

		memberDao.makeTestData();

	}
}
