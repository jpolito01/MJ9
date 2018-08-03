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
	<link rel="stylesheet" href="resources/assets/css/Acotrs.css">
</head>
<body style="font-family: Open sans;">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div id="my_page_container" class="container page-padding-top my_container_height" style="background-color: #f2f2f2;">
	<form method="post" id="profile-page">
		<div class="col-sm-12 main-div">
			<div class="col-sm-10 leftbarCelebrity">
			<!-- <blockquote class="instagram-media" data-instgrm-version="7" style=" background:#FFF; border:0; border-radius:3px; box-shadow:0 0 1px 0 rgba(0,0,0,0.5),0 1px 10px 0 rgba(0,0,0,0.15); margin: 1px; max-width:658px; padding:0; width:99.375%; width:-webkit-calc(100% - 2px); width:calc(100% - 2px);"><div style="padding:8px;"> <div style=" background:#F8F8F8; line-height:0; margin-top:40px; padding:50.0% 0; text-align:center; width:100%;"> <div style=" background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAsCAMAAAApWqozAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAAMUExURczMzPf399fX1+bm5mzY9AMAAADiSURBVDjLvZXbEsMgCES5/P8/t9FuRVCRmU73JWlzosgSIIZURCjo/ad+EQJJB4Hv8BFt+IDpQoCx1wjOSBFhh2XssxEIYn3ulI/6MNReE07UIWJEv8UEOWDS88LY97kqyTliJKKtuYBbruAyVh5wOHiXmpi5we58Ek028czwyuQdLKPG1Bkb4NnM+VeAnfHqn1k4+GPT6uGQcvu2h2OVuIf/gWUFyy8OWEpdyZSa3aVCqpVoVvzZZ2VTnn2wU8qzVjDDetO90GSy9mVLqtgYSy231MxrY6I2gGqjrTY0L8fxCxfCBbhWrsYYAAAAAElFTkSuQmCC); display:block; height:44px; margin:0 auto -44px; position:relative; top:-22px; width:44px;"></div></div><p style=" color:#c9c8cd; font-family:Arial,sans-serif; font-size:14px; line-height:17px; margin-bottom:0; margin-top:8px; overflow:hidden; padding:8px 0 7px; text-align:center; text-overflow:ellipsis; white-space:nowrap;"><a href="https://www.instagram.com/p/BZ8v4enn2TO/" style=" color:#c9c8cd; font-family:Arial,sans-serif; font-size:14px; font-style:normal; font-weight:normal; line-height:17px; text-decoration:none;" target="_blank">A post shared by Madhuri Dixit (@madhuridixitnene)</a> on <time style=" font-family:Arial,sans-serif; font-size:14px; line-height:17px;" datetime="2017-10-07T13:53:56+00:00">Oct 7, 2017 at 6:53am PDT</time></p></div></blockquote> <script async defer src="//platform.instagram.com/en_US/embeds.js"></script> -->
					<c:forEach items="${celebrity_list}" var="cl">
						<div class="basic-info col-sm-12">
							<div class="col-sm-3">
								<img class="profile-photo1" width="100%" src="${cl.getProfile_photo_path()}">
							</div>
							<div class="col-sm-9">
							<div class="row">
							<div class="col-sm-10">
								<div class="celeb_name">
									<span class="profile_celebrity_name">${cl.getCelebrity_name()}</span>
								<!-- </div>
								<div class="caleb_category"> -->
								<br />
									<span class="category_celebrity">
										<c:forEach items="${celebrity_cat}" var="c" varStatus="i">
											${c.getCategory_name()}
											<c:if test="${i.index+1!=fn:length(celebrity_cat)}">,</c:if>
										</c:forEach> 
									</span>
								</div>
								<div class="celeb_follower">
									<span class="follower_celebrity"> ${cl.getFollower_count()} 
										<c:if test="${cl.getFollower_count()<=1}">Follower</c:if>
										<c:if test="${cl.getFollower_count()>1}">Followers</c:if>
									</span>
								</div>
								</div>
								<div class="col-sm-2">
								<div align="center">
									<c:if test="${cl.getFollower_id()==user_id}">
										<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
										<!-- <input type="button" class="btn btn-follow-empty" value="Following"> -->
									<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${cl.getId()}','celebrity-profile')"><button type="button" class="btn-follow">Unfollow</button></a>
									</c:if>
									<c:if test="${cl.getFollower_id()!=user_id}">
										<input type="button" class="btn-follow" onclick="follow('${cl.getCelebrity_id()}',1)" value="Follow" class="btn">
									</c:if>
									
								</div>
							</div>
							</div>
								<div class="row">
								 <div class="col-sm-12">
									<div class="celeb_bio" style="text-align: justify;">
									<span>${cl.getCelebrity_bio()}</span>
								 </div>
								</div>
								</div>
							</div>
							<%-- <div class="col-sm-2">
								<div align="center">
									<c:if test="${cl.getFollower_id()==user_id}">
										<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
										<!-- <input type="button" class="btn btn-follow-empty" value="Following"> -->
									<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${cl.getId()}','celebrity-profile')"><button type="button" class="btn-follow">Unfollow</button></a>
									</c:if>
									<c:if test="${cl.getFollower_id()!=user_id}">
										<input type="button" class="btn-follow" onclick="follow('${cl.getCelebrity_id()}',1)" value="Follow" class="btn">
									</c:if>
									
								</div>
							</div> --%>
						</div>
					</c:forEach>
						<div class="col-sm-12 navbar-design">
							<nav class="navbar navbar-inverse my-nav-style navbar-margin">
							  <div class="container-fluid cf">
							    <div class="navbar-header">
							      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
							        <span class="icon-bar"></span>
							        <span class="icon-bar"></span>
							        <span class="icon-bar"></span>                        
							      </button>
							    <!--   <a class="navbar-brand" href="#">WebSiteName</a> -->
							    </div>
							    <div class="collapse navbar-collapse" id="myNavbar">
							      <!-- <ul class="nav navbar-nav">  -->
							      <ul class="nav nav-justified">
							        <li id="all_post"><a href="celebrity-profile?q=all_post">All Post</a></li>
							        <li id="twitter"><a href="celebrity-profile?q=twitter">Twitter</a></li>
							        <li id="facebook"><a href="celebrity-profile?q=facebook">Facebook</a></li>
							        <li id="instagram"><a href="celebrity-profile?q=instagram">Instagram</a></li>
							        <li id="news"><a href="celebrity-profile?q=news">In the news</a></li>
							      </ul>
							    </div>
							  </div>
							</nav>
						</div>
						
					    <!-- movie producer news --> 
					   
						
						<c:if test="${celebrity_is_movie==false}">
						<div class="row">
						<c:forEach var="x" begin="0" end="2">
						<div class="col-sm-4">
						<c:set var="cardl" value="0"></c:set>
						<c:forEach var="cp" items="${celebrity_post}" varStatus="i">
							<c:if test="${not empty cp.getNews_title() && cp.getNews_title()!= 'null'}">
							
								<c:if test="${cardl%3==x}">
									<div id="${cp.getNews_id()}" class="col-sm-12 post-info">
										<div class="col-sm-12 ">
											<div class="col-sm-3 post-header">
												<c:set var="temp_flg" value="0"></c:set>  <!-- It is very important -->
												<c:set var="activity_type" value="1"></c:set>
												<c:if test="${cp.getNews_source()=='Facebook'}">
													<img src="resources/assets/images/fb.png" height="30px" width="30px">
													<c:set var="temp_flg" value="${temp_flg+1}"></c:set>
														<c:set var="activity_type" value="${activity_type+1}"></c:set>
												</c:if>
												<c:if test="${cp.getNews_source()=='Instagram'}">
													<img src="resources/assets/images/instagram.png" height="30px" width="30px">
													<c:set var="temp_flg" value="${temp_flg+1}"></c:set>
														<c:set var="activity_type" value="${activity_type+1}"></c:set>
												</c:if>
												<c:if test="${cp.getNews_source()=='Twitter'}">
													<img src="resources/assets/images/twitter.png" height="30px" width="30px">
													<c:set var="temp_flg" value="${temp_flg+1}"></c:set>
														<c:set var="activity_type" value="${activity_type+1}"></c:set>
												</c:if>
												<c:if test="${temp_flg==0}">
													<img src="resources/assets/images/news.png" height="30px" width="30px">
												</c:if>
											 </div>
											<div class="col-sm-9"> 
													<span class="pull-right profile-date-style" style="padding-top: 12px;">
													${cp.getPublication_date()}
													</span>
											</div>
										</div>
										
										<c:if test="${cp.getNews_source()=='Instagram' || cp.getNews_source()=='Twitter'}">
												<div class="col-sm-12 post-title" id="${cp.getNews_id()}_post_title">
													<c:set var="len" value="${fn:length(cp.getNews_title())}"></c:set>
													<c:if test="${fn:length(cp.getNews_title())>=120}">
														<c:set var="title" value="${fn:substring(cp.getNews_title(), 0,120)}..."></c:set>
													</c:if>
													<c:if test="${fn:length(cp.getNews_title())<120}">
														<c:set var="title" value="${cp.getNews_title()}"></c:set>
													</c:if>
													<c:set var="more_title" value="${title}"/>
													
															 <%
															    String more_title = ""; 
															 	String text=(String)pageContext.getAttribute("more_title");
															 	text=text.replace("?", "");
															 	more_title = (String)pageContext.getAttribute("more_title");   //No exception.
															 	more_title=more_title.replace("\'", "\\'");
															 	more_title=more_title.replace("?", "");
															 %>
													<c:if test="${not empty cp.getNews_description_url()}">
														<a class="break-word-style" id="${cp.getNews_id()}_title" href="${cp.getNews_description_url()}" target="_BLANK">
															<%=text%>
														</a>
													</c:if>
													<c:if test="${empty cp.getNews_description_url()}">
														<label class="break-word-style" id="${cp.getNews_id()}_title"><%=text%></label>
														<c:if test="${len>120}">
															<c:set var="img_post" value="${cp.getImage_url()}"></c:set>
															<c:if test="${cp.getImage_url()=='null'}">
																<c:set var="img_post" value=""></c:set>
															</c:if>
															
															<a href="#${cp.getNews_id()}" onclick="ReadMore_FbPost('<%=more_title%>','${img_post}','${cp.getNews_source()}','${cp.getPublication_date()}')">Read More </a>
														</c:if>
													</c:if>
											</div>
											<div class="col-sm-12 padding-one">
												<div class="col-sm-12">
												<c:if test="${cp.getImage_url()!='null' && not empty cp.getImage_url()}">	
													<a href="#${cp.getNews_id()}" onclick="ReadMore_FbPost('<%=more_title%>','${cp.getImage_url()}','${cp.getNews_source()}','${cp.getPublication_date()}')">
														<img src="${cp.getImage_url()}" width="100%"></a>
												</c:if>
												</div>
											</div>
									
										</c:if>
										<c:if test="${cp.getNews_source()!='Instagram' && cp.getNews_source()!='Twitter'}">
											
												<div class="col-sm-12 post-title" id="${cp.getNews_id()}_post_title">
														<c:set var="len" value="${fn:length(cp.getNews_title())}"></c:set>
														<c:if test="${fn:length(cp.getNews_title())>=120}">
															<c:set var="title" value="${fn:substring(cp.getNews_title(), 0,120)}..."></c:set>
														</c:if>
														<c:if test="${fn:length(cp.getNews_title())<120}">
															<c:set var="title" value="${cp.getNews_title()}"></c:set>
														</c:if>
														<c:set var="more_title" value="${title}"/>
																 <%
																    String more_title = ""; 
																 	String text=(String)pageContext.getAttribute("more_title");
																 	text=text.replace("?", "");
																 	more_title = (String)pageContext.getAttribute("more_title");   //No exception.
																 	more_title=more_title.replace("\'", "\\'");
																 	more_title=more_title.replace("?", "");
																 %>
														<c:if test="${not empty cp.getNews_description_url()}">
															<a class="break-word-style" id="${cp.getNews_id()}_title" href="${cp.getNews_description_url()}" target="_BLANK">
																<%=text%>
															</a>
														</c:if>
														<c:if test="${empty cp.getNews_description_url()}">
															<label class="break-word-style" id="${cp.getNews_id()}_title"><%=text%></label>
															<c:if test="${len>120}">
																<c:set var="img_post" value="${cp.getImage_url()}"></c:set>
																<c:if test="${cp.getImage_url()=='null'}">
																	<c:set var="img_post" value=""></c:set>
																</c:if>
																
																<a href="#${cp.getNews_id()}" onclick="ReadMore_FbPost('<%=more_title%>','${img_post}','${cp.getNews_source()}','${cp.getPublication_date()}')">Read More </a>
															</c:if>
														</c:if>
												</div>
												<div class="col-sm-12 padding-one" align="center">
													<!-- <div class="col-sm-4 col-sm-offset-3"> -->
													<c:choose>
													<c:when test="${cp.getNews_source()=='Facebook'}">
															<c:if test="${cp.getImage_url()!='null' && not empty cp.getImage_url()}">	
														<a href="#${cp.getNews_id()}" onclick="ReadMore_FbPost('<%=more_title%>','${cp.getImage_url()}','${cp.getNews_source()}','${cp.getPublication_date()}')">
															<img src="${cp.getImage_url()}" width="150px" height="150px">
														</a>
													</c:if>
													</c:when>
													<c:otherwise>
															<c:if test="${cp.getImage_url()!='null' && not empty cp.getImage_url()}">	
																<a href="#${cp.getNews_id()}" onclick="ReadMore_FbPost('<%=more_title%>','${cp.getImage_url()}','${cp.getNews_source()}','${cp.getPublication_date()}')">
																	<img src="${cp.getImage_url()}" width="100%">
																</a>
															</c:if>
													</c:otherwise>
														</c:choose>	
													<!-- </div> -->
												</div>
										</c:if>
										
										<div class="col-sm-12 profile-like-comment">
										<c:set var="like-count" value="0"></c:set>
											<c:if test="${temp_flg!=0}">
												<c:if test="${user_id==cp.getUser_id()}">
									 				<a class="text-decoration-style"><img src="resources/assets/images/liked.png" onclick="like('${cp.getNews_id()}','0','2','${cp.getNews_source()}')" height="35px" width="35px" id="${cp.getNews_id()}_${activity_type}_img"> </a>
										 			<span id="${cp.getNews_id()}_${activity_type}_likes">
										 			<c:if test="${not empty like_cout}">
										 			<c:forEach var="a" items="${like_cout}">
														<c:if test="${cp.getNews_id()==a.getPost_id_or_news_id() && activity_type==a.getActivity_type_id()}">
												        ${a.getLike_count()}
														</c:if>
													</c:forEach>
													</c:if>
													<c:if test="${empty like_cout}">${like-count}</c:if>
													</span>
									 			</c:if>
									 			<c:if test="${user_id!=cp.getUser_id()}">
									 				<a class="text-decoration-style"><img src="resources/assets/images/like.png" onclick="like('${cp.getNews_id()}','1','2','${cp.getNews_source()}')" height="35px" width="35px" id="${cp.getNews_id()}_${activity_type}_img"> </a>
									 				<%-- <span id="${cp.getNews_id()}_${activity_type}_likes"></span> --%>
									 			<span id="${cp.getNews_id()}_${activity_type}_likes">
									 			<c:if test="${not empty like_cout}">
										 			<c:forEach var="a" items="${like_cout}">
														<c:if test="${cp.getNews_id()==a.getPost_id_or_news_id() && activity_type==a.getActivity_type_id()}">
												        ${a.getLike_count()}
														</c:if>
													</c:forEach>
													</c:if>
													<c:if test="${empty like_cout}">${like-count}</c:if>
													</span>
									 			</c:if>
									 			
												<c:set var="CommentsCount" value="0"></c:set>
										 			<c:forEach var="uc" items="${comment_cout}">
										 				<c:if test="${cp.getNews_id()==uc.getPost_id_or_news_id()}">
										 					<c:set var="CommentsCount" value="${uc.getComment_count()}"></c:set>
										 				</c:if>
										 			</c:forEach>
											 		<a class="text-decoration-style" onclick="user_comments('${cp.getNews_id()}',2)">
											 			<c:if test="${CommentsCount>0 }">
											 				<img src="resources/assets/images/comments.png" height="30px" width="30px" id="${cp.getNews_id()}_${activity_type}_comments">
											 			</c:if>
											 			<c:if test="${CommentsCount==0 }">
											 				<img src="resources/assets/images/comment-empty.png" height="30px" width="30px" id="${cp.getNews_id()}_${activity_type}_comments">
											 			</c:if>
													</a><span id="${cp.getNews_id()}_${activity_type}_comments_count">${CommentsCount}</span>
											</c:if>
											<c:if test="${temp_flg==0}">
												<c:if test="${user_id==cp.getUser_id()}">
									 				<a class="text-decoration-style"><img src="resources/assets/images/liked.png" onclick="like('${cp.getNews_id()}','0','1','${cp.getNews_source()}')" height="35px" width="35px" id="${cp.getNews_id()}_${activity_type}_img"> </a>
									 			<%-- <span id="${cp.getNews_id()}_${activity_type}_likes"></span> --%>
									 				<span id="${cp.getNews_id()}_${activity_type}_likes">
									 				<c:if test="${not empty like_cout}">
										 			<c:forEach var="a" items="${like_cout}">
														<c:if test="${cp.getNews_id()==a.getPost_id_or_news_id() && activity_type==a.getActivity_type_id()}">
												        ${a.getLike_count()}
														</c:if>
													</c:forEach>
													</c:if>
													<c:if test="${empty like_cout}">${like-count}</c:if>
													</span>
									 			</c:if>
									 			<c:if test="${user_id!=cp.getUser_id()}">
									 				<a class="text-decoration-style"><img src="resources/assets/images/like.png" onclick="like('${cp.getNews_id()}','1','1','${cp.getNews_source()}')" height="35px" width="35px" id="${cp.getNews_id()}_${activity_type}_img"> </a>
									 			<%-- <span id="${cp.getNews_id()}_${activity_type}_likes"></span> --%>
									 				<span id="${cp.getNews_id()}_${activity_type}_likes">
									 				<c:if test="${not empty like_cout}">
										 			<c:forEach var="a" items="${like_cout}">
														<c:if test="${cp.getNews_id()==a.getPost_id_or_news_id() && activity_type==a.getActivity_type_id()}">
												        ${a.getLike_count()}
														</c:if>
													</c:forEach>
													</c:if>
													<c:if test="${empty like_cout}">${like-count}</c:if>
													</span>
									 			</c:if>
									 			<c:set var="CommentsCount" value="0"></c:set>
									 			<c:forEach var="uc" items="${comment_cout}">
									 				<c:if test="${cp.getNews_id()==uc.getPost_id_or_news_id()}">
									 					<c:set var="CommentsCount" value="${uc.getComment_count()}"></c:set>
									 				</c:if>
									 			</c:forEach>
											 		<a class="text-decoration-style" onclick="user_comments('${cp.getNews_id()}',1)">
											 			<c:if test="${CommentsCount>0 }">
											 				<img src="resources/assets/images/comments.png" height="30px" width="30px" id="${cp.getNews_id()}_${activity_type}_comments">
											 			</c:if>
											 			<c:if test="${CommentsCount==0 }">
											 				<img src="resources/assets/images/comment-empty.png" height="30px" width="30px" id="${cp.getNews_id()}_${activity_type}_comments">
											 			</c:if>
													</a><span id="${cp.getNews_id()}_${activity_type}_comments_count">${CommentsCount}</span>
											</c:if>
											
												<%-- <img src="resources/assets/images/liked.png" onclick="like('${en.getNews_id()}','0')" height="35px" width="35px"> 
												<img src="resources/assets/images/comment-empty.png" height="35px" width="35px"> --%>
										</div>
								</div>
								</c:if>
								<c:set var="cardl" value="${cardl+2}"></c:set>
							</c:if>
						</c:forEach>
						</div>
						</c:forEach>
					
						</div>
						
						<!-- ------------------------Next previous button---------------------------------- -->
							<c:if test="${total_news_post>limit}">
							<div class="well whiteWell calendarWell">
								<div class="row">
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
												<input type="hidden" name="activate_profile_tab" value="${tab}">
												<c:set var="individual_btn_offset" value="0" />
												<c:if test="${individual_btn_offset!=offset }">
													<button class="prev_next_btn" type="submit"
														name="btn_previous" id="btn_previous" value="${offset}">Previous</button>
												</c:if>
												<c:forEach begin="1" end="${btn_count}" var="b">
													<c:if test="${individual_btn_offset==offset}">
														<button type="submit" name="btn_offset"
															class="active_page" value="${individual_btn_offset}">${b}
														</button>
													</c:if>
													<c:if test="${individual_btn_offset!=offset}">
														<button type="submit" name="btn_offset"
															class="passive_page" value="${individual_btn_offset}">${b}
														</button>
													</c:if>
													<c:set var="individual_btn_offset"
														value="${individual_btn_offset+limit}" />
												</c:forEach>
												<c:if test="${(individual_btn_offset-limit)!=offset}">
													<button class="prev_next_btn" type="submit" name="btn_next" id="btn_next"
														value="${offset}">Next</button>
												</c:if>
											</c:if>
										</div>
										<br /> <br />
									</div>
								</div>
								</div>
							</c:if>
							</c:if>
					
			</div>
			<div class="col-sm-2 sidebar" style="padding-left:0">
		    <c:if test="${celebrity_is_movie}">
		    <c:forEach var="m" items="${movieCast}">
		    <div class="col-sm-12 celebrityoftheday">
					<div align="center">
						 ${m.getCaracter_name()}
					</div>
				</div>
					<div class="col-sm-12 celebrity_div">
						<div class="col-sm-12 celebrity-may-know">
							<div class="col-sm-12 left-right-zero">
							<a href="#" onclick="smothScrolling('${m.getCelebrity_id()}')">
								<img src="${m.getProfile_photo_path()}" width="100%"></a>
							</div>
						</div>
						<div class="col-sm-12 name-like">
							<div class="col-sm-12 left-right-zero padding-bottom-prop" style="padding-top: 5px;">
							<a href="#" onclick="smothScrolling('${m.getCelebrity_id()}')">
								<span class="celebrity_name_may">${m.getCelebrity_name()} </span></a><br />
								${m.getFollower_count()} followers <br>
								<c:if test="${m.getFollower_id()==user_id}">
									<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
									<!-- <input type="button" class="btn btn-follow-empty" value="Following"> -->
								<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getId()}','celebrity-profile')"><button class="btn-follow">Unfollow</button></a>
								</c:if>
								<c:if test="${m.getFollower_id()!=user_id}">
									<input type="button" class="btn-follow" value="Follow" onclick="follow('${c.getCelebrity_id()}',1)">
								</c:if>
							</div>
							
						</div>
					</div>
				</c:forEach>
			</c:if>
			
			<c:if test="${not empty specific_actor_movies}">
				<div class="col-sm-12 celebrityoftheday">
					<div align="center">
						 Movies
					</div>
				</div>
				
				<c:forEach var="c" items="${specific_actor_movies}">
					<div class="col-sm-12 celebrity_div">
						<div class="col-sm-12 celebrity-may-know">
							<div class="col-sm-12 left-right-zero">
							<a href="#" onclick="ShowCelebrity_profile('${c.getCelebrity_id()}')">
								<img src="${c.getProfile_photo_path()}" width="100%"></a>
							</div>
						</div>
						<div class="col-sm-12 name-like">
							<div class="col-sm-12 left-right-zero padding-bottom-prop" style="padding-top: 5px;">
							<a href="#" onclick="ShowCelebrity_profile('${c.getCelebrity_id()}')">
								<span class="celebrity_name_may">${c.getCelebrity_name()} </span></a><br />
								${c.getFollower_count()} followers <br>
								<c:if test="${c.getFollower_id()==user_id}">
									<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
									<!-- <input type="button" class="btn btn-follow-empty" value="Following"> -->
								<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getId()}','celebrity-profile')"><button class="btn-follow">Unfollow</button></a>
								</c:if>
								<c:if test="${c.getFollower_id()!=user_id}">
									<input type="button" class="btn-follow" value="Follow" onclick="follow('${c.getCelebrity_id()}',1)">
								</c:if>
							</div>
						
						</div>
					</div>
				</c:forEach>
			</c:if>
			
				<div class="col-sm-12 celebrityoftheday">
					<div align="center">
						 Suggested Celebrities
					</div>
				</div>
				
				<c:forEach var="c" items="${celebrity_may_know}">
					<div class="col-sm-12 celebrity_div">
						<div class="col-sm-12 celebrity-may-know">
							<div class="col-sm-12 left-right-zero">
							<a href="#" onclick="ShowCelebrity_profile('${c.getCelebrity_id()}')">
								<img src="${c.getProfile_photo_path()}" width="100%"></a>
							</div>
						</div>
						<div class="col-sm-12 name-like">
							<div class="col-sm-12 left-right-zero padding-bottom-prop" style="padding-top: 5px;">
							<a href="#" onclick="ShowCelebrity_profile('${c.getCelebrity_id()}')">
								<span class="celebrity_name_may">${c.getCelebrity_name()} </span></a><br />
								${c.getFollower_count()} followers <br>
									<c:if test="${c.getFollower_id()==user_id}">
									<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
									<!-- <input type="button" class="btn btn-follow-empty" value="Following"> -->
								<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${c.getId()}','celebrity-profile')"><button class="btn-follow">Unfollow</button></a>
								</c:if>
								<c:if test="${c.getFollower_id()!=user_id}">
									<input type="button" class="btn-follow" value="Follow" onclick="follow('${c.getCelebrity_id()}',1)">
								</c:if>
							</div>
							
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div id="facebook_post" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-body">
			      		<div class="row">
			      			<div class="col-sm-12">
								<div class="col-sm-6"> 
									<img id="post_icon"  height="30px" width="30px">
									<span id="pub_date" style="padding-top: 4px;" class="profile-date-style"></span>
								</div>
								<div class="col-sm-6" style="padding-right: 0px;"> 
									<!-- <span class="pull-right" id="pub_date" style="padding-top: 4px;"> </span> -->
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span></button>
						          
								</div>
							</div>
							<div class="col-sm-12" style="border: 1px solid #f2f2f2; margin-top: 10px;"> </div>
							<div class="col-sm-12">
								<label id="lbl_description"></label>
 							</div>
 							<div class="col-sm-12" id="post-img-div">
 								<img id="post-img" width="100%">
 							</div>
			      		</div>
			      </div>
			      <!-- <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div> -->
			    </div>
			
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
			      	<!-- <form method="post" id="follow-celebrity"> -->
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
					      		<button type="button" onclick="follow('','')" class="btn-follow">Submit</button>
					      	</div>
				      	</div>
				    <!-- </form> -->
			      	</div>
				</div>
			</div>
		</div> 
		</div>
		</form>
		
		<div id="modal_comments" class="modal" role="dialog">
		 	<div class="modal-dialog modal-lg">
				<div class="modal-content">
			      <div class="modal-header my-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Add Comments</h4>
			      </div>
			      <div class="modal-body">
			      	<form action="user-comments" method="post">
			      		<div class="row top-bottom-one">
			      		<div class="col-md-12">
			      		<div id="commentsErr" style="color:red"></div>
			      		</div>
			      			<div class="col-sm-12 top-bottom-one">
			      				<textarea class="form-control write-comments" rows="5" name="comments" id="comments" placeholder="Add Comments"></textarea>
			      			</div>
			      			<div class="col-sm-12">
			      				<div class="col-sm-12 all-comments">
			      					<label> All Comments </label>
			      				</div>
			      				<div class="col-sm-12 comments-details" id="comments-details">
			      					<c:forEach var="cm" items="${news_comments}" >
			      					<div class="col-sm-12 single-comments" id="single-comments">
				      					<div class="col-sm-4 left-right-zero" id="UserName">
				      						<label>${cm.getName()} </label>
				      					</div>
				      					<div class="col-sm-7 left-right-zero" id="comment">
				      						${cm.getComments()}
				      					</div>
				      					<div class="col-sm-1 left-right-zero" id="delete_btn">
				      						<c:if test="${cm.getUser_id()==user_id}">
						      						<a class="text-decoration-style" onclick="delete_comment('${cm.getComment_id()}')">Delete </a>
						      					</c:if>
				      					</div>
				      				</div>
			      					</c:forEach>
			      				</div>
			      			</div>
			      			
			      			
			      			<div class="col-sm-12 top-bottom-one">
			      				<input type="button" class="btn-follow" value="Post" onclick="user_comments('','');">
			      			</div>
			      		</div>
			      		</form>
			      </div>
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
 <script src="resources/assets/js/ScrollJs.js"></script>
