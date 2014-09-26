package action.business.domain;

public class AutionBoardReply extends Reply {

	private int freeBoardNum;
	private String memberID;
	private int replyPwd;
	private int isMember;

	public AutionBoardReply(int replyNum, String memberID, int masterNum, int replyOrder,
			int replyStep, String replyContent, int freeBoardNum, int replyPwd, int isMember) {
		super(replyNum, masterNum, replyOrder, replyStep, replyContent);
		this.freeBoardNum = freeBoardNum;
		this.memberID = memberID;
		this.replyPwd = replyPwd;
		this.isMember = isMember;
	}

	public int getFreeBoardNum() {
		return freeBoardNum;
	}

	public void setFreeBoardNum(int freeBoardNum) {
		this.freeBoardNum = freeBoardNum;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int getReplyPwd() {
		return replyPwd;
	}

	public void setReplyPwd(int replyPwd) {
		this.replyPwd = replyPwd;
	}

	public int getIsMember() {
		return isMember;
	}

	public void setIsMember(int isMember) {
		this.isMember = isMember;
	}
	
	

	
	
	
}
