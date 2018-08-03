var NEWS_ID=0, NEWS_SOURCE=0, CELEBRITY_ID=0, USER_ID=0, FOLLOW_ID=0, PAGE_NAME=null;
$(function(){
	 $('.followTitleButton').tooltip(); 
	if($(window).width() <= 768){
		$("#headerImage").attr("src","resources/assets/images/rsbs-header-mobile.jpg");
		$("#headerImage").css({'height':'70%'});
		var accountDiv=$("#accountDiv").html();
		if(accountDiv){
		$("#accountDivMobile").html(accountDiv);
		}
		$("#accountDiv").hide();
		$("#accountDiv1").hide();
	}
});

function readMore(news_id, news_source, postIcon)
{

	$("#read_more_post").text("");
	$("#news-icon").attr("src","");
	$("#news-date").text("");
	$("#read_more_post_img").attr("src","");
	$("#read_more_post_img").show();
	$.ajax({
		type: "POST",
		url:'readmore-function',
		data:'news_id='+news_id+'&news_source='+news_source,
		success:function(res){
			var json = JSON.parse(res);
			$("#read_more_post").html(json.news_title);
			$("#news-icon").attr("src",postIcon);
			$("#news-date").text(json.news_date);
			if(json.news_image!=null && json.news_image!="" && json.news_image!="null" && json.news_image!="undefined")
			{
		    $("#read_more_post_img").attr("src",json.news_image);
			}else{
				  $("#read_more_post_img").hide();
			}
		},
		error:function(){
		}
	});
}

function showProfile(celebrity_id)
{
	document.forms[0].action="show-profile?celebrity_id="+celebrity_id;
	document.forms[0].method="POST";
	document.forms[0].submit();
}

function validateUserProfile()
{
	var first_name=$('#First_name').val();
	var last_name=$("#Last_name").val();
	var contact_no=$("#Contact_no").val();
	var email_id=$("#Email_id").val();
	
	
	if(first_name == null || first_name == '')
		{
		
		$(".errorMsg").text("Please enter First name");
		$("#errorModal").modal('show');
		return false;
		}
	if(!isNaN(first_name))
	{
	$(".errorMsg").text("Please enter valid first name");
	$("#errorModal").modal('show');
	return false;
	}
	if(last_name==null || last_name=="")
	{
	$(".errorMsg").text("Please enter last name");
	$("#errorModal").modal('show');
	return false;
	}
	if(!isNaN(last_name))
	{
	$(".errorMsg").text("Please enter valid last name");
	$("#errorModal").modal('show');
	return false;
	}
	if(contact_no==null || contact_no=="")
	{
	$(".errorMsg").text("Please enter contact no");
	$("#errorModal").modal('show');
	return false;
	}
	if(contact_no.length>10 || contact_no.length<10)
	{
		$(".errorMsg").text("Please enter 10 digit contact no");
		$("#errorModal").modal('show');
		return false;
	
	}
	if(email_id==null || email_id=="")
	{
	$(".errorMsg").text("Please enter email id");
	$("#errorModal").modal('show');
	return false;
	}
	var x = email_id;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
    	$(".errorMsg").text("Please enter valid email id");
    	$("#errorModal").modal('show');
    	return false;
    }
	if(isNaN(contact_no))
	{
	$(".errorMsg").text("Please enter valid contact number");
	$("#errorModal").modal('show');
	return false;
	}
	
	
	
	return true;
}
function validatePassword()
{
	var old_password=$("#Old_password").val();
	var new_password=$("#New_password").val();
	var confirm_password=$("#Confirm_password").val();
	if(old_password==null || old_password=="")
	{
	$(".errorMsg").text("Please enter old password");
	$("#errorModal").modal('show');
	return false;
	}

	/*if(isNaN(old_password))
	{
	$(".errorMsg").text("Please enter valid old password");
	$("#errorModal").modal('show');
	return false;
	}*/
	
	if(new_password==null || new_password=="")
	{
	$(".errorMsg").text("Please enter new password");
	$("#errorModal").modal('show');
	return false;
	}
	
	/*if(isNaN(new_password))
	{
	$(".errorMsg").text("Please enter valid new password");
	$("#errorModal").modal('show');
	return false;
	}*/
	
	if(confirm_password==null || confirm_password=="")
	{
	$(".errorMsg").text("Please enter confirm password");
	$("#errorModal").modal('show');
	return false;
	}
	
	/*if(isNaN(confirm_password))
	{
	$(".errorMsg").text("Please enter valid confirm password");
	$("#errorModal").modal('show');
	return false;
	}*/
	
	if (new_password.length < 8 || new_password.length > 45) {

		$(".errorMsg").text(
				"Password characters should be between 8 and 45 respectively");
		$("#errorModal").modal('show');
		return false;
	}
	if (confirm_password.length < 8 || confirm_password.length > 45) {

		$(".errorMsg").text(
				"Password characters should be between 8 and 45 respectively");
		$("#errorModal").modal('show');
		return false;
	}

	if (new_password != confirm_password) {

		$(".errorMsg").text("Confirm passwords do not match");
		$("#errorModal").modal('show');
		return false;
	}
	
	return true;
}
function activeSideNav(index)
{
	if(index==1)
		{
		document.getElementById("edit_profile").action="settings?activeTab="+index;
		document.getElementById("edit_profile").method="post";
		document.getElementById("edit_profile").submit();
		}
	if(index==2)
	{
	document.getElementById("changePassword").action="change-password?activeTab="+index;
	document.getElementById("changePassword").method="post";
	document.getElementById("changePassword").submit();
	}
	if(index==3)
	{
	document.getElementById("user_follows").action="followers?activeTab="+index;
	document.getElementById("user_follows").method="post";
	document.getElementById("user_follows").submit();
	}
}

