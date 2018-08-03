<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <!-- <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css"> -->
   
<title>RSBS - Footer</title>
<style>
h6{
    font-family: century schoolbook;
    font-style: italic;
    color:white;
}
.button{
font-family: century schoolbook;
background-color: #2087ed;
color:white; 
border: 1px solid #0b70d4;
padding:5px;
padding-left: 15px;
    padding-right: 15px;
 border-radius:20px;
 position:relative;
 }
 .icons{
 height:35px;
 width:35px;
 }
 .fb{
 margin-right:5px;
 }
 .insta{
 margin-left:5px;
 }
 .subscribe{
 margin-top:25px;
 }
 .footer-logo-style{
  padding-left:165px;
 }
 @media(max-width:991px)
 {
 .footer-logo-style{
  padding-left:90px;
 }
 }
</style>
</head>
<body>
<div id="my_footer" class="container main-container"  style="background-color:black; padding:25px;">
<div class="row">
           <!-- <div class="col-md-8">
	          <div class="row">
	               <div class="col-md-7" style="padding-left:50px; padding-right:10px">	
	           	     <h6>Subscribe to get daily updates</h6>	
					 <input class="form-control" type="text" placeholder="Enter your email address" >
				   </div>
				   <div class="col-md-5 subscribe" > 
					 <input type="button" class="button" value="Subscribe">
				   </div> 
		     </div>
		 </div> --> 
 			
		   <div class="col-md-4"> 
			   <div class="row">
			   		<div class="col-md-12">
			   			<h6 align="center" >Follow us</h6>
			   		</div>
			   </div>
			   <div class="row">
			   		<div class="col-md-12 form-inline footer-logo-style">                
	              <a href="https://www.facebook.com/RedSaidBlueSaid" target="_blank"> <img src="resources/assets/images/fb.png" class="icons fb" alt="FB Icon"></a>
			      <a href="https://twitter.com/RSaidBSaid" target="_blank"> <img src="resources/assets/images/twitter.png" class="icons" alt="Twitter Icon"></a>
			      <a href="https://www.instagram.com/redsaidbluesaid" target="_blank"> <img src="resources/assets/images/instagram.png" class="icons insta" alt="instagram Icon"></a>
	           </div>
			   </div>
           </div>
      
       <div class="col-md-8 footerCopyrightDiv"> 
       <span class="footerCopyrightText">© 2018 Red Said Blue Said</span>
       <!-- <a href="privacy-policy" class="privacyPolicyDiv">Privacy Policy</a> -->
       <a href="resources/assets/pdfs/RSBS-privacy_policy.pdf" class="privacyPolicyDiv" target="_blank"><span class="footerCopyrightText">Privacy Policy</span></a>
       <a href="resources/assets/pdfs/RSBS-Terms_of_Use.pdf" class="privacyPolicyDiv" target="_blank"><span class="footerCopyrightText">Terms of Use</span></a>
       <a href="resources/assets/pdfs/DMCA.pdf" class="privacyPolicyDiv" target="_blank">DMCA</a>
       </div>
      
	</div>
	</div>
</body>
</html>