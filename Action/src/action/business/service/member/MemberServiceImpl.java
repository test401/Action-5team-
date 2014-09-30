package action.business.service.member;

import action.business.service.member.MemberDao;
import action.dataaccess.member.MemberDaoImpl;
import action.business.domain.member.Member;
import action.business.service.DataDuplicatedException;
import action.business.service.DataNotFoundException;

public class MemberServiceImpl implements MemberService {

private MemberDao memberDataAccess;
    
    /* 
     * 1. MemberDaoImpl 객체를 생성하여 memberDataAccess 인스턴스 변수 초기화  
     */
    public MemberServiceImpl() {
    	memberDataAccess = new MemberDaoImpl();
    }
    
	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#registerMember(casestudy.business.domain.Member)
	 * 
	 * 2. MemberDao 객체를 사용해 인자로 받은 회원 정보를 등록한다.
	 */
	@Override
	public void registerMember(Member member) throws DataDuplicatedException {
		if(memberDataAccess.memberIDExists(member.getMemberID())){
			throw new DataDuplicatedException("동일한 ID를 가진 회원이 있습니다.");
		} else {
			memberDataAccess.insertMember(member);
		}
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#findMember(java.lang.String)
	 * 
	 * 3. MemberDao 객체를 사용해 인자로 받은 memberID에 해당하는 회원 정보를 찾아서 리턴한다.
	 */
	@Override
	public Member findMember(String memberID) throws DataNotFoundException{
		if(memberDataAccess.memberIDExists(memberID)){
			 return memberDataAccess.selectMember(memberID);
		} else {
			throw new DataNotFoundException("존재하지 않는 회원입니다.");
		}
       
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#updateMember(casestudy.business.domain.Member)
	 * 
	 * 4. MemberDao 객체를 사용해 인자로 받은 회원 정보를 갱신한다.
	 */
	@Override
	public void updateMember(Member member) throws DataNotFoundException{
		if(memberDataAccess.memberIDExists(member.getMemberID())){
			memberDataAccess.updateMember(member);
		} else {
			throw new DataNotFoundException("존재하지 않는 회원입니다.");
		}
      
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#removeMember(casestudy.business.domain.Member)
	 * 
	 * 5. MemberDao 객체를 사용해 인자로 받은 회원 정보를 삭제한다.
	 */
	@Override
	public void removeMember(Member member) throws DataNotFoundException{
		if(memberDataAccess.memberIDExists(member.getMemberID())){
			memberDataAccess.deleteMember(member);
		} else {
			throw new DataNotFoundException("존재하지 않는 회원입니다.");
		}	
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#loginCheck(java.lang.String, java.lang.String)
	 * 
	 * 6. MemberDao 객체를 사용해 인자로 받은 memberID, password로 
	 *   로그인 가능 여부를 확인하고, 해당 회원 정보를 리턴한다.
	 */
	@Override
	public Member loginCheck(String id, String password) {
		return memberDataAccess.checkMember(id, password);
	}

	/* (non-Javadoc)
	 * @see casestudy.business.service.MemberService#getAllMembers()
	 * 
	 * 7. MemberDao 객체를 사용해 모든 회원 정보를 구해서 리턴한다.
	 */
	@Override
	public Member[] getAllMembers() {
		return memberDataAccess.selectAllMembers();
	}

}
