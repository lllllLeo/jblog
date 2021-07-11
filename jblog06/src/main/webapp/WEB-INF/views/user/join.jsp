<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function(){
	btn = $('#btn-check');
	btn.click(function(){
		var id = $("#id").val();
		if(id == ""){
			return;
		}
		$.ajax({
			url: "/jblog03/user/api/checkid?id=" + id,
			type: "get",
			dataType: "json",
			error: function(xhr, status, e){
				console.error(status, e);
			},
			success: function(response){
				console.log(response);
				
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				
				if(response.data){
					alert("존재하는 아이디입니다. 다른 아이디를 사용하세요.");
					$("#id").val("");
					$("#id").focus();
					return;
				}
				
				$("#btn-check").hide();
				$("#img-check").show();
			}
		});
	});
});
</script>
</head>
<body>
	<div class="center-content">
<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<form class="join-form" id="join-form" method="post" action="">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<form:input path="email"/>
			<input type="button" id="btn-check" value="중복체크">
			<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="width:18px; vertical-align: bottom; display: none"/>
			<p style="color:#f00; text-align:left; padding-left:0">
				<form:errors path="email" />
			</p> 
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
