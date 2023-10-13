<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="${pageContext.request.contextPath}" class="logo-link">
  <h1 class="logo"></h1>
</a>
<ul class="menu">
  <c:choose>
    <c:when test="${empty authUser}">
      <!-- 비로그인 상태일 때 표시할 내용 -->
      <li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
      <li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
    </c:when>
    <c:otherwise>
      <!-- 로그인 상태일 때 표시할 내용 -->
      <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
      <li><a href="${pageContext.request.contextPath}/${authUser.id}">내블로그</a></li>
    </c:otherwise>
  </c:choose>

</ul>
