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
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="${pageContext.request.contextPath }/assets/ejs/ejs.js" type="text/javascript"></script>
<script>
	$(function(){
		
		$("#category-form").submit(function(event){
			event.preventDefault();
			addCategory();
		});
		
		$(document).on("click", "#table-category td a", function(event){
			event.preventDefault();
			let no = $(this).data("no");
			deleteCategory(no);
			
		});
		
		fetch();
		
	}); 
	    
	var fetch = function(){
		$.ajax({
			url: "${pageContext.request.contextPath }/${id }/admin/category/api",
			dataType: "json",	
			type: "get", 		
			contentType: "application/json",	 
			success: function(response){
				var html = listEJS.render(response);
				$(".admin-cat").append(html);
			}
		});
	}   
	
	var addCategory = function(){
		vo = {}
		// $(".validateTips.error").hide();
		vo.name = $("#category-name").val();
		if(vo.name == ""){
			$(".validateTips.error.name").show();
			return;
		}
		vo.desc= $("#category-desc").val();
		if(vo.desc == ""){
			$(".validateTips.error.desc").show();
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath }/${id }/admin/category/api/add",
			dataType: "json",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(vo),
			success: function(response){
				var html = listItemEJS.render(response.data);
				$(".admin-cat tr").filter(":first-child").after(html);
				$(".validateTips.error.name").hide();
				$(".validateTips.error.desc").hide();
				$("#category-desc").val("");
				$("#category-name").val("");
			}
		});
	};
	
	var deleteCategory = function(no){
		$.ajax({
			url: "${pageContext.request.contextPath }/${id }/admin/category/api/delete/" + no,
			type: "post",
			success: function(response){
				$(".admin-cat tr[data-no=" + response.data + "]").remove();
			}
		})
	}
	
	var listEJS = new EJS({
		url: "${pageContext.request.contextPath }/assets/ejs/list-template.ejs"
	});
	var listItemEJS = new EJS({
		url: "${pageContext.request.contextPath }/assets/ejs/listitem-template.ejs"
	});
	    
	
</script>


</head>
<body>
	<div id="container">
<c:import url="/WEB-INF/views/includes/admin/header.jsp"/>

		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${id }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${id }/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					
					<!-- ejs -->

				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="category-form" action="${pageContext.request.contextPath}/${id }/admin/category" method="post">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" id="category-name" name="name"></td>
			      			<td><p class="validateTips error name" style="display: none"> 좀 똑바로좀 입력해줘 </p></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" id="category-desc" name="desc"></td>
			      			<td><p class="validateTips error desc" style="display: none"> 좀 똑바로좀 입력해줘 </p></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
	      		</form>	
			</div>
		</div>
<c:import url="/WEB-INF/views/includes/admin/footer.jsp"/>
	</div>
</body>
</html>