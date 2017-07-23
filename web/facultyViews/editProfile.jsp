<%-- 
    Document   : editProfile
    Created on : Jul 21, 2017, 12:08:28 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <link href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" rel="stylesheet">
        <link href="<c:url value='/static/css/myStyle.css'></c:url>" rel="stylesheet">
        <c:url var="admin" value="/images/admin.png"></c:url>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <nav class="navbar navbar-inverse sidebar" role="navigation">
         <div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button> 
                    <a class="navbar-brand hidden-lg" href="#" style="padding:10px">NIT KKR Test Protocol</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
                    
			<ul class="nav navbar-nav">
                            <li class="divider"></li>
                            <li class= "hidden-xs" style="padding:10px"0><img class="img-circle" src="${admin}" width="150" height="150" ></li>
                            <li style="text-align:center"><a href="#">Hello ${sessionScope.user.name}</a></li> 
                            <li class="divider"></li>
				<li><a href="<c:url value='/facultyViews/StudentProfile.jsp'></c:url>">Evaluation Report<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-list"></span></a></li>
                                <li class="active"><a href="#">Edit Profile<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-edit"></span></a></li>
                                <li ><a href="<c:url value='/facultyViews/changePasswordStudent.jsp'></c:url>">Change Password<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a></li>
				<li><a href="<c:url value='/facultyViews/notification.jsp'></c:url>">Notification<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-education"></span></a></li>
                                <li ><a href="<c:url value='/mainLogout'></c:url>">Logout<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-log-out"></span></a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="main" style="padding: 20px">
    <h2>Edit Profile</h2>
    <form class="form-inline" action="/SMS/update">
<div style="color: red">${error}</div>
<div style="color: green">${success}</div>
<table style="width: 50%">
<tr>
<td>Name::</td>
<td><input type="text" name="name" value="${sessionScope.user.name}"></td>
</tr>
<tr>
<td>Roll Number::</td>
<td><input type="text" name="roll" value="${sessionScope.user.roll}"></td>
</tr>
<tr>
<td>Branch::</td>
<td><select name="branch">
<option  value="${sessionScope.user.branch}">${sessionScope.user.branch}</option>
<option value="cse">Computer Science</option>
<option value="it">Information Technology</option>
<option value="ec">Electronics</option>
<option value="eee">Electrical</option>
<option value="mec">Mechanical</option>
<option value="civ">Civil</option>
<option value="pie">Production</option>
</select>
</td>
</tr>
<tr>
<td>Batch::</td>
<td><input type="number" name="batch" min="2014" max="2050" step="1" value="${sessionScope.user.batch}"></td>
</tr>
<tr>
<td>Email Id::</td>
<td><input type="email" name="emailId" value="${sessionScope.user.email}"></td>
</tr>
<tr>
</table>
<input type="submit" value="UPDATE" class="col-md-offset-1" >
        </table>
    </form>
 </div>
    </body>
     <script src="<c:url value='/static/js/jquery-3.2.1.min.js'></c:url>"></script>
     <script src="<c:url value='/bootstrap/js/bootstrap.js'></c:url>"></script>
    <script src="<c:url value='/static/js/adminJs.js'></c:url>"></script>
</html>
