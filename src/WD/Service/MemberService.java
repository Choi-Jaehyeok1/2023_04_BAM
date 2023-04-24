package WD.Service;

import com.KoreaIT.jave.WD.dto.Member;

import WD.Container.Container;
import WD.dao.MemberDao;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public int setMemberId() {
		return memberDao.setLastId();
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
	
	
	public String getWriterName(int logindId) {
		return memberDao.getWriterName(logindId);
	}
	
	
	

	public void makeTestData() {

		memberDao.makeTestData();

	}
	
	
	
	
	
	
}
