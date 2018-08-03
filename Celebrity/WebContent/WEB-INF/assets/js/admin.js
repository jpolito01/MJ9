var REDIRECT_TO="";
var CELEBRITY_ID=0;
var IS_ACTIVE=-1;
var SEARCH_ID=0;
var WEB_ID=0;
var flag=0;
var DeleteRoleId=0;
TAG=0;
INDEX=0;
ROLE_NAME=null;

function activate_deactivate(celebriity_id,is_active,yes_no,redirect_to) // if yes_no=="yes" action else no just close
{
	if(redirect_to!="" && redirect_to!=null)
		{
		REDIRECT_TO=redirect_to;
		}
	
	if(CELEBRITY_ID==0 && IS_ACTIVE==-1)
		{
			CELEBRITY_ID=celebriity_id;
			IS_ACTIVE=is_active;
		}
	if(IS_ACTIVE==0 && CELEBRITY_ID!=0) 
		{
			$("#modal-title").text("Deactivate Account");
			$("#btn_confirm_yes").attr("onclick","activate_deactivate('','','yes')");
			$("#span_msg").text("Are you sure you want to deactivate this account? No updates or posts related to this account will appear on the website.");
			$("#mod_confirm").modal('show');
		}
	else if(IS_ACTIVE==1 && CELEBRITY_ID!=0)
		{
		$("#modal-title").text("Activate Account");
		$("#btn_confirm_yes").attr("onclick","activate_deactivate('','','yes')");
			$("#span_msg").text("Are you sure you want to activate this account?");
			$("#mod_confirm").modal('show');
		}
	if(yes_no=='yes' && CELEBRITY_ID!=0)
		{
			document.forms[0].action="admin-home?active_inactive_is="+CELEBRITY_ID+"&status="+IS_ACTIVE+"&redirectTo="+REDIRECT_TO;
			document.forms[0].method="POST";
			document.forms[0].submit();
			CELEBRITY_ID=0;
			IS_ACTIVE=-1;
		}
}
function delete_celebrity(id,yes_no)
{
	if(CELEBRITY_ID==0)
		{
			CELEBRITY_ID=id;
			$("#modal-title").text("Delete Celebrity");
			$("#btn_confirm_yes").attr("onclick","delete_celebrity('','yes')");
			$("#span_msg").text("Are you sure you want to delete this celebrity? All data (News, posts, updates, comments etc.) related to this celebrity will be removed completely.");
			$("#mod_confirm").modal('show');
			
		}
	if(CELEBRITY_ID!=0 && yes_no=='yes')
		{
			document.forms[0].action="admin-home?delete_celebrity_id="+CELEBRITY_ID;
			document.forms[0].method="POST";
			document.forms[0].submit();
			CELEBRITY_ID=0;
		}
}


function validate_celebrity_edit_form()
	{
		var result=false;
		var celebrity_name=$("input[name='celebrity_name1']").val();
		var cat_count=$("input[name='category_name1']:checked").length;
		var country=$("select[name='country_id1']").val();
		var state_id=$("select[name='state_id1']").val();
		var file=$("input[name='d_file1']").val();
		if(celebrity_name!='' && cat_count>0 && country>0 && state_id>0 && file!='')
			{
				result=true;
			}
		return result;
	}
var _URL = window.URL || window.webkitURL;
$("#profile_photo_name").change(function (e) {
    var file, img;
    if ((file = this.files[0])) {
        img = new Image();
        img.onload = function () {
            
            if(this.width!=this.height && this.height<800)
            	{
            			alert("Your image resolution is "+this.width + "x" + this.height+"\nPlease upload square image which is greater than 800 X 800 resolution.");
            			return false;
            	}
        };
        img.src = _URL.createObjectURL(file);
    }
});
function validate_celebrity_form()
	{
	document.getElementById("addError").innerHTML = "";
		var result=false;
		var celebrity_name=$("#celebrity_name_add").val().trim();
		$("#celebrity_name_add").val(celebrity_name);
	//	if($("input[name='category_name']").length>0){
		var cat_count=$("input[name='category_name']:checked").length;
	
	//}
	//	var country=$("select[name='country_id']").val();
	//	var state_id=$("select[name='state_id']").val();
		if(celebrity_name!='' && cat_count>0)// && country>0 && state_id>0)// && file!='')
			{
				result=true;
			}
		if(!result)
			{
				/*alert("fill mandatory fields");*/
			document.getElementById("addError").innerHTML = "fill mandatory fields";
			}
		
		return result;
	}

