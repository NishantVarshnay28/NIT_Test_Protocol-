<%-- 
    Document   : testPage1
    Created on : Jul 19, 2017, 7:33:07 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Test</title>
        <link href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" rel="stylesheet">
        <link href="<c:url value='/static/css/myStyle.css'></c:url>" rel="stylesheet">
    </head>
    <body>
 <jsp:include page="header.jsp"></jsp:include>
 <h2>Enter Test Details</h2>
 <div class="container">
<form method="post" action="<c:url value='/getSubjects'></c:url>">  
<select name="branch"  class="input-lg">
<option>Select A Branch....</option>
<option value="cse">Computer Science</option>
<option value="it">Information Technology</option>
<option value="ec">Electronics</option>
<option value="eee">Electrical</option>
<option value="mechanical">Mechanical</option>
<option value="civil">Civil</option>
<option value="production">Production</option>
</select>
<select name="batch" class="input-lg">
    <option> Select Batch...</option>
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
</select>
<input type="submit" value="Get Subjects" class="col-md-offset-1 input-lg ">
 </form>
<div class="container">
<form method="post" action="<c:url value='/createTest'></c:url>" class='form-inline'  >
    <input type='hidden' value='${branch}' name='branch'>
    <input type='hidden' value='${batch}' name='batch'>
    <table style="width:100%;padding: 20%">
        <tr>
            <td><h3>Subject::</h3></td>
            <td><select name='subject' class=" input-lg ">
                <option>Select a subject...</option>
                <c:forEach items='${subjectList}' var='subject'>
                <option value='${subject[0]}'>${subject[1]}</option>
                </c:forEach>
                </select></td>
        </tr>
        <tr>
            <td><h3>Date::</h3></td>
            <td> <input type='date' name='date' class=" input-lg "></td>
        </tr>
        <tr>
            <td><h3>Start Time::</h3></td>
            <td><input type='time' name='time' class="input-lg "></td>
        </tr>
        <tr>
            <td><h3>End Time::</h3></td>
            <td><input type='time' name='duration' class="input-lg "></td>
        </tr>
    </table>
    <input type='submit' value='CREATE TEST' class="col-md-offset-1 input-lg ">
</form>
 </div>
</body>
   <script src="<c:url value='/static/js/jquery-3.2.1.min.js'></c:url>"></script>
   <script src="<c:url value='/bootstrap/js/bootstrap.js'></c:url>"></script>
   <script src="<c:url value='/static/js/adminJs.js'></c:url>"></script>
</html>
