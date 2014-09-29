<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../action.css">
<title>Action</title>
</head>
<body>
     <div class="tableContainer">
        <div class="tableRow">		
            <c:import url="banner.jsp" />
        </div>
        
        <div class="tableRow">
            <c:import url="side-bar.jsp" />
            <c:import url="mainContent.jsp" />
        </div>
        
        <div class="tableRow">

            <div class="tableCell">
                <c:import url="foter.jsp" />
            </div>
        </div>
    </div>
</body>
</html>
