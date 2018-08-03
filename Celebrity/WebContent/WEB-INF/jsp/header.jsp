<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <title>Home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
<link rel="shortcut icon" type="image/icon" href="resources/assets/images/favicon.ico" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="resources/assets/css/jquery-ui.css" rel="stylesheet">
 <link rel="stylesheet" href="resources/assets/css/common-style.css">
 <link rel="stylesheet" href="resources/assets/css/header.css">
<!-- <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,600i,700|Playfair+Display:400,400i,700,700i,900" rel="stylesheet"> -->
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700|Roboto:300,400,400i,500,700,900" rel="stylesheet">

</head>
<body style="background-repeat: no-repeat;">
<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		if (session.getAttribute("user_name") == null) {
			response.sendRedirect("home");
		}
	%>
	
	<!-- <div class="container left-right-zero" style="background-color: white; width: 100%; height: 100px;"> -->
<div id="my_header" class="container left-right-zero header-style" style="background: url(resources/assets/images/header-img.png)">
	<!-- <form id="formSearch" method="post"> -->
		<div class="col-sm-12 left-right-zero title_bar">
			<div class="col-sm-2 logo">
					<a href="home"><img class="img-logo" src="resources/assets/images/logo.png"></a>
			</div>
			<div class="col-sm-7">
			<div class="row verified-box">
			<div class="col-sm-2 verifiedDiv">Verified</div>
			<div class="col-sm-2 removePaddings">Twitter feed<img src="resources/assets/images/icon.png" class="verifiedIcon"></div>
			<div class="col-sm-2 removePaddings">Instagram feed<img src="resources/assets/images/icon.png" class="verifiedIcon"></div>
			<div class="col-sm-3 removePaddings fbAccounts">Facebook accounts<img src="resources/assets/images/icon.png" class="verifiedIcon"></div>
			<div class="col-sm-3 removePaddings">Certified news sources<img src="resources/assets/images/icon.png" class="verifiedIcon"></div>
			</div>
			</div>
				<div class="col-sm-3 removePaddings searchAccountMainDiv">
					<div class="search-box-margin" >
					<div class="col-sm-8 searchBoxMobile">
					<form id="formSearch" action="commonSearch" method="post">
						<div class="col-sm-12 div_search_box">
							<div class="col-sm-11 left-right-zero searchBoxMobileInside">
								<input class="search-box" type="text" name="txt_search" id="txt_search" value="${search_key}">
							</div>
							<div class="col-sm-1 left-right-zero search-icon-style searchIconMobile">
								<a href='#' onclick="CommonSearchFun()">
									<img class="img-search" src="resources/assets/images/search.png" width="30px" height="30px"></a>
							</div> 	
						</div>
						</form>
					</div>
					<div class="col-sm-4 dropdown settings-dropdown accountMobile removePaddings">
					<c:if test="${not empty user_name}">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<div class="col-sm-12 removePaddings">
							<div class="col-sm-12 user-name-style">
								<img src="resources/assets/images/user-settings.png" height="25px" width="28px">
							</div>
							<div class="col-sm-12 user-name-style removePaddings">
								<span class="user-name-style">${user_first_name}</span>
							</div>
						</div>
						<c:if test="${role_id==1 or role_id==2}">
											<ul class="dropdown-menu settings-dropdown-menu">
												<li><a href="user-setting">Settings</a></li>
												<li><a href="logout">Logout</a></li>
											</ul>
										</c:if>
					</a>
					</c:if>
					<c:if test="${empty user_name}">
					<a href="#" data-toggle="modal" data-target="#modal_login">
					<div class="col-sm-12">
							<!-- <div class="col-sm-1 user-name-style">
								<div>
									<a href="#" data-toggle="modal" data-target="#modal_login"> <img src="resources/assets/images/user-settings.png" height="25px" width="28px"> </a>
								</div>
							</div>
							<a href="#" data-toggle="modal" data-target="#modal_login" class="user-name-style">
							<div class="col-sm-3 user-name-style user_logo_margin"><span class="">Account</span></div></a> -->
					
						    <div class="col-sm-12 user-name-style">
								<img src="resources/assets/images/user-settings.png" height="25px" width="28px">
							</div>
							<div class="col-sm-12 user-name-style">
								<span class="user-name-style">Account</span>
							</div>	
					</div>
					</a>
					</c:if>
					</div>
						<%-- <c:if test="${not empty user_name}">
							<div class="col-sm-1 user-name-style">
								<div class="dropdown settings-dropdown" >
									<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img src="resources/assets/images/user-settings.png" height="25px" width="28px"> </a>
										<c:if test="${role_id==1 or role_id==2}">
											<ul class="dropdown-menu settings-dropdown-menu">
												<li><a href="user-setting">Setting</a></li>
												<li><a href="logout">Logout</a></li>
											</ul>
										</c:if>
								</div>
							</div>
							<div class="col-sm-3 user-name-style user_logo_margin dropdown settings-dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" class="user-name-style">
							<span class="user-name-style">${user_first_name}</span></a>
							<c:if test="${role_id==1 or role_id==2}">
											<ul class="dropdown-menu settings-dropdown-menu">
												<li><a href="user-setting">Setting</a></li>
												<li><a href="logout">Logout</a></li>
											</ul>
										</c:if>
							</div>
						</c:if>
						<c:if test="${empty user_name}">
							<div class="col-sm-1 user-name-style">
								<div>
									<a href="#" data-toggle="modal" data-target="#modal_login"> <img src="resources/assets/images/user-settings.png" height="25px" width="28px"> </a>
								</div>
							</div>
							<a href="#" data-toggle="modal" data-target="#modal_login" class="user-name-style">
							<div class="col-sm-3 user-name-style user_logo_margin"><span class="">Account</span></div></a>
						</c:if> --%>
				</div>
			</div>
		</div>
	<!-- </form> -->
		<%-- <div class="col-sm-12 left-right-zero title_bar">
			<div class="col-sm-1 logo">
				<a href="home"><img class="img-logo" src="resources/assets/images/logo.png"></a>
			</div>
			<div class="col-sm-7"> <!-- style="height: 100px;padding-top:2.5%;"> -->
				<form id="formSearch" method="post">
						<div class="col-sm-12 col-sm-offset-7">
							<div class="col-sm-7 div_search_box">
									<input class="search-box form-control" type="text" name="txt_search" id="txt_search">
									<a onclick="CommonSearchFun()"><img src="resources/assets/images/search.png" width="25px" height="25px"></a> 	
							</div>
							<c:if test="${empty user_name}">
								<div class="col-sm-5">
									<button type="button" class="btn-flat" data-target="#modal_login" data-toggle="modal"> Login </button>		
									<button type="button" class="btn-flat" data-target="#modal_register" data-toggle="modal"> Register </button>
								</div>
							</c:if>
							<c:if test="${not empty user_name && role_id==1}"> <!-- Admin  -->
								<div class="col-sm-5">
									<div class="dropdown">
									  <button class="btn-flat dropdown-toggle" type="button" data-toggle="dropdown">${user_name}
									  <span class="caret"></span></button>
									  <ul class="dropdown-menu">
									    <li><a href="user-setting">Setting</a></li>
									    <li><a href="logout">Logout</a></li>
									  </ul>
									</div>
								</div>
							</c:if>
							
							<c:if test="${not empty user_name && role_id==2}"> <!-- User -->
								<div class="col-sm-5">
									<div class="dropdown">
									  <button class="btn-flat dropdown-toggle" type="button" data-toggle="dropdown">${user_name}
									  <span class="caret"></span></button>
									  <ul class="dropdown-menu">
									    <li><a href="user-setting">Setting</a></li>
									    <li><a href="logout">Logout</a></li>
									  </ul>
									</div>
								</div>
							</c:if>
							
						</div>
			</form>
			</div>
			<div id="alert_message" class="modal fade" role="dialog">
							 	<div class="modal-dialog">
									<div class="modal-content">
									      <div class="modal-header1" style="padding: 3%;">
									      		<button type="button" class="close" data-dismiss="modal">&times;</button>
									      		<label id="lbl_message">  </label>
									      </div>
								     </div>
								 </div>
							</div>
		</div> --%>
		<div class="col-sm-12 left-right-zero">
			<div class="topnav" id="myTopnav">
				<!-- <a id="home" href="home">Home</a> -->
				<c:if test="${role_id==1}"> 
						<a id="dashboard" href="admin-home">Dashboard</a>
				</c:if>
				  <!-- <a id="new_release" href="new-release">New Release</a> -->
				  <!-- <a id="hottest_band" href="hottest-band">Hottest Band</a> -->
				  <!-- <a id="actors_models"href="actors-models">All Celebrities</a> -->
				  <a id="all-celebrities-tab" href="all-celebrities">All Celebrities</a>
				  <a id="actors-models-tab" href="actors-models">Actors/Models</a>
				  <a id="athletes-tab" href="athletes">Athletes</a>
				  <a id="musician-tab" href="musician">Musicians</a>
				  <a id="movie-tab" href="movie">Movies</a>
				  <a id="blogs-tab" href="blogs">Blogs</a>
					<c:if test="${empty user_name}">
				  	<a href="#" data-toggle="modal" data-target="#modal_login">Request a celebrity</a>
				  </c:if>
				  <c:if test="${not empty user_name}">
				  	<a href="#" data-toggle="modal" data-target="#request_celebrity">Request a Celebrity</a>
				  </c:if>
				  <!-- <a id="blogs" href="daily-news-updates">Daily updates</a> -->
				 <%--  <c:if test="${not empty user_name && role_id==1}"> <!-- Admin  -->
								<div class="col-sm-5">
									<div class="dropdown">
									  <button class="btn-flat dropdown-toggle" type="button" data-toggle="dropdown">${user_name}
									  <span class="caret"></span></button>
									  <ul class="dropdown-menu">
									    <li><a href="user-setting">Setting</a></li>
									    <li><a href="logout">Logout</a></li>
									  </ul>
									</div>
								</div>
							</c:if> --%>
				  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
			</div>
		</div>
		
		</div>
		
		<!-- *	*	*	*	*	*	*	*	*	*	* Login 	*	*	*	*	*	*	*	*	*	* -->
		
		<div id="modal_login" class="modal fade" role="dialog" style="z-index:999">
		 	<div class="modal-dialog">
				<div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Login</h4>
			      </div>
			      <div class="modal-body">
			      	<form action="login" method="post">
			      		<div class="row">
				       		<div class="col-sm-12 padding-one">
				       			<div class="col-sm-3">
				       				<label>User Name </label>
				       			</div>
				       			<div class="col-sm-9">
				       				<input class="form-control" type="text" name="user_name">
				       			</div>
				       		</div>
				       		<div class="col-sm-12 padding-one">
				       			<div class="col-sm-3">
				       				<label>Password</label>
				       			</div>
				       			<div class="col-sm-9">
				       				<input class="form-control" type="password" name="password"> 
				       			</div>
				       		</div>
				       		<div class="col-sm-12">
				       				<label class="redColor" id="login_message"> </label>
				       		</div>
			       		</div>
			       		<div class="row" >
			       			<div align="center" class="padding-one">
			       				<input type="submit" class="btn-flat" value="Login" onclick="return validateLogin();">
			       				<!-- <input type="button" class="btn-flat" data-dismiss="modal" value="Close"> -->
			       			</div>
			       			<div align="center" class="padding-one">
			       				<a href="#" class="forgot-password-link" data-dismiss="modal" data-toggle="modal" data-target="#forgot_password_modal"> Forgot password </a>
			       			</div>
			       			<div align="center" class="padding-one">
			       				<a href="#" data-dismiss="modal" data-target="#modal_register" data-toggle="modal"> Register </a>
			       			</div>
			       		</div>
			       	</form>
			      </div>
			    </div>
		  </div>
		</div>
		
		
		
