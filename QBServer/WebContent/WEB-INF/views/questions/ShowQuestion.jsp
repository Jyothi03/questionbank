<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:choose>
	<c:when test="${isAdmin}">
		<jsp:include page="../../../AdminSideNavigation.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:include page="../../../SideNavigation.jsp"/>
	</c:otherwise>
</c:choose>

<html class="st-layout ls-top-navbar-large ls-bottom-footer show-sidebar sidebar-l3" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta charset="UTF-8">
    
  		<link href="TeacherDashboardFiles/all.css" rel="stylesheet">

	 	<link href="TeacherDashboardFiles/app.css" rel="stylesheet">

		<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
		
		<title>Show Question and Answer</title>
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

   											<form action="CourseAddQuestion" method="post">
   												<div class="form-group">
    												<div>
    													<label><strong>Course</strong></label>
    												</div>
     												<div class="form-control-material static required">
     													<label>${question.course.courseName}</label>
 													</div>
   												</div>
     											<div class="form-group">
    												<div>
    													<label><strong>Chapter</strong></label>
    												</div>
     												<div class="form-control-material static required">
     													<label>${question.chapter}</label>
 													</div>
    											</div>
   
   												<div class="form-group">
    												<div>
    													<label><strong>Question</strong></label>
    												</div>
     												<div class="form-control-material static required">
     													<label>${question.question}</label>
 													</div>
   												</div>

   												<div class="form-group">
    												<div>
    													<label><strong>Answer</strong></label>
    												</div>
     												<div class="form-control-material static required">
     													<label>${answer.answerText}</label>
 													</div>
   												</div>
 
  												<p class="message"></p>
  											</form>
 										</div>
                  					</div>
                  				</div>
                  				
                  					<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="ShowCourse">
      											<c:param name="id" value="${question.course.id}" />
      										</c:url>">Course</a>&nbsp;&nbsp;
                  					<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="EditQuestion">
      											<c:param name="id" value="${question.id}" />
      											<c:param name="answerId" value="${answer.id}"/>
      										</c:url>">Edit</a>&nbsp;
      								<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/CourseAddQuestion">
												<c:param name="courseId" value="${question.course.id}" />
											 </c:url>" >Add Question and Answers</a>
														  <p>&nbsp;</p>
                  				
                    			
                  			</div>
        				</div>
        			</div>
        		</div>
			</div>
		</div>

	</body>
</html>