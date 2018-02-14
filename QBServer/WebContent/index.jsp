<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en" class=" js no-touch">
 
 <head>
  <title>QBMS</title>
   
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="IndexFiles/bootstrap.min.css">
   <link href='https://fonts.googleapis.com/css?family=Open+Sans:300,600|Raleway:600,300|Josefin+Slab:400,700,600italic,600,400italic' rel='stylesheet' type='text/css'>
   <link href="IndexFiles/font-awesome.css" rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="IndexFiles/slick.css"/>
   <link rel="stylesheet" type="text/css" href="IndexFiles/slick-theme.css"/>
   <link rel="stylesheet" type="text/css" href="IndexFiles/style.css">
 </head>
 
 <body>
  <!--HEADER START-->
  <row class="main-navigation">
   <nav class="navbar navbar-default">
	<div class="container">
	 
	 <div class="navbar-header">
	  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	   <span class="icon-bar"></span>
	   <span class="icon-bar"></span>
	   <span class="icon-bar"></span>
	  </button>
	  <a class="navbar-brand" href="index.jsp"><i class="fa fa-fw fa-graduation-cap"></i> QBMS</a>
	 </div>
	  
	 <div class="collapse navbar-collapse" id="myNavbar">
	  <ul class="nav navbar-nav navbar-right" style="padding-top: 5px;">
	   <li class="active"><a href="index.jsp">Home</a></li>
	   <li ><a href="AboutTeam.jsp">Contact </a></li>
	  </ul>
	 </div>
    </div>
   
   </nav>
  </row>
  <!--HEADER END-->

  <!--BANNER START-->
  <div id="banner" class="section-padding">
   <div class="container">
	<div class="row">
	 <div class="jumbotron">
	  <h1 class="small">Welcome To <span class="bold">QBMS</span></h1>
	  <p class="big">Question Bank Management System</p>
	   <!--   <a href="adminlogin.jsp" class="btn btn-banner">Admin Sign In<i class="fa fa-send"></i></a> -->
	  <a href="teacherlogin.jsp" class="btn btn-banner">Sign In<i class="fa fa-send"></i></a>
      <a href="teachersignup.jsp" class="btn btn-banner">Sign Up<i class="fa fa-user-plus"></i></a>
	 </div>
	</div>
   </div>
  </div>
  <!--BANNER END-->

  <!--CTA1 START-->
  <div class="cta-1">
   <div class="container">
	<div class="row text-center white">
	 <h1 class="cta-title">Agile Engineering!!</h1>
	 <p class="cta-sub-title">Question Bank Management System Project</p>
	</div>
   </div>
  </div>
  <!--CTA1 END-->
 
  <!--FOOTER START-->
  <div class="footer-bottom">
   <div class="container">
	<div style="visibility: visible; animation-name: zoomIn;" class="col-md-12 text-center wow zoomIn">
	 <div class="footer_copyright">
	 <p> Copyright © Team 'C' All Rights Reserved.</p>					
	 </div>
    </div>
   </div>
  </div>
  <!--FOOTER END--> 
    
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="IndexFiles/jquery.mixitup.js" type="text/javascript"></script>
  <script type="text/javascript" src="IndexFiles/slick.min.js"></script>
	
  <!--for smooth scrolling-->
  <script>
	 $('a').click(
	 function()
	 {
      $('html, body').animate({ scrollTop: $( $.attr(this, 'href') ).offset().top}, 500);
      return false;
     }
     );
  </script>
  	
  <script type="text/javascript">
     $(function()
     {
      // Instantiate MixItUp:
      $('#Container').mixItUp();
     });

	 $('.autoplay').slick({
     dots: true,
     infinite: false,
     speed: 300,
     slidesToShow: 2,
     slidesToScroll: 3,
     responsive: [
     {
      breakpoint: 1024,
      settings: 
      {
       slidesToShow: 3,
       slidesToScroll: 3,
       infinite: true,
       dots: true
      }
     },
     {
      breakpoint: 600,
      settings: 
      {
       slidesToShow: 2,
       slidesToScroll: 2
      }
     },
     {
      breakpoint: 480,
      settings:
      {
       slidesToShow: 1,
       slidesToScroll: 1
      }
     }

    // You can unslick at a given breakpoint now by adding:
    // settings: "unslick"
    // instead of a settings object
    ]
    });
  </script>
 </body>
</html>