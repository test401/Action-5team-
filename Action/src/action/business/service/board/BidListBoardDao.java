package action.business.service.board;

import java.util.List;
import java.util.Map;

import action.business.domain.board.BidListBoard;

public interface BidListBoardDao {
	
	/**
	 * 조건에 맞는 모든 게시물 목록을 조회한다.
	 * 
	 * @param searchInfo 검색에 사용될 조건 정보를 담고 있는 Map 객체
	 * @return 검색된 게시물 목록을 담고 있는 List 객체
	 */
	public List<BidListBoard> selectBoardList(Map<String, Object> searchInfo);

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
	public BidListBoard selectBoard(int num);	
	
	/**
	 * 인수로 주어진 번호에 해당하는 게시글이 있는지 확인한다.
	 * 
	 * @param num 존재여부를 확인하려는 게시글의 번호
	 * @return 해당하는 게시글이 존재하면 true, 존재하지 않으면 false
	 */
	public boolean boardNumExists(int num);

	/**
	 * 입찰가 갱신을 위하여 사용.
	 * 주어진 번호의 게시글에 입력된 사용자가 입찰을 했는지 여부를 알려줌
	 * 
	 * @param board 존재여부를 확인하려는 입찰목록 정보	 *
	 * @return 해당하는 게시글이 존재하면 true, 존재하지 않으면 false
	 */
	public boolean boardNumExists(BidListBoard board);

	/**
	 * 인수로 주어진 Board 객체의 정보로 새로운 게시글을 등록한다.
	 * 
	 * @param board 등록할 게시글 정보를 담고 있는 Board 객체
	 */
	public void insertBoard(BidListBoard board);
	
	/**
	 * 인수로 주어진 Board 객체의 정보로 기존 입찰가격을 갱신한다.
	 * 
	 * @param board 수정할 입찰가격 정보를 담고 있는 Board 객체
	 */
	public void updatePrice(BidListBoard board);

	/**
	 * 인수로 주어진 번호에 해당하는 게시글을 삭제한다.
	 * 
	 * @param num 삭제하려는 게시글의 번호
	 */
	public void deleteBoard(int num);

}
