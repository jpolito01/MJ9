<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <title>Settings</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div id="my_page_container" class="container page-padding-top my_container_height" style="width: 100%;">
		
		
		 <div class="row">
  <div class="col-sm-3">
    <div class="sidebar-nav">
      <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <span class="visible-xs navbar-brand">Sidebar menu</span>
        </div>
        <form id="menu_bar" method="post">
        <div class="navbar-collapse collapse sidebar-navbar-collapse" id="celebrity-nav">
          <ul class="nav navbar-nav"> <!-- Do not change these ID attributes -->
            <li id="li_update_profile"><a href="#" id="update_profile" onclick="ChahgeSetting(this)">Update Profile</a></li>
            <li id="li_change_password"><a href="#" id="change_password" onclick="ChahgeSetting(this)">Change Password</a></li>
            <li id="li_follow_list"><a href="#" id="follow_list" onclick="ChahgeSetting(this)">Follow</a></li>
           <c:if test="${role_id==2}">
	            <li id="li_activate_acc"><a href="#" id="activate_acc" onclick="ChahgeSetting(this)">Activate/Deactivate Account</a></li>
	            <!-- <li id="li_follow_list"><a href="#" id="follow_list" onclick="ChahgeSetting(this)">Follow</a></li> -->
            </c:if>
          </ul>
        </div><!--/.nav-collapse -->
        </form>
      </div>
    </div>
  </div>
  <div class="col-sm-9">
  	<c:if test="${not empty message}">
  		<div class="alert alert-success">
  		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		  <strong>Success!</strong> ${message}
		</div>
  	</c:if>
  
	<div id="err_msg"></div>
	
  	<c:if test="${activated_tab=='update_profile'}">
	    <div class="col-sm-11 UpdateProfile">
	    		<div class="setting-title">
	    			<label class="dashboard-title"> Update Profile </label>
	    		</div>
	    		<div class="col-sm-12 setting-content">
		    		<form name="updateProfile" action="update-profile" method="post">
		    			<c:forEach items="${user_profile}" var="up">
		    				<div class="col-sm-12 padding-one">
		    					<div class="col-sm-2">
		    						<label> Name : </label>
		    					</div>
		    					<div class="col-sm-4">
		    						<input class="form-control" type="text" name="first_name" value="${up.getFirst_name()}">
		    					</div>
		    					<div class="col-sm-4">
		    						<input class="form-control" type="text" name="last_name" value="${up.getLast_name()}"> 
		    					</div>
		    				</div>
		    				<div class="col-sm-12 padding-one">
		    					<div class="col-sm-2">
		    						<label> Address :  </label>
		    					</div>
		    					<div class="col-sm-8">
		    						<textarea class="form-control" name="address">${up.getAddress()}</textarea>
		    					</div>
		    				</div>
		    				<div class="col-sm-12 padding-one">
		    					<div align="center">
		    						<input class="btn-flat" type="submit" value="Save Changes">
		    						<input class="btn-flat" type="button" value="Cancel">
		    					</div>
		    				</div>
		    				</c:forEach>
		    			</form>
	    		</div>
	    </div>
    </c:if>
    <c:if test="${activated_tab=='change_password'}">
    <div class="ChangePassword">
    	<div class="panel panel-primary">
    	<form name="changePassword" id="ChangePassword" method="post" action="change_password">
    		<div class="panel-heading">
    			Change Password
    		</div>
    		<div class="panel-body">
    			<div class="col-sm-12 padding-one" >
    				<div class="col-sm-3">
    					<label> Enter Old Password </label>
    				</div>
    				<div class="col-sm-9">
    					<input type="password" name="old_password" class="form-control" placeholder="Old Password" onblur="CheckPassword(this)">
    				</div>
    			</div>
    			<div class="col-sm-12 padding-one">
    				<div class="col-sm-3">
    					<label> Enter Password </label>
    				</div>
    				<div class="col-sm-9">
    					<input type="password" name="new_password" class="form-control" placeholder="New Password">
    				</div>
    			</div>
    			
    			<div class="col-sm-12 padding-one">
    				<div class="col-sm-3">
    					<label> Confirm Password </label>
    				</div>
    				<div class="col-sm-9">
    					<input type="password" name="confirm_password" class="form-control" placeholder="Confirm Password">
    				</div>
    			</div>
    			<div class="col-sm-12 padding-one">
    				<div align="center">
    					<input type="submit" value="Save"  onclick="return validateChangePassword();" class="btn btn-primary">
    					<input type="button" value="cancel" class="btn btn-primary">
    				</div>
    			</div>
    			
    		</div>
    		</form>
    	</div>
    </div>
    </c:if>
    <c:if test="${activated_tab=='follow_list'}">
     <div class="follow_list">
    	<!-- <div class="panel panel-primary">
    		<div class="panel-heading">
    			Follow to
    		</div>
    		<div class="panel-body">
    			
    		</div>
    	</div> -->
    	<form action="followers" method="post" id="user_follows">
<div class="row">
<div class="col-md-12">
<div class="table-responsive">
								<table class="table table-bordered table-background"> 
									<thead class="thead">
										<tr>
											<th> Celebrity Name </th>
											<th> Action </th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="b" items="${getUserFollowerList}" varStatus="status">
											<tr>
												<td> ${b.getCelebrity_name()}</td>
												<td> 
													
													<a href="#" data-toggle="modal" data-target="#unfollowCelebrityModal" onclick="unfollowCelebrity('${b.getId()}','')"><button class="btn-flat">Unfollow</button></a>
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
    </c:if>
    
     <c:if test="${activated_tab=='activate_acc'}">
	     <div class="ActivateAcc">
	    	<div class="panel panel-primary">
	    		<div class="panel-heading">
	    			Activate deactivate Acc
	    		</div>
	    		<div class="panel-body">
	    			
	    		</div>
	    	</div>
	    </div>
    </c:if>
    
  </div>
</div>
	</div>

<div class="modal fade" id="unfollowCelebrityModal">
						<div class="modal-dialog modal-lg" style="width: 40%;">
							<div class="modal-content">
								<div class="modal-header">
									Are you sure you want to unfollow this celebrity?
								</div>
								<div class="modal-body">
									<div class="row">
									<div class="col-md-12" align="right">
									<div class="save-btn">
										<input type="button" class="btn-flat" value="Yes" onclick="unfollowCelebrity('','')">
										<input type="button" class="btn-flat" value="No" data-dismiss="modal">
									</div>
									</div>
									</div>
								</div>
							</div>
						</div>
		</div>

<!-- user-setting -->
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script>
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	
	var tab='${activated_tab}';
	tab="li_"+tab;
	$("#"+tab).addClass("active-sidenav");
});
function ChahgeSetting(obj)
{
	document.getElementById("menu_bar").action="user-setting?menu_name="+obj.id;
	document.getElementById("menu_bar").method="POST";
	document.getElementById("menu_bar").submit();
}

</script>

</body>
</html>