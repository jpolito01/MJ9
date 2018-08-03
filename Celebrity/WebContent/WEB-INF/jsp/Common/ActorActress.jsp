<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <title>Home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/assets/css/Acotrs.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div id="my_page_container" class="container left-right-zero page-padding-top my_container_height" style="background-color: white; width: 100%;">
		<div class="col-sm-12 padding-one">
			<div class="col-sm-12">
				<label class="title">Actors/Models</label>
			</div>
			<div class="col-sm-12">
				<c:forEach var="i" begin="65" end="90">
				    <a style="text-decoration: none;" class="padding-one" href="actors-models?q=<%=Character.toChars((Integer)pageContext.getAttribute("i"))%>">
				    	<span id="<%=Character.toChars((Integer)pageContext.getAttribute("i"))%>" class="span_letters"><%=Character.toChars((Integer)pageContext.getAttribute("i"))%></span> 
				    </a>
				</c:forEach>
			</div>
		</div>
		<div class="col-sm-12 celebrityContainers">
			<c:forEach var="ci" items="${celebrity_info}">
				<div class="col-sm-4 boxesForIpad">
					<div class="col-sm-12 profile-card">
						<div class="col-sm-5 celeb_ing">
							<a href="#" onclick="ShowCelebrity_profile('${ci.getCelebrity_id()}')"> 
								<img src="${ci.getProfile_photo_path()}" width="100%">
							</a>
						</div>
						<div class="col-sm-7">
							<div class="col-sm-12 profile-content removePaddings">
								<div class="col-sm-12 padding-one">
									<a class="celebrity_name" href="#" onclick="ShowCelebrity_profile('${ci.getCelebrity_id()}')"> ${ci.getCelebrity_name()} </a>
								</div>
								<div class="col-sm-12 padding-one">
									<c:set var="follower_cnt" value="0"></c:set>
									<c:forEach var="f" items="${celebrity_follower_count}">
										<c:if test="${f.getCelebrity_id()==ci.getCelebrity_id()}">
											<c:set var="follower_cnt" value="${f.getFollower_count()}"></c:set>
										</c:if>
									</c:forEach>
									<span class="span-follower">${follower_cnt} followers</span>
								</div>
								<div class="col-sm-12 padding-one">
									<c:if test="${ci.getFollower_id()==user_id}">
										<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
										<!-- <input type="button" class="btn btn-follow-empty" value="Following"> -->
										<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${ci.getId()}','actor')"><button type="button" class="btn-follow">Unfollow</button></a>
									</c:if>
									<c:if test="${ci.getFollower_id()!=user_id}">
										<input class="btn-follow" type="button" value="Follow" onclick="followCelebrity('${ci.getCelebrity_id()}','1')">
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			
		</div>
	</div>
	<div class="modal fade" id="newsLetterConfirmationModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header my-header">
			        	<button type="button" class="close" data-dismiss="modal">&times;</button>
			        	<!-- <h4 class="modal-title">News Letter Confirmation</h4> -->
			        	<span class="news-title">How do you like to receive news by sms or mail?</span>
			      	</div>
			      	<div class="modal-body">
			      	<!-- <form action=""> -->
				      	<div class="row">
				      		<div class="col-md-12 celebrity_name_may">
				      			<input type="radio" id="mail" name="news_letter_type" value="0" checked="checked"> Mail
				      		</div>
				      	</div>
				      	<div class="row">
				      		<div class="col-md-12 celebrity_name_may">
				      			<input type="radio" id="sms" name="news_letter_type" value="1"> SMS
				      		</div>
				      	</div>
				      	<div class="row">
					      	<div class="col-md-12">
					      		<button onclick="followCelebrity('','')" class="btn-follow">Submit</button>
					      	</div>
				      	</div>
				    <!-- </form> -->
			      	</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="unfollowCelebrityModal">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									Are you sure you want to unfollow this celebrity?
								</div>
								<div class="modal-body">
								<form action="" method="post" id="user_follows">
									<div class="row">
									<div class="col-md-12" align="right">
									<div class="save-btn">
										<input type="button" class="btn-follow" value="Yes" onclick="unfollowCelebrity('','')">
										<input type="button" class="btn-follow" value="No" data-dismiss="modal">
									</div>
									</div>
									</div>
								</form>
								</div>
							</div>
						</div>
		</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>		
<script>
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	
	$("#actors-models-tab").addClass("active");
	$("#"+'${latter}').addClass("active-letter");
});

var ENTITY_ID="",ENTITY_TYPE_ID="";

function followCelebrity(entity_id,entity_type_id)
{
	 if('${user_id}'=="")
	 {
	 	$("#modal_login").modal('show');
	 	return false;
	 }
else
	 {
			/* var tab_id="actor";
			fun_FinalFollow(entity_id,entity_type_id,tab_id); */
			if(entity_id!="")
			 {
			 ENTITY_ID=entity_id;
			 ENTITY_TYPE_ID=entity_type_id;
			 /* $("#newsLetterConfirmationModal").modal('show'); */
			 }
		 /* else{ */
			 var tab_id="actor";
			/*  var news_letter_type=$('input[name="news_letter_type"]:checked').val(); */
			 fun_FinalFollow(ENTITY_ID,ENTITY_TYPE_ID,tab_id);
		/*  } */
	 }
}
</script>
</body>
</html>