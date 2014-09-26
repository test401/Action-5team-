package action.business.domain;

import java.util.Date;

import oracle.sql.BLOB;

public class Board_fomat {
	
	/**게시판 공통 요소
	 * 게시글 번호, 회원ID, 제목, 작성자, 내용*/
	private String boardNum;
	private String memberID;
	private String title;
	private String writer;
	private String content;
	
	/**댓글 공통 요소
	 * 댓글 번호, 마스터 댓글 번호, 댓글의 댓글 번호, 댓글의 스탭, 댓글 내용*/
	private int replyNum;
	private int masterNum;
	private int replyOrder;
	private int replyStep;
	private String replyContent;
	
	/**1. 회원경매목록 테이블
	 * 목록번호,게시글 번호(게시판 공통), 회원ID(게시판 공통)*/
	private int listNum;
	
	
	/**2. 카테고리테이블 테이블
	 * 카테고리ID, 카테고리 이름, 제목(게시판 공통), 작성자(게시판 공통)*/
	private int categoryID;
	private String categoryName;
	
	/**3. 경매 게시판 테이블
	 * 게시글번호(게시판 공통), 글제목(게시판 공통), 작성자(게시판 공통), 대표이미지, 내용,
	 * 카테고리(카테고리테이블), 시작일시, 종료일시, 즉시구매여부, 시작가, 
	 * 즉시구매가, 현재 입찰가격*/
	private String image;
	private BLOB contents;
	private Date startTime;
	private Date endTiem;
	private int isImmediately;
	private int startPrice;
	private int immediatelyPrice;
	private int currentPrice;

	/**4. 입찰목록 테이블
	 * 입찰번호, 
	 * 경매게시글 번호(게시판 공통), 입찰자ID(게시판 공통-회원ID), 입찰 가격(경매 게시판 테이블)*/
	private int bidNum;
	
	/**5. 경매 게시판 댓글 테이블
	 * 댓글번호(댓글 공통), 경매게시글 번호(게시판 공통), 작성자(게시판 공통), 댓글내용(댓글 공통), 
	 * 마스터 댓글 번호(댓글 공통), 댓글의 댓글 번호(댓글 공통), 댓글의 스탭(댓글 공통), 
	 * 댓글 패스워드, 회원 여부*/
	
	private int replyPwd;
	private int isMember;
	
	/**6. 문의 게시판 테이블
	 * 문의게시글 번호(게시판 공통), 제목(게시판 공통), 작성자(게시판 공통), 내용(게시판 공통) */
	
	/**7. 문의 게시판 답글 테이블
	 * 문의게시글 댓글 번호(댓글 공통), 문의게시글 번호(게시판 공통), 내용(게시판 공통), 작성자(게시판 공통) */
	
	/**8. 자유 게시판 테이블
	 * 자유게시글 번호(게시판 공통), 제목(게시판 공통), 작성자(게시판 공통), 내용(게시판 공통), 공지사항여부 */
	private int isNotice;

	/**9. 자유 게시판 댓글 테이블
	 * 댓글 번호(댓글 공통), 자유게시글 번호(공통), 작성자(공통), 댓글내용(댓글 공통)
	 * 마스터댓글번호(댓글 공통), 댓글의 댓글번호(댓글 공통), 댓글의 스탭(댓글 공통) */
	
	public Board_fomat() {
		
	}
	
	/**1. 경매목록 테이블 */
	public Board_fomat(String boardNum, String memberID, int listNum) {
		super();
		this.listNum = listNum;
		this.boardNum = boardNum;
		this.memberID = memberID;
	}

	/**2. 카테고리 테이블 */
	public Board_fomat(int categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	/**3. 경매 게시판 테이블 */
	public Board_fomat(String boardNum, String title, String writer, String image,
			BLOB contents, int categoryID, Date startTime, Date endTiem, int isImmediately,
			int startPrice, int immediatelyPrice, int currentPrice) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.writer = writer;
		this.image = image;
		this.contents = contents;
		this.categoryID = categoryID;
		this.startTime = startTime;
		this.endTiem = endTiem;
		this.isImmediately = isImmediately;
		this.startPrice = startPrice;
		this.immediatelyPrice = immediatelyPrice;
		this.currentPrice = currentPrice;
	}

	/**4. 입찰 목록 테이블 */
	public Board_fomat(String boardNum, String memberID, int currentPrice, int bidNum) {
		super();
		this.bidNum = bidNum;
		this.boardNum = boardNum;
		this.memberID = memberID;
		this.currentPrice = currentPrice;
	}

	/**5. 경매 게시판 댓글 테이블 */
	public Board_fomat(String boardNum, String writer, int replyNum, int masterNum,
			int replyOrder, int replyStep, String replyContent, int replyPwd,
			int isMember) {
		super();
		this.replyNum = replyNum;
		this.boardNum = boardNum;
		this.writer = writer;
		this.replyContent = replyContent;
		this.masterNum = masterNum;
		this.replyOrder = replyOrder;
		this.replyStep = replyStep;
		this.replyPwd = replyPwd;
		this.isMember = isMember;
	}
	
	/**6. 문의 게시판 테이블 */
	public Board_fomat(String boardNum, String title, String writer, String content) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	/**7. 문의 게시판 답글 테이블 */
	public Board_fomat(String boardNum, String writer, String content, int replyNum) {
		super();
		this.replyNum = replyNum;
		this.boardNum = boardNum;
		this.content = content;
		this.writer = writer;
	}

	/**8. 자유 게시판 테이블 */
	public Board_fomat(String boardNum, String title, String writer, String content,
			int isNotice) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.isNotice = isNotice;
	}

	/**9. 자유 게시판 댓글 테이블 */
	public Board_fomat(String boardNum, String writer, int replyNum, int masterNum,
			int replyOrder, int replyStep, String replyContent) {
		super();
		this.replyNum = replyNum;
		this.boardNum = boardNum;
		this.writer = writer;
		this.replyContent = replyContent;
		this.masterNum = masterNum;
		this.replyOrder = replyOrder;
		this.replyStep = replyStep;
	}
	
}
