<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tiles:insertDefinition name="user">
    <tiles:putAttribute name="body">
        <script src="/js/sweetalert.min.js"></script>
        <script src="/js/jquery.min.js" ></script>
        <script src="/js/stomp.js"></script>
        <script src="/js/sockjs-0.3.4.min.js"></script>
        <script>
            function checkedDeviceReley(id){
                var value = $('#device-reley-' +  id).val();
                $('#device-reley-' +  id).prop('checked', value == 1);
            }

            function connectDeviceWs() {
                var socket = new SockJS('/ws');
                stompClient = Stomp.over(socket);
                console.log('Connected device socket ....');
            }

            function controlReley(id, macAddress, gpio){

                var value = $('#device-reley-' +  id).val();
                if(value == '0'){
                    $('#device-reley-' +  id).val(1);
                }else{
                    $('#device-reley-' +  id).val(0);
                }
                value = $('#device-reley-' +  id).val();
                stompClient.send("/app/device/reley/control/" + macAddress + "-" + gpio, {}, JSON.stringify({'deviceId': id, 'value': value}));
            }
        </script>
        <style>
            .table th, .table td {
                text-align: center;
                padding: 10px 10px;
            }
            .switch {
                 position: relative;
                 display: inline-block;
                 width: 50px;
                 height: 25px;
             }


            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 20px;
                width: 20px;
                left: 5px;
                bottom: 3px;
                background-color: white;
                -webkit-transition: .4s;
                transition: .4s;
            }

            input:checked + .slider {
                background-color: #2196F3;
            }


            input:checked + .slider:before {
                -webkit-transform: translateX(20px);
                -ms-transform: translateX(20px);
                transform: translateX(20px);
            }

            /* Rounded sliders */
            .slider.round {
                border-radius: 34px;
            }

            .slider.round:before {
                border-radius: 50%;
            }
        </style>

        <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title" align="center">Danh Sách Thiết Bị</h4>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Trạng Thái</th>
                                    <th>Mô Tả</th>
                                    <th>Cập Nhập</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${lstReley}" var="reley" varStatus="loop">
                                    <tr>
                                        <td>
                                            <label class="switch" >
                                                <input id="device-reley-${reley.deviceId}" value="${reley.value}" type="checkbox" onclick="controlReley('${reley.deviceId}', '${reley.macAddress}', '${reley.gpio}');">
                                                <span class="slider round"></span>
                                                <script>checkedDeviceReley('${reley.deviceId}');</script>
                                            </label>
                                        </td>
                                        <td><a href="#">${reley.description}</a></td>
                                        <td>${reley.updateTime}</td>
                                    </tr>
                                </c:forEach>
                                <script>connectDeviceWs();</script>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>