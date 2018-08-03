<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
  <title>Celebrity Profile</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-type" content="text/html;charset=UTF-8">
 <link rel="stylesheet" href="resources/assets/css/style.css">
</head>
<body style="	font-family: Roboto;">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div id="my_page_container" class="container page-padding-top my_container_height" style="background-color: #fff;">
		<div class="col-sm-12" id="blogDetailsMainDivs">
				  				<div class="col-sm-9">
				  						  		<c:forEach var="mb" items="${blog_details}" varStatus="i">
				  						  			<div class="row card-news">        
					            		<div class="inner-box" id="blogDetailsBox">
					            		 <p class="blogDetailstitle">${mb.getBlog_title()} </p>
					            		 <p><fmt:formatDate type = "date" pattern="EEEE, d MMMM y"  value = "${mb.getCreate_date()}" /></p>
					            		 		<c:if test="${not empty mb.getBlog_image()}">
						            			<img style="width: 65%" src="${mb.getBlog_image()}" alt="User Avatar">
						            			</c:if>
									          		     <p class="movieBlogDescr">
													${mb.getMovie_blogs()}
									          </p>
									          </div>
									          </div>
					          				  		</c:forEach>
		</div>
						  				<div class="col-sm-3">
						  				  	<div class="col-sm-12 celebrityoftheday movieBlogsHeading">
				  			<div align="center"><span>More posts</span></div> 
				  		</div>
				  		<c:forEach var="mb" items="${more_blogs}" varStatus="ind">
				  						  			<div class="row card-news">        
					            		<div class="inner-box  allBlogs-inner-box">
					            							            			<c:if test="${not empty mb.getBlog_image()}">
						            		<a href="#"  onclick="getDetailedBlog('${mb.getBlog_id()}')"> 
						            			<img style="width: 100%" src="${mb.getBlog_image()}" alt="User Avatar">
						            		</a>
						            		</c:if>
								            <div class="box-footer">
									          <a href="#"  onclick="getDetailedBlog('${mb.getBlog_id()}')">
									          		 <span class="newstitle">${mb.getBlog_title()} </span>
									          </a>
									          <p class="movieBlogDescr">
									          	<c:if test="${fn:length(mb.getMovie_blogs())>=120}">
														<c:set var="title" value="${fn:substring(mb.getMovie_blogs(), 0,120)}..."></c:set>
														${title}
														<p><a href="#"  onclick="getDetailedBlog('${mb.getBlog_id()}')" class="readMoreAllBlogs">Read more</a></p>
													</c:if>
													<c:if test="${fn:length(mb.getMovie_blogs())<120}">
													${mb.getMovie_blogs()}
													</c:if>
									          </p>
									          </div>
									          </div>
									          </div>
			</c:forEach>
		</div>
       </div>
	</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
 
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	$("#blogs-tab").addClass("active");
});

</script>
</body>
</html>