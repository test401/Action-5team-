package action.business.service.board;

import java.util.Map;

import action.business.domain.board.QnABoard;
import action.business.service.DataNotFoundException;

public interface QnABoardService {
	
	/**
     * 특정 번호의 게시글의 내용을 찾는다.
     *
     * @param num 검색하고자 하는 게시글의 번호
     * @return 검색된 게시글 정보를 담고 있는 Board 객체
     * @throws DataNotFoundException 번호에 해당하는 게시글이 존재하지 않을 경우 발생
     */
	public QnABoard findBoard(int num) throws DataNotFoundException;
	
    /**
     * 조건에 맞는 모든 게시물 목록을 조회한다.
     * 
     * @param searchInfo 검색에 사용될 조건 정보를 담고 있는 Map 객체
     * @return 검색된 모든 게시물 정보를 담고 있는 Board 배열
     */
	public QnABoard[] getBoardList(Map<String, Object> searchInfo);

	/**
	 * 조건에 맞는 모든 게시글 수를 조회한다.
	 * 
	 * @param searchInfo 검색에 사용될 조건 정보를 담고 있는 Map 객체
	 * @return 검색된 모든 게시글의 개수
	 */
	public int getBoardCount(Map<String, Object> searchInfo);
	
    /**
     * 새로운 게시글을 등록한다.
     *
     * @param board 등록할 게시글 정보를 담고 있는 Board 객체
     */
	public void writeBoard(QnABoard board);
	
    /**
     * 기존 게시글을 수정한다.
     *
     * @param board 수정할 게시글 정보를 담고 있는 Board 객체
     * @throws DataNotFoundException 해당하는 게시글이 존재하지 않을 경우 발생 
     */
	public void updateBoard(QnABoard board) throws DataNotFoundException;	
	
    /**
     * 특정 번호의 게시글을 삭제한다.
     *
     * @param num 삭제하고자 하는 게시글의 번호
     * @throws DataNotFoundException 번호에 해당하는 게시글이 존재하지 않을 경우 발생
     */
	public void removeBoard(int num) throws DataNotFoundException;

}
