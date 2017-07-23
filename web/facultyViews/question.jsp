<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, models.Question"%>
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
 <h2>Test</h2>
 
 <%! static  int qState=0; %>
 <div class='row'>
     <div class='col-md-10 container-fluid'>

    <% 
        int qInd=Integer.parseInt(request.getParameter("quesInd"));
        pageContext.setAttribute("qInd",qInd);
        int answers[]= (int[])session.getAttribute("answerList");
        System.out.println("next");
        for(int a: answers)
            System.out.println("Answer: "+ a);
     %> 
         
     
 <form method="post">
     <table>
             <tr>
                 <td>Question</td>
                 <td><textarea name="question" rows="8" cols="150">${questionList[qInd].question}</textarea></td>
             </tr>
             <tr>
                 <td><input id='aRB' type="radio" name="ans" value="1" onchange='<% answers[qInd]=1; session.setAttribute("answerList",answers);%>'></td>
                 <td><textarea name="option1" rows="1" cols="150">${questionList[qInd].option1}</textarea></td>
             </tr>
             <tr>
                <td><input id='aRB' type="radio" name="ans" value="2" onchange='<% answers[qInd]=2; session.setAttribute("answerList",answers);%>'></td>
                 <td><textarea name="option2" rows="1" cols="150">${questionList[qInd].option2}</textarea></td>
             </tr>
             <tr>
                <td><input id='aRB' type="radio" name="ans" value="3" onchange='<% answers[qInd]=3; session.setAttribute("answerList",answers);%>'></td>
                 <td><textarea name="option3" rows="1" cols="150">${questionList[qInd].option3}</textarea></td>
             </tr>
             <tr>
                 <td><input id='aRB' type="radio" name="ans" value="4" onchange='<% answers[qInd]=4; session.setAttribute("answerList",answers);%>'></td>
                 <td><textarea name="option4" rows="1" cols="150">${questionList[qInd].option4}</textarea></td>
             </tr>
             
             <tr>
                 <td><input type="submit" value="Next" onclick="form.action='/SMS/nextServlet';"></td>
                 <td><input type="submit" value="Submit" onclick="form.action='/SMS/submitServlet';"></td>
             </tr>
     </table>
    </form>
 </div>
         <div class='col-md-2'>
             <script language="JavaScript">
             TargetDate = "07/23/2017 6:00 PM";
             BackColor = "palegreen";
             ForeColor = "navy";
             CountActive = true;
             CountStepper = -1;
             LeadingZero = true;
             DisplayFormat = "%%D%% Days, %%H%% Hours, %%M%% Minutes, %%S%% Seconds.";
             FinishMessage = "It is finally here!";
             </script>
             <script language="JavaScript" src="//scripts.hashemian.com/js/countdown.js"></script>
              <%
                     int s=((ArrayList<Question>)session.getAttribute("questionList")).size();
                     
                     int row= s/3;
                     out.println("No of rows : "+row);
                     pageContext.setAttribute("row",row);
                     int count=1;
              %>
                     
                     Rows: ${row}
             <div style=" border:2px solid black" >
             <table>
                     <c:forEach varStatus="counter" begin="1" end="${row}">
                         <tr>
                          <c:forEach begin="1" end="3">
                              <c:set var="count" value="<%= count++ %>"></c:set>
                              <td><a href="/SMS/facultyViews/question.jsp?quesInd=${count-1}">${count}</a></td>
                         </c:forEach>
                         </tr>
                      </c:forEach>
                      <c:set var="rest" value="<%= s%3 %>"></c:set>
                      <c:forEach varStatus="counter" begin="1" end="1">
                        <tr>
                       <c:forEach begin="1" end="${rest}">
                              <c:set var="count" value="<%= count++ %>"></c:set>
                              <td><a href="/SMS/facultyViews/question.jsp?quesInd=${count-1}">${count}</a></td>
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

   <script>
                          $(document).ready(function(){
                              var selectedVal = "";
var selected = $("input[type='radio'][name='ans']:checked");
if (selected.length > 0) {
    selectedVal = selected.val();
    console.log(selectVal);
}
                              /*$('aRB').on("change",function(){
                                var value=  $('input[name=ans]:checked').val();
                                 alert( ""+value);
                                 console.log(value);
                              });*/
                          });
                          
 
                      </script>
                      
</body>
  </html>
