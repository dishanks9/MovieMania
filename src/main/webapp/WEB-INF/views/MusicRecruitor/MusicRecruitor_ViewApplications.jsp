<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<div class="col-lg-4 col-lg-offset-4">
			<input type="search" id="search" value="" class="form-control"
				placeholder="Search for the jobs to apply">
		</div>
	</div>
	<div class="table-responsive col-md-10">
		<table class="table table-striped table-bordered" id="table">
			<caption>View Applicants</caption>
			<thead>
				<tr>
					<th>Musicians ID</th>
					<th>Musicians Name</th>
					<th>Musicians Speciality</th>
					<th>Musicians Past Company/Band Name</th>
					<th>Musicians contact number</th>
					<th>Musicians Email ID</th>
					<th>Resume</th>
					<th>Email</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="job" items="${applications}">
					<tr>
						<td>${job.user.userId}</td>
						<td>${job.user.firstName}</td>
						<td>${job.user.instrumentSpeciality}</td>
						<td>${job.user.pastBand}</td>
						<td>${job.user.contactNumber}</td>
						<td>${job.user.emailId}</td>
						<td><a href="downloadRec?id=${job.user.userId}" target="_blank">Resume</a></td>
						<td><a href="#" data-toggle="modal" data-target="#myModal">Email</a>
						<div class="modal fade" id="myModal" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="container" style="margin-top: 40px">
												<div class="row">
													<div class="col-sm-6 col-md-4 col-md-offset-1">
														<div class="panel panel-default">
															<div class="panel-heading">
																<strong> Applicant status</strong>
															</div>
															<div class="panel-body">
																<form role="form" action="musicrecruitor-sendemail.htm"
																	method="GET">
																	<fieldset>
																		<br>
																		<div class="row">
																			<div class="col-sm-12 col-md-10  col-md-offset-1 ">
												
																				<div class="form-group">
																					<div class="input-group">

																						<input name="status" placeholder="change status to">
																					</div>
																				</div>
																				<input type="hidden" name="jobId" value="${job.job.jobId}"/>
																				<input type="hidden" name="userId" value="${job.user.userId}"/>
																			

																				<div class="form-group">
																					<input type="submit"
																						class="btn btn-lg btn-primary btn-block"
																						value="Change and send email">
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
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div></td>
					</tr>
				</c:forEach>
				
								
			</tbody>
		</table>
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