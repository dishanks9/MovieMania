<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="table-responsive col-md-10">
	<table class="table table-striped table-bordered">
	<caption>View Jobs</caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Description</th>
				<th>Requirements</th>
				<th>Skills Required</th>
				<th>Hourly Salary</th>
				<th>Additional Requirement</th>
				<th>Minimum Hours</th>
				<th>Duration</th>
				<th>Number of Positions</th>
				<th>Locations</th>
				<th>Band Name</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="job" items="${jobs}">
				<tr>
					<td>${job.jobId}</td>
					<td>${job.jobTitle}</td>
					<td>${job.jobDescription}</td>
					<td>${job.jobRequirements}</td>
					<td>${job.skillsRequired}</td>
					<td>${job.hourlySalary}</td>
					<td>${job.additionalRequirement}</td>
					<td>${job.minimumHours}</td>
					<td>${job.duration}</td>
					<td>${job.noOfPositions}</td>
					<td>${job.location}</td>
					<td>${job.companyName}</td>	
					<td><a href="musicrecruitor-viewjobapplications.htm?jobId=${job.jobId}" class="btn btn-success">View Applications</a></td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>