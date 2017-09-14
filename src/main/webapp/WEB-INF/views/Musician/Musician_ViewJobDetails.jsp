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
						<c:choose>
							<c:when test="${applied=='applied'}">
								<h3>You have already applied to this job.</h3>
							</c:when>
							<c:otherwise>
								<td><a href="#" data-toggle="modal" data-target="#myModal">Apply</a>
									<div class="modal fade" id="myModal" role="dialog">
										<div class="modal-dialog">

											<!-- Modal content-->
											<div class="modal-content">
												<div class="container" style="margin-top: 40px">
													<div class="row">
														<div class="col-sm-6 col-md-4 col-md-offset-1">
															<div class="panel panel-default">
																<div class="panel-heading">
																	<strong> Resume</strong>
																</div>
																<div class="panel-body">
																	<form role="form"
																		action="musician-applyToJob.htm?id=${job.jobId}"
																		method="POST" enctype="multipart/form-data"
																		onsubmit="return validate();">
																		<fieldset>
																			<br>
																			<div data-ng-app="" class="row">
																				<div class="col-sm-12 col-md-10  col-md-offset-1 ">
																					<div class="form-group"></div>
																					<div class="form-group">
																						<select class="form-control"
																							name="res">
																							<option value="new">Upload new resume</option>
																							<c:if test="${resume=='yes'}"><option value="old">Use old resume</option></c:if>
																							
																							
																						</select>
																					</div>
																					<div class="input-group">
																					
																						<a href="download" target="_blank">view
																							previous resume</a>
																					</div>


																					<label for="file" class="control-label">Select
																						Resume:</label> <input type="file" name="file"
																						class="form-control" />


																					<div class="form-group">
																						<input type="submit"
																							class="btn btn-lg btn-primary btn-block"
																							value="Upload">
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

	function validate() {
		if (!this.form.checkbox.checked) {
			alert('Please select the type of resume');
			return false;
		}
		return true;
	}
</script>
