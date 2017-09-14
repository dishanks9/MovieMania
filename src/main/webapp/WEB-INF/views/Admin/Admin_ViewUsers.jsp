<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
<c:when test="${empty user}">
<h3>No users found</h3>
</c:when>
<c:otherwise>
<div class="table-responsive col-md-10">
	<table class="table table-striped table-bordered" id="table">
	<caption>View Users</caption>
		<thead>
			<tr>
				<th>User ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Role</th>
				<th>Status</th>
				<th>username</th>
				<th>email id</th>
				
				
			</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${user}">
				<tr>
					<td>${user.userId}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.role}</td>
					<td class="status${user.userId}">${user.status}</td>
					
					<td>${user.username}</td>
					<td>${user.emailId}</td>
					<c:choose>
					<c:when test="${user.status=='active'}">
						<td class="action${user.userId}"><a href="#" class="deactivate">Disable</a>
					</c:when>
					<c:otherwise>
					<td class="action${user.userId}"><a href="#" class="activate">Enable</a>
					</c:otherwise>
					</c:choose>
					
				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</c:otherwise>
</c:choose>
<script src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>
<script>$(function () {
    $( '#table' ).searchable({
        striped: true,
        oddRow: { 'background-color': '#f5f5f5' },
        evenRow: { 'background-color': '#fff' },
        searchType: 'fuzzy'
    });
});


$(document).on('click', '.activate', function(e) {
    e.preventDefault();
    var uId = $(this).parent().parent().children(':first-child').text();
    $.ajax({
        url: 'admin-enable.htm',                
        contentType: "application/json; charset=utf-8",
        data: { 'uId': uId },
        type: 'GET',
        cache: false,
        success: function (response) {
        	
            $('.status'+response).text("active");
            $('.action'+response).find("a").removeClass("activate").addClass("deactivate").text("Disable");
        }
    });            
});

$(document).on('click', '.deactivate', function(e) {
    e.preventDefault();
    var userId = $(this).parent().parent().children(':first-child').text();
    $.ajax({
        url: 'admin-disable.htm',                
        contentType: "application/json; charset=utf-8",
        data: { 'userId': userId },
        type: 'GET',
        cache: false,
        success: function (response) {
        
        	$('.status'+response).text("Disabled");
        	$('.action'+response).find("a").removeClass("deactivate").addClass("activate").text("Enable");
        }
    });            
});


function changeButton(){
	$(document).ready(function(){
		alert('abc');
		$('.activate').removeAttr("href");
	});
}
</script>