<!-- ******************************** Register **************************************************  -->
		<div id="modal_register" class="modal fade" role="dialog">
		 	<div class="modal-dialog">
				<div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Register</h4>
			      </div>
			      <div class="modal-body">
			      	<form action="register" method="post">
			      	<div class="row">
			      		<div class="col-sm-12" align="center">
			      			<span  id="regiError" class="redColor"></span>
			      		</div>
			      		<div class="col-sm-12" align="center">
			      			<span  id="regiSuccess" class="greenColor"></span>
			      		</div>
			      	</div>
			      		<div class="row padding-one">
				       		<div class="col-sm-3">
				       			<label>Enter Name </label>
				       		</div>
				       		<div class="col-sm-4">
				       			<input type="text" class="form-control" name="first_name" placeholder="First Name">
				       		</div>
				       		<div class="col-sm-4">
				       			<input type="text" class="form-control" name="last_name" placeholder="Last Name">
				       		</div>
			       		</div>
			       		<div class="row padding-one">
			       			<div class="col-sm-3">
			       				<label>Email Id </label>
			       			</div>
			       			<div class="col-sm-8">
			       				<!-- <input type="text" class="form-control" name="user_name" id="email_id" placeholder="Email id" onblur="validate_emailId()"> -->
			       				<input type="text" class="form-control" name="email_id" id="email_id_validate" placeholder="Email id">
			       			</div>
			       		</div>
			       		<div class="row padding-one">
			       			<div class="col-sm-3">
			       				<label>Password </label>
			       			</div>
			       			<div class="col-sm-8">
			       				<input type="password" class="form-control" name="password"  id="reg_password" placeholder="Password">
			       			</div>
			       		</div>
			       		
			       		<div class="row padding-one">
			       			<div align="center">
				       			<input type="submit" value="Register" class="btn-flat btn-primary" onclick="return validate_registration()">
				       			<button type="button" class="btn-flat btn-primary" data-dismiss="modal">Close</button>
			       			</div>
			       		</div>
			       	</form>
			      </div>
			    </div>
		  </div>
		</div>
		
		<!-- ******************************** Forgot password **************************************************  -->
		
		<div class="modal fade" id="forgot_password_modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-header-background" align="center">
		        <h5 class="modal-title modal-title" id="exampleModalLabel">Forgot Password</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		    </div>
		    <div class="modal-body pod-text" align="center">
		    	<form action="" id="forgot_password">
		    		<div class="row form-group">
		    		<div class="col-md-12"><span class="redColor" id="forgot_pass_err"></span></div>
		    		<div class="col-md-12"><span class="greenColor" id="forgot_pass_success"></span></div>
		    		</div>
			    	<div class="row form-group">
			    		<div class="col-md-4">Enter Email Id</div>
			    		<div class="col-md-8">
			    			<input type="text" id="forgot_pass_email" name="forgot_pass_email" class="form-control">
			    		</div>
			    	</div>
			    	<div class="row form-group">
				    	<div class="col-md-12">
				    		<input type="button" value="Submit" class="btn-flat" onclick="return validateForgotPassword()">
				    	</div>
			    	</div>
			    </form>
		    </div>
		</div>
	</div>
