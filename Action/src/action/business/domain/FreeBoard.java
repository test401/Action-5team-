package action.business.domain;

/**8. 자유 게시판 테이블
 * 자유게시글 번호(게시판 공통), 제목(게시판 공통), 작성자(게시판 공통), 내용(게시판 공통), 공지사항여부 */
public class FreeBoard extends Board{
	
	private int isNotice;

	public FreeBoard(int boardNum, String title, String memberID, String content, int isNotice) {
		super(boardNum, title, memberID, content);
		this.isNotice = isNotice;
	}

	public int getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
	}

	
	
}
