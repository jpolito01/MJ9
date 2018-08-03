  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
 <link rel="stylesheet" href="resources/assets/css/bootstrap-grid.min.css">
 <link rel="stylesheet" href="resources/assets/css/bootstrap-reboot.min.css">
 <link rel="stylesheet" href="resources/assets/css/common.css"> 
  <link rel="stylesheet" href="resources/assets/css/nav-style.css"> 
   <link rel="stylesheet" href="resources/assets/css/all-pages.css"> 
   <link href="https://fonts.googleapis.com/css?family=Oswald:400,500|Roboto:300,400,400i,500" rel="stylesheet">
<title>RSBS - Header</title>
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-9407994341079432",
    enable_page_level_ads: true
  });
</script>
</head>
<body>

<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		if (session.getAttribute("user_name") == null) {
			response.sendRedirect("home");
		}
	%>
<div id="my_header" class="header-style main-container">
<div class="row"><img src="resources/assets/images/rsbs-header.png" width="100%" id="headerImage"></div>
<!-- <div class="container main-container"> -->
		
		<nav class="navbar navbar-toggleable-md navbar-light navbar-size navbar_font">
					  <button class="navbar-toggler navbar-toggler-right sandwich-button-style" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					    <span class="navbar-toggler-icon"></span>
					  </button>
		  				<!-- <a class="navbar-brand" href="#">Navbar</a> -->
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
						    <ul class="navbar-nav mr-auto">
							      <li class="nav-item middle-border">
							        		<a id="home" class="nav-link menubar-li-padding nav-hover-link nav_line_height" href="home">Home <span class="sr-only">(current)</span></a>
							      </li>
							      <c:if test="${role_id==1}">
								   		<li class="nav-item middle-border">
									        		<a id="dashboard" class="nav-link menubar-li-padding nav-hover-link nav_line_height" href="admin-home">Dashboard</a>
									      </li>
							      </c:if>
							      <!-- <li class="nav-item middle-border" id="politicians">
							        		<a class="nav-link menubar-li-padding" href="all-politicians">Politicians <span class="sr-only">(current)</span></a>
							      </li>
							      <li class="nav-item middle-border">
							        		<a class="nav-link menubar-li-padding" href="#" data-toggle="modal" data-target="#LoginModal">Login</a>
							      </li> -->
		                    <c:set var="total_records" value="0"></c:set>
							<c:forEach var="ca" items="${all_categories}">
									<li class="dropdown dropdown-large middle-border">
										 <a id="cat${ca.getCategory_id()}" href="#" class="nav-link menubar-li-padding nav-hover-link nav_line_height" data-toggle="dropdown" onmouseover="hover('img${ca.getCategory_id()}');" onmouseout="unhover('img${ca.getCategory_id()}');">${ca.getCategroy_name()} <span class="caret-padding"> <img id="img${ca.getCategory_id()}" src="resources/assets/images/expand.png" width="10px" style="height:10px" class="caret-image"></span></a>
										<%-- <a id="cat${ca.getCategory_id()}" href="#" class="nav-link menubar-li-padding nav-hover-link" data-toggle="dropdown" onmouseover="hover('img${ca.getCategory_id()}');" onmouseout="unhover('img${ca.getCategory_id()}');">${ca.getCategroy_name()} <span class="caret" style="font-size: 40px;color: #000000;"></span></a> --%>
										<div class="dropdown-menu dropdown-menu-large bg-faded">
											<!-- <div class="row"> -->
											<c:choose>
												<c:when test="${ca.getCategory_id()==4}">
												<div class="row">
													<c:forEach var="ap" items="${precident}">
														<%-- <c:if test="${ap.getPolitician_position()=='President of the United States'}"> --%>
																<div class="col-md-12 rowMargin" align="center">
																	<span style="color: gray">${ap.getPolitician_position()}</span><br />
															<a href="#" class="nav-link-color" onclick="showProfile('${ap.getCelebrity_id()}')">${ap.getCelebrity_name()}</a>
																</div>
														<%-- </c:if> --%>
														
													</c:forEach>
													</div>
													<div class="row">
													<div class="col-md-2"></div>
													<c:forEach var="ap" items="${vise_precident}">
													
																<%-- <c:if test="${ap.getPolitician_position()=='Vice President of the United States of America'}"> --%>
																	<div class="col-md-4 rowMargin" align="center">
																		<span style="color: gray">${ap.getPolitician_position()}</span><br />
																<a href="#" class="nav-link-color" onclick="showProfile('${ap.getCelebrity_id()}')">${ap.getCelebrity_name()}</a>
																	</div>
																<%-- </c:if> --%>
																<%-- <c:if test="${ap.getPolitician_position()=='Secretary of State'}">
																	<div class="col-md-6 rowMargin" align="center">
																		<span style="color: gray">${ap.getPolitician_position()}</span><br />
																<a href="#" class="nav-link-color" onclick="showProfile('${ap.getCelebrity_id()}')">${ap.getCelebrity_name()}</a>
																	</div>
																</c:if> --%>
													</c:forEach>
													</div>
													<%-- <c:forEach var="a" items="${all_precidentian_cabinates}">
														<c:set var="total_records" value="${total_records+1}"></c:set>
														
													</c:forEach> --%>
													<%-- <c:set var="status_index" value="0"></c:set>
												<c:set var="data_index" value="${(total_records/4)-1}"></c:set>
												<c:forEach var="col" begin="1" end="4">
												<div class="col-md-3 precident_name_style">
													<c:forEach var="ap" items="${all_precidentian_cabinates}" begin="${status_index}" end="${data_index}" varStatus="st">
																<div class="menubar-li-padding">
																	<span style="color: gray">${ap.getPolitician_position()} ${total_records/4}</span><br />
																	<a href="#" class="nav-link-color" onclick="showProfile('${ap.getCelebrity_id()}')">${ap.getCelebrity_name()}</a></div>
															
													 </c:forEach>
													 <c:set var="status_index" value="${status_index+(total_records/4)+1}"></c:set>
														<c:set var="data_index" value="${data_index+(total_records/4)+1}"></c:set>
													 </div>
													</c:forEach> --%>
													<div class="row">
													<c:forEach var="ap" items="${all_precidentian_cabinates}" varStatus="st">
													<div class="col-md-3 precident_name_style">
																<div class="menubar-li-padding">
																	<span style="color: gray">${ap.getPolitician_position()}</span><br />
																	<a href="#" class="nav-link-color" onclick="showProfile('${ap.getCelebrity_id()}')">${ap.getCelebrity_name()}</a></div>
															</div>
													 </c:forEach>
													 </div>
												</c:when>
												<c:otherwise>
												<div class="row">
												<c:set var="status_index" value="0"></c:set>
												<c:set var="data_index" value="8"></c:set>
												<c:forEach var="col" begin="1" end="6">
													<div class="col-md-2">
														<c:forEach var="ap" items="${all_states}" begin="${status_index}" end="${data_index}" varStatus="st">
															<span class="menubar-li-padding">
																				<a href="#" class="nav-link-color" onclick="getPoliticianInfo('${ap.getState_id()}','${ca.getCategory_id()}')">${ap.getState_name()}</a>
																				</span><br />
																
														</c:forEach>
														<c:set var="status_index" value="${status_index+9}"></c:set>
														<c:set var="data_index" value="${data_index+9}"></c:set>
													</div>
												</c:forEach>
												</div>
												</c:otherwise>
											</c:choose>
												
										    <!-- </div> -->
										</div>
									</li>
							</c:forEach>
							<li class="nav-item middle-border">
							        		<a id="blogs" class="nav-link menubar-li-padding nav-hover-link nav_line_height" href="blogs">Blogs</a>
							</li>
							<li class="nav-item middle-border">
							        		<a id="bills" class="nav-link nav-hover-link nav_line_height menubar-li-padding" href="bills">Bills & resolutions</a>
							</li>
							<li class="nav-item middle-border" id="accountDivMobile">
							        		
							</li>
						    </ul>	      
							    <form class="form-inline my-2 my-lg-0" action="user-searches" method="post">
								      <input class="mr-sm-2 search-box-style" type="text" value="Search" name="search_key" id="search_key" onclick="clearText()" />
								      <!-- <input class="mr-sm-2" type="text" value="Search" name="search_key"> -->
								      <!-- <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
							    </form>
							    <div id="accountDiv1">
							    <c:choose>
							    <c:when test="${not empty user_id}">
							    <div class="dropdown user-dropdown">
									<a href="#" data-toggle="dropdown" class="user-icon-margin"> <img src="resources/assets/images/user.png" height="25px" width="28px" class="user-icon-style"> </a>
										<ul class="dropdown-menu user-dropdown-menu">
											<li><a href="settings" class="link-style">Settings</a></li>
											<li><a href="logout" class="link-style">Logout</a></li>
											</ul>
								</div>
								<div class="dropdown user-dropdown">
							    <a href="#" data-toggle="dropdown"><div class="nav-link">${user_first_name}</div></a>
							    <ul class="dropdown-menu user-dropdown-menu">
											<li><a href="settings" class="link-style">Settings</a></li>
											<li><a href="logout" class="link-style">Logout</a></li>
											</ul>
							    </div>
							    </c:when>
							    <c:otherwise>
							    
							    <a href="#" data-toggle="modal" data-dismiss="modal" data-target="#LoginModal" class="menubar-li-padding user-icon-margin"> <img src="resources/assets/images/user.png" height="25px" width="28px" class="user-icon-style"> </a>
								<a href="#" data-toggle="modal" data-dismiss="modal" data-target="#LoginModal" id="accountWord">
								<div class="nav-link header-user-name">Account</div></a>
							    </c:otherwise>
							    </c:choose>
							   </div>
							   <div id="accountDiv">
						<c:choose>
	<c:when test="${not empty user_id}">
	<div class="row">
	<div class="col-md-10"></div>
					<div class="col-md-2">
		<div class="dropdown margin2 user-dropdown">
		<a href="#" data-toggle="dropdown" class="user-dropdown-block text_decoration_style user-name-style"> 
		<img src="resources/assets/images/user.png" height="25px" width="28px"> 
		<span class="user-name-style">${user_first_name}</span>
		<!-- <div class="dropdown-block"> -->
			<ul class="dropdown-menu user-dropdown-menu">
				<li><a href="settings" class="link-style">Settings</a></li>
				<li><a href="logout" class="link-style">Logout</a></li>
				</ul>
				<!-- </div> -->
				</a>
		</div>
				
		</div>
		</div>
	</c:when>
	<c:otherwise>
	<!-- <div class="margin1"></div> -->
	<div class="row">
	<div class="col-md-10"></div>
		<div class="col-md-2">
		<div class="dropdown margin2 accountDiv">
		<a href="#" data-toggle="modal" data-target="#LoginModal" class="user-name-style"> 
		<img src="resources/assets/images/user.png" height="22px" width="22px"> 
		<span class="user-name-style">Account</span>
		</a>		
		</div>
				
		</div>
		</div>
	</c:otherwise>
	</c:choose>	
	</div>	
						  </div>
		</nav>
	</div>	
