<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<div class="container">

	<div class="row">
		<div class="col-lg-4 col-lg-offset-4">
			<input type="search" id="search" value="" class="form-control"
				placeholder="Search for the jobs to apply">
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<table class="table" id="table">
				<thead>
					<tr>
						<th>Musician ID</th>
						<th>Musician First Name</th>
						<th>Musician Last Name</th>
						<th>Past experience</th>
						<th>Instrument speciality</th>
						<th>Past Band</th>
						<th>Contact Number</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td>${mr.userId}</td>
							<td>${mr.firstName}</td>
							<td>${mr.lastName}</td>
							<td>${mr.pastYearsExperience}</td>
							<td>${mr.instrumentSpeciality}</td>
							<td>${mr.pastBand}</td>
							<td>${mr.contactNumber}</td>
							<c:set var="yes" scope="session" value="${yes}"/>
							<c:choose>
							<c:when test="${ yes == 'no'}">
							<td><a href="musicrecruitor-emailmusician.htm?recId=${mr.userId}">Send message</a></td>
							</c:when>
							<c:otherwise>
							<td><a href="musicrecruitor-connectmusician.htm?recId=${mr.userId}">Connect</a></td>
							</c:otherwise>
							</c:choose>
							
							


							
						</tr>
					
				</tbody>
			</table>
			<hr>
		</div>
	</div>
</div>

<script
	src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>
<script>
	$(function() {
		$('#table').searchable({
			striped : true,
			oddRow : {
				'background-color' : '#f5f5f5'
			},
			evenRow : {
				'background-color' : '#fff'
			},
			searchType : 'fuzzy'
		});
	});
</script>
