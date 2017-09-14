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
				placeholder="Search for the recruitor to connect">
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<table class="table" id="table">
				<thead>
					<tr>
						<th>Recruitor ID</th>
						<th>Recruitor First Name</th>
						<th>Recruitor Last Name</th>				
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mr" items="${mr}">
						<tr>
							<td>${mr.userId}</td>
							<td>${mr.firstName}</td>
							<td>${mr.lastName}</td>
							<td><a href="musician-viewrecruitordetails.htm?recId=${mr.userId}">View Details</a></td>
						</tr>
					</c:forEach>
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
