<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
  <title>Home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="resources/assets/css/style.css">
    
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div id="my_page_container" class="container page-padding-top my_container_height" style="background-color: #f2f2f2;">
	<div class="col-sm-12" style="padding: 2%;">
			<div class="col-sm-12">
				<label class="title">User Searched Term :${search_key}</label>
			</div>
		<c:forEach var="col" begin="0" end="2">
			<c:set var="temp" value="${col}"></c:set>
			<div class="col-sm-4">
				<c:forEach var="cn" items="${user_searches_news}" varStatus="ind">
					<c:if test="${ind.index==temp}">
						<div class="col-sm-12 search-card">
							<div class="col-sm-12">
								<div class="publication_date"> ${cn.getPublication_date()} </div>
								<a class="news-link" href="${cn.getNews_description_url()}" target="_BLANK">${cn.getNews_title()} </a> 
							</div>
							<c:if test="${not empty cn.getImage_url()}">
								<div class="col-sm-12" style="margin-top: 12px;margin-bottom: 8px;">
									<div align="center"><img src="${cn.getImage_url()}" width="100%"></div>
								</div>
							</c:if>
						</div>
						<c:set var="temp" value="${temp+3}"></c:set>
					</c:if>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
	<%-- <c:forEach var="cn" items="${user_searches_news}">
		<label>${cn.getNews_title()} </label> <br />${cn.getNews_description()}<br />
	</c:forEach> --%>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script type="text/javascript">
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
</script>
</body>
</html>