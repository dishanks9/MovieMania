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
</head>
<style>

</style>
<body background="${pageContext.servletContext.contextPath}/resources/home.jpg">
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
      <a class="navbar-brand" href="login.htm" style="font-size:30px">MusicJobs</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" data-toggle="modal" data-target="#myModal" style="font-size:20px">Login</a>
        <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Sign in to continue</strong>
					</div>
					<div class="panel-body">
						<form role="form" action="home.htm" method="POST">
							<fieldset>
								<br>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span> 
												<input class="form-control" placeholder="Username" name="username" type="text" id="username" required autofocus>
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-lock"></i>
												</span>
												<input class="form-control" placeholder="Password" name="password" type="password" required value="">
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-thumbs-up"></i>
												</span>
												<select class="form-control" name="role" id="aioConceptName">
													<option value="admin">admin</option>
													<option value="musicRecruitor">Music Recruitor</option>
													<option value="musician">Musician</option>
												</select>
												
											</div>
										</div>
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in" id="activate">
										</div>
										<div class="form-group">
											<a href="forgotPassword.htm">Forgot your password?</a>
										</div>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					
                </div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
      </li></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="font-size:20px">Register <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="musician-signup.htm" style="font-size:15px">Register as a musician</a></li>
            <li><a href="musicrecruitor-signup.htm" style="font-size:15px">Register as a music recruitor</a></li>
            
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<tiles:insertAttribute name="body" />
</body>
</html>