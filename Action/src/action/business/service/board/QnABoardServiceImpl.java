package action.business.service.board;

import java.util.Map;

import action.business.service.board.QnABoardDao;
import action.business.domain.board.QnABoard;
import action.business.service.DataNotFoundException;
import action.dataaccess.board.QnABoardDaoImpl;

public class QnABoardServiceImpl implements QnABoardService {

	private QnABoardDao boardDaoAccess;

	public QnABoardServiceImpl(){
		boardDaoAccess = new QnABoardDaoImpl();
	}

	@Override
	public QnABoard findBoard(int num) throws DataNotFoundException {
		if(boardDaoAccess.boardNumExists(num)){			
			return boardDaoAccess.selectBoard(num);
		} else {
			throw new DataNotFoundException("해당 게시글을 찾을 수 없습니다. ");
		}		
	}

	/**
	 * 조건에 맞는 모든 게시물 목록을 조회한다.
	 * 
	 * @return 검색된 모든 게시물 정보를 담고 있는 Board 배열
	 */
	@Override
	public QnABoard[] getBoardList(Map<String, Object> searchInfo) {		
		return boardDaoAccess.selectBoardList(searchInfo).toArray(new QnABoard[0]);
	}

	/**
	 * 조건에 맞는 모든 게시글 수를 조회한다.
	 * 
	 * @return 검색된 모든 게시글의 개수
	 */
	@Override
	public int getBoardCount(Map<String, Object> searchInfo) {
		return boardDaoAccess.selectBoardCount(searchInfo);
	}

	/**
	 * 새로운 게시글을 등록한다.
	 *
	 * @param board 등록할 게시글 정보를 담고 있는 Board 객체
	 */
	@Override
	public void writeBoard(QnABoard board) {
		boardDaoAccess.insertBoard(board);
	}

	/**
	 * 기존 게시글을 수정한다.
	 *
	 * @param board 수정할 게시글 정보를 담고 있는 Board 객체
	 * @throws DataNotFoundException 해당하는 게시글이 존재하지 않을 경우 발생 
	 */
	@Override
	public void updateBoard(QnABoard board) throws DataNotFoundException {
		boardDaoAccess.updateBoard(board); 
	}

	/**
	 * 특정 번호의 게시글을 삭제한다.
	 *
	 * @param num 삭제하고자 하는 게시글의 번호
	 * @throws DataNotFoundException 번호에 해당하는 게시글이 존재하지 않을 경우 발생
	 */
	@Override
	public void removeBoard(int num) throws DataNotFoundException {
		boardDaoAccess.deleteBoard(num);
	}	

}
