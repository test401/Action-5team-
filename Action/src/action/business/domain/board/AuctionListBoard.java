package action.business.domain.board;

/**1. 회원경매목록 테이블
 * 목록번호,게시글 번호(게시판 공통), 회원ID(게시판 공통)*/
public class AuctionListBoard extends Board {
	
	private int listNum;
	private int price;

	// 경매완료 리스트
	public AuctionListBoard(int listNum, String memberID,int boardNum,  int price) {
		super(boardNum, memberID);
		this.listNum = listNum;
		this.price = price;
	}
	
	// 경매완료 삽입
	public AuctionListBoard(String memberID,int boardNum,  int price) {
		super(boardNum, memberID);
		this.price = price;
	}
	
	
	public int getListNum() {
		return listNum;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
