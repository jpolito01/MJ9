<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Celebrity - Add Celebrity Cast</title>
<link rel="stylesheet" href="resources/assets/css/common-style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div id="my_page_container" class="container page-padding-top my_container_height" style="width: 99%;padding: 50px;">
	<form action="add-cast" method="post">
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
		  <div class="col-sm-9 set-font">
		   <div class="row">
		   		<div class="col-sm-12">
			   		<label class="dashboard-title">${movie_name}</label>
			   	</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-6">
							<label>Add Celebrity Role</label>
						</div>
					</div>
				</div>
			</div>
			
			
			<c:if test="${empty edit_movie_cast}">	
			
			<div class="add-cast-box form-group" id="strText0">
				<div class="row" id="add-celeb-cast">
				    <div class="col-md-10">
					    <div class="row form-group">
							<div class="col-md-3">
								<input type="radio" name="new_existing0" id="existing_radio0" checked="checked"> Existing celebrity
							</div>
							<div class="col-md-3">
								<input type="radio" name="new_existing0" id="new0" onclick="return showCelebrityModal('0')" > New celebrity
							</div>
						</div>
					</div> 
				</div>
			<div id="cast_content0">
				<div class="row" id="celeb-cast-info">
			        <div class="col-md-12">
				        <div class="row">
				        <!-- <div class="col-md-3 cast-text">Select celebrity name</div> -->
							<div class="col-md-6 form-group">
							<span>Select celebrity name: </span>
								<select id="celeb_name0" name="casted_celebrity" class="form-control required">
									<option value="0">Select</option>
									<c:forEach var="a" items="${all_celebrities_info}">
										<option value="${a.getCelebrity_id()}">${a.getCelebrity_name()}</option>
									</c:forEach>
							    </select>
							</div>
						<!-- <div class="col-md-3 cast-text">Select celebrity role</div> -->
							<div class="col-md-6 form-group">
							 <span>Select celebrity role: </span>
								<select id="cast0" name="caracter_name" class="form-control required">
									<option value="0">Select</option>
									<c:forEach var="c" items="${all_casts}">
										<option value="${c.getId()}">${c.getCaracter_name()}</option>
									</c:forEach>
							    </select>
							</div>
						</div>
					</div> 
				  </div>
				</div>
			</div>
			</c:if>
			
			<!-- Edit Cast -->
								     	<c:if test="${not empty edit_cast && edit_cast=='yes' && not empty edit_movie_cast}">	
								       	     	<c:forEach var="ce" items="${edit_movie_cast}" varStatus="a">
			<div class="add-cast-box form-group" id="strText${a.index}">
				<div class="row" id="add-celeb-cast">
				    <div class="col-md-10">
					    <div class="row form-group">
							<div class="col-md-3">
								<input type="radio" name="new_existing0" id="existing_radio${a.index}" checked="checked"> Existing celebrity
							</div>
							<div class="col-md-3">
								<input type="radio" name="new_existing0" id="new${a.index}" onclick="return showCelebrityModal('${a.index}')" > New celebrity
							</div>
						</div>
					</div> 
				</div>
			<div id="cast_content${a.index}">
				<div class="row" id="celeb-cast-info">
			        <div class="col-md-12">
				        <div class="row">
				        <!-- <div class="col-md-3 cast-text">Select celebrity name</div> -->
							<div class="col-md-6 form-group">
							<span>Select celebrity name: </span>
								<select id="celeb_name0" name="casted_celebrity" class="form-control required">
									<option value="0">Select</option>
									<c:forEach var="a" items="${all_celebrities_info}">
										<option value="${a.getCelebrity_id()}" <c:if test="${ce.getCelebrity_id()==a.getCelebrity_id()}">selected="selected"</c:if>>${a.getCelebrity_name()}</option>
									</c:forEach>
							    </select>
							</div>
						<!-- <div class="col-md-3 cast-text">Select celebrity role</div> -->
							<div class="col-md-6 form-group">
							 <span>Select celebrity role: </span>
								<select id="cast0" name="caracter_name" class="form-control required">
									<option value="0">Select</option>
									<c:forEach var="c" items="${all_casts}">
										<option value="${c.getId()}" <c:if test="${ce.getCaracter()==c.getId()}">selected="selected"</c:if>>${c.getCaracter_name()}</option>
									</c:forEach>
							    </select>
							</div>
						</div>
					</div> 
				  </div>
				</div>
				<div id="cast_delete${a.index}">
									<input type="button" class="btn-flat btn-primary" value="Delete Role" onclick="return deleteMovieCastRole('${ce.getId()}','no-action');">
				</div>
			</div>
			</c:forEach>
			</c:if>
			<div id="content"></div>
			<div class="row form-group">
			    <div class="col-md-5">
							<input type="button" value="Add more" class="btn-flat" onclick="addCast()">
							<input type="button" value=" Remove " class="btn-flat" onclick="removeElement()">
						</div>
				<div class="col-md-6" align="left">
					<input type="submit" class="btn-flat btn-primary" value="Save" onclick="return validateCasts()">
				</div>
			</div>
	      </div>
	    </div>
	    </form>
	  </div>
	  
	  
	  <div id="modal_addCelebrity" class="modal fade" role="dialog">
				 	<div class="modal-dialog modal-lg">
						<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Add Celebrity</h4>
					      </div>
					      <div class="modal-body">
					      	<form:form method="post" enctype="multipart/form-data" >
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
						       		<%-- <div class="col-sm-12 padding-one" >
						       			<div class="col-sm-3">
						       				Select Category
						       			</div>
						       			<div class="col-sm-9">
						       				<c:forEach var="cat" items="${celebrity_category}">
						       					<input type="checkbox" name="category_name" id="category_name${cat.getCategory_id()}" value="${cat.getCategory_id()}"> ${cat.getCategory_name()}
						       				</c:forEach>
						       				
						       			</div>
						       		</div> --%>
						       		
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
					       				<input type="button" class="btn-flat btn-primary" value="Save" onclick="return showCelebrityModal('');">
					       				<input type="button" class="btn-flat btn-primary"  data-dismiss="modal" value="Close">
					       			</div>
					       		</div>
					       	</form:form>
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
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script src="resources/assets/js/admin.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var max_height=$("#my_header").height()+$("#footer").height();
	$("#my_page_container").css("min-height",($(window).height()-max_height)+220);
	/* $("#celeb_name"+'${index}').val('${movie_id}');
	$("#existing"+'${index}').attr('checked',true); */
});

