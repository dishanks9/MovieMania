<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:choose>
<c:when test="${empty participants}">
<h3>No participants found</h3>
</c:when>
<c:otherwise>
<div class="container">

	<div class="row">
		<div class="col-lg-4 col-lg-offset-4">
		<h4>Total participants are: ${fn:length(participants)}</h4>
			<input type="search" id="search" value="" class="form-control"
				placeholder="Search for the musician">
		</div>
	</div>
	<div class="table-responsive col-md-10">
		<table class="table table-striped table-bordered" id="table">
			<caption>View Participants</caption>
			<thead>
				<tr>
					<th>Musicians ID</th>
					<th>Musicians Name</th>
					<th>Musicians Speciality</th>
					<th>Musicians Past Company/Band Name</th>
					<th>musicians contact number</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="participant" items="${participants}">
					<tr>
						<td>${participant.userId}</td>
						<td>${participant.firstName}</td>
						<td>${participant.instrumentSpeciality}</td>
						<td>${participant.pastBand}</td>
						<td>${participant.contactNumber}</td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</c:otherwise>
</c:choose>
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