</div>


<div class="modal fade" id="request_celebrity">
	<div class="modal-dialog">
		<div class="modal-content">
						      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Request a celebrity</h4>
			      </div>
		    <div class="modal-body pod-text" align="center">
		    	<form action="add-celebrity-reuest" method="post" id="add-celebrity-reuest">
		    		<div class="row form-group">
		    		<div class="col-md-12"><span class="redColor" id="request_celeb_err"></span></div>
		    		<div class="col-md-12"><span class="greenColor" id="request_celeb_success"></span></div>
		    		</div>
			    	<div class="row form-group">
			    		<div class="col-md-4">Celebrity Name</div>
			    		<div class="col-md-8">
			    			<input type="text" id="celebrity_name" name="celebrity_name" class="form-control">
			    		</div>
			    	</div>
			    	<div class="row form-group">
			    		<div class="col-md-4">Select Type</div>
			    		<div id="celebrity_categories_chk" class="col-md-8" align="left"></div>
			    	</div>
			    	<div class="row form-group">
				    	<div class="col-md-12">
				    		<input type="submit" value="Submit" class="btn-flat" onclick="return validateRequestCelebrity();">
				    	</div>
			    	</div>
			    </form>
		    </div>
		</div>
	</div>
</div>
	<!-- <script src="//code.jquery.com/jquery-1.12.4.js"></script> -->
	
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
		<script src="resources/assets/js/jquery-ui.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="resources/assets/js/style.js"></script>
 <script src="resources/assets/js/common.js"></script>
 <script type="text/javascript">
 var searchBoxValues=[];
