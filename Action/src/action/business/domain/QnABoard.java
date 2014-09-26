package action.business.domain;

/**6. 문의 게시판 테이블
 * 문의게시글 번호(게시판 공통), 제목(게시판 공통), 작성자(게시판 공통), 내용(게시판 공통) */
public class QnABoard extends Board {
	
	public QnABoard(int boardNum, String title, String memberID, String content) {
		super(boardNum, title, memberID, content);
	}
	
	

}