function unfollowCelebrity(follow_id,page_name)
{
	if(follow_id!='')
		{
		FOLLOW_ID=follow_id.trim();
		PAGE_NAME=page_name.trim();
		}
	else{
	document.getElementById("user_follows").action="followers?follow_id="+FOLLOW_ID+"&redirectTo="+PAGE_NAME;
	document.getElementById("user_follows").method="post";
	document.getElementById("user_follows").submit();
	}
}
function show_news_tab(news_id,celebrity_id)
{
	document.forms[0].action="show-profile?news_id="+news_id+"&celebrity_id="+celebrity_id;
	document.forms[0].method="post";
	document.forms[0].submit();
}
function showPoliticianProfile(politician_id,news_id,news_source)
{
	politician_id=politician_id.trim();
	news_id=news_id.trim();
	news_source=news_source.trim();
	document.forms[0].action="show-profile?politician_news_id="+news_id+"&celebrity_id="+politician_id+"&news_source="+news_source;
	document.forms[0].method="post";
	document.forms[0].submit();
}
function validateRegistration(index_from)
{
	var first_name=$("#first_name").val();
	var last_name=$("#last_name").val();
	//var contact_no=$("#contact_no").val();
	var email_id=$("#email_id").val();
	if(first_name==null || first_name=="")
		{

		document.getElementById("regiErrName").innerHTML = "Please enter First name";
		return false;
		}
	if(!isNaN(first_name))
	{
	
	document.getElementById("regiErrName").innerHTML = "Please enter valid first name";
	return false;
	}
	if(last_name==null || last_name=="")
	{
	
	document.getElementById("regiErrName").innerHTML = "Please enter last name";
	return false;
	}
	if(!isNaN(last_name))
	{
	
	document.getElementById("regiErrName").innerHTML = "Please enter valid last name";
	return false;
	}
/*	if(contact_no==null || contact_no=="")
	{
	
	document.getElementById("regiErrName").innerHTML = "Please enter contact no";
	return false;
	}
	if(isNaN(contact_no))
	{
	
	document.getElementById("regiErrName").innerHTML = "Please enter valid contact number";
	return false;
	}
	if(contact_no.length>10 || contact_no.length<10)
	{
	
	document.getElementById("regiErrName").innerHTML = "Please enter 10 digit contact no";
	return false;
	}*/
	
	if(email_id==null || email_id=="")
	{
	
	document.getElementById("regiErrName").innerHTML = "Please enter email id";
	return false;
	}
	var x = email_id;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
    	document.getElementById("regiErrName").innerHTML = "Please enter valid email id.";
        return false;
    }

	var new_password=$("#new_password").val();
	var confirm_password=$("#confirm_password").val();
	
	if(new_password==null || new_password=="")
	{
	
	document.getElementById("regiErrName").innerHTML = "Please enter new password";
	return false;
	}
	if(confirm_password==null || confirm_password=="")
	{
		
	document.getElementById("regiErrName").innerHTML = "Please enter confirm password";
	return false;
	}
	if (new_password.length < 8 || new_password.length > 45) {

		document.getElementById("regiErrName").innerHTML = "Password characters should be between 8 and 45 respectively";
		return false;
	}
	if (confirm_password.length < 8 || confirm_password.length > 45) {

		document.getElementById("regiErrName").innerHTML = "Password characters should be between 8 and 45 respectively";
		return false;
	}

	if (new_password != confirm_password) {
		
		document.getElementById("regiErrName").innerHTML = "Confirm passwords do not match.";
		return false;
	}
	
	return true;
}
function loginValidation(index_from)
{	
	var index=index_from;
	$.ajax({
		url:"login-redirection",
		data:"index_from="+index,
		success:function(res){
		},
		error:function(){
		}
	});
}
function userLikes(news_id, news_source, celebrity_id)
{
	news_id=news_id.trim();
	news_source=news_source.trim();
	celebrity_id=celebrity_id.trim();
	image="resources/assets/images/liked.png";
		$.ajax({
			type: "POST",
			url:'user-likes-function',
			data:'news_id='+news_id+'&news_source='+news_source+'&celebrity_id='+celebrity_id,
			success:function(res){
				var json = JSON.parse(res);
				$("#"+news_id+"_"+news_source+"_"+"likes").text(json.like_count);
				$("#"+news_id+"_"+news_source+"_"+"img").attr("src",image);
				$("#"+news_id+"_"+news_source+"_"+"likesAnchor").attr("onclick","userUnLikes('"+json.like_id+"','"+news_id+"','"+news_source+"')");
				
			},
			error:function(){
			}
		}); 
}
function userComments(news_id, news_source, celebrity_id, user_id)
{
	
	var image="resources/assets/images/comments.png";
	news_id=news_id.trim();
	news_source=news_source.trim();
	celebrity_id=celebrity_id.trim();
	user_id=user_id.trim();
	var comments=$("#comment").val();
	if(news_id==null || news_id=='')
	{
		if($("#comment").val()==null || $("#comment").val()=="")
		{
			document.getElementById("commentsErr").innerHTML = "Please enter comments";
			return false;
		}
	}
	
	if(news_id!='')
		{
		NEWS_ID=news_id, 
		NEWS_SOURCE=news_source, 
		CELEBRITY_ID=celebrity_id;
		USER_ID=user_id;
		}
	
	$.ajax({
			
			type: "POST",
			url:'user-comments-function',
			data:'news_id='+NEWS_ID+'&news_source='+NEWS_SOURCE+'&celebrity_id='+CELEBRITY_ID+'&comments='+comments,
			success:function(res){
				var data = JSON.parse(res);
				
				$("#see_all_posts").empty();
				$("#comments_title").text("");
				$("#comments_title").removeClass("comments_title_border");
				if(data.comments_info.length>0)
				{
				$("#see_all_posts").append(
						'<div class="row">'+
						'<div class="col-md-12" align="right">'+
						'<a class="cursor_pointer see_all_link" id="all_posts" onclick="allPosts('+1+')">See all</a>'+
						'</div>'+
						'</div>'	
				);
				$("#comments_title").text("All Comments");
				$("#comments_title").addClass("comments_title_border");
				}
				
				$("#comments-details").empty();
				for(var i=0;i<data.comments_info.length;i++)
				{
					$("#comments-details").append(
						'<div class="row" id="single-comments">'+
						'<div class="col-md-4" id="UserName">'+
						'<label>'+data.comments_info[i].user_name+'</label>'+
						'</div>'+
						'<div class="col-md-6" id="comment">'+
						'<label>'+data.comments_info[i].comments+'</label>'+
						'</div>'+
						'<div class="col-md-1" id="delete_btn_'+i+'">'+
						'</div>'+
						'</div>'
						
					);
					$("#single_comments").append("<div class='col-md-1'></div>");
					if(USER_ID==data.comments_info[i].user_id)
					{
					$("#delete_btn_"+i).append(
					'<a class="news-link-style cursor_pointer" onclick="delete_comments('+data.comments_info[i].comment_id+','+NEWS_ID+',\''+NEWS_SOURCE+'\','+0+')"><img src="resources/assets/images/delete.png" width="30px" height="30px"></a>'
					);
				}
				
				if($("#comment").val()!=null && $("#comment").val()!='')
					{
					$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"ucommentted").attr("src",image);
					}
				$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"comments").text(data.comments_info[i].comments_count);
				$("#comment").val("");
				}
				document.getElementById("commentsErr").innerHTML = "";
			},
			error:function(){
			}
		}); 
}
function delete_comments(delete_comment_id,news_id,news_source,see_all_posts)
{
	
	var image="resources/assets/images/comment-empty.png";
		$.ajax({
			type: "POST",
			url:'user-comments-function',
			data:'delete_comment_id='+delete_comment_id+'&news_id='+news_id+'&news_source='+news_source+'&see_all_posts='+see_all_posts,
			success:function(res){
				var data = JSON.parse(res);
				
				$("#see_all_posts").empty();
				$("#comments_title").text("");
				$("#comments_title").removeClass("comments_title_border");
				if(data.comments_info.length>0)
				{
				/*$("#see_all_posts").append(
						'<div class="row">'+
						'<div class="col-md-12" align="right">'+
						'<a class="register-link cursor_pointer" id="all_posts" onclick="allPosts('+1+')">See all</a>'+
						'</div>'+
						'</div>'	
				);*/
					if(see_all_posts==0)
					{
						$("#see_all_posts").append(
								'<div class="row">'+
								'<div class="col-md-12" align="right">'+
								'<a class="cursor_pointer see_all_link" id="all_posts" onclick="allPosts('+1+')">See all</a>'+
								'</div>'+
								'</div>'	
						);
					}
				else{
					$("#see_all_posts").append(
							'<div class="row">'+
							'<div class="col-md-12" align="right">'+
							'<a class="cursor_pointer see_all_link" id="all_posts" onclick="allPosts('+0+')">See less</a>'+
							'</div>'+
							'</div>'	
					);
				}
				$("#comments_title").text("All Comments");
				$("#comments_title").addClass("comments_title_border");
				}
				
				$("#comments-details").empty();
				if(data.comments_info.length>0)
				{
				for(var i=0;i<data.comments_info.length;i++)
				{
					$("#comments-details").append(
						'<div class="row" id="single-comments">'+
						'<div class="col-md-4" id="UserName">'+
						'<label>'+data.comments_info[i].user_name+'</label>'+
						'</div>'+
						'<div class="col-md-6" id="comment">'+
						'<label>'+data.comments_info[i].comments+'</label>'+
						'</div>'+
						'<div class="col-md-1" id="delete_btn_'+i+'">'+
						'</div>'+
						'</div>'
					);
					$("#single_comments").append("<div class='col-md-1'></div>");
					if(USER_ID==data.comments_info[i].user_id)
					{
						$("#delete_btn_"+i).append(
						'<a class="news-link-style cursor_pointer" onclick="delete_comments('+data.comments_info[i].comment_id+','+NEWS_ID+',\''+NEWS_SOURCE+'\','+see_all_posts+')"><img src="resources/assets/images/delete.png" width="30px" height="30px"></a>'
						);
					}
					if(data.comments_info[i].user_id!=USER_ID)
						{
						$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"ucommentted").attr("src",image);
						}
				$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"comments").text(data.comments_info[i].comments_count);
				//$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"ucommentted").attr("src",image);
				}
				}
				else{
					$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"comments").text("");
					$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"ucommentted").attr("src",image);
				}
			},
			error:function(){
			}
		});
}
function userUnLikes(delete_like_id,news_id, news_source)
{
		news_id=news_id.trim();
		news_source=news_source.trim();
			
	var image="resources/assets/images/like.png";
	delete_like_id=delete_like_id.trim();
	$.ajax({
		type: "POST",
		url:'delete-user-likes',
		data:'delete_like_id='+delete_like_id+'&news_id='+news_id+'&news_source='+news_source,
		success:function(res){
			var json = JSON.parse(res);
			if(json!=0)
			{
			$("#"+news_id+"_"+news_source+"_"+"likes").text(json);
			}
			else{
				$("#"+news_id+"_"+news_source+"_"+"likes").text("");
			}
			$("#"+news_id+"_"+news_source+"_"+"img").attr("src",image);
			$("#"+news_id+"_"+news_source+"_"+"likesAnchor").attr("onclick","userLikes('"+news_id+"','"+news_source+"','"+""+"')");

			/*$("#"+news_id+"_"+news_source+"_"+"likes").text(json);
			$("#"+news_id+"_"+news_source+"_"+"img").attr("src",image);*/
		},
		error:function(){
		}
	});
}
function clearText()
{
	$("#search_key").val("");
}
function getPoliticianInfo(state_id,category_id)
{
	document.forms[0].action="statewise-politician?state_id="+state_id+"&category_id="+category_id;
	document.forms[0].methdo="post";
	document.forms[0].submit();
}
function readMoreBlog(blog_id)
{
	document.forms[0].action="blog-details?blog_id="+blog_id;
	document.forms[0].methdo="post";
	document.forms[0].submit();
}
function showBio(politician_id)
{
	document.forms[0].action="politician-bio?politician_id="+politician_id;
	document.forms[0].methdo="post";
	document.forms[0].submit();
}
function allPosts(post_index)
{
	var see_all_posts;
	var image="resources/assets/images/comments.png";
	var comments=$("#comment").val();
	if(post_index==1)
		{
		see_all_posts="1";
		}
	else{
		see_all_posts="0";
	}
	
	$.ajax({
			
			type: "POST",
			url:'user-comments-function',
			data:'news_id='+NEWS_ID+'&news_source='+NEWS_SOURCE+'&celebrity_id='+CELEBRITY_ID+'&comments='+comments+'&see_all_posts='+see_all_posts,
			success:function(res){
				var data = JSON.parse(res);
				
				$("#see_all_posts").empty();
				$("#comments_title").text("");
				$("#comments_title").removeClass("comments_title_border");
				if(data.comments_info.length>0)
				{
					if(see_all_posts==0)
						{
							$("#see_all_posts").append(
									'<div class="row">'+
									'<div class="col-md-12" align="right">'+
									'<a class="cursor_pointer see_all_link" id="all_posts" onclick="allPosts('+1+')">See all</a>'+
									'</div>'+
									'</div>'	
							);
						}
					else{
						$("#see_all_posts").append(
								'<div class="row">'+
								'<div class="col-md-12" align="right">'+
								'<a class="cursor_pointer see_all_link" id="all_posts" onclick="allPosts('+0+')">See less</a>'+
								'</div>'+
								'</div>'	
						);
					}
				$("#comments_title").text("All Comments");
				$("#comments_title").addClass("comments_title_border");
				}
				
				$("#comments-details").empty();
				for(var i=0;i<data.comments_info.length;i++)
				{
					$("#comments-details").append(
						'<div class="row" id="single-comments">'+
						'<div class="col-md-4" id="UserName">'+
						'<label>'+data.comments_info[i].user_name+'</label>'+
						'</div>'+
						'<div class="col-md-6" id="comment">'+
						'<label>'+data.comments_info[i].comments+'</label>'+
						'</div>'+
						'<div class="col-md-1" id="delete_btn_'+i+'">'+
						'</div>'+
						'</div>'
					);
					$("#single_comments").append("<div class='col-md-1'></div>");
					if(USER_ID==data.comments_info[i].user_id)
					{
					$("#delete_btn_"+i).append(
					'<a class="news-link-style cursor_pointer" onclick="delete_comments('+data.comments_info[i].comment_id+','+NEWS_ID+',\''+NEWS_SOURCE+'\','+see_all_posts+')"><img src="resources/assets/images/delete.png" width="30px" height="30px"></a>'
					);
				}
				if($("#comment").val()!=null && $("#comment").val()!='')
					{
					$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"ucommentted").attr("src",image);
					}
				$("#"+NEWS_ID+"_"+NEWS_SOURCE+"_"+"comments").text(data.comments_info[i].comments_count);
				$("#comment").val("");
				}
				
			},
			error:function(){
			}
		}); 
}