var intTextBox;
var castTextBox;
var INDEX;

function showCelebrityModal(index)
{
	if(index!='')
	{
		INDEX=index;
		$("#existing_radio"+INDEX).attr('checked',false);
		$("#modal_addCelebrity").modal('show');	
	}
	else{
		var name=$("#celebrity_name_add").val();
		var bio=$("#celebrity_bio").val();
		var facebook_page=$("#facebok_page_name").val();
		var twitter_page=$("#twitter_handle_name").val();
		var insta_page=$("#instagram_page_name").val();
		
		if(name=="" || name==null)
			{
			$(".errorMsg").text("Please enter celebrity name");
			$("#errorModal").modal('show');
			return false;
			}
	    /* var category=[];
	    
		$("input[name='category_name']:checked").each(function(){
			category.push($(this).val());
		 }); */
	    
	    var celeb_name_select=$("#celeb_name"+INDEX);
	    
	    var formData = new FormData();
	    if($('input[type=file]')[0].files[0]!=null && $('input[type=file]')[0].files[0]!=""){
	    formData.append('d_file', $('input[type=file]')[0].files[0]);
	    }
	    if(name!=null && name!="")
	    	{
	    	formData.append('name', name);
	    	}
	    if(bio!=null && bio!="")
    	{
	    	formData.append('bio', bio);
    	}
	    if(facebook_page!=null && facebook_page!="")
    	{
	    	formData.append('facebook_page', facebook_page);
    	}
	    if(twitter_page!=null && twitter_page!="")
    	{
	    	formData.append('twitter_page', twitter_page);
    	}
	    if(insta_page!=null && insta_page!="")
    	{
	    	formData.append('insta_page', insta_page);
    	}
	    /* if(category!=null && category!="")
    	{
	    	formData.append('category', category);
    	} */
    	var url_path;
	    if($('input[type=file]')[0].files[0]!=null){
	    	url_path="add-cast-function";
    	}
    	else{
    		url_path="add-cast-function-photo";
    	}
	    $.ajax({
			url:url_path,
			data:formData,
			processData : false,
	        contentType : false,
	        type : 'POST',
			success:function(res){
			
				var data = JSON.parse(res);
				
				celeb_name_select.empty();
				for(var i=0;i<data.celebrity_info.length;i++)
				{
					celeb_name_select.append($("<option></option>")
						     .attr("value", data.celebrity_info[i].celebrity_id).text(data.celebrity_info[i].celebrity_name));
					$("#celeb_name"+INDEX).val(data.celebrity_info[i].new_celeb_id);
				}
				
				$("#modal_addCelebrity").modal('hide');
				
				$("#new"+INDEX).attr('checked',false);
				$("#existing_radio"+INDEX).attr('checked',true);
			},
			error:function(){
				/* alert("Somthing wrong"); */
			}
		});
	}
}
/* function showCelebrityModal1(){
	alert("inside");
} */

