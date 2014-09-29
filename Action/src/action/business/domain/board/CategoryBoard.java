package action.business.domain.board;


/**2. 카테고리테이블 테이블
 * 카테고리ID, 카테고리 이름, 제목(게시판 공통), 작성자(게시판 공통)*/
public class CategoryBoard extends Board {
	
	private int categoryID;
	private String categoryName;
	
	public CategoryBoard(int categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
	
	
}
