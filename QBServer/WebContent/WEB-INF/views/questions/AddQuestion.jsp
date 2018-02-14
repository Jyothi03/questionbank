<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:choose>
	<c:when test="${isAdmin}">
		<jsp:include page="../../../AdminSideNavigation.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:include page="../../../SideNavigation.jsp"/>
	</c:otherwise>
</c:choose>

<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- <meta charset="UTF-8"> -->
  <link href="TeacherDashboardFiles/all.css" rel="stylesheet">
  <link href="TeacherDashboardFiles/app.css" rel="stylesheet">
  <!--     	<link rel="stylesheet" href="AdminFiles/reset.css">
  <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
  <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Montserrat:400,700'>-->
  <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
  <!-- <link rel="stylesheet" href="AdminFiles/stylesss.css"> -->
  <title>Add New Question and Answer</title>
 </head>
	
 <body>  
  <!-- content push wrapper -->
  <div class="st-pusher" id="content">
  <!-- this is the wrapper for the content -->
   <div class="st-content">
    <!-- extra div for emulating position:fixed of the menu -->
    <div class="st-content-inner padding-none">
     <div class="container-fluid">
      
      <div class="container">
  	   <div class="info">
        <h1>QBMS</h1><span>Question Bank Management System</span> 
  	   </div>
	  </div>
	
      <div class="page-section">
       <h1 class="text-display-1">New Question and Answer</h1>
      </div>
            			
      <div class="row" data-toggle="isotope">
       <div class="item col-xs-12 ">
        <div class="panel panel-default paper-shadow" data-z="0.5">
         <div class="panel-heading">
		  <div class="form">
		   <!-- 	<form>
                     <input type="button" value="Print this page" onClick="window.print()">
                    </form> -->
           <span class="error">
  			<c:forEach items="${errors}" var="error">
  			 <c:out value="* ${error}" /><br />
			</c:forEach>
  		   </span>

   		   <form:form action="CourseAddQuestion" modelAttribute="question" method="post">
   			
   			<!-- <div class="form-group">
   			 <div class="form-control-material static required">
      		  <input type="text" class="form-control" name="courseId" id="courseId" maxlength="50" placeholder="Course" value="${question.course.id}" required >
   			 </div>
   			</div> -->
   			
   			
   			
   			
   			<div style="width: 35%;">
   			 &nbsp;
			  <form:select class="form-group form-control static btn-success " path="course.id">
      		   <form:option value="-" label="Select Course"/> 
      		   <form:options items="${courses}" var="item" value="${item.courseName}" itemValue="id" itemLabel="courseName"/>
              </form:select>
   			
     	   </div>
     		
     		<div class="form-group">
     		 <div class="form-control-material static required">
      		  <input type="text" class="form-control" name="chapter" id="chapter" maxlength="7" placeholder="Chapter Number" value="${question.chapter}" onkeypress='return event.charCode >= 48 && event.charCode <= 57'  value="${course.credit}" required >
 			 </div>									
 			</div>
   
   			<div class="form-group">
   			 <div class="form-control-material static required">
    		  <input type="text" class="form-control" name="question" id="question" maxlength="256" placeholder="Question" value="${question.question}" required>
    		 </div>
   			</div>
   			
   			<div class="form-group">
   			 <div class="form-control-material static required">
    		  <input type="text" class="form-control" name="answerText" id="answerText" maxlength="256" placeholder="Answer" value="${answer.answerText}" required>
    		 </div>
   			</div>
   											
   			<button class="btn btn-success paper-shadow relative" name="submit" type="submit">Create</button>
   			&nbsp;
   			<c:choose>
   					 <c:when test="${ question.course.id == null}">
              <a class="btn btn-success paper-shadow relative" href="<c:url value="/teacherdashboard.jsp" />" >Cancel</a>
             </c:when>
             <c:otherwise>
				<a class="btn btn-success paper-shadow relative" href="<c:url value="/ShowCourse" >
             			<c:param name="id" value="${question.course.id}"/>
           			</c:url>" >Cancel
            	</a>
             </c:otherwise>
   			</c:choose>
            
       
  			<p class="message"> </p>
  	       </form:form>
 		  </div>
         </div>
        </div>
        
        <div class="panel-header">
         
        </div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>

 </body>
</html>