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
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-9407994341079432",
    enable_page_level_ads: true
  });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RSBS - Politician Profile</title>
<link rel="stylesheet" href="resources/assets/css/all-pages.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/insideHeader.jsp" %>
<div class="container main-container inside-page-padding">
		<div class="col-sm-12">
			<c:if test="${not empty error}">
					<div class="alert alert-danger">
							${error}
					</div>
			</c:if>
		</div>
		<form method="post" id="profile-page">
		<div class="row main-div" >
				<div class="col-sm-9 leftbarPolitician">
					<div class="col-sm-12 politician-bio">
							<div class="col-sm-12">
								<!-- <div class="col-sm-12"> -->
									<div class="row short-description">
											<c:forEach items="${celebrity_info}" var="c" varStatus="i">
											<c:if test="${c.getParty_id()==1}">
											 <div class="col-sm-2"> 
															<img src="resources/assets/images/democr.png" width="100%" class="republicanImg">
													</div>
											</c:if>
											<c:if test="${c.getParty_id()==2}">
											 <div class="col-sm-2"> 
															<img src="resources/assets/images/republicans.png" width="100%" class="republicanImg">
													</div>
											</c:if>
													
													<div class="col-sm-7">
															<div class="col-sm-12">
																<p class="politician-name name-font name-margin">${c.getCelebrity_name()}</p>
																<%-- <span class="follower-cnt">${c.getFollower_count()} </span> --%>
																
															</div>
															<div class="col-sm-12">
																<c:set var="followers_cnt" value="0"></c:set>
																				<c:forEach var="f" items="${politician_suggested_follower_count}">
																						<c:if test="${f.getCelebrity_id()==c.getCelebrity_id()}">
																								<c:set var="followers_cnt" value="${f.getFollower_count()}"></c:set>
																						</c:if>
																				</c:forEach>
																				<div class="span-follower">${followers_cnt} followers</div>
															</div>
															<div class="col-sm-12">
														   <div class="row">
														   		<div class="col-sm-12">
														   			<label class="politician_info_title">Position: </label>  <span class="politician_info_text">${c.getPolitician_position()}</span>
																</div>
																<%-- <div class="col-sm-9 col_padding">
																	<span class="politician_info_text">${c.getPolitician_position()}</span>
																</div> --%>
															</div>
							                           </div>
							                      
							                           <div class="col-sm-12">
								                           	<div class="row">
								                           		<div class="col-sm-12">
									                           		<label class="politician_info_title">State: </label>  <span class="politician_info_text">${c.getState_name()}</span>
																</div>
																<%-- <div class="col-sm-9 col_padding">
																	<span class="politician_info_text">${c.getState_name()}</span>
																</div> --%>
															</div>
							                           </div>
							                           <div class="col-sm-12">
								                           	<div class="row">
								                           		<div class="col-sm-12">
									                           		<label class="politician_info_title">Tenure: </label>  <span class="politician_info_text">${c.getTenure_from()} - <c:if test="${c.getTenure_to()==current_year}">Present</c:if><c:if test="${c.getTenure_to()!=current_year}">${c.getTenure_to()}</c:if></span>
																</div>
																<%-- <div class="col-sm-9 col_padding">
																	<span class="politician_info_text">${c.getTenure_from()} - <c:if test="${c.getTenure_to()==current_year}">Present</c:if><c:if test="${c.getTenure_to()!=current_year}">${c.getTenure_to()}</c:if></span>
																</div> --%>
															</div>
							                           </div>
							                           <div class="col-sm-12 profile_content_text">
							                           		<div class="row">
							                           			<div class="col-sm-12">
									                           		<label class="politician_info_title">Party name: </label>  <span class="politician_info_text">${c.getParty_name()}</span>
																</div>
																<%-- <div class="col-sm-9 col_padding">
																	<span class="politician_info_text">${c.getParty_name()}</span>
																</div> --%>
															</div>
							                           </div>
															<div class="col-sm-12">
															<c:if test="${empty user_id}">
															<c:set var="color_code" value="0"/>
																<c:if test="${c.getParty_id()==1}">
																		<a href="#" data-toggle="modal" data-target="#LoginModal">
																		<button type="button" class="blue1 cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																		<c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${c.getParty_id()==2}">
																		<a href="#" data-toggle="modal" data-target="#LoginModal">
																		<button type="button" class="red1 cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																		<c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
																<a href="#" data-toggle="modal" data-target="#LoginModal">
																		<button type="button" class="others_buttons cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																		
																</c:if>
															</c:if>
															<c:if test="${not empty user_id}">
															<c:if test="${c.getFollower_id()==user_id}">
															<c:set var="color_code" value="0"/>
															<c:if test="${c.getParty_id()==1}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getFollow_id()}','politician_profile')"><button type="button" class="blue1 cursor_pointer">Unfollow</button></a>
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${c.getParty_id()==2}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getFollow_id()}','politician_profile')"><button type="button" class="red1 cursor_pointer">Unfollow</button></a>
																	<c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
																     <a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getFollow_id()}','politician_profile')"><button type="button" class="others_buttons cursor_pointer">Unfollow</button></a>
																
																</c:if>
															</c:if>
															<c:if test="${c.getFollower_id()!=user_id}">
															<c:set var="color_code" value="0"/>
																<c:if test="${c.getParty_id()==1}">
																		<!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> --><button type="button" class="blue1 cursor_pointer followTitleButton" onclick="followCelebrity('${c.getCelebrity_id()}','1','politician_profile')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
																      <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${c.getParty_id()==2}">
																		<!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> --><button type="button" class="red1 cursor_pointer followTitleButton" onclick="followCelebrity('${c.getCelebrity_id()}','1','politician_profile')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
																        <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
																	<button type="button" class="others_buttons cursor_pointer followTitleButton" onclick="followCelebrity('${c.getCelebrity_id()}','1','politician_profile')" title="Get daily personalised updates in your inbox">Follow</button>
															    </c:if>
															</c:if>
															</c:if>
															<button type="button" class="view-bio-btn cursor_pointer" onclick="showBio('${c.getCelebrity_id()}')">View Bio</button>
															</div>
													</div>
													<c:if test="${not empty c.getProfile_photo_path()}">
														<div class="col-sm-3">
																<img src="/PoliticianPhotoes${c.getProfile_photo_path()}" width="100%" class="politician-page-image">
																<!-- <img src="resources/assets/images/2.jpg" width="100%"></a> -->
														</div>
													</c:if>
											</c:forEach>
								</div>
							<!-- </div> -->
							</div>
							<!-- <div class="col-sm-12"> -->
							<div class="col-sm-12 div-nav">
									<ul class="nav nav-pills nav-justified">
										  <li class="nav-item">
										    <a id="all_post"  class="nav-link post-tap-style politician-nav-link <c:if test="${active_tab=='all_post'}"> active</c:if>" href="show-profile?tab=all_post">All Posts</a>
										  </li>
										  <li class="nav-item">
										    <a id="facebook" class="nav-link post-tap-style politician-nav-link <c:if test="${active_tab=='facebook'}"> active</c:if>" href="show-profile?tab=facebook">Facebook</a>
										  </li>
										  <li class="nav-item">
										    <a id="twitter"  class="nav-link post-tap-style politician-nav-link <c:if test="${active_tab=='twitter'}"> active</c:if>" href="show-profile?tab=twitter">Twitter</a>
										  </li>
										  <li class="nav-item">
										    <a id="instagram"  class="nav-link post-tap-style politician-nav-link <c:if test="${active_tab=='instagram'}"> active</c:if>" href="show-profile?tab=instagram">Instagram</a>
										  </li>
										  <li class="nav-item">
										    <a id="news"  class="nav-link post-tap-style politician-nav-link<c:if test="${active_tab=='news'}"> active</c:if>" href="show-profile?tab=news">News </a>
										  </li>
									</ul>
							</div>
							<!-- </div> -->
							
							<!-- <div class="col-sm-12"> -->
									<div class="row">
										<c:forEach begin="0" end="1" var="col">
													<div class="col-sm-6">
															<div class="col-sm-12">
																<c:forEach items="${news_post}" var="np" varStatus="in">
																	<c:if test="${in.index%2==col}">
																	<c:set var="news_source" value="0"></c:set>
																	<c:choose>
																	<c:when test="${np.getNews_source()=='Facebook'}">
																	<c:set var="news_source" value="facebook"></c:set>
																	</c:when>
																	<c:when test="${np.getNews_source()=='Twitter'}">
																	<c:set var="news_source" value="twitter"></c:set>
																	</c:when>
																	<c:when test="${np.getNews_source()=='Instagram'}">
																	<c:set var="news_source" value="instagram"></c:set>
																	</c:when>
																	<c:otherwise>
																	<c:set var="news_source" value="news"></c:set>
																	</c:otherwise>
																	</c:choose>
																	  <div id="${in.index}">
																		<div class="row news-card" id="${np.getNews_id()}_${news_source}">
																				
																				<div class="col-sm-1 poliProfileFeedSource">
																					<c:set var="flag" value="0"></c:set>
																					<c:set var="flag1" value="0"></c:set>
																					<c:set var="liked_activity_id" value="1"></c:set>
																					<c:set var="twitterFlag" value="0"></c:set>
																					<c:set var="post_icon" value="resources/assets/images/news.png"></c:set>
																					<c:if test="${np.getNews_source()=='Facebook'}">
																							<c:set var="post_icon" value="resources/assets/images/fb.png"></c:set>
																							<c:set var="flag1" value="1"></c:set>
																							<c:set var="liked_activity_id" value="2"></c:set>
																					</c:if>
																					<c:if test="${np.getNews_source()=='Twitter'}">
																							<c:set var="flag" value="1"></c:set>
																							<c:set var="flag1" value="1"></c:set>
																							<c:set var="twitterFlag" value="1"></c:set>
																							<c:set var="liked_activity_id" value="2"></c:set>
																							<c:set var="post_icon" value="resources/assets/images/twitter.png"></c:set>
																					</c:if>
																					<c:if test="${np.getNews_source()=='Instagram'}">
																							<c:set var="flag" value="1"></c:set>
																							<c:set var="flag1" value="1"></c:set>
																							<c:set var="liked_activity_id" value="2"></c:set>
																							<c:set var="post_icon" value="resources/assets/images/instagram.png"></c:set>
																					</c:if>
																						<img src="${post_icon}" width="35px">
																				</div>
																				<div class="col-sm-11">
																						<div class="col-sm-12">
																						
																						 <c:if test="${twitterFlag==1}">
																				  <c:if test="${not empty np.getTwitter_handle()}">
																								<p class="twitter-name break-word-style">
																							 	@${np.getTwitter_handle()} 
																							 	
																								</p>
																								</c:if>  
																								</c:if>
																							<%-- 	<c:if test="${fn:containsIgnoreCase(np.getNews_title(), 'https://tco')}">
        <p>Doesn't contain 'apple'</p>
    </c:if> --%>
																								<c:if test="${not empty np.getNews_title() && np.getNews_title()!='null' && np.getNews_title()!='NULL'}">
																								<p class="news-font break-word-style" id="${np.getNews_id()}_${news_source}_news_title">
																								
																										 <c:choose>
																										 <c:when test="${flag1!=0}">
																										 ${np.getNews_title()}<br />
																										 <c:if test="${not empty np.getNews_description_url() && np.getNews_description_url()!='null' && np.getNews_description_url()!='NULL'}">
																												<a class="break-word-style read_more_font" href="${np.getNews_description_url()}" target="_blank">Read More</a> 
																									 	</c:if>
																									 	<c:if test="${empty np.getNews_description_url()}">
																												<a href="#" class="read_more_font" data-toggle="modal" data-target="#read_more_modal" onclick="readMore('${np.getNews_id()}','${news_source}','${post_icon}')">Read More</a>
																									 	</c:if>
																									 	 </c:when>
																										 <c:otherwise>
																										<a class="news-link-style break-word-style" href="${np.getNews_description_url()}" target="_blank">
																												${np.getNews_title()}</a><br />
																												<a class="break-word-style read_more_font" href="${np.getNews_description_url()}" target="_blank">Read More</a> 
																										 </c:otherwise>
																										 </c:choose>
																										
																								</p>
																								</c:if>
																						</div>	
																						<c:if test="${not empty np.getImage_url() and  np.getImage_url()!='null' and np.getImage_url()!='unknown'}">
																								<div class="col-sm-12">
																									<hr>
																									<c:if test="${flag==1}">
																											
																											<a href="#" onclick="readMore('${np.getNews_id()}','${news_source}','${post_icon}')" data-toggle="modal" data-target="#read_more_modal">
																											<div align="center"><img src="${np.getImage_url()}" width="90%" class="politician-profile-img"></a></div>
																									
																									</c:if>
																									<c:if test="${flag==0}">
																									<c:choose>
																											<c:when test="${np.getNews_source()=='Facebook'}">
																											<a href="#" onclick="readMore('${np.getNews_id()}','${news_source}','${post_icon}')" data-toggle="modal" data-target="#read_more_modal">
																											<div align="center"><img src="${np.getImage_url()}" width="150px" height="150px"></div></a>
																									       </c:when>
																											<c:otherwise>
																											<a class="break-word-style" href="${np.getNews_description_url()}" target="_blank">
																											<div align="center"><img src="${np.getImage_url()}" width="90%" class="politician-profile-img"></div></a>
																									
																											</c:otherwise>
																											</c:choose>
																									
																									</c:if>
																								</div>
																						</c:if>
																				</div>
																				<div class="col-sm-12">
																					<hr>
																					<c:choose>
																					<c:when test="${empty user_id}">
																					<a data-toggle="modal" data-target="#LoginModal" class="cursor_pointer"><img src="resources/assets/images/like.png" width="32px" height="32px" id="${np.getNews_id()}_${news_source}_img"></a>
																					<span id="${np.getNews_id()}_${news_source}_likes">
																					<c:forEach var="a" items="${getUserLikesCount}">
																					<c:if test="${np.getNews_id()==a.getPost_id_or_news_id() && liked_activity_id==a.getActivity_id()}">
																					${a.getLike_count()}
																					</c:if>
																					</c:forEach>
																					</span>
																					<a data-toggle="modal" data-target="#LoginModal" class="cursor_pointer"> <img src="resources/assets/images/comment-empty.png" width="32px" height="32px" id="${np.getNews_id()}_${news_source}_ucommentted"></a>
																					<span id="${np.getNews_id()}_${news_source}_comments">
																					<c:forEach var="a" items="${getUserCommentsCount}">
																					<c:if test="${np.getNews_id()==a.getPost_id_or_news_id() && liked_activity_id==a.getActivity_id()}">
																					${a.getComment_count()}
																					</c:if>
																					</c:forEach>
																					</span>
																					</c:when>
																					<c:otherwise>
																					<c:if test="${np.getUser_id()==user_id}">
																					<a id="${np.getNews_id()}_${news_source}_likesAnchor" class="cursor_pointer" onclick="userUnLikes('${np.getLike_id()}','${np.getNews_id()}','${news_source}')"><img src="resources/assets/images/liked.png" width="32px" height="32px" id="${np.getNews_id()}_${news_source}_img"></a>
																					<span id="${np.getNews_id()}_${news_source}_likes">
																					<c:forEach var="a" items="${getUserLikesCount}">
																					<c:if test="${np.getNews_id()==a.getPost_id_or_news_id() && liked_activity_id==a.getActivity_id()}">
																					${a.getLike_count()}
																					</c:if>
																					</c:forEach>
																					</span>
																					</c:if>
																					
																					<c:if test="${np.getUser_id()!=user_id}">
																					<a id="${np.getNews_id()}_${news_source}_likesAnchor" class="cursor_pointer" onclick="userLikes('${np.getNews_id()}','${news_source}','${np.getCelebrity_id()}')"><img src="resources/assets/images/like.png" width="32px" height="32px" id="${np.getNews_id()}_${news_source}_img"></a>
																					<span id="${np.getNews_id()}_${news_source}_likes">
																					<c:forEach var="a" items="${getUserLikesCount}">
																					<c:if test="${np.getNews_id()==a.getPost_id_or_news_id() && liked_activity_id==a.getActivity_id()}">
																					${a.getLike_count()}
																					</c:if>
																					</c:forEach>
																					</span>
																					</c:if>
																					
																					<c:if test="${np.getCommented_user_id()==user_id}">
																					<a class="cursor_pointer" data-toggle="modal" data-target="#userCommentsModal" onclick="userComments('${np.getNews_id()}','${news_source}','${np.getCelebrity_id()}','${user_id}')"> <img src="resources/assets/images/comments.png" width="32px" height="32px" id="${np.getNews_id()}_${news_source}_ucommentted"></a>
																					<span id="${np.getNews_id()}_${news_source}_comments">
																					<c:forEach var="a" items="${getUserCommentsCount}">
																					<c:if test="${np.getNews_id()==a.getPost_id_or_news_id() && liked_activity_id==a.getActivity_id()}">
																					${a.getComment_count()}
																					</c:if>
																					</c:forEach>
																					</span>
																					</c:if>
																					
																					<c:if test="${np.getCommented_user_id()!=user_id}">
																					<a class="cursor_pointer" data-toggle="modal" data-target="#userCommentsModal" onclick="userComments('${np.getNews_id()}','${news_source}','${np.getCelebrity_id()}','${user_id}')"> <img src="resources/assets/images/comment-empty.png" width="32px" height="32px" id="${np.getNews_id()}_${news_source}_ucommentted"></a>
																					<span id="${np.getNews_id()}_${news_source}_comments">
																					<c:forEach var="a" items="${getUserCommentsCount}">
																					<c:if test="${np.getNews_id()==a.getPost_id_or_news_id() && liked_activity_id==a.getActivity_id()}">
																					${a.getComment_count()}
																					</c:if>
																					</c:forEach>
																					</span>
																					</c:if>
																					
																					</c:otherwise>
																					</c:choose>
																					<c:if test="${flag1==1}">
																					<span class="date-margin date-style">${np.getPublication_date()}</span>
																					</c:if>
																					<c:if test="${flag1==0}">
																					<span class="date-margin date-style">${np.getPublication_date()}</span>
																					</c:if>
																					</div>
																		</div>
																		</div>
																	</c:if>
																</c:forEach>
																
															</div>
													</div>
											</c:forEach>
									</div>
							<!-- </div> -->
				</div>
				
				<!-- ------------------------Next previous button---------------------------------- -->
							<c:if test="${total_news_post>limit}">
							<div class="well whiteWell calendarWell">
								<div class="row">
								<input type="hidden" name="active_tab_name" value="${active_tab}">
									<div class="col-md-6">Show ${offset} to ${offset+limit}
										out of ${total_news_post}</div>
									<div class="col-md-6" align="right">
										<div class="col-md-12">
											<c:if test="${total_news_post>limit}">
												<c:set var="btn_count" value="0" />
												<fmt:parseNumber var="btn_count" integerOnly="true"
													type="number" value="${total_news_post/limit}" />

												<c:if test="${total_news_post%limit!=0 }">
													<c:set var="btn_count" value="${(btn_count)+1}" />
												</c:if>
												<c:set var="individual_btn_offset" value="0" />
												<c:if test="${individual_btn_offset!=offset }">
													<button class="prev_next_btn cursor_pointer" type="submit"
														name="btn_previous" value="${offset}">Previous</button>
												</c:if>
												<c:forEach begin="1" end="${btn_count}" var="b">
													<c:if test="${individual_btn_offset==offset}">
														<button type="submit" name="btn_offset"
															class="active_page cursor_pointer" value="${individual_btn_offset}">${b}
														</button>
													</c:if>
													<c:if test="${individual_btn_offset!=offset}">
														<button type="submit" name="btn_offset"
															class="passive_page cursor_pointer" value="${individual_btn_offset}">${b}
														</button>
													</c:if>
													<c:set var="individual_btn_offset"
														value="${individual_btn_offset+limit}" />
												</c:forEach>
												<c:if test="${(individual_btn_offset-limit)!=offset}">
													<button class="prev_next_btn cursor_pointer" type="submit" name="btn_next"
														value="${offset}">Next</button>
												</c:if>
											</c:if>
										</div>
										<br /> <br />
									</div>
								</div>
								</div>
							</c:if>
		</div>
		<div class="col-sm-3 sidebar">
				<div class="row row1"> 
						<div class="col-sm-12">
								<div class="col-sm-12 title-bar">
										<span> Suggested </span>
								</div>
								<c:forEach var="s" items="${suggested_politician}">
								<div class="col-sm-12 suggested-follow suggested-cards">
										<div class="row" >
												<div class="col-sm-4">
												<a href="#" onclick="showProfile('${s.getCelebrity_id()}')">
														<img src="/PoliticianPhotoes${s.getProfile_photo_path()}" width="100%" class="politician-profile-img"></a>
												<!-- <img src="resources/assets/images/2.jpeg" width="100%"></a> -->
												</div>
												<div class="col-sm-8">
													<!-- <div class="col-sm-12 suggested-follow"> -->	
															<div class="row div-follow">
																	<div class="col-sm-12 div-politician-info">
																				<a href="#" class="name-link-style" onclick="showProfile('${s.getCelebrity_id()}')"><span> ${s.getCelebrity_name()}</span></a><br />
																				<!-- <span class="follower-cnt">11 Followers </span> -->
																				<c:set var="follower_cnt" value="0"></c:set>
																				<c:forEach var="f" items="${politician_suggested_follower_count}">
																						<c:if test="${f.getCelebrity_id()==s.getCelebrity_id()}">
																								<c:set var="follower_cnt" value="${f.getFollower_count()}"></c:set>
																						</c:if>
																				</c:forEach>
																				<span class="span-follower suggested-follower-text">${follower_cnt} followers</span>
																	</div>
																	<div class="col-sm-12 div-follow div-politician-follow">
																			<%-- <button type="button" class="red1" onclick="followCelebrity('${s.getCelebrity_id()}','1','politician_profile')"> Follow </button> --%>
																	<c:if test="${empty user_id}">
																	<c:set var="color_code" value="0"/>
																		<c:if test="${s.getParty_id()==1}">
																			<a href="#" data-toggle="modal" data-target="#LoginModal">
																			<button type="button" class="blue1 cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																			<c:set var="color_code" value="${color_code+1}"/>
																		</c:if>
																		<c:if test="${s.getParty_id()==2}">
																			<a href="#" data-toggle="modal" data-target="#LoginModal">
																			<button type="button" class="red1 cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																			<c:set var="color_code" value="${color_code+1}"/>
																		</c:if>
																		<c:if test="${color_code==0}">
																		      <a href="#" data-toggle="modal" data-target="#LoginModal">
																			<button type="button" class="others_buttons cursor_pointer followTitleButton" title="Get daily personalised updates in your inbox">Follow</button></a>
																		</c:if>
																	</c:if>
															<c:if test="${not empty user_id}">
	                           				                <c:if test="${s.getFollower_id()==user_id}">
	                           				                <c:set var="color_code" value="0"/>
															<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
																<c:if test="${s.getParty_id()==1}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${s.getFollow_id()}','politician_profile')"><button type="button" class="blue1 cursor_pointer unfollow-btn">Unfollow</button></a>
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${s.getParty_id()==2}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${s.getFollow_id()}','politician_profile')"><button type="button" class="red1 cursor_pointer unfollow-btn">Unfollow</button></a>
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${s.getFollow_id()}','politician_profile')"><button type="button" class="others_buttons cursor_pointer unfollow-btn">Unfollow</button></a>
																
																</c:if>
															</c:if> 
															<c:if test="${s.getFollower_id()!=user_id}">
															<c:set var="color_code" value="0"/>
																<c:if test="${s.getParty_id()==1}">
																	<!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
																	<button type="button" class="blue1 cursor_pointer followTitleButton" onclick="followCelebrity('${s.getCelebrity_id()}','1','politician_profile')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${s.getParty_id()==2}">
																	<!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
																	<button type="button" class="red1 cursor_pointer followTitleButton" onclick="followCelebrity('${s.getCelebrity_id()}','1','politician_profile')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
																	<button type="button" class="others_buttons cursor_pointer followTitleButton" onclick="followCelebrity('${s.getCelebrity_id()}','1','politician_profile')" title="Get daily personalised updates in your inbox">Follow</button><!-- </a> -->
																
																</c:if>
															</c:if>
															</c:if>
																	</div>
															</div>
													<!-- </div> -->
												</div>
												</div>
										</div>
									</c:forEach>
						</div>
				</div>
		</div>
		
