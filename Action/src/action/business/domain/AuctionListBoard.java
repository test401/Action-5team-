package action.business.domain;

/**1. 회원경매목록 테이블
 * 목록번호,게시글 번호(게시판 공통), 회원ID(게시판 공통)*/
public class AuctionListBoard extends Board {
	
	private int listNum;

	public AuctionListBoard(int boardNum, String memberID, int listNum) {
		super(boardNum, memberID);
		this.listNum = listNum;
	}

	public int getListNum() {
		return listNum;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	
	
	
	
}
