package action.util;

public class PageHandler {
	// 한 페이지에 보여줄 게시글 개수
	public static final int PAGE_LIST_SIZE = 5;
	// 페이지 선택 바에 보여줄 페이지 개수
	public static final int PAGE_GROUP_SIZE = 3;

	// 전체 게시글 개수로부터 전체 페이지 개수를 구한다.
	public static int getTotalPageCount(int totalBoardCount) {
		//return (int) Math.ceil(totalBoardCount / (float) PAGE_LIST_SIZE);
		int pageCount = totalBoardCount / PAGE_LIST_SIZE;
		if (totalBoardCount % PAGE_LIST_SIZE != 0) {
			pageCount++;
		}
		return pageCount;
	}

	// 특정 페이지의 페이지 선택 바에 표시될 시작 페이지 번호를 구한다.
	public static int getStartPageNumber(int pageNumber) {
		return (pageNumber - 1) / PAGE_GROUP_SIZE * PAGE_GROUP_SIZE + 1;
	}

	// 특정 페이지의 페이지 선택 바에 표시될 끝 페이지 번호를 구한다.
	public static int getEndPageNumber(int pageNumber, int totalBoardCount) {
		int totalPageCount = getTotalPageCount(totalBoardCount);
		int startPageNumber = getStartPageNumber(pageNumber);
		
		int endPageNumber = startPageNumber + (PAGE_GROUP_SIZE - 1);
		if (endPageNumber > totalPageCount) {
			endPageNumber = totalPageCount;
		}
		return endPageNumber;
	}

	// 특정 페이지의 게시글 목록에서 처음 보여질 게시글의 행 번호를 구한다.
	public static int getStartRow(int pageNumber) {
		return (pageNumber - 1) * PAGE_LIST_SIZE + 1;
	}

	// 특정 페이지의 게시글 목록에서 마지막에 보여질 게시글의 행 번호를 구한다.
	public static int getEndRow(int pageNumber) {
		return pageNumber * PAGE_LIST_SIZE;
	}
}
