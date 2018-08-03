<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
  <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-119766288-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-119766288-1');
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RSBS - Bills</title>
</head>
<body style="background-color: #f2f2f2;">
<%@ include file="/WEB-INF/jsp/insideHeader.jsp"%>
<div id="my_page_container" class="container main-container inside-page-padding min-page-height" style="width:100%;">
	<div class="row">
		<div class="col-md-10 offset-md-1">
		<div class="row form-group title-bar" style="margin-right: 0px;margin-left: 0px;">
			<div class="col-md-6" align="left"><span class="bills_title">Most-Viewed Bills</span></div>
			<c:forEach var="b" items="${bills}">
				<c:set var="bill_date" value="${b.getBill_date()}"></c:set>
			</c:forEach>
			<div class="col-md-6" align="right">
					<span class="bills_date"><fmt:formatDate type = "date" value = "${bill_date}" pattern="d MMMM y" /></span>
			</div>
		</div>
		<c:set var="my_index" value="0"></c:set>
			<div class="table-responsive">
								<table class="table table-bordered table-background"> 
									<tbody>
										<c:forEach var="b" items="${bills}" varStatus="st">
											<c:set var="my_index" value="${st.index+1}"></c:set>
											<tr>
												<td> ${my_index}. </td>
												<td> <a href="${b.getBill_url()}" target="_blank">${b.getBill_number()}</a> </td>
												<td> ${b.getBill_title()} </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		var max_height=$("#my_header").height()+$("#my_footer").height();
		$("#my_page_container").css("min-height",($(window).height()-max_height)+150);
		$("#bills").addClass("active-nav-link");
	});
</script>
</body>
</html>