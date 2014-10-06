package action.business.service.reply;

import java.util.List;

import action.business.domain.reply.QnABoardReply;

public interface QnABoardReplyDao {
	/**
	 * 조건에 맞는 모든 게시물 목록을 조회한다.
	 * 
	 * @param boardNum 구하고자 하는 댓글들을 담은 게시글의 번호
	 * @return 검색된 게시물 목록을 담고 있는 List 객체
	 */
	public List<QnABoardReply> selectReplyList(int boardNum);

	
	
	/**
	 * 조건에 맞는 모든 댓글 개수를 조회한다.
	 * 
	 * @param boardNum 구하고자 하는 댓글들을 담은 게시글의 번호
	 * @return 검색된 모든 댓글의 개수
	 */
	public int selectReplyCount(int boardNum);

	
	
	/**
	 * 지정된 번호에 해당하는 댓글을 검색한다.
	 * @param replyNum 검색하고자 하는 댓글의 번호
	 * @return
	 */
	public QnABoardReply selectReply(int replynum);

	
	
	/**
	 * 인수로 주어진 번호에 해당하는 댓글이 있는지 확인한다.
	 * 
	 * @param replyNum 존재여부를 확인하려는 댓글의 번호
	 * @return 해당하는 댓글이 존재하면 true, 존재하지 않으면 false
	 */
	public boolean isReplyExists(int replynum);

	
	
	/**
	 * 인수로 주어진 Reply 객체의 정보로 새로운 댓글을 등록한다.
	 * 
	 * @param reply 등록할 댓글 정보를 담고 있는 Reply 객체
	 */
	public void insertReply(QnABoardReply reply);
	
	
	
	/**
	 * 인수로 주어진 Reply 객체의 정보로 댓글에 새로운 댓글을 등록한다.
	 * 
	 * @param reply 등록할 답글 정보를 담고 있는 Reply 객체
	 */
	public void InsertReplyOnReply(QnABoardReply reply);
	
	
	
	/**
	 * 인수로 주어진 Reply 객체의 정보로 기존 게시글을 수정한다.
	 * 
	 * @param reply 수정할 게시글 정보를 담고 있는 Reply 객체
	 */	
	public void updateReply(QnABoardReply reply);

	
	
	/**
	 * 인수로 주어진 번호에 해당하는 게시글을 삭제한다.
	 * 
	 * @param replyNum 삭제하려는 댓글의 번호
	 */
	public void deleteReply(int replyNum);

}
