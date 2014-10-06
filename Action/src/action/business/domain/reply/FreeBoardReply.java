package action.business.domain.reply;

public class FreeBoardReply extends Reply{

	private int freeBoardNum;	
	
	/** 댓글 작성용 */
	public FreeBoardReply (int replyNum, int freeBoardNum, String memberID, String replyContent) {
		super(replyNum, memberID, replyContent);
		this.freeBoardNum = freeBoardNum;
	}
	
	/** 댓글의 댓글 작성용, 댓글 상세 조회용 */
	public FreeBoardReply (int replyNum, int freeBoardNum, String memberID, String replyContent,
			int masterNum, int replyOrder, int replyStep) {
		super(replyNum, memberID, replyContent, masterNum, replyOrder, replyStep);
		this.freeBoardNum = freeBoardNum;
	}
	
	/** 댓글 목록 조회용 */
	public FreeBoardReply (int replyNum, String memberID, String replyContent, int replyStep) {
		super(replyNum, memberID, replyContent, replyStep);
	}

	public int getFreeBoardNum() {
		return freeBoardNum;
	}

	public void setFreeBoardNum(int freeBoardNum) {
		this.freeBoardNum = freeBoardNum;
	}
	
	

	
}
