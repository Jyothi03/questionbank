<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${isAdmin}">
		<jsp:include page="../../../AdminSideNavigation.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:include page="../../../SideNavigation.jsp"/>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<!-- <meta charset="UTF-8"> -->
    	<link href="TeacherDashboardFiles/all.css" rel="stylesheet">

	 	<link href="TeacherDashboardFiles/app.css" rel="stylesheet">
    	
  	
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
              				<div class="item col-xs-12 ">
              				<div class="panel-header">
                    				<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/TeacherAddCourse" />" >Add Course</a>
                    						      														&nbsp;
                    				<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/teacherdashboard.jsp" />" >Dashboard</a>
                    		</div>
                    		<p>&nbsp;</p>
                  				<div class="panel panel-default paper-shadow" data-z="0.5">
	                				<table class="table table-hover">
	    								<thead>
	    										    <tr>
														<th class=" collg-4 ">Course</th>
														<th class=" collg-2 ">Number</th>
														<th class=" collg-2 ">Department</th>
														<th class=" collg-2 ">Credits</th>
														<th></th>
													</tr>
	    								</thead>
	    								<tbody>
		   									<c:forEach items="${courses}" var="item">
		    												<tr>
		      													<td><c:out value="${item.courseName}" /></td>
		      													<td><c:out value="${item.deptabbreviation}-${item.courseNumber}" /></td>
		      													<td><c:out value="${item.deptname}" /></td>
		      													<td><c:out value="${item.credit}" /></td>
		      													<td>
		      														<a class="btn btn-success paper-shadow relative btn-sm" href="<c:url value="/ShowCourse"> <c:param name="id" value="${item.id}" /></c:url>">Show</a>
		      														&nbsp;
		      														<a class="btn btn-success paper-shadow relative btn-sm" href="<c:url value="/EditCourse"><c:param name="id" value="${item.id}" /></c:url>">Edit</a>
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

	</body>
	<footer class="footer">
      <strong>QBMS</strong>  © Copyright 2016
    </footer>
</html>