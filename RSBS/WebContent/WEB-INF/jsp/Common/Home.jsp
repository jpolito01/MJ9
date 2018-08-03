<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<!-- <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-9407994341079432",
    enable_page_level_ads: true
  });
</script> -->
<meta charset="UTF-8">
<meta name="description" content="Information of politicians in US">
<meta name="keywords"
	content="most popular politicians in us, politicians in us, us politicians bills, us politicians posts">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RSBS - Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <link rel="stylesheet"
	href="resources/assets/css/bootstrap-grid.min.css">
<link rel="stylesheet"
	href="resources/assets/css/bootstrap-reboot.min.css"> -->
<style type="text/css">
.navbar {
	border-radius: 0px !important;
	margin-bottom: 0px !important;
	min-height:0px !important;
}
.navbar-collapse{
padding-left:0px;
padding-right:0px;
}
.navbar-nav>li>a{
padding-top:0px !important;
padding-bottom:0px !important;
}
.navbar-nav>li{
padding-top:8px !important;
}
.show>.dropdown-menu {
    font-size:15px;
}
.navbar-size {
    padding: 1px 0px !important;
}
.modal-title{
padding-left: 90% !important;
}
.alert-dismissable .close, .alert-dismissible .close {
    right: 0px !important;
}
</style>
</head>
<body style="background-color: #f2f2f2;">
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="container main-container page-padding-top">

		<c:if test="${not empty error}">
		<div class="row">
			<div class="col-sm-12">
				<div class="alert alert-danger alert-dismissable navbar_font">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${error}
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty message}">
		<div class="row">
			<div class="col-sm-12">
				<div class="alert alert-success alert-dismissable navbar_font">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</div>
		</c:if>
		<!-- <div id="rsbsLandingPage"> -->
