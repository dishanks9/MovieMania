<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>

<div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-10 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong>Send email</strong>
					</div>
					<div class="panel-body">
						<form:form  commandName="emailDetails" action="musicrecruitor-emailmusician.htm" method="post">
							<fieldset>
								<br>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
												<form:input path="fromEmail" class="form-control" placeholder="Receiver's email ID" type="text"/>
										</div>
										<div class="form-group">		
												<form:input path="toEmail" class="form-control" placeholder="Sender's email ID" type="text"/>
										</div>
										<div class="form-group">		
												<form:input path="subject" class="form-control" placeholder="Subject" type="text"/>
										</div>
										<div class="form-group">		
												<form:textarea path="message" rows="10" cols="50" placeholder="message" class="form-control"/>
										</div>
										
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" id="submit" value="Send">
											
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
	$("#submit").submit(function(){
        
    
	$.ajax({
	    url : "postsendEmail.htm",
	    type: "POST",
	    data : formData,
	    success: function()
	    {
	    	alert("Submitted");
	    },
	    error: function ()
	    {
	 
	    }
	});
	
	});
	</script>
