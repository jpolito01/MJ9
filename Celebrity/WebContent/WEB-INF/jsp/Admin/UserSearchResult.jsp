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
			            <li class="active-sidenav"><a href="user-search-result">User Searches</a></li>
			            <li><a href="blacklist-website">Black List Websites</a></li>
			            <li><a href="movie-blogs">Blogs</a></li>
			            <li><a href="requested-celebrities" class="sidenav-link">Requested Celebrities</a></li>
			          </ul>
			        </div><!--/.nav-collapse -->
			      </div>
			    </div>
		  </div>
		  <div class="col-sm-9 set-font">
		  	<label class="dashboard-title">User Searches </label>
			  	<div class="col-sm-12">
			  		
			  		<c:if test="${not empty error}">
						<div class="col-sm-12">
							<div class="alert alert-danger">
							 	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								${error}
							</div>
						</div>
					</c:if>
					<c:if test="${not empty message}">
						<div class="col-sm-12">
							<div class="alert alert-success">
							 	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								${message}
							</div>
						</div>
					</c:if>
				  	<form   method="post" id="frm_user_searches">
				  		<table class="table table-bordered">
				  			<thead class="thead">
				  				<tr>
				  					<td>Searched Term</td>
				  					<td>Count </td>
				  					<td>Add Celebrity</td>
				  					<td>Action</td>
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<c:forEach var="s" items="${search_terms}"> 
				  					<tr>
				  						<td>${s.getSearch_key()} </td> 
				  						<td>${s.getSearch_count()} </td> 
				  						<td><a href="#" onclick="AddCelebrity('${s.getSearch_key()}','${s.getId()}')"> Add </a> </td> 
				  						<td> 
											<a href="#" onclick="editSearchTerm('${s.getId()}','${s.getSearch_key()}')">
												<img src="resources/assets/images/edit.png" height="40px" width="40px"> 
											</a> 
				  							<a href="#" onclick="DeleteSearchTerm('${s.getId()}')">
												<img src="resources/assets/images/delete.png" height="40px" width="40px"> 
											</a>
				  						</td> 
				  					</tr>
				  				</c:forEach>
				  				
				  			</tbody>
				  		</table>
				  		<div id="mod_editSearchTerm" class="modal fade" role="dialog">
						 	<div class="modal-dialog">
								<div class="modal-content">
								      <div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal">&times;</button>
								        <h4 class="modal-title">Edit Search Term</h4>
								      </div>
								      <div class="modal-body">
								      		<div class="row">
								      			<div class="col-sm-12">
								      				<div class="col-sm-4">
								      					Search term
								      				</div>
								      				<div class="col-sm-8">
								      					<input type="text" name="search_term" id="search_term" class="form-control">
								      				</div>
								      			</div>
								      		</div>
								      </div>
								      <div class="modal-footer">
								      	<button type="button" class="btn-flat" name="edit_search" id="edit_search" onclick="editSearchTerm('','')">Save</button>
								      	<button type="button" class="btn-flat" data-dismiss="modal">Close</button>
								      </div>
							     </div>
							   </div>
						</div>
				
				  		
				  		
				  		
				  	</form>
			  	</div>
		  </div>
		  <div id="modal_addCelebrity" class="modal fade" role="dialog">
				 	<div class="modal-dialog modal-lg">
						<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Add Celebrity</h4>
					      </div>
					      <div class="modal-body">
					      	<form:form action="celebrity" method="post" enctype="multipart/form-data" >
					      		<div class="row">
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-3">
						       				Enter Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="celebrity_name" id="celebrity_name" type="text">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Enter Celebrity Bio:
						       			</div>
						       			<div class="col-sm-9">
						       				<textarea class="form-control" name="celebrity_bio" id="celebrity_bio" rows="5"></textarea>
						       			</div>
						       		</div>
						       		
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Facebook Page Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="facebok_page_name" id="facebok_page_name" type="text">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Twitter Handle Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="twitter_handle_name" id="twitter_handle_name" type="text">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Instagram Page Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="instagram_page_name" id="instagram_page_name" type="text">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Select Category
						       			</div>
						       			<div class="col-sm-9">
						       				<c:forEach var="cat" items="${celebrity_category}">
						       					<input type="checkbox" name="category_name" id="category_name" value="${cat.getCategory_id()}"> ${cat.getCategory_name()}
						       				</c:forEach>
						       				
						       			</div>
						       		</div>
						       		
						       		<%-- <div class="col-sm-12 padding-one">
						       			<div class="col-sm-3">
						       				Select Country:
						       			</div>
						       			<div class="col-sm-9">
						       				<select class="form-control" name="country_id" id="country_id" onchange="getState(this)">
						       					<option value="0">Select Country </option>
						       					<c:forEach var="c" items="${country_list}"> 
						       						<option value="${c.getCountry_id()}">${c.getCountry_name()}</option>
						       					</c:forEach>
						       				</select>
						       			</div>
						       		</div> --%>
						       		<!-- <div class="col-sm-12 padding-one">
						       			<div class="col-sm-3">
						       				Select State:
						       			</div>
						       			<div class="col-sm-9">
						       				<select class="form-control" name="state_id" id="state_id">
						       					<option value="0">Select State </option>
						       				</select>
						       			</div>
						       		</div> -->
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Upload profile Photo
						       			</div>
						       			<div class="col-sm-9">
						       				<input type="file" name="d_file" id="profile_photo_name" />
						       			</div>
						       			<input type="hidden" name="search_id">
						       		</div>
					       		</div>
					       		<div class="row">
					       			<div align="center">
					       				<input type="submit" class="btn-flat btn-primary" value="Save" onclick="return validate_celebrity_form();">
					       				<input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close">
					       			</div>
					       		</div>
					       	</form:form>
					      </div>
					    </div>
				  </div>
			</div>
			 <div id="confirm_box" class="modal fade" role="dialog">
				 	<div class="modal-dialog">
						<div class="modal-content">
					      <div class="modal-header">
					      Do you want to delete this search term?
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					      </div>
					      <div class="modal-body">
					      		
					      		<div>
					      			<button type="button" class="btn-flat" onclick="DeleteSearchTerm('')">Yes</button>
					      			<button type="button" class="btn-flat" data-dismiss="modal">No</button>
					      		</div>
					      </div>
					    </div>
				</div>
		</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script src="resources/assets/js/admin.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	
});
	function AddCelebrity(search_term,id)
	{
		$("#modal_addCelebrity").modal("show");
		$("#celebrity_name").val(search_term);
		$("input[name='search_id']").val(id);
	}
</script>
</body>

</html>