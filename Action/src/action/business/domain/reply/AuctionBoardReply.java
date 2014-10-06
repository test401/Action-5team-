package action.business.domain.reply;

public class AuctionBoardReply extends Reply {

	private int auctionBoardNum;
	private String writer;
	private String replyPwd;
	private int isMember;
	
	public AuctionBoardReply(int replyNum, String writer, int masterNum, int replyOrder,
			int replyStep, String replyContent, int auctionBoardNum, String replyPwd, int isMember) {
		super(replyNum, replyContent, masterNum, replyOrder, replyStep);
		this.auctionBoardNum = auctionBoardNum;
		this.writer = writer;
		this.replyPwd = replyPwd;
		this.isMember = isMember;
	}	
	
    /** 댓글 등록 */
	public AuctionBoardReply(int replyNum, String writer,
			String replyContent, String replyPwd, int masterNum, int replyOrder, int replyStep,
			int auctionBoardNum) {
		super(replyNum, replyContent, masterNum, replyOrder,
				replyStep);
		this.auctionBoardNum = auctionBoardNum;
		this.writer = writer;
		this.replyPwd = replyPwd;
	}
	
	/** 댓글 출력 */
	public AuctionBoardReply(int replyNum, String writer, String replyContent, int replyStep){
		super(replyNum, replyContent, replyStep);
		this.writer = writer;
	}
	

	public int getAuctionBoardNum() {
		return auctionBoardNum;
	}


	public void setAuctionBoardNum(int auctionBoardNum) {
		this.auctionBoardNum = auctionBoardNum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReplyPwd() {
		return replyPwd;
	}

	public void setReplyPwd(String replyPwd) {
		this.replyPwd = replyPwd;
	}

	public int getIsMember() {
		return isMember;
	}

	public void setIsMember(int isMember) {
		this.isMember = isMember;
	}
	
	

	
	
	
}
