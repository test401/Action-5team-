package action.business.domain.reply;

public class FreeBoardReply extends Reply{

	private int freeBoardNum;

	public FreeBoardReply(int replyNum, String memberID, int masterNum, int replyOrder,
			int replyStep, String replyContent, int freeBoardNum) {
		super(replyNum, memberID, replyContent, masterNum, replyOrder, replyStep);
		this.freeBoardNum = freeBoardNum;
	}
	
	/**댓글 작성 */
	public FreeBoardReply(String memberID, String replyContent) {
		super(memberID, replyContent);
	}

	public int getFreeBoardNum() {
		return freeBoardNum;
	}

	public void setFreeBoardNum(int freeBoardNum) {
		this.freeBoardNum = freeBoardNum;
	}
	
	

	
}