<div class="row">
			<div class="col-sm-12 removePaddings">
				<div class="col-sm-8 trendingNewsDiv">
					<div class="col-sm-12">
						<div class="row topNewsMargin">
							<div class="col-sm-12 form-group title-bar Tredingpadding">
								<span>Trending now</span>
							</div>
							<div id="myCarousel" class="col-sm-12 first-part carousel slide"
								data-ride="carousel">

								<div class="carousel-inner">
									<c:set var="acc_class" value="active"></c:set>
									<c:set var="car_flag" value="0"></c:set>
									<c:forEach var="tn" items="${top_news }">
										<c:if test="${car_flag==0}">
											<c:set var="acc_class" value="active"></c:set>
										</c:if>
										<c:if test="${car_flag!=0}">
											<c:set var="acc_class" value="active1"></c:set>
										</c:if>
										<div class="item ${acc_class}">
											<div class="row">
												<c:set var="more_title" value="${tn.getNews_title()}" />
												<c:set var="news_source" value="0"></c:set>
												<c:choose>
													<c:when test="${tn.getNews_source()=='Facebook'}">
														<c:set var="news_source" value="facebook"></c:set>
													</c:when>
													<c:when test="${tn.getNews_source()=='Twitter'}">
														<c:set var="news_source" value="twitter"></c:set>
													</c:when>
													<c:when test="${tn.getNews_source()=='Instagram'}">
														<c:set var="news_source" value="instagram"></c:set>
													</c:when>
													<c:otherwise>
														<c:set var="news_source" value="news"></c:set>
													</c:otherwise>
												</c:choose>

												<%-- <c:if test="${not empty tn.getProfile_photo_path()}">
											<div class="col-sm-1 first-part-img">
				                             	<a href="#" class="news-link-style" onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">
		 										<img class="d-block img-fluid" src="/PoliticianPhotoes${tn.getProfile_photo_path()}" width="100%"></a>
											</div>
										</c:if> --%>
												<div class="col-sm-2 first-part-img">
													<div class="col-sm-12">
														 <a href="#" class="news-link-style"
															onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">
															<img class="d-block img-fluid" src="/PoliticianPhotoes${tn.getProfile_photo_path()}" width="100%">
														</a> 
														<%-- <a href="#" class="news-link-style"
															onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">
															<img class="d-block img-fluid" src="resources/assets/images/2.jpeg" width="100%">
														</a> --%>
													</div>
												</div>

												<div class="col-sm-10">
													<c:if
														test="${not empty tn.getNews_title() && tn.getNews_title()!='null' && tn.getNews_title()!='NULL'}">
														<p style="margin-top: 10px; margin-bottom: -5px">
															<a href="#"
																class="news-link-style break-word-style first-part-text stories-details"
																onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">
																${tn.getNews_title()}.</a>
														</p>
													</c:if>
													<c:set var="more_title_description"
														value="${tn.getNews_description()}" />
													<c:if test="${not empty tn.getNews_description() && tn.getNews_description()!='null' && tn.getNews_description()!='NULL'}">
														<p class="description-style break-word-style stories-details">
															<a href="#" class="news-link-style break-word-style"
																onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">
																${tn.getNews_description()}.</a><br />
														</p>
														<div>
															<c:set var="color_code" value="0" />
															<c:if test="${tn.getParty_id()==1}">
																<button type="button"
																	class="blue1 readmore-btn-size cursor_pointer precident_name_style"
																	onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">Read
																	more</button>
																<c:set var="color_code" value="${color_code+1}" />
															</c:if>
															<c:if test="${tn.getParty_id()==2}">
																<button type="button"
																	class="red1 readmore-btn-size cursor_pointer precident_name_style"
																	onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">Read
																	more</button>
																<c:set var="color_code" value="${color_code+1}" />
															</c:if>
															<c:if test="${color_code==0}">
																<button type="button"
																	class="others_buttons readmore-btn-size cursor_pointer precident_name_style"
																	onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">Read
																	more</button>
															</c:if>
														</div>
														
														<%-- <a href="#" onclick="showPoliticianProfile('${tn.getCelebrity_id()}','${tn.getNews_id()}','${news_source}')">Read More</a> --%>

													</c:if>
												</div>

											</div>
											<c:set var="car_flag" value="${car_flag+1}"></c:set>
										</div>
									</c:forEach>
								</div>
								  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-4 politicianoftheDayMainDiv">
					<div class="col-md-12 politicianoftheDayDivChild">
						<div class="row row1 row_margin">

							<c:forEach var="p" items="${politician_of_the_day}">
								<div class="col-sm-12 title-bar form-group">
									<span>Politician of the day</span>
								</div>
								<%-- <c:if test="${not empty p.getProfile_photo_path()}">
							<div class="col-sm-12 pod-image" style="padding-left:0px;padding-right:0px">
								<a href="#" onclick="showProfile('${p.getCelebrity_id()}')"> <img src="/PoliticianPhotoes${p.getProfile_photo_path()}" width="100%"> </a>
							<img src="resources/assets/images/2.jpeg" width="100%"></a>
							</div>
					</c:if> --%>
								<div class="col-sm-12 ">
									<div class="row description form-group"
										style="padding-top: 10px; padding-bottom: 10px;">
										<div class="col-sm-4">
												<%-- <img src="/PoliticianPhotoes${p.getProfile_photo_path()}" width="100%"> </a> --%>
												<!-- <img src="resources/assets/images/2.jpeg" width="100%"> -->
											<a href="#" onclick="showProfile('${p.getCelebrity_id()}')"> <img src="/PoliticianPhotoes${p.getProfile_photo_path()}" width="100%"> </a>
										</div>
										<div class="col-sm-8 politicianoftheDayDescr">
											<div class="row">

												<a style="margin-top: -6px;" href="#"
													class="news-link-style"
													onclick="showProfile('${p.getCelebrity_id()}')"><span
													class="pod-title">${p.getCelebrity_name()}</span></a>

											</div>
											<div class="row">
												<div class="col-sm-12">
													<div class="row">
														<div class="col-sm-4 col_padding">
															<span class="politician_info_title">Position: </span>
														</div>
														<div class="col-sm-8 col_padding">
															<span class="politician_info_text">${p.getPolitician_position()}</span>
														</div>
													</div>
												</div>

												<div class="col-sm-12">
													<div class="row">
														<div class="col-sm-4 col_padding">
															<span class="politician_info_title">State: </span>
														</div>
														<div class="col-sm-8 col_padding">
															<span class="politician_info_text">${p.getState_name()}</span>
														</div>
													</div>
												</div>
												<div class="col-sm-12">
													<div class="row">
														<div class="col-sm-4 col_padding">
															<span class="politician_info_title">Party name: </span>
														</div>
														<div class="col-sm-8 col_padding">
															<span class="politician_info_text">${p.getParty_name()}</span>
														</div>
													</div>
												</div>

												<div
													class="col-sm-12 pod-row-padding polbottompadding col_padding">
													<div align="left">

														<button type="button"
															class="view-bio-btn cursor_pointer politicianbiobtn"
															onclick="showBio('${p.getCelebrity_id()}')">View
															Bio</button>
														<c:if test="${empty user_id}">
															<c:set var="color_code" value="0" />
															<c:if test="${p.getParty_id()==1}">
																<a href="#" data-toggle="modal"
																	data-target="#LoginModal">
																	<button type="button"
																		class="blue1 cursor_pointer politicianfollowbtn followTitleButton" title="Get daily personalised updates in your inbox">Follow</button>
																</a>
																<c:set var="color_code" value="${color_code+1}" />
															</c:if>
															<c:if test="${p.getParty_id()==2}">
																<a href="#" data-toggle="modal"
																	data-target="#LoginModal">
																	<button type="button"
																		class="red1 cursor_pointer politicianfollowbtn followTitleButton" title="Get daily personalised updates in your inbox">Follow</button>
																</a>
																<c:set var="color_code" value="${color_code+1}" />
															</c:if>
															<c:if test="${color_code==0}">
																<a href="#" data-toggle="modal"
																	data-target="#LoginModal">
																	<button type="button"
																		class="others_buttons cursor_pointer politicianfollowbtn followTitleButton" title="Get daily personalised updates in your inbox">Follow</button>
																</a>
															</c:if>
														</c:if>
														<c:if test="${not empty user_id}">
															<c:set var="color_code" value="0" />
															<c:if test="${p.getFollower_id()==user_id}">
																<c:set var="color_code" value="0" />
																<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
																<c:if test="${p.getParty_id()==1}">
																	<a href="#" data-toggle="modal"
																		data-target="#unfollowCelebrityModal"
																		onclick="unfollowCelebrity('${p.getFollow_id()}','homePage')"><button
																			type="button"
																			class="blue1 cursor_pointer politicianfollowbtn">Unfollow</button></a>
																	<c:set var="color_code" value="${color_code+1}" />
																</c:if>
																<c:if test="${p.getParty_id()==2}">
																	<a href="#" data-toggle="modal"
																		data-target="#unfollowCelebrityModal"
																		onclick="unfollowCelebrity('${p.getFollow_id()}','homePage')"><button
																			type="button"
																			class="red1 cursor_pointer politicianfollowbtn">Unfollow</button></a>
																	<c:set var="color_code" value="${color_code+1}" />
																</c:if>
																<c:if test="${color_code==0}">
																	<a href="#" data-toggle="modal"
																		data-target="#unfollowCelebrityModal"
																		onclick="unfollowCelebrity('${p.getFollow_id()}','homePage')"><button
																			type="button"
																			class="others_buttons cursor_pointer politicianfollowbtn">Unfollow</button></a>
																</c:if>
															</c:if>
															<c:if test="${p.getFollower_id()!=user_id}">
																<c:set var="color_code" value="0" />
																<c:if test="${p.getParty_id()==1}">
																	<!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
																	<button type="button"
																		class="blue1 cursor_pointer politicianfollowbtn followTitleButton"
																		onclick="followCelebrity('${p.getCelebrity_id()}','1','homePage')" title="Get daily personalised updates in your inbox">Follow</button>
																	<!-- </a> -->
																	<c:set var="color_code" value="${color_code+1}" />
																</c:if>
																<c:if test="${p.getParty_id()==2}">
																	<!-- <a href="#" data-toggle="modal" data-target="#newsLetterConfirmationModal"> -->
																	<button type="button"
																		class="red1 cursor_pointer politicianfollowbtn followTitleButton"
																		onclick="followCelebrity('${p.getCelebrity_id()}','1','homePage')" title="Get daily personalised updates in your inbox">Follow</button>
																	<!-- </a> -->
																	<c:set var="color_code" value="${color_code+1}" />
																</c:if>
																<c:if test="${color_code==0}">
																	<button type="button"
																		class="others_buttons cursor_pointer politicianfollowbtn followTitleButton"
																		onclick="followCelebrity('${p.getCelebrity_id()}','1','homePage')" title="Get daily personalised updates in your inbox">Follow</button>
																</c:if>
															</c:if>
														</c:if>
													</div>

												</div>
											</div>
										</div>
									</div>

								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			</div>
			<!-- <div class="col-sm-12"> -->
			<div class="row">
				<div class="col-sm-12 col_sm_padding">
					<div class="title-bar Tredingpadding">
						<span>Top news</span>
					</div>
				</div>
				<div class="col-sm-12 col_sm_padding">
					<div class="col-sm-12 top4-news-card">
						<div class="row top_news_contents">
						<div class="col-sm-12 removePaddings">
							<c:forEach var="p" items="${popular_news}" varStatus="stt">
								<c:set var="news_source" value="0"></c:set>
								<c:choose>
									<c:when test="${p.getNews_source()=='Facebook'}">
										<c:set var="news_source" value="facebook"></c:set>
									</c:when>
									<c:when test="${p.getNews_source()=='Twitter'}">
										<c:set var="news_source" value="twitter"></c:set>
									</c:when>
									<c:when test="${p.getNews_source()=='Instagram'}">
										<c:set var="news_source" value="instagram"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="news_source" value="news"></c:set>
									</c:otherwise>
								</c:choose>


								<div class="col-sm-3 top_news_right_border">
									    <div class="row">
										<div class="col-sm-12">
											<div class="col-sm-5 news-img">
												<c:if test="${not empty p.getProfile_photo_path()}">
							<div class="news-img">
								<a href="#" class="news-link-style" onclick="showPoliticianProfile('${p.getCelebrity_id()}','${p.getNews_id()}','${news_source}')">
								<img src="/PoliticianPhotoes${p.getProfile_photo_path()}" width="100%">
								<!-- <img src="resources/assets/images/2.jpg" width="100%"> --></a>
								
							</div>
						 </c:if>


												<%-- <img src="resources/assets/images/${stt.index+1}.jpeg"
													width="100%"> --%>
											</div>
											<div class="col-sm-7 top_news_text_padding">
												<!-- <div class="bottom-padding"> -->
												<c:if
													test="${not empty p.getNews_title() && p.getNews_title()!='null' && p.getNews_title()!='NULL'}">
													<p class="top_news_text break-word-style">
														<a href="#" class="news-link-style break-word-style"
															onclick="showPoliticianProfile('${p.getCelebrity_id()}','${p.getNews_id()}','${news_source}')"
															style="max-width: 97%"> ${p.getNews_title()}</a>
													</p>
												</c:if>
												<div align="left">
													<c:set var="color_code" value="0" />
													<c:if test="${p.getParty_id()==1}">
														<button type="button"
															class="blue1 readmore-btn-size cursor_pointer"
															onclick="showPoliticianProfile('${p.getCelebrity_id()}','${p.getNews_id()}','${news_source}')">Read
															more</button>
														<c:set var="color_code" value="${color_code+1}" />
													</c:if>
													<c:if test="${p.getParty_id()==2}">
														<button type="button"
															class="red1 readmore-btn-size cursor_pointer"
															onclick="showPoliticianProfile('${p.getCelebrity_id()}','${p.getNews_id()}','${news_source}')">Read
															more</button>
														<c:set var="color_code" value="${color_code+1}" />
													</c:if>
													<c:if test="${color_code==0}">
														<button type="button"
															class="others_buttons readmore-btn-size cursor_pointer"
															onclick="showPoliticianProfile('${p.getCelebrity_id()}','${p.getNews_id()}','${news_source}')">Read
															more</button>
													</c:if>
												</div>
												<!-- </div> -->
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- </div> -->
			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="col-sm-12  title-bar">
							<span> More stories</span>
						</div>
					</div>
					<div class="col-sm-12">
			        <c:forEach begin="0" end="3" var="col">
								<div class="col-sm-3 homepageMoreStoryCards">
								<div class="col-sm-12">
									<c:forEach var="np" items="${all_post}" varStatus="in">
									<c:if test="${in.index%4==col}">
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
																				<div class="col-sm-12">
																				<div class="col-sm-1 homepageFeedSource">
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
																						<img src="${post_icon}" width="32px">
																				</div>
																				<div class="col-sm-11 homepageTitles">
																						 <c:if test="${twitterFlag==1}">
																				  <c:if test="${not empty np.getTwitter_handle()}">
																								<p class="twitter-name break-word-style">
																							 	@${np.getTwitter_handle()} 
																								</p>
																								</c:if>  
																								</c:if>
																								<c:if test="${news_source=='facebook'}">
																								<p class="twitter-name break-word-style">
																							 	${np.getCelebrity_name()} 
																								</p>
																								</c:if>
																							<%-- 	<c:if test="${fn:containsIgnoreCase(np.getNews_title(), 'https://tco')}">
        <p>Doesn't contain 'apple'</p>
    </c:if> --%>
																								<c:if test="${not empty np.getNews_title() && np.getNews_title()!='null' && np.getNews_title()!='NULL'}">
																								<p class="news-font break-word-style" id="${np.getNews_id()}_${news_source}_news_title">
																								<a href="#" class="news-link-style break-word-style"
																		onclick="showPoliticianProfile('${np.getCelebrity_id()}','${np.getNews_id()}','${news_source}')">
																		${fn:substring(np.getNews_title(), 0, 120)}....
																		
																	</a>
																										
																								</p>
																								</c:if>
																						
																					
																				</div>
																				</div>
																					<div class="col-sm-12">
																						<c:if test="${not empty np.getImage_url() and  np.getImage_url()!='null' and np.getImage_url()!='unknown'}">
																								<div class="col-sm-12">
																									<hr>
																									<a href="#" class="news-link-style"
																	onclick="showPoliticianProfile('${np.getCelebrity_id()}','${np.getNews_id()}','${news_source}')">
																	<img src="${np.getImage_url()}" width="100%">
																</a>
																								</div>
																						</c:if></div>
																			<%-- <div class="col-sm-12 btn-read-more">
																			<br>
															<c:set var="color_code" value="0" />
															<c:if test="${np.getParty_id()==1}">
																<button type="button" class="blue1 cursor_pointer"
																	onclick="showPoliticianProfile('${np.getCelebrity_id()}','${np.getNews_id()}','${news_source}')">Read
																	more</button>
																<c:set var="color_code" value="${color_code+1}" />
															</c:if>
															<c:if test="${np.getParty_id()==2}">
																<button type="button" class="red1 cursor_pointer"
																	onclick="showPoliticianProfile('${np.getCelebrity_id()}','${np.getNews_id()}','${news_source}')">Read
																	more</button>
																<c:set var="color_code" value="${color_code+1}" />
															</c:if>
															<c:if test="${color_code==0}">
																<button type="button"
																	class="others_buttons cursor_pointer"
																	onclick="showPoliticianProfile('${np.getCelebrity_id()}','${np.getNews_id()}','${news_source}')">Read
																	more</button>
															</c:if>