<!-- </div> -->
<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
				 Login
</button> -->
<div class="modal fade" id="LoginModal" role="dialog"> <!-- tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
  <div class="modal-dialog">
    <div class="modal-content">
         <div class="col-sm-12 modal-header modal-header-background">
	      	<div class="col-sm-6">
	      		<h3 class="modal-title" >Login</h3>
	      	</div>
	      	<div class="col-sm-6" align="right">
	      		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      	</div>
          </div>
      <form  id="login_form"  action="login" method="post">
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
			        					<input type="text" id="user_name" name="user_name" class="form-control" placeholder="Email Id" style="font-size:14px;">
			        		</div>
			        	</div>
			        	<div class="row form-group">
			        	<div class="col-md-1"></div>
			        		<div class="col-md-10">
			        					<input type="password" id="password" name="password" class="form-control" placeholder="Password" style="font-size:14px;">
			        		</div>
			        	</div>
			        	<div class="row form-group">
			        		<div class="col-md-12" align="center">
			        			<button type="submit" class="btn-follow cursor_pointer" onclick="return validateLogin();">Login</button>
			        		</div>
			        	</div>
			        	<div class="row">
			        		<div class="col-md-12" align="center">
			        		<a href="#" class="forgot-password-link" data-dismiss="modal" data-toggle="modal" data-target="#forgot_password_modal">Forgot Password</a>
			        		</div>
			        	</div>
			      </div>
				      <div class="modal-footer register-link-padding" align="center">
						      <a href="#" data-toggle="modal" data-dismiss="modal" data-target="#RegisterModal" class="register-link">New user? Register here</a>
				      </div>
	      </form>
    </div>
  </div>
