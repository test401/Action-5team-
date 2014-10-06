package action.business.service.reply;

import action.business.domain.reply.AuctionBoardReply;
import action.business.service.DataNotFoundException;

public interface AuctionBoardReplyService {	
	
    /**
     * 특정 번호의 댓글을 찾는다.
     *
     * @param replyNum 검색하고자 하는 댓글의 번호
     * @return 검색된 댓글 정보를 담고 있는 AuctionBoardReply 객체
     * @throws DataNotFoundException 번호에 해당하는 댓글이 존재하지 않을 경우 발생
     */
	public AuctionBoardReply findBoard(int replyNum) throws DataNotFoundException;
	
	
	
    /**
     * 한 게시물 내의 모든 댓글 목록을 조회한다.
     * 
     * @param boardNum 댓글 목록을 포함하고있는 게시글의 번호
     * @return 검색된 모든 댓글 정보를 담고 있는 AuctionBoardReply 배열
     */
	public AuctionBoardReply[] getBoardList(int boardNum);

	
	
	/**
	 * 한 게시물 내의 모든 댓글 수를 조회한다.
	 * 
	 * @param boardNum 댓글 목록을 포함하고있는 게시글의 번호
	 * @return 검색된 모든 댓글의 개수
	 */
	public int getBoardCount(int boardNum);
	
	
	
    /**
     * 새로운 댓글을 등록한다.
     *
     * @param reply 등록할 댓글 정보를 담고 있는 AuctionBoardReply 객체
     */
	public void writeReply(AuctionBoardReply reply);
	
	
	
    /**
     * 기존 댓글을 수정한다.
     *
     * @param reply 수정할 댓글 정보를 담고 있는 AuctionBoardReply 객체
     * @throws DataNotFoundException 해당하는 댓글이 존재하지 않을 경우 발생 
     */
	public void updateReply(AuctionBoardReply reply) throws DataNotFoundException;
	
	
	
	/**
	 * 기존 댓글에 추가로 댓글을 등록한다
	 * 
	 * @param reply 등록할 댓글 정보를 담고 있는 AuctionBoardReply 객체
	 * @throws DataNotFoundException 해당하는 댓글이 존재하지 않을 시
	 */
	public void writeReplyOnReply(AuctionBoardReply reply) throws DataNotFoundException;	

	
	
    /**
     * 특정 번호의 댓글을 삭제한다.
     *
     * @param replyNum 삭제하고자 하는 댓글의 번호
     * @throws DataNotFoundException 번호에 해당하는 댓글이 존재하지 않을 경우 발생
     */
	public void removeReply(int replyNum) throws DataNotFoundException;

}
