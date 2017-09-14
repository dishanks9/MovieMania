<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-10 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Post a Job</strong>
					</div>
					<div class="panel-body">
						<form:form  commandName="jobs" action="musicrecruitor-postjob.htm" method="post">
							<fieldset>
								<br>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
										<label for="jobTitle">Job Title*: </label>
												<form:input path="jobTitle" class="form-control" placeholder="jobTitle" name="jobTitle" id="jobTitle" type="text"/>
												<font color="red"><form:errors path="jobTitle" /></font>
										</div>
										<div class="form-group">
										<label for="jobDescription">Job Description*: </label>		
												<form:input path="jobDescription" class="form-control" placeholder="jobDescription" name="jobDescription" id="jobDescription" type="text"/>
												<font color="red"><form:errors path="jobDescription" /></font>
										</div>
										<div class="form-group">
										<label for="jobRequirements">Job Requirements*: </label>		
												<form:input path="jobRequirements" class="form-control" placeholder="jobRequirements" name="jobRequirements" id="jobRequirements" type="text"/>
												<font color="red"><form:errors path="jobRequirements" /></font>
										</div>
										<div class="form-group">
										<label for="skillsRequired">Skills required*: </label>		
												<form:input path="skillsRequired" class="form-control" placeholder="skillsRequired" name="skillsRequired" id="skillsRequired" type="text"/>
												<font color="red"><form:errors path="skillsRequired" /></font>
										</div>
										<div class="form-group">
										<label for="hourlySalary">Hourly salary*: </label>		
												<form:input path="hourlySalary" class="form-control" placeholder="hourlySalary" name="hourlySalary" id="hourlySalary" type="number" min="0"/>
												<font color="red"><form:errors path="hourlySalary" /></font>
										</div>
										<div class="form-group">
										<label for="additionalRequirement">Additional Requirement*: </label>		
												<form:input path="additionalRequirement" class="form-control" placeholder="additionalRequirement" name="additionalRequirement" id="additionalRequirement" type="text"/>
												<font color="red"><form:errors path="additionalRequirement" /></font>
										</div>
										<div class="form-group">
										<label for="minimumHours">Minimum hours per week*: </label>		
												<form:input path="minimumHours" class="form-control" placeholder="minimumHours" name="minimumHours" id="minimumHours" type="number" min="0"/>
												<font color="red"><form:errors path="minimumHours" /></font>
										</div>
										<div class="form-group">
										<label for="duration">Job total duration*: </label>		
												<form:input path="duration" class="form-control" placeholder="duration" name="duration" id="duration" type="number" min="0"/>
												<font color="red"><form:errors path="duration" /></font>
										</div>
										<div class="form-group">
										<label for="noOfPositions">No of openings*: </label>		
												<form:input path="noOfPositions" class="form-control" placeholder="noOfPositions" name="noOfPositions" id="noOfPositions" type="number" min="0"/>
												<font color="red"><form:errors path="noOfPositions" /></font>
										</div>
										<div class="form-group">
										<label for="location">Job Location*: </label>		
												<form:input path="location" class="form-control" placeholder="location" name="location" id="location" type="text"/>
												<font color="red"><form:errors path="location" /></font>
										</div>
										<div class="form-group">
										<label for="companyName">Band Name*: </label>		
												<form:input path="companyName" class="form-control" placeholder="companyName" name="companyName" id="companyName" type="text"/>
												<font color="red"><form:errors path="companyName" /></font>
										</div>
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" value="Post Job">
										</div>
									</div>
								</div>
							</fieldset>
						</form:form>
					</div>
					
                </div>
			</div>
		</div>
	</div>

