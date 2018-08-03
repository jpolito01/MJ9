<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-119766288-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-119766288-1');
</script>
  <title>RSBS - User Searches</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- <link rel="stylesheet" href="resources/assets/css/style.css"> -->
    <link rel="stylesheet" href="resources/assets/css/all-pages.css">
</head>
<body>
<%-- <c:choose>
<c:when test="${empty user_id}">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
</c:when>
<c:otherwise>
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
</c:otherwise>
</c:choose> --%>
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
<div id="my_page_container" class="container main-container inside-page-padding min-page-height">
	<div class="col-sm-12">
			<div class="col-sm-12">
				<label class="title">User Searched Term :</label><label class="politician-info">${search_key}</label>
			</div>
			<%-- <div class="row">
		<c:forEach var="col" begin="0" end="2">
			<c:set var="temp" value="${col}"></c:set>
			<div class="col-sm-4">
				<c:forEach var="list" items="${user_searches_news}" varStatus="i">
				<c:forEach var="cn" items="${list}"  varStatus="ind">
					<c:if test="${ind.index==temp}">
						<div class="col-sm-12 search-result-card">
							<div class="col-sm-12">
								<div class="publication_date blog_date_font"> ${cn.getPublication_date()} </div>
								<a class="news-link blog_desc_font search-result-link" href="${cn.getNews_description_url()}" target="_BLANK">${cn.getNews_title()} </a> 
							</div>
							<c:if test="${not empty cn.getImage_url() && cn.getImage_url()!='null' && cn.getImage_url()!='unknown'}">
								<div class="col-sm-12" style="margin-top: 12px;margin-bottom: 8px;">
									<div align="center"><img src="${cn.getImage_url()}" width="100%"></div>
								</div>
							</c:if>
						</div>
						<c:set var="temp" value="${temp+3}"></c:set>
					</c:if>
					</c:forEach>
				</c:forEach>
				
			</div>
		</c:forEach>
		</div> --%>
		
		<div class="row">
		<c:if test="${empty politician_info}">
			
				<div class="col-md-12 no_result_found" align="center">
					<span>No Result Found</span>
				</div>
			
		</c:if>
		<c:forEach var="col" begin="0" end="3">
		<c:set var="temp" value="${col}"></c:set>
		<div class="col-sm-3 col_padding statewise-politician">
				<c:forEach var="ci" items="${politician_info}" varStatus="st">
				<c:if test="${st.index==temp}">
				<!-- <div class="col-sm-3"> -->
				<%-- <a href="#" onclick="showProfile('${ci.getCelebrity_id()}')" class="bio-card-style"> --%>
					<div class="row profile-card contents">
						<div class="col-sm-4 celeb_ing">
							<a href="#" onclick="showProfile('${ci.getCelebrity_id()}')"> 
								<img src="/PoliticianPhotoes${ci.getProfile_photo_path()}" width="100%" class="statewise-politician-img">
							<!-- <img src="resources/assets/images/2.jpeg" width="100%"> -->
							</a>
						</div>
						<div class="col-sm-8">
							<!-- <div class="col-sm-12"> -->
								<div class="col-sm-12 padding-one politician_name_cards">
									<a class="politician_name" href="#" onclick="showProfile('${ci.getCelebrity_id()}')"> ${ci.getCelebrity_name()} </a>
								</div>
								<div class="col-sm-12 padding-one bio-card-upper-text">
									<c:set var="follower_cnt" value="0"></c:set>
									<c:forEach var="f" items="${politician_follower_count}">
										<c:if test="${f.getCelebrity_id()==ci.getCelebrity_id()}">
											<c:set var="follower_cnt" value="${f.getFollower_count()}"></c:set>
										</c:if>
									</c:forEach>
									<span class="follower-cnt">${follower_cnt} followers</span>
								</div>
									<%-- <div class="suggested-follower-text">
										<div class="row padding-one">
											<div class="col-md-4">
												<label>State </label>
											</div>
											<div class="col-md-8">
												${ci.getState_name()}
											</div>
										</div>
										<div class="row padding-one bio-card-upper-text">
											<div class="col-md-4">
												<label>Category </label>
											</div>
											<div class="col-md-8">
												<c:forEach var="a" items="${politician_categories}" varStatus="st">
													<c:if test="${a.celebrity_id==ci.celebrity_id}">
														<span>${a.getCategroy_name()}
															<c:if test="${st.index<fn:length(politician_categories)-1}">,</c:if>
															<c:if test="${st.index==fn:length(politician_categories)-1}">.</c:if>
														</span><br />
													</c:if>
												</c:forEach>
											</div>
										</div>
									</div> --%>
									<!-- <div class="col-sm-12"> -->
									<div class="row">
								<div class="col-sm-12 poli-of-the-day-text">
									
									<c:if test="${empty user_id}">
									<c:set var="color_code" value="0"/>
									<c:if test="${ci.getParty_id()==1}">
											<a href="#" data-toggle="modal" data-target="#LoginModal">
													<button type="button" class="blue1 cursor_pointer cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
									        <c:set var="color_code" value="${color_code+1}"/>
									</c:if>
									<c:if test="${ci.getParty_id()==2}">
											<a href="#" data-toggle="modal" data-target="#LoginModal">
													<button type="button" class="red1 cursor_pointer cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
									        <c:set var="color_code" value="${color_code+1}"/>
									</c:if>
									<c:if test="${color_code==0}">
                                    <a href="#" data-toggle="modal" data-target="#LoginModal">
													<button type="button" class="others_buttons cursor_pointer cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
									       
                                    </c:if>
									</c:if>
									
									<c:if test="${not empty user_id}">
	                           				 <c:if test="${ci.getFollower_id()==user_id}">
	                           				 <c:set var="color_code" value="0"/>
													<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
											 		<c:if test="${ci.getParty_id()==1}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${ci.getFollow_id()}','search_politician')"><button type="button" class="blue1 cursor_pointer">Unfollow</button></a>
																     <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${ci.getParty_id()==2}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${ci.getFollow_id()}','search_politician')"><button type="button" class="red1 cursor_pointer">Unfollow</button></a>
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
                                                                     <a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${ci.getFollow_id()}','search_politician')"><button type="button" class="others_buttons cursor_pointer">Unfollow</button></a>
                                                                
                                                                </c:if>
											 </c:if>
									         <c:if test="${ci.getFollower_id()!=user_id}">
									         <c:set var="color_code" value="0"/>
										         <c:if test="${ci.getParty_id()==1}">
											         <!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
													 <button type="button" class="blue1 cursor_pointer cursor_pointer followTitleButton" onclick="followCelebrity('${ci.getCelebrity_id()}','1','search_politician')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
											         <c:set var="color_code" value="${color_code+1}"/>
										       </c:if>
										       <c:if test="${ci.getParty_id()==2}">
											         <!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
													 <button type="button" class="red1 cursor_pointer cursor_pointer followTitleButton" onclick="followCelebrity('${ci.getCelebrity_id()}','1','search_politician')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
											         <c:set var="color_code" value="${color_code+1}"/>
										       </c:if>
										       <c:if test="${color_code==0}">
										       		<button type="button" class="others_buttons cursor_pointer cursor_pointer followTitleButton" onclick="followCelebrity('${ci.getCelebrity_id()}','1','search_politician')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
										       
										       </c:if>
									        </c:if>
									</c:if>
									<button type="button" class="view-bio-btn cursor_pointer" onclick="showBio('${ci.getCelebrity_id()}')">View Bio</button>
                                    <%-- <button type="button" class="btn-follow cursor_pointer cursor_pointer" onclick="showBio('${ci.getCelebrity_id()}')">Bio</button> --%>
								</div>
							</div>
							<!-- </div> -->
						</div>
					</div>
					<!-- </a> -->
					
				<!-- </div> -->
				<c:set var="temp" value="${temp+4}"></c:set>
				</c:if>
			</c:forEach>
			</div>
			</c:forEach>
			
		</div>
		</div>
	</div>
	<%-- <c:forEach var="cn" items="${user_searches_news}">
		<label>${cn.getNews_title()} </label> <br />${cn.getNews_description()}<br />
	</c:forEach> --%>
	
