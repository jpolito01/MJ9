<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RSBS - Manage Parties</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
  <link rel="stylesheet" href="resources/assets/css/all-pages.css">
</head>
<body style="font-family: roboto; background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp" %>
	<div id="my_page_container" class="container inside-page-padding min-page-height" style="width:100%">
		 <div class="row">
			 <div class="col-sm-2">
			          <ul class="nav flex-column">
										  <li id="manage-politicians" class="side-nav-item side-nav-padding">
										    	<a class="side-nav-link" href="admin-home">Manage Politicians</a>
										  </li>
										  <!-- <li class="side-nav-item">
										    	<a class="side-nav-link" href="#">General Setting </a>
										  </li> -->
										  <li class="side-nav-item side-nav-padding active">
										   		 <a class="side-nav-link" href="manage-parties">Manage Parties</a>
										  </li>
										  <li class="side-nav-item side-nav-padding">
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
			  <form method="post" id="manage-parties" action="manage-parties">
				   <div class="col-sm-12">
				   		<!-- <label class="dashboard-title">Manage Parties </label> -->
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
								<label> Enter Party Name: </label>
							</div>
							<div class="col-sm-7">
								<input type="text" name="party_name" id="party_name" class="form-control">
							</div>
							<div class="col-sm-2">
								<button type="submit" class="red1 cursor_pointer" onclick="return validateParty()">Add to list</button>
							</div>
						</div>
						<div class="col-sm-12 show-website">
							<div class="table-responsive">
								<table class="table table-bordered"> 
									<thead class="thead">
										<tr>
											<th> Party Name </th>
											<th> Action </th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="b" items="${getAllPartyInfo}">
											<tr>
												<td> ${b.getParty_name()}</td>
												<td> 
													<a href="#" onclick="editParty('${b.getParty_name()}','${b.getParty_id()}')"><img src="resources/assets/images/edit.png" width="40px" height="40px"></a>
													<a href="#"  onclick="deleteParty('${b.getParty_id()}')"><img src="resources/assets/images/delete.png" width="40px" height="40px"></a>
													
												 </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="modal fade" id="edit_party_name">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header modal-header-background">
									<h5 class="modal-title">Edit Party</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span></button>
								</div>
								<div class="modal-body">
									<div class="row form-group">
										<div class="col-sm-8">
										<label> Edit Party Name </label>
											<input type="text" class="form-control" name="edited_party_name">	
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<span id="party_error" class="red-text"></span>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="save-btn">
												<input type="button" class="btn-follow cursor_pointer" value="Update" onclick="editParty('','')">
												<input type="button" class="btn-follow cursor_pointer" value="Cancel" data-dismiss="modal">
											</div>
										</div>
									</div>
									<!-- <div class="row">
										<div class="col-sm-12">
											<span style="color:#002868">Note:</span> If you delete this party then it affects on the design and the user may not be able to see the follow button.
										</div>
									</div> -->
								</div>
							</div>
						</div>
		</div>
		<div class="modal fade" id="delete_party">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header modal-header-background">
									<h5 class="modal-title">Delete Party</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
								</div>
								<div class="modal-body">
								<div class="row">
										<div class="col-sm-12">
											Are you sure you want to delete this party?
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<span style="color:#002868; font-weight:500">Note:</span> If you delete this party then it affects on the design and the user may not be able to see the follow button.
										</div>
									</div>
									
								</div>
								<div class="modal-footer">
									<div class="row">
									<div class="col-md-12" align="right">
									<div class="save-btn">
										<input type="button" class="btn-follow cursor_pointer" value="Yes" onclick="deleteParty('')">
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