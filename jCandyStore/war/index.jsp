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
              <li class="active"><a href="#">Home</a></li>
              <li><a href="about.jsp">About</a></li>
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
            <p style="font-style:italic">... because pets are so XXth century !</p>
<p>
              <!-- <img src="./assets/img/ManyCandies.jpg" alt="image from http://www.flickr.com/photos/siderean/423705391/"/> -->
               <div class="row-fluid">
                  <div class="span4">
                    <p>
                      <a href="/productView?cat=ACIDULE" title="Acidulés">
                        <!-- <img src="./assets/img/categories/acidules_icon.jpg"> -->
                        Acidulés
                      </a>
                    </p>
                  </div>
                  <div class="span4">
                    <p>
                      <a href="/productView?cat=BONBON" title="Bonbons">
                        <!-- <img src="./assets/img/categories/bonbon_icon.jpg"> -->
                        Bonbons
                      </a>
                    </p>
                  </div>
                  <div class="span4">
                    <p>
                      <a href="/productView?cat=CARAMEL" title="Caramels">
                        <!-- <img src="./assets/img/categories/caramel_icon.jpg"> -->
                        Caramels
                      </a>
                    </p>
                  </div>
<!--                   <div class="span4">
                    <p>
                      <a href="/productView?cat=REGLISSE" title="Réglisses">
                        <img src="./assets/img/categories/reglisse_icon.jpg">
                        Réglisses
                      </a>
                    </p>
                  </div>                  --> 
                </div>
               <div class="row-fluid">
                  <div class="span4">
                    <p>
                      <a href="/productView?cat=CHOCOLAT" title="Chocolats">
                        <!-- <img src="./assets/img/categories/chocolat_icon.jpg"> -->
                        Chocolats
                      </a>
                    </p>
                  </div>
                  <div class="span4">
                    <p>
                      <a href="/productView?cat=GELIFIE" title="Gélifiés">
                        <!-- <img src="./assets/img/categories/gelifie_icon.jpg"> -->
                        Gélifiés
                      </a>
                    </p>
                  </div>
                  <div class="span4">
                    <p>
                      <a href="/productView?cat=PATISSERIE" title="Patisseries">
                        <!-- <img src="./assets/img/categories/patisseries_icon.jpg"> -->
                        Patisseries
                      </a>
                    </p>
                  </div>
<!--                   <div class="span4">
                    <p>
                      <a href="/productView?cat=SUCETTE" title="Sucettes">
                        <img src="./assets/img/categories/sucette_icon.jpg">
                        Sucettes
                      </a>
                    </p>
                  </div> -->
                </div>                
            </p>
            <p><a class="btn btn-primary btn-large">Start shopping! &raquo;</a></p>
          </div>
          <div class="row-fluid">
            <div class="span4">
              <h2>Jelly Bean</h2>
              <p><img src="./assets/img/candies/_JellyBeans.png" align="left" hspace="5"/>
              Jelly beans are a small bean-shaped type of confectionery with a hard candy shell and a gummy
              interior which come in a wide variety of flavors. The confection is primarily made of sugar. </p>
              <p><a class="btn" href="/productView?id=GE-PO-05">Buy now! &raquo;</a></p>
            </div><!--/span-->
            <div class="span4">
              <h2>Caramel</h2>
              <p><img src="./assets/img/candies/_Caramel.png" align="left" hspace="5"/>
              Beige to dark-brown confectionery product made by heating any of a variety of sugars. It is used as a
              flavoring in puddings and desserts, as a filling in bonbons, and as a topping for ice cream and coffee.</p>
              <p><a class="btn" href="/productView?id=CA-RT-02">Buy now! &raquo;</a></p>
            </div><!--/span-->
            <div class="span4">
              <h2>Roudoudou</h2>
              <p><img src="./assets/img/candies/_Roudoudou.png" align="left" hspace="5"/>
              Le roudoudou est une confiserie en sucre cuit parfumée, coulée dans une petite coquille en plastique.
              A l'origine le contenant était une petite boite en bois style boite à camembert miniature. </p>
              <p><a class="btn" href="/productView?id=SU-PO-03">Buy now! &raquo;</a></p>
            </div><!--/span-->
          </div><!--/row-->
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
    <script src="./assets/js/bootstrap-transition.js"></script>
    <script src="./assets/js/bootstrap-alert.js"></script>
    <script src="./assets/js/bootstrap-modal.js"></script>
    <script src="./assets/js/bootstrap-dropdown.js"></script>
    <script src="./assets/js/bootstrap-scrollspy.js"></script>
    <script src="./assets/js/bootstrap-tab.js"></script>
    <script src="./assets/js/bootstrap-tooltip.js"></script>
    <script src="./assets/js/bootstrap-popover.js"></script>
    <script src="./assets/js/bootstrap-button.js"></script>
    <script src="./assets/js/bootstrap-collapse.js"></script>
    <script src="./assets/js/bootstrap-carousel.js"></script>
    <script src="./assets/js/bootstrap-typeahead.js"></script>

  </body>
</html>