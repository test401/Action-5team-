package action.business.service.board;

import java.util.Map;

import action.business.service.board.AuctionBoardDao;
import action.dataaccess.board.AuctionBoardDaoImpl;
import action.business.domain.board.AuctionBoard;
import action.business.service.DataNotFoundException;

public class AuctionBoardServiceImpl implements AuctionBoardService {
	
	private AuctionBoardDao boardDaoAccess;

	public AuctionBoardServiceImpl(){
		boardDaoAccess = new AuctionBoardDaoImpl();
	}	

	@Override
	public AuctionBoard findBoard(int num) throws DataNotFoundException {
		if(boardDaoAccess.boardNumExists(num)){			
			return boardDaoAccess.selectBoard(num);
		} else {
			throw new DataNotFoundException("해당 게시글을 찾을 수 없습니다. ");
		}
	}

	@Override
	public AuctionBoard[] getBoardList(Map<String, Object> searchInfo) {
		return boardDaoAccess.selectBoardList(searchInfo).toArray(new AuctionBoard[0]);
	}

	@Override
	public int getBoardCount(Map<String, Object> searchInfo) {
		return boardDaoAccess.selectBoardCount(searchInfo);
	}

	@Override
	public void writeBoard(AuctionBoard board) {
		boardDaoAccess.insertBoard(board);
		
	}

	@Override
	public void updateBoard(AuctionBoard board) throws DataNotFoundException {
		if(boardDaoAccess.boardNumExists(board.getBoardNum())){			
			boardDaoAccess.updateBoard(board);
		} else {
			throw new DataNotFoundException("해당 게시글을 찾을 수 없습니다. ");
		}
		
		
	}
	
	@Override
	public boolean updatePrice(AuctionBoard board) throws DataNotFoundException {
		if(boardDaoAccess.boardNumExists(board.getBoardNum())){
			if(boardDaoAccess.isPrice(board)){
				boardDaoAccess.updatePrice(board);	
				return true;
			}else{
				return false;
			}
		} else {
			throw new DataNotFoundException("해당 게시글을 찾을 수 없습니다.");
		}
		
		
	}

	@Override
	public void removeBoard(int num) throws DataNotFoundException {
		
		if(boardDaoAccess.boardNumExists(num)){			
			boardDaoAccess.deleteBoard(num);
		} else {
			throw new DataNotFoundException("해당 게시글을 찾을 수 없습니다. ");
		}
		
		
	}

	

}
