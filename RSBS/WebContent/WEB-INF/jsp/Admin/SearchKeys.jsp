<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RSBS - User Search Keys</title>
<link rel="stylesheet" href="resources/assets/css/jquery-ui.css"
	type="text/css" />
</head>
<body style="font-family: roboto; background-color:#f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
<div id="my_page_container" class="container main-container inside-page-padding" style="width:100%">
		<div class="row">
		<div class="col-md-2">
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
										  <li class="side-nav-item side-nav-padding active">
										   		 <a class="side-nav-link" href="search-keys">User Search</a>
										  </li>
										  <li class="side-nav-item side-nav-padding">
										   		 <a class="side-nav-link" href="add-blogs">Blogs</a>
										  </li>
								</ul>				  
						</div>
						<div class="col-md-10">
						<div class="row">
						<div class="col-md-12">
						<form action="" method="post" id="add-search-key">
						<div class="col-sm-12">
				   		<!-- <label class="dashboard-title">Manage User Searches </label> -->
					</div>
						<div class="row">
						<div class="col-md-12">
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
						</div>
						<div class="row">
						<div class="col-md-12">
						<div class="table-responsive">
						<table class="table table-bordered table-background">
						<thead>
						<tr>
						<th>Search Key</th>
						<th>Count</th>
						<th>Add Politician</th>
						<th>Action</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="a" items="${getAllSearchKeys}">
						<tr>
						<td>${a.getSearch_key()}</td>
						<td>${a.getCount()}</td>
						<td><a href="#" data-toggle="modal" data-target="#AddPoliticianModal" onclick="getSearchKeyId('${a.getSearch_key_id()}','${a.getSearch_key()}')">Add</a></td>
						<td>
						<a href="#" data-toggle="modal" data-target="#editPoliticianModal" onclick="EditPoliticianName('${a.getSearch_key_id()}','${a.getSearch_key()}')"><img src="resources/assets/images/edit.png" width="40px" height="40px"></a>
						<a href="#" data-toggle="modal" data-target="#deletePoliticianModal" onclick="deletePoliticianInfo('${a.getSearch_key_id()}','${a.getSearch_key()}')"><img src="resources/assets/images/delete.png" width="40px" height="40px"></a>
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
</div>

