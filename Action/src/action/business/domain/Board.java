package action.business.domain;

/**게시판 공통 요소
 * 게시글 번호, 회원ID, 제목, 작성자, 내용
 * 
 * @author 선호
 * */
public abstract class Board {
	
	/** 게시글번호*/
	private int boardNum;
	/** 회원ID*/
	private String memberID;
	/** 제목*/
	private String title;
	/** 내용*/
	private String content;
	
	public Board() {
		
	}
	
	
	
	public Board(int boardNum, String memberID) {
		super();
		this.boardNum = boardNum;
		this.memberID = memberID;
	}
	
	public Board(String title, String memberID) {
		super();
		this.title = title;
		this.memberID = memberID;
	}
	
	public Board(String title, String memberID, String content) {
		super();
		this.title = title;
		this.memberID = memberID;
		this.content = content;
	}
	
	public Board(int boardNum, String title, String memberID) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.memberID = memberID;
	}
	
	public Board(int boardNum, String title, String memberID, String content) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.memberID = memberID;
		this.content = content;
	}

	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
