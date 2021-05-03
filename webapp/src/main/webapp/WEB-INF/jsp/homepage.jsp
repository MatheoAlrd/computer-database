<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head></head>

<body>
    <h1>This is the body of the sample view</h1>

    <s:authorize access="hasRole('ROLE_USER')">
        This text is only visible to a user
        <br/> <br/>
        <a href="<c:url value="/admin/adminpage.html" />">Restricted Admin Page</a>
        <br/> <br/>
    </s:authorize>
	
    <s:authorize access="hasRole('ROLE_ADMIN')">
        This text is only visible to an admin
        <br/>
        <a href="<c:url value="/admin/adminpage.html" />">Admin Page</a>
        <br/>
    </s:authorize>

    <a href="<c:url value="/perform_logout" />">Logout</a>

</body>
</html>