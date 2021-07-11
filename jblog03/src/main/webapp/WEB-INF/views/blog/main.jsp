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
</head>
<body>
	<div id="container">
<c:import url="/WEB-INF/views/includes/admin/header.jsp"/>
		<div id="wrapper">
		
		<c:choose>
			<c:when test="${map.postVo != null }">
			<div id="content">
				<div class="blog-content">
					<h4>${map.postVo.title }</h4>
					<p style="text-align: right;">${map.postVo.reg_date }<p>
					<p>${map.postVo.contents }<p>
				</div>
				
				<ul class="blog-list">
					<c:forEach var="post" items="${map.postList }">
						<li><a href="${pageContext.request.contextPath}/${id }/${post.category_no}/${post.no }">${post.title }</a> <span>${post.reg_date }</span>	</li>
					</c:forEach>
				</ul>
				
			</div>
			</c:when>
			<c:otherwise>
				<div id="content">
					<p style="text-align:center; height: 100px; line-height: 100px;"> 작성된 글이 없습니다.</p>
				</div>
			</c:otherwise>
		</c:choose>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${map.blogVo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				
				<c:forEach var="category" items="${map.categoryList }">
					<c:choose>
						<c:when test="${map.postList[0].category_no eq category.no }">
							<li class="selected"><a href="${pageContext.request.contextPath}/${id }/${category.no }">${category.name }</a></li> 
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/${id }/${category.no }">${category.name } </a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
			</ul>
		</div>
		
<c:import url="/WEB-INF/views/includes/admin/footer.jsp"/>
	</div>
</body>
</html>