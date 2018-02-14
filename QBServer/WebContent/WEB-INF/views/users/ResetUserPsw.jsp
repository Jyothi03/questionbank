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
  		<link href="TeacherDashboardFiles/all.css" rel="stylesheet">
	 	<link href="TeacherDashboardFiles/app.css" rel="stylesheet">
		<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

		<title>Reset User Password</title>
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
              				<h1 class="text-display-1">Reset User Password</h1>
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
   											<%-- <form:form action="ResetUserPsw" modelAttribute="user" method="post"> --%>
   											<form action="ResetUserPsw" method="post">
   												<input type="hidden" name="id" value="${user.id}">

   												<div class="form-group">
    												<div class="form-control-material static required">
    													<input class="form-control" id="password" name="password" type="password" required maxlength="13" placeholder="Enter Password">
    												</div>
   												</div>
   
   												<div class="form-group">
    												<div class="form-control-material static required">
     													<input class="form-control" id="password2" name="rpassword" type="password" required maxlength="13" placeholder="Confirm Password">
    												</div>
   												</div>

   												<button class="btn btn-success" name="submit" type="submit">Reset Password</button>
  											</form>
  											<%-- </form:form> --%>
 										</div>
                  					</div>
                  				</div>
                  				<div class="panel-header">
                  					<c:if test="${isAdmin}">
                  						<a class="btn btn-success paper-shadow relative" href="<c:url value="/UsersView" />" >List Users </a>&nbsp;
                  					</c:if>
                  					<a class="btn btn-success paper-shadow relative" href="<c:url value="/ShowUser">
      											<c:param name="id" value="${user.id}" />
      										</c:url>">Show User</a>&nbsp;
      								<%-- <a class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated href="<c:url value="/ResetUserPsw">
												<c:param name="userId" value="${user.id}" />
											</c:url>" >Reset Password</a>&nbsp; --%>
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