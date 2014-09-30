package action.business.domain.reply;

public class QnABoardReply extends Reply {
	
	private int qnaBoradNum;

	public QnABoardReply(int replyNum, String memberID,String replyContent, int masterNum, int replyOrder,
			int replyStep,  int qnaBoradNum) {
		super(replyNum, memberID, replyContent, masterNum, replyOrder, replyStep);
		this.qnaBoradNum = qnaBoradNum;
	}
	
	

	public QnABoardReply(String memberID, String replyContent) {
		super(memberID, replyContent);
	
	}

	public int getQnaBoradNum() {
		return qnaBoradNum;
	}

	public void setQnaBoradNum(int qnaBoradNum) {
		this.qnaBoradNum = qnaBoradNum;
	}
	
	
}
