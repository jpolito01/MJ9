<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RSBS - Settings</title>
</head>
<body style="font-family: roboto; background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
<div id="my_page_container" class="container main-container inside-page-padding min-page-height" style="width:100%;">
<div class="row">
<div class="col-md-2">
<ul class="nav flex-column" id="mySidenav">
										  <li class="side-nav-item sidefont side-nav-padding" id="sidenav1">
										    	<a data-toggle="tab" class="side-nav-link" href="#edit-profile" onclick="activeSideNav('1')">Edit Profile</a>
										  </li>
										  <li class="side-nav-item sidefont side-nav-padding" id="sidenav2">
										    	<a data-toggle="tab" class="side-nav-link" href="#change_password" onclick="activeSideNav('2')">Change Password </a>
										  </li>
										  <%-- <c:if test="${role_id==2}"> --%>
										  <li class="side-nav-item sidefont side-nav-padding" id="sidenav3">
										    	<a data-toggle="tab" class="side-nav-link" href="#follow" onclick="activeSideNav('3')">Follow </a>
										  </li>
										  <%-- </c:if> --%>
								</ul>
</div>
<div class="col-md-10">
<div class="tab-content">
<div id="edit-profile" class="tab-pane active">
<form action="settings" method="post" id="edit_profile">
<div class="row">
<div class="col-md-12">
				   		<!-- <label class="dashboard-title">Edit Profile </label> -->
					</div>
					<div class="col-md-12">
					<c:if test="${not empty message }">
							<div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            ${message}
                            </div>
					</c:if>
					<c:if test="${not empty error}">
							<div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            ${error}
                            </div>
					</c:if>
					</div>
					</div>
					<c:forEach var="a" items="${getUserInformation}">
					<div class="row">
					<div class="col-md-8 web-box table-background">
					<div class="row form-group stories-details">
					<div class="col-md-6">
					<span class="settings-title-font">First Name</span>
					<input type="text" id="First_name" name="first_name" class="form-control" value="${a.getFirst_name()}">
					</div>
					<div class="col-md-6">
					<span class="settings-title-font">Last Name</span>
					<input type="text" id="Last_name" name="last_name" class="form-control" value="${a.getLast_name()}">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span class="settings-title-font">Contact Number</span>
					<input type="text" id="Contact_no" name="contact_no" class="form-control" value="${a.getContact_no()}">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span class="settings-title-font">Email Id</span>
					<input type="text" id="Email_id" name="email_id" class="form-control" value="${a.getEmail_id()}">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12" align="center">
					<input type="submit" value="Update" class="red1" onclick="return validateUserProfile()">
					</div>
					</div>
					</div>
					</div>
					</c:forEach>
</form>
</div>
<div class="tab-pane" id="change_password">
<form action="change-password" method="post" id="changePassword">
<div class="row">
<div class="col-md-12">
				   		<!-- <label class="dashboard-title">Change Password </label> -->
					</div>
					<div class="col-md-12">
					<c:if test="${not empty message }">
							<div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>${message}</strong> 
                            </div>
					</c:if>
					<c:if test="${not empty error}">
							<div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            ${error}
                            </div>
					</c:if>
					</div>
					</div>
					<div class="row">
					<div class="col-md-8 web-box table-background">
					<div class="row form-group stories-details">
					<div class="col-md-12">
					<span class="settings-title-font">Enter Old Password</span>
					<input type="password" id="Old_password" name="old_password" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span class="settings-title-font">Enter New Password</span>
					<input type="password" id="New_password" name="new_password" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12">
					<span class="settings-title-font">Enter Confirm Password</span>
					<input type="password" id="Confirm_password" name="confirm_password" class="form-control">
					</div>
					</div>
					<div class="row form-group">
					<div class="col-md-12" align="center">
					<input type="submit" value="Change" class="red1" onclick="return validatePassword()">
					</div>
					</div>
					</div>
					</div>
