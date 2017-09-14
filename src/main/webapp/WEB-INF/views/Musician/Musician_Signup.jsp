<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container" style="margin-top: 40px">
	<div class="row">
		<div class="col-sm-6 col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong> Sign up to continue</strong>
				</div>
				<div class="panel-body">
					<form:form commandName="musician" action="musician-signup.htm"
						method="post" onsubmit="return validate();">
						<fieldset>
							<br>
							<div class="row">
								<div class="col-sm-12 col-md-10  col-md-offset-1 ">

									<div class="form-group">
										<label for="fName">First Name*: </label>
										<form:input path="firstName" class="form-control"
											placeholder="First Name" name="fName" id="fName" type="text" />
										<p id="firstNameError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="lName">Last Name*: </label>
										<form:input path="lastName" class="form-control"
											placeholder="Last Name" name="lName" id="lName" type="text" />
										<p id="lastNameError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="username">Username*: </label>
										<form:input path="username" class="form-control"
											placeholder="Username" name="username" id="username"
											type="text" />
										<p id="userNameError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="password">Password(should be combination of numbers and letters of length between 3-8)*: </label>
										<form:input path="password" class="form-control"
											placeholder="Password" name="password" id="password"
											type="password" />
										<p id="passwordError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="emailId">Email ID*: </label>
										<form:input path="emailId" class="form-control"
											placeholder="Email id" name="emailId" id="emailId"
											type="email" />
										<p id="emailError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="addressLine1">Address Line 1*: </label>
										<form:hidden path="address.id" class="form-control" />
										<form:input path="address.addressLine1" class="form-control"
											placeholder="Address Line1" name="addressLine1"
											id="addressLine1" type="text" />
										<p id="addressLineError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="city">City*: </label>
										<form:input path="address.city" class="form-control"
											placeholder="City" name="city" id="city" type="text" />
										<p id="cityError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="state">State*: </label>
										<form:input path="address.state" class="form-control"
											placeholder="State" name="state" id="state" type="text" />
										<p id="stateError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="zipCode">Zipcode*: </label>
										<form:input path="address.zipCode" class="form-control"
											placeholder="Zipcode" name="zipCode" id="zipCode" type="text" />
										<p id="zipError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="pastYearsExperience">Past Years
											Experience(In years)*: </label>
										<form:input path="pastYearsExperience" class="form-control"
											placeholder="pastYearsExperience" name="pastYearsExperience"
											id="pastYearsExperience" type="text" />
										<p id="pastExpError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="instrumentSpeciality">Instrument
											Speciality: </label>
										<form:input path="instrumentSpeciality" class="form-control"
											placeholder="instrumentSpeciality"
											name="instrumentSpeciality" id="instrumentSpeciality"
											type="text" />
										<p id="instError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="pastBand">Previous Band: </label>
										<form:input path="pastBand" class="form-control"
											placeholder="pastBand" name="pastBand" id="pastBand"
											type="text" />
										<p id="pastError" style="color: red"></p>
									</div>
									<div class="form-group">
										<label for="contactNumber">Contact Number*: </label>
										<form:input path="contactNumber" class="form-control"
											placeholder="contactNumber" name="contactNumber"
											id="contactNumber" type="text" />
										<p id="contactError" style="color: red"></p>
									</div>

									<div class="form-group">
										<div class="row">

											<div class="col-sm-12 col-md-4  col-md-offset-1 ">
												<input type="submit"
													class="btn btn-lg btn-primary btn-block" value="Sign up"
													id="submit1">
											</div>

										</div>
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

<script>
	function validate() {

		var firstName = document.getElementById("fName").value;
		var lastName = document.getElementById("lName").value;
		var userName = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var emailId = document.getElementById("emailId").value;
		var addLine = document.getElementById("addressLine1").value;
		var city = document.getElementById("city").value;
		var state = document.getElementById("state").value;
		var zipCode = document.getElementById("zipCode").value;
		var pastExp = document.getElementById("pastYearsExperience").value;
		var contact = document.getElementById("contactNumber").value;
		var regExpName = /[^A-Za-z0-9]+/;
		var regExpZip = /\d{5}/;
		var regExpPast = /^\d+$/;
		var regExppassword = /^([a-zA-Z0-9]){3,8}$/;
		var regExpNum = /\d{3}-\d{3}-\d{4}/;
		if (firstName == "") {
			document.getElementById("firstNameError").innerHTML = "Please enter first Name";
			return false;
		} else if (regExpName.test(firstName)) {
			document.getElementById("firstNameError").innerHTML = "Please enter first with Name letters and numbers only";
			return false;
		}
		if (lastName == "") {
			document.getElementById("lastNameError").innerHTML = "Please enter last Name";
			return false;
		} else if (regExpName.test(lastName)) {
			document.getElementById("lastNameError").innerHTML = "Please enter last Name with letters and numbers only";
			return false;
		}
		if (userName == "") {
			document.getElementById("userNameError").innerHTML = "Please enter Username";
			return false;
		}
		if (password == "") {
			document.getElementById("passwordError").innerHTML = "Please enter password";
			return false;
		}
		else if (!regExppassword.test(password)) {
			document.getElementById("passwordError").innerHTML = "Please enter valid password";
			return false;
		}
		if (emailId == "") {
			document.getElementById("emailError").innerHTML = "Please enter Email ID";
			return false;
		}
		if (addLine == "") {
			document.getElementById("addressLineError").innerHTML = "Please enter Address Line 1";
			return false;
		}
		if (city == "") {
			document.getElementById("cityError").innerHTML = "Please enter City";
			return false;
		}
		if (state == "") {
			document.getElementById("stateError").innerHTML = "Please enter State";
			return false;
		}
		if (zipCode == "") {
			document.getElementById("zipError").innerHTML = "Please enter Zipcode";
			return false;
		} else if (!regExpZip.test(zipCode)) {
			document.getElementById("zipError").innerHTML = "Please enter 5 digit zip code only";
			return false;
		}
		if (pastExp == "") {
			document.getElementById("pastExpError").innerHTML = "Please enter past experience";
			return false;
		} else if (!regExpPast.test(pastExp)) {
			document.getElementById("pastExpError").innerHTML = "Please enter number of years you have worked with another band(s)";
			return false;
		}
		if (contact == "") {
			document.getElementById("contactError").innerHTML = "Please enter contact number";
			return false;
		} else if (!regExpNum.test(contact)) {
			alert('abc2');
			document.getElementById("contactError").innerHTML = "Please enter valid 10 digit contact number";
			return false;
		}

		return true;

	}
</script>
