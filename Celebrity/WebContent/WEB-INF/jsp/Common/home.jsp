<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
  <title>Home</title>
  <meta charset="UTF-8">
  <meta name="description" content="Search information of US celebrities">
  <meta name="keywords" content="most popular celebrities in us, celebrities in us, musicians in us, athlets in us, us movies, us movie blogs, posts of us celebrities">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
 <link rel="stylesheet" href="resources/assets/css/style.css">
    
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div id="my_page_container" class="container left-right-zero content-div page-padding-top my_container_height"> <!-- style="overflow-x:scroll;overflow-y:scroll" -->
		<div class="col-sm-12">
				<div id="main_page" class="col-sm-12" style="padding: 1% 0;">				
				  	<div id="news-section" class="row main">
				  <div id="actual-news" class="col-sm-8">
				  	<c:if test="${not empty error_message}">
						<div class="col-sm-12">
							<div class="alert alert-danger">
							 	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								${error_message}
							</div>
						</div>
					</c:if>
					<c:if test="${not empty message}">
						<div class="col-sm-12">
							<div class="alert alert-success">
							 	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								${message}
							</div>
						</div>
					</c:if>
						  		 <div id="myCarousel" class="carousel slide" data-ride="carousel">
								    
								    <div class="carousel-inner">
								    <c:set var="acc_class" value="active"></c:set>
								    <c:set var="flag" value="0"></c:set>
									<c:forEach items="${trending_news}" var="t" varStatus="i">
											<c:if test="${flag==0}">
												<c:set var="acc_class" value="active"></c:set>
											</c:if>
									      	<c:if test="${flag!=0}">
												<c:set var="acc_class" value="active1"></c:set>
											</c:if>
											
										      <div class="item ${acc_class}">
										      	<div class="col-sm-3 slider-img">
										      	
										      	<img src="${t.getProfile_photo_path()}" alt="${t.getCelebrity_name()}" style="width:100%;">
										      	</div>
										      	<div class="col-sm-9">
										      		<!-- <div class="box-footer"> -->
											         <div style="margin-left: 10px;margin-top: 10px;">
											         <c:set var="title_text" value="${t.getNews_title()}"></c:set>
											          <c:set var="descr_text" value="${t.getNews_description()}"></c:set>
											          <%
											          String more_title="";
											          	String title_text=(String)pageContext.getAttribute("title_text");
											          title_text=title_text.replace("?","");
											          /* title_text=title_text.replace("\'","\\'"); */
											       /*    if(title_text.length()>100)
														{
											        	  title_text=title_text.substring(0, 200);
														} */
														more_title = (String)pageContext.getAttribute("descr_text");   //No exception.
														/* more_title=more_title.replace("\'", "\\'"); */
														more_title=more_title.replace("?", "");
														if(more_title.length()>180)
														{
															more_title=more_title.substring(0, 180);
															more_title=more_title+"...";
														}
											          %>
											          <div class="slider-text">
												          <a class="slider-text break-word-style" href="#" onclick="ReadMore('${t.getEntity_id()}','${t.getNews_id()}')"> <%=title_text%> <br /></a><br />
												 
											          </div>
											        
												          <a class="description-style break-word-style" href="#" onclick="ReadMore('${t.getEntity_id()}','${t.getNews_id()}')">
															<%-- <span>${fn:substring(t.getNews_description(), 0,100)}...</span><br /> --%>
															<span><%=more_title%></span><br />
															</a>
															<button class="btn-follow" onclick="ReadMore('${t.getEntity_id()}','${t.getNews_id()}')">Read more</button>
														
											          </div>
											        <!-- </div> -->
										      	</div>
										      </div>
										<c:set var="flag" value="${flag+1}"></c:set>
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
								  </div> <!-- end-->
								   </div> 
							  <div class="col-sm-4">
							  	<div class="col-sm-12 celebrityoftheday">
				  			<div align="center"><span> Celebrity of the day </span></div> 
				  		</div>
				  		<c:forEach var="en" items="${celebrity_ofthe_day}" varStatus="i">
					  			<div class="col-sm-12 box">
					  				<div class="col-sm-5 slider-img">
					  			<a href="#" onclick="ShowCelebrity_profile('${en.getCelebrity_id()}')">
					              <img style="width: 100%" src="${en.getProfile_photo_path()}" alt="${en.getCelebrity_name()}">
					            </a>
