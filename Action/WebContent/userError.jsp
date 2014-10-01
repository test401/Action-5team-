<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오류 발생</title>
    <link rel="stylesheet" href="css/dukeshop.css">	
</head>
<body>
    <div class="tableContainer">
        <div class="tableRow">
            <!-- START of main content-->
            <div class="main">
                <h4 id="error">입력 정보 오류</h4>
                <p>아래와 같은 문제가 있습니다.</p>
                <ul>
<%--                 <%
                // request scope 속성에서 에러메시지를 구해 출력한다.
                @SuppressWarnings("unchecked")
                List<String> errorMsgs = (List<String>) request.getAttribute("errorMsgs");
                for (String message : errorMsgs) {
                %> --%>
                <c:forEach items="${requestScope.errorMsgs}" var="message">
                    <li>${message}</li>
<%--                 <%
                } // end of for loop
                %> --%>
                </c:forEach>
                </ul>
                <p>다시 시도해 주세요.</p>
            </div>                
            <!-- END of main content-->
        </div>
    </div>
</body>
</html>  
