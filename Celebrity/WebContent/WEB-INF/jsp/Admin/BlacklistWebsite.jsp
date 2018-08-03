<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<head>
  <title>Celebrity</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
</head>
<body style="font-family: Open sans;">
<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div id="my_page_container" class="container page-padding-top my_container_height">
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
			            <li><a href="celebrity">Celebrity</a></li>
			            <!-- <li><a href="band">Band</a></li>
			            <li><a href="sport">Sport</a></li> -->
			            <li><a href="released-movies">Released Movies</a></li>
			            <!-- <li><a href="#">Released Series</a></li> -->
			            <li><a href="general-settings">Manage Categories</a></li>
			            <li><a href="manage-role" class="sidenav-link">Manage Celebrity Role</a></li>
			            <li><a href="user-search-result">User Searches</a></li>
			            <li class="active-sidenav"><a href="blacklist-website">Black List Websites</a></li>
			            <li><a href="movie-blogs">Blogs</a></li>
			            <li><a href="requested-celebrities" class="sidenav-link">Requested Celebrities</a></li>
			          </ul>
			        </div><!--/.nav-collapse -->
			      </div>
			    </div>
		  </div>
		  <div class="col-sm-9 set-font">
			  <form method="post" id="blacklist" action="blacklist-website">
				   <div class="col-sm-12">
				   <div class="col-md-6">
				   		<label class="dashboard-title">Black List </label>
				   	</div>
				   	<!-- <div class="col-md-6">
				   	<button type="button" class="btn-flat" onclick="sentTextMail()">Sent text mail</button>
				   	</div> -->
					</div>
					<div class="col-sm-12">
					<c:if test="${not empty message }">
							<div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							  <strong>Success!</strong> ${message}
							</div>
					</c:if>
					<c:if test="${not empty error}">
							<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							  <strong>Success!</strong> ${error}
							</div>
					</c:if>
					</div>
					<div class="col-sm-12 web-box">
						<div class="col-sm-12 add-website">
							<div class="col-sm-3">
								<label> Enter Website Name </label>
							</div>
							<div class="col-sm-7">
								<input type="text" name="website_name" class="form-control">
							</div>
							<div class="col-sm-1">
								<button type="submit" class="btn-flat">Add to list</button>
							</div>
						</div>
						<div class="col-sm-12 show-website">
							<div class="table-responsive">
								<table class="table table-bordered"> 
									<thead class="thead">
										<tr>
											<td> Website Url </td>
											<td> Action </td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="b" items="${blacklist_website}">
											<tr>
												<td> ${b.getWebsite_name()}</td>
												<td> 
													
													<a href="#"  onclick="deleteBlackList_website('${b.getWeb_id()}')"><img src="resources/assets/images/delete.png" width="40px" height="40px"></a>
													<a href="#" onclick="editBlackList_website('${b.getWebsite_name()}','${b.getWeb_id()}')"><img src="resources/assets/images/edit.png" width="40px" height="40px"></a>
												 </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="modal fade" id="edit_blacklist_website">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									Edit Website
								</div>
								<div class="modal-body">
									<div class="col-sm-12">
										<div class="col-sm-4">
											<label> Edit Website Name </label>
										</div>
										<div class="col-sm-8">
											<input type="text" class="form-control" name="edited_website_name">	
										</div>
									</div>
									
									<div class="save-btn">
										<input type="button" class="btn-flat" value="Update" onclick="editBlackList_website('','')">
										<input type="button" class="btn-flat" value="Cancel" data-dismiss="modal">
									</div>
								</div>
							</div>
						</div>
		</div>
				</form>
		</div>
		
	</div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script src="resources/assets/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
});
</script>
</body>
</html>