</div>
					            <div class="col-sm-7"style="padding-left:0">
					             
					                <div class="col-sm-12 border-right">
					                  <div class="description-block">
					                  <a href="#" onclick="ShowCelebrity_profile('${en.getCelebrity_id()}')">
					                    <h4 class="description-header">${en.getCelebrity_name()}</h4></a>
					                    <span class="description-text">${en.getFollower_count()} Followers</span>
					                  </div>
					                  <div class="category-text">
										<c:forEach items="${celebrity_cat}" var="c" varStatus="i">
											${c.getCategory_name()}
											<c:if test="${i.index+1!=fn:length(celebrity_cat)}">,</c:if>
										</c:forEach> 
					                  </div>
					                   <div class="description-block">
					                  	<c:if test="${en.getFollower_id()==user_id}">
					                  		<!-- <img src="resources/assets/images/done.png" height="35px" width="100px"> -->
					                  		<!-- <input type="button" class="btn-follow-empty" value="Following"> -->
					                  		<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${en.getId()}','home-page')"><button class="btn-follow" type="button">Unfollow</button></a>
					                  	</c:if>
					                  	<c:if test="${en.getFollower_id()!=user_id}">
					                   		 <button class="btn-follow btn-follow-margin" onclick="follow('${en.getCelebrity_id()}','1')">Follow</button>
					                   	</c:if>
					                   	 <button class="btn-follow" onclick="ShowCelebrity_profile('${en.getCelebrity_id()}')">View Bio</button>
					                  </div>
					                </div>
					             
					            
					            </div>
					          </div>
				  		</c:forEach>
				  		</div>
							  
							</div>  
							  
							  <div class="col-sm-12 celebrityoftheday moreStories">
				  			<div><span> More Stories </span></div> 
				  		</div>
						  <div class="row">
						  <c:forEach var="x" begin="0" end="3">
						  	<div class="col-sm-3">
							  	<c:forEach var="en" items="${entity_news}" varStatus="i">
							  	<c:if test="${i.index%4==x}">
							  	<!-- activity type flag -->
							  <%-- 	<c:set var="activity_type" value="1"></c:set>
												<c:if test="${en.getNews_source()=='Facebook'}">
														<c:set var="activity_type" value="${activity_type+1}"></c:set>
												</c:if>
												<c:if test="${en.getNews_source()=='Instagram'}">
														<c:set var="activity_type" value="${activity_type+1}"></c:set>
												</c:if>
												<c:if test="${en.getNews_source()=='Twitter'}">
														<c:set var="activity_type" value="${activity_type+1}"></c:set>
												</c:if> --%>
												
							  	
							  		<div class="row card-news">        
					            		<div class="inner-box">
					            		<c:if test="${not empty en.getImage_url() && en.getImage_url()!='null' && en.getImage_url()!='' && en.getImage_url()!='undefined' && en.getImage_url()!='unknown'}">
										      	<a href="#" onclick="ReadMore('${en.getEntity_id()}','${en.getNews_id()}')"> 
						            			<img style="width: 100%" src="${en.getImage_url()}" alt="${en.getCelebrity_name()}">
						            		</a>
						            		
										      	</c:if>
										      	<%-- 	<c:if test="${empty en.getImage_url() || en.getImage_url()=='null' || en.getImage_url()=='' || en.getImage_url()=='undefined' || en.getImage_url()=='unknown'}">
										      	<a href="#" onclick="ReadMore('${en.getEntity_id()}','${en.getNews_id()}')"> 
						            			<img style="width: 100%" src="${en.getProfile_photo_path()}" alt="${en.getCelebrity_name()}">
						            		</a>
						            		 	
										      	</c:if> --%>
						            		
								            <div class="box-footer">
								                  <c:set var="title_text" value="${en.getNews_title()}"></c:set>
								                      <%
											          
											          String title_text=(String)pageContext.getAttribute("title_text");
											          title_text=title_text.replace("?","");
											          /* title_text=title_text.replace("\'","\\'"); */
											          
											          %>
									          <a href="#" onclick="ReadMore('${en.getEntity_id()}','${en.getNews_id()}')">
									          		 <span class="newstitle"><%=title_text%> </span>
									          </a>
								            
								              <div class="like-comment">
								              <c:if test="${empty user_id}">
								              		<a href="#" data-toggle="modal" data-target="#modal_login" class="text-decoration-style">
								              		<img src="resources/assets/images/like.png" height="35px" width="35px" id="${en.getNews_id()}_${activity_type}_img"> </a>
								              		<c:set var="like_count" value="0" />
										 			<c:forEach var="lc" items="${like_cout}">
										 				<c:if test="${lc.getPost_id_or_news_id()==en.getNews_id()}">
										 					<c:set var="like_count" value="${lc.getLike_count()}" />
										 				</c:if>
										 			</c:forEach>
										 				<span id="${en.getNews_id()}_${activity_type}_likes">${like_count}</span>
										 				
										 				
								              		<a href="#" data-toggle="modal" data-target="#modal_login" class="text-decoration-style">
								              		<img src="resources/assets/images/comment-empty.png" height="35px" width="35px"></a>
								              		<c:set var="commentcount" value="0"></c:set>
										 			<c:forEach var="cc" items="${comment_cout}">
										 				<c:if test="${cc.getPost_id_or_news_id()==en.getNews_id()}">
										 					<c:set var="commentcount" value="${cc.getComment_count()}"></c:set>
										 				</c:if>
										 			</c:forEach>
										 			<span>${commentcount}</span>
								              </c:if>
								              <c:if test="${not empty user_id}">
										 			<c:if test="${en.getFollower_id()==user_id}">
										 				<!-- <input type="button" class="btn btn-follow-empty" value="Following"> -->
										 				<!-- <img src="resources/assets/images/done.png" height="30px" width="100px"> -->
										                <a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${en.getId()}','home-page')"><button class="btn-follow" type="button">Unfollow</button></a>
										 			</c:if>
										 			<c:if test="${en.getFollower_id()!=user_id}">
										 				<button type="button" class="btn-follow" onclick="follow('${en.getEntity_id()}','${en.getEntity_type_id()}')">Follow</button>
										 			</c:if>
										 		 	<c:if test="${user_id==en.getUser_id()}">
										 				<a class="text-decoration-style"><img src="resources/assets/images/liked.png" onclick="like('${en.getNews_id()}','0','1','${en.getNews_source()}')" height="35px" width="35px" id="${en.getNews_id()}_${activity_type}_img"> </a>
										 			</c:if>
										 			<c:if test="${user_id!=en.getUser_id()}">
										 				<a class="text-decoration-style"><img src="resources/assets/images/like.png" onclick="like('${en.getNews_id()}','1','1','${en.getNews_source()}')" height="35px" width="35px" id="${en.getNews_id()}_${activity_type}_img"> </a>
										 			</c:if> 
										 			<c:set var="like_count" value="0" />
										 			<c:forEach var="lc" items="${like_cout}">
										 				<c:if test="${lc.getPost_id_or_news_id()==en.getNews_id()}">
										 					<c:set var="like_count" value="${lc.getLike_count()}" />
										 				</c:if>
										 			</c:forEach>
										 				<span id="${en.getNews_id()}_${activity_type}_likes">${like_count}</span>
										 			
										 			
										 			<c:set var="commentcount" value="0"></c:set>
										 			<c:forEach var="cc" items="${comment_cout}">
										 				<c:if test="${cc.getPost_id_or_news_id()==en.getNews_id()}">
										 					<c:set var="commentcount" value="${cc.getComment_count()}"></c:set>
										 					<c:set var="activity_type_id" value="${cc.getActivity_type_id()}"></c:set>
										 				</c:if>
										 			</c:forEach>
										 			<a class="text-decoration-style"  onclick="user_comments('${en.getNews_id()}','1')"> 
										 				<c:if test="${commentcount==0}">
										 					<img src="resources/assets/images/comment-empty.png" height="35px" width="35px" id="${en.getNews_id()}_${activity_type}_comments">
										 				</c:if>
										 				<c:if test="${commentcount>0}">
										 					<img src="resources/assets/images/comments.png" height="35px" width="34px" id="${en.getNews_id()}_${activity_type}_comments">
										 				</c:if> 
										 			</a>
										 			<span id="${en.getNews_id()}_${activity_type}_comments_count">${commentcount}</span>
										 			</c:if>
										 		</div>
								            </div>
								         </div>
								    </div>
								    </c:if>
								  </c:forEach>
						 	</div>
						 	</c:forEach>
						 	
						 </div>
						
				  
				  
				  
				</div>
		</div>
		
