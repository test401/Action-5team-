package action.business.service;

import action.business.domain.member.Member;
import action.business.service.DataDuplicatedException;
import action.business.service.DataNotFoundException;

public interface MemberService {
	
	/**
     * 인수로 주어진 Member 객체의 정보에 해당하는 회원을 등록(회원 가입)한다.
     *
     * @param member 등록하고자 하는 회원정보를 담고 있는 Member 객체
     * @throws DataDuplicatedException 중복된 memberID를 가진 기존 회원이 존재할 경우 발생 
     */
	public void registerMember(Member member) throws DataDuplicatedException;
	
    /**
     * 인수로 주어진 memberID에 해당하는 회원을 검색한다.
     *
     * @param memberID 검색하고자 하는 회원의 memberID
     * @return 검색된 회원정보를 담고 있는 Member 객체
     * @throws DataNotFoundException memberID에 해당하는 회원이 존재하지 않을 경우 발생 
     */
	public Member findMember(String memberID) throws DataNotFoundException;
	
    /**
     * 인수로 주어진 Member 객체의 정보로 해당 회원정보를 갱신(회원정보 변경)한다.
     *
     * @param member 갱신하고자 하는 회원정보를 담고 있는 Member 객체
     * @throws DataNotFoundException 해당 회원이 존재하지 않을 경우 발생
     */
	public void updateMember(Member member) throws DataNotFoundException;
	
    /**
     * 인수로 주어진 Member 객체의 정보에 해당하는 회원을 제거(회원 탈퇴)한다.
     *
     * @param member 제거하고자 하는 회원정보를 담고 있는 Member 객체
     * @throws DataNotFoundException 해당 회원이 존재하지 않을 경우 발생
     */
	public void removeMember(Member member) throws DataNotFoundException;
	
    /**
     * 인수로 주어진 memberID와 password에 해당하는 회원정보를 확인하여 
     * 유효한 회원인가 여부(로그인 가능 여부)를 판별한다.<br/>
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
	public Member loginCheck(String memberID, String password);
	
    /**
     * 모든 회원을 검색한다.
     * 
     * @return 검색된 모든 회원정보를 담고 있는 Member 배열
     */
	public Member[] getAllMembers();

}
