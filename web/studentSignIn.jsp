<%-- 
    Document   : studentSignIn
    Created on : Jul 17, 2017, 5:23:45 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" rel="stylesheet">
        <link href="<c:url value='/static/css/myStyle.css'></c:url>" rel="stylesheet">
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
        <title>Student Sign In</title>
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
    <h2>Student Sign In!</h2>
<div style="color:red">${msg}</div>
<form method="post" action="addStudent">
<table>
<tr>
<td>Name::</td>
<td><input type="text" name="name"></td>
</tr>
<tr>
<td>Roll Number::</td>
<td><input type="text" name="roll"></td>
</tr>
<tr>
<td>Branch::</td>
<td><select name="branch">
<option value="cse">Computer Science</option>
<option value="it">Information Technology</option>
<option value="ece">Electronics</option>
<option value="eee">Electrical</option>
<option value="mec">Mechanical</option>
<option value="civ">Civil</option>
<option value="pie">Production</option>
</select>
</td>
</tr>
<tr>
<td>Batch::</td>
<td><input type="number" name="batch" min="2014" max="2050" step="1" value="2014"></td>
</tr>
<tr>
<td>Email Id::</td>
<td><input type="email" name="emailId"></td>
</tr>
<tr>
<td>Password::</td>
<td><input type="password" name="password"></td>
</tr>
<tr>
<td>Confirm Password::</td>
<td><input type="password" name="cpassword"></td>
</tr>
</table>
<input type="submit" value="Submit">
</form>
    </body>
    <script src="<c:url value='/static/js/jquery-3.2.1.min.js'></c:url>"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.js'></c:url>"></script>
    <script src="<c:url value='/static/js/adminJs.js'></c:url>"></script>
</html>