</div>
</form>
</div>

<div class="modal fade" id="read_more_modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header modal-header-background">
				<!-- <h5 class="modal-title" id="exampleModalLabel">Read More</h5> -->
					<span>
					       <img id="news-icon" width="20px" height="20px">
					</span>
				    <span id="news-date" class="read-more-date-margin date-style"></span>
				    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
				</div>
				<div class="modal-body read-more-font">
					<div align="center" class="form-group">
					     <img id="read_more_post_img" width="100%">
					</div>
				
					<span id="read_more_post" class="read-more-font break-word-style"></span>
				</div>
			</div>
		</div>
	</div>
	
<div class="modal fade" id="userCommentsModal">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header modal-header-background">
<h5 class="modal-title" id="exampleModalLabel">Comment</h5>

<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span></button>
						          </div>
	<div class="modal-body comment_madal_font">
		<div class="row form-group">
			<div class="col-md-12"><span id="commentsErr" style="color:red;"></span></div>
		</div>
		<div class="row form-group">
			<div class="col-md-12">
				<textarea rows="4" id="comment" name="comment" class="form-control"></textarea>
			</div>
		</div>
		<div class="row form-group">
			<div class="col-md-12" align="left">
				<input type="button" class="btn-follow" value="Post" onclick="userComments('','','','')">
			</div>
		</div>
		<div id="comments_title"></div>
		<div id="comments-details">
	
	</div>
	<div id="see_all_posts"></div>
	</div>
</div>
</div>
</div>

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

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="resources/assets/js/common.js"></script> -->
<script src="resources/assets/js/ScrollJs.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	if('${politician_news_id}'!='')
	{
		var news_source='${news_source_redirect}';
		 if(news_source!=""){
			 $(".nav-link").removeClass("active");
			 $("#"+news_source).addClass("active");
		}
		var url = document.location.href+"#"+'${politician_news_id}'+'_'+'${news_source_redirect}';
		document.location = url;
		$("#"+'${politician_news_id_active}'+'_'+'${news_source_redirect_active}'+'_news_title').css('background-color','#e8eeff');
		$(window).scrollTop($("#"+'${politician_news_id}'+'_'+'${news_source_redirect}').offset().top-200);
	}
	if('${show_news}'!='')
	{
		var url = document.location.href+"#"+'${show_news}';
		document.location = url;
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
		var redirectTo=PROFILE.trim();
		document.forms[0].action="follow-politician?celebrity_id="+CELEB_ID+"&redirectTo="+redirectTo;
		document.forms[0].method="post";
		document.forms[0].submit();
}

</script>

</body>
</html>