<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="table-responsive col-md-10">
	<table class="table table-striped table-bordered">
	<caption>View Events</caption>
		<thead>
			<tr>
				<th>Event ID</th>
				<th>Event Name</th>
				<th>Event Details</th>
				<th>Event Location</th>
				
				
			</tr>
			</thead>
			<tbody>
			<c:forEach var="event" items="${eventList}">
				<tr>
					<td>${event.eventId}</td>
					<td>${event.eventName}</td>
					<td>${event.eventDetails}</td>
					<td>${event.location}</td>
					<td><a href="admin-view-participants.htm?eventId=${event.eventId}">View Participants</a>
				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>