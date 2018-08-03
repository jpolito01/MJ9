<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<head>
  <title>Manage Celebrity Role</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div class="container padding-zero page-padding-top my_container_height">
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
			        <div class="navbar-collapse collapse sidebar-navbar-collapse" id="celebrity-nav">
			          <ul class="nav navbar-nav">
			            <li class="side-tab"><a href="celebrity">Celebrity</a></li>
			            <!-- <li><a href="band">Band</a></li>
			            <li><a href="sport">Sport</a></li> -->
			            <li><a href="released-movies">Released Movies</a></li>
			            <!-- <li><a href="#">Released Series</a></li> -->
			            <li><a href="general-settings">Manage Categories</a></li>
			            <li class="active-sidenav"><a href="manage-role" class="sidenav-link">Manage Celebrity Role</a></li>
			            <li><a href="user-search-result">User Searches</a></li>
			            <li><a href="blacklist-website">Black List Websites</a></li>
			            <li><a href="movie-blogs">Blogs</a></li>
			            <li><a href="requested-celebrities" class="sidenav-link">Requested Celebrities</a></li>
			          </ul>
			        </div><!--/.nav-collapse -->
			      </div>
			    </div>
		  </div>
		  <div class="col-sm-9 set-font"> 
		  <form method="post" id="ManageRole">
		  <c:if test="${not empty message}">
						<div class="col-sm-12">
							<div class="alert alert-success">
							 	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								${message}
							</div>
						</div>
					</c:if>
			   <div class="col-sm-12 padding-one">
			   		<!-- <input id="manage_role" type="button" class="manage-role-tab" value="Manage role" onclick="ManageRole(this)"> -->
			   		<!-- <input id="manage_country" type="button" class="general-setting-tab" value="Manage Country/State" onclick="ManageCategory(this)"> -->
				<span class="dashboard-title">Manage Role</span>
				</div>
				<%-- <c:if test="${tab_name=='manage_category'}"> --%>
				<div class="col-sm-12 padding-one">
					<div class="col-sm-12 setting-contents">
							<div class="col-sm-12 padding-one">
								<div class="col-sm-3">	
									<label>Enter New Role :  </label> 
								</div>
								<div class="col-sm-6">
									<input type="text" name="caracter_name" class="form-control">
								</div>
								<div class="col-sm-1">
									<input type="submit" class="btn-flat" value="Save" onclick="return saveRole()">
								</div>
							</div>
							<div class="col-sm-12" style="margin-top: 2%;">
								<div class="table-responsive">
									<table class="table table-bordered ">
										<thead class="thead">
											<tr>
												<th> Sr.No. </th>
												<th> Role name </th>
												<th> Delete</th>
												<th> Edit</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="cat" items="${all_roles}" varStatus="v">
												<tr>
													<td>${v.index+1} </td>
													<td id="td_role_name_${cat.getId()}">
															<span id="span_role_name_${cat.getId()}"> ${cat.getCaracter_name()} </span>
													</td>
													<td> <a href="#" data-toggle="modal" data-target="#delete-role" onclick="DeleteRole_id('${cat.getId()}')">Delete </a></td>
													<td>
														<%-- <button id="edit_${cat.getCategory_id()}" type="button" onclick="return EditCategory_name(this,'${cat.getCategory_id()}','${cat.getCategory_name()}')">Edit</button> --%>
														<a id="edit_${v.index+1}" href="#" onclick="EditRole_name(this,'${cat.getId()}','${cat.getCaracter_name()}')">Edit</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
				</div>
				<%-- </c:if> --%>
				
			</form>	
			</div>
		</div>
	</div>
	<div id="delete-role" class="modal fade" role="dialog">
				 	<div class="modal-dialog">
						<div class="modal-content">
					      <div class="modal-header">
					      Are you sure you want to delete this role?
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					      </div>
					      <div class="modal-body">
					      	   
					      		<div>
					      			<button type="button" class="btn-flat" onclick="DeleteRole_id('')">Yes</button>
					      			<button type="button" class="btn-flat" data-dismiss="modal">No</button>
					      		</div>
					      </div>
					    </div>
				</div>
		</div>
		<div id="edit-role" class="modal fade" role="dialog">
				 	<div class="modal-dialog">
						<div class="modal-content">
					      <div class="modal-header">
					      Are you sure you want to edit this role?
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					      </div>
					      <div class="modal-body">
					      	   
					      		<div>
					      			<button type="button" class="btn-flat" onclick="EditRole_name('','','')">Yes</button>
					      			<button type="button" class="btn-flat" data-dismiss="modal">No</button>
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
	<script src="resources/assets/js/admin.js"></script>
	<script>
		$(document).ready(function(){
				$("#"+'${tab_name}').addClass("setting_active");
		});
	</script>
	
</body>
</html>