function validate_celebrity_form_edit()
{
document.getElementById("addError").innerHTML = "";
	var result=false;
	var celebrity_name=$("#celebrity_name_edit").val().trim();
	$("#celebrity_name_edit").val(celebrity_name);
//	if($("input[name='category_name']").length>0){
	var cat_count=$("input[name='category_name']:checked").length;

//}
//	var country=$("select[name='country_id']").val();
//	var state_id=$("select[name='state_id']").val();
	if(celebrity_name!='' && cat_count>0)// && country>0 && state_id>0)// && file!='')
		{
			result=true;
		}
	if(!result)
		{
			/*alert("fill mandatory fields");*/
		document.getElementById("addError").innerHTML = "fill mandatory fields";
		}
	
	return result;
}

	function getState(country)
	{
		var country_id=country.value;
		if(country_id==0)
			{
				alert("Please select valid country");
				return false;
			}
		else{
			$.ajax({
				url:"get-state",
				data:{"country_id":country_id},
				success:function(res){
					
					var state_count=$("#state_id").find("option").length;
					var obj_state = JSON.parse(res);
					for(var s=0;s<obj_state.length;s++)
						{
						$('#state_id')
						.find('option')
    					.remove()
    					.end()
				         .append($("<option></option>")
				         .attr("value",obj_state[s].id+"")
				         .text(obj_state[s].name+"")); 
						}
				},
				error:function(){
					//alert("Somthing wrong");
				}
			});
			
		}
		
	}
function edit_profile(celebrity_id)
	{
		document.forms[0].action="admin-home?celebrity_id="+celebrity_id;
		document.forms[0].method="POST";
		document.forms[0].submit();	
	}

function Showcelebrity_Details(celebrity_id)
{
	document.forms[0].action="admin-home?show_celebrity_details="+celebrity_id;
	document.forms[0].method="POST";
	document.forms[0].submit();
		
}
function SaveCategory()
{
var category_name=$("input[name='category_name']").val().trim();
if(category_name=="")
	{
		$(".errorMsg").text("Category Name should not empty");
		$("#errorModal").modal('show');
		return false;
	}
else
	{
	return true;
	}
}

