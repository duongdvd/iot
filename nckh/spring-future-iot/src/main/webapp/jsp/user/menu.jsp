<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">

        <li class="nav-item nav-profile">
            <div class="nav-link">
                <button onclick="window.location.href = '/device/add';" class="btn btn-primary btn-block" href="/device/add">
                    Thêm Thiết Bị
                   <i class="mdi mdi-plus"></i>
                </button>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#user" aria-expanded="false" aria-controls="user">
                <i class="menu-icon fa fa-user-o"></i>
                <span class="menu-title">Xin chào, ${employee.fullname}</span>
                <i class="menu-arrow"></i>
            </a>

            <div class="collapse" id="user">
                <ul class="nav flex-column sub-menu">

                    <li class="nav-item">
                        <a class="nav-link" href="/acount/changePassWd"> Quản Lý Tài Khoản </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href=""> Đổi Mật Khẩu </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/logout"> Đăng Xuất </a>
                    </li>
                </ul>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/device/add">
                <i class="menu-icon fa fa-gear"></i>
                <span class="menu-title">Quản Lý Thiết Bị</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#sensor" aria-expanded="false" aria-controls="sensor">
                <i class="menu-icon fa fa-microchip"></i>
                <span class="menu-title">Thiết Bị Cảm Biến</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="sensor">
                <ul class="nav flex-column sub-menu" style="padding-left: 3rem">
                    <li class="nav-item">
                        <a class="nav-link" href="/device/cbnd">
                            <i class="menu-icon fa fa-thermometer-full" style="margin-right:0px;"></i>
                            <span class="menu-title">Cảm Biến Nhiệt Độ</span>
                        </a>
                    </li>
                    <li class="nav-item">

                    </li>
                </ul>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#reley-control" aria-expanded="false" aria-controls="reley-control">
                <i class="menu-icon fa fa-adjust"></i>
                <span class="menu-title">Thiết Bị Bật Tắt</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="reley-control">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item">
                        <a class="nav-link" href="/device/reley/list"> Bảng Chung </a>
                    </li>
                </ul>
            </div>
        </li>

        <sec:authorize access="hasRole('ROLE_POSTER')">
        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#auth" aria-expanded="false" aria-controls="auth">
                <i class="menu-icon fa fa-edit"></i>
                <span class="menu-title">Quản Lý Bài Viết</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="auth">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/posts"> Soạn Thảo </a>
                    </li>
                </ul>
            </div>
        </li>
        </sec:authorize>

    </ul>
</nav>