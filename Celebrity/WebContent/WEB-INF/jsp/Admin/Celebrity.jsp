<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
  <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
  <title>Celebrity</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <!-- <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css"> -->
  <link href="resources/assets/css/jquery.dataTables.css" rel="stylesheet">
  <link rel="stylesheet" href="resources/assets/css/common-style.css">
</head>
<body style="font-family: Open sans;">
<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div id="my_page_container" class="container page-padding-top my_container_height">
		 <div class="row">
			 <div class="col-sm-3">
			    <div class="sidebar-nav">
			      <div class="navbar navbar-default navbar-background" role="navigation">
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
			            <li class="active-sidenav"><a href="celebrity" class="sidenav-link">Celebrity</a></li>
			            <!-- <li><a href="band" class="sidenav-link">Band</a></li>
			            <li><a href="sport" class="sidenav-link">Sport</a></li> -->
			            <li><a href="released-movies" class="sidenav-link">Released Movies</a></li>
			            <!-- <li><a href="#" class="sidenav-link">Released Series</a></li> -->
			            <li><a href="general-settings" class="sidenav-link">Manage Categories</a></li>
			            <li><a href="manage-role" class="sidenav-link">Manage Celebrity Role</a></li>
			            <li><a href="user-search-result" class="sidenav-link">User Searches</a></li>
			            <li><a href="blacklist-website" class="sidenav-link">Black List Websites</a></li>
			            <li><a href="movie-blogs" class="sidenav-link">Blogs</a></li>
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
			   		<label class="dashboard-title">Celebrities </label>
			   		<!-- <input type="button" class="btn-green pull-right" value="Add celebrity" data-target="#modal_addCelebrity" data-toggle="modal"> -->
			   		<input type="button" class="btn-green pull-right" value="Add celebrity" onclick="addCelebrityModal()">
				</div>
				<div class="col-sm-12 padding-one">
				<div class="row form-group">
						                        <div class="col-md-5">
						                        	<div class="row">
						                        		<div class="col-md-10">
						                        			<div class="row">
																<div class="col-md-6"> 
																	<span>Show entries</span>
																</div>
																<div class="col-md-6 removePaddings1 removePaddings" align="left">
																	<select name="limit" id="limit" onchange="changeLimit()" class="form-control rounded-select">
																	<option value="10" <c:if test="${limit==10}">selected="selected"</c:if>>10 </option>
																	<option value="20"<c:if test="${limit==20}">selected="selected"</c:if>>20 </option>
																	<option value="30"<c:if test="${limit==30}">selected="selected"</c:if>>30 </option>
																	<option value="40"<c:if test="${limit==40}">selected="selected"</c:if>>40 </option>
																	<option value="50"<c:if test="${limit==50}">selected="selected"</c:if>>50 </option>
																	</select>
																</div>
															</div>
														</div>
													</div>
												</div>
											
												<div class="col-md-1 col-md-offset-1" align="right">
													<span>Search</span>
												</div>
												<div class="col-md-4" align="right">
												 	<input class="form-control rounded-select" placeholder="   Search" type="text" id="search_celebrity_key" name="search_celebrity_key" value="${search_celebrity_key}">
												</div>
												<div class="col-md-1 removePaddings1 removePaddings">
													<button class="btn-flat" type="button" onclick="searchCelebrity()">Go </button>
												</div>
											</div>
						<form method="post" id="show_celeb" action="celebrity_pagination">
						
							<table id="example" class="table table-bordered">
								<thead class="thead">
									<tr>
										<td> Celebrity Name </td> 
										<td> Type </td>
										<td> Activate/deactivate </td>
										<td> Delete </td>
									</tr>
								</thead>
								<c:forEach var="ci" items="${celebrity_info}">
								<tr>
									<td> 
										<a href="#" onclick="Showcelebrity_Details('${ci.getCelebrity_id()}')">${ci.getCelebrity_name()}</a>
									</td>
									<td> 
										<c:forEach var="cc" items="${mapped_category}">
											<c:if test="${cc.getCelebrity_id()==ci.getCelebrity_id()}">
												${cc.getCategory_name()}<br />
											</c:if>
										</c:forEach>
									</td>
									
									<td>
										<c:if test="${ci.getIs_active()==1}">
											<a href="#" onclick="activate_deactivate('${ci.getCelebrity_id()}','0','','celebrity')"> Deactivate </a>
										</c:if>
										 <c:if test="${ci.getIs_active()==0}">
										 	<%-- <input class="btn-flat btn-info" type="button" onclick="activate_deactivate('${ci.getCelebrity_id()}','1','')" value="Activate"> --%>
										 	<a href="#" onclick="activate_deactivate('${ci.getCelebrity_id()}','1','','celebrity')">Activate</a>
										 	
										 </c:if>
										
									</td>
									<td> <%-- <input class="btn-flat btn-info" type="button" value="delete" onclick="delete_celebrity('${ci.getCelebrity_id()}','no-action')">  --%>
										<a href="#" onclick="delete_celebrity('${ci.getCelebrity_id()}','no-action')">
											<img src="resources/assets/images/delete.png" height="40px" width="40px"> 
										</a>
										<a href="#" onclick="edit_profile('${ci.getCelebrity_id()}')"> 
											<img src="resources/assets/images/edit.png" height="40px" width="40px">
										</a>
									</td>
								</tr>
								</c:forEach>
							</table>
										<!-- ------------------------Next previous button---------------------------------- -->
							
							<c:if test="${total_celebrity>limit}">
							<div class="well whiteWell calendarWell">
								<div class="row">
									<div class="col-md-6">Show ${offset} to ${offset+limit}
										out of ${total_celebrity}</div>
									<div class="col-md-6" align="right">
										<div class="col-md-12">
											<c:if test="${total_celebrity>limit}">
												<c:set var="btn_count" value="0" />
												<fmt:parseNumber var="btn_count" integerOnly="true"
													type="number" value="${total_celebrity/limit}" />
												<c:if test="${total_celebrity%limit!=0 }">
													<c:set var="btn_count" value="${(btn_count)+1}" />
												</c:if>
										
												<c:set var="individual_btn_offset" value="0" />
												<c:if test="${individual_btn_offset!=offset }">
													<button class="prev_next_btn cursor_pointer" type="submit"
														name="btn_previous" value="${offset}">Previous</button>
												</c:if>
												<c:forEach begin="1" end="${btn_count}" var="b">
													<c:if test="${individual_btn_offset==offset}">
														<button type="submit" name="btn_offset"
															class="active_page cursor_pointer" value="${individual_btn_offset}">${b}
														</button>
													</c:if>
													<c:if test="${individual_btn_offset!=offset}">
														<button type="submit" name="btn_offset"
															class="passive_page cursor_pointer" value="${individual_btn_offset}">${b}
														</button>
													</c:if>
													<c:set var="individual_btn_offset"
														value="${individual_btn_offset+limit}" />
												</c:forEach>
												<c:if test="${(individual_btn_offset-limit)!=offset}">
													<button class="prev_next_btn cursor_pointer" type="submit" name="btn_next"
														value="${offset}">Next</button>
												</c:if>
											</c:if>
										</div>
										<br /> <br />
									</div>
								</div>
								</div>
							</c:if>
							</form>
				</div>
	<!-- Model to edit celebrity -->
			<c:if test="${not empty edit_celebrity && edit_celebrity=='yes'}">	
				<div id="modal_editCelebrity" class="modal fade" role="dialog">
				 	<div class="modal-dialog modal-lg">
						<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Update Celebrity Details</h4>
					      </div>
					      <div class="modal-body">
					      	<form:form action="edit-celebrity" method="post" enctype="multipart/form-data" >
					      	<c:forEach var="ce" items="${edit_celebrity_info}">
					      	
					      		<div class="row">
					      		<div class="col-sm-12 padding-one" style="color:red">
							      		<div class="col-sm-12" align="center">
							      			<span id="addError"></span> 
							      		</div>
						      		</div>
					      			<div class="col-sm-12 padding-one">
					      				<c:if test="${empty ce.getProfile_photo_path()}">
					      					<img src="resources/assets/images/user.png" height="200"  width="200">
					      				</c:if>
					      				<c:if test="${not empty ce.getProfile_photo_path()}">
					      					<img src="${ce.getProfile_photo_path()}" height="200"  width="200">
					      				</c:if>
					      			</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label">Enter Name: </label><sup style="color: red">*</sup>
						       			</div>
						       			<div class="col-sm-8">
						       				<input class="form-control" name="celebrity_name" id="celebrity_name_edit" type="text" value="${ce.getCelebrity_name()}">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-4">
						       				<label class="form-label">Celebrity Bio:</label>
						       			</div>
						       			<div class="col-sm-8">
						       				<%-- <input class="form-control" name="celebrity_bio" id="celebrity_bio" type="text" value="${ce.getCelebrity_bio()}"> --%>
						       				<textarea name="celebrity_bio" id="celebrity_bio" rows="5" class="form-control">${ce.getCelebrity_bio()}</textarea>
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-4">
						       				<label class="form-label">Select Category: </label><sup style="color: red">*</sup>
						       			</div>
						       			<div class="col-sm-8">
						       			
						       				 <c:forEach var="cat" items="${category_edit}">
						       					<input type="checkbox" name="category_name" id="category_name" value="${cat.getCategory_id()}" <c:if test="${cat.getCelebrity_id()==ce.getCelebrity_id()}">checked="true"</c:if>> ${cat.getCategory_name()}
						       				</c:forEach>
						       			</div>
						       		</div>
						       		
						       		<%-- <div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label">Select Country: </label>
						       			</div>
						       			<div class="col-sm-8">
						       				<select class="form-control" name="country_id" id="country_id" onchange="getState(this)">
						       					<option value="0">Select Country </option>
						       					<c:forEach var="c" items="${country_list}"> 
						       						<option value="${c.getCountry_id()}"<c:if test="${c.getCountry_id() ==ce.getCountry_id()}"> selected="selected"</c:if>>${c.getCountry_name()}</option>
						       					</c:forEach>
						       				</select>
						       			</div>
						       		</div> --%>
						       		<%-- <div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label">Select State:</label>
						       			</div>
						       			<div class="col-sm-8">
						       				<select class="form-control" name="state_id" id="state_id">
						       					<option value="0">Select State </option>
						       					<c:forEach var="st" items="${state_list}">
						       						<option value="${st.getState_id()}"<c:if test="${st.getState_id()==ce.getState_id()}"> selected="selected"</c:if>>${ce.getState_name()} </option>
						       					</c:forEach>
						       				</select>
						       			</div>
						       		</div> --%>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label"> Facebook page:</label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input type="text" class="form-control" value="${ce.getFacebok_page_name()}" name="facebok_page_name">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label"> Twitter Handle Name:</label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input type="text" class="form-control" value="${ce.getTwitter_handle_name()}" name="twitter_handle_name">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-4">
						       				<label class="form-label"> Instgram Page Name: </label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input type="text" class="form-control" value="${ce.getInstagram_page_name()}" name="instagram_page_name">
						       			</div>
						       		</div>
						       		<div class="col-sm-12 padding-one" >
						       			<div class="col-sm-4">
						       				<label class="form-label"> Upload profile Photo: </label>
						       			</div>
						       			<div class="col-sm-8">
						       				<input type="file" name="d_file" id="profile_photo_name"/>
						       			</div>
						       		</div>
					       		</div>
					       		<div class="row">
					       			<div align="center">
					       				<input type="submit" class="btn-flat" value="Update" onclick="return validate_celebrity_form_edit();">
					       				<!-- <a href="celebrity"><input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close"></a> -->
					       				<button type="button" class="btn-flat"><a href="celebrity" style="color: white; text-decoration: none;"> close </a> </button>
					       			</div>
					       		</div>
					       		</c:forEach>
					       	</form:form>
					     </div>
					    </div>
				  </div>
				</div>
			</c:if>