function EditCategory_name(tag,index,cat_name)
{
	if(tag.text=="Edit")
	{
		$("#span_category_name_"+index).remove();
		$("#td_category_name_"+index).append("<input type='text' class='form-control' value='"+cat_name+"' name='edit_category_name_"+index+"'>");
		$("input[name='edit_category_name_"+index+"']").focus();
		tag.text="Save";
		return false;
	}
	else if(tag.text=="Save")
		{
		var tab_name=$(".setting_active").attr('id');
		var category_name=$("input[name='edit_category_name_"+index+"']").val();
		document.getElementById("GeneralSetting").action="general-settings?category_id="+index+"&edit_category_name="+category_name+"&tab_name="+tab_name;
		document.getElementById("GeneralSetting").method="POST";
		document.getElementById("GeneralSetting").submit(); 
		
		}
}
function DeleteCateogry_id(cat_id)
{
	var tab_name=$(".setting_active").attr('id');
	document.getElementById("GeneralSetting").action="general-settings?delete_category_id="+cat_id+"&tab_name="+tab_name;
	document.getElementById("GeneralSetting").method="POST";
	document.getElementById("GeneralSetting").submit(); 
}
function ManageCategory(obj)
{
	document.getElementById("GeneralSetting").action="general-settings?tab_name="+obj.id;
	document.getElementById("GeneralSetting").method="POST";
	document.getElementById("GeneralSetting").submit(); 
}
function SaveCountry()
{
	var country_name=$("input[name='country_name']").val().trim();
	if(country_name=="")
		{
			alert("Please enter valid name."); 
			return false;
			
		}
	else
		{
		var tab_name=$(".setting_active").attr('id');
		document.getElementById("GeneralSetting").action="general-settings?tab_name="+tab_name+"&country_name="+country_name;
		document.getElementById("GeneralSetting").method="POST";
		document.getElementById("GeneralSetting").submit(); 
		}
	
}
function addCelebrityModal()
{
	$("#modal_addCelebrity").modal('show');
}
function DeleteSearchTerm(search_id)
{
	search_id=search_id.trim();
	if(search_id!="")
		{
			SEARCH_ID=search_id;
			$("#confirm_box").modal("show");
		}
	else if(SEARCH_ID!=0){
		document.getElementById("frm_user_searches").action="user-search-result?delete_id="+SEARCH_ID;
		document.getElementById("frm_user_searches").method="POST";
		document.getElementById("frm_user_searches").submit();
		SEARCH_ID=0;
	}
	
}
function editSearchTerm(search_id,search_key)
{
	search_id=search_id.trim();
	search_key=search_key.trim();
		if(search_id!='' && search_key!='')
			{
				$("#edit_search").val(search_id);
				$("input[name='search_term']").val(search_key);
				$("#mod_editSearchTerm").modal("show");
			}
		else
			{
				search_id=$("#edit_search").val();
				search_key=$("input[name='search_term']").val();
				document.getElementById("frm_user_searches").action="user-search-result?search_id="+search_id+"&search_key="+search_key;
				document.getElementById("frm_user_searches").method="POST";
				document.getElementById("frm_user_searches").submit();
			}
}
function deleteBlackList_website(web_id)
{
	document.getElementById("blacklist").action="blacklist-website?delete_web_id="+web_id;
	document.getElementById("blacklist").method="POST";
	document.getElementById("blacklist").submit();
}
function editBlackList_website(website_name,web_id)
{
	website_name=website_name.trim();
	web_id=web_id.trim();
	
	if(website_name!='' && web_id!='')
		{
		WEB_ID=web_id;
			$("#edit_blacklist_website").modal("show");
			$("input[name='edited_website_name']").val(website_name);
		}
	else{
		var website=$("input[name='edited_website_name']").val();
		document.getElementById("blacklist").action="blacklist-website?edit_web_id="+WEB_ID+"&website_name="+website;
		document.getElementById("blacklist").method="POST";
		document.getElementById("blacklist").submit();
		WEB_ID=0;
	}
	
}


function validate_movieblog_form()
{
	var result=false;
	var blog_title=$("input[name='blog_title']").val();
	//var movie_blogs=$("textarea[name='movie_blogs']").val();
	//var movie_id=$("select[name='movie_id']").val();

	if(blog_title!='' && movie_blogs!='')
		{
			result=true;
		}
	if(!result)
		{
			document.getElementById("addError").innerHTML = "Please fill mandatory fields";
		}
		var html_code_description= $("#movieBlogModal .Editor-editor").html();
	  $("#movie_blogs").val(html_code_description);
	
	return result;
}
function validate_movieblog_form_update()
{
	var result=false;
	var blog_title=$("#blog_title_update").val();
	//var movie_blogs=$("#movie_blogs_update").val();
	//var movie_id=$("#movie_id_update").val();
	if(blog_title!='' && movie_blogs!='')
		{
			result=true;
		}
	if(!result)
		{
			document.getElementById("editError").innerHTML = "Please fill mandatory fields";
		}
	 var html_code_description= $("#movieBlogEditModal .Editor-editor").html();
	  $("#movie_blogs_update").val(html_code_description);
	return result;
}
function edit_blog(blog_id){
	$("#blogImageUpdate").parent().show();
	$.ajax({
		url: "get_blog_data",
		method:"POST",
		data:"blog_id="+blog_id,
		success:function(res){
			var blogData=JSON.parse(res);
			$("#blog_title_update").val(blogData.blog_title);
			//$("#movie_blogs_update").val(blogData.movie_blogs);
			$("#movieBlogEditModal .Editor-editor").html(blogData.movie_blogs);
			if(typeof blogData.blog_image != 'undefined' && blogData.blog_image!=""){
				$("#blogImageUpdate").attr("src",blogData.blog_image);
				$("#blog_image").attr("value",blogData.blog_image);
			}else{
				$("#blogImageUpdate").parent().hide();
			}
	//		$('#movie_id_update >option[value='+blogData.movie_id+']').attr('selected', true);
			
		},
		error:function(){
			//alert("Somthing wrong");
		}
	});
}

