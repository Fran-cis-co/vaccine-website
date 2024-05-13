<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>VaccineList</title>
</head>
<body class="container">
<p><a href="NewVaccine">New Vaccine</a> | <a href="NewDoses">New Doses</a> | <a href="PatientList">Go to Patient List</a><p>

<table class="table table-bordered table-sm mt-3">
	<tr>
		<th scope="col">Vaccine</th>
		<th scope="col">Doses Required</th>
		<th scope="col">Days Between Doses</th>
		<th scope="col">Total Doses Recieved</th>
		<th scope="col">Total Doses Left</th>
	</tr>

	<c:forEach items="${entries}" var="entry">
		<tr>
			<td scope="row">${entry.vaccineName}</td>
			<td>${entry.dosesRequired}</td>
			<td>
				<c:choose>
				<c:when test="${entry.dosesRequired == 1}"> </c:when>
				<c:otherwise>${entry.daysBetweenDoses}</c:otherwise>
				</c:choose>
			</td>
			<td>${entry.dosesRecieved}</td>
			<td>${entry.dosesLeft}</td>
			<td><a href="EditVaccine?id=${entry.id}">Edit</a></td>
		</tr>
	</c:forEach>
</table>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>