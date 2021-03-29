<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<style><%@include file="../../css/bootstrap.min.css"%></style>
<style><%@include file="../../css/font-awesome.css"%></style>
<style><%@include file="../../css/main.css"%></style>

</head>
<body>
	<%@include file="header.jsp"%>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${totalComputers} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" />
						<input type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a>
					<a class="btn btn-default" id="editComputer" onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;">
							<input type="checkbox" id="selectall"/>
							<span style="vertical-align: top;"> - 
								<a href=""	id="deleteSelected" onclick="$.fn.deleteSelected();">
									<i class="fa fa-trash-o fa-lg"></i>
								</a>
							</span>
						</th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>
					</tr>
				</thead>

				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="editComputer?id=${computer.id}"> ${computer.name}</a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.companyName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="dashboard?page=1" aria-label="First"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="dashboard?page=${previousPage}">Previous</a></li>
				<c:if test="${pageStart != 1}">
					<li><a href="">...</a></li>
				</c:if>
				<c:forEach var="page" begin="${pageStart}" end="${pageEnd}" step="1">						
						<li><a href="dashboard?page=${page}">${page}</a></li>					
				</c:forEach>
				<c:if test="${pageEnd != pageMax}">
					<li><a href="">...</a></li>
				</c:if>
				<li><a href="dashboard?page=${nextPage}">Next</a></li>
				<li><a href="dashboard?page=${pageMax}" aria-label="Last"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		

			<div class="btn-group btn-group-sm pull-right" role="group">
				<form id="pageSizeForm" action="#" method="GET">
					<button name="pageSize" type="submit" class="btn btn-default" value="10">10</button>
					<button name="pageSize" type="submit" class="btn btn-default" value="50">50</button>
					<button name="pageSize" type="submit" class="btn btn-default" value="100">100</button>
				</form>
			</div>
		</div>
	</footer>
	
	<script><%@include file="../../js/dashboard.js"%></script>

</body>
</html>