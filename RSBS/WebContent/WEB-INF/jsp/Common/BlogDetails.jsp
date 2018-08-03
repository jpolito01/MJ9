<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-119766288-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-119766288-1');
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RSBS - Blog Details</title>
<link rel="stylesheet" href="resources/assets/css/all-pages.css">
<style>
#my_page_container a:visited,#my_page_container a:link{
text-decoration:none;
color:#0275d8;
}
</style>
</head>
<body style="background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
<div id="my_page_container" class="container main-container container-margin inside-page-padding min-page-height">
<form method="post">
	<div class="row page-margin">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-9 paddingRight">
				<c:forEach var="a" items="${blog_details}">
					<div class="row">
						<div class="col-md-12"><span class="celebrity_name">${a.getBlog_title()}</span></div>
					</div>
					<div class="row">
						<div class="col-md-12">
						
						<span class="blog_date_font"><fmt:formatDate type = "date" value = "${a.getBlog_date()}" pattern="EEEE, d MMMM y" /></span>
						</div>
					</div>
					<div class="row news-part">
					<c:if test="${not empty a.getImage_path()}">
						<div class="col-md-8">
						<img src="/PoliticianPhotoes${a.getImage_path()}" width="75%"></div>
						</c:if>
					</div>
					<div class="row">
						<div class="col-md-12">
							<span class="blog_desc_font">${a.getDescription()}</span>
						</div>
					</div>
				</c:forEach>
				</div>
				
				<div class="col-md-3">
				<div class="row form-group">
				<div class="col-md-12 title-bar nav-link" align="center"><span>More Posts</span></div>
				</div>
				<c:forEach var="ap" items="${getAllBlogs}">
					<div class="row news-part contents blogDivPaddings">
					<div class="col-md-12">
					<c:if test="${not empty ap.getImage_path()}">
						<a href="#" onclick="readMoreBlog('${ap.getBlog_id()}')" class="image-margin">
							<img src="/PoliticianPhotoes${ap.getImage_path()}" width="100%"></a>
					</c:if>
						<!-- <div> -->
						<div class="col-md-12 blog-cards-title">
							<a href="#" onclick="readMoreBlog('${ap.getBlog_id()}')" class="image-margin">
								<span class="blog_title">${ap.getBlog_title()}</span></a></div>
				
			<%-- 	<c:set var="more_title" value="${ap.getDescription()}"/>
							<%
									String more_title = ""; 
									String text=(String)pageContext.getAttribute("more_title");
									text=text.replace("?", "");
									if(text.length()>80)
									{
												text=text.substring(0, 80);
									}
									more_title = (String)pageContext.getAttribute("more_title");   //No exception.
									more_title=more_title.replace("\'", "\\'");
									more_title=more_title.replace("?", "");
							 %>
				<c:if test="${fn:length(more_title)>80}">
				      <div class="col-md-12 removePaddings"><span class="blog_desc_font1"><%=text%></span></div>
					  <div class="col-md-12 removePaddings"><a href="#" onclick="readMoreBlog('${ap.getBlog_id()}')" class="readmore_border text_decoration_style">Read more</a></div>
				</c:if>
				<c:if test="${fn:length(more_title)<80}">
						<div class="col-md-12 removePaddings"><span class="blog_desc_font1"><%=text%></span></div>
				</c:if> --%>
				 </div> 
					</div>
				</c:forEach>
				</div>
			</div>
		</div>
	</div>
	</form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script src="resources/assets/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#blogs").addClass('active-nav-link');
	var max_height=$("#my_header").height()+$("#my_footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
});
</script>
</body>
</html>