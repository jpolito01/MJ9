<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
<title>RSBS - Politicians</title>
<link rel="stylesheet" href="resources/assets/css/all-pages.css">
</head>
<body style="background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
<%-- <c:choose>
<c:when test="${empty user_id}">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
</c:when>
<c:otherwise>
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
</c:otherwise>
</c:choose> --%>

	<div id="my_page_container" class="container main-container inside-page-padding min-page-height">
	<c:if test="${not empty politician_info}">
	<div class="row">
		<div class="col-md-12" align="left">
			<span class="state_label">State: </span><span class="state_label"> ${politician_state_name}</span>
		</div>
	</div>
	</c:if>
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
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${ci.getFollow_id()}','statewise_politician')"><button type="button" class="blue1 cursor_pointer">Unfollow</button></a>
																     <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${ci.getParty_id()==2}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${ci.getFollow_id()}','statewise_politician')"><button type="button" class="red1 cursor_pointer">Unfollow</button></a>
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
                                                                     <a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${ci.getFollow_id()}','statewise_politician')"><button type="button" class="others_buttons cursor_pointer">Unfollow</button></a>
                                                                
                                                                </c:if>
											 </c:if>
									         <c:if test="${ci.getFollower_id()!=user_id}">
									         <c:set var="color_code" value="0"/>
										         <c:if test="${ci.getParty_id()==1}">
											         <!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
													 <button type="button" class="blue1 cursor_pointer cursor_pointer followTitleButton" onclick="followCelebrity('${ci.getCelebrity_id()}','1','statewise_politician')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
											         <c:set var="color_code" value="${color_code+1}"/>
										       </c:if>
										       <c:if test="${ci.getParty_id()==2}">
											         <!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
													 <button type="button" class="red1 cursor_pointer cursor_pointer followTitleButton" onclick="followCelebrity('${ci.getCelebrity_id()}','1','statewise_politician')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
											         <c:set var="color_code" value="${color_code+1}"/>
										       </c:if>
										       <c:if test="${color_code==0}">
										       		<button type="button" class="others_buttons cursor_pointer cursor_pointer followTitleButton" onclick="followCelebrity('${ci.getCelebrity_id()}','1','statewise_politician')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
										       
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
	
	<%-- <div class="modal fade" id="AllPolititiansLoginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header modal-header-background">
        <h5 class="modal-title" id="exampleModalLabel">Login</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form id="" action="login" method="post">
			      <div class="modal-body stories-details pod-text">
			      <div class="row form-group">
					      <div class="col-md-1"></div>
						      <div class="col-md-10">
						      <div id="errName" style="color:red"></div>
						      </div>
					      </div>
			        	<div class="row form-group">
			        	<div class="col-md-1"></div>
			        		<div class="col-md-10">
			        					<input type="text" id="user_name" name="user_name" class="form-control" placeholder="Email Id">
			        		</div>
			        	</div>
			        	<div class="row form-group">
			        	<div class="col-md-1"></div>
			        		<div class="col-md-10">
			        					<input type="password" id="password" name="password" class="form-control" placeholder="Password">
			        		</div>
			        	</div>
			        	<div class="row form-group">
			        		<div class="col-md-12" align="center">
			        			<button type="submit" class="btn-follow" onclick="validateLogin()">Login</button>
			        		</div>
			        	</div>
			        	<div class="row">
			        		<div class="col-md-12" align="center">
			        		<a href="#" class="forgot-password-link">Forgot Password</a>
			        		</div>
			        	</div>
			      </div>
				      <div class="modal-footer register-link-padding">
						      <a href="#" data-toggle="modal" data-target="#AllPoliticianRegisterModal" class="register-link">New user?Register here</a>
				      </div>
	      </form>
    </div>
  </div>
</div>	

<div class="modal fade" id="AllPoliticianRegisterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header modal-header-background">
        <h5 class="modal-title" id="exampleModalLabel">Register</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body stories-details pod-text">
      <form action="register" method="post" id="register">
      <div class="row">
      <div class="col-md-12">
      <div id="errName" style="color:red"></div>
      </div>
      </div>
      <div class="row form-group">
					<div class="col-md-6">
					<span>First Name</span>
					<input type="text" id="first_name" name="first_name" class="form-control">
					</div>
					<div class="col-md-6">
					<span>Last Name</span>
					<input type="text" id="last_name" name="last_name" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span>Contact Number</span>
					<input type="text" id="contact_no" name="contact_no" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span>Email Id</span>
					<input type="text" id="email_id" name="email_id" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span>Password</span>
					<input type="text" id="new_password" name="new_password" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span>Confirm Password</span>
					<input type="text" id="confirm_password" name="confirm_password" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12" align="center">
					<input type="submit" value="Register" class="btn-flat" onclick="return validateRegistration('all_politicians')">
					<button type="button" class="btn-flat" data-dismiss="modal">Close</button>
					</div>
					</div>
					</form>
      </div>
      </div>
      </div>
</div> --%>

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
	var image="resources/assets/images/dropdown.png";
	$("#cat"+'${category_id}').addClass('active-nav-link');
	$("#img"+'${category_id}').attr("src",image);
	
		var max_height=$("#my_header").height()+$("#my_footer").height();
		$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
		
			
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