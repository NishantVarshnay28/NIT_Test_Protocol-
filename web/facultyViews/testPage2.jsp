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
<h3>Test code::${sessionScope.testCode}</h3>
${success}
 <h2>Question</h2>
 <form method="post">
     <table>
             <tr>
                 <td>Question</td>
                 <td><textarea name="question" rows="8" cols="150"></textarea></td>
             </tr>
             <tr>
                 <td>Option 1</td>
                 <td><textarea name="option1" rows="1" cols="150"></textarea></td>
             </tr>
             <tr>
                <td>Option 2</td>
                 <td><textarea name="option2" rows="1" cols="150"></textarea></td>
             </tr>
             <tr>
                <td>Option 3</td>
                 <td><textarea name="option3" rows="1" cols="150"></textarea></td>
             </tr>
             <tr>
                <td>Option 4</td>
                 <td><textarea name="option4" rows="1" cols="150"></textarea></td>
             </tr>
             <tr>
                 <td>Answer </td>
                 <td><input type="number" name="answer" min="1" max="4" step="1" value="1"></td>
             </tr>
             <tr>
                 <td>Weightage</td>
                 <td><input type="text" name="weightage"></td>
             </tr>
             
             <tr>
                 <td><input type="submit" value="Next" onclick="form.action='/SMS/nextServlet';"></td>
                 <td><input type="submit" value="Submit" onclick="form.action='/SMS/submitServlet';"></td>
             </tr>
     </table>
    </form>
</body>
   <script src="<c:url value='/static/js/jquery-3.2.1.min.js'></c:url>"></script>
   <script src="<c:url value='/bootstrap/js/bootstrap.js'></c:url>"></script>
   <script src="<c:url value='/static/js/adminJs.js'></c:url>"></script>
</html>
