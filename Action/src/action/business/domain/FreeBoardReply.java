package action.business.domain;

public class FreeBoardReply extends Reply{

	private int freeBoardNum;

	public FreeBoardReply(int replyNum, int masterNum, int replyOrder,
			int replyStep, String replyContent, int freeBoardNum) {
		super(replyNum, masterNum, replyOrder, replyStep, replyContent);
		this.freeBoardNum = freeBoardNum;
	}

	public int getFreeBoardNum() {
		return freeBoardNum;
	}

	public void setFreeBoardNum(int freeBoardNum) {
		this.freeBoardNum = freeBoardNum;
	}
	
	

	
}
