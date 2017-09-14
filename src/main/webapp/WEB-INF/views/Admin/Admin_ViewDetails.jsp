<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-10 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> View Details</strong>
					</div>
					<div class="panel-body">
						<form action="admin-approve-user.htm">
							<fieldset>
								<br>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
												<input class="form-control" placeholder="First Name" value="${recruitor.firstName}" id="fName" type="text"/>
										</div>
										<div class="form-group">		
												<input class="form-control" placeholder="Last Name" value="${recruitor.lastName}" name="lName" id="lName" type="text"/>
										</div>
										<div class="form-group">		
												<input class="form-control" placeholder="Username" value="${recruitor.username}" name="username" id="username" type="text"/>
										</div>
										
										<div class="form-group">		
												<input class="form-control" placeholder="Email id" value="${recruitor.emailId}" name="emailId" id="emailId" type="email"/>
										</div>
										<div class="form-group">		
												<input class="form-control" value="${recruitor.address.addressLine1}" placeholder="Address Line1" name="addressLine1" id="addressLine1" type="text"/>
										</div>
										<div class="form-group">		
												<input class="form-control" value="${recruitor.address.city}" placeholder="City" name="city" id="city" type="text"/>
										</div>
										<div class="form-group">		
												<input class="form-control" value="${recruitor.address.state}" placeholder="State" name="state" id="state" type="text"/>
										</div>
										<div class="form-group">		
												<input class="form-control" value="${recruitor.address.zipCode}" placeholder="Zipcode" name="zipCode" id="zipCode" type="text"/>
										</div>
										<div class="form-group">		
												<input class="form-control" value="${recruitor.genre}" placeholder="genre" name="genre" id="genre" type="text"/>
										</div>
										<div class="form-group">		
												<input class="form-control" value="${recruitor.achievements}" placeholder="achievements" name="achievements" id="achievements" type="text"/>
										</div>
										<div class="form-group">		
												<input class="form-control" value="${recruitor.mobileNumber}" placeholder="mobileNumber" name="mobileNumber" id="mobileNumber" type="text"/>
										</div>
										<c:if test="${recruitor.status=='Disabled' }">
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" value="Approve">
										</div>
										</c:if>
										<c:if test="${recruitor.status=='active' }">
										<div class="form-group">
											<h4>Already approved</h4>
										</div>
										</c:if>
										<input type="hidden" name="recId" value="${recruitor.userId}"/>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					
                </div>
			</div>
		</div>
	</div>
