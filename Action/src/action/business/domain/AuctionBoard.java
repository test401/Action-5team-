package action.business.domain;

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
	private Date endTiem;
	private int catagoryID;
	private int isImmediately;
	private int startPrice;
	private int immediatelyPrice;
	private int currentPrice;
	
	public AuctionBoard(int boardNum, String title, String memberID, 
			String image, BLOB contents, Date startTime,
			Date endTiem, int catagoryID, int isImmediately, int startPrice,
			int immediatelyPrice, int currentPrice) {
		super(boardNum,title,memberID);
		this.image = image;
		this.contents = contents;
		this.startTime = startTime;
		this.endTiem = endTiem;
		this.catagoryID = catagoryID;
		this.isImmediately = isImmediately;
		this.startPrice = startPrice;
		this.immediatelyPrice = immediatelyPrice;
		this.currentPrice = currentPrice;
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

	public Date getEndTiem() {
		return endTiem;
	}

	public void setEndTiem(Date endTiem) {
		this.endTiem = endTiem;
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
