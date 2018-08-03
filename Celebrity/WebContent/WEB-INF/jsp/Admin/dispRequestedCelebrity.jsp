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
			            <li><a href="celebrity" class="sidenav-link">Celebrity</a></li>
			            <!-- <li><a href="band" class="sidenav-link">Band</a></li>
			            <li><a href="sport" class="sidenav-link">Sport</a></li> -->
			            <li><a href="released-movies" class="sidenav-link">Released Movies</a></li>
			            <!-- <li><a href="#" class="sidenav-link">Released Series</a></li> -->
			            <li><a href="general-settings" class="sidenav-link">Manage Categories</a></li>
			            <li><a href="manage-role" class="sidenav-link">Manage Celebrity Role</a></li>
			            <li><a href="user-search-result" class="sidenav-link">User Searches</a></li>
			            <li><a href="blacklist-website" class="sidenav-link">Black List Websites</a></li>
			            <li><a href="movie-blogs" class="sidenav-link">Blogs</a></li>
			            <li class="active-sidenav"><a href="requested-celebrities" class="sidenav-link">Requested Celebrities</a></li>
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
			   		<label class="dashboard-title">Requested Celebrities </label>
				</div>
				<form action="requested-celebrities" method="post">
				<div class="col-md-5 padding-one">
				<div class="row">
																<div class="col-md-6"> 
																	<span>Show entries</span>
																</div>
																<div class="col-md-6 removePaddings" align="left">
																	<select name="limit" id="limit" onchange="changeLimit1()" class="form-control rounded-select">
																	<option value="10" <c:if test="${limit==10}">selected="selected"</c:if>>10 </option>
																	<option value="20"<c:if test="${limit==20}">selected="selected"</c:if>>20 </option>
																	<option value="30"<c:if test="${limit==30}">selected="selected"</c:if>>30 </option>
																	<option value="40"<c:if test="${limit==40}">selected="selected"</c:if>>40 </option>
																	<option value="50"<c:if test="${limit==50}">selected="selected"</c:if>>50 </option>
																	</select>
																</div>
															</div>
				</div>
				<div class="col-sm-12 padding-one">
				
							<table id="example" class="table table-bordered">
								<thead class="thead">
									<tr>
										<td> Celebrity Name </td> 
										<td> Type </td>
									</tr>
								</thead>
								<c:forEach var="ci" items="${celebrity_info}">
								<tr>
									<td> 
										${ci.getCelebrity_name()}
									</td>
									<td> 
										<c:forEach var="cc" items="${mapped_category}">
											<c:if test="${ci.getCelebrity_id()==cc.getCelebrity_id() && ci.getRequested_celebrity_id()==cc.getRequested_celebrity_id()}">
												${cc.getCategory_name()}<br />
											</c:if>
										</c:forEach>
									</td>
								</tr>
								</c:forEach>
							</table>
							
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
				</div>

			</form>
				
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
});

</script>
</body>
</html>
