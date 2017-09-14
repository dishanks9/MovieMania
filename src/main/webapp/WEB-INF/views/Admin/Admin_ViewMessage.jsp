<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
<c:when test="${empty message}">
<h3>No messages found</h3>
</c:when>
<c:otherwise>
<div class="table-responsive col-md-10">
	<table class="table table-striped table-bordered">
	<caption>View Messages</caption>
		<thead>
			<tr>
				<th>Message ID</th>
				<th>Recruitor Name</th>
				<th>Message</th>
				
				
			</tr>
			</thead>
			<tbody>
			<c:forEach var="message" items="${message}">
				<tr>
					<td>${message.id}</td>
					<td>${message.musicRecruitor.firstName}</td>
					<td>${message.message}</td>
					<td><a href="admin-message-details.htm?msgId=${message.id}">View Details</a>
				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</c:otherwise>
</c:choose>