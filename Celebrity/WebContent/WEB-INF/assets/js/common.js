var FOLLOW_ID=0,PAGE_NAME=null;

function ShowCelebrity_profile(id)
{
	document.forms[0].action="celebrity-profile?celebrity_id="+id;
 	document.forms[0].method="POST";
 	document.forms[0].submit();	
}
function fun_FinalComments(news_id,tab_id)
{
	document.forms[0].action="user-comments?news_id="+news_id+"&tab_id="+tab_id;
 	document.forms[0].method="POST";
 	document.forms[0].submit();
}
function fun_FinalFollow(entity_id,entity_type_id,tab_id)
{
	
	document.forms[0].action="user-follow?entity_id="+entity_id+"&entity_type_id="+entity_type_id+"&tab_id="+tab_id;
 	document.forms[0].method="POST";
 	document.forms[0].submit();
}

function fun_FinalLike(newsid,flag,tab_id,activity_type,newssource,user_id)
{
	if(user_id!=0)
	{
		var news_id=newsid.trim();
		var activity_type_id=activity_type.trim();
		var news_source=newssource.trim();
		var like_image="resources/assets/images/like.png";
		var liked_image="resources/assets/images/liked.png";
			$.ajax({
				type: "POST",
				url:'user-likes-function',
				data:'news_id='+news_id+'&like-dislike='+flag+'&activity_type_id='+activity_type_id,
				success:function(res){
					
					var json = JSON.parse(res);
					
						if(flag==0)
						{
							$("#"+news_id+"_"+activity_type_id+"_"+"likes").text(json.like_count);
							$("#"+news_id+"_"+activity_type_id+"_"+"img").attr("src",like_image);
							$("#"+news_id+"_"+activity_type_id+"_"+"img").attr("onclick","like('"+news_id+"','"+1+"','"+1+"','"+news_source+"')");
						}
						if(flag==1)
						{
							$("#"+news_id+"_"+activity_type_id+"_"+"likes").text(json.like_count);
							$("#"+news_id+"_"+activity_type_id+"_"+"img").attr("src",liked_image);
							$("#"+news_id+"_"+activity_type_id+"_"+"img").attr("onclick","like('"+news_id+"','"+0+"','"+1+"','"+news_source+"')");
	
						}
				},
				error:function(){
					//alert("Somthing wrong");
				}
			}); 
	}
}

function ReadMore(celebrity_id,news_id)
{
	var news_src="news";
	document.forms[0].action="celebrity-profile?celebrity_id="+celebrity_id+"&news_id="+news_id+"&news_source="+news_src;
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function ReadMore_FbPost(description,img,post_type,pub_date)
{
	$("#post-img-div").show();
	$("#lbl_description").text(description);
	if(img!=null && img!=""){
	$("#post-img").attr("src",img);
	}else{
		$("#post-img-div").hide();
	}
	$("#pub_date").text(pub_date);
	$("#facebook_post").modal("show");//src="resources/assets/images/fb.png"
	if(post_type=="Twitter")
		{
		$("#post_icon").attr("src","resources/assets/images/twitter.png");
		}
	else if(post_type=="Instagram")
		{
		$("#post_icon").attr("src","resources/assets/images/instagram.png");
		}
	else if(post_type=="Facebook")
		{
		$("#post_icon").attr("src","resources/assets/images/fb.png");
		}
	else 
		{
		$("#post_icon").attr("src","resources/assets/images/news.png");
		}
	}
function CommonSearchFun()
{
	
	var txt_search=$("input[id='txt_search']").val();
	document.getElementById("formSearch").action="commonSearch?search_query="+txt_search;
	document.getElementById("formSearch").method="POST";
	document.getElementById("formSearch").submit();
}

function validateLogin()
{
	var password, user_name;
	user_name=$("input[name='user_name']").val().trim();
	password=$("input[name='password']").val().trim();
	
	if(password=="" || user_name=="")
		{
			$("#login_message").text("Please enter user name & password.");
			return false;
		}
	else
		{
		var x = user_name;
	    var atpos = x.indexOf("@");
	    var dotpos = x.lastIndexOf(".");
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
	    	$("#login_message").text("Please enter valid user name.");
	        return false;
	    }
		
			return true;
		}
}
function validateChangePassword()
{
	var old_password,new_password,confirm_password;
	old_password=$("input[name='old_password']").val();
	new_password=$("input[name='new_password']").val();
	confirm_password=$("input[name='confirm_password']").val();
	if(old_password=="" || new_password=="" ||confirm_password=="")
	{
			$('#err_msg').append(
					'<div class="alert alert-danger">'+
			  		'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+
					  'Please enter values in all fields.'+
					'</div>'		
			);
			return false;
	}
	else if(new_password !=confirm_password)
	{
		$('#err_msg').append(
				'<div class="alert alert-danger">'+
		  		'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+
				  'Password and confirm password does not match.'+
				'</div>'		
		);
		return false;
	}else if(confirm_password.trim().length < 6)
	{
		$('#err_msg').append(
				'<div class="alert alert-danger">'+
		  		'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+
				  'Password should be greater than 6 characters.'+
				'</div>'		
		);
		return false;
	}
	else
	{
			return true;
	}
}

