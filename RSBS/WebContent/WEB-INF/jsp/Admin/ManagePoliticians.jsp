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
										  <li id="manage-politicians" class="side-nav-item side-nav-padding active">
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
										  <li class="side-nav-item side-nav-padding">
										   		 <a class="side-nav-link" href="user-accounts">User accounts</a>
										  </li>
								</ul>				  
						</div>
						<div class="col-sm-10">
							<div class="col-sm-12">
							<form method="post" id="show_celeb">
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
											<div class="col-sm-12 form-group">
													<input type="button" class="red1 cursor_pointer" value="Add Politician"  data-toggle="modal" data-target="#AddCelebrityModal">
											</div>
											<!-- </div> -->
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
											
												<div class="col-md-1 offset-md-3" align="right">
													<span>Search</span>
												</div>
												<div class="col-md-3" align="right">
												 	<input class="form-control rounded-select" placeholder="   Search" type="text" id="search_celebrity_key" name="search_celebrity_key" value="${search_celebrity_key}">
												</div>
												<div class="col-md-1" align="right">
													<button class="red1 go_btn_padding" type="button" onclick="searchCelebrity()">Go </button>
												</div>
											</div>
												<div class="table-responsive">
  														<table class="table table-bordered table-background" id="my_data_table">
															<thead>
																	<tr>
																			<th> Politician Name</th>
																			<th> Party Name </th>
																			<th> Category name </th>
																			<th>Activate/Deactivate </th>
																			<th> Action</th>
																	</tr>
															</thead>
															<tbody>
																<c:forEach items="${celebrity_info}" var="ci">
																		<tr>
																				<td>${ci.getCelebrity_name()} </td>
																				<td>${ci.getParty_name()}</td>
																				<td>
																					<c:forEach var="c" items="${mapped_categories}" varStatus="st">
																						<c:if test="${ci.getCelebrity_id()==c.getCelebrity_id()}">
																								${c.getCategroy_name()}<br />
																								<%-- <c:if test="${st.index<fn:length(mapped_categories)-1}">,</c:if>
															                                    <c:if test="${st.index==fn:length(mapped_categories)}">.</c:if> --%>
																						</c:if>
																					</c:forEach> 
																				</td>
																				<td> 
																						<c:if test="${ci.getIsActive()==1 }">
																								<a href="#" onclick="ActivateDeactivate('${ci.getCelebrity_id()}',0)"> Deactivate </a>
																						</c:if>
																						<c:if test="${ci.getIsActive()==0 }">
																								<a href="#" onclick="ActivateDeactivate('${ci.getCelebrity_id()}',1)"> Activate </a>
																						</c:if>
																				</td>
																				<td> 	
																				        <a href="#" onclick="ViewCelebrity('${ci.getCelebrity_id()}')"  data-toggle="modal" data-target="#ViewCelebrityModal" >View</a>
																						<a href="#" onclick="EditCelebrity('${ci.getCelebrity_id()}')"  data-toggle="modal" data-target="#EditCelebrityModal" ><img src="resources/assets/images/edit.png" width="40px" height="40px"></a>
																						<a href="#" onclick="deleteCelebrity('${ci.getCelebrity_id()}')" data-toggle="modal" data-target="#deleteCelebrityModal"><img src="resources/assets/images/delete.png" width="40px" height="40px"></a>
																				
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
					
					<div class="modal fade" id="AddCelebrityModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
									        				
									        				<c:forEach var="i" items="${years}">
										        				<option value="${i}">${i}</option>
										        				
									        				</c:forEach>
									        				
									        			</select>
									        			</div>
									        			<div class="col-sm-4">
									        			<select class="form-control" name="tenure_to" id="tenure_to" class="form-control">
									        				<option value="0">Select To</option>
									        				<c:forEach var="i" items="${years}">
										        				
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
									        					<input type="text" id="term_ends" name="term_ends" class="form-control add_datepicker_terms">
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
									        					<input type="text" name="facebook_page" id="facebook_page_0" class="form-control">
									        			</div>
									        			
									        			
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> twitter_handle </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<input type="text" name="twitter_handle" id="twitter_handle" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group addTwitterAcctRowAdd">
									        			<div class="col-sm-4"></div>
									        			<div class="col-sm-1">
									        			<button type="button" class="btn btn-follow" onclick="addTwitterAcctRowInAdd()">+</button>
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
												      <button type="submit" class="btn-follow cursor_pointer" onclick="return validate_add_celebrity()">Save</button>
												       <button type="button" class="btn-follow cursor_pointer" data-dismiss="modal">Close</button>
										      </div>
							      </form:form>
						    </div>
						  </div>
						</div>
					
					<div class="modal fade" id="deleteCelebrityModal">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									Are you sure you want to delete this politician?
								</div>
								<div class="modal-body">
									<div class="row">
									<div class="col-md-12" align="right">
									<div class="save-btn">
										<input type="button" class="btn-follow cursor_pointer" value="Yes" onclick="deleteCelebrity('')">
										<input type="button" class="btn-follow cursor_pointer" value="No" data-dismiss="modal">
									</div>
									</div>
									</div>
								</div>
							</div>
						</div>
		</div>
		
					<div class="modal fade" id="EditCelebrityModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg" role="document">
						    <div class="modal-content">
						      <div class="modal-header modal-header-background">
						        <h5 class="modal-title" id="exampleModalLabel">Edit Politician</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <form:form action="add-politician" method="post" enctype="multipart/form-data" >
									      <div class="modal-body">
									      
									      		<div class="row form-group">
									      				<div class="col-sm-4">
									      						<img id="profile_pic" width="100%">
									      				</div>
									      		</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Politician Name <sup class="star_color">*</sup> </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="celebrity_name_e" name="celebrity_name" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Date Of Birth </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="date_of_birth_e" name="date_of_birth" class="form-control add_datepicker">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Place Of Birth </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="place_of_birth_e" name="place_of_birth" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> Party Name <sup class="star_color">*</sup></label>
									        			</div>
									        			<div class="col-sm-8">
									        					<select   class="form-control" name="party_id" id="party_id_e" class="form-control">
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
									        					<select   class="form-control" name="state_id" id="state_id_e" class="form-control">
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
									        								<input type="checkbox" name="categroy_name" id="edit_cat_${c.getCategory_id()}" value="${c.getCategory_id()}" class="categoryEditName">${c.getCategroy_name()}
									        						</c:forEach>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Position </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<!-- <input type="text" id="politician_position_e" name="politician_position" class="form-control"> -->
									        				<textarea rows="4" id="politician_position_e" name="politician_position" class="form-control"></textarea>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Tenure </label>
									        			</div>
									        			<div class="col-sm-4">
									        			<select class="form-control" name="tenure_from" id="tenure_from_e" class="form-control">
									        				<option value="0">Select From</option>
									        				<c:forEach var="i" items="${years}">
										        				<option value="${i}">${i}</option>
										        				
									        				</c:forEach>
									        			</select>
									        			</div>
									        			<div class="col-sm-4">
									        			<select class="form-control" name="tenure_to" id="tenure_to_e" class="form-control">
									        				<option value="0">Select To</option>
									        				<c:forEach var="i" items="${years}">
										        				<%-- <option value="${i}">${i}</option> --%>
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
									        					<!-- <input type="text" id="commity_e" name="commity" class="form-control"> -->
									        					<textarea rows="4" id="commity_e" name="commity" class="form-control"></textarea>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Term Ends </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="term_ends_e" name="term_ends" class="form-control add_datepicker_terms">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Year In Position </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="year_in_position_e" name="year_in_position" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Net Worth </label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="net_worth_e" name="net_worth" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label>Predecessor</label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" id="predecessor_e" name="predecessor" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> Facebook Page</label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input type="text" name="facebook_page" id="facebook_page_e" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> Twitter Handle </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<input type="text" name="twitter_handle" id="twitter_handle_edit" class="form-control">
									        			</div>
									        			
									        	</div>
									        	<div class="row form-group addTwitterAcctRow">
									        			<div class="col-sm-4"></div>
									        			<div class="col-sm-1">
									        			<button type="button" class="btn btn-follow" onclick="addTwitterAcctRow()">+</button>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-4">
									        					<label> Instagram Page</label>
									        			</div>
									        			<div class="col-sm-8">
									        					<input id="instagram_page_e" name="instagram_page" class="form-control">
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			
									        	<div class="col-sm-8">
									            	<span>Do you want to change the profile image?   </span><input type="checkbox" name="upload-image" onclick="addUploadPhoto()" >Yes 
									        	</div>	
									        	</div>
									        	<div style="display: none;" class="row form-group" id="view_browse_img">
									        			<div class="col-sm-4">
									        					<label> Upload Profile Photo</label>
									        			</div>
									        			<div class="col-sm-8">
									        					
									        					<input type="file" id="d_file_e" name="d_file">
									        			</div>
									        	</div>
									        	<div class="row form-group" id="upload-photo-div"></div>
									        	<div class="row form-group">
									        	<div class="col-sm-4">
									        	<label> About </label>
									        	</div>
									        	<div class="col-sm-8">
									        	<textarea rows="4" name="bio" id="bio_e" class="form-control"></textarea>
									        	</div>
									        	</div>
									        	
									        	<div class="row form-group">
										        	<div class="col-sm-4">
										        		<label> Career </label>
										        	</div>
										        	<div class="col-sm-8">
										        		<textarea rows="4" name="career" id="career_e" class="form-control"></textarea>
										        	</div>
									        	</div>
									        	<div class="row form-group">
									      				<div class="col-sm-12" align="center">
									      				<span  id="validate-edit-error-msg" style="color:red"></span>
									      				</div>
									      		</div>
									      </div>
										      <div class="modal-footer">
												      <button type="submit" class="btn-follow cursor_pointer" onclick="return validate_edit_celebrity()">Update</button>
												       <button type="button" class="btn-follow cursor_pointer" data-dismiss="modal">Close</button>
										      </div>
							      </form:form>
						    </div>
						  </div>
						</div>	
					
					<div class="modal fade" id="ViewCelebrityModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg" role="document">
						    <div class="modal-content">
						      <div class="modal-header modal-header-background">
						        <h5 class="modal-title" id="exampleModalLabel">Politician Information</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
									      <div class="modal-body" id="content_body">
									      		<div class="row form-group">
									      				<div class="col-sm-4 offset-sm-1">
									      						<img id="profile_pic_v" width="100%">
									      				</div>
									      		</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Politician Name </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="celebrity_name_v" name="celebrity_name"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Date Of Birth </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="date_of_birth_v" name="date_of_birth"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Place Of Birth </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="place_of_birth_v" name="place_of_birth"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label> Party Name</label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label name="party_name" id="party_name_v"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label> State</label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="state_name_v"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        		<div class="col-sm-1"></div>
									        	<div class="col-md-4"><label id="category"></label></div>
									        	<div class="col-md-6" id="pol_category"></div>
									        	</div>
									        	<div class="row form-group">
									        		<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Position </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="politician_position_v" name="politician_position"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Tenure </label>
									        			</div>
									        			<div class="col-sm-3">
									        			<label name="tenure_from" id="tenure_from_v"></label>
									        			</div>
									        			<div class="col-sm-3">
									        			<label name="tenure_to_v" id="tenure_to_v"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Committees </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="commity_v"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        			<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Term Ends </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="term_ends_v" name="term_ends"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Year In Position </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="year_in_position_v" name="year_in_position"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Net Worth </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="net_worth_v" name="net_worth"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label>Predecessor</label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="predecessor_v" name="predecessor"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label> Facebook Page</label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label name="facebook_page" id="facebook_page_v"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label> Twitter Handle </label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label name="twitter_handle" id="twitter_handle_v"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        			<div class="col-sm-4">
									        					<label> Instagram Page</label>
									        			</div>
									        			<div class="col-sm-6">
									        					<label id="instagram_page_v" name="instagram_page"></label>
									        			</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
									        	<div class="col-sm-4">
									        	<label> About </label>
									        	</div>
									        	<div class="col-sm-6">
									        	<label name="bio" id="bio_v"></label>
									        	</div>
									        	</div>
									        	<div class="row form-group">
									        	<div class="col-sm-1"></div>
										        	<div class="col-sm-4">
										        		<label> Career </label>
										        	</div>
										        	<div class="col-sm-6">
										        		<label name="career" id="career_v"></label>
										        	</div>
									        	</div>
									      </div>
						    </div>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src=" https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.js"></script>
<script src="resources/assets/js/jquery.dataTables.min.js"></script>
<script src="resources/assets/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#my_footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
	
	/* $('#my_data_table').DataTable(); */
	
	var start = new Date();
    start.setFullYear(start.getFullYear() - 78);
    var end = new Date();
    end.setFullYear(end.getFullYear());
 	
	$(".add_datepicker_terms").datepicker({
    	//minDate: 1,
    	//maxDate: -1,
    	yearRange: "1940:2025",
    	changeYear:true,
      	onSelect: function(dateText, inst) {
    	  	
    	  	var date2 = $('#term_ends').datepicker( "option", "dateFormat", 'dd-mm-yy' ).val();
    	  	var date3 = $('#term_ends_e').datepicker( "option", "dateFormat", 'dd-mm-yy' ).val();
    	  	
    	 		$("input[id='term_ends']").val(date2);
    	 		$("input[id='term_ends_e']").val(date3);
      	}
    });
	
		$("#dashboard").addClass('active-nav-link');
});




</script>
</body>
</html>