<script>
$( document ).ready(function() {
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	
	$("#"+'${tab}').addClass("active");
	
	 if('${show_news}'!='')
		 {
		 /* alert("header height....."+$('.header-style').height());
		 alert("div height....."+$("#"+'${show_news}').height()); */
		 var url = document.location.href+"#"+'${show_news}';
	     document.location = url;
	     /* $("#"+'${show_news}').css('font-weight','bold'); */
	     $("#"+'${show_news}'+'_post_title').css('background-color','#b8fcb8');
	     //$("#"+'${show_news}').css('margin-top','200px');
	     /* $('html, body').animate({
	          scrollTop: $("#"+'${show_news}').offset().top-200
	     }, 1000); */
	     
	     //$("#"+'${show_news}').css('display','block');
	     //$("#"+'${show_news}').css('content','" "');
	     //$("#"+'${show_news}').css('height','182.844px');
	     //$("#"+'${show_news}').css('margin-top','-182.844px');
	     //$("#"+'${show_news}').css('visibility','hidden');
	     $(window).scrollTop($("#"+'${show_news}').offset().top-200);
		 } 
	 
	  if('${show_comments}'!='')
	 {
	 	$("#modal_comments").modal("show");
	 } 
	 
});

 $(window).scroll(function() {    
    var scroll = $(window).scrollTop();
    if('${show_news}'!='')
	 {
	    if (scroll > $("#"+'${show_news}').position.top ) {
	    	$("#"+'${show_news}').css('margin-top','0px');
	    }
	 }
}); 

