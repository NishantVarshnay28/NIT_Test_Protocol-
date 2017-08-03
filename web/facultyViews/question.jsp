<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, models.Question"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Test</title>
        <link href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" rel="stylesheet">
        <link href="<c:url value='/static/css/myStyle.css'></c:url>" rel="stylesheet">
        <style>
            a {
            background-color: #5e5e5e ;color :white;padding: 5px 25px;text-align: center;text-decoration: none;display: inline-block;
            }
            h3 
            {
                 text-align: center;
                 text-decoration: underline;
                 padding-bottom:  10px;
            }
        </style>
    </head>
    <body>
 <jsp:include page="header.jsp"></jsp:include>
 <div class='row container-fluid'>
     <div class='col-md-11 container-fluid'></div>
     <div class='col-md-1 container-fluid'><a href="/SMS/facultyViews/StudentProfile.jsp">HOME</a></div>
 </div>
 <div class='row container'>
     <div class='col-md-9 container'>

    <% 
        int qInd=Integer.parseInt(request.getParameter("quesInd"));
        session.setAttribute("qInd",qInd);
     %> 
     <h3>Test Code:: ${testDetail.testcode} </h3>
      <h4>Weightage::${questionList[qInd].weightage}</h4>
 <form method="post">
     <table>
             <tr>
                 <td>Question  ${qInd+1}</td>
                 <td><textarea name="question" rows="8" cols="100">${questionList[qInd].question}</textarea></td>
             </tr>
             <tr>
                 <td>1.</td>
                 <td><textarea name="option1" rows="1" cols="100">${questionList[qInd].option1}</textarea></td>
             </tr>
             <tr>
                <td>2.</td>
                 <td><textarea name="option2" rows="1" cols="100">${questionList[qInd].option2}</textarea></td>
             </tr>
             <tr>
                <td>3.</td>
                 <td><textarea name="option3" rows="1" cols="100">${questionList[qInd].option3}</textarea></td>
             </tr>
             <tr>
                 <td>4.</td>
                 <td><textarea name="option4" rows="1" cols="100">${questionList[qInd].option4}</textarea></td>
             </tr>
             </table>
             Your Response::
             <c:if test= "${ answerList[qInd] == 0}"  >
             <input type="number" name="ans" min="1" max="4" step="1" >
             </c:if>
             <c:if test= "${ answerList[qInd] != 0}"  >
             <input type="number" name="ans" min="1" max="4" step="1" value="${answerList[qInd]}">
             </c:if>
             <br>
             <br>
             <input type="submit" Value="Save"onclick="form.action='/SMS/saveAnswer';" class="col-md-offset-1 " >(Please click here to save your response)
             <input type="submit" Value="End Test" onclick="form.action='/SMS/endTest';" class="col-md-offset-1 ">
             </form>
             <span id="result"></span>
             
 </div>
         <div class='col-md-3 container-fluid'>
              
             <h4>Time Left::</h4>
             <script language="JavaScript">
             TargetDate= "${eTime}";
             BackColor = "palegreen";
             ForeColor = "navy";
             CountActive = true;
             CountStepper = -1;
             LeadingZero = true;
             DisplayFormat = "%%H%% Hours, %%M%% Mins, %%S%% Secs.";
             FinishMessage ="Time is over now Your response will not recorded";
             </script>
             <script language="JavaScript" src="//scripts.hashemian.com/js/countdown.js"></script>
              <%
                     int s=((ArrayList<Question>)session.getAttribute("questionList")).size();
                     int row= s/3;
                     pageContext.setAttribute("row",row);
                     int count=1;
              %>
      
              <h4>Go to Question::</h4>     
             <div style=" border:2px solid black" >
                 <table style="border: 1px solid black;table-layout: fixed;width: 160px;">
                     <c:forEach varStatus="counter" begin="1" end="${row}">
                         <tr style="border: 1px solid black;">
                          <c:forEach begin="1" end="3">
                              <c:set var="count" value="<%= count++ %>"></c:set>
                              <td style="border: 1px solid black; text-align: center;"><a href="/SMS/facultyViews/question.jsp?quesInd=${count-1}">${count}</a></td>
                         </c:forEach>
                         </tr>
                      </c:forEach>
                      <c:set var="rest" value="<%= s%3 %>"></c:set>
                      <c:forEach varStatus="counter" begin="1" end="1">
                        <tr style="border: 1px solid black; text-align: center;">
                       <c:forEach begin="1" end="${rest}">
                              <c:set var="count" value="<%= count++ %>"></c:set>
                              <td style="border: 1px solid black;"><a href="/SMS/facultyViews/question.jsp?quesInd=${count-1}">${count}</a></td>
                         </c:forEach>
                        </tr>
             </c:forEach>
               </table>
             </div>
             </div>
 </div>

                      
                      
   <script src="<c:url value='/static/js/jquery-3.2.1.min.js'></c:url>"></script>
   <script src="<c:url value='/bootstrap/js/bootstrap.js'></c:url>"></script>
   <script src="<c:url value='/static/js/adminJs.js'></c:url>"></script>

 
                      
</body>
  </html>
