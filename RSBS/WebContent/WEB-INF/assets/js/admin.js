var PARTY_ID=0,DELETE_PARTY_ID=0,DELETE_WEB_ID=0,DELETE_CELEBRITY_ID=0,SEARCH_KEY_ID=0,SEARCH_KEY=null,DELETE_SEARCH_KEY_ID=0, BLOG_ID;

function ActivateDeactivate(celebrity_id, flag)
{
	document.getElementById("show_celeb").action="manage-politician?ActDctCelebrityId="+celebrity_id+"&flag="+flag;
	document.getElementById("show_celeb").method="POST";
	document.getElementById("show_celeb").submit();
}
function EditCelebrity(celebrity_id)
{
   $("#twitter_handle_edit").val("");
   $('.additionalTwitterAccounts').remove();
	$.ajax({
			url:"set-attributes",
			data:"celebId_edit="+celebrity_id,
			success:function(res){
				var json = JSON.parse(res);
				
				$("#upload-profile-photo-div").empty();
				$("#validate-edit-error-msg").empty();
				$("#upload-photo-div").empty();
				$(".categoryEditName").attr("checked",false);
				for(var i=0;i<json.basic_info.length;i++)
				{
				$("input[id='celebrity_name_e']").val(json.basic_info[i].celebrity_name);
				$("select[id='party_id_e']").val(json.basic_info[i].party_id);
				$("input[id='facebook_page_e']").val(json.basic_info[i].facebook_page);
				$("input[id='twitter_handle_e']").val(json.basic_info[i].twitter_data);
				$("input[id='instagram_page_e']").val(json.basic_info[i].instagram_page);
				$('#bio_e').text(json.basic_info[i].bio);
				
				$('#date_of_birth_e').val(json.basic_info[i].date_of_birth);
				$('#place_of_birth_e').val(json.basic_info[i].place_of_birth);
				$('#politician_position_e').val(json.basic_info[i].position);
				$('#term_ends_e').val(json.basic_info[i].term_ends);
				$('#year_in_position_e').val(json.basic_info[i].year);
				$('#net_worth_e').val(json.basic_info[i].net_worth);
				$('#predecessor_e').val(json.basic_info[i].predecessor);
				$('#career_e').text(json.basic_info[i].career);
				$('#commity_e').val(json.basic_info[i].commity);
				
				if(json.basic_info[i].tenure_from==null || json.basic_info[i].tenure_from=='' || json.basic_info[i].tenure_from==undefined)
				{
					$('#tenure_from_e').val(0);
				}
				else{
					$('#tenure_from_e').val(json.basic_info[i].tenure_from);
				}
				
				if(json.basic_info[i].tenure_to==null || json.basic_info[i].tenure_to=='' || json.basic_info[i].tenure_to==undefined)
				{
					$('#tenure_to_e').val(0);
				}
				else{
					$('#tenure_to_e').val(json.basic_info[i].tenure_to);
				}
				
				document.getElementById("profile_pic").src="/PoliticianPhotoes"+json.basic_info[i].profile_photo;
				$("select[id='state_id_e']").val(json.basic_info[i].state_id);
				
				
				/*if(json.basic_info[i].profile_photo!="" && json.basic_info[i].profile_photo!=null && json.basic_info[i].profile_photo!="null" && json.basic_info[i].profile_photo!="undefined")
					{
					
						$("#upload-profile-photo-div").append(
								' <div class="col-sm-8"> '+
				            	' <input type="checkbox" name="upload-image" onclick="addUploadPhoto()"> Do you want to replace the profile image? '+
				        	    ' </div> '	
						);
					}
				else{
					
					$("#upload-profile-photo-div").append(
							' <div class="col-sm-4"> '+
        					' <label> Upload Profile Photo</label> '+
        			        ' </div> '+
        			        ' <div class="col-sm-8"> '+
        					' <input type="file" id="d_file_edit" name="d_file" class="d_file_edit_class"> '+
        			        ' </div> '
					);
				}*/
				}
				
				for(var j=0;j<json.category.length;j++)
				{
					var category_id=json.category[j].category_id;
					$("#edit_cat_"+category_id).attr("checked",true);
				}
				for(var k=0;k<json.twitter.length;k++)
				{
					if(k==0){
						$("#twitter_handle_edit").val(json.twitter[k].twitter_handle);
					}else{
					$('<div class="row form-group additionalTwitterAccounts"><div class="col-sm-4"></div><div class="col-sm-6"><input type="text" name="twitter_handle" class="form-control" value="'+json.twitter[k].twitter_handle+'"></div><div class="col-sm-1"><button type="button" class="btn btn-follow" onclick="deleteTwitterAcctRow(this)">X</button></div></div>').insertBefore( ".addTwitterAcctRow" );
					}
				}
				
			},
			error:function(){
				
			}
		});
}
function ViewCelebrity(celebrity_id)
{
	String.prototype.replaceAll = function(target, replacement) {
		  return this.split(target).join(replacement);
	};
	
	$('#celebrity_name_v').text("");
	$('#celebrity_name_title').text("");
	$('#party_name_v').text("");
	$('#facebook_page_v').text("");
	$('#twitter_handle_v').text("");
	$('#instagram_page_v').text("");
	$('#bio_v').text("");
	$('#state_name_v').text("");
	$('#date_of_birth_v').text("");
	$('#place_of_birth_v').text("");
	$('#tenure_from_v').text("");
	$('#politician_position_v').text("");
	$('#term_ends_v').text("");
	$('#year_in_position_v').text("");
	$('#net_worth_v').text("");
	$('#predecessor_v').text("");
	$('#career_v').text("");
	document.getElementById("profile_pic_v").src="";
	$('#category').text("");
	$('#commity_v').html("");

	$.ajax({
		url:"show-celebrity",
		data:"celebId_show="+celebrity_id,
		success:function(res){
			var json = JSON.parse(res);
			
			for(var i=0;i<json.basic_info.length;i++)
			{
			if(json.basic_info[i].celebrity_name!=null && json.basic_info[i].celebrity_name!="" && json.basic_info[i].celebrity_name!="null" && json.basic_info[i].celebrity_name!="undefined")
			{
				$('#celebrity_name_v').text(json.basic_info[i].celebrity_name);
				$('#celebrity_name_title').text("Politician Name");
			}
			if(json.basic_info[i].party_name!=null && json.basic_info[i].party_name!="" && json.basic_info[i].party_name!="null" && json.basic_info[i].party_name!="undefined")
			{
				$('#party_name_v').text(json.basic_info[i].party_name);
			}
			if(json.basic_info[i].facebook_page!=null && json.basic_info[i].facebook_page!="" && json.basic_info[i].facebook_page!="null" && json.basic_info[i].facebook_page!="undefined")
			{
				$('#facebook_page_v').text(json.basic_info[i].facebook_page);
			}
			if(json.basic_info[i].twitter_data!=null && json.basic_info[i].twitter_data!="" && json.basic_info[i].twitter_data!="null" && json.basic_info[i].twitter_data!="undefined")
			{
				$('#twitter_handle_v').text(json.basic_info[i].twitter_data);
			}
			if(json.basic_info[i].instagram_page!=null && json.basic_info[i].instagram_page!="" && json.basic_info[i].instagram_page!="null" && json.basic_info[i].instagram_page!="undefined")
			{
				$('#instagram_page_v').text(json.basic_info[i].instagram_page);
			}
			if(json.basic_info[i].bio!=null && json.basic_info[i].bio!="" && json.basic_info[i].bio!="null" && json.basic_info[i].bio!="undefined")
			{
				$('#bio_v').html(json.basic_info[i].bio.replaceAll("\n", "<br/>"));
			}
			if(json.basic_info[i].state!=null && json.basic_info[i].state!="" && json.basic_info[i].state!="null" && json.basic_info[i].state!="undefined")
			{
				$('#state_name_v').text(json.basic_info[i].state);
			}
			if(json.basic_info[i].date_of_birth!=null && json.basic_info[i].date_of_birth!="" && json.basic_info[i].date_of_birth!="null" && json.basic_info[i].date_of_birth!="undefined")
			{
				$('#date_of_birth_v').text(json.basic_info[i].date_of_birth);
			}
			if(json.basic_info[i].place_of_birth!=null && json.basic_info[i].place_of_birth!="" && json.basic_info[i].place_of_birth!="null" && json.basic_info[i].place_of_birth!="undefined")
			{
				$('#place_of_birth_v').text(json.basic_info[i].place_of_birth);
			}
			/*if(json.basic_info[i].position!=null && json.basic_info[i].position!="" && json.basic_info[i].position!="null" && json.basic_info[i].position!="undefined")
			{
				$('#politician_position_v').text(json.basic_info[i].position);
			}*/
			
			var tenure_from=0;
			var tenure_to=0;
			var d = new Date();
			var year = d.getFullYear();
			
			if(json.basic_info[i].tenure_from!=null && json.basic_info[i].tenure_from!="" && json.basic_info[i].tenure_to!="null" && json.basic_info[i].tenure_to!="undefined")
			{
				tenure_from=json.basic_info[i].tenure_from;
			}
			if(json.basic_info[i].tenure_to!=null && json.basic_info[i].tenure_to!="" && json.basic_info[i].tenure_to!="null" && json.basic_info[i].tenure_to!="undefined")
			{
				if(json.basic_info[i].tenure_to == year)
				{
					tenure_to="Present";
				}
				else{
					tenure_to=json.basic_info[i].tenure_to;
				}
			}
			
				$('#tenure_from_v').text(tenure_from +" - "+ tenure_to);
			
			if(json.basic_info[i].position!=null && json.basic_info[i].position!="" && json.basic_info[i].position!="null" && json.basic_info[i].position!="undefined")
			{
				$('#politician_position_v').html(json.basic_info[i].position.replaceAll("\n", "<br/>"));
			}
			if(json.basic_info[i].term_ends!=null && json.basic_info[i].term_ends!="" && json.basic_info[i].term_ends!="null" && json.basic_info[i].term_ends!="undefined")
			{
				$('#term_ends_v').text(json.basic_info[i].term_ends);
			}
			if(json.basic_info[i].year!=null && json.basic_info[i].year!="" && json.basic_info[i].year!="null" && json.basic_info[i].year!="undefined")
			{
				$('#year_in_position_v').text(json.basic_info[i].year);
			}
			if(json.basic_info[i].net_worth!=null && json.basic_info[i].net_worth!="" && json.basic_info[i].net_worth!="null" && json.basic_info[i].net_worth!="undefined")
			{
				$('#net_worth_v').text(json.basic_info[i].net_worth);
			}
			if(json.basic_info[i].predecessor!=null && json.basic_info[i].predecessor!="" && json.basic_info[i].predecessor!="null" && json.basic_info[i].predecessor!="undefined")
			{
				$('#predecessor_v').text(json.basic_info[i].predecessor);
			}
			if(json.basic_info[i].career!=null && json.basic_info[i].career!="" && json.basic_info[i].career!="null" && json.basic_info[i].career!="undefined")
			{
				
				var career_info=json.basic_info[i].career.replaceAll("\n", "<br/>");
				$('#career_v').html(career_info);
			}
			if(json.basic_info[i].profile_photo!=null && json.basic_info[i].profile_photo!="" && json.basic_info[i].profile_photo!="null" && json.basic_info[i].profile_photo!="undefined")
			{
				
			document.getElementById("profile_pic_v").src="/PoliticianPhotoes"+json.basic_info[i].profile_photo;
				//document.getElementById("profile_pic_v").src="image"+json.basic_info[i].profile_photo;
			}
			if(json.basic_info[i].commity!=null && json.basic_info[i].commity!="" && json.basic_info[i].commity!="null" && json.basic_info[i].commity!="undefined")
			{
				$('#commity_v').html(json.basic_info[i].commity.replaceAll("\n", "<br/>"));
			}
		}
			
			$("#pol_category").empty();
			if(json.category.length>0)
				{
				$('#category').text("Categories");
				}
			
			for(var j=0;j<json.category.length;j++)
				{
				$("#pol_category").append(
					'<div class="row">'+
					'<div class="col-md-12">'+
					'<label>'+json.category[j].category_name+'</label>'+
					'</div>'+
					'</div>'
				);
				}
		},
		error:function(){
		}
	});
}
function deleteBlackList_website(web_id)
{	
	web_id=web_id.trim();
	if(web_id!='')
		{
		DELETE_WEB_ID=web_id;
		$("#delete_website").modal("show");
		}
	else{
		document.getElementById("blacklist").action="blacklist-website?delete_web_id="+DELETE_WEB_ID;
		document.getElementById("blacklist").method="POST";
		document.getElementById("blacklist").submit();
		DELETE_PARTY_ID=0;
	}
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
		if(website==null || website=="")
		{
		document.getElementById("blacklist_error").innerHTML = "Please enter website url";
		return false;
		}
		document.getElementById("blacklist").action="blacklist-website?edit_web_id="+WEB_ID+"&website_name="+website;
		document.getElementById("blacklist").method="POST";
		document.getElementById("blacklist").submit();
		WEB_ID=0;
	}
	
}
function editParty(party_name,party_id)
{
	party_name=party_name.trim();
	party_id=party_id.trim();
	
	if(party_name!='' && party_id!='')
		{
		PARTY_ID=party_id;
		$("#edit_party_name").modal("show");
		$("input[name='edited_party_name']").val(party_name);
		}
	else{
		var party=$("input[name='edited_party_name']").val();
		if(party==null || party=="")
			{
			document.getElementById("party_error").innerHTML = "Please enter party name";
			return false;
			}
		document.getElementById("manage-parties").action="manage-parties?edit_party_id="+PARTY_ID+"&party_name="+party;
		document.getElementById("manage-parties").method="POST";
		document.getElementById("manage-parties").submit();
		PARTY_ID=0;
	}
}
function deleteParty(party_id)
{
	party_id=party_id.trim();
	if(party_id!='')
		{
		DELETE_PARTY_ID=party_id;
		$("#delete_party").modal("show");
		}
	else{
		document.getElementById("manage-parties").action="manage-parties?delete_party_id="+DELETE_PARTY_ID;
		document.getElementById("manage-parties").method="POST";
		document.getElementById("manage-parties").submit();
		DELETE_PARTY_ID=0;
	}
}
function deleteCelebrity(celebrity_id)
{
	celebrity_id=celebrity_id.trim();
	if(celebrity_id!='')
		{
		DELETE_CELEBRITY_ID=celebrity_id;
		}
	else{
		document.getElementById("show_celeb").action="manage-politician?delete_celebrity_id="+DELETE_CELEBRITY_ID;
		document.getElementById("show_celeb").method="POST";
		document.getElementById("show_celeb").submit();
		DELETE_CELEBRITY_ID=0;
	}
}
function getSearchKeyId(search_key_id,search_key)
{
	search_key=search_key.trim();
	search_key_id=search_key_id.trim();

	if(search_key!='' && search_key_id!='')
		{
		DELETE_SEARCH_KEY_ID=search_key_id;
		SEARCH_KEY=search_key;
		$("input[name='celebrity_name']").val(search_key);
		
		}
	else{

		var celebrity_name=$("#celebrity_name").val();
		var party_id=$("#party_id").val();
		var state_id=$("#state_id").val();
		
		var category_name=[];
		if(celebrity_name==null || celebrity_name=="")
			{
			document.getElementById("validate-error-msg").innerHTML = "Please enter politician name";
			return false;
			}
		if(party_id==0)
		{
		document.getElementById("validate-error-msg").innerHTML = "Please select party name";
		return false;
		}
		if(state_id==0)
		{
		document.getElementById("validate-error-msg").innerHTML = "Please select state";
		return false;
		}
		
		$(".categoryName:checked").each(function(){
			category_name.push($(this).val());
		 });
	if ($("input[name='categroy_name']:checked").length == 0) {
		document.getElementById("validate-error-msg").innerHTML = "Please select category";
			return false;
		}
	
		$.ajax({
			type: "POST",
			url:'delete-added-search-key',
			data:'delete_search_key_id='+DELETE_SEARCH_KEY_ID,
			success:function(res){
			},
			error:function(){
			}
		}); 
	}
	
}
function EditPoliticianName(search_key_id,searchname)
{
	if(searchname!='' && search_key_id!='')
		{
		SEARCH_KEY_ID=search_key_id.trim();
		SEARCH_KEY=searchname.trim();
		$("input[name='search_key1']").val(searchname);
		}
	else{
		var search_name=$("input[name='search_key1']").val();
		
		if(search_name==null || search_name=="")
		{
		document.getElementById("errName").innerHTML = "Please enter search key";
		return false;
		}
		document.getElementById("add-search-key").action="search-keys?edit_search_key_id="+SEARCH_KEY_ID+"&search_key_name="+search_name;
		document.getElementById("add-search-key").method="POST";
		document.getElementById("add-search-key").submit();
		SEARCH_KEY_ID=0;
		SEARCH_KEY=null;
	}
}
function deletePoliticianInfo(search_key_id,search_key)
{
	search_key_id=search_key_id.trim();
	search_key=search_key.trim();

	if(search_key!='' && search_key_id!='')
		{
		SEARCH_KEY=search_key;
		SEARCH_KEY_ID=search_key_id;
		}
	else{
		document.getElementById("add-search-key").action="search-keys?delete_search_key_id="+SEARCH_KEY_ID+"&delete_search_key="+SEARCH_KEY;
		document.getElementById("add-search-key").method="POST";
		document.getElementById("add-search-key").submit();
		SEARCH_KEY_ID=0;
		SEARCH_KEY=null;
	}
}
function deleteBlog(delete_blog_id)
{
	if(delete_blog_id!='')
		{
		BLOG_ID=delete_blog_id.trim();
		}
	else{
        document.getElementById("deleteBlogs").action="manage-blogs?delete_blog_id="+BLOG_ID;
        document.getElementById("deleteBlogs").method="POST";
        document.getElementById("deleteBlogs").submit();
        BLOG_ID=0;
	}
}
function editBlog(blog_id)
{
	
	$.ajax({
		
		type: "POST",
		url:"edit-blogs",
		data:"edit_blog_id="+blog_id,
		success:function(res){
			var json = JSON.parse(res);
			$("#blog_title_e").val(json.blog_title);
			$("#EditBlogModal .Editor-editor").html(json.description);
			if(json.blog_image!=null && json.blog_image!="")
			{
			document.getElementById("blog_image").src="/PoliticianPhotoes"+json.blog_image;
			}
		},
		error:function(){
		}
	});

}
function validateParty()
{
	var party_name=$("#party_name").val();
	if(party_name==null || party_name=="")
		{
		$(".errorMsg").text("Please enter party name");
		$("#errorModal").modal('show');
		return false;
		}
}
function validateBlacklistWebsite()
{
	var website_name=$("#website_name").val();
	if(website_name==null || website_name=="")
		{
		$(".errorMsg").text("Please enter website url");
		$("#errorModal").modal('show');
		return false;
		}
}
function blogValidation()
{
	var blog_title=$("#blog_title").val();
	
	if(blog_title==null || blog_title=="")
	{
		
	document.getElementById("adderrName").innerHTML = "Please enter blog title";
	return false;
	}
	  var html_code_description= $(".Editor-editor").clone().html();
	    $("#description").val(html_code_description);
}