$("#see_more").click(function(){
	var text= $("#see_more").text();
	if(text=='See More')
		{
			$("#see_more").text("See Less");
			$("#show_comments").removeClass("show-less");
			$("#show_comments").addClass("show-more");
			//$("#show_comments").attr("style","background-color: #f2f2f2;padding-left: 0px;padding-right: 0px;height: 170px; overflow-y: auto;");
		}
	else if(text=="See Less")
		{
			$("#see_more").text("See More");
			$("#show_comments").removeClass("show-more");
			$("#show_comments").addClass("show-less");
			//$("#show_comments").attr("style","background-color: #f2f2f2;padding-left: 0px;padding-right: 0px;");
		}
		 
	 
 });
function like(news_id,flag,activity_type_id,news_source) //don't remove this function from here, activity_type_id== news(1)/facebook post(2)/ instagram post(3)
{
	 if('${user_id}'=="")
		 {
		 	$("#modal_login").modal('show');
		 	return false;
		 }
	 else
		 {
		 var tab_id="celebrity_profile";
		 var subtab_id=$(".active").attr("id");
		 tab_id=tab_id+","+subtab_id;
		 fun_FinalLike(news_id,flag,tab_id,activity_type_id,news_source,'${user_id}');
		 }
}

var NEWS_ID=0,ACTIVITY_ID=0;
function user_comments(news_id,activity_type_id)
{
	if('${user_id}'=="")
	 {
	 	$("#modal_login").modal('show');
	 	return false;
	 }
	else
	 {
		var tab_id="celebrity_profile";
	 	var subtab_id=$(".active").attr("id");
	 	tab_id=tab_id+","+subtab_id;
		 
	 	var image="resources/assets/images/comments.png";
		 var comments=$("#comments").val();
			if(news_id==null || news_id=='')
			{
				if($("#comments").val()==null || $("#comments").val()=="")
				{
					document.getElementById("commentsErr").innerHTML = "Please enter comments";
					return false;
				}
			}
		 if(news_id!='')
		 {
			 NEWS_ID=news_id;
			 ACTIVITY_ID=activity_type_id;
			 
		 }
		
			$.ajax({
				url:"comments",
				data:{
						comment_newsid:NEWS_ID,
						tab_id:tab_id,
						activity_type_id:ACTIVITY_ID,
						comments:comments
					},
				type:"POST",
				success:function(result){
					var data=JSON.parse(result);
					$("#comments-details").empty();
					for(var i=0;i<data.comments.length;i++)
						{
						$("#comments-details").append(
								'<div class="col-sm-12 single-comments" id="single-comments">'+
									'<div class="col-sm-4 left-right-zero" id="UserName">'+
		      							'<label>'+data.comments[i].user_name+'</label>'+
			      					'</div>'+
			      					'<div class="col-sm-7 left-right-zero" id="comment">'+
			      						''+data.comments[i].comments_details+
			      					'</div>'+
			      					'<div class="col-sm-1 left-right-zero" id="delete_btn_'+i+'">'+
			      					'</div>'+
		      					'</div>'
								);
						$("#single_comments").append("<div class='col-sm-1'></div>");
						if('${user_id}'==data.comments[i].user_id)
							{
							$("#delete_btn_"+i).append(
		  						'<a class="text-decoration-style" onclick=delete_comment('+data.comments[i].comment_id+','+NEWS_ID+','+ACTIVITY_ID+')>Delete</a>'
		      					);
							} 
						$("#"+NEWS_ID+"_"+ACTIVITY_ID+"_"+"comments_count").text(data.comments[i].comment_count);
	
						}
					if($("#comments").val()!=null && $("#comments").val()!='')
					{
					$("#"+NEWS_ID+"_"+ACTIVITY_ID+"_"+"comments").attr("src",image);
					}
				
				
				$("#comments").val("");
				document.getElementById("commentsErr").innerHTML = "";
					$("#modal_comments").modal("show");
				},
				error:function(){}
			});
		
		
		 	/* var tab_id="celebrity_profile";
		 	var subtab_id=$(".active").attr("id");
		 	tab_id=tab_id+","+subtab_id;
		 	fun_FinalComments(news_id,tab_id); */
	 }
} 

