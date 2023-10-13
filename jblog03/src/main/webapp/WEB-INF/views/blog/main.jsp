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
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
					<c:when test="${empty PostList }"><h3 style="color:gray">생성된 게시물이 존재하지 않습니다.</h3></c:when>

					<c:otherwise>
					<h4>${PostList[postIndex].postTitle }</h4>
					<p>
						${fn:replace(PostList[postIndex].postContents, newline, "<br>") }
					<p>

						</c:otherwise>
						</c:choose>
				</div>
				<ul class="blog-list">
					<c:forEach var="post" items="${PostList}">
						<li>
							<a href="${pageContext.request.contextPath}/${blogVo.blogId}/${blogVo.categoryNo}/${post.postNo}" >${post.postTitle}</a>
							<span style="float: right;">
							<fmt:parseDate value="${post.postRegDate}" var="parsedDate" pattern="yyyy-MM-dd" />
							<fmt:formatDate value="${parsedDate}" pattern="yyyy/MM/dd" />
							</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<%--이미지--%>
		<div id="extra">
			<div class="blog-logo">
<%--				<img src="${pageContext.request.contextPath}/assets/images/zzalang2.jpg" style="width: 100%; margin-top: 20px;">--%>
					<img src="${pageContext.request.contextPath}${blogVo.blogImage }" style="width: 100%; margin-top: 20px;">

			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="category" items="${categoryList}">
					<li>
						<a href="${pageContext.request.contextPath}/${blogVo.blogId}/${category.categoryNo}">
								${category.categoryName}</a>
					</li>
				</c:forEach>
			</ul>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>