<!-- Modal Add celebrity  -->
		<%-- <c:if test="${fn:length(edit_celebrity_info)<=0}"> --%>
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
						      		<div class="col-sm-12 padding-one" style="color:red">
							      		<div class="col-sm-12" align="center">
							      			<span id="addError"></span> 
							      		</div>
						      		</div>
						       		<div class="col-sm-12 padding-one">
						       			<div class="col-sm-3">
						       				Enter Name:<sup style="color: red">*</sup>
						       			</div>
						       			<div class="col-sm-9">
						       				<input class="form-control" name="celebrity_name" id="celebrity_name_add" type="text">
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
						       				Select Category:<sup style="color: red">*</sup>
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
			<%-- </c:if> --%>

<!-- Show celebrity Info.... -->
			<c:if test="${not empty show_celebrity_details && show_celebrity=='yes'}">	
				<div id="modal_showCelebrity" class="modal fade" role="dialog">
				 	<div class="modal-dialog modal-lg">
						<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Celebrity</h4>
					      </div>
					      <div class="modal-body">
					      <div class="row left-right-zero">
					      		<c:forEach var="ce" items="${show_celebrity_details}">
					      			<div class="col-sm-12">
						      			<div class="col-sm-3 padding-one">
						      				<c:if test="${empty ce.getProfile_photo_path()}">
						      					<img src="resources/assets/images/user.png"  width="100%">
						      				</c:if>
						      				<c:if test="${not empty ce.getProfile_photo_path()}">
						      					<img src="${ce.getProfile_photo_path()}" width="100%">
						      				</c:if>
						      			</div>
						      			<div class="col-sm-8 padding-one">
						      				<div class="col-sm-12 padding-one">
						      						<div class="col-sm-4"><label class="lbl_field_name">Name :</label> </div>
						      						<div class="col-sm-8"> ${ce.getCelebrity_name()}</div>
						      				</div>
						      				<div class="col-sm-12 padding-one">
						      						<div class="col-sm-4"> <label style="color: #03178e"> Facebook Page: </label> </div>
						      						<div class="col-sm-8"> ${ce.getFacebok_page_name()}</div>
						      				</div>
						      				<div class="col-sm-12 padding-one">
						      						<div class="col-sm-4"> <label style="color: #03178e"> Twitter Handle: </label> </div>
						      						<div class="col-sm-8"> ${ce.getTwitter_handle_name()}</div>
						      				</div>
						      				<div class="col-sm-12 padding-one">
						      						<div class="col-sm-4"> <label style="color: #03178e"> Instagram Page: </label> </div>
						      						<div class="col-sm-8"> ${ce.getInstagram_page_name()}</div>
						      				</div>
						      			</div>
					      			</div>
					      			<div class="col-sm-12">
					      				<div class="col-sm-12">
					      					<label class="lbl_field_name">Celebrity Biography </label>
					      				</div>
					      				<div class="col-sm-12">
					      					${ce.getCelebrity_bio()}
					      				</div>
					      			</div>
					      			<!--celebrity_id, celebrity_name, celebrity_nick_name, country_id, state_id, profile_photo_name, profile_photo_path, is_active  -->
					      			
					      		</c:forEach>
					      </div>
					      </div>
					     </div>
					   </div>
					</div>
					
			
			</c:if>
			
			

<!-- ***********************Common Models**************************  -->			
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
		</div> <!-- Main Row  Add content inside  -->
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/admin.js"></script>
<!--  -->
<script>
//
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	
	/* $('#example').DataTable(); */
	
	$("#dashboard").addClass("active");
	if('${edit_celebrity}'=='yes' && '${edit_celebrity}'!='')
		{
			$("#modal_editCelebrity").modal('show');
		}
	if('${show_celebrity}'=='yes')
		{
		$("#modal_showCelebrity").modal('show');
		}
	if($(window).width()<768){
		$(".removePaddings1").removeClass("removePaddings");
	}
});

</script>
</body>
</html>
