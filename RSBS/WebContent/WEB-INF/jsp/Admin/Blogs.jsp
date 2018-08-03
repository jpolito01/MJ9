<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RSBS - Blogs</title>


<style type="text/css">
.Editor-editor{
	outline: 1px solid rgba(0,0,0,.15) !important;
}
a:visited, a:link{
text-decoration:none;
color:#0275d8;
}
</style>
</head>
<body style="font-family: roboto; background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
<link href="resources/assets/css/editor.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div id="my_page_container" class="container main-container inside-page-padding" style="width:100%">
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
										  <li class="side-nav-item side-nav-padding">
										   		 <a class="side-nav-link" href="blacklist-website">Black-List Websites</a>
										  </li>
										  <li class="side-nav-item side-nav-padding">
										   		 <a class="side-nav-link" href="search-keys">User Search</a>
										  </li>
										  <li class="side-nav-item side-nav-padding active">
										   		 <a class="side-nav-link" href="add-blogs">Blogs</a>
										  </li>
								</ul>				  
						</div>
						<div class="col-sm-10">
							<div class="col-sm-12">
							<form method="post" id="blogs">
									<div class="row">
										<!-- <div class="row form-group"> -->
											<div class="col-sm-12 form-group">
													<input type="button" class="red1 cursor_pointer" value="Add Blog"  data-toggle="modal" data-target="#AddBlogs">
											</div>
											<!-- </div> -->
											<div class="col-sm-12">
												<div class="table-responsive">
  														<table class="table table-bordered table-background">
															<thead>
																	<tr>
																			<th> Blog Title </th>
																			<th> Action </th>
																			
																	</tr>
															</thead>
															<tbody>
																<c:forEach items="${get_blogs}" var="ci">
																		<tr>
																				<td>${ci.getBlog_title()} </td>
																				<td> 	
																				        <%-- <a href="#" onclick="ViewCelebrity('${ci.getCelebrity_id()}')"  data-toggle="modal" data-target="#ViewCelebrityModal" >View</a> --%>
																						<a href="#" onclick="editBlog('${ci.getBlog_id()}')"  data-toggle="modal" data-target="#EditBlogModal" ><img src="resources/assets/images/edit.png" width="40px" height="40px"></a>
																						<a href="#" onclick="deleteBlog('${ci.getBlog_id()}')" data-toggle="modal" data-target="#deleteBlogModal"><img src="resources/assets/images/delete.png" width="40px" height="40px"></a>
																				
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
					<div class="modal fade" id="AddBlogs" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
						  <div class="modal-content">
							<div class="modal-header modal-header-background">
							<div class="modal-title" id="exampleModalLabel"><h5>Add Blog</h5></div>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
							</div>
							<div class="modal-body">
							<form:form action="add-blogs" method="post" enctype="multipart/form-data">
							            <div class="row form-group">
											<div class="col-md-12" align="center">
												<span id="adderrName" class="red-text"></span>
											</div>
									    </div>
							            <div class="row form-group">
									        			<div class="col-sm-3">
									        					<label>Blog Title </label>
									        			</div>
									        			<div class="col-sm-9">
									        					<input type="text" id="blog_title" name="blog_title" class="form-control">
									        			</div>
									    </div>
									    <div class="row form-group">
									        	<div class="col-sm-3">
									        		<label> Description </label>
									        	</div>
									        	<div class="col-sm-9">
									                <div class="txtEditor"></div>
									        		<!-- <textarea rows="4" name="description" id="description" class="form-control"></textarea> -->
									        	<input type="hidden" name="description" id="description">
									        	</div>
									    </div>
									    <div class="row form-group">
									        			<div class="col-sm-3">
									        					<label> Upload Image</label>
									        			</div>
									        			<div class="col-sm-9">
									        					<input type="file" id="d_file" name="d_file">
									        			</div>
									    </div>
									    <div class="modal-footer">
												      <button type="submit" class="red1 cursor_pointer" onclick="return blogValidation()">Save</button>
												      <button type="button" class="red1 cursor_pointer" data-dismiss="modal">Close</button>
										</div>
							</form:form>
							</div>
						</div>
					</div>
					</div>
					
					<div class="modal fade" id="deleteBlogModal">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									Are you sure you want to delete this blog?
								</div>
								<div class="modal-body">
								<form action="manage-blogs" method="post" id="deleteBlogs">
								<div class="row">
									<div class="col-md-12" align="right">
										<div class="save-btn">
											<input type="button" class="btn-follow cursor_pointer" value="Yes" onclick="deleteBlog('')">
											<input type="button" class="btn-follow cursor_pointer" value="No" data-dismiss="modal">
										</div>
									</div>
									</div>
								</form>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade" id="EditBlogModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
						  <div class="modal-content">
							<div class="modal-header modal-header-background">
								<div class="modal-title" id="exampleModalLabel"><h5>Edit Blog</h5></div>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
							</div>
							<div class="modal-body">
							<form:form action="edit-politician-blogs" method="post" enctype="multipart/form-data">
							
							            <div class="row form-group">
							            <div class="col-md-12">
							            <img id="blog_image" style="width:50%;" >
							            </div>
							            </div>
							            <div class="row form-group">
											<div class="col-md-12" align="center">
												<span id="edit-errName" class="red-text"></span>
											</div>
									    </div>
							            <div class="row form-group">
									        			<div class="col-sm-3">
									        					<label>Blog Title </label>
									        			</div>
									        			<div class="col-sm-9">
									        					<input type="text" id="blog_title_e" name="blog_title" class="form-control">
									        			</div>
									    </div>
									    <div class="row form-group">
									        	<div class="col-sm-3">
									        		<label> Description </label>
									        	</div>
									        	<div class="col-sm-9">
									        		<div class="txtEditor1"></div>
