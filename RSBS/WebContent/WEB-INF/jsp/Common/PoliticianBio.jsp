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
<title>RSBS - Politician Bio</title>
</head>
<body style="background-color:#f2f2f2;">
	<%@ include file="/WEB-INF/jsp/insideHeader.jsp" %>
		<div id="my_page_container" class="container main-container container-width inside-page-padding min-page-height">
			<!-- <div class="row">
			 <div class="col-md-12"> -->
			 <c:forEach items="${politician_info}" var="c" varStatus="i">
			   <div class="row form-group">
				<div class="col-md-8">
				<div class="bio-style bio_text">
				<div class="row">
				
											<%-- <c:forEach items="${politician_info}" var="c" varStatus="i"> --%>
											<!-- <div class="col-sm-3">
												<img src="resources/assets/images/2.jpg" width="100%" class="bio-image">
											</div> -->
											<c:if test="${not empty c.getProfile_photo_path()}">
												<div class="col-sm-2 bio-image-div">
														<img src="/PoliticianPhotoes${c.getProfile_photo_path()}" width="100%" class="bio-image">
												</div>
											</c:if>
													<div class="col-sm-6">
															<div class="col-sm-12">
																<p class="politician-name name-font">${c.getCelebrity_name()}</p>
																<%-- <span class="follower-cnt">${c.getFollower_count()} </span> --%>
																<%-- <c:set var="followers_cnt" value="0"></c:set>
																				<c:forEach var="f" items="${politician_suggested_follower_count}">
																						<c:if test="${f.getCelebrity_id()==c.getCelebrity_id()}">
																								<c:set var="followers_cnt" value="${f.getFollower_count()}"></c:set>
																						</c:if>
																				</c:forEach>
																				<span class="span-follower">${followers_cnt} followers</span> --%>
															</div>
															<%-- <div class="col-sm-12">
																	<p class="politician-info">${c.getPolitician_position()}</p>
															</div> --%>
															<div class="col-sm-12">
														   <div class="row">
														   		<div class="col-sm-12">
														   			<label class="bio_title">Position: </label>  ${c.getPolitician_position()}
																</div>
																<%-- <div class="col-sm-8">
																	<span>${c.getPolitician_position()}</span>
																</div> --%>
															</div>
							                           </div>
							                      
							                           <div class="col-sm-12">
								                           	<div class="row">
								                           		<div class="col-sm-12">
									                           		<label class="bio_title">State: </label>  ${c.getState_name()}
																</div>
																<%-- <div class="col-sm-8">
																	<span>${c.getState_name()}</span>
																</div> --%>
															</div>
							                           </div>
							                           <div class="col-sm-12">
								                           	<div class="row">
								                           		<div class="col-sm-12">
									                           		<label class="bio_title">Tenure: </label><span>  ${c.getTenure_from()} - <c:if test="${c.getTenure_to()==current_year}">Present</c:if><c:if test="${c.getTenure_to()!=current_year}">${c.getTenure_to()}</c:if></span>
																</div>
																<%-- <div class="col-sm-8">
																	<span>${c.getTenure_from()} - <c:if test="${c.getTenure_to()==current_year}">Present</c:if><c:if test="${c.getTenure_to()!=current_year}">${c.getTenure_to()}</c:if></span>
																</div> --%>
															</div>
							                           </div>
							                           <div class="col-sm-12">
								                           	<div class="row">
								                           		<div class="col-sm-12">
									                           		<label class="bio_title">Years in position: </label>  ${c.getYear_in_position()}
																</div>
																<%-- <div class="col-sm-8">
																	<span>${c.getYear_in_position()}</span>
																</div> --%>
															</div>
							                           </div>
															
															
													</div>
												<div class="col-sm-3" align="center">
													<div class="row">
														<div class="col-sm-9 bio-party-image">
															<c:if test="${c.getParty_id()==1}">
																	<img src="resources/assets/images/democratic-full.png" width="100%" >
																	<span class="red-text">Democratic</span>
															</c:if>
															<c:if test="${c.getParty_id()==2}">
																			<img src="resources/assets/images/republican-full.png" width="100%" >
																			<span class="red-text">Republican</span>
															</c:if>
														</div>
													</div>
													<div class="col-sm-12 bio-page-buttons">
														<div class="row form-group">
															<c:if test="${empty user_id}">
															<c:set var="color_code" value="0"/>
																<c:if test="${c.getParty_id()==1}">
																<a href="#" data-toggle="modal" data-target="#LoginModal">
																		<button type="button" class="blue1 cursor_pointer bio_button_font followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																		<c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${c.getParty_id()==2}">
																<a href="#" data-toggle="modal" data-target="#LoginModal">
																		<button type="button" class="red1 cursor_pointer bio_button_font followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																		<c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
                                                                       <a href="#" data-toggle="modal" data-target="#LoginModal">
																		<button type="button" class="others_buttons cursor_pointer bio_button_font followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																	
                                                                </c:if>
															</c:if>
															<c:if test="${not empty user_id}">
															<c:if test="${c.getFollower_id()==user_id}">
															<c:set var="color_code" value="0"/>
															<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
																<c:if test="${c.getParty_id()==1}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getFollow_id()}','politician_bio')"><button type="button" class="blue1 cursor_pointer bio_button_font">Unfollow</button></a>
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${c.getParty_id()==2}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getFollow_id()}','politician_bio')"><button type="button" class="red1 cursor_pointer bio_button_font">Unfollow</button></a>
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
                                                                	 <a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getFollow_id()}','politician_bio')"><button type="button" class="others_buttons cursor_pointer bio_button_font">Unfollow</button></a>
                                                                
                                                                </c:if>
															</c:if>
															<c:if test="${c.getFollower_id()!=user_id}">
															<c:set var="color_code" value="0"/>
																<c:if test="${c.getParty_id()==1}">
																	<!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
																	<button type="button" class="blue1 cursor_pointer bio_button_font followTitleButton" onclick="followCelebrity('${c.getCelebrity_id()}','1','politician_bio')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${c.getParty_id()==2}">
																	<!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
																	<button type="button" class="red1 cursor_pointer bio_button_font followTitleButton" onclick="followCelebrity('${c.getCelebrity_id()}','1','politician_bio')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
																		<button type="button" class="others_buttons cursor_pointer bio_button_font followTitleButton" onclick="followCelebrity('${c.getCelebrity_id()}','1','politician_bio')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
																</c:if>
															</c:if>
															</c:if>
															
															<button type="button" class="view-bio-btn cursor_pointer bio_button_font bio_button_font" onclick="showProfile('${c.getCelebrity_id()}')">View Posts</button>
															</div>
													</div>
												</div>
								</div>
								</div>
								<div class="bio_text">
								<div class="row">
									<div class="col-md-5 middle-border-right">
										<%-- <div class="row">
											<div class="col-md-6">
												<label class="bio_title">Tenure</label>
											</div>
											<div class="col-md-6">
											<span>${c.getTenure_from()}-<c:if test="${c.getTenure_to()==current_year}">Present</c:if><c:if test="${c.getTenure_to()!=current_year}">${c.getTenure_to()}</c:if></span>
										 
											</div>
										</div> --%>
										<div class="row">
											<div class="col-md-6">
												<label class="bio_title">Term ends</label>
											</div>
											<div class="col-md-6">
												<span>${c.getTerm_ends()}</span>
											</div>
										</div>
										
									</div>
									<div class="col-md-5">
										<%-- <div class="row">
											<div class="col-md-6">
												<label class="bio_title">Years in position</label>
											</div>
											<div class="col-md-6">
												<span>${c.getYear_in_position()}</span>
											</div>
										</div> --%>
										<div class="row">
											<div class="col-md-6">
												<label class="bio_title">Predecessor</label>
											</div>
											<div class="col-md-6">
												<span>${c.getPredecessor()}</span>
											</div>
										</div>
									</div>
								</div>
								
								</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-default">
						<!-- <div class="panel-heading">
							<div align="center" class="title-bar bio-panel-heading">Personal Details</div>
						</div> -->
						<div class="panel-body bio_text">
							<div class="row">
								<div class="col-md-12">
									   <div class="row form-group middle-border-bottom">
											<div class="col-md-4">
												<label class="bio_title">Date of birth</label>
											</div>
											<div class="col-md-8">
												<span>${c.getDate_of_birth()}</span>
											</div>
										</div>
										<div class="row form-group middle-border-bottom">
											<div class="col-md-4">
												<label class="bio_title">Place of birth</label>
											</div>
											<div class="col-md-8">
												<span>${c.getPlace_of_birth()}</span>
											</div>
										</div>
										<div class="row">
											<div class="col-md-4">
												<label class="bio_title">Committees</label>
											</div>
											<div class="col-md-8">
												<span>${c.getCommity()}</span>
											</div>
										</div>
									 <%-- <div class="row form-group">
											<div class="col-md-6">
												<label class="bio_title">Net worth</label>
											</div>
											<div class="col-md-6">
												<span>${c.getNet_worth()}</span>
											</div>
										</div>  --%>
										<!-- <div class="row form-group">
											<div class="col-md-6">
												<label>Religion</label>
											</div>
											<div class="col-md-6">
												<span>March 26, 1940</span>
											</div>
										</div> -->
								 </div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- </div>
			</div> -->
			<div class="row form-group">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading title-bar bio-panel-heading">
							<span>About</span>
						</div>
						<div class="panel-body bio_text">
							${c.getBio()}
						</div>
					</div>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading title-bar bio-panel-heading">
							<span>Career</span>
						</div>
						<div class="panel-body bio_text">
							${c.getCareer()}
						</div>
					</div>
				</div>
			</div>
				</c:forEach>
		</div>
		
		<%-- <div class="modal fade" id="BioLoginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
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
				      <div class="modal-footer register-link-padding" align="center">
						      <a href="#" data-toggle="modal" data-target="#BioRegisterModal" class="register-link">New user?Register here</a>
				      </div>
	      </form>
    </div>
  </div>
</div>	

<div class="modal fade" id="BioRegisterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Register</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body pod-text">
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
		<!-- <script src="resources/assets/js/common.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->

		<script type="text/javascript">
		$(document).ready(function(){
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