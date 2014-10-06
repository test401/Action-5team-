package action.business.domain.board;


/**3. 경매 게시판 테이블
 * 게시글번호(게시판 공통), 글제목(게시판 공통), 작성자(게시판 공통), 대표이미지, 내용,
 * 카테고리ID, 시작일시, 종료일시, 즉시구매여부, 시작가, 
 * 즉시구매가, 현재 입찰가격*/

public class AuctionBoard extends Board {

	private String contents;
	private String startTime;
	private String endTime;
	private int categoryID;
	private int isImmediately;
	private int startPrice;
	private int immediatelyPrice;
	private int currentPrice;
	private String image;
	private String mainImage;
	
	
	/** 경매 리스트 조회용 */
	public AuctionBoard(int boardNum, String title, String memberID,
			String endTime, int categoryID,
			int immediatelyPrice, int currentPrice, String mainImage) {

		super(boardNum, title, memberID);
		this.endTime = endTime;
		this.categoryID = categoryID;
		this.immediatelyPrice = immediatelyPrice;
		this.currentPrice = currentPrice;
		this.mainImage = mainImage;
	}
	
	/** 경매 상세 조회용 */
	public AuctionBoard(int boardNum, String title, String memberID, 
			String contents, String startTime,
			String endTime, int categoryID, int isImmediately, int startPrice,
			int immediatelyPrice, int currentPrice, String image, String mainImage) {

		super(boardNum,title,memberID);
		this.contents = contents;
		this.startTime = startTime;
		this.endTime = endTime;
		this.categoryID = categoryID;
		this.isImmediately = isImmediately;
		this.startPrice = startPrice;
		this.immediatelyPrice = immediatelyPrice;
		this.currentPrice = currentPrice;
		this.image = image;
		this.mainImage = mainImage;
	}
	
	/** 경매 작성용 */

	public AuctionBoard(String memberID, int categoryID, String title,
			int startPrice, int immediatelyPrice, int isImmediately, String endTime, String contents,
			int currentPrice,  String mainImage, String image) {

		super(title, memberID);
		this.contents = contents;
		this.endTime = endTime;
		this.categoryID = categoryID;
		this.isImmediately = isImmediately;
		this.startPrice = startPrice;
		this.immediatelyPrice = immediatelyPrice;
		this.currentPrice = currentPrice;
		this.image = image;
		this.mainImage = mainImage;
	}
	
	/** 경매 수정용 */

	public AuctionBoard(String title, String contents, int categoryID,
			int isImmediately, int immediatelyPrice, String image, String mainImage) {
		super(title);
		this.contents = contents;
		this.categoryID = categoryID;
		this.isImmediately = isImmediately;
		this.immediatelyPrice = immediatelyPrice;
		this.image = image;
		this.mainImage = mainImage;
	}

	/** 입찰하기 */
	public AuctionBoard(int boardNum, int currentPrice) {
		super(boardNum);
		this.currentPrice = currentPrice;
	}

	

	public AuctionBoard() {
		// TODO Auto-generated constructor stub
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
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

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	@Override
	public String toString() {
		return "AuctionBoard [contents=" + contents + ", startTime="
				+ startTime + ", endTime=" + endTime + ", categoryID="
				+ categoryID + ", isImmediately=" + isImmediately
				+ ", startPrice=" + startPrice + ", immediatelyPrice="
				+ immediatelyPrice + ", currentPrice=" + currentPrice
				+ ", image=" + image + ", mainImage=" + mainImage + "]";
	}
	
	
	
	
	
}
