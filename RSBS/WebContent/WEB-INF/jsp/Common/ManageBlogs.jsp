<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>RSBS - Blogs</title>
<link rel="stylesheet" href="resources/assets/css/all-pages.css">
</head>
<body style="background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
	<div id="my_page_container" class="container main-container container-margin inside-page-padding min-page-height">
	<form method="post">
	<div class="row page-margin">
	<div class="col-md-12">
		    <div class="row form-group">
				<div class="col-md-12">
				<label class="celebrity_name">Blogs</label>
				</div>
			</div>
		<%-- <div id="recent-blog" style="background-color:white">
			<div class="row">
				<c:forEach var="tb" items="${getResentBlog}">
				<c:if test="${not empty tb.getImage_path()}">
				    <div class="col-md-3">
				    <a href="#" onclick="readMoreBlog('${tb.getBlog_id()}')">
						<img src="/PoliticianPhotoes/${tb.getImage_path()}" width="100%"></a>
					</div>
				</c:if>
					<div class="col-md-9 blog_title_div">
					<div class="col-md-12">
						<a href="#" onclick="readMoreBlog('${tb.getBlog_id()}')">
							<span class="celebrity_name">${tb.getBlog_title()}</span></a></div>
				
				<c:set var="more_title" value="${tb.getDescription()}"/>
							<%
									String more_title = ""; 
									String text=(String)pageContext.getAttribute("more_title");
									text=text.replace("?", "");
									if(text.length()>350)
									{
												text=text.substring(0, 350);
									}
									more_title = (String)pageContext.getAttribute("more_title");   //No exception.
									more_title=more_title.replace("\'", "\\'");
									more_title=more_title.replace("?", "");
							 %>
				<c:if test="${fn:length(more_title)>350}">
				      <div class="col-md-12 blog_desc_font"><%=text%>.....</div>
					 <div class="col-md-12"> <button type="button" class="btn-follow readmore-btn-size cursor_pointer" onclick="readMoreBlog('${tb.getBlog_id()}')">Read more</button></div>
				</c:if>
				<c:if test="${fn:length(more_title)<350}">
						<div class="col-md-12 blog_desc_font"><%=text%></div>
				</c:if>
					
					</div>
				</c:forEach>
		</div>
	</div> --%>
	<div id="blog-cards">
		<div class="row form-group">
		  <c:forEach var="col" begin="0" end="3">
		  <c:set var="temp" value="${col}"></c:set>
			<div class="col-md-3 news-part">
				<c:forEach var="ap" items="${all_blogs}" varStatus="st">
				<c:if test="${st.index==temp}">
					<!-- <div class="col-md-3 news-part"> -->
					
						<div class="contents bottom-margin blogDivPaddings">
								<c:if test="${not empty ap.getImage_path()}">
						<a href="#" onclick="readMoreBlog('${ap.getBlog_id()}')">
						    <img src="/PoliticianPhotoes${ap.getImage_path()}" width="100%">
							<!-- <img src="resources/assets/images/fb.png" class="icons fb" alt="FB Icon"> -->
							</a>
						</c:if>
						<div class="col-md-12 blog-cards-title">
					    <a href="#" onclick="readMoreBlog('${ap.getBlog_id()}')">
								<span class="blog_title">${ap.getBlog_title()}</span></a></div>
				
		 	<%-- <c:set var="more_title" value="${ap.getDescription()}"/>
							<%
									String more_title = ""; 
									String text=(String)pageContext.getAttribute("more_title");
									text=text.replace("?", "");
									if(text.length()>60)
									{
												text=text.substring(0, 60);
									}
									more_title = (String)pageContext.getAttribute("more_title");   //No exception.
									more_title=more_title.replace("\'", "\\'");
									more_title=more_title.replace("?", "");
							 %>
				<c:if test="${fn:length(more_title)>60}">
				      <div class="col-md-12 blog_desc_font1"><%=text%></div>
					  <div class="col-md-12"><a href="#" onclick="readMoreBlog('${ap.getBlog_id()}')" class="readmore_border text_decoration_style">Read more</a></div>
				</c:if>
				<c:if test="${fn:length(more_title)<60}">
						<div class="col-md-12 blog_desc_font1"><%=text%></div>
				</c:if>  --%>
				</div>
					 <!-- </div>  -->
					<c:set var="temp" value="${temp+4}"></c:set>
					</c:if>
				</c:forEach>
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