</form>
</div>
<div class="tab-pane" id="follow">
<form action="followers" method="post" id="user_follows">
<div class="row">
<div class="col-md-8">
<div class="table-responsive">
								<table class="table table-bordered table-background"> 
									<thead class="thead">
										<tr>
											<th class="settings-title-font"> Celebrity Name </th>
											<th class="settings-title-font"> Action </th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="b" items="${getUserFollowerList}" varStatus="status">
											<tr>
												<td> ${b.getCelebrity_name()}</td>
												<td> 
													
													<%-- <a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${b.getFollow_id()}','')"><button class="red1">Unfollow</button></a> --%>
												 
												  <c:set var="color_code" value="0"/>
											 	 	<c:if test="${b.getParty_id()==1}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${b.getFollow_id()}','')"><button type="button" class="blue1 cursor_pointer">Unfollow</button></a>
																     <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${b.getParty_id()==2}">
																	<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${b.getFollow_id()}','')"><button type="button" class="red1 cursor_pointer">Unfollow</button></a>
																    <c:set var="color_code" value="${color_code+1}"/>
																</c:if>
																<c:if test="${color_code==0}">
                                                                     <a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${b.getFollow_id()}','')"><button type="button" class="others_buttons cursor_pointer">Unfollow</button></a>
                                                                
                                                                </c:if> 
												 </td>
											</tr>
										
										</c:forEach>
									</tbody>
								</table>
							</div>
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
									<div class="row">
									<div class="col-md-12" align="right">
									<div class="save-btn">
										<input type="button" class="red1" value="Yes" onclick="unfollowCelebrity('','')">
										<input type="button" class="red1" value="No" data-dismiss="modal">
									</div>
									</div>
									</div>
								</div>
							</div>
						</div>
		</div>
<div class="modal fade" id="errorModal" role="dialog">
				<div class="modal-dialog">

					<div class="modal-content">
						<div class="modal-body">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<p class="errorMsg"></p>
						</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/WEB-INF/jsp/footer.jsp"%>
			<script src="resources/assets/js/common.js"></script>
<script type="text/javascript">
/* function w3_open() {
	document.getElementById("mySidenav").style.display = "block";
}
function w3_close() {
	document.getElementById("mySidenav").style.display = "none";
}

	$(document).ready(function() {
		$('#sidenav1').addClass("active");
	});
	
	$('.sidefont').bind('click', function(e) {
		$('#sidenav1').removeClass('active');
		$('#edit-profile').removeClass('active');
		
		$('#sidenav2').addClass('active');
		$('#change_password').addClass('active');
	}); */
	
	$(document).ready(function(){
		$('#sidenav1').addClass("active");
		if('${activeTab}'==1)
			{
			$('#sidenav1').addClass('active');
			$('#edit-profile').addClass('active');
			
			$('#sidenav2').removeClass('active');
			$('#change_password').removeClass('active');
			$('#sidenav3').removeClass('active');
			$('#follow').removeClass('active');
			}
		if('${activeTab}'==2)
		{
		$('#sidenav2').addClass('active');
		$('#change_password').addClass('active');
		
		$('#sidenav1').removeClass('active');
		$('#edit-profile').removeClass('active');
		$('#sidenav3').removeClass('active');
		$('#follow').removeClass('active');
		}
		if('${activeTab}'==3)
		{
		$('#sidenav3').addClass('active');
		$('#follow').addClass('active');
		
		$('#sidenav1').removeClass('active');
		$('#edit-profile').removeClass('active');
		$('#sidenav2').removeClass('active');
		$('#change_password').removeClass('active');
		}
		
		if('${successTabActive}'==1)
		{
		$('#sidenav1').addClass('active');
		$('#edit-profile').addClass('active');
		
		$('#sidenav2').removeClass('active');
		$('#change_password').removeClass('active');
		$('#sidenav3').removeClass('active');
		$('#follow').removeClass('active');
		}
	if('${successTabActive}'==2)
	{
	$('#sidenav2').addClass('active');
	$('#change_password').addClass('active');
	
	$('#sidenav1').removeClass('active');
	$('#edit-profile').removeClass('active');
	$('#sidenav3').removeClass('active');
	$('#follow').removeClass('active');
	}
	if('${successTabActive}'==3)
	{
	$('#sidenav3').addClass('active');
	$('#follow').addClass('active');
	
	$('#sidenav1').removeClass('active');
	$('#edit-profile').removeClass('active');
	$('#sidenav2').removeClass('active');
	$('#change_password').removeClass('active');
	}
	
	var max_height=$("#my_header").height()+$("#my_footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
	
	});
</script>
</body>
</html>