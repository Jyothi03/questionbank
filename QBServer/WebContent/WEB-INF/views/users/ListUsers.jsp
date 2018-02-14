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
<title>Users</title>
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
              			<h1 class="text-display-1">Teachers</h1>
					</div>
            			
            			
            			
            				<div class="row" data-toggle="isotope">
	              				<div class="item col-xs-12 ">
	              				<div class="panel-header">
                    				<a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/AddUser" />" >Add User</a>
                    						      														
                    		</div>
	                    		<p>&nbsp;</p>
	                  				<div class="panel panel-default paper-shadow" data-z="0.5">
		                				<table class="table table-hover">
		    								<thead>
		    										    <tr>
															<th class=" collg-4 ">First Name</th>
															<th class=" collg-2 ">Last Name</th>
															<th class=" collg-2 ">User Name</th>
															<th class=" collg-2 "></th>
															<th></th>
														</tr>
		    								</thead>
		    								<tbody>
			   									<c:forEach items="${users}" var="item">
    											<tr>
      												<td><c:out value="${item.firstName}" /></td>
      												<td><c:out value="${item.lastName}" /></td>
      												<td><c:out value="${item.userName}" /></td>
      												<td>
      													<a class="btn btn-success paper-shadow relative btn-sm" href="
      														<c:url value="/ShowUser">
      															<c:param name="id" value="${item.id}" />
      														</c:url>"
      													>
      														Show
      													</a>
      													&nbsp
      													<a class="btn btn-success paper-shadow relative btn-sm" href="
      														<c:url value="/EditUser"> 
      															<c:param name="id" value="${item.id}" />
      														</c:url>"
      													>
      														Edit
      													</a>
      												</td>
    											</tr>
  											</c:forEach>
		  									</tbody>
		   							  </table>
	                  				</div>
	                  				<div class="panel-header">
                  				<c:choose>
                  					<c:when test="${isAdmin == true}">
                 						<a class="btn btn-success paper-shadow relative" href="<c:url value="/admindashboard.jsp" />" >Dashboard</a>
                  					</c:when>
                  					<c:otherwise>
                  						<a class="btn btn-success paper-shadow relative" href="<c:url value="/teacherdashboard.jsp" />" >Dashboard</a>
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