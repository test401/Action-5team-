package action.business.service.board;

import java.util.Map;

import action.business.domain.board.AuctionListBoard;
import action.business.service.DataNotFoundException;
import action.dataaccess.board.AuctionListBoardDaoImpl;

public class AuctionListBoardServiceImpl {
	
	private AuctionListBoardDaoImpl boardDaoAccess;

	public AuctionListBoardServiceImpl(){
		boardDaoAccess = new AuctionListBoardDaoImpl();
	}	

	@Override
	public AuctionListBoard findBoard(int num) throws DataNotFoundException {
		if(boardDaoAccess.boardNumExists(num)){			
			return boardDaoAccess.selectBoard(num);
		} else {
			throw new DataNotFoundException("해당 게시글을 찾을 수 없습니다. ");
		}
	}

	@Override
	public AuctionListBoard[] getBoardList(Map<String, Object> searchInfo) {
		return boardDaoAccess.selectBoardList(searchInfo).toArray(new AuctionListBoard[0]);
	}

	@Override
	public int getBoardCount(Map<String, Object> searchInfo) {
		return boardDaoAccess.selectBoardCount(searchInfo);
	}

	@Override
	public void writeBoard(AuctionListBoard board) {
		boardDaoAccess.insertBoard(board);
		
	}

	@Override
	public void updateBoard(AuctionListBoard board) throws DataNotFoundException {
		boardDaoAccess.updateBoard(board); 
		
	}

	@Override
	public void removeBoard(int num) throws DataNotFoundException {
		boardDaoAccess.deleteBoard(num);
		
	}

}
