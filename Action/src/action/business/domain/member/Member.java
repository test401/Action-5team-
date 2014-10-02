package action.business.domain.member;

/**
 * 사이트 등록되는 모든 회원과 관리자 계정 도메인 
 * @author Sin-eon
 */
public class Member {
	
	/** 유효한 회원임을 나타내는 상수 */
	public static final int VALID_MEMBER = 1;
	
	/** memberID가 존재하지 않는 회원임을 나타내는 상수 */
	public static final int INVALID_ID = 0; 
	
	/** password가 일치하지 않는 회원임을 나타내는 상수 */
	public static final int INVALID_PASSWORD = -1;
	
	
	/** 회원 ID */
	private String memberID;
	
	/** 회원 비밀번호 */
	private String password;
	
	/** 회원 이름 */
	private String name;
	
	/** 회원 주소 */
	private String address;
	
	/** 회원 연락처 */
	private String tel;
	
	/** 회원 경고 회수 */
	private int warnCount;
	
	/**
	 * 회원 등급
	 * 0은 일반 회원, 1은 관리자로 구분한다.
	 */
	private int isAdmin;
	
	/** 회원의 로그인 유효성 체크 */
	private int loginCheck;
	
	
	/** 기본 생성자 */
	public Member(){
		
	}	
	
	/** 
	 * 회원 등록용 생성자 
	 * 
	 */
	public Member(String memberID, String password, String name, String address,
			String tel, int warnCount, int isAdmin) {		
		this.memberID = memberID;
		this.password = password;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.warnCount = warnCount;
		this.isAdmin = isAdmin;
	}
		
	/** 회원 로그인용 생성자 */
	public Member(String memberID, String password) {		
		this.memberID = memberID;
		this.password = password;
	}	
	
	/** 회원 정보수정용 생성자 */
	public Member(String password, String name, String address, String tel) {		
		this.password = password;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}

	/** getter & setter */
	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getWarnCount() {
		return warnCount;
	}

	public void setWarnCount(int warnCount) {
		this.warnCount = warnCount;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getLoginCheck() {
		return loginCheck;
	}

	public void setLoginCheck(int loginCheck) {
		this.loginCheck = loginCheck;
	}

	@Override
	public String toString() {
		return "Member [id=" + memberID + ", password=" + password + ", name=" + name
				+ ", address=" + address + ", tel=" + tel + ", warningCount="
				+ warnCount + ", memberClass=" + isAdmin + "]";
	}

}