function activateTabs(activeTab)
{
	activeTab=activeTab.trim();
	$.ajax({
		type: "POST",
		url:'pagination-active-tab',
		data:'activeTab='+activeTab,
		success:function(res){
		},
		error:function(){
		}
	});
}
function validateLogin()
{
	 var valid=false;
	var user_name=$("#user_name").val();
	var password=$("#password").val();
	$.ajax({
		type: "POST",
		url:'validate-login',
		data:'user_name='+user_name+'&password='+password,
		success:function(res){
			if(res=="wrong")
				{
				
				$("#errName").text("Invalid Login Credentials");
				$("#user_name").val("");
				$("#password").val("");
				valid=false;
				}
			else if(res=="ok")
				{
				document.getElementById("login_form").submit();
				valid=true;
				return valid;
				}
		},
		error:function(){
		}
	});
	return valid;
}
function validateForgotPassword()
{
	var forgot_pass_email=$("#forgot_pass_email").val();
	if(forgot_pass_email==null || forgot_pass_email=="")
		{
		document.getElementById("forgot_pass_err").innerHTML = "Please enter email id";
		return false;
		}
	
	document.getElementById('forgot_password').action="forgot-password?forgot_pass_email="+forgot_pass_email;
	document.getElementById('forgot_password').method="post";
	document.getElementById('forgot_password').submit();
}