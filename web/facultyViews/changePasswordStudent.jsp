<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="models.Admin" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Evaluation Report</title>
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
                                <li ><a href="<c:url value='/facultyViews/editProfile.jsp'></c:url>">Edit Profile<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-edit"></span></a></li>
                                <li class="active" ><a href="#">Change Password<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a></li>
				<li><a href="<c:url value='/facultyViews/notification.jsp'></c:url>">Notification<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-education"></span></a></li>
                                <li ><a href="<c:url value='/mainLogout'></c:url>">Logout<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-log-out"></span></a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="main" style="padding: 20px">
<div style="color: red">${nme}</div>
<div style="color: red">${success}</div>
<h2>Change Password</h2>
<form class="form-inline" action="<c:url value='/changeStudent'></c:url>" method="post">
<table style="width:50%">
<tr>
<td>Old Password</td>
<td><input type="password" name="old" class="form-control"></td>
</tr>
<tr>
<td>Password::</td>
<td><input type="password" name="password" class="form-control"></td>
</tr>
<tr>
<td>Confirm Password::</td>
<td><input type="password" name="cpassword" class="form-control"></td>
</tr>
</table>
<input type="submit" value="Submit">
</form>

</div>
    
     <script src="<c:url value='/static/js/jquery-3.2.1.min.js'></c:url>"></script>
     <script src="<c:url value='/bootstrap/js/bootstrap.js'></c:url>"></script>
    <script src="<c:url value='/static/js/adminJs.js'></c:url>"></script>
    </body>
</html>
