package action.business.domain.reply;

public class QnABoardReply extends Reply {
	
	private int qnaBoradNum;

	public QnABoardReply(int replyNum, int masterNum, int replyOrder,
			int replyStep, String replyContent, int qnaBoradNum) {
		super(replyNum, masterNum, replyOrder, replyStep, replyContent);
		this.qnaBoradNum = qnaBoradNum;
	}

	public int getQnaBoradNum() {
		return qnaBoradNum;
	}

	public void setQnaBoradNum(int qnaBoradNum) {
		this.qnaBoradNum = qnaBoradNum;
	}
	
	
}
