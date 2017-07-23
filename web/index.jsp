<%-- 
    Document   : index
    Created on : Jul 17, 2017, 4:30:00 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
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
        <title>NIT KKR TEST PROTOCOL</title>
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
        <div class="container-fluid">
        <div class="col-sm-8 carousel hidden-xs" id="myCarousel" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
          <img src="<c:url value='/images/nit1.jpg'></c:url>" style="width: 100%; height: 550px;">
      </div>

      <div class="item">
        <img src="<c:url value='/images/nit2.jpg'></c:url>"   style="width: 100%; height: 550px;">
      </div>
    
      <div class="item">
        <img src="<c:url value='/images/nit3.jpg'></c:url>"  style="width: 100%; height: 550px;">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
        </div>
      <div class="col-sm-4">
      <h2> Login</h2>
      <img src="<c:url value='/images/login.png'></c:url>"  style=" width:200px;height:200px;">
        <div style="color:red"><% if(request.getParameter("Loginmsg")!=null)
         out.println("Login First!!"); %></div>
        <div style="color:red">${msg}</div>
        <form action="login" class="form-inline" style="padding:80px" method="post">
            <a href="studentSignIn.jsp">Sign in for Student</a>
          <table style="width: 50%">
            <tr>
             <td><input type="radio" name="role" value="student">Student</td>
             <td><input type="radio" name="role" value="faculty">Faculty</td>
             </tr>
              <tr>
                  <td>Id::</td>
                  <td><input type="text" name="id"></td>
              </tr>
              <tr>
                  <td>Password::</td>
                  <td><input type="password" name="password"></td>
              </tr>
          </table>
          <input type="submit" value="Login">
      </form>
  </div>
</div>
     </body>
     <script src="<c:url value='/static/js/jquery-3.2.1.min.js'></c:url>"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.js'></c:url>"></script>
    <script src="<c:url value='/static/js/adminJs.js'></c:url>"></script>
</html>