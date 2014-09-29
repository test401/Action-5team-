package action.business.domain.board;

import oracle.sql.BLOB;

/**8. 자유 게시판 테이블
 * 자유게시글 번호(게시판 공통), 제목(게시판 공통), 작성자(게시판 공통), 내용(게시판 공통), 공지사항여부 */
public class FreeBoard extends Board{
	
	private int isNotice;

	//게시판 목록 조회
	public FreeBoard(int boardNum, String title, String memberID, int isNotice) {
		super(boardNum, title, memberID);
		this.isNotice = isNotice;
	}

	//게시판 상세 조회
	public FreeBoard(int boardNum, String title, String memberID, BLOB contents, int isNotice) {
		super(boardNum, title, memberID, contents);
		this.isNotice = isNotice;
	}
	
	//게시글 작성
	public FreeBoard(String title, String memberID, BLOB contents, int isNotice) {
		super(title, memberID, contents);
		this.isNotice = isNotice;
	}
	
	//게시글 수정
	public FreeBoard(String title, String content, int isNotice) {
		super(title, content);
		this.isNotice = isNotice;
	}

	public int getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
	}

	
	
}
