package action.business.service.reply;

import action.business.domain.reply.QnABoardReply;
import action.business.service.DataNotFoundException;
import action.dataaccess.reply.QnABoardReplyDaoImpl;

public class QnABoardReplyServiceImpl implements QnABoardReplyService{
	
	private QnABoardReplyDao replyDaoAccess;
	
	public QnABoardReplyServiceImpl(){
		replyDaoAccess = new QnABoardReplyDaoImpl();
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.QnABoardReplyService#findBoard(int)
	 */
	@Override
	public QnABoardReply findBoard(int replyNum) throws DataNotFoundException {
		if(replyDaoAccess.isReplyExists(replyNum)){			
			return replyDaoAccess.selectReply(replyNum);
		} else {
			throw new DataNotFoundException("해당 댓글을 찾을 수 없습니다. ");
		}	
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.QnABoardReplyService#getBoardList(int)
	 */
	@Override
	public QnABoardReply[] getBoardList(int boardNum) {
		return replyDaoAccess.selectReplyList(boardNum).toArray(new QnABoardReply[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.QnABoardReplyService#getBoardCount(int)
	 */
	@Override
	public int getBoardCount(int boardNum) {
		return replyDaoAccess.selectReplyCount(boardNum);
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.QnABoardReplyService#writeReply(action.business.domain.reply.QnABoardReply)
	 */
	@Override
	public void writeReply(QnABoardReply reply) {
		replyDaoAccess.insertReply(reply);		
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.QnABoardReplyService#updateReply(action.business.domain.reply.QnABoardReply)
	 */
	@Override
	public void updateReply(QnABoardReply reply) throws DataNotFoundException {
		replyDaoAccess.updateReply(reply);			
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.QnABoardReplyService#writeReplyOnReply(action.business.domain.reply.QnABoardReply)
	 */
	@Override
	public void writeReplyOnReply(QnABoardReply reply) throws DataNotFoundException {
		if(replyDaoAccess.isReplyExists(reply.getReplyNum())){			
			replyDaoAccess.InsertReplyOnReply(reply);
		} else {
			throw new DataNotFoundException("원본 댓글을 찾을 수 없습니다. ");
		}	
	}

	/*
	 * (non-Javadoc)
	 * @see action.business.service.reply.QnABoardReplyService#removeReply(int)
	 */
	@Override
	public void removeReply(int replyNum) throws DataNotFoundException {
		replyDaoAccess.deleteReply(replyNum);				
	}

}