<!-- ------------------Registration ------------------------------- -->	
		
<!-- ------------------Login Model  
 -------------------------------- -->	
		

		<!--  Comment Model  -->
		<div id="modal_comments" class="modal" role="dialog">
		 	<div class="modal-dialog modal-lg">
				<div class="modal-content">
			      <div class="modal-header my-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Comments</h4>
			      </div>
			      <div class="modal-body">
			      	<form action="user-comments" method="post">
			      		<div class="row top-bottom-one">
			      		<div class="col-sm-12">
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
			      					<%-- <c:forEach var="cm" items="${news_comments}" > --%>
			      					<div class="col-sm-12 single-comments" id="single-comments">
				      					<div class="col-sm-4 left-right-zero" id="UserName">
				      						<label></label>
				      					</div>
				      					<div class="col-sm-7 left-right-zero" id="comment">
				      					</div>
				      					<div class="col-sm-1 left-right-zero" id="delete_btn">
				      						
				      					</div>
				      				</div>
			      					<%-- </c:forEach> --%>
			      				</div>
			      			</div>
			      			
			      			
			      			<div class="col-sm-12 top-bottom-one">
			      				<input type="button" class="btn-follow" value="Post" onclick="user_comments('','')">
			      			</div>
			      		</div>
			      		</form>
			      </div>
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
					      		<button onclick="follow('','')" class="btn-follow">Submit</button>
					      	</div>
				      	</div>
				    <!-- </form> -->
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
	
 <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="resources/assets/js/style.js"></script>
 <script src="resources/assets/js/common.js"></script> -->
 
