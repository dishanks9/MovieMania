<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">

	<form:form commandName="admin" action="admin-update-details.htm" id="form1" onsubmit="return validate();">
		<fieldset>
			<br>
			<div class="row">
				<div class="col-sm-12 col-md-10  col-md-offset-1 ">
					<div class="form-group">
					<label for="userId">User ID*: </label>
						<form:input path="userId" class="form-control"
							placeholder="First Name" name="fName" id="userId" type="text"
							readonly="true" required="true"/>
							
					</div>
					<div class="form-group">
					<label for="fName">First Name*: </label>
						<form:input path="firstName" class="form-control"
							placeholder="First Name" name="fName" id="fName" type="text"
							readonly="true"/>
							<p id="firstNameError" style="color:red"></p>
					</div>
					<div class="form-group">
					<label for="lName">Last Name*: </label>
						<form:input path="lastName" class="form-control"
							placeholder="Last Name" name="lName" id="lName" type="text"
							readonly="true" required="true"/>
							<p id="lastNameError" style="color:red"></p>
					</div>
					<div class="form-group">
					<label for="username">Username*: </label>
						<form:input path="username" class="form-control"
							placeholder="Username" name="username" id="username" type="text"
							readonly="true"/>
							<p id="userNameError" style="color:red"></p>
					</div>

					<div class="form-group">
					<label for="emailId">Email ID*: </label>
						<form:input path="emailId" class="form-control"
							placeholder="Email id" name="emailId" id="emailId" type="email"
							readonly="true"/>
							<p id="emailError" style="color:red"></p>
					</div>
					<div class="form-group">
					<label for="addressLine1">Address Line 1*: </label>
					<form:hidden path="address.id" class="form-control"/>
						<form:input path="address.addressLine1" class="form-control"
							placeholder="Address Line1" name="addressLine1" id="addressLine1"
							type="text" readonly="true"/>
							<p id="addressLineError" style="color:red"></p>
					</div>
					<div class="form-group">
					<label for="city">City*: </label>
						<form:input path="address.city" class="form-control"
							placeholder="City" name="city" id="city" type="text"
							readonly="true"/>
							<p id="cityError" style="color:red"></p>
					</div>
					<div class="form-group">
					<label for="state">State: </label>
						<form:input path="address.state" class="form-control"
							placeholder="State" name="state" id="state" type="text"
							readonly="true"/>
							<p id="stateError" style="color:red"></p>
					</div>
					<div class="form-group">
					<label for="zipCode">Zipcode*: </label>
						<form:input path="address.zipCode" class="form-control"
							placeholder="Zipcode" name="zipCode" id="zipCode" type="text"
							readonly="true" />
							<p id="zipError" style="color:red"></p>
					</div>
					
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12 col-md-4  col-md-offset-1 ">
								<button type="button" id="edit1"
									class="btn btn-lg btn-primary btn-block">edit</button>
							</div>
							<div class="col-sm-12 col-md-4  col-md-offset-1 ">
								<input type="submit" class="btn btn-lg btn-primary btn-block"
									value="Update details" id="submit1" disabled>
							</div>

						</div>
					</div>
				</div>
			</div>

		</fieldset>
	</form:form>

</div>

<script type="text/javascript">
	$('#edit1').click(function() {
		$('#form1 input').removeAttr('readonly');
		$('#edit1').attr('disabled', true);
		$('#submit1').removeAttr('disabled');
		$('#userId').attr('readonly',true);
	});
	
	
	function validate(){
		var firstName=document.getElementById("fName").value;
		var lastName=document.getElementById("lName").value;
		var userName=document.getElementById("username").value;
		var emailId=document.getElementById("emailId").value;
		var addLine=document.getElementById("addressLine1").value;
		var city=document.getElementById("city").value;
		var state=document.getElementById("state").value;
		var zipCode=document.getElementById("zipCode").value;
		var regExpName=/[^A-Za-z0-9]+/;
		var regExpZip=/\d{5}/;
		if(firstName==""){
			document.getElementById("firstNameError").innerHTML="Please enter first Name";
			return false;
		}
		else if(regExpName.test(firstName)){
			document.getElementById("firstNameError").innerHTML="Please enter first with Name letters and numbers only";
			return false;
		}
		if(lastName==""){
			document.getElementById("lastNameError").innerHTML="Please enter last Name";
			return false;
		}
		else if(regExpName.test(lastName)){
			document.getElementById("lastNameError").innerHTML="Please enter last Name with letters and numbers only";
			return false;
		}
		if(userName==""){
			document.getElementById("userNameError").innerHTML="Please enter Username";
			return false;
		}
		if(emailId==""){
			document.getElementById("emailError").innerHTML="Please enter Email ID";
			return false;
		}
		if(addLine==""){
			document.getElementById("addressLineError").innerHTML="Please enter Address Line 1";
			return false;
		}
		if(city==""){
			document.getElementById("cityError").innerHTML="Please enter City";
			return false;
		}
		if(state==""){
			document.getElementById("stateError").innerHTML="Please enter State";
			return false;
		}
		if(zipCode==""){
			document.getElementById("zipError").innerHTML="Please enter Zipcode";
			return false;
		}
		else if(!regExpZip.test(zipCode)){
			document.getElementById("zipError").innerHTML="Please enter 5 digit zip code only";
			return false;
		}
		return true;
			
	}
</script>