<!-- 			<div id="alert_message" class="modal fade" role="dialog">
							 	<div class="modal-dialog">
									<div class="modal-content">
									      <div class="modal-header1" style="padding: 3%;">
									      		<button type="button" class="close" data-dismiss="modal">&times;</button>
									      		<label id="lbl_message">No results found for this search.</label>
									      </div>
								     </div>
								 </div>
							</div>
</div> -->

<div class="modal fade" id="newsLetterConfirmationModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header my-header">
			        	<!-- <h4 class="modal-title">News Letter Confirmation</h4> -->
			        	<span class="news_letter_modal_title">How do you like to receive news by sms or mail?</span>
			        	<button type="button" class="close" data-dismiss="modal">&times;</button>
			      	</div>
			      	<div class="modal-body">
			      	<!-- <form action=""> -->
				      	<div class="row">
				      		<div class="col-md-12 news_letter_modal_text">
				      			<input type="radio" id="mail" name="news_letter_type" value="0" checked="checked"> Mail
				      		</div>
				      	</div>
				      	<div class="row">
				      		<div class="col-md-12 news_letter_modal_text">
				      			<input type="radio" id="sms" name="news_letter_type" value="1"> SMS
				      		</div>
				      	</div>
				      	<div class="row">
					      	<div class="col-md-12">
					      		<button onclick="followCelebrity('','','')" class="btn-follow">Submit</button>
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
<script src="resources/assets/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#my_footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
	 if('${error_message}'!='')
	 {
	 	$("#alert_message").modal("show");
	 }
});

var CELEB_ID=0,ENTITY_TYPE_ID=0,PROFILE=null;

function followCelebrity(celebrity_id,entity_type_id,politician_profile)
{
	if(celebrity_id!='')
	{
		CELEB_ID=celebrity_id.trim();
		ENTITY_TYPE_ID=entity_type_id.trim();
		PROFILE=politician_profile.trim();
		
	}
	/* else
	{
		var news_letter_type=$('input[name="news_letter_type"]:checked').val(); */
		var statewise=PROFILE.trim();
		document.forms[0].action="follow-politician?celebrity_id="+CELEB_ID+"&redirectTo="+statewise;
		document.forms[0].method="post";
		document.forms[0].submit();
	/* } */
}
</script>
</body>
</html>