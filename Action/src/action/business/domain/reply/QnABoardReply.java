package action.business.domain.reply;

public class QnABoardReply extends Reply {
	
	private int qnaBoardNum;

	public QnABoardReply(int replyNum, String memberID, String replyContent, int masterNum, int replyOrder,
			int replyStep,  int qnaBoardNum) {
		super(replyNum, memberID, replyContent, masterNum, replyOrder, replyStep);
		this.qnaBoardNum = qnaBoardNum;
	}
	
	

	public QnABoardReply(int replyNum, String memberID, String replyContent, int replyStep) {
		super(replyNum, memberID, replyContent, replyStep);
	
	}

	public int getQnaBoardNum() {
		return qnaBoardNum;
	}

	public void setQnaBoardNum(int qnaBoardNum) {
		this.qnaBoardNum = qnaBoardNum;
	}
	
	
}