</div>	

<div class="modal fade" id="RegisterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      
      <div class="col-sm-12 modal-header modal-header-background">
	      	<div class="col-sm-6">
	      		<h3 class="modal-title" >Register</h3>
	      	</div>
	      	<div class="col-sm-6" align="right">
	      		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      	</div>
          </div>
      <div class="modal-body pod-text register-modal-style">
      <form action="register" method="post" id="register">
      <div class="row">
      <div class="col-md-12">
      <div id="regiErrName" style="color:red"></div>
      </div>
      </div>
           <div class="row form-group">
					<div class="col-md-12">
					<i class="followNote">Create a free account to follow politicians and get their updates in your inbox.</i>
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
				<!-- 	<div class="row form-group">
					<div class="col-md-12">
					<span>Contact Number</span>
					<input type="text" id="contact_no" name="contact_no" class="form-control">
					</div>
					</div> -->
					<div class="row form-group">
					<div class="col-md-12">
					<span>Email Id</span>
					<input type="text" id="email_id" name="email_id" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span>Password</span>
					<input type="password" id="new_password" name="new_password" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span>Confirm Password</span>
					<input type="password" id="confirm_password" name="confirm_password" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12" align="center">
					<input type="submit" value="Register" class="btn-follow cursor_pointer" onclick="return validateRegistration()">
				<!-- 	<button type="button" class="btn-follow cursor_pointer" data-dismiss="modal">Close</button> -->
					</div>
					</div>
					</form>
      </div>
       <div class="modal-footer register-link-padding" align="center">
	   <a href="#" data-toggle="modal" data-dismiss="modal" data-target="#LoginModal" class="register-link">Already a user? Login here</a>
       </div>
      </div>
      </div>
