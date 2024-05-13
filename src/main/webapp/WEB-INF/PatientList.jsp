<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Patient Management</title>
</head>
<body class="container">

<p><a href="AddPatient">New Patient</a> | <a href="VaccineList">Go to Vaccines</a><p>

<table class="table table-bordered table-sm mt-3">
	<tr>
		<th scope="col">Id</th>
		<th scope="col">Name</th>
		<th scope="col">Vaccine</th>
		<th scope="col">1st Dose</th>
		<th scope="col">2nd Dose</th>
	</tr>

	<c:forEach items="${patientEntries}" var="entry">
		<tr>
			<td>${entry.id}</td>
			<td>${entry.name}</td>
			<td>${entry.vaccine}</td>
			<td>${entry.firstDose}</td>
			<td>
				<c:choose>
					<c:when test="${entry.secondDose != 'no'}">
						${entry.secondDose}
					</c:when>
					<c:when test="${entry.vaccineDosesLeft == '0'}">
						Out of Stock
					</c:when>
					<c:when test="${entry.vaccineDosesRequired == '1'}">
						-
					</c:when>
					<c:when test="${entry.secondDose == 'no'}">
						<a href="RecievedVaccine?id=${entry.id}">Recieved</a>
					</c:when>				
					<c:otherwise>${entry.secondDose}</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>