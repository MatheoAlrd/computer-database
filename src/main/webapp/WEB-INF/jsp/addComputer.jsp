<!DOCTYPE html>
<html>

<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
	<%@include file="header.jsp"%>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form id="addFormComputer" name="addFormComputer"
						action="addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									name="computerName" type="text" class="form-control"
									id="computerName" placeholder="Computer name">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									name="introduced" type="date" class="form-control"
									id="introduced" placeholder="Introduced date">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									name="discontinued" type="date" class="form-control"
									id="discontinued" placeholder="Discontinued date" />
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select name="companyId"
									class="form-control" id="company">
									<option value="0">None</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>