<!-- 									        		<textarea rows="4" name="description" id="description_e" class="form-control Editor-editor" hidden="true"></textarea> 
 -->									        		
 														<input type="hidden" name="description" id="description_e">
									        	
									        	</div>
									    </div>
									    <div class="row form-group">
									        			<div class="col-sm-3">
									        					<label> Upload Image</label>
									        			</div>
									        			<div class="col-sm-9">
									        					<input type="file" id="d_file_e" name="d_file">
									        			</div>
									    </div>
									    <div class="modal-footer">
												      <button type="submit" class="btn btn-follow" onclick="return editBlogValidation()">Update</button>
												      <button type="button" class="btn btn-follow" data-dismiss="modal">Close</button>
										</div>
							</form:form>
							</div>
						</div>
					</div>
					</div>
			
			<%@ include file="/WEB-INF/jsp/footer.jsp"%>		
		
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="resources/assets/js/admin.js"></script>
<script src="resources/assets/js/editor.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#dashboard").addClass('active-nav-link');
	var max_height=$("#my_header").height()+$("#my_footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
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
		/* 'texteffects':true,
		'aligneffects':true,
		'textformats':true,
		'fonteffects':true,
		'actions' : true,
		'insertoptions' : true,
		'extraeffects' : true,
		'advancedoptions' : true,
		'screeneffects':true,
		'bold': true,
		'italics': true,
		'underline':true,
		'ol':true,
		'ul':true,
		'undo':true,
		'redo':true,
		'l_align':true,
		'r_align':true,
		'c_align':true,
		'justify':true,
		'insert_link':true,
		'unlink':true,
		'insert_img':true,
		'hr_line':true,
		'block_quote':true,
		'source':true,
		'strikeout':true,
		'indent':true,
		'outdent':true,
		'fonts':fonts,
		'styles':styles,
		'print':true,
		'rm_format':true,
		'status_bar':true,
		'font_size':fontsizes,
		'color':colors,
		'splchars':specialchars,
		'insert_table':true,
		'select_all':true,
		'togglescreen':true */
	});       
	
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
});
</script>
</body>
</html>