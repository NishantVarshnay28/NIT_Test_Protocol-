<%-- 
    Document   : adminProfile
    Created on : Jul 11, 2017, 7:37:19 PM
    Author     : Dell
--%>

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
				<li class="active"><a href="#">Evaluation Report<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-list"></span></a></li>
                                <li ><a href="<c:url value='/facultyViews/testPage1.jsp'></c:url>">Create Test<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-education"></span></a></li>
                                <li ><a href="<c:url value='/facultyViews/changePasswordFaculty.jsp'></c:url>">Change Password<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a></li>
				<li ><a href="<c:url value='/mainLogout'></c:url>">Logout<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-log-out"></span></a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="main" style="padding: 20px">
    
<h2>Evaluation Report</h2>   
    
<form  class="form-inline" action="<c:url value='/getTestCodeFaculty'></c:url>">
    
<select name="branch" class="input-lg">
    <option> Select Branch...</option>
    <option value="cse">Computer Science</option>
    <option value="mec">Mechanical</option>
    <option value="civ">Civil</option>
</select>

<select name="year" class="input-lg">
    <option> Select Batch...</option>
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
</select>
 <input type="submit" value="Get Test Code" class="input-lg">
</form>
<form class="form-inline" action="showResultFaculty">
    <select name="testCode" class="input-lg">
    <option> Select Test Code...</option>
<c:forEach items="${testCode}" var ="code">
    <option value="${code}">${code}</option>
</c:forEach>
</select>
<input type="submit" value="Filter" class="col-md-offset-1 input-lg">
</form>
 <c:if test="${record!=null}">
 <table class="table table-hover">
   <tr>
       <td>Maximum Marks::${record[0][0]}</td>
        <td>Date::${record[0][1]}</td>
        <td>Subject::${record[0][2]}</td>
    </tr>      
 </table>
<table class="table table-hover">
    <thead>
      <tr>
        <th>Roll Number</th>
        <th>Name</th>
        <th>Obtained Marks</th>
      </tr>
    </thead>
    <tbody>
 <c:forEach items="${record}" var ="code" begin="0" varStatus="status">
     <c:if test="${status.index !=0}">
     <tr>
     <c:forEach items="${code}" var="detail">
     <td> ${detail}</td>
     </c:forEach>
     </tr>
     </c:if>
 </c:forEach>
 </tbody>
 </table>
 </c:if>
</div>
    
     <script src="<c:url value='/static/js/jquery-3.2.1.min.js'></c:url>"></script>
     <script src="<c:url value='/bootstrap/js/bootstrap.js'></c:url>"></script>
    <script src="<c:url value='/static/js/adminJs.js'></c:url>"></script>
    </body>
</html>
