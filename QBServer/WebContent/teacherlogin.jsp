<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html >
 <head>
  <meta charset="UTF-8">
  <title>QBMS</title>
  <link rel="stylesheet" href="TeacherFiles/reset.css">
  <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
  <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Montserrat:400,700'>
  <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
  <link rel="stylesheet" href="TeacherFiles/stylesss.css">
 </head>

 <body>
  <%
  String message=request.getParameter("message");
  if (message != null &&! message.trim().equals(""))
{  
  %>
  <script type="text/javascript">
    alert('<%= message %>');
</script>

<% } %>
  <div class="container">
   <div class="info">
     <a href="index.jsp" style= "text-decoration: none !important; color:white;"><h1>QBMS</h1><span>Question Bank Management System</span></a> 
   </div>
  </div>

  <div class="form">
   
   <div class="thumbnail">
    <img src="TeacherFiles/54.png" onclick="window.location.href='index.jsp'"/>
   </div>
    
   
    
   <form action="teacherlogin" method="post">
    
    <div class="form-group">
     <div class="form-control-material">
      <input class="form-control" id="username" type="text" name="username" required placeholder="Username">
     </div>
    </div>
    
    <div class="form-group">
     <div class="form-control-material">
      <input class="form-control" id="password" type="password" name="password" required placeholder="Password">
     </div>
    </div>

    <button  name="submit" type="submit">Login<i class="fa fa-fw fa-unlock-alt"></i> </button>
     
    <p class="message">Not registered? <a href="teachersignup.jsp">Create an account</a></p>
	
   </form>
  </div>

  <script src="TeacherFiles/index.js"></script>
  
 </body>
</html>
