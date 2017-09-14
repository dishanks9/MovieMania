<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-10 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Post Event</strong>
					</div>
					<div class="panel-body">
						<form:form  commandName="event" action="admin-post-event.htm" method="post">
							<fieldset>
								<br>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
										<label for="eventName">Event Name: </label>
												<form:input path="eventName" class="form-control" placeholder="eventName" name="eventName" id="eventName" type="text" required="true"/>
											
										</div>
										<div class="form-group">
												<label for="eventDetails">Event Details: </label>		
												<form:textarea path="eventDetails" rows="4" cols="50" class="form-control" placeholder="eventDetails" name="eventDetails" id="eventDetails" type="text" required="true"/>
												
										</div>
										<div class="form-group">
												<label for="location">Location: </label>			
												<form:input path="location" class="form-control" placeholder="location" name="location" id="location" type="text" required="true"/>
												
										</div>
										
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" value="Post event	">
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