function delete_blog(blog_id,blog_image,yes_no){
 if(CELEBRITY_ID==0)
	{
		CELEBRITY_ID=blog_id;
		$("#modal-title").text("Delete Blog");
		$("#btn_confirm_yes").attr("onclick","delete_blog('"+blog_id+"','"+blog_image+"','yes')");
		$("#span_msg").text("Are you sure you want to delete this blog.");
		$("#mod_confirm").modal('show');
	}
if(CELEBRITY_ID!=0 && yes_no=='yes')
	{
	CELEBRITY_ID=0;
	$.ajax({
		url: "delete-blog",
		method:"POST",
		data:{"blog_id":blog_id,"blog_image":blog_image},
		success:function(data){
			window.location.reload();	
		},
		error:function(){
			//alert("Somthing wrong");
		}
	});
	}
}
function edit_movie(celebrity_id)
{
	document.forms[0].action="edit-movie-display?celebrity_id="+celebrity_id;
	document.forms[0].method="POST";
	document.forms[0].submit();	
//	$.ajax({
//		url: "get_celebrity_data",
//		method:"POST",
//		data:{"celebrity_id":celebrity_id},
//		success:function(data){
//			var movieData=JSON.parse(data);
//			$("#celebrity_name_update").val(movieData.celebrity_name);
//			$("#celebrity_bio_update").html(movieData.celebrity_bio);
//			$("#facebok_page_name_update").val(movieData.facebook_page);
//			$("#twitter_handle_name_update").val(movieData.twitter_page);
//			$("#instagram_page_name_update").val(movieData.insta_page);
//			if(typeof movieData.celebrity_profile_photo != 'undefined' && movieData.celebrity_profile_photo!=""){
//				$("#celebrity_profile_photo").attr("src",movieData.celebrity_profile_photo);
//			}
//			$("#modal_editCelebrity").modal('show');
//
//			//window.location.reload();	
//		},
//		error:function(){
//			alert("Somthing wrong");
//		}
//	});
}

function validate_movie_form_update()
{
	var result=false;
	var celebrity_name=$("#celebrity_name_update").val();
	if(celebrity_name!='')// && country>0 && state_id>0)// && file!='')
		{
			result=true;
		}
	if(!result)
		{
			alert("fill mandatory fields");
		}
	
	return result;
}

function validateMovieForm(){
	var result=true;
	var celebrity_name=$("#celebrity_name_add").val();
	if(celebrity_name=="" || celebrity_name==null)// && country>0 && state_id>0)// && file!='')
		{
		result=false;
		$(".errorMsg").text("Fill mandatory fields");
		$("#errorModal").modal('show');
		return false;
		}
	
	return result;
}

function validateMovieForm1(){
	var result=true;
	var celebrity_name=$("#celebrity_name_edit").val();
	if(celebrity_name=="" || celebrity_name==null)// && country>0 && state_id>0)// && file!='')
		{
		result=false;
		$(".errorMsg").text("Fill mandatory fields");
		$("#errorModal").modal('show');
		return false;
		}
	
	return result;
}