var ENTITY_ID="",ENTITY_TYPE_ID="";

function follow(entity_id,entity_type_id)
{
	 if('${user_id}'=="")
	 {
	 	$("#modal_login").modal('show');
	 	return false;
	 }
else
	 {
		/* tab_id="celebrity-profile";
		fun_FinalFollow(entity_id,entity_type_id,tab_id); */
		if(entity_id!="")
		 {
		 ENTITY_ID=entity_id;
		 ENTITY_TYPE_ID=entity_type_id;
		 /* $("#newsLetterConfirmationModal").modal('show'); */
		 }
	 /* else{ */
		 
		 var tab_id="celebrity-profile";
		 /* var news_letter_type=$('input[name="news_letter_type"]:checked').val(); */
		 fun_FinalFollow(ENTITY_ID,ENTITY_TYPE_ID,tab_id);
		 ENTITY_ID=0;
		 ENTITY_TYPE_ID=0;
	/*  } */
	 }
}
function delete_comment(comment_id,news_id,activity_type_id)
{
	 	var tab_id="celebrity-profile";; 
	 	var subtab_id=$(".active").attr("id");
	 	tab_id=tab_id+","+subtab_id;
		/* document.forms[0].action="user-comments?comment_id="+comment_id+"&tab_id="+tab_id;
	 	document.forms[0].method="POST";
	 	document.forms[0].submit(); */
	 	
	 	var image="resources/assets/images/comment-empty.png";
		 if(news_id!='')
		 {
			 NEWS_ID=news_id;
			 ACTIVITY_ID=activity_type_id;
			 
		 }
		 
		 	$.ajax({
				url:"comments",
				data:{
						comment_newsid:NEWS_ID,
						activity_type_id:ACTIVITY_ID,
						comment_id:comment_id
					},
				type:"POST",
				success:function(result){
					var data=JSON.parse(result);
					$("#comments-details").empty();
					if(data.comments.length==0)
					{
					$("#"+NEWS_ID+"_"+ACTIVITY_ID+"_"+"comments_count").text(0);
					$("#"+NEWS_ID+"_"+ACTIVITY_ID+"_"+"comments").attr("src",image);
				
					}
					for(var i=0;i<data.comments.length;i++)
						{
						$("#comments-details").append(
								'<div class="col-sm-12 single-comments" id="single-comments">'+
									'<div class="col-sm-4 left-right-zero" id="UserName">'+
		      							'<label>'+data.comments[i].user_name+'</label>'+
			      					'</div>'+
			      					'<div class="col-sm-7 left-right-zero" id="comment">'+
			      						''+data.comments[i].comments_details+
			      					'</div>'+
			      					'<div class="col-sm-1 left-right-zero" id="delete_btn_'+i+'">'+
			      					'</div>'+
		      					'</div>'
								);
						$("#single_comments").append("<div class='col-sm-1'></div>");
						if('${user_id}'==data.comments[i].user_id)
							{
							$("#delete_btn_"+i).append(
		  						'<a class="text-decoration-style" onclick=delete_comment('+data.comments[i].comment_id+','+NEWS_ID+','+ACTIVITY_ID+')>Delete</a>'
		      					);
							}
						$("#"+NEWS_ID+"_"+ACTIVITY_ID+"_"+"comments_count").text(data.comments[i].comment_count);
						}
					
					if(data.comments.length>0)
					{
					 	image="resources/assets/images/comments.png";
						$("#"+NEWS_ID+"_"+ACTIVITY_ID+"_"+"comments").attr("src",image);
					}
					
					
				$("#comments").val("");
				document.getElementById("commentsErr").innerHTML = "";
				$("#modal_comments").modal("show");
				},
				error:function(){}
			});
}

