<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="AdminSideNavigation.jsp"/>  

<html class="st-layout ls-top-navbar-large ls-bottom-footer show-sidebar sidebar-l3" lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Welcome</title>
  <link href="AdminDashboardFiles/all.css" rel="stylesheet">

  
  <link href="AdminDashboardFiles/app.css" rel="stylesheet">

</head>

<body>

  <!-- Wrapper required for sidebar transitions -->
  <div class="st-container">

	
	
	
	
	
	
	
	
	
	
	
	

    <!-- sidebar effects OUTSIDE of st-pusher: -->
    <!-- st-effect-1, st-effect-2, st-effect-4, st-effect-5, st-effect-9, st-effect-10, st-effect-11, st-effect-12, st-effect-13 -->
	
	
	
    <!-- content push wrapper -->
    <div class="st-pusher" id="content">

      <!-- sidebar effects INSIDE of st-pusher: -->
      <!-- st-effect-3, st-effect-6, st-effect-7, st-effect-8, st-effect-14 -->

      <!-- this is the wrapper for the content -->
      <div class="st-content">

        <!-- extra div for emulating position:fixed of the menu -->
        <div class="st-content-inner padding-none">

          <div class="container-fluid">

            <div class="page-section">
              <h1 class="text-display-1">Dashboard</h1>
            </div>

            <div class="row" data-toggle="isotope">
             
			<div class="row" data-toggle="isotope">
				<div class="item col-xs-12 col-lg-6">
                	<div class="panel panel-default paper-shadow" data-z="0.5">
                  		<div class="panel-heading">
                    		<h4 class="text-headline margin-none">Departments</h4>
                    	</div>

                  		<ul class="list-group">
                    		<li class="list-group-item media v-middle">
                      			<div class="media-body">
                      			  <a class="text-subhead list-group-link">
                      			  	<p class="text-subhead text-light">Shows all the Uploaded Departments with the Department Name, Abbrevation and provides an option to Show and Edit individual Department</p>
                      			  </a>
                      			</div>
                  		</ul>

                  		<div class="panel-footer text-right">
	           				<a  data-animated href="<c:url value="/DepartmentsView" />"class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated > View Departments</a>
                  		</div>
                	</div>
              	</div>
			</div>
			
			<div class="row" data-toggle="isotope">
				<div class="item col-xs-12 col-lg-6">
                  		<div class="panel panel-default paper-shadow" data-z="0.5">
                   		<div class="panel-heading">
                    		<h4 class="text-headline margin-none">Courses</h4>
                    		</div>

                  		<ul class="list-group">
                    		<li class="list-group-item media v-middle">
                      		<div class="media-body">
								<a class="text-subhead list-group-link">
									<p class="text-subhead text-light">Shows all the Uploaded Courses with the Course Name, Number, Department, Credits and provides an option to Show and Edit individual Course</p>
                   				</a>
                      		</div>
                  		</ul>

                  		<div class="panel-footer text-right">
 	           				<a  data-animated href="<c:url value="/AdminCourseView" />"class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated > View Courses</a>
                  		</div>
                		</div>
              		</div>
              	</div>
           
              	
           <div class="row" data-toggle="isotope">
              <div class="item col-xs-12 col-lg-6">
                <div class="panel panel-default paper-shadow" data-z="0.5">
                  <div class="panel-heading">
                    <h4 class="text-headline margin-none">Teachers</h4>
				  </div>
			
                  <ul class="list-group">
                    <li class="list-group-item media v-middle"> 
                      <div class="media-body">
                     		<p class="text-subhead text-light">Shows all the Teachers with the Teacher FirstName, LastName, UserName and provides an option to Show and Edit individual Teacher</p>
                      </div>
     			  </ul>
				  	       
                  <div class="panel-footer text-right">
                    <a href="<c:url value="/UsersView" />" class="btn btn-white paper-shadow relative" data-z="0" data-hover-z="1" data-animated>View Teachers</a>
                  </div>
                </div>
              </div>
        	</div>
        </div>	
			
        	<!-- /st-content-inner -->
		</div>
      	</div>
      	<!-- /st-content -->
	</div>
    </div>
    <!-- /st-pusher -->
    </div>
    </body>
<!-- Footer -->
    <footer class="footer">
      <strong>QBMS</strong>  &copy; Copyright 2016
    </footer>
    <!-- // Footer -->