<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../../SideNavigation.jsp"/>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  		<link href="TeacherDashboardFiles/all.css" rel="stylesheet">
	 	<link href="TeacherDashboardFiles/app.css" rel="stylesheet">
		<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
		<title>Generate Course PDF</title>
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
						</div><div class="page-section">
              				<h1 class="text-display-1">Course Questionnaire</h1>
            			</div>
            			
            			<div class="row" data-toggle="isotope">
              
              				<div class="item col-xs-12 col-lg-6">
                				<div class="panel panel-default paper-shadow" data-z="0.5">
                				
                  					<div class="panel-heading">
										<div class="form">
	
   											<form action="CreateCourseQuestionSheet" method="post">
   												<input type="hidden" name="courseId" value="${course.id}">
   
   												<div class="form-group form-control-material static required">
													<select name="includeAnswers" required="true">
  														<option value="">-- Select Type</option>
  														<option value="true">Question &amp; Answer</option>
  														<option value="false">Questionnaire</option>
													</select>
   												</div>
   												
   												<div class="form-group form-control-material static">
    												<input type="text" class="form-control" name= "pdfTitle" id="pdfTitle" placeholder="Handout Title"/>
   												</div>

   												<button class="btn btn-success" name="submit" type="submit">Create PDF</button>
  											</form>
 										</div>
									</div>
								</div>
							</div>
						</div>
                  		<a class="btn btn-success paper-shadow relative" href="<c:url value="/ShowCourse">
      							<c:param name="id" value="${course.id}" />
      						</c:url>">Show Course</a>&nbsp;
                  		<a class="btn btn-success paper-shadow relative" href="<c:url value="/TeacherCourseView" />" >List Courses </a>&nbsp;
					</div>
				</div>
			</div>
		</div>
	</body>
</html>