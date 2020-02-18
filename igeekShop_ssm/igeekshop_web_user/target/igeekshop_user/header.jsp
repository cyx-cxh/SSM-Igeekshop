<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
 <script type="text/javascript">
 $(function () {

 	// alert("aaa")
	 var content="";
	 $.ajax({
		 url:"${pageContext.request.contextPath }/categoryList",
		 type:"get",
		 dataType:"json",
		 success:function (data) {
			 for (var i = 0; i < data.length; i++) {
				 content+="<li><a href='${pageContext.request.contextPath}/productList?cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";

			 }
			 $("#categoryUl").html(content);
		 }
	 });

});
 </script>
<!DOCTYPE html>

<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
		<c:if test="${empty loginUser }">
		<li><a href="login.jsp">登录</a></li>
			<li><a href="register.jsp">注册</a></li>
		</c:if>
			<c:if test="${not empty loginUser }">
		<li><a href="javascript:;">${loginUser.username }</a></li>
			<li><a href="${pageContext.request.contextPath }/UserloginOut">注销</a></li>
		</c:if>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/myorders">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath }/showproduct">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categoryUl">
					
				</ul>
				<form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath }/productList">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search" name="pname" >
					</div>
					<button type="submit" class="btn btn-default">查询</button>
				</form>
			</div>
		</div>
	</nav>
</div>