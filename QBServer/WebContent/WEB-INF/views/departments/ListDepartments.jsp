<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${isAdmin}">
		<jsp:include page="../../../AdminSideNavigation.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:include page="../../../SideNavigation.jsp"/>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
  	<link href="TeacherDashboardFiles/all.css" rel="stylesheet">
	<link href="TeacherDashboardFiles/app.css" rel="stylesheet">
<title>Departments</title>
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
              			<h1 class="text-display-1">Departments</h1>
					</div>
            			
            		<div class="row" data-toggle="isotope">
	              		<div class="item col-xs-12 col-lg-6">
                  			<div class="panel panel-default paper-shadow" data-z="0.5">
                				<div class="panel-header text-right">
                    				<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/AddDepartment" />" >+</a>
                    			</div>
                				
                				<div class="panel-heading">
                    				<!-- <h4 class="text-headline margin-none">All Courses</h4> -->
                    				<div class="table-list-content">
										<table width="100%">
											<tr>
												<th>Name</th>
												<th>Abbreviation</th>
												<th></th>
											</tr>
  											<c:forEach items="${departments}" var="item">
    											<tr>
      												<td><c:out value="${item.name}" /></td>
      												<td><c:out value="${item.abbreviation}" /></td>
      												<td>
      													<a href="
      														<c:url value="/ShowDepartment">
      															<c:param name="id" value="${item.id}" />
      														</c:url>"
      													>
      														Show
      													</a>
      													&nbsp;&nbsp;|&nbsp;
      													<a href="
      														<c:url value="/EditDepartment"> 
      															<c:param name="id" value="${item.id}" />
      														</c:url>"
      													>
      														Edit
      													</a>
      												</td>
    											</tr>
  											</c:forEach>
										</table>
                    				</div>
                  				</div>
                  			</div>
                  			<div class="panel-header">
                  				<c:choose>
                  					<c:when test="${isAdmin == true}">
                  						<a class="btn btn-success paper-shadow relative " href="<c:url value="/admindashboard.jsp" />" >Dashboard</a>
                  					</c:when>
                  					<c:otherwise>
                  						<a class="btn btn-success paper-shadow relative " href="<c:url value="/teacherdashboard.jsp" />" >Dashboard</a>
                  					</c:otherwise>
                  				</c:choose>
                   			</div>
                  		</div>
        			</div>
        		</div>
        	</div>
		</div>
	</div>
</body>
</html>