<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Matrimony</title>
	</head>
	<body>
	<div>
		<h2>Matrimony List</h2>

		<input type="button" value="Add Matrimony" onclick="window.location.href='addMatrimonyForm'; return false;"/>
        <form:form action="search" method="POST">
			    <input type="text" placeholder="Search By Matrimony Name" name="theSearchName" value="${theSearchName}">
			    <input type="submit" value="Search"/>
        </form:form>

		<table border="1">
			<tr>
				<th>S No.</th>
				<th>Full Name
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=fullName,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=fullName"> Asc </a>
				</th>

				<th>Gender</th>
				<th>Address
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=address,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=address"> Asc </a>
				</th>

				<th>Religion
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=religion,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=religion"> Asc </a>
				</th>

				<th>Occupation
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=occupation,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=occupation"> Asc </a>
				</th>

				<th>Hobbies</th>
				<th colspan="2">Action</th>
			</tr>

			<c:set var="index" value="${page * 5 + 1}" />

			<c:forEach var="matrimony" items="${matrimonys }">
				<c:url var="updateLink" value="/matrimony/updateMatrimonyForm">
					<c:param name="matrimonyId" value="${matrimony.id}"></c:param>
				</c:url>

				<c:url var="deleteLink" value="/matrimony/delete">
					<c:param name="matrimonyId" value="${matrimony.id}"></c:param>
				</c:url>

				<tr>
					<td>${index}</td>
					<td>${matrimony.fullName}</td>
					<td>${matrimony.gender}</td>
					<td>${matrimony.address}</td>
					<td>${matrimony.religion}</td>
					<td>${matrimony.occupation}</td>
					<td>${matrimony.hobbies}</td>
					<td>
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
					</td>
					<td>
						<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this matrimony?'))) return false">Delete</a>
					</td>
					<c:set var="index" value="${index + 1}" />
					<c:if test="${!matrimony.is_match_found}">
					<td><a href="/updateMatchFound?id=${matrimony.id}">Mark As Match Found </a></td>
					</c:if>
					<c:if test="${matrimony.is_match_found}">
					<td>Match Already Found</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>

		        <br><br>
                	<c:choose>
                        <c:when test="${totalPage == 0}">
                            No Record Found
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                                    &nbsp &nbsp<a href="/search?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>


	</body>
</html>