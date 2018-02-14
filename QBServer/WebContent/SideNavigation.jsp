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
      <!--  <a class="svg"><img style="margin-top:35px;margin-right:35px;" alt="" src="AdminDashboardFiles/logo.PNG" ></a> -->
     
      </div>
     
     </div>

     <!-- Collect the nav links, forms, and other content for toggling -->
     <div class="collapse navbar-collapse" id="main-nav">
       
       <!--<ul class="nav navbar-nav"> </ul> -->
       
       <ul class="nav navbar-nav navbar-nav-bordered navbar-right">
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
       <li class="active"> <a href="teacherdashboard.jsp"> <i class="fa fa-bar-chart-o"></i><span>Dashboard</span></a></li>
       <div class="panel-group" id="accordion">
     



    
    
    <div class="panel panel-default" style="margin-bottom:-5px;">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2"><i class="fa fa-file-o"></i>Course Menu</a>
        </h4>
      </div>	
      <div id="collapse2" class="panel-collapse collapse ">
        <div class="panel-body">

 <li class="hasSubmenu"> 
         <a href="<c:url value="/TeacherAddCourse" />" ><i class="fa fa-book"></i><span>Add Course</span></a>
         <ul id="course-menu">
      	
	   <button style=" margin-top: 7px; margin-bottom: -7px;" name="courseview" type="submit" value="Course View" class="btn btn-inverse" onclick="window.location.href='TeacherCourseView'"><i class="fa fa-list-alt"></i><span style="text-transform: none;">View All Courses</span></button></form></li>
             	
          <!--  <li><form action="" method="post"> <input type="hidden"  name="tid" value=""/>
		   <button name="uploadlecture" type="submit" value=" Assign Leacture " href="AddCourse.jsp" class="btn btn-inverse"><span>Add Courses</span></form></li>   
             	
          <li><form action="" method="post"> <input type="hidden"  name="tid" value= ""/>
		   <button name="viewlectureofcourse" type="submit" value=" Lecture View " href="ShowQuestion" class="btn btn-inverse">Edit Courses</span></form></li>  -->          			  
       	 </ul>
        </li>

</div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3"><i class="fa fa-question-circle"></i>Q&A Menu</a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body">

    <li class="hasSubmenu">

       
         <a href="<c:url value="/CourseAddQuestion"/>"><i class="fa fa-edit"></i><span>Add Q&A</span></a>         
		  <ul id="quiz-menu"> 
		   <!-- <li><form action="" method="post"> <input type="hidden"  name="tid" value= ""/> -->
		   
            <button name="questionview" type="submit" value="Question View" class="btn btn-inverse" onclick="window.location.href='TeacherQuestionView'"><i class="fa fa-list-alt"></i><span style="text-transform: none;">View All Q&A</span></button></form></li>  	

		</ul>
       </li>
</div>
      </div>
<!--     
<div class="panel panel-default" style="margin-bottom:-5px;">
 <div class="panel-heading">
  <h4 class="panel-title">
   <a data-toggle="collapse" data-parent="#accordion" href="#collapse4"><i class="fa fa-file-o"></i>Question Paper Menu</a>
  </h4>
 </div>	
 
 <div id="collapse4" class="panel-collapse collapse ">
  <div class="panel-body">
   <li class="hasSubmenu"> 
    
    <a href="<c:url value="/TeacherAddCourse" />" ><i class="fa fa-book"></i><span>Build QuestionPaper</span></a>
    <ul id="course-menu">
     <button name="courseview" type="submit" value="Course View" class="btn btn-inverse" onclick="window.location.href='TeacherCourseView'"><i class="fa fa-list-alt"></i><span style="text-transform: none;">Print Question Paper</span></button></form></li>
    </ul>
   </li>
  </div>
 </div>
</div>
-->    
      
    </div>    

    <li class="active" style="margin-left: 18px; font-size: 16px;"> <a href="index.jsp"> <i class="fa fa-sign-out"></i><span>Logout</span></a></li>
  </div>
      </div>
    </div>
         
		    
          