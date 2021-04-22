<!DOCTYPE html>
<html>
<head></head>

<body>

	<%@include file="header.jsp"%>

	<h1>Login</h1>

	<form name='f' action="registration" method='POST'>

		<table>
			<tr>
				<td>First Name :</td>
				<td><input type='text' name='firstName' value=''></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><input type='text' name='lastName' value=''></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td>Role :</td>
				<td><select name="role" class="form-control">
						<option value="">None</option>
						<option value="USER">USER</option>
						<option value="ADMIN">ADMIN</option>
				</select></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>

	</form>

</body>
</html>