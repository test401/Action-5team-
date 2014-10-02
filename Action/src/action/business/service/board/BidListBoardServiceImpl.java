package action.business.service.board;

import java.util.Map;

import action.business.domain.board.BidListBoard;
import action.business.service.DataNotFoundException;
import action.dataaccess.board.BidListBoardDaoImpl;

public class BidListBoardServiceImpl implements BidListBoardService {
	
	private BidListBoardDao boardDaoAccess;
	
	public BidListBoardServiceImpl(){
		boardDaoAccess = new BidListBoardDaoImpl();
	}
	@Override
	public BidListBoard findBoard(int num) throws DataNotFoundException {
		if(boardDaoAccess.boardNumExists(num)){			
			return boardDaoAccess.selectBoard(num);
		} else {
			throw new DataNotFoundException("해당 게시글을 찾을 수 없습니다. ");
		}
	}

	@Override
	public BidListBoard[] getBoardList(Map<String, Object> searchInfo) {
		return boardDaoAccess.selectBoardList(searchInfo).toArray(new BidListBoard[0]);
	}

	@Override
	public int getBoardCount(Map<String, Object> searchInfo) {
		return boardDaoAccess.selectBoardCount(searchInfo);
	}

	@Override
	public void writeBoard(BidListBoard board) {
		if(boardDaoAccess.boardNumExists(board)){
			boardDaoAccess.updatePrice(board);
		} else {
			boardDaoAccess.insertBoard(board);
		}		
	}

	@Override
	public void removeBoard(int num) throws DataNotFoundException {
		boardDaoAccess.deleteBoard(num);
		
	}

}
