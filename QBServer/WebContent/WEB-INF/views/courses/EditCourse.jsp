<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		
		<title>Edit Course</title>
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
              
              				<div class="item col-xs-12 col-lg-6">
                				<div class="panel panel-default paper-shadow" data-z="0.5">
                				
                  					<div class="panel-heading">
										<div class="form">
	
    										<span class="error">
  												<c:forEach items="${errors}" var="error">
  													<c:out value="* ${error}" /><br />
  												</c:forEach>
  											</span>
   											<%-- <form action="UpdateCourse" method="post"> --%>
   											<form:form action="UpdateCourse" modelAttribute="course" method="post">
   												<input type="hidden" name="id" value="${course.id}">
    											<div class="form-group">
     												<div class="form-control-material">
      													<input type="text" class="form-control" name="courseName" id="courseName" maxlength="50" placeholder="Course Name" required value="${course.courseName}">
 													</div>
    											</div>
   
   												<div class="form-group form-control-material static required">
													<form:select path="department.id">
      													<form:option value="-" label="--Select Department"/>
      													<form:options items="${departments}" itemValue="id" itemLabel="abbreviation"/>
            										</form:select>
   												</div>
   
   												<div class="form-group form-control-material static required">
    												<input type="text" class="form-control" name= "courseNumber" id="courseNumber" maxlength="8" placeholder="Course Number" value="${course.courseNumber}">
   												</div>
   
   												<div class="form-group form-control-material static required">
													<input type="text" class="form-control" name="credit" id="credit" maxlength="1" placeholder="Course Credits" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="${course.credit}" >
   												</div>

   												<button class="btn btn-success" name="submit" type="submit">Update Course</button>
  											<%-- </form> --%>
  											</form:form>
 										</div>
                  					</div>
                  				</div>
                  				<div class="panel-header">
                  					<a class="btn btn-success paper-shadow relative " href="<c:url value="/TeacherCourseView" />" >List Courses</a>&nbsp;
                  					<a class="btn btn-success paper-shadow relative " href="<c:url value="ShowCourse">
      											<c:param name="id" value="${course.id}" />
      										</c:url>">Show Course</a>
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