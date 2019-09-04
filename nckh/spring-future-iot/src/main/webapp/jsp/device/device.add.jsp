<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tiles:insertDefinition name="user">
    <tiles:putAttribute name="body">
        <script src="/js/sweetalert.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            function deleteDevice(btn, id) {
                swal("Hành động này sẽ xóa  toàn bộ dữ liệu thiết bị !", {
                    buttons: {
                        cancel: "Hủy bỏ",
                        defeat: "Đồng ý",
                    },
                }).then((value) => {
                    switch(value) {
                    case "defeat":
                        $.ajax({
                            type: 'DELETE',
                            url: '/api/device/delete/' + id,
                            data: {get_param: 'value'},
                            success: function () {
                                var row = btn.parentNode.parentNode;
                                row.parentNode.removeChild(row);
                            },
                            // error:
                        });
                    }
                }
            );}
        </script>
        <style>
            .table th, .table td {
                text-align: center;
                padding: 10px 10px;
            }
        </style>

        <div class="row">
            <div class="col-md-3 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title" align="center">Thêm Thiết Bị</h4>
                        <p style="color:red" align="center">${isExist}</p>
                        <form:form action="/device/save" modelAttribute="device" method="post">
                            <div class="form-group">
                                <label>Mac ID:<form:errors path="macAddress" cssStyle="color:red;"/></label>
                                <form:input type="text" path="macAddress" cssClass="form-control form-control-lg"
                                            placeholder="Địa chỉ mac"/>
                            </div>
                            <div class="form-group">
                                <label>GPIO:</label>
                                <form:select path="gpio" cssClass="form-control form-control-sm">
                                    <form:options items="${mapGpio}"/>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label>Mô Tả:<form:errors path="description" cssStyle="color:red;"/></label>
                                <form:input type="text" path="description" cssClass="form-control form-control-lg"
                                            placeholder="Vị trí đặt thiết bị"/>
                            </div>

                            <div class="form-group">
                                <label>Thể Loại:</label>
                                <form:select path="typeCode" cssClass="form-control form-control-sm">
                                    <form:options items="${optionTypeDevice}"/>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label>Hệ Mật:</label>
                                <select name="algorithm" class="form-control form-control-sm">
                                    <c:forEach items="${algorithms}" var="algorithm" varStatus="loop">
                                        <option value="${algorithm}">${algorithm}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-success mr-2">Thêm</button>
                        </form:form>
                    </div>
                </div>
            </div>

            <div class="col-md-9 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title" align="center">Danh Sách Thiết Bị</h4>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Mô Tả</th>
                                    <th>Thể Loại</th>
                                    <th>Key Secret - IV</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${lstDeviceAndKey}" var="deviceAndKey" varStatus="loop">
                                    <tr>
                                        <td><a href="/device/${deviceAndKey.typeCode}/${deviceAndKey.deviceId}">${deviceAndKey.description}</a></td>
                                        <td>${deviceAndKey.typeName}</td>
                                        <td>${deviceAndKey.keySecret} - ${deviceAndKey.initVector}</td>
                                        <td>
                                            <button onclick="deleteDevice(this, '${deviceAndKey.deviceId}')"
                                                    class="btn btn-danger">Xóa
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>