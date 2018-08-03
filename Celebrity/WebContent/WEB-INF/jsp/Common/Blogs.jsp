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
<body style="	font-family: Roboto;">
<!--<iframe src="//www.facebook.com/plugins/likebox.php?
href=http%3A%2F%2Fww.facebook.com%2FDfgdfgdgf&amp;width=490&amp;colorscheme=light&amp;show_faces=true&amp;height=435" scrolling="yes" frameborder="0" style="border:none; overflow:hidden; width=490px; height=300px; background:white; float:left;" allowTransparency="true"></iframe>  -->
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div id="my_page_container" class="container left-right-zero page-padding-top my_container_height" style="background-color: white; width: 100%;">
		<div class="col-sm-12" id="allBlogsMainDivs">
					<div class="col-sm-12">
			<div class="col-sm-12">
				<label class="title">Blogs</label>
			</div> 
			</div>
		<div class="col-sm-12">
	<%-- 	<div class="col-sm-12 featuredBlogDiv">
			<div class="row card-news">        
					            		<div class="inner-box  allBlogs-featured-inner-box ">
					            				<div class="row">
					            				<c:if test="${not empty all_blogs[0].getBlog_image()}">
					            				<div class="col-sm-5">
					            					<a href="#"  onclick="getDetailedBlog('${all_blogs[0].getBlog_id()}')"> 
						            			<img style="width: 100%" src="${all_blogs[0].getBlog_image()}" alt="User Avatar">
						            		</a>
					            		</div>
					            		<c:set var="columnVal" value="col-sm-7"></c:set>
					            								            		</c:if>
					            		<c:if test="${empty all_blogs[0].getBlog_image()}">
					            							            		<c:set var="columnVal" value="col-sm-12"></c:set>
					            		</c:if>
					            				<div class="${columnVal}" id="featuredBlogRight">
									          <a href="#"  onclick="getDetailedBlog('${all_blogs[0].getBlog_id()}')" class="featuredBlogTitle">
									          		${all_blogs[0].getBlog_title()} 
									          </a>
									          <p class="movieBlogDescr">
									          	<c:if test="${fn:length(all_blogs[0].getMovie_blogs())>=300}">
														<c:set var="title" value="${fn:substring(all_blogs[0].getMovie_blogs(), 0,300)}..."></c:set>
														${title}
														<p>
														<a href="#"  onclick="getDetailedBlog('${all_blogs[0].getBlog_id()}')">Read more</a>
														<input class="btn-follow" type="button" value="Read more" onclick="getDetailedBlog('${all_blogs[0].getBlog_id()}')">
														<p>
													</c:if>
													<c:if test="${fn:length(all_blogs[0].getMovie_blogs())<300}">
													${all_blogs[0].getMovie_blogs()}
													</c:if>
									          </p>
									          </div>
									          </div>
									          </div>
									          </div>
		</div> --%>
		<c:forEach var="col" begin="0" end="2">
			<c:set var="temp" value="${col}"></c:set>
				<div class="col-sm-4">
							<c:forEach var="mb" items="${all_blogs}" varStatus="ind">
									<c:if test="${ind.index==temp}">
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
												<c:set var="temp" value="${temp+3}"></c:set>
					</c:if>
			</c:forEach>
							</div>
					</c:forEach>
			
		</div>
	</div>
			</div>
	
	<!-- <iframe src="//www.facebook.com/plugins/likebox.php?
href=http%3A%2F%2Fww.facebook.com%2FAngelina.Jolie&amp;width=490&amp;colorscheme=light&amp;show_faces=true&amp;height=435" scrolling="yes" frameborder="0" style="border:none; overflow:hidden; width=490px; height=300px; background:white; float:left;" allowTransparency="true"></iframe>
	 -->
	 
	 <%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script>
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	
	$("#blogs-tab").addClass("active");
	$("#"+'${latter}').addClass("active-letter");
});
function followCelebrity(entity_id,entity_type_id)
{
	 if('${user_id}'=="")
	 { 
	 	$("#modal_login").modal('show');
	 	return false;
	 }
else
	 {
		var tab_id="musician";
		fun_FinalFollow(entity_id,entity_type_id,tab_id);
	 }
}
</script>
</body>
</html>