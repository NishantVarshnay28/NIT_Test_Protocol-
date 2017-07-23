<%-- 
    Document   : header
    Created on : Jul 14, 2017, 1:38:49 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <c:url var="logo" value="/images/nitLogo.jpg"></c:url>
        <style>
            .head
            {
                background-color: #222;
                padding: 10px;
                color: #CCC;
            }
            h2 
            {
                 text-align: center;
                 text-decoration: underline;
                 padding-bottom:  10px;
            }
        </style>
    </head>
    <body>
         <div class="container-fluid head">
            <div class="col-sm-2">
            <img src="${logo}">
            </div>
            <div class="col-sm-10">
                <h1 class= "hidden-xs">National Institute Of Technology,Kurukshetra Test Protocol</h1>
            </div>
        </div>
    </body>
</html>
