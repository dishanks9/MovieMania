<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<div class="jumbotron">
		<h1>Welcome to MusicJobs.com .</h1>
		<p>Explore the different features where you can search for your
			favorite jobs and hunt for the musician who can fit for your
			requirement.</p>
	</div>
	<p>This is some text.</p>
	<p>This is another text.</p>
</div>
<br>
<div class="container">
	<div class="panel panel-success">
		<div class="panel-heading"><h3>Hello Musician.</h3></div>
		<div class="panel-body"><h3>It's not an accident that musicians
			become musicians and engineers become engineers: it's what they're
			born to do. If you can tune into your purpose and really align with
			it, setting goals so that your vision is an expression of that
			purpose, then life flows much more easily.</h3></div>
	</div>
</div>
<div id="myModal1" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">${messageType}</h4>
      </div>
      <div class="modal-body">
        <p>${messageDetails}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="goBack()">Close</button>
      </div>
    </div>

  </div>
</div>
<script type="text/javascript">
    $(window).on('load',function(){
        $('#myModal1').modal('show');
    });
    function goBack() {
        window.history.back();
    }
</script>