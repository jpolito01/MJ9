<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
  <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>RSBS - Dashboard</title>
<link rel="stylesheet" href="resources/assets/css/jquery-ui.css"
	type="text/css" />
	<link rel="stylesheet" href="resources/assets/css/jquery-ui.min.css"
	type="text/css" />
	<link href="resources/assets/css/jquery.dataTables.css" rel="stylesheet">
</head>
<body style="font-family: roboto; background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
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
										  <li class="side-nav-item side-nav-padding">
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
										  <li class="side-nav-item side-nav-padding active">
										   		 <a class="side-nav-link" href="user-accounts">User accounts</a>
										  </li>
								</ul>				  
						</div>
						<div class="col-sm-10">
							<div class="col-sm-12">
							<form method="post" action="user-accounts">
									<div class="row">
										<!-- <div class="row form-group"> -->
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
											<div class="row form-group">
						                        <div class="col-md-4">
						                        	<div class="row">
						                        		<div class="col-md-10">
						                        			<div class="row">
																<div class="col-md-6"> 
																	<span>Show entries</span>
																</div>
																<div class="col-md-6" align="left">
																	<select name="limit" id="limit" onchange="changeLimitUserAccounts()" class="form-control rounded-select">
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
											
											</div>
												<div class="table-responsive">
  														<table class="table table-bordered table-background" id="my_data_table">
															<thead>
																	<tr>
																			<th>Name</th>
																			<th>Email id</th>
																			<th>Follows Politicians?</th>
																	</tr>
															</thead>
															<tbody>
																<c:forEach items="${user_accounts}" var="ci">
																		<tr>
																				<td>${ci.getFirst_name()} ${ci.getLast_name()}</td>
																				<td>${ci.getEmail_id()}</td>
																				<td>
																				<c:if test="${ci.getFollow_id()!=0}">
																				Yes
																				</c:if>
																				<c:if test="${ci.getFollow_id()==0}">
																				No
																				</c:if>
																				</td>
																			
																		</tr>
																</c:forEach>
															</tbody>
													</table>
												</div>
												
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
							
											</div>
										</div>
									</form>
								</div>
						</div>
					</div>
					
					
					
					
					
</div>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src=" https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.js"></script>
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#my_footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
	
	/* $('#my_data_table').DataTable(); */
	
		$("#dashboard").addClass('active-nav-link');
});




</script>
</body>
</html>