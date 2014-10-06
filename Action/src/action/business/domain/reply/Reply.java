package action.business.domain.reply;

/**댓글 공통 요소
 * 댓글 번호, 마스터 댓글 번호, 댓글의 댓글 번호, 댓글의 스탭, 댓글 내용
 * 
 * @author 선호
 * */
public abstract class Reply {
	
	/** 댓글 번호*/
	private int replyNum;
	/** 작성자*/
	private String memberID;
	/** 마스터 댓글 번호*/
	private int masterNum;
	/** 댓글의 댓글 번호*/
	private int replyOrder;
	/** 댓글의 스탭*/
	private int replyStep;
	/** 댓글 내용*/
	private String replyContent;
	
	public Reply(int replyNum, String memberID,String replyContent, 
			int masterNum, int replyOrder, int replyStep) {
		super();
		this.replyNum = replyNum;
		this.memberID = memberID;
		this.replyContent = replyContent;
		this.masterNum = masterNum;
		this.replyOrder = replyOrder;
		this.replyStep = replyStep;
	}
	
	public Reply(int replyNum, String replyContent, 
			int masterNum, int replyOrder, int replyStep) {
		super();
		this.replyNum = replyNum;
		this.replyContent = replyContent;
		this.masterNum = masterNum;
		this.replyOrder = replyOrder;
		this.replyStep = replyStep;
	}	
	
	/** 비회원 댓글 출력용 */
	public Reply(int replyNum, String replyContent, int replyStep){
		this.replyNum = replyNum;
		this.replyContent = replyContent;
		this.replyStep = replyStep;
	}
	
	/** 댓글 출력용 */
	public Reply(int replyNum, String memberID, String replyContent, int replyStep) {
		super();	
		this.replyNum = replyNum;
		this.memberID = memberID;
		this.replyContent = replyContent;
		this.replyStep = replyStep;
	}
	
	/** 댓글 작성용 */
	public Reply(int replyNum, String memberID, String replyContent){
		this.replyNum = replyNum;
		this.memberID = memberID;
		this.replyContent = replyContent;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getMasterNum() {
		return masterNum;
	}

	public void setMasterNum(int masterNum) {
		this.masterNum = masterNum;
	}

	public int getReplyOrder() {
		return replyOrder;
	}

	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
	}

	public int getReplyStep() {
		return replyStep;
	}

	public void setReplyStep(int replyStep) {
		this.replyStep = replyStep;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	
	
	
	
	
}
