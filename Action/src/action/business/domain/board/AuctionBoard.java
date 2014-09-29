package action.business.domain.board;

import java.util.Date;

import oracle.sql.BLOB;


/**3. 경매 게시판 테이블
 * 게시글번호(게시판 공통), 글제목(게시판 공통), 작성자(게시판 공통), 대표이미지, 내용,
 * 카테고리ID, 시작일시, 종료일시, 즉시구매여부, 시작가, 
 * 즉시구매가, 현재 입찰가격*/

public class AuctionBoard extends Board {

	private String image;
	private BLOB contents;
	private Date startTime;
	private Date endTime;
	private int catagoryID;
	private int isImmediately;
	private int startPrice;
	private int immediatelyPrice;
	private int currentPrice;
	
	
	/** 경매 리스트 조회용 */
	public AuctionBoard(int boardNum, String title, String memberID,
			String image, Date endTime, int catagoryID,
			int immediatelyPrice, int currentPrice) {
		super(boardNum, title, memberID);
		this.image = image;
		this.endTime = endTime;
		this.catagoryID = catagoryID;
		this.immediatelyPrice = immediatelyPrice;
		this.currentPrice = currentPrice;
	}
	
	/** 경매 상세 조회용 */
	public AuctionBoard(int boardNum, String title, String memberID, 
			String image, BLOB contents, Date startTime,
			Date endTime, int catagoryID, int isImmediately, int startPrice,
			int immediatelyPrice, int currentPrice) {
		super(boardNum,title,memberID);
		this.image = image;
		this.contents = contents;
		this.startTime = startTime;
		this.endTime = endTime;
		this.catagoryID = catagoryID;
		this.isImmediately = isImmediately;
		this.startPrice = startPrice;
		this.immediatelyPrice = immediatelyPrice;
		this.currentPrice = currentPrice;
	}
	
	/** 경매 작성용 */
	public AuctionBoard(String title, String memberID, String image, BLOB contents, Date startTime,
			Date endTime, int catagoryID, int isImmediately, int startPrice,
			int immediatelyPrice, int currentPrice) {
		super(title, memberID);
		this.image = image;
		this.contents = contents;
		this.startTime = startTime;
		this.endTime = endTime;
		this.catagoryID = catagoryID;
		this.isImmediately = isImmediately;
		this.startPrice = startPrice;
		this.immediatelyPrice = immediatelyPrice;
		this.currentPrice = currentPrice;
	}
	
	/** 경매 수정용 */
	public AuctionBoard(String title, String image, BLOB contents, int catagoryID,
			int isImmediately, int immediatelyPrice) {
		super(title);
		this.image = image;
		this.contents = contents;
		this.catagoryID = catagoryID;
		this.isImmediately = isImmediately;
		this.immediatelyPrice = immediatelyPrice;
	}

	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BLOB getContents() {
		return contents;
	}

	public void setContents(BLOB contents) {
		this.contents = contents;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getCatagoryID() {
		return catagoryID;
	}

	public void setCatagoryID(int catagoryID) {
		this.catagoryID = catagoryID;
	}

	public int getIsImmediately() {
		return isImmediately;
	}

	public void setIsImmediately(int isImmediately) {
		this.isImmediately = isImmediately;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	public int getImmediatelyPrice() {
		return immediatelyPrice;
	}

	public void setImmediatelyPrice(int immediatelyPrice) {
		this.immediatelyPrice = immediatelyPrice;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	
	
	
}
