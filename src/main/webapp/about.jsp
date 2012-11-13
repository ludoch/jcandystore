<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>jCandyStore, because pets are so XXth century !</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Alexis">

    <!-- Le styles -->
    <link href="./assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link href="./assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="./assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="./assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="./assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="./assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="./assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <body> 
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">jCandyStore</a>
          <div class="btn-group pull-right">
            
            <%
            UserService userService = UserServiceFactory.getUserService();
            User user = userService.getCurrentUser();
            if (user != null) {
               pageContext.setAttribute("user", user);
            %>
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-user"></i> ${user}
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/cart">Shopping Cart</a></li>
                    <li class="divider"></li>
                    <li><a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign Out</a></li>
                </ul>
            <%
              } else {
            %>
                <a class="btn dropdown-toggle" href="<%= userService.createLoginURL(request.getRequestURI()) %>">
                    <i class="icon-user"></i> Sign In
                </a>
            <%
            }
            %>

          </div>
          <div class="nav-collapse">
            <ul class="nav">
              <li><a href="/">Home</a></li>
              <li class="active"><a href="#about">About</a></li>
              <!-- 
              <li><a href="#contact">Contact</a></li>
               -->
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
          <div class="hero-unit">
            <h1>Welcome to jCandyStore!</h1>
            <h2>This frankenstein* demo application was built with the following <a href="http://cloud.google.com/">Google Cloud</a> services :</h2>
            <ul>
                <li><a href="http://cloud.google.com/appengine">Google AppEngine</a></li>
                <li><a href="https://developers.google.com/appengine/downloads.html#Google_App_Engine_SDK_for_Java">Google Eclipse SDK</a></li> 
                <li><a href="https://developers.google.com/cloud-sql/">CloudSQL</a></li> 
                <li><a href="http://www.youtube.com/watch?v=NU_wNR_UUn4">Cloud Endpoints</a></li> 
                <li>...</li>
            </ul>

			<h2>and standard server-side Java APIs :</h2>
			<ul>
				<li>Servlet HttpSession</li>
				<li>JPA 2.0</li>
				<li>JAX-RS 1.1</li>
				<li>JSF 2.0</li>
				<li>...</li>
			</ul>

			<p>
			<small>*: quickly hacked together to showcase support for those Java APIs, not an architectural blueprint by any mean.</small>
			</p> 

          </div>
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Google Bistro 2012</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <script src="./assets/js/jquery.js"></script>
<!--     <script src="./assets/js/bootstrap-transition.js"></script>  -->
    <script src="./assets/js/bootstrap-alert.js"></script>
    <script src="./assets/js/bootstrap-modal.js"></script>
    <script src="./assets/js/bootstrap-dropdown.js"></script>
<!--    <script src="./assets/js/bootstrap-scrollspy.js"></script>  -->
    <script src="./assets/js/bootstrap-tab.js"></script>
    <script src="./assets/js/bootstrap-tooltip.js"></script>
    <script src="./assets/js/bootstrap-popover.js"></script>
    <script src="./assets/js/bootstrap-button.js"></script>
    <script src="./assets/js/bootstrap-collapse.js"></script>
    <script src="./assets/js/bootstrap-carousel.js"></script>
    <script src="./assets/js/bootstrap-typeahead.js"></script>

  </body>
</html>