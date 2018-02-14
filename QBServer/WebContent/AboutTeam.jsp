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
	   <li ><a href="index.jsp">Home</a></li>
	   <li class="active"><a href="AboutTeam.jsp">Contact </a></li>
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
	  <div class="jumbotron white">
	   <h1 class="bold"><span>Team Charlie</span></h1>
	   <div class="FSColContainer">
                  <div class="FSfirstCol">
                     <p><img src="images/chris.jpeg" alt="Christian" width="85" height="85"><br /><strong>Christian Amadeo Levi</strong><br>504-452-6940<br><a href="mailto:calevi@my.uno.edu">calevi@my.uno.edu</a>
                  </div>
                  <div class="FSthirdCol">
                     <p>Technical Lead, ABC Company</p>
                  </div>
       </div>
       <div class="FSColContainer">
                  <div class="FSfirstCol">
                     <p><img src="images/Raj.jpg" alt="Rajkiran" width="85" height="85"><br /><strong>Raj Kiran Reddy Gunna</strong><br>580-665-7777<br><a href="mailto:rgunna@my.uno.edu">rgunna@my.uno.edu</a>
                  </div>
                  <div class="FSthirdCol">
                     <p>Graduate Professional Assistant, Hotel, Restaurant, Tourism Adm, UNO.</p>
                  </div>
       </div>
       <div class="FSColContainer">
                  <div class="FSfirstCol">
                     <p><img src="images/sefak.jpeg" alt="Sefak" width="85" height="85"><br /><strong>Sefak Kahriman</strong><br>504-434-9997<br><a href="mailto:skahrima@my.uno.edu">skahrima@my.uno.edu</a>
                  </div>
                  <div class="FSthirdCol">
                     <p>Software Developer, XYZ Company</p>
                  </div>
       </div>
              <div class="FSColContainer">
                  <div class="FSfirstCol">
                     <p><img src="images/Jo.jpg" alt="Jyothi" width="85" height="85"><br /><strong>Shiva Jyothi Angala</strong><br>504-541-7721<br><a href="mailto:skahrima@my.uno.edu">sangala@my.uno.edu</a>
                  </div>
                  <div class="FSthirdCol">
                     <p>Graduate Professional Assistant - MANGCollege of LA, Educ &amp; Hum Dev, UNO.</p>
                  </div>
       </div>
              <div class="FSColContainer">
                  <div class="FSfirstCol">
                     <p><img src="images/Unni.jpg" alt="Unni" width="85" height="85"><br /><strong>Unnikrishnan Radhakrishna Pillai</strong><br>504-450-8052<br><a href="mailto:uradhakr@my.uno.edu">uradhakr@my.uno.edu</a>
                  </div>
                  <div class="FSthirdCol">
                     <p>Graduate Professional Assistant - MANG, University Marketing, UNO.</p>
                  </div>
       </div></div>
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