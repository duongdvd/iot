<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	window.onscroll = function() {scrollFunction()};
	function scrollFunction() {
		if (document.body.scrollTop > 10 || document.documentElement.scrollTop > 10) {
			document.getElementById("navbar").style.backgroundColor = "#ffffffff";
		} else {
			document.getElementById("navbar").style.backgroundColor = "#ffffffd6";
		}
	}
</script>
<nav class="navbar navbar-default navbar-fixed-top" id = "navbar" style="background-color: #ffffffd6;">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar" style="background-color: #828282;"></span> <span
					class="icon-bar" style="background-color: #828282;"></span> <span
					class="icon-bar" style="background-color: #828282;"></span>
			</button>
			<a class="navbar-brand brand-logo" style="color: #f40 !important;" href="/">
				FUIOT
			</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="/">Trang Chủ</a></li>
				<li><a href="/">Giới Thiệu</a></li>
				<li><a href="/">Hướng Dẫn</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!hasAnyRole('ROLE_USER')">
					<li>
                        <button onclick="window.location.href = '/login';" class="btn  btn-rounded btn-fw">ĐĂNG NHẬP</button>
                    </li>
					<li>
                        <button onclick="window.location.href = '/register';" class="btn  btn-rounded btn-fw">ĐĂNG KÝ</button>
                    </li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_USER')">
					<li>
                        <button onclick="window.location.href = '/device/add';" class="btn  btn-rounded btn-fw">QUẢN LÝ</button>
					</li>
				</sec:authorize>
				<li>
					<form action="/logout" id="logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}">
					</form>
				</li>
			</ul>
		</div>
	</div>
</nav>



