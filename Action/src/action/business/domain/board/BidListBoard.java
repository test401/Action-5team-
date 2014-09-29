package action.business.domain.board;

/**4. 입찰목록 테이블
 * 입찰번호, 
 * 경매게시글 번호(게시판 공통), 입찰자ID(게시판 공통-회원ID), 입찰 가격*/
public class BidListBoard extends Board {
	
	private int bidNum;
	private int price;

	//입찰 목록
	public BidListBoard(int bidNum, int boardNum, String memberID, int price) {
		super(boardNum, memberID);
		this.price = price;
		this.bidNum = bidNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBidNum() {
		return bidNum;
	}

	public void setBidNum(int bidNum) {
		this.bidNum = bidNum;
	}
	
	
	
	
}