$(function(){
	if($(window).width() > 768){
	  $(window).scroll(function() {
	        "use strict";
	        if ($(window).scrollTop() > 40) {
	          $("#my_header").addClass("minimizedHeader");
	        } else {
		          $("#my_header").removeClass("minimizedHeader");
	        }
	    }); 
	}else{
		$(".verified-box").html('<div class="col-sm-11 verifiedDiv">Verified</div><div class="col-sm-11 removePaddings"><img src="resources/assets/images/icon.png" class="verifiedIcon">&nbsp; <span class="VerifiedContent">Twitter feed, Instagram feed, Facebook accounts, Certified news sources</span></div>');
		if($(window).width() < 768){
		$(window).scroll(function() {
		        "use strict";
		        if ($(window).scrollTop() > 40) {
		          $(".verified-box").hide();
		          $("#my_page_container").addClass("minimizedHeaderPage");
		        } else {
			          $(".verified-box").show();
			          $("#my_page_container").removeClass("minimizedHeaderPage");
		        }
		    }); 
		}
	}

	 	/* $('#txt_search').autocomplete({
			  //autoFocus: true,
          	  source: function (request, response) {
          		  var s=$('#txt_search').val();
                	$.ajax({
                		type:"post",
                		url:"get-celebrity-suggestions",
        				data:"search_term="+s,
                        success: function (res) {
                        	var obj = JSON.parse(res);
        					response(obj);  
                        }
                    });
                },
                minLength: 0
                }).focus(function() {
                $(this).autocomplete("search", "");
            });  
	 $("#txt_search").click(function(event){
		var ev=event.type;
	      $("#txt_search").autocomplete({
	       		source: function (request, response) {
	       		var search_val = $("#txt_search").val(); 
	       		if(ev=="click" && search_val!=""){
	       			var search_val="";
	          	}
	       		$.ajax({
            		type:"post",
            		url:"get-celebrity-suggestions",
    				data:"search_term="+search_val,
                    success: function (res) {
                    	var obj = JSON.parse(res);
    					response(obj);  
    					ev="";
                    }
                });
	            },
	            minLength: 0
	            }).trigger("focus");
	  });  */
	  
	  if('${error_message}'=='Invalid login.'){
		  $("#login_message").text("Invalid login credentials.");
		  $('#modal_login').modal('show');
	  }
	  if('${forgot_p_invalid}'=='Please enter valid email id.'){
		  $("#forgot_pass_err").text("Please enter valid email id.");
		  $('#forgot_password_modal').modal('show');
	  }
	  if('${message}'=='Your updated password have been sent on your mail. Please check your mail.'){
		  $("#forgot_pass_success").text("Your updated password have been sent on your mail. Please check your mail.");
		  $('#forgot_password_modal').modal('show');
	  }
	  if('${error_message}'=='This email id is already present.'){
		  $("#regiError").text("This email id is already present.");
		  $('#modal_register').modal('show');
	  }
	  if('${message}'=='Celebrity requested successfully.'){
		  $("#request_celeb_success").text("Celebrity requested successfully.");
		  $('#request_celebrity').modal('show');
	  }
	  
	 /*  if('${message}'=='Welcome to NowYaNo!!! Your account has been created successfully.'){
		  $("#regiSuccess").text("Welcome to NowYaNo!!! Your account has been created successfully.");
		  $('#modal_register').modal('show');
	  } */
	  callSearchBoxValues();
	  callCelebrityCategories();
});