<!--  <script src="resources/assets/js/ScrollJs.js"></script> -->
 
 <script>
 
  /* jQuery(window).bind('scroll', function () {
	 if ($(window).scrollTop() > $("#ads-section").height()) {
	     $("#blog"+'${last_blog_id}').css('position','fixed');
	     $("#blog"+'${last_blog_id}').css('width','30%');
	     $("#blog"+'${last_blog_id}').css('margin-top','-600px');
	     } else {
	         $("#blog"+'${last_blog_id}').css('position','relative');
	         $("#blog"+'${last_blog_id}').css('width','100%'); 
	         $("#blog"+'${last_blog_id}').css('margin-top','0px');
	     }
	 });  */

 $( document ).ready(function() {
	 var max_height=$("#my_header").height()+$("#footer").height();
		$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
		
	 /* $("#blog"+'${last_blog_id}').addClass("sidebar"); */
	 $("#home").addClass("active");
	 if('${show_comments}'!='')
	 {
	 	$("#modal_comments").modal("show");
	 } 
	 if('${type}'!='')
		 {
		 var t='${type}';
		 	$("#"+t).addClass("active");
		 }
	 if('${show_comments}'!='')
		 {
		 	$("#modal_comments").modal("show");
		 }
	 if('${error_message}'!='')
		 {
		 	$("#alert_message").modal("show");
		 }
	});
 $(".a-flat").click(function(){
		
		document.forms[0].action="home?type="+this.id;
		document.forms[0].method="POST";
		document.forms[0].submit();
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
		 var tab_id=$(".active > .a-flat").attr('id');
		 fun_FinalLike(news_id,flag,tab_id,activity_type_id,news_source,'${user_id}');
			/* document.forms[0].action="user-like?news_id="+news_id+"&like-dislike="+flag+"&tab_id="+tab_id+"&activity_type_id="+activity_type_id;
		 	document.forms[0].method="POST";
		 	document.forms[0].submit(); */
		 }
 }
 
 var NEWS_ID=0,ACTIVITY_ID=0;
 function user_comments(news_id,activity_type_id)
 {
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
	 
	 	var tab_id=$(".active > .a-flat").attr('id');
	 	/* fun_FinalComments(news_id,tab_id); */
	 	$.ajax({
			url:"comments",
			data:{
					comment_newsid:NEWS_ID,
					tab_id:"home",
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
	  						'<a class="text-decoration-style" class="text-decoration-style" onclick=delete_comment('+data.comments[i].comment_id+','+NEWS_ID+','+ACTIVITY_ID+')>Delete</a>'
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
	 /* } */
 } 
 function delete_comment(comment_id,news_id,activity_type_id)
 {
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
	 if(entity_id!="")
		 {
		 ENTITY_ID=entity_id;
		 ENTITY_TYPE_ID=entity_type_id;
		 /* $("#newsLetterConfirmationModal").modal('show'); */
		 }
	 /* else{ */ 
		 
		 var tab_id=$(".active > .a-flat").attr('id');
		 /* var news_letter_type=$('input[name="news_letter_type"]:checked').val(); */
		 fun_FinalFollow(ENTITY_ID,ENTITY_TYPE_ID,tab_id);
	 /* } */
	 }
 }

 /* $(function(){
	 if($(window).width() >= 768){
		$('.sidebar').sticky('#main_page'); // Initialize the sticky scrolling on an item
	 }
		$(window).scroll(function() {
			   if($(window).scrollTop() + $(window).height() == $(document).height() && $(window).width() >= 768) {
			      // $('.sidebar').css({'position':'relative','left':0});
                   $('.sidebar').css({'height':$(window).height() - ($('.header-style').height()+100)});

			   }
			});
	}); */

 /* (function($, window, undefined) {

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
 */
     /* Sticky.prototype.moveIt = function() {
         // This will decide whether to move the stickied item
         var scrollTop = $(window).scrollTop();
         var height = this.element.outerHeight(true);
         var realStop = this._stop - height;

         if (this._parentHeight - this._offset > height && !this._frozen) {
             if (scrollTop >= this._start && scrollTop <= realStop) {
                 if(this.options.useFixed){
                     this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'auto','height':$(window).height() - ($('.header-style').height()+35),'overflow-x':'hidden'});
                //top:215
                 } else {
                    // this.updateOffset(scrollTop - this._start);
                 }
             } else {
            	// alert($(window).height()-120);
                 this.element.css({'position':'fixed','left':this.element.offset().left,'overflow-y':'auto','height':$(window).height() - ($('.header-style').height()+35),'overflow-x':'hidden'});
                 *//*  if (scrollTop < this._start) {
                    // this.updateOffset(0);
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
    /*  Sticky.prototype.updateOffset = function(yOffset) {
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

 </script>
 
 <%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
<!-- <img src="image/1.jpg" height="200" width="200"> -->


