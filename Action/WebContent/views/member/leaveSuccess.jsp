<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/Action/action.css">
<script src="/Action/js/member.js"></script>
<title>Action Register Success</title>
</head>
<body>
     <div class="tableContainer">
        <div class="tableRow">
            <c:import url="/views/banner.jsp"/>
        </div>
        <div class="tableRow">
            <c:import url="/views/side-bar.jsp" />
            <div class="views/main.jsp">
	            <form action="/Action/views/main.jsp" method="POST">
					<p>
						그동안 이용해 주셔서 감사합니다.
					</p>
					<br>
					<input type="submit" value="홈으로" >
	            </form>
        	</div>
        </div>
        
        <div class="tableRow">

            <div class="tableCell">
               <c:import url="/views/foter.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>
	