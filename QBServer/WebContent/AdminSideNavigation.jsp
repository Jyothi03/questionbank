<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

   

<html class="st-layout ls-top-navbar-large ls-bottom-footer show-sidebar sidebar-l3" lang="en">
 <head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>QBMS</title>
 
  <link href="TeacherDashboardFiles/all.css" rel="stylesheet">
  <link href="TeacherDashboardFiles/app.css" rel="stylesheet">
 </head>

 <body>
 <%
 if(session.getAttribute("name")==null){%>
	 <c:redirect url="teacherlogin.jsp" />
<%
 }
 %>

  <!-- Wrapper required for sidebar transitions -->
  <div class="st-container">
   
   <!-- Fixed navbar -->
   
   <div class="navbar navbar-size-large navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
     <div class="navbar-header">
      
      <a href="#sidebar-menu" data-toggle="sidebar-menu" class="toggle pull-left visible-xs"><i class="fa fa-ellipsis-v"></i></a>
      
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav">
       <span class="sr-only">Toggle navigation</span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
      </button>
      
      <div class="navbar-brand navbar-brand-primary navbar-brand-logo navbar-nav-padding-left"> Team Charlie  
      </div>
     
     </div>

     <!-- Collect the nav links, forms, and other content for toggling -->
     <div class="collapse navbar-collapse" id="main-nav">
       
       <!--<ul class="nav navbar-nav"> </ul> -->
       
       <ul class="nav navbar-nav navbar-nav-bordered navbar-right">
        <%-- <li><a ><i class="fa fa-user" aria-hidden="true"></i>&nbsp;<span class="badge badge-primary" style ="font-size: 16px;"><%=session.getAttribute("name")%></span></a></li> --%>
        <li><a href="<c:url value="/ShowUser">
					<c:param name="id" value="${sessionScope.userId}" />
				</c:url>" ><i class="fa fa-user" aria-hidden="true"></i>&nbsp;<span class="badge badge-primary" style ="font-size: 16px;"><%=session.getAttribute("name")%></span></a></li>
        &nbsp;<li><a  style= "padding-right: 62px;" href="index.jsp"><i class="fa fa-fw fa-sign-out">Logout</i>&nbsp;</a></li>







       </ul>

     </div>
        <!-- /navbar-collapse -->

    </div>
   </div>
	
  <!-- Sidebar component with st-effect-1 (set on the toggle button within the navbar) -->
    <div class="sidebar left sidebar-size-3 sidebar-offset-0 sidebar-visible-desktop sidebar-visible-mobile sidebar-skin-dark" id="sidebar-menu" data-type="collapse">
     <div data-scrollable>
      
      <div class="sidebar-block">
       <div class="profile">
        <a class="svg"><img style="margin-top: -19px; margin-right: 34px; width: 118%; margin-left: -17px;
    margin-bottom: -15px;" alt="" src="AdminDashboardFiles/qbmslogo.jpg" ></a>          
       </div>
      </div>
        
      <ul class="sidebar-menu">
       <li class="active"> <a href="admindashboard.jsp"> <i class="fa fa-home"></i><span>Dashboard</span></a></li>
       <li class="active"> <a href="AddDepartment"> <i class="fa fa-building-o"></i><span>Create Department</span></a></li>
       <li class="active"> <a href="AddUser"> <i class="fa fa-university"></i><span>Create Teacher</span></a></li>
       <li class="active"> <a href="TeacherAddCourse"> <i class="fa fa-pencil-square"></i><span>Create Course</span></a></li>
       <li class="active"> <a href="index.jsp"> <i class="fa fa-sign-out"></i><span>Logout</span></a></li>
      </div>
    </div>
         
		    
          