function editBlogValidation()
{
	/*var blog_title=$("#blog_title_e").val();
	if(blog_title==null || blog_title=="")
	{
	document.getElementById("edit-errName").innerHTML = "Please enter blog title";
	return false;
	}*/
	var blog_title=$("#blog_title_e").val();
    if(blog_title==null || blog_title=="")
    {
    document.getElementById("edit-errName").innerHTML = "Please enter blog title";
    return false;
    }
    var html_code_description= $("#EditBlogModal .Editor-editor").html();
    $("#description_e").val(html_code_description);
}

function validate_add_celebrity(){
	var celebrity_name=$("#celebrity_name").val();
	var party_id=$("#party_id").val();
	var state_id=$("#state_id").val();
	
	var category_name=[];
	if(celebrity_name==null || celebrity_name=="")
		{
		document.getElementById("validate-error-msg").innerHTML = "Please enter politician name";
		return false;
		}
	if(party_id==0)
	{
	document.getElementById("validate-error-msg").innerHTML = "Please select party name";
	return false;
	}
	if(state_id==0)
	{
	document.getElementById("validate-error-msg").innerHTML = "Please select state";
	return false;
	}
	
	$(".categoryName:checked").each(function(){
		category_name.push($(this).val());
	 });
if ($("input[name='categroy_name']:checked").length == 0) {
	document.getElementById("validate-error-msg").innerHTML = "Please select category";
		return false;
	}
}

