<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Login</title>
    
    
    <link rel="stylesheet" href="AdminFiles/reset.css">

    <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
<link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Montserrat:400,700'>
<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="AdminFiles/stylesss.css">

    
    
    
  </head>

  <body>

    
<div class="container">
  <div class="info">
  
    <h1>QBMS</h1><span>Question Bank Management System</span> 
  </div>
</div>
<div class="form">
  <div class="thumbnail"><img src="AdminFiles/54.png"  onclick="window.location.href='index.jsp'" /></div>
 <div > 
 <h3 style="
    margin-bottom: 10px;
    margin-top: -20px;
    color: #2DBE60;
">Admin</h3></div>


  <form  action="AdminLogin" method="post" >
    <input id="username" type="text" name="username" required placeholder="Username"/>
    <input  id="password" type="password" name="password" required placeholder="Password"/>
    <button name="submit" type="submit">Login<i class="fa fa-fw fa-unlock-alt"></i></button> &nbsp;
     <p>
     
     </p>
    <p class="message">Not an Admin? <a href="teacherlogin.jsp">Click Here</a></p>
     </form>
</div>
<video id="video" autoplay="autoplay" loop="loop" poster="polina.jpg">
  <source src="http://andytran.me/A%20peaceful%20nature%20timelapse%20video.mp4" type="video/mp4"/>
</video>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="AdminFiles/index.js"></script>

    
    
    
  </body>
</html>