function callSearchBoxValues(){
	$.ajax({
		type:"post",
		url:"get-celebrity-suggestions",
        success: function (res) {
        	var obj = JSON.parse(res);
			searchBoxValues=obj;
        }
    });
}
function callCelebrityCategories(){
	$.ajax({
		type:"post",
		url:"get-all-celebrity-categories",
        success: function (res) {
        	var str=res.split("~");
        	var category_id=str[0];
        	var category_name=str[1];
        	var id=category_id.split(",");
        	var name=category_name.split(",");
        	for(var i=0;i<id.length;i++){
        		$("#celebrity_categories_chk").append(
        				 '<input type="checkbox" name="celebrity_category_ids" value="'+id[i]+'"> '+name[i]+'<br>'
        		);
        	}
        },
		error:function(){
			
		}
    });
}
/* $('#txt_search').autocomplete({
	 autoFocus: true,
     source: searchBoxValues,
     minLength: 0,
     scroll: true
      }).focus(function() {
      $(this).autocomplete("search", "");
}); */
$('#txt_search').autocomplete({
	 autoFocus: true,
	
   source: searchBoxValues,
   max:10,
   minLength:1,
	delay: 100,
		autoFill: true,
		mustMatch: true,
		matchContains: false,
		scrollHeight: 220,
   scroll: true,
   }).focus(function() {
    $(this).autocomplete("search", "");
});
/* source: function(request, response) {
    var results = $.ui.autocomplete.filter(searchBoxValues, request.term);

    response(results.slice(0, 10));
}, */
/* $('#txt_search').keyup({
  minLength: 0,
  max:12,
  source: function(request, response) {
      var data = $.grep(searchBoxValues, function(value) {
          return value.substring(0, request.term.length).toLowerCase() == request.term.toLowerCase();
      });            

      response(data);
  }
}).focus(function () {
  $(this).autocomplete("search", "");
}); */ 
$("#txt_search").click(function(event){
	 $("#txt_search").autocomplete({
           source: searchBoxValues
       }).trigger("focus");
});
</script>
</body>
</html>