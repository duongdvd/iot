<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tiles:insertDefinition name="user">
    <tiles:putAttribute name="body">
        <script src="/js/sockjs-0.3.4.min.js"></script>
        <script src="/js/stomp.js"></script>
            <script type="text/javascript">
                function connectDeviceWs() {
                    var socket = new SockJS('/ws');
                    stompClient = Stomp.over(socket);
                    console.log('Connected device socket....');
                }
            function connectCbnd(empId, username) {
                stompClient.connect({}, function (frame) {
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/cbnd/'+ empId + username, function (sensor) {
                        var sensor = JSON.parse(sensor.body);
                        var id = sensor.deviceId;
                        document.getElementById('cbnd-value').innerHTML = sensor.temperatureValue + "°C" + " - " + sensor.humidityValue + "%";
                        document.getElementById('cbnd-value-table-' + id).innerHTML = sensor.temperatureValue + "°C" + " - " + sensor.humidityValue + "%";
                        document.getElementById('cbnd-update-table-' + id).innerHTML = sensor.updateTime;
                        document.getElementById('cbnd-status').innerHTML = "Tình Trạng: Đã hoạt động.";
                    });
                });
               
            }

            function getTemperatureById(deviceId) {
                $.ajax({
                    type: 'GET',
                    url: '/api/cbnd/' + deviceId,
                    data: { get_param: 'value' },
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    success: function (sensor) {
                        document.getElementById('cbnd-value').innerHTML = sensor.temperatureValue + "°C" + " - " + sensor.humidityValue + "%";
                        document.getElementById('cbnd-description').innerHTML = "Mô Tả: " + sensor.location;
                        document.getElementById('cbnd-status').innerHTML = "Tình Trạng: " + sensor.status;
                    }
                });

            }
        </script>


        <div class="row">
            <div class="col-lg-5 grid-margin stretch-card">
                <!--weather card-->
                <div class="card card-weather">
                    <div class="card-body">
                        <div class="weather-date-location">
                            <h3>${dayOfWeek}</h3>
                            <p class="text-gray">
                                <span class="weather-date">${dMY}</span>
                                <span class="weather-location">VN</span>
                            </p>
                        </div>
                        <div class="weather-data d-flex">
                            <div class="mr-auto">
                                <h4 class="display-3" id="cbnd-value">
                                        ${sensor.temperatureValue}&deg;C - ${sensor.humidityValue}%
                                </h4>
                                <p id="cbnd-status">Tình Trạng: Ngoại tuyến</p>
                                <p id="cbnd-description">Mô Tả: ${sensor.description}</p>
                            </div>
                        </div>
                    </div>
                    <div class="card-body p-0">
                        <div class="d-flex weakly-weather">
                            <div class="weakly-weather-item">
                                <p class="mb-0">
                                    Sun
                                </p>
                                <i class="mdi mdi-weather-cloudy"></i>
                                <p class="mb-0">
                                    30°
                                </p>
                            </div>
                            <div class="weakly-weather-item">
                                <p class="mb-1">
                                    Mon
                                </p>
                                <i class="mdi mdi-weather-hail"></i>
                                <p class="mb-0">
                                    31°
                                </p>
                            </div>
                            <div class="weakly-weather-item">
                                <p class="mb-1">
                                    Tue
                                </p>
                                <i class="mdi mdi-weather-partlycloudy"></i>
                                <p class="mb-0">
                                    28°
                                </p>
                            </div>
                            <div class="weakly-weather-item">
                                <p class="mb-1">
                                    Wed
                                </p>
                                <i class="mdi mdi-weather-pouring"></i>
                                <p class="mb-0">
                                    30°
                                </p>
                            </div>
                            <div class="weakly-weather-item">
                                <p class="mb-1">
                                    Thu
                                </p>
                                <i class="mdi mdi-weather-pouring"></i>
                                <p class="mb-0">
                                    29°
                                </p>
                            </div>
                            <div class="weakly-weather-item">
                                <p class="mb-1">
                                    Fri
                                </p>
                                <i class="mdi mdi-weather-snowy-rainy"></i>
                                <p class="mb-0">
                                    31°
                                </p>
                            </div>
                            <div class="weakly-weather-item">
                                <p class="mb-1">
                                    Sat
                                </p>
                                <i class="mdi mdi-weather-snowy"></i>
                                <p class="mb-0">
                                    32°
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <!--weather card ends-->
            </div>
            <div class="col-lg-7 grid-margin stretch-card" style="text-align: center">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title" align="center">Danh Sách Cảm Biến Nhiệt Độ</h4>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Mô Tả</th>
                                    <th>Nhiệt Độ - Độ Ẩm</th>
                                    <th>Cập nhập</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sensors}" var="sensor" varStatus="loop">
                                    <tr>
                                        <td><a href="#" onclick="getTemperatureById('${sensor.deviceId}')" >${sensor.description}</a></td>
                                        <td id="cbnd-value-table-${sensor.deviceId}">${sensor.temperatureValue}°C - ${sensor.humidityValue}%</td>
                                        <td id="cbnd-update-table-${sensor.deviceId}">${sensor.updateTime}</td>
                                    </tr>
                                </c:forEach>
                                <script>
                                    connectDeviceWs();
                                    connectCbnd('${employee.id}', '${employee.username}');
                                </script>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>