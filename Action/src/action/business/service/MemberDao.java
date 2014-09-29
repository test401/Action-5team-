package action.business.service;

import action.business.domain.member.Member;

public interface MemberDao {
	
	/**
     * 데이터 저장소에 인수로 주어진 Member 객체의 정보를 추가한다.
     *
     * @param member 추가하고자 하는 회원정보를 담고 있는 Member 객체
     */
	public void insertMember(Member member);
	
	
    /**
     * 데이터 저장소에서 인수로 주어진 memberID에 해당하는 회원정보를 검색한다.
     *
     * @param memberID 검색하고자 하는 회원의 memberID
     * @return 검색된 회원정보를 담고 있는 Member 객체
     */
	public Member selectMember(String memberID);
	
	
    /**
     * 데이터 저장소에서 인수로 주어진 Member 객체의 정보를 갱신한다.
     *
     * @param member 갱신하고자 하는 회원정보를 담고 있는 Member 객체
     */
	public void updateMember(Member member);
	
	
    /**
     * 데이터 저장소에서 인수로 주어진 Member 객체의 정보를 삭제한다.
     *
     * @param member 삭제하고자 하는 회원정보를 담고 있는 Member 객체
     */
	public void deleteMember(Member member);
	
	
    /**
     * 데이터 저장소에서 인수로 주어진 memberID와 password에 해당하는 회원정보를 확인하여 
     * 유효한 회원인가 여부를 판별한다.
     * 
     *  아이디가 존재하지 않을 경우에는 Member의 check 값을 Member.INVALID_ID 로,
     *  아이디는 존재하나 패스워드가 맞지 않을 경우에는 Member의 check 값을 Member.INVALID_PASSWORD 로,
     *  아이디와 패스워드가 모두 일치할 경우에는 Member의 check의 값은 Member.VALID_MEMBER 로 세팅하고 
     *  해당 회원정보를 담은 Member 객체를 리턴한다.
     *
     * @param memberID 확인하고자 하는 회원의 memberID
     * @param password 확인하고자 하는 회원의 password
     * @return 유효 회원 여부 등의 회원정보를 담고 있는 Member 객체
     */
	public Member checkMember(String memberID, String password);
	
	
    /**
     * 데이터 저장소에서 모든 회원정보를 검색한다.
     * 
     * @return 검색된 모든 회원정보를 담고 있는 Member 배열
     */
	public Member[] selectAllMembers();
	
	
    /**
     * 데이터 저장소에 인수로 주어진 memberID에 해당하는 기존 회원정보가 있는지 확인한다.
     * 
     * @return 해당하는 회원정보가 존재하면 true, 존재하지 않으면 false
     */
	public boolean memberIDExists(String memberID);

}
