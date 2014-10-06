package action.business.domain.board;

/**6. 문의 게시판 테이블
 * 문의게시글 번호(게시판 공통), 제목(게시판 공통), 작성자(게시판 공통), 내용(게시판 공통) */
public class QnABoard extends Board {
	
	//게시판 목록 조회
	public QnABoard(int boardNum, String title, String memberID) {
		super(boardNum, title, memberID);
	}
	//게시판 상세 조회
	public QnABoard(int boardNum, String title, String memberID, String contents) {
		super(boardNum, title, memberID, contents);
	}
	
	//게시판 작성
	public QnABoard(String title, String memberID, String contents) {
		super(title, memberID, contents);
	}
	//게시판 수정
	public QnABoard(String title, String content) {
		super(title, content);
	}
	

}
