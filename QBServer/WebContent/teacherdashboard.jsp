<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="SideNavigation.jsp"/> 
<html class="st-layout ls-top-navbar-large ls-bottom-footer show-sidebar sidebar-l3" lang="en">
 <head>
  <title>Welcome</title>
  <link href="TeacherDashboardFiles/all.css" rel="stylesheet">
  <link href="TeacherDashboardFiles/app.css" rel="stylesheet">
 </head>
 <body>
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
        
        <div class="item col-xs-12 col-lg-6">
         <div class="panel panel-default paper-shadow" data-z="0.5">
          <div class="panel-heading">
           <h4 class="text-headline margin-none">Courses</h4>
          </div>
          <ul class="list-group">
                    		<li class="list-group-item media v-middle">
                      			<div class="media-body">
                      			  <a class="text-subhead list-group-link">
                      			  	<p class="text-subhead text-light">Shows all the Uploaded Courses with its Name, Number, Department, Credits and provides an option to Show and Edit individual Course</p>
                      			  </a>
                      			</div>
          </ul>
          
	      <div class="panel-footer">
           <a  data-animated href="<c:url value="/TeacherCourseView" />"class="btn btn-success paper-shadow relative" data-z="0" data-hover-z="1" data-animated > View Courses</a>
          </div>
         </div>
        </div>
          
        
       </div>
      </div>
     </div>
	</div>
   </div>
		
    <footer class="footer">
      <strong>QBMS</strong>  &copy; Copyright 2016
    </footer>
    <!-- // Footer -->