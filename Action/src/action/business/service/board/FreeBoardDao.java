package action.business.service.board;

import java.util.List;
import java.util.Map;

import action.business.domain.board.FreeBoard;

public interface FreeBoardDao {
	
	/**
	 * 조건에 맞는 모든 게시물 목록을 조회한다.
	 * 
	 * @param searchInfo 검색에 사용될 조건 정보를 담고 있는 Map 객체
	 * @return 검색된 게시물 목록을 담고 있는 List 객체
	 */
	public List<FreeBoard> selectBoardList(Map<String, Object> searchInfo);

	/**
	 * 조건에 맞는 모든 게시글 개수를 조회한다.
	 * 
	 * @param searchInfo 검색에 사용될 조건 정보를 담고 있는 Map 객체
	 * @return 검색된 모든 게시글의 개수
	 */
	public int selectBoardCount(Map<String, Object> searchInfo);

	/**
	 * 지정된 번호에 해당하는 게시글을 검색한다.
	 * @param num 검색하고자 하는 게시글의 번호
	 * @return
	 */
	public FreeBoard selectBoard(int num);
	
	/**
	 * 인수로 주어진 번호에 해당하는 게시글이 있는지 확인한다.
	 * 
	 * @param num 존재여부를 확인하려는 게시글의 번호
	 * @return 해당하는 게시글이 존재하면 true, 존재하지 않으면 false
	 */
	public boolean boardNumExists(int num);

	/**
	 * 인수로 주어진 Board 객체의 정보로 새로운 게시글을 등록한다.
	 * 
	 * @param board 등록할 게시글 정보를 담고 있는 Board 객체
	 */
	public void insertBoard(FreeBoard board);	

	/**
	 * 인수로 주어진 Board 객체의 정보로 기존 게시글을 수정한다.
	 * 
	 * @param board 수정할 게시글 정보를 담고 있는 Board 객체
	 */
	public void updateBoard(FreeBoard board);

	/**
	 * 인수로 주어진 번호에 해당하는 게시글을 삭제한다.
	 * 
	 * @param num 삭제하려는 게시글의 번호
	 */
	public void deleteBoard(int num);

}
