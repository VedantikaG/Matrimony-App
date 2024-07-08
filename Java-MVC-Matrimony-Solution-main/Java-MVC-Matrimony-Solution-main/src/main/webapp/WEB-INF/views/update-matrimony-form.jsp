<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Matrimony Management</title>
	</head>
	<body>
	<div>
		<h2>Matrimony </h2>
		<h3>Update Matrimony</h3>
		<div>
			<div>
				<hr>
				<form:form action="saveMatrimony" modelAttribute="matrimony" method="POST">
					<form:hidden path="id"/>
					<div>
						<label for="fullName">Full Name: </label>
						<form:input path="fullName" name="fullName" />
						<form:errors path="fullName"/>
					</div>
                    <div>
						<label for="gender">Gender: </label>
						<form:select path="gender">
						<form:option value="M" label="Male"/>
                        <form:option value="F" label="Female"/>
                        </form:select>
                        <form:errors path="gender"/>
					</div>
                    <div>
						<label for="address">Address: </label>
						<form:textarea path="address" name="address" />
						<form:errors path="address"/>
					</div>
                    <div>
						<label for="religion">Religion: </label>
						<form:input path="religion" name="religion" />
						<form:errors path="religion"/>
					</div>
                    <div>
						<label for="occupation">Occupation: </label>
						<form:input path="occupation" name="occupation" />
						<form:errors path="occupation"/>
					</div>
                    <div>
						<label for="hobbies">Hobbies: </label>
						<form:textarea path="hobbies" name="hobbies" />
						<form:errors path="hobbies"/>
					</div>
					<input type="submit" value="Save" class="btn btn-info btn-lg btn-block"/>
				</form:form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/matrimony/list">Back to List</a>
	</div>
	</body>
</html>