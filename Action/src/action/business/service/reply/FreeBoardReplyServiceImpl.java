package action.business.service.reply;

import action.business.domain.reply.FreeBoardReply;
import action.business.service.DataNotFoundException;
import action.dataaccess.reply.FreeBoardReplyDaoImpl;

public class FreeBoardReplyServiceImpl implements FreeBoardReplyService {
	
	private FreeBoardReplyDao replyDaoAccess;
	
	public FreeBoardReplyServiceImpl(){
		replyDaoAccess = new FreeBoardReplyDaoImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyService#findBoard(int)
	 */
	@Override
	public FreeBoardReply findBoard(int replyNum) throws DataNotFoundException {
		if(replyDaoAccess.isReplyExists(replyNum)){			
			return replyDaoAccess.selectReply(replyNum);
		} else {
			throw new DataNotFoundException("해당 댓글을 찾을 수 없습니다. ");
		}	
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyService#getBoardList(int)
	 */
	@Override
	public FreeBoardReply[] getBoardList(int boardNum) {
		return replyDaoAccess.selectReplyList(boardNum).toArray(new FreeBoardReply[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyService#getBoardCount(int)
	 */
	@Override
	public int getBoardCount(int boardNum) {
		return replyDaoAccess.selectReplyCount(boardNum);
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyService#writeReply(action.business.domain.reply.FreeBoardReply)
	 */
	@Override
	public void writeReply(FreeBoardReply reply) {
		replyDaoAccess.insertReply(reply);	
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyService#updateReply(action.business.domain.reply.FreeBoardReply)
	 */
	@Override
	public void updateReply(FreeBoardReply reply) throws DataNotFoundException {
		replyDaoAccess.updateReply(reply);		
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyService#writeReplyOnReply(action.business.domain.reply.FreeBoardReply)
	 */
	@Override
	public void writeReplyOnReply(FreeBoardReply reply) throws DataNotFoundException {
		if(replyDaoAccess.isReplyExists(reply.getReplyNum())){			
			replyDaoAccess.InsertReplyOnReply(reply);
		} else {
			throw new DataNotFoundException("원본 댓글을 찾을 수 없습니다. ");
		}		
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.FreeBoardReplyService#removeReply(int)
	 */
	@Override
	public void removeReply(int replyNum) throws DataNotFoundException {
		replyDaoAccess.deleteReply(replyNum);		
	}

}