/* function activateTabs(activeTab)
{
    
	$.ajax({
		type: "POST",
		url:'pagination-active-tab',
		data:'activeTab='+activeTab,
		success:function(res){
		},
		error:function(){
			alert("Somthing wrong");
		}
	}); 
} */
function validateComments()
{
	if($("#comments").val()==null || $("#comments").val()=="")
	{
		document.getElementById("commentsErr").innerHTML = "Please enter comments";
		return false;
	} 
	
	return true;
}

/* $(function(){
	 if($(window).width() >= 768){
	$('.sidebar').sticky('.main-div'); // Initialize the sticky scrolling on an item
	 }
	$(window).scroll(function() {
		   if($(window).scrollTop() + $(window).height() == $(document).height() && $(window).width() >= 768) {
		     //  $('.sidebar').css({'position':'relative','left':0});
               $('.sidebar').css({'height':$(window).height() - ($('.header-style').height()+100)});

		   }
		});
});

(function($, window, undefined) {

 "use strict"; */

 /**
  * Sticky Element constructor
  * @param elm
  * @param par
  * @param options
  * @constructor
  */
 /* var Sticky = function(elm, par, options) {
     this.element = elm;
     this.parent = par;
     this._frozen = false;
     this.options = $.extend({
         animate: false,
         useFixed: true,
         animTime: 300
     }, options);
     this.init();
 };

 Sticky.prototype.init = function() {
     this.element.addClass("sticky-scroll");
     this.update();
 };

 Sticky.prototype.update = function() {
     //This will handle any resizing of the container the sticky scroll is in and update the boundaries if necessary
     this.setBoundaries();
     this.moveIt();
 };

 Sticky.prototype.moveIt = function() {
     // This will decide whether to move the stickied item
     var scrollTop = $(window).scrollTop();
     var height = this.element.outerHeight(true);
     var realStop = this._stop - height;

     if (this._parentHeight - this._offset > height && !this._frozen) {
         if (scrollTop >= this._start && scrollTop <= realStop) {
        	// alert($(window).height()-200);
//alert($(window).height() - ($('.header-style').height()+35));
             if(this.options.useFixed){
                 this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'auto','height':$(window).height() - ($('.header-style').height()+35),'overflow-x':'hidden'});
             } else {
                // this.updateOffset(scrollTop - this._start);
             }
         } else {
             this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'auto','height':$(window).height() - ($('.header-style').height()+35),'overflow-x':'hidden'}); */
           /*   if (scrollTop < this._start) {
               //  this.updateOffset(0);
             } else if (scrollTop > realStop) {
                 this.updateOffset(this._parentHeight - height - this._offset);
             } */
        /*  }
     }
 };

 Sticky.prototype.setBoundaries = function() {
     // This will set the boundaries the stickied item can move between and it's left position
     this._offset = this.element.position().top;
     this._start = this.parent.offset().top + this._offset;
     this._parentHeight = this.parent.outerHeight();
     this._stop = this._start + this._parentHeight - this._offset;
 }; */

 /**
  * Update Stickied Element's offset
  * @param yOffset
  */
 /* Sticky.prototype.updateOffset = function(yOffset) {
     if (!this._lastPosition !== yOffset) {
         // This moves the item
         if (this.animate) {
             this.element.stop().animate({
                 'top': yOffset
             }, this.animTime);
         } else {
             this.element.css('top', yOffset);
         }
         this._lastPosition = yOffset;
     }
 };

 Sticky.prototype.toggleFreeze = function() {
     // This will freeze the stickied item in place wherever it is
     this._frozen = !this._frozen;
 };

 $.fn.sticky = function(par, options) {
     var method, args, ret = false;
     if (typeof options === "string") {
         args = [].slice.call(arguments, 0);
     }

     this.each(function() {
         var self = $(this);
         var parent = par;
         if (parent) {
             parent = self.parent().closest(parent);
         } else {
             parent = self.parent();
         }
         parent.css({'position':'relative'}); // Set parent position to relative
         self.css({'position':'relative'}); // Set item position to relative
         var instance = self.data("stickyInstance");

         if (instance && options) {
             if (typeof options === "object") {
                 ret = $.extend(instance.options, options);
             } else if (options === "options") {
                 ret = instance.options;
             } else if (typeof instance[options] === "function") {
                 ret = instance[options].apply(instance, args.slice(1));
             } else {
                 throw new Error("Sticky Element has no option/method named " + method);
             }
         } else {
             instance = new Sticky(self, parent, options || {});
             self.data("stickyInstance", instance);
             $.fn.sticky._instances.push(instance);
         }
     });
     return ret || this;
 };

 $.fn.sticky._instances = [];

 $(window).on({
     'resize': function(e) {
         // Update the position/offset changed on resize and move
         $.each($.fn.sticky._instances, function() {
             this.update();
         });
     },
     'scroll': function() {
         // Move all those suckers on scroll
         $.each($.fn.sticky._instances, function() {
             if (!this._frozen) {
                 this.moveIt();
             }
         });
     }
 });
}(jQuery, window)); */

function smothScrolling(celebrity_id)
{
	$('html, body').animate({
        scrollTop: $("#celebrity_"+celebrity_id).offset().top-200
   }, 800);
}
</script>
</body>
</html>