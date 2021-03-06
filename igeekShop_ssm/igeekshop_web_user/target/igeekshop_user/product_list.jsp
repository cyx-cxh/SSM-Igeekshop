<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
	width: 100%;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<body>


	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>


	<div class="row" style="width: 1210px; margin: 0 auto;">
		<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath }/showproduct">首页</a>

				</li>
				<li><a href="#">${category.cname}</a>

				</li>
			</ol>
		</div>

		<c:forEach items="${products }" var="pro">
		<div class="col-md-2" style="text-align: center;height: 200px;margin: 20px;">
			<a href="${pageContext.request.contextPath }/productinfo?pid=${pro.pid }&cid=${pro.cid}">
				<img src="${pageContext.request.contextPath }/${pro.pimage }"
				width="170" height="170" style="display: inline-block;">
			</a>
			<p>
			<a href="${pageContext.request.contextPath }/productinfo?pid=${pro.pid}&cid=${pro.cid}" style='color: green'>${pro.pname }</a>
			</p>
			<p>
				<font color="#FF0000">商城价：&yen;${pro.shopPrice }</font>
			</p>
		</div>
		</c:forEach>
		<div>
	<!--分页 -->
<%--	<nav aria-label="Page navigation">--%>
<%--			<ul class="pagination">--%>
<%--			<c:if test="${pd.currentPage!=1 }">--%>
<%--			<li >--%>
<%--			--%>
<%--				<a --%>
<%--			href="${pageContext.request.contextPath}/product?method=productList&currentPage=${pd.currentPage - 1}&rows=15--%>
<%--				&pname=${condition.pname[0]}&cid=${cid }"--%>
<%--				aria-label="Previous"> --%>
<%--				<span aria-hidden="true">&laquo;</span>--%>
<%--				</a></li>--%>
<%--			</c:if>--%>
<%--			--%>
<%--			<c:forEach items="${pd.pages }" var="page">--%>
<%--			<c:if test="${pd.currentPage==page }">--%>
<%--					<li class="active">--%>
<%-- 			<a 	href="${pageContext.request.contextPath}/product?method=productList&currentPage=${page }&rows=15&pname=${condition.pname[0]}&cid=${cid }--%>
<%-- 			"--%>
<%--						>${page }</a>--%>
<%--					</li>--%>
<%--			</c:if>--%>
<%--   					<c:if test="${pd.currentPage!=page }">--%>
<%--					<li >--%>
<%--			<a 	href="${pageContext.request.contextPath}/product?method=productList&currentPage=${page}&rows=15&pname=${condition.pname[0]}&cid=${cid }--%>
<%--			--%>
<%--						">${page }</a>--%>
<%--					</li>--%>
<%--			</c:if>--%>
<%--			--%>
<%--			</c:forEach>--%>
<%--			<c:if test="${pd.currentPage!=pd.totalPage }">--%>
<%--			<li><a 	href="${pageContext.request.contextPath}/product?method=productList&currentPage=${pd.currentPage + 1}&rows=15--%>
<%--			&pname=${condition.pname[0]}&cid=${cid }--%>
<%--						" aria-label="Next"> <span aria-hidden="true">&raquo;</span>--%>
<%--				</a></li>--%>
<%--			</c:if>--%>
<%--				--%>
<%--			</ul>--%>
<%--		</nav>--%>
		</div>
	</div>
	<!-- 分页结束 -->

	<!--商品浏览记录-->
	<div
		style="width: 1210px; margin: auto; padding: 0 9px; border: 1px solid #ddd; border-top: 2px solid #999; height: 246px;">

		<h4 style="width: 50%; float: left; font: 14px/30px 微软雅黑">浏览记录</h4>
		<div style="width: 50%; float: right; text-align: right;">
			<a href="">more</a>
		</div>
		<div style="clear: both;"></div>

		<div style="overflow: hidden;">

			<ul style="list-style: none;">
			<c:forEach items="${historyList }" var="history">
			<li
					style="width: 150px; height: 216px; float: left; margin: 0 8px 0 0; padding: 0 18px 15px; text-align: center;"><img
					src="${pageContext.request.contextPath }/${history.pimage}" width="130px" height="130px" /></li>
			</c:forEach>
				
			</ul>

		</div>
	</div>


	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>