<div class="modal fade" id="editPoliticianModal">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header modal-header-background">
									Edit Search Key
								</div>
								<div class="modal-body">
								<div class="row">
								<div class="col-md-5">
								<label>Enter Search Key</label>
								</div>
								<div class="col-md-7">
								<input type="text" name="search_key1" id="search_key" class="form-control">
								</div>
								</div>
								<div class="row">
										<div class="col-md-12">
											<span id="errName" class="red-text"></span>
										</div>
									</div>
								
									<div class="row">
									<div class="col-md-12" align="right">
									<div class="save-btn">
										<input type="button" class="btn-follow cursor_pointer" value="Yes" onclick="EditPoliticianName('','')">
										<input type="button" class="btn-follow cursor_pointer" value="No" data-dismiss="modal">
									</div>
									</div>
									</div>
								</div>
							</div>
						</div>
		</div>
		<div class="modal fade" id="deletePoliticianModal">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									Are you sure you want to delete this politician?
								</div>
								<div class="modal-body">
									<div class="row">
									<div class="col-md-12" align="right">
									<div class="save-btn">
										<input type="button" class="btn-follow cursor_pointer" value="Yes" onclick="deletePoliticianInfo('','')">
										<input type="button" class="btn-follow cursor_pointer" value="No" data-dismiss="modal">
									</div>
									</div>
									</div>
								</div>
							</div>
						</div>
		</div>

						<div class="modal fade" id="AddPoliticianModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg" role="document">
						    <div class="modal-content">
						      <div class="modal-header modal-header-background">
						        <h5 class="modal-title" id="exampleModalLabel">Add Politician</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <form:form action="add-politician" method="post" enctype="multipart/form-data" >
									      <div class="modal-body">
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Politician Name <sup class="star_color">*</sup></label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="celebrity_name" name="celebrity_name" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Date Of Birth </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="date_of_birth" name="date_of_birth" class="form-control add_datepicker">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Place Of Birth </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="place_of_birth" name="place_of_birth" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> Party <sup class="star_color">*</sup></label>
									        			</div>
									        			<div class="col-sm-8">
									        					<select   class="form-control" name="party_id" id="party_id" class="form-control">
									        							<option value="0">Select Party </option>
									        							<c:forEach var="p" items="${party_info}">
									        										<option value="${p.getParty_id()}"> ${p.getParty_name()}  </option>
									        							</c:forEach>
									        						
									        					</select>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> State <sup class="star_color">*</sup></label>
									        			</div>
									        			<div class="col-sm-8">
									        					<select   class="form-control" name="state_id" id="state_id" class="form-control">
									        							<option value="0">Select State </option>
									        							<c:forEach var="s" items="${all_states}">
									        										<option value="${s.getState_id()}"> ${s.getState_name()}  </option>
									        							</c:forEach>
									        						
									        					</select>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Politician Category <sup class="star_color">*</sup></label>
									        			</div>
									        			<div class="col-sm-8">
								        							<c:forEach var="c" items="${all_politician_category}">
                                                                      <input type="checkbox" name="categroy_name"
														value="${c.getCategory_id()}" class="categoryName"> ${c.getCategroy_name()} <br>
									        						</c:forEach>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Position </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<!-- <input type="text" id="politician_position" name="politician_position" class="form-control"> -->
									        				<textarea rows="4" id="politician_position" name="politician_position" class="form-control"></textarea>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Tenure </label>
									        			</div>
									        			<div class="col-sm-4">
									        			<select class="form-control" name="tenure_from" id="tenure_from" class="form-control">
									        				<option value="0">Select From</option>
									        				<%-- <c:forEach var="i" items="${years}">
										        				
										        				<c:choose>
										        					<c:when test="${i==current_year}">
										        						<option value="${i}">Present</option>
										        					</c:when>
										        					<c:otherwise>
										        						<option value="${i}">${i}</option>
										        					</c:otherwise>
										        				</c:choose>
										        			
									        				</c:forEach> --%>
									        				<c:forEach var="i" items="${years}">
										        				<option value="${i}">${i}</option>
										        				
									        				</c:forEach>
									        			</select>
									        			</div>
									        			<div class="col-sm-4">
									        			<select class="form-control" name="tenure_to" id="tenure_to" class="form-control">
									        				<option value="0">Select To</option>
									        				<c:forEach var="i" items="${years}">
										        				
										        				<%-- <c:choose>
										        					<c:when test="${i==current_year}">
										        						<option value="${i}">Present</option>
										        					</c:when>
										        					<c:otherwise>
										        						<option value="${i}">${i}</option>
										        					</c:otherwise>
										        				</c:choose> --%>
										        				<c:choose>
										        				<c:when test="${i==current_year}">
										        				<option value="${i}">Present</option>
										        				</c:when>
										        				<c:otherwise>
										        				<option value="${i}">${i}</option>
										        				</c:otherwise>
										        				</c:choose>
										        			
									        				</c:forEach>
									        			</select>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Committees </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<!-- <input type="text" id="commity" name="commity" class="form-control"> -->
									        					<textarea rows="4" id="commity" name="commity" class="form-control"></textarea>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Term Ends </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id=term_ends name="term_ends" class="form-control add_datepicker_terms">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Year In Position </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="year_in_position" name="year_in_position" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Net Worth </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id=net_worth name="net_worth" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Predecessor</label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id=predecessor name="predecessor" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> Facebook page</label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" name="facebook_page" id="facebook_page" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> twitter_handle </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" name="twitter_handle" id="twitter_handle" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> instagram_page</label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input id="instagram_page" name="instagram_page" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> Upload profile photo</label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="file" id="d_file" name="d_file">
									        			</div>
									        	</div>
									        	<div class="row form-group">
										        	<div class="col-sm-4">
										        		<label> About </label>
										        	</div>
										        	<div class="col-sm-8">
										        		<textarea rows="4" name="bio" id="bio" class="form-control"></textarea>
										        	</div>
									        	</div>
									        	<div class="row form-group">
										        	<div class="col-sm-4">
										        		<label> Career </label>
										        	</div>
										        	<div class="col-sm-8">
										        		<textarea rows="4" name="career" id="career" class="form-control"></textarea>
										        	</div>
									        	</div>
									        	<div class="row form-group">
									      				<div class="col-sm-12" align="center">
									      				<span  id="validate-error-msg" style="color:red"></span>
									      				</div>
									      		</div>
									      </div>
										      <div class="modal-footer">
												      <button type="submit" class="btn-follow cursor_pointer" onclick="return getSearchKeyId('','')">Save</button>
												       <button type="button" class="btn-follow cursor_pointer" data-dismiss="modal">Close</button>
										      
										      </div>
							      </form:form>
						    </div>
						  </div>
						</div>
						
						<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src=" https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.js"></script>
<script src="resources/assets/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#my_footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
	
	var start = new Date();
    start.setFullYear(start.getFullYear() - 78);
    var end = new Date();
    end.setFullYear(end.getFullYear());
    
	/* $(".add_datepicker").datepicker({
    	//minDate: 1,
    	maxDate: -1,
    	yearRange: start.getFullYear() + ':' + end.getFullYear(),
    	changeYear:true,
      	onSelect: function(dateText, inst) {
    	  	var date = $('#date_of_birth').datepicker( "option", "dateFormat", 'dd-mm-yy' ).val();
    	  	
    	 		$("input[id='date_of_birth']").val(date);
      	}
    }); */
	
	$(".add_datepicker_terms").datepicker({
    	//minDate: 1,
    	//maxDate: -1,
    	yearRange: "1940:2025",
    	changeYear:true,
      	onSelect: function(dateText, inst) {
      		var date2 = $('#term_ends').datepicker( "option", "dateFormat", 'dd-mm-yy' ).val();
      		$("input[id='term_ends']").val(date2);
      	}
    });
	
	$("#dashboard").addClass('active-nav-link');
});
</script>
</body>
</html>