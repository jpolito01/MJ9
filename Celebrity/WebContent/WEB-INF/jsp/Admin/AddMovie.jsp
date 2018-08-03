<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Celebrity - Add Movie</title>
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
			            <li><a href="user-search-result">User Searches</a></li>
			            <li><a href="blacklist-website">Black List Websites</a></li>
                        <li><a href="movie-blogs">Blogs</a></li>
			            <li><a href="requested-celebrities" class="sidenav-link">Requested Celebrities</a></li>
			          </ul>
			        </div><!--/.nav-collapse -->
			      </div>
			    </div>
		  </div>
		  <div class="col-sm-9">
		  	<c:if test="${not empty message}">
						<div class="col-sm-12">
							<div class="alert alert-success">
							 	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								${message}
							</div>
						</div>
					</c:if>
		  <c:if test="${!edit_movie && edit_movie!='yes'}">	
		   <div class="col-sm-12">
			   		<label class="dashboard-title" id="addMovieTitle">Add Movie</label>
		   </div>
		   <form:form action="add-movie" method="post" enctype="multipart/form-data" >
					      		<div class="row">
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-3">
						       				Enter Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="celebrity_name" id="celebrity_name_add" type="text">
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
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Upload movie Photo
						       			</div>
						       			<div class="col-sm-9">
						       				<input type="file" name="d_file" id="profile_photo_name" />
						       			</div>
						       		</div>
						       		</div>
						       		
					       		<!-- </div> -->
					       		<div class="row">
					       			<div align="center">
					       				<input type="submit" class="btn-flat btn-primary" value="Next" onclick="return validateMovieForm()">
					       				<!-- <input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close"> -->
					       			</div>
					       		</div>
					       		<br />
					       	</form:form>
					       	</c:if>
					       	
					     <!--   	Edit Movie -->
					     	<c:if test="${not empty edit_movie && edit_movie=='yes'}">	
					  	
		   <div class="col-sm-12">
			   		<label class="dashboard-title" id="addMovieTitle">Edit Movie</label>
		   </div>
					  
					       	   <form:form action="edit-celebrity" method="post" enctype="multipart/form-data" >
					       	     	<c:forEach var="ce" items="${edit_celebrity_info}">
					      		<div class="row">
					      		<div class="col-sm-12 padding-one">
					      				<c:if test="${empty ce.getProfile_photo_path()}">
					      					<img src="resources/assets/images/user.png" height="200"  width="200">
					      				</c:if>
					      				<c:if test="${not empty ce.getProfile_photo_path()}">
					      					<img src="${ce.getProfile_photo_path()}" height="200"  width="200">
					      				</c:if>
					      			</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-3">
						       				Enter Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="celebrity_name" id="celebrity_name_edit" type="text" value="${ce.getCelebrity_name()}">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Movie Description:
						       			</div>
						       			<div class="col-sm-9">
						       				<textarea name="celebrity_bio" id="celebrity_bio" rows="5" class="form-control">${ce.getCelebrity_bio()}</textarea>
						       			</div>
						       		</div>
						       		
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Facebook Page Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input type="text" class="form-control" value="${ce.getFacebok_page_name()}" name="facebok_page_name">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Twitter Handle Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input type="text" class="form-control" value="${ce.getTwitter_handle_name()}" name="twitter_handle_name">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Instagram Page Name:
						       			</div>
						       			<div class="col-sm-9">
						       				<input type="text" class="form-control" value="${ce.getInstagram_page_name()}" name="instagram_page_name">
						       			</div>
						       		</div>
						       		<input type="hidden" name="category_name" id="category_name" value="6">
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Upload movie Photo
						       			</div>
						       			<div class="col-sm-9">
						       				<input type="file" name="d_file" id="profile_photo_name" />
						       			</div>
						       		</div>
						       		</div>
						       		
					       		<!-- </div> -->
					       		<div class="row">
					       			<div align="center">
					       				<input type="submit" class="btn-flat btn-primary" value="Next" onclick="return validateMovieForm1()">
					       				<!-- <input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close"> -->
					       			</div>
					       		</div>
					       		<br />
					       		</c:forEach>
					       	</form:form>
					       	</c:if>
					       	
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
		var max_height=$("#my_header").height()+$("#footer").height();
		$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	});
	</script>
</body>
</html>