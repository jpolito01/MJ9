<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<head>
  <title>Celebrity</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="resources/assets/css/jquery.dataTables.css" rel="stylesheet">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div id="my_page_container" class="container page-padding-top my_container_height" style="width: 99%;">
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
			            <li class="active-sidenav"><a href="released-movies">Released Movies</a></li>
			            <!-- <li><a href="#">Released Series</a></li> -->
			            <li><a href="general-settings">Manage Categories</a></li>
			            <li><a href="manage-role" class="sidenav-link">Manage Celebrity Role</a></li>
			            <li><a href="user-search-result" class="sidenav-link">User Searches</a></li>
			            <li><a href="blacklist-website" class="sidenav-link">Black List Websites</a></li>
                        <li><a href="movie-blogs">Blogs</a></li>
			            <li><a href="requested-celebrities" class="sidenav-link">Requested Celebrities</a></li>
			          </ul>
			        </div><!--/.nav-collapse -->
			      </div>
			    </div>
		  </div>
		  <div class="col-sm-9 set-font">
		  <c:if test="${not empty message}">
						<div class="col-sm-12">
							<div class="alert alert-success">
							 	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								${message}
							</div>
						</div>
					</c:if>
		   <div class="col-sm-12">
			   		<label class="dashboard-title">Released Movies </label>
			   		<a href="add-movie">
			   		<input type="button" class="btn-green pull-right" value="Add Movie"></a>
		</div>
				<div class="col-sm-12 padding-one">
							<table id="example" class="table table-bordered">
								<thead class="thead">
									<tr>
										<td> Movie Name </td> 
										<td> Activate/deactivate </td>
										<td> Delete </td>
									</tr>
								</thead>
								<c:forEach var="ci" items="${movies_list}">
								<tr>
									<td> 
<%-- 										<a href="#" onclick="Showcelebrity_Details('${ci.getCelebrity_id()}')">${ci.getCelebrity_name()}</a>
 --%>					
 ${ci.getCelebrity_name()}				
 </td>
									<td>
										<c:if test="${ci.getIs_active()==1}">
											<a href="#" onclick="activate_deactivate('${ci.getCelebrity_id()}','0','','movie')">Deactivate</a>
										</c:if>
										 <c:if test="${ci.getIs_active()==0}">
										 	<a href="#" onclick="activate_deactivate('${ci.getCelebrity_id()}','1','','movie')">Activate</a>
										 	
										 </c:if>
										
									</td>
									<td> <%-- <input class="btn-flat btn-info" type="button" value="delete" onclick="delete_celebrity('${ci.getCelebrity_id()}','no-action')">  --%>
										<a href="#" onclick="delete_movie('${ci.getCelebrity_id()}','no-action')">
											<img src="resources/assets/images/delete.png" height="40px" width="40px"> 
										</a>
										<a href="#" onclick="edit_movie('${ci.getCelebrity_id()}')"> 
											<img src="resources/assets/images/edit.png" height="40px" width="40px">
										</a>
									</td>
								</tr>
								</c:forEach>
							</table>
	
				</div>
		  
					<%-- <c:if test="${fn:length(edit_celebrity_info)<=0}"> --%>
				<div id="modal_addCelebrity" class="modal fade" role="dialog">
				 	<div class="modal-dialog modal-lg">
						<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Add Movie</h4>
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
						       				Movie Description:
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
						       		<input type="hidden" name="category_name" id="category_name" value="6">
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Upload movie Photo
						       			</div>
						       			<div class="col-sm-9">
						       				<input type="file" name="d_file" id="profile_photo_name" />
						       			</div>
						       		</div>
					       		<!-- </div> -->
					       		<div class="row">
					       			<div align="center">
					       				<input type="submit" class="btn-flat btn-primary" value="Save" onclick="return validate_movie_form();">
					       				<input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close">
					       			</div>
					       		</div>
					       	</form:form>
					      </div>
					    </div>
				  </div>
				</div>
				
				
				<div id="modal_editCelebrity" class="modal fade" role="dialog">
				 	<div class="modal-dialog modal-lg">
						<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Update Movie Details</h4>
					      </div>
					      <div class="modal-body">
					      	<form:form action="edit-celebrity" method="post" enctype="multipart/form-data" >
					      		<div class="row">
					      			<div class="col-sm-12 padding-one">
					      					<img src="resources/assets/images/user.png" height="200"  width="200" id="celebrity_profile_photo">					      				
					      			</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<span style="color: red">*</span> <label class="form-label">Enter Name: </label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input class="form-control" name="celebrity_name" id="celebrity_name_update" type="text" >
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-4">
						       				<label class="form-label">Movie Description:</label>
						       			</div>
						       			<div class="col-sm-8">
						       				<%-- <input class="form-control" name="celebrity_bio" id="celebrity_bio" type="text" value="${ce.getCelebrity_bio()}"> --%>
						       				<textarea name="celebrity_bio" id="celebrity_bio_update" rows="5" class="form-control"></textarea>
						       			</div>
						       		</div>
						       	
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label"> Facebook page</label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input type="text" class="form-control" name="facebok_page_name" id="facebok_page_name_update">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label"> Twitter Handle Name :</label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input type="text" class="form-control"  name="twitter_handle_name" id="twitter_handle_name_update">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label"> Instgram Page Name : </label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input type="text" class="form-control" name="instagram_page_name" id="instagram_page_name_update">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-4">
						       				<label class="form-label">Upload movie Photo</label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input type="file" name="d_file" id="profile_photo_name"/>
						       			</div>
						       		</div>
						       		<input type="hidden" name="category_name" id="category_name" value="6">
						       		
					       		</div>
					       		
					       		<div class="row">
					       			<div align="center">
					       				<input type="submit" class="btn-flat" value="Update" onclick="return validate_movie_form_update();">
					       				<!-- <a href="celebrity"><input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close"></a> -->
					       				<button type="button" class="btn-flat"><a href="released-movies" style="color: white; text-decoration: none;"> close </a> </button>
					       			</div>
					       		</div>
					       	</form:form>
					     </div>
					    </div>
				  </div>
				</div>
				<div id="mod_confirm" class="modal fade" role="dialog">
			 	<div class="modal-dialog">
					<div class="modal-content">
				      <div class="modal-header">
				      		<label id="modal-title"></label>
				      </div>
				      <div class="modal-body">
				      		<span id="span_msg"> </span>
				      </div>
				      <div class="row">
				      	<div class="col-sm-12" align="right">
				      		<div class="col-sm-12 padding-one">
							<input type="button" id="btn_confirm_yes" class="btn-flat" value="Yes" >
				      		<input type="button" class="btn-flat" value="No" data-dismiss="modal" onclick="CELEBRITY_ID=0;IS_ACTIVE=-1;">
				      		</div>
				      	</div>				      
				      </div>
				     </div>
				  </div>
			</div>
				
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
	<script src="resources/assets/js/jquery.dataTables.min.js"></script>
	<script src="resources/assets/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	
	$('#example').DataTable();
});
</script>
</body>
</html>
