<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>RSBS - Blacklist Websites</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body style="font-family: roboto; background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp" %>
	<div id="my_page_container" class="container main-container inside-page-padding min-page-height" style="width:100%">
		 <div class="row">
			 <div class="col-sm-2">
			          <ul class="nav flex-column">
										  <li id="manage-politicians" class="side-nav-item side-nav-padding">
										    	<a class="side-nav-link" href="admin-home">Manage Politicians</a>
										  </li>
										  <!-- <li class="side-nav-item">
										    	<a class="side-nav-link" href="#">General Setting </a>
										  </li> -->
										  <li class="side-nav-item side-nav-padding">
										   		 <a class="side-nav-link" href="manage-parties">Manage Parties</a>
										  </li>
										  <li class="side-nav-item side-nav-padding active">
										   		 <a class="side-nav-link" href="blacklist-website">Black-List Websites</a>
										  </li>
										  <li class="side-nav-item side-nav-padding">
										   		 <a class="side-nav-link" href="search-keys">User Search</a>
										  </li>
										  <li class="side-nav-item side-nav-padding">
										   		 <a class="side-nav-link" href="add-blogs">Blogs</a>
										  </li>
								</ul>
		  </div>
		  <div class="col-sm-10 set-font">
			  <form method="post" id="blacklist" action="blacklist-website">
				   <div class="col-sm-12">
				   		<!-- <label class="dashboard-title">Black List </label> -->
					</div>
					<div class="col-sm-12">
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
					<div class="col-sm-12 web-box table-background">
						<div class="row form-group" style="margin-top:20px">
							<div class="col-sm-2">
								<label> Enter URL: </label>
							</div>
							<div class="col-sm-7">
								<input type="text" id="website_name" name="website_name" class="form-control">
							</div>
							<div class="col-sm-2">
								<button type="submit" class="red1 cursor_pointer" onclick="return validateBlacklistWebsite()">Add to list</button>
							</div>
						</div>
						<div class="col-sm-12 show-website">
							<div class="table-responsive">
								<table class="table table-bordered"> 
									<thead class="thead">
										<tr>
											<th> Website Url </th>
											<th> Action </th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="b" items="${blacklist_website}">
											<tr>
												<td> ${b.getWebsite_name()}</td>
												<td> 
													<a href="#" onclick="editBlackList_website('${b.getWebsite_name()}','${b.getWeb_id()}')"><img src="resources/assets/images/edit.png" width="40px" height="40px"></a>
													<a href="#"  onclick="deleteBlackList_website('${b.getWeb_id()}')"><img src="resources/assets/images/delete.png" width="40px" height="40px"></a>
													
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
								<div class="modal-header modal-header-background">
									Edit Blacklist Website Url
								</div>
								<div class="modal-body">
									<div class="row form-group">
										<div class="col-sm-8">
										<label> Edit Website Url </label>
											<input type="text" class="form-control" name="edited_website_name">	
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<span id="blacklist_error" class="red-text"></span>
										</div>
									</div>
									<div class="row">
									<div class="col-md-12">
									<div class="save-btn">
										<input type="button" class="btn-follow cursor_pointer" value="Update" onclick="editBlackList_website('','')">
										<input type="button" class="btn-follow cursor_pointer" value="Cancel" data-dismiss="modal">
									</div>
									</div>
									</div>
								</div>
							</div>
						</div>
		</div>
		<div class="modal fade" id="delete_website">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									Are you sure you want to delete this website URL?
								</div>
								<div class="modal-body">
									<div class="row">
									<div class="col-md-12" align="right">
									<div class="save-btn">
										<input type="button" class="btn-follow cursor_pointer" value="Yes" onclick="deleteBlackList_website('')">
										<input type="button" class="btn-follow cursor_pointer" value="No" data-dismiss="modal">
									</div>
									</div>
									</div>
								</div>
							</div>
						</div>
		</div>
				</form>
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
<script type="text/javascript">
$(document).ready(function(){
	$("#dashboard").addClass('active-nav-link');
	var max_height=$("#my_header").height()+$("#my_footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
});
</script>
</body>
</html>