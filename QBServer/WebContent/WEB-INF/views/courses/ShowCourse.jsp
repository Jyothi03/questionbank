<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    
  		<link href="TeacherDashboardFiles/all.css" rel="stylesheet">

	 	<link href="TeacherDashboardFiles/app.css" rel="stylesheet">
    
		<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
		
		<title>Show Course</title>
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
              				<h1 class="text-display-1">Course</h1>
            			</div>
            			
            			<div class="row" data-toggle="isotope">
                		 	<div class="item col-xs-30 col-lg-15">
                		  		<div class="panel panel-default paper-shadow" data-z="10">
                		   			<div class="panel-heading">
										<div class="form">

    										<div class="form-group">
    											<div>
    												<label><strong>Course Name</strong></label>
    											</div>
     											<div class="form-control-material static required">
     												<label>${course.courseName}</label>
 												</div>
    										</div>
   
   											<div class="form-group">
   												<div>
    												<label><strong>Course Dept/Number</strong></label>
   												</div>
   												<div class="form-group form-control-material static required">
     												<label>${course.department.abbreviation}-${course.courseNumber}</label>
   												</div>
   											</div>
   
   											<div class="form-group">
   												<div>
    												<label><strong>Credits</strong></label>
   												</div>
   												<div class="form-control-material static required">
     												<label>${course.credit}</label>
     											</div>
   											</div>

														</div>
                  					</div>
                  				</div>
                  				<div class="panel-header">
                  					<a class="btn btn-success paper-shadow relative" href="<c:url value="/TeacherCourseView" />" >List Courses </a>&nbsp;
                  					<a class="btn btn-success paper-shadow relative" href="<c:url value="/EditCourse">
      											<c:param name="id" value="${course.id}" />
      										</c:url>">Edit Course</a>&nbsp;
      								<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/CourseAddQuestion">
												<c:param name="courseId" value="${course.id}" />
											</c:url>" >Add Question and Answers</a>&nbsp;
      								<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/PrintCourseQuestionsView">
												<c:param name="courseId" value="${course.id}" />
											</c:url>" >Create Document</a>&nbsp;
                
                    			</div>
                  			</div>
        				</div>
        				
        				
        				
        				
        				
        				
        				<div class="page-section">
              				<h1 class="text-display-1">Questions and Answers</h1>
            			</div>
            			
            			<div class="row" data-toggle="isotope">
                		 <div class="item col-xs-30 col-lg-15">
                		 		     <div class="panel panel-default paper-shadow" data-z="10">
                		   <div class="panel-heading">
                		   
                
										<div class="form">

    										

											<div class="form-group">
												
												
                				<!-- COURSE QUESTIONS -->
                				<div class="row" data-toggle="isotope">
	              				<div class="item col-xs-12 ">
	              				
	                    		<p>&nbsp;</p>
	                  				<div class="panel panel-default paper-shadow" data-z="0.5">
		                				<table class="table table-hover">
		    								<thead>
		    										    <tr>
															<th class=" collg-4 ">Chapter</th>
															<th class=" collg-2 ">Question</th>
															<th class=" collg-2 ">Answer</th>
															<th class=" collg-2 "></th>
															<th></th>
														</tr>
		    								</thead>
		    								<tbody>
			   									<c:forEach items="${questions}" var="question">
	  												<tr>
	      													<td><c:out value="${question.chapter}" /></td>
	      													<td><c:out value="${question.question}" /></td>
	      								 				    <td><c:out value="${question.answer}" /></td> 
	      													<td>
	      														<a class="btn btn-success paper-shadow relative btn-sm" href="<c:url value="/ShowQuestion">
	      																	<c:param name="id" value="${question.id}" />
	      																</c:url>">Show</a>&nbsp;
	      														<a class="btn btn-success paper-shadow relative btn-sm" href="<c:url value="/EditQuestion"> 
	      																	<c:param name="id" value="${question.id}" />
	      																	<c:param name="answerId" value="${question.answerId}"/>  
	      																	</c:url>">Edit</a>
	      													</td>
	    												</tr>
	  												</c:forEach>
		  									</tbody>
		   							  </table>
	                  				</div>
	                  			</div>
	        				</div>
	                				
													
												</div>
	 										</div>
	                  					</div>
	                  				</div>
	                  				
	                  			</div>
	        				</div>
        			</div>
        		</div>
			</div>
		</div>

	</body>
	<footer class="footer">
      <strong>QBMS</strong>  © Copyright 2016
    </footer>
</html>