function sentTextMail()
{
	document.forms[0].action="sent-text-mail";
	document.forms[0].method="post";
	document.forms[0].submit();
}
function ManageRole(obj)
{
	document.getElementById("ManageRole").action="manage-role?tab_name="+obj.id;
	document.getElementById("ManageRole").method="POST";
	document.getElementById("ManageRole").submit(); 
}
function saveRole()
{
var caracter_name=$("input[name='caracter_name']").val().trim();
if(caracter_name=="")
	{
		$(".errorMsg").text("Role Name should not empty");
		$("#errorModal").modal('show');
		return false;
	}
else
	{
	return true;
	}
}
function DeleteRole_id(delete_role_id)
{
	if(delete_role_id!='')
	{
		DeleteRoleId=delete_role_id;
	}
	else{
	document.getElementById("ManageRole").action="manage-role?delete_role_id="+DeleteRoleId;
	document.getElementById("ManageRole").method="POST";
	document.getElementById("ManageRole").submit(); 
	}
}
function EditRole_name(tag,index,role_name)
{
	if(tag!='' && index!='' && role_name!='')
	{
		TAG=tag;
		INDEX=index;
		ROLE_NAME=role_name;
	}
	if(TAG.text=="Edit")
	{
		$("#span_role_name_"+INDEX).remove();
		$("#td_role_name_"+INDEX).append("<input type='text' class='form-control' value='"+ROLE_NAME+"' name='edit_role_name_"+INDEX+"'>");
		$("input[name='edit_role_name_"+INDEX+"']").focus();
		TAG.text="Save";
		return false;
	}
	else if(TAG.text=="Save")
		{
		if(tag!='' && index!='' && role_name!='')
		{
			$("#edit-role").modal('show');
		}
		else{
			var edit_role_name=$("input[name='edit_role_name_"+INDEX+"']").val();
			document.getElementById("ManageRole").action="manage-role?edited_role_id="+INDEX+"&edit_role_name="+edit_role_name;
			document.getElementById("ManageRole").method="POST";
			document.getElementById("ManageRole").submit(); 
			}
		}
}

function delete_movie(id,yes_no)
{
	if(CELEBRITY_ID==0)
		{
			CELEBRITY_ID=id;
			$("#modal-title").text("Delete Celebrity");
			$("#btn_confirm_yes").attr("onclick","delete_movie('','yes')");
			$("#span_msg").text("Are you sure you want to delete this movie? All data (News, posts, updates, comments etc.) related to this movie will be removed completely.");
			$("#mod_confirm").modal('show');
			
		}
	if(CELEBRITY_ID!=0 && yes_no=='yes')
		{
			document.forms[0].action="delete-movie?delete_celebrity_id="+CELEBRITY_ID;
			document.forms[0].method="POST";
			document.forms[0].submit();
			CELEBRITY_ID=0;
		}
}

function deleteMovieCastRole(id,yes_no)
{
	if(CELEBRITY_ID==0)
		{
			CELEBRITY_ID=id;
			$("#modal-title").text("Delete Movie Role");
			$("#btn_confirm_yes").attr("onclick","deleteMovieCastRole('','yes')");
			$("#span_msg").text("Are you sure you want to delete this role?");
			$("#mod_confirm").modal('show');
		}
	if(CELEBRITY_ID!=0 && yes_no=='yes')
		{
			document.forms[0].action="delete-cast-role?delete_celebrity_id="+CELEBRITY_ID;
			document.forms[0].method="POST";
			document.forms[0].submit();
			CELEBRITY_ID=0;
		}
}
function changeLimit()
{
	var limit=$("select[name='limit']").val();
	document.forms[0].action="celebrity_pagination?limit="+limit;
	document.forms[0].method="post";
	document.forms[0].submit();
}
function changeLimit1()
{
	var limit=$("select[name='limit']").val();
	document.forms[0].action="requested-celebrities?limit="+limit;
	document.forms[0].method="post";
	document.forms[0].submit();
}
function searchCelebrity()
{
	if($("#search_celebrity_key").val()!="")
	{
		var search_celebrity_key=$("#search_celebrity_key").val().trim();
		document.forms[0].action="celebrity_pagination?search_celebrity_key="+search_celebrity_key;
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	/*else{
			$(".errorMsg").text("Please enter search key");
			$("#errorModal").modal('show');
			return false;
	}*/
}