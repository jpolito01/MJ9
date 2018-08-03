<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Celebrity - Error Page</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div id="my_page_container" class="page-padding-top my_container_height">
<div align="center">
<img src="resources/assets/images/404-error.png" style="padding: 80px;">
</div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	
});
</script>
</body>
</html>