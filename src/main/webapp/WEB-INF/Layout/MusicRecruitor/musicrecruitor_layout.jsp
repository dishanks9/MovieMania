<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>

	<title>Home</title>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>

<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="musicrecruitor-landing.htm" style="font-size:30px">MusicJobs</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="musicrecruitor-postjob.htm"style="font-size:15px">Post Job</a></li>
        <li><a href="musicrecruitor-getpostedjobs.htm" style="font-size:15px">View All Jobs</a></li>
        <li><a href="musicrecruitor-updatedetails.htm" style="font-size:15px">Update Profile</a></li>
        <li><a href="musicrecruitor-search-musician.htm" style="font-size:15px">Connect with musicians</a>
        <li><a href="musicrecruitor-myconnections.htm" style="font-size:15px">My connections</a>
      </ul>

      <ul class="nav navbar-nav navbar-right">
       <li><a href="#">Welcome, ${sessionScope.user.username}</a></li>
        <li><a href="login.htm">Logout</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<tiles:insertAttribute name="body"/>

</body>
</html>
