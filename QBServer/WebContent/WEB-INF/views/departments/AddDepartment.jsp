<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../../../AdminSideNavigation.jsp"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   
 	<link href="TeacherDashboardFiles/all.css" rel="stylesheet">

	<link href="TeacherDashboardFiles/app.css" rel="stylesheet">
	<title>Add New Department</title>
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
              			<h1 class="text-display-1">New Department</h1>
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

   										<form:form action="AddDepartment" modelAttribute="dept" method="post">
    										<div class="form-group">
     											<div class="form-control-material static required">
      												<input type="text" class="form-control" name="name" id="name" maxlength="50" placeholder="Department Name" value="${dept.name}" required >
 												</div>
    										</div>
   
    										<div class="form-group">
     											<div class="form-control-material static required">
      												<input type="text" class="form-control" name="abbreviation" id="abbreviation" maxlength="10" placeholder="Department Abbreviation" value="${dept.abbreviation}" required >
 												</div>
    										</div>
 
   											<button name="submit" type="submit">Create</button>
  											<p class="message"></p>
  										<%-- </form> --%>
  										</form:form>
 									</div>
                  				</div>
                  			</div>
                  			<div class="panel-header">
                  				<a class="btn btn-success paper-shadow relative " href="<c:url value="/DepartmentsView" />" >Cancel</a>
                   			</div>
                  		</div>
        			</div>
        		</div>
        	</div>
		</div>
	</div>

</body>
</html>