function validate_edit_celebrity(){
	var celebrity_name=$("#celebrity_name_e").val();
	var party_id=$("#party_id_e").val();
	var state_id=$("#state_id_e").val();
	
	var category_name=[];
	if(celebrity_name==null || celebrity_name=="")
		{
		document.getElementById("validate-edit-error-msg").innerHTML = "Please enter politician name";
		return false;
		}
	if(party_id==0)
	{
	document.getElementById("validate-edit-error-msg").innerHTML = "Please select party name";
	return false;
	}
	if(state_id==0)
	{
	document.getElementById("validate-edit-error-msg").innerHTML = "Please select state";
	return false;
	}
	
	$(".categoryEditName:checked").each(function(){
		category_name.push($(this).val());
	 });
if ($(".categoryEditName:checked").length == 0) {
	document.getElementById("validate-edit-error-msg").innerHTML = "Please select category";
		return false;
	}
}

function addUploadPhoto() {
	if($('input[name="upload-image"]').is(":checked"))
		{
			$("#view_browse_img").show();
		}
	else
		{
			$("#view_browse_img").hide();
		}
	
//	$("#upload-photo-div").append(
//			' <div class="col-sm-4"> '+
//			' <label> Upload Profile Photo</label> '+
//	        ' </div> '+
//	        ' <div class="col-sm-8"> '+
//			' <input type="file" id="d_file_edit" name="d_file" class="d_file_edit_class"> '+
//	        ' </div> '
//	);
}