function CheckPassword(txt_obj)
{
	var old_password=txt_obj.value;
	old_password=old_password.trim();
	$.ajax({
		url:"check_password",
		data:{password:old_password},
		type:"POST",
		success:function(result){
			if(result=='true')
				{
				$('#err_msg').text('');
				return true;
				}
			else
				{
				txt_obj.value="";
				/*alert("Incorrect password");*/
				if($('#err_msg').text()==''){
					$('#err_msg').append(
							'<div class="alert alert-danger">'+
					  		'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'+
							  'Incorrect old password.'+
							'</div>'		
					);
				}
				
				return false;
				}
		},
		error:function(){
			
		}	
	});
	
//	document.getElementById("ChangePassword").action="user-setting?menu_name="+obj.id;
//	document.getElementById("ChangePassword").method="POST";
//	document.getElementById("ChangePassword").submit();
}
function validate_registration()
{
	var first_name=$("input[name='first_name']").val().trim();
	var last_name=$("input[name='last_name']").val().trim();
	var user_name=$("input[name='email_id']").val().trim();
	var password=$("input[id='reg_password']").val().trim();
	var result=false;
	if(first_name != "" && last_name!="" && user_name!="" && password!="")
		{
			var x = user_name;
		    var atpos = x.indexOf("@");
		    var dotpos = x.lastIndexOf(".");
		    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
		    	$("#regiError").text("Please enter valid email id.");
		        return false;
		    }
		    else if($("input[id='reg_password']").val().trim().length < 6)
		    	{
		    		$("#regiError").text("Password should be greater than 6 characters.");
		    		result=false;
		    		return false;
		    	}
		    else
				result=true;
		}
	else
		{
		$("#regiError").text("Please enter values in all fields.");
			return false;
		}
	return result;
}
function validate_email_Id()
{
	var email_id=$("#email_id_validate").val();//email_onj.value;
	email_id=email_id.trim();
	if(email_id=="" || email_id==null)
		{
			alert("Please enter email id");
			return false;
		}
	else
		{
		$.ajax({
			url:"validate_emailid",
			type:"POST",
			data:{"emailid":email_id},
			success:function(res){
				if(res=="not_ok")
					{
						$("#email_id").val("");
						return false;
					}
				else if(res=="ok")
					{
					return true;
					}
			},
			error:function(){
				//alert("Something wrong");

			}
		});
		}
}
function getDetailedBlog(blog_id)
{
	document.forms[0].action="blog-details?blog_id="+blog_id;
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function validateForgotPassword()
{
	var forgot_pass_email=$("#forgot_pass_email").val();
	if(forgot_pass_email==null || forgot_pass_email=="")
		{
		$("#forgot_pass_err").text("Please enter email id.");
		return false;
		}
	var x = forgot_pass_email;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
    	$("#forgot_pass_err").text("Please enter valid email id.");
        return false;
    }
	
	document.getElementById('forgot_password').action="forgot-password?forgot_pass_email="+forgot_pass_email;
	document.getElementById('forgot_password').method="post";
	document.getElementById('forgot_password').submit();
}

function validateRequestCelebrity(){
	var celebrity_name=$("#celebrity_name").val();
	if(celebrity_name==null || celebrity_name=="")
		{
		$("#request_celeb_err").text("Please enter celebrity name.");
		return false;
		}
	return true;
}

function unfollowCelebrity(follow_id,page_name)
{
	if(follow_id!='')
		{
		FOLLOW_ID=follow_id.trim();
		PAGE_NAME=page_name.trim();
		}
	else{
	document.getElementById("user_follows").action="followers?follow_id="+FOLLOW_ID+"&tab_id="+PAGE_NAME;
	document.getElementById("user_follows").method="post";
	document.getElementById("user_follows").submit();
	}
}
