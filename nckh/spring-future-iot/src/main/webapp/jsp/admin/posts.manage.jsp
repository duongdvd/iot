<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tiles:insertDefinition name="user">
    <tiles:putAttribute name="body">
        <script type="text/javascript" src="/static/ckeditor/ckeditor.js"></script>
        <style>
            .table th, .table td {
                text-align: center;
                padding: 10px 10px;
            }
        </style>

        <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h3 class="card-title" style="text-align: center">Trình Soạn Thảo</h3>
                        <form:form cssClass="forms-sample" action="/admin/posts/save" method="post"
                                   modelAttribute="posts">
                            <form:input path="draftersId" type="hidden" value="${employee.id}"/>
                            <div class="row">
                                <div class="col-lg-10">
                                    <div class="form-group">
                                        <form:input path="title" type="text" cssClass="form-control" placeholder="Tiêu đề bài viết"/>
                                    </div>
                                </div>
                                <div class="col-lg-2">
                                    <div class="form-group">
                                        <form:select path="theme" cssClass="form-control form-control-sm" >
                                            <form:option value="">Chủ đề bài viết</form:option>
                                            <c:forEach items="${themes}" var="theme" varStatus="loop">
                                                <form:option value="${theme}">${theme}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Nội dung</label>
                                <form:textarea path="content" cssClass="form-control" rows="2"></form:textarea>
                            </div>
                            <button type="submit" class="btn btn-primary mr-2">Lưu</button>
                            <button type="reset" class="btn btn-secondary">Hủy</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            CKEDITOR
                .replace(
                    'content',
                    {
                        filebrowserBrowseUrl: '/admin/ckfinder/ckfinder.html',
                        filebrowserImageBrowseUrl: '/admin/ckfinder/ckfinder.html?type=Images',
                        filebrowserFlashBrowseUrl: '/admin/ckfinder/ckfinder.html?type=Flash',
                        filebrowserUploadUrl: '/admin/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
                        filebrowserImageUploadUrl: '/admin/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
                        filebrowserFlashUploadUrl: '/admin/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
                    });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>