function changeLimit()
{
	var limit=$("select[name='limit']").val();
	document.forms[0].action="admin-home?limit="+limit;
	document.forms[0].method="post";
	document.forms[0].submit();
}
function searchCelebrity()
{
	if($("#search_celebrity_key").val()!="")
	{
		var search_celebrity_key=$("#search_celebrity_key").val().trim();
		document.forms[0].action="admin-home?search_celebrity_key="+search_celebrity_key;
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	/*else{
			$(".errorMsg").text("Please enter search key");
			$("#errorModal").modal('show');
			return false;
	}*/
}
function addTwitterAcctRow(){
	$('<div class="row form-group additionalTwitterAccounts"><div class="col-sm-4"></div><div class="col-sm-6"><input type="text" name="twitter_handle" class="form-control"></div><div class="col-sm-1"><button type="button" class="btn btn-follow" onclick="deleteTwitterAcctRow(this)">X</button></div></div>').insertBefore( ".addTwitterAcctRow" );
	}

function addTwitterAcctRowInAdd(){
		$('<div class="row form-group"><div class="col-sm-4"></div><div class="col-sm-6"><input type="text" name="twitter_handle" class="form-control"></div><div class="col-sm-1"><button type="button" class="btn btn-follow" onclick="deleteTwitterAcctRow(this)">X</button></div></div>').insertBefore( ".addTwitterAcctRowAdd" );
		}
		
function deleteTwitterAcctRow(ele){
		$(ele).parent().parent().remove();
		}

function changeLimitUserAccounts()
{
	var limit=$("select[name='limit']").val();
	document.forms[0].action="user-accounts?limit="+limit;
	document.forms[0].method="post";
	document.forms[0].submit();
}
