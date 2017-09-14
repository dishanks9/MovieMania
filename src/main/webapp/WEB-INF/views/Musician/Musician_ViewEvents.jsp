<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<div class="container">
    
    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">
            <input type="search" id="search" value="" class="form-control" placeholder="Search for the events to register">
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table class="table" id="table">
                <thead>
			<tr>
				<th>Event ID</th>
				<th>Event Name</th>
				<th>Event Description</th>
				<th>Event Location</th>
				
			</tr>
			</thead>
			<tbody>
			<c:forEach var="event" items="${events}">
				<tr>
					<td>${event.eventId}</td>
					<td>${event.eventName}</td>
					<td>${event.eventDetails}</td>
					<td>${event.location}</td>
					<td><a href="musician-register-event.htm?eventId=${event.eventId}">Register</a>			
				</tr>
			</c:forEach>
		</tbody>
            </table>
            <hr>
        </div>
    </div>
</div>

<script src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>
<script>$(function () {
    $( '#table' ).searchable({
        striped: true,
        oddRow: { 'background-color': '#f5f5f5' },
        evenRow: { 'background-color': '#fff' },
        searchType: 'fuzzy'
    });
});
</script>
