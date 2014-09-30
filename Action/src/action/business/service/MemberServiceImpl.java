package action.business.service;

import action.business.domain.member.Member;

public class MemberServiceImpl implements MemberService {

	@Override
	public void registerMember(Member member) throws DataDuplicatedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member findMember(String memberID) throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMember(Member member) throws DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMember(Member member) throws DataNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member loginCheck(String memberID, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member[] getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
