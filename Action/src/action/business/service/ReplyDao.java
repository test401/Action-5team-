package action.business.service;

import java.util.List;

import action.business.domain.reply.Reply;

public interface ReplyDao {
	
	/**
	 * 해당 글의 모든 댓글 목록을 조회한다.
	 * 
	 * @param searchInfo 검색에 사용될 조건 정보를 담고 있는 Map 객체
	 * @return 검색된 게시물 목록을 담고 있는 List 객체
	 */
	public List<Reply> selectReplyList();

	/**
	 * 해당 글의 모든 댓글 개수를 조회한다.
	 * 
	 * @return 검색된 모든 댓글의 개수
	 */
	public int selectReplyCount();

	/**
	 * 지정된 번호에 해당하는 댓글을 검색한다.
	 * @param num 검색하고자 하는 댓글의 번호
	 * @return
	 */
	public Reply selectReply(int replyNum);
	
	/**
	 * 인수로 주어진 번호에 해당하는 댓글이 있는지 확인한다.
	 * 
	 * @param num 존재여부를 확인하려는 댓글의 번호
	 * @return 해당하는 댓글이 존재하면 true, 존재하지 않으면 false
	 */
	public boolean replyNumExists(int replyNum);

	/**
	 * 인수로 주어진 Reply 객체의 정보로 새로운 댓글을 등록한다.
	 * 
	 * @param reply 등록할 댓글 정보를 담고 있는 Reply 객체
	 */
	public void insertReply(Reply reply);
	
	/**
	 * 인수로 주어진 Reply 객체의 정보로 기존 댓글을 수정한다.
	 * 
	 * @param reply 수정할 댓글 정보를 담고 있는 Reply 객체
	 */
	public void updateReply(Reply reply);

	/**
	 * 인수로 주어진 번호에 해당하는 댓글을 삭제한다.
	 * 
	 * @param num 삭제하려는 댓글의 번호
	 */
	public void deleteReply(int replyNum);

}
