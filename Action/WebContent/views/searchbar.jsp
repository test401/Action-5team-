<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="buttonbar" >
	<form name="searchForm" action="list?pageNumber=${currentPageNumber}">
		<select name="searchType" >
			<option value="all" <c:if test="${empty param.searchType || param.searchType=='all'}">
			selected="selected"
			</c:if>	>전체검색</option>
			<option value="title"<c:if test="${param.searchType=='title'}">
			selected="selected"
			</c:if>>제목</option>
			<option value="writer" <c:if test="${param.searchType=='writer'}">
			selected="selected"
			</c:if>>글쓴이</option>
			<option value="contents"<c:if test="${param.searchType=='contents'}">
			selected="selected"
			</c:if>>내용</option>
		</select> 
		<input id="searchinput" type="text" name="searchText" size="20" value="${param.searchText}" onfocus="this.select();">
		<input type="button" value="검색" onclick="searchCheck(this.form);"> 
	</form>
</div>