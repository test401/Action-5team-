package action.business.service.reply;

import action.business.domain.reply.AuctionBoardReply;
import action.business.service.DataNotFoundException;
import action.dataaccess.reply.AuctionBoardReplyDaoImpl;

public class AuctionBoardReplyServiceImpl implements AuctionBoardReplyService {
	
	private AuctionBoardReplyDao replyDaoAccess;

	public AuctionBoardReplyServiceImpl(){
		replyDaoAccess = new AuctionBoardReplyDaoImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyService#findBoard(int)
	 */
	@Override
	public AuctionBoardReply findBoard(int replyNum) throws DataNotFoundException {
		if(replyDaoAccess.isReplyExists(replyNum)){			
			return replyDaoAccess.selectReply(replyNum);
		} else {
			throw new DataNotFoundException("해당 댓글을 찾을 수 없습니다. ");
		}	
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyService#getBoardList(int)
	 */
	@Override
	public AuctionBoardReply[] getBoardList(int boardNum) {
		return replyDaoAccess.selectReplyList(boardNum).toArray(new AuctionBoardReply[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyService#getBoardCount(int)
	 */
	@Override
	public int getBoardCount(int boardNum) {
		return replyDaoAccess.selectReplyCount(boardNum);
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyService#writeReply(action.business.domain.reply.AuctionBoardReply)
	 */
	@Override
	public void writeReply(AuctionBoardReply reply) {
		replyDaoAccess.insertReply(reply);		
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyService#updateReply(action.business.domain.reply.AuctionBoardReply)
	 */
	@Override
	public void updateReply(AuctionBoardReply reply) throws DataNotFoundException {
		replyDaoAccess.updateReply(reply);		
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyService#writeReplyOnReply(action.business.domain.reply.AuctionBoardReply)
	 */
	@Override
	public void writeReplyOnReply(AuctionBoardReply reply) throws DataNotFoundException {
		if(replyDaoAccess.isReplyExists(reply.getReplyNum())){			
			replyDaoAccess.InsertReplyOnReply(reply);
		} else {
			throw new DataNotFoundException("원본 댓글을 찾을 수 없습니다. ");
		}		
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.AuctionBoardReplyService#removeReply(int)
	 */
	@Override
	public void removeReply(int replyNum) throws DataNotFoundException {
		replyDaoAccess.deleteReply(replyNum);
	}

}
