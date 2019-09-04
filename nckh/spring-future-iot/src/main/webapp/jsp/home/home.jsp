<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<tiles:insertDefinition name="home">
    <tiles:putAttribute name="body">
        <style>
             .carousel-inner > .item > img{
                max-width: 150%;
            }
        </style>
        <div id="myCarousel" class="carousel slide" data-ride="carousel" >
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">

                <div class="item active">
                    <img src="/images/imBlynk.jpg" alt="New York" width="1680px" height="700px">
                    <div class="carousel-caption">
                        <h3>New York</h3>
                        <p>The atmosphere in New York is lorem ipsum.</p>
                    </div>
                </div>

                <div class="item">
                    <img src="/images/imBlynk.jpg" alt="Chicago" width="1680px" height="700px">
                    <div class="carousel-caption">
                        <h3>Chicago</h3>
                        <p>Thank you, Chicago - A night we won't forget.</p>
                    </div>
                </div>

                <div class="item">
                    <img src="/images/imBlynk.jpg" alt="Los Angeles" width="1680px" height="700px">
                    <div class="carousel-caption">
                        <h3>LA</h3>
                        <p>Even though the traffic was a mess, we had the best time playing at Venice Beach!</p>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>