</div>

<div class="modal fade" id="forgot_password_modal" role="dialog"> <!-- tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
  <div class="modal-dialog">
    <div class="modal-content">
         <div class="col-sm-12 modal-header modal-header-background">
	      	<div class="col-sm-10">
	      		<h3 class="forgot-modal-title" >Forgot Password</h3>
	      	</div>
	      	<div class="col-sm-2" align="right">
	      		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      	</div>
          </div>
      <form action="" id="forgot_password">
			      <div class="modal-body stories-details pod-text">
			    		<div class="row">
			    		<div class="col-sm-12"><span id="forgot_pass_err"></span></div>
			    		</div>
				    	<div class="row form-group">
				    		<div class="col-sm-4">Enter Email Id</div>
				    		<div class="col-sm-8">
				    			<input type="text" id="forgot_pass_email" name="forgot_pass_email" class="form-control" style="font-size:14px;">
				    		</div>
				    	</div>
			    		<div class="row form-group">
					    	<div class="col-sm-12" align="center">
					    		<input type="button" value="Submit" class="btn-follow cursor_pointer" onclick="return validateForgotPassword()">
					    	</div>
				    	</div>
			      
			      </div> 
				      
	      </form>
    </div>
  </div>
</div>	
<!-- <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
<!-- <script src="resources/assets/js/jquery.min.js"></script> -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script src="resources/assets/js/common.js"></script>
<!--  <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>  -->

<script type="text/javascript">
$(function(){
	$("#accountDiv1").show();
	$("#accountDiv").hide();
	if($(window).width() >= 700){
		  $(window).scroll(function() {
		        "use strict";
		        if ($(window).scrollTop() > 40) {
		        	$("#headerImage").attr("src","resources/assets/images/rsbs-header-minimized.png");
			          $(".page-padding-top").attr("id","minimizedPagePaddingTop");
		        } else {
			          $(".page-padding-top").removeAttr("id","minimizedPagePaddingTop");
		        	$("#headerImage").attr("src","resources/assets/images/rsbs-header.png");
		        }
		    }); 
		}
});
function hover(element) {
    $("#"+element).attr("src", 'resources/assets/images/dropdown.png');
    $('.dropdown').removeClass('show');
}
function unhover(element) {
	$("#"+element).attr("src", 'resources/assets/images/expand.png');
}
</script>
</body>
</html>