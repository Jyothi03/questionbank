<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../../SideNavigation.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<!-- <meta charset="UTF-8"> -->
    
  		<link href="TeacherDashboardFiles/all.css" rel="stylesheet">

	 	<link href="TeacherDashboardFiles/app.css" rel="stylesheet">
    
<!--     	<link rel="stylesheet" href="AdminFiles/reset.css">

    	<link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
		<link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Montserrat:400,700'>
		<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

     	<link rel="stylesheet" href="AdminFiles/stylesss.css"> -->
		
		<title>Courses</title>
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
              				<h1 class="text-display-1">Courses</h1>
            			</div>
            			
            			<div class="row" data-toggle="isotope">
              
              				<div class="item col-xs-12 col-lg-6">
                  				<div class="panel panel-default paper-shadow" data-z="0.5">
                				<div class="panel-header text-right">
                    				<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/TeacherAddCourse" />" >+</a>
                    			</div>
                				
                  					<div class="panel-heading">
                    					<!-- <h4 class="text-headline margin-none">All Courses</h4> -->
                    					<div class="table-list-content">
											<table width="100%">
												<tr>
													<th>Course</th>
													<th>Number</th>
													<th>Department</th>
													<th>Credits</th>
													<th></th>
												</tr>
  												<c:forEach items="${courses}" var="item">
    												<tr>
      													<td><c:out value="${item.courseName}" /></td>
      													<td><c:out value="${item.courseNumber}" /></td>
      													<td><c:out value="${item.deptName}" /></td>
      													<td><c:out value="${item.credit}" /></td>
      													<td>
      														<a href="<c:url value="/ShowCourse">
      																	<c:param name="id" value="${item.id}" />
      																</c:url>">Show</a>&nbsp;&nbsp;|&nbsp;
      														<a href="<c:url value="/EditCourse"> 
      																	<c:param name="id" value="${item.id}" />
      																</c:url>">Edit</a>
      													</td>
    												</tr>
  												</c:forEach>
											</table>
                    					</div>
                  					</div>
                  				</div>
                  				<div class="panel-header">
                  					<a href="<c:url value="/teacherdashboard.jsp" />" >Dashboard</a>
                    			</div>
                  			</div>
        				</div>
        			</div>
        		</div>
			</div>
		</div>

	</body>
</html>