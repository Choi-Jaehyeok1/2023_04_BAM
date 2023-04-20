package WD.controller;

import com.KoreaIT.jave.WD.dto.Member;

public abstract class Controller {

	public static Member loginedMember;
	
	public abstract void doAction(String cmd, String Keyword);
	
	public abstract void makeTestData();
	
}