</div> --%>
																		</div>
																		</div>
																					
									<%-- 	<c:set var="news_source" value="0"></c:set>
										<c:set var="more_title" value="${ap.getNews_title()}" />
										<%
											String more_title = "";
													String text = (String) pageContext
															.getAttribute("more_title");

													if (text.length() > 199) {
														text = text.substring(0, 199);
													}
													more_title = (String) pageContext
															.getAttribute("more_title"); //No exception.
										%>
										<c:if test="${i.index%4==col}">

											<div class="col-sm-12 stories">
												<c:if
													test="${ap.getNews_source()=='Twitter' or ap.getNews_source()=='Instagram'}">

													<c:choose>
														<c:when test="${ap.getNews_source()=='Facebook'}">
															<c:set var="news_source" value="facebook"></c:set>
														</c:when>
														<c:when test="${ap.getNews_source()=='Twitter'}">
															<c:set var="news_source" value="twitter"></c:set>
														</c:when>
														<c:when test="${ap.getNews_source()=='Instagram'}">
															<c:set var="news_source" value="instagram"></c:set>
														</c:when>
														<c:otherwise>
															<c:set var="news_source" value="news"></c:set>
														</c:otherwise>
													</c:choose>

													<div class="row">
														<c:if
															test="${not empty ap.getImage_url() && ap.getImage_url()!='null' && ap.getImage_url()!='unknown'}">
															<div class="col-sm-12 ">
																<a href="#" data-toggle="modal" data-target="#read_more_modal" onclick="readMore('<%=more_title%>','${ap.getImage_url()}')">
																<a href="#" class="news-link-style"
																	onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">
																	<img src="${ap.getImage_url()}" width="100%">
																</a>
															</div>
															<hr>
														</c:if>
														<div class="col-sm-12 top-margin">
															<c:if test="${not empty ap.getNews_title() && ap.getNews_title()!='null' && ap.getNews_title()!='NULL'}">
																<p class="news-title">
																	<a href="#" class="news-link-style break-word-style"
																		onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">
																		${fn:substring(ap.getNews_title(), 0, 120)}....
																		<%=text%> ${ap.getNews_title()}
																	</a>
																</p>
															</c:if>
														</div>
														<div class="col-sm-12 btn-read-more">
															<c:set var="color_code" value="0" />
															<c:if test="${ap.getParty_id()==1}">
																<button type="button" class="blue1 cursor_pointer"
																	onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">Read
																	more</button>
																<c:set var="color_code" value="${color_code+1}" />
															</c:if>
															<c:if test="${ap.getParty_id()==2}">
																<button type="button" class="red1 cursor_pointer"
																	onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">Read
																	more</button>
																<c:set var="color_code" value="${color_code+1}" />
															</c:if>
															<c:if test="${color_code==0}">
																<button type="button"
																	class="others_buttons cursor_pointer"
																	onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">Read
																	more</button>
															</c:if>


														</div>
													</div>
												</c:if>
												<c:if
													test="${ap.getNews_source()!='Twitter' and ap.getNews_source()!='Instagram'}">
													<c:choose>
														<c:when test="${ap.getNews_source()=='Facebook'}">
															<c:set var="news_source" value="facebook"></c:set>
														</c:when>
														<c:when test="${ap.getNews_source()=='Twitter'}">
															<c:set var="news_source" value="twitter"></c:set>
														</c:when>
														<c:when test="${ap.getNews_source()=='Instagram'}">
															<c:set var="news_source" value="instagram"></c:set>
														</c:when>
														<c:otherwise>
															<c:set var="news_source" value="news"></c:set>
														</c:otherwise>
													</c:choose>
													<div class="row">
														<c:set var="div_style" value="col-sm-12"></c:set>
														<c:if
															test="${not empty ap.getImage_url() && ap.getImage_url()!='null' && ap.getImage_url()!='unknown' && ap.getNews_source()=='Facebook'}">
															<c:set var="div_style" value="col-sm-6 offset-sm-3"></c:set>
														</c:if>

														<c:if
															test="${not empty ap.getImage_url() && ap.getImage_url()!='null' && ap.getImage_url()!='unknown'}">
															<div class="${div_style}">
																<a href="#" data-toggle="modal" data-target="#read_more_modal" onclick="readMore('<%=more_title%>','${ap.getImage_url()}')">
																<a href="#" class="news-link-style"
																	onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">
																	<img src="${ap.getImage_url()}" width="100%">
																</a>
															</div>
														</c:if>

														<div class="col-sm-12">
															<p class="news-title news-text-margin">
															<c:if test="${not empty ap.getNews_title() && ap.getNews_title()!='null' && ap.getNews_title()!='NULL'}">
																<a href="#" class="news-link-style break-word-style"
																	onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">
																	${ap.getNews_title()} </a>
																	</c:if>
															</p>
															<div class=" row btn-read-more read-more-style">
																<!-- <div class="col-md-6"> -->
																<c:set var="color_code" value="0" />
																<c:if test="${ap.getParty_id()==1}">
																	<button type="button" class="blue1 cursor_pointer"
																		onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">Read
																		more</button>
																	<c:set var="color_code" value="${color_code+1}" />
																</c:if>
																<c:if test="${ap.getParty_id()==2}">
																	<button type="button" class="red1 cursor_pointer"
																		onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">Read
																		more</button>
																	<c:set var="color_code" value="${color_code+1}" />
																</c:if>
																<c:if test="${color_code==0}">
																	<button type="button"
																		class="others_buttons cursor_pointer"
																		onclick="showPoliticianProfile('${ap.getCelebrity_id()}','${ap.getNews_id()}','${news_source}')">Read
																		more</button>
																</c:if>
																<!-- </div> -->
																<div class="col-md-6" align="right"><span class="date-margin date-style"><fmt:formatDate type = "date" value = "${ap.getNews_date()}" pattern="d MMM y" /></span></div>
															</div>

														</div>

													</div>
												</c:if>
											</div>
										</c:if> --%>
											</c:if>
									</c:forEach>
								</div>
								</div>
							</c:forEach>
						
					</div>
				</div>
			</div>
		<!-- </div> -->
	</div>

	<!-- //Century Schoolbook -->
	<div class="modal fade" id="read_more_modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span> <img id="news-icon" width="20px" height="20px">
					</span> <span id="news-date"></span>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div align="center" class="form-group">
						<img id="read_more_post_img" width="200px" height="200px">
					</div>

					<label id="read_more_post"></label>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="newsLetterConfirmationModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header my-header">
					<!-- <h4 class="modal-title">News Letter Confirmation</h4> -->
					<span class="news_letter_modal_title">How do you like to
						receive news by sms or mail?</span>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<!-- <form action=""> -->
					<div class="row">
						<div class="col-md-12 news_letter_modal_text">
							<input type="radio" id="mail" name="news_letter_type" value="0"
								checked="checked"> Mail
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 news_letter_modal_text">
							<input type="radio" id="sms" name="news_letter_type" value="1">
							SMS
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
				<div class="modal-header">Are you sure you want to unfollow
					this celebrity?</div>
				<div class="modal-body">
					<form action="" method="post" id="user_follows">
						<div class="row">
							<div class="col-md-12" align="right">
								<div class="save-btn">
									<input type="button" class="btn-follow" value="Yes"
										onclick="unfollowCelebrity('','')"> <input
										type="button" class="btn-follow" value="No"
										data-dismiss="modal">
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
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
	<script src="resources/assets/js/common.js"></script> -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="resources/assets/js/common.js"></script>


	<script type="text/javascript">
		$(document).ready(function() {
			$("#home").addClass('active-nav-link');
		});

		var CELEB_ID = 0, ENTITY_TYPE_ID = 0, PROFILE = null;

		function followCelebrity(celebrity_id, entity_type_id,
				politician_profile) {
			if (celebrity_id != '') {
				CELEB_ID = celebrity_id.trim();
				ENTITY_TYPE_ID = entity_type_id.trim();
				PROFILE = politician_profile.trim();

			}

			var redirectTo = PROFILE.trim();
			document.forms[0].action = "follow-politician?celebrity_id="
					+ CELEB_ID + "&redirectTo=" + redirectTo;
			document.forms[0].method = "post";
			document.forms[0].submit();

		}
	</script>

</body>
</html>