function addCast() {
	 intTextBox = $("#add-celeb-cast >div").length;
	/* intTextBox = 0; */

	var contentID = document.getElementById('content');
	var newTBDiv = document.createElement('div');
	newTBDiv.setAttribute('id', 'strText' + intTextBox);
	newTBDiv.setAttribute('class', 'add-cast-box form-group');
	newTBDiv.innerHTML = '<div class="row" id="add-celeb-cast">'
		    + '<div class="col-md-10">'
		    + '<div class="row form-group">'
			+ '<div class="col-md-3">'
			+ '<input type="radio" name="new_existing'+intTextBox+'" id="existing_radio'+intTextBox+'" checked="checked"> Existing celebrity'
			+ '</div>'
			+ '<div class="col-md-3">'
			+ '<input type="radio" name="new_existing'+intTextBox+'" id="new'+intTextBox+'" onclick="return showCelebrityModal('+intTextBox+')" > New celebrity'
			+ '</div>'
			+ '</div>'
			+ '</div>' 
			+ '</div>'
			+ '<div id="cast_content'+intTextBox+'">'
			+ '<div class="row" id="celeb-cast-info">'
		    + '<div class="col-md-12">'
		    + '<div class="row">'
			+ '<div class="col-md-6 form-group">'
			+ ' <span>Select celebrity name: </span>'
			+' <c:set var="search" value="'" />'
			+' <c:set var="replace" value="\\'" />'
			+ '<select id="celeb_name'+intTextBox+'" name="casted_celebrity" class="form-control">'
			+ '<option value="0">Select</option>'
			+ '<c:forEach var="a" items="${all_celebrities_info}">'
			+ '<c:set var="string2" value="${a.getCelebrity_name()}" />'
			+ '<option value="${a.getCelebrity_id()}">${fn:replace(string2, search, replace)}</option>'
			+ '</c:forEach>'
		    + '</select>'
			+ '</div>'
			+ '<div class="col-md-6 form-group">'
			+ ' <span>Select celebrity role: </span>'
			+ '<select id="cast'+intTextBox+'" name="caracter_name" class="form-control">'
			+ '<option value="0">Select</option>'
			+ '<c:forEach var="c" items="${all_casts}">'
			+ '<option value="${c.getId()}">${c.getCaracter_name()}</option>'
			+ '</c:forEach>'
		    + '</select>'
			+ '</div>'
			+ '</div>'
			+ '</div>' 
			+ '</div>';
			+ '</div>';

	contentID.appendChild(newTBDiv);
	/* intTextBox=intTextBox+1; */
}

function removeElement() {
	if (intTextBox != 0) {
		
		var contentID = document.getElementById('content');
		contentID.removeChild(document.getElementById('strText'
				+ intTextBox));
		intTextBox = intTextBox - 1;
	}
}

function addCastinfo(index)
{
	$("#new"+index).attr('checked',false);
	castTextBox = 1;
	/* intTextBox = 0; */
	var contentID = document.getElementById('cast_content'+index);
	var newTBDiv = document.createElement('div');
	newTBDiv.setAttribute('id', 'castInfoText' + castTextBox);
	/* newTBDiv.setAttribute('class', 'add-cast-box'); */
	newTBDiv.innerHTML = '<div class="row" id="celeb-cast-info">'
		    + '<div class="col-md-12">'
		    + '<div class="row">'
			+ '<div class="col-md-12 form-group">'
			+' <c:set var="search1" value="'" />'
			+' <c:set var="replace1" value="\\'" />'
			+ '<select id="celeb_name'+castTextBox+'" name="casted_celebrity" class="form-control required">'
			+ '<option value="0">Select</option>'
			+ '<c:forEach var="a" items="${all_celebrities_info}">'
			+ '<c:set var="string3" value="${a.getCelebrity_name()}" />'
			+ '<option value="${a.getCelebrity_id()}">${fn:replace(string3, search1, replace1)}</option>'
			+ '</c:forEach>'
		    + '</select>'
			+ '</div>'
			
			+ '<div class="col-md-12 form-group">'
			+ '<select id="cast'+castTextBox+'" name="caracter_name" class="form-control required">'
			+ '<option value="0">Select</option>'
			+ '<c:forEach var="c" items="${all_casts}">'
			+ '<option value="${c.getId()}">${c.getCaracter_name()}</option>'
			+ '</c:forEach>'
		    + '</select>'
			+ '</div>'
			+ '</div>'
			+ '</div>' 
			+ '</div>';
            
	contentID.appendChild(newTBDiv);
	castTextBox=castTextBox+1;
}

function validateCasts()
{
	var celebrity_name=[];
	var caracter_name=[];
	
	var conceptName = $('#celeb_name0').find(":selected").text();
	var count=0;
	if(intTextBox=="undefined" || intTextBox==null || intTextBox=="")
		{
		count=0;
		}
	else{
		count=intTextBox;
	}
	
   for(var i=0;i<=count;i++)
	{
		$('#celeb_name'+i).each(function(){
			celebrity_name.push($(this).val());
		 });
	}
   
   for(var i=0;i<=count;i++)
	{
		$('#cast'+i).each(function(){
			caracter_name.push($(this).val());
		 });
	}
   
	for(var i=0;i<celebrity_name.length;i++)
		{
			if(celebrity_name[i]==0)
			{
				$(".errorMsg").text("Please select celebrity name");
				$("#errorModal").modal('show');
				return false;
			}
		}
	
	for(var i=0;i<caracter_name.length;i++)
	{
		if(caracter_name[i]==0)
		{
			$(".errorMsg").text("Please select cast name");
			$("#errorModal").modal('show');
			return false;
		}
	}
}

</script>
</html>