<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
  <title>Celebrity</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/assets/css/common-style.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <style type="text/css">
  .Editor-editor{
	outline: 1px solid rgba(0,0,0,.15) !important;
}
  </style>
</head>
<body style="font-family: Open sans">
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<link href="resources/assets/css/editor.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<div id="my_page_container" class="container page-padding-top my_container_height" style="width: 98%;">
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
			            <li><a href="blacklist-website">Black List Websites</a></li>
			            <li class="active-sidenav"><a href="movie-blogs">Blogs</a></li>
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
  			   		<label class="dashboard-title">Blogs</label>
  <input type="button" class="btn-green pull-right" value="Add Blogs" data-toggle="modal" data-target="#movieBlogModal">
    </div>
  
<div class="col-sm-12 padding-one">
							<table id="example" class="table table-bordered">
								<thead class="thead">
									<tr>
										<td> Blog Title </td> 
										<td> Delete </td>
									</tr>
								</thead>
								<c:forEach var="cc" items="${blog_list}">
								<tr>
									<td> 
												${cc.getBlog_title()}<br />
									</td>
											<td> <%-- <input class="btn-flat btn-info" type="button" value="delete" onclick="delete_celebrity('${ci.getCelebrity_id()}','no-action')">  --%>
									   	<a href="#" onclick="delete_blog('${cc.getBlog_id()}','${cc.getBlog_image()}','no-action')">
											<img src="resources/assets/images/delete.png" height="40px" width="40px"> 
										</a> 
										<a href="#" data-toggle="modal" data-target="#movieBlogEditModal" onclick="edit_blog('${cc.getBlog_id()}')"> 
											<img src="resources/assets/images/edit.png" height="40px" width="40px">
										</a>
									</td>
								</tr>
								</c:forEach>
								
							</table>
	
				</div>  </div>
  <div class="modal fade" id="movieBlogModal" role="dialog">
   	<div class="modal-dialog modal-lg">
						<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Add Blogs</h4>
					      </div>
					      <div class="modal-body">
					      	<form:form action="movie-blogs" method="post" enctype="multipart/form-data" >
					      		<div class="row">
					      		<div class="col-sm-12 padding-one" style="color:red">
							      		<div class="col-sm-12" align="center">
							      			<span id="addError"></span> 
							      		</div>
						      		</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-3">
						       				Enter Title:
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="blog_title" id="blog_title" type="text">
						       			</div>
						       		</div>
						       <!-- 		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Select Movie:
						       			</div>
						       			<div class="col-sm-9">
						       				<select class="form-control" name="movie_id" id="movie_id">
						       				<option value="">Select Movie</option>
						       				<c:forEach items="${movies_list}" var="ml">
						       						<option value="${ml.getCelebrity_id()}">${ml.getCelebrity_name()}</option>
						       				</c:forEach>
						       				</select>
						       			</div>
						       		</div> -->
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Enter Blog:
						       			</div>
						       			<div class="col-sm-9">
						       				<div class="txtEditor1"></div>
						       				<!-- <textarea class="form-control" name="movie_blogs" id="movie_blogs" rows="8"></textarea> -->
						       				<input type="hidden" name="movie_blogs" id="movie_blogs">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Upload Blog Image
						       			</div>
						       			<div class="col-sm-9">
						       				<input type="file" name="blog_file" id="blog_file" />
						       			</div>
						       		</div>
					       		</div>
					       		<div class="row">
					       			<div align="center">
					       				<input type="submit" class="btn-flat btn-primary" value="Save" onclick="return validate_movieblog_form();">
					       									       				<input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close">
					       			</div>
					       		</div>
					       	</form:form>
					      </div>
					    </div>
				  </div>
  </div>
  
  <div class="modal fade" id="movieBlogEditModal" role="dialog">
    	<div class="modal-dialog modal-lg">
						<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Edit Blog</h4>
					      </div>
					      <div class="modal-body">
					      	<form:form action="movie-blogs-update" method="post" enctype="multipart/form-data" >
					      		<div class="row">
					      		<div class="col-sm-12 padding-one" style="color:red">
							      		<div class="col-sm-12" align="center">
							      			<span id="editError"></span> 
							      		</div>
						      		</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-3">
						       				Enter Title:
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="blog_title" id="blog_title_update" type="text">
						       			</div>
						       		</div>
						       	<%-- 	<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Select Movie:
						       			</div>
						       			<div class="col-sm-9">
						       				<select class="form-control" name="movie_id" id="movie_id_update">
						       				<option value="">Select Movie</option>
						       				<c:forEach items="${movies_list}" var="ml">
						       						<option value="${ml.getCelebrity_id()}">${ml.getCelebrity_name()}</option>
						       				</c:forEach>
						       				</select>
						       			</div>
						       		</div> --%>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Enter Blog:
						       			</div>
						       			<div class="col-sm-9">
						       				<div class="txtEditor"></div>
						       				<!-- <textarea class="form-control" name="movie_blogs" id="movie_blogs_update" rows="8"></textarea> -->
						       				<input type="hidden" name="movie_blogs" id="movie_blogs_update">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Upload Blog Image
						       			</div>
						       			<div class="col-sm-9">
						       			<div class="col-sm-2">
						       			<img alt="blog-image" src="#" class="img-thumbnail" id="blogImageUpdate">
						       			</div>
						       				<div class="col-sm-10">
						       				<input type="file" name="blog_file" id="blog_file_update"  />
						       				<input type="hidden" name="blog_image" id="blog_image">
						       				</div>
						       			</div>
						       		</div>
						     </div>
					       		<div class="row">
					       			<div align="center">
					       				<input type="submit" class="btn-flat btn-primary" value="Save" onclick="return validate_movieblog_form_update();">
					       									       				<input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close">
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
				      		<input type="button" id="btn_confirm_no" class="btn-flat" value="No" data-dismiss="modal" onclick="CELEBRITY_ID=0;">
				      		</div>
				      	</div>				      
				      </div>
				     </div>
				  </div>
			</div>
</div>
	</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<!-- <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script> -->
<script src="resources/assets/js/admin.js"></script>
<script src="resources/assets/js/editor.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	$(".txtEditor").Editor({
		/* 'insertoptions' : false, */
		'advancedoptions' : false,
		'insert_table':false,
		'togglescreen':false,
		/* 'actions' : false, */
		'screeneffects':false,
		'insert_img':false,
		'source':false,
		'print':false,
		'rm_format':false,
		'status_bar':false,
		'splchars':false,
		'block_quote':false
	});
	$(".txtEditor1").Editor({
		/* 'insertoptions' : false, */
		'advancedoptions' : false,
		'insert_table':false,
		'togglescreen':false,
		/* 'actions' : false, */
		'screeneffects':false,
		'insert_img':false,
		'source':false,
		'print':false,
		'rm_format':false,
		'status_bar':false,
		'splchars':false,
		'block_quote':false
	});
});
</script>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="resources/assets/js/style.js"></script>
<script src="resources/assets/js/common.js"></script> -->
</body>
</html>