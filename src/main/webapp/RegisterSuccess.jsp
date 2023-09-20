<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<h3>
<%
 String name = request.getParameter("name");
 String email = request.getParameter("email");

 %>
 <%= "Dear "+name + ","+"Your email: "+email+" is registered successfully." %>

</h3>
<p><a href="index.jsp">Login now!</a></p>
</body>
</html>