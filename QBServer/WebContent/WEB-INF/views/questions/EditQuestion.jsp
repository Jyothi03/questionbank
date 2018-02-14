<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
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
		
		<title>Edit Question and Answer</title>
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
              				<h1 class="text-display-1">Question and Answer</h1>
            			</div>
            			
            			<div class="row" data-toggle="isotope">
              
              				<div class="item col-xs-12 col-lg-6">
                				<div class="panel panel-default paper-shadow" data-z="0.5">
                				
                  					<div class="panel-heading">
										<div class="form">
    										<span class="error">
  												<c:forEach items="${errors}" var="error">
  													<c:out value="* ${error}" /><br />
  												</c:forEach>
  											</span>

   											<%-- <form action="UpdateQuestion" method="post"> --%>
   											<form:form action="UpdateQuestion" modelAttribute="question" method="post">
   												<input type="hidden" name="id" value="${question.id}">
   												<input type="hidden" name="answerId" value="${answer.id}">
   												<div class="form-group">
   													<div class="form-control-material static required">
														<form:select path="course.id">
      														<form:option value="-" label="--Select Course"/>
      														<form:options items="${courses}" itemValue="id" itemLabel="courseName"/>
            											</form:select>
   													</div>
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
 
   												<button name="submit" type="submit">Update Question and Answer</button>
  												<p class="message"></p>
  											<%-- </form> --%>
  											</form:form>
 										</div>
                  					</div>
                  				</div>
                  				<div class="panel-header">
                  					<a href="<c:url value="ShowCourse">
      											<c:param name="id" value="${question.course.id}" />
      										</c:url>">Course</a>&nbsp;&nbsp;|&nbsp;
                  					<a href="<c:url value="ShowQuestion">
      											<c:param name="id" value="${question.id}" />
      										</c:url>">Show</a>
                    			</div>
                  			</div>
        				</div>
        			</div>
        		</div>
			</div>
		</div>

	</body>
</html>