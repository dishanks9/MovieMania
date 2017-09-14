<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<a href="report.xls">View excel</a>
<div class="table-responsive col-md-10">
	<table class="table table-striped table-bordered">
	<caption>View Jobs</caption>
		<thead>
			<tr>
				<th>Application ID</th>
				<th>Job ID</th>
				<th>Date Applied</th>
				<th>Status</th>
				
			</tr>
			</thead>
			<tbody>
			<c:forEach var="job" items="${applications}">
				<tr>
					<td>${job.id}</td>
					<td>${job.job.jobId}</td>
					<td>${job.dateApplied}</td>
					<td>${job.jobStatus}</td>
					<td><a href="musician-viewjobdetails.htm?jobId=${job.job.jobId}">View Job details</a>
				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>