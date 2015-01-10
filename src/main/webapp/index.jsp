<%
response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
String newLocn = request.getContextPath()+"/nov30/pages/signin.xhtml";
response.setHeader("Location",newLocn);
%>
<html>

<head>
<meta http-equiv="refresh" content="0;URL=http://118.139.161.91:8080/nov30/pages/signin.xhtml">
<h1>Welcome</h1>
</head>

<body>
</body>

</html>

