<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>File Upload Example in JSP and Servlet - Java web application</title>
</head>

<body>
<%
    String id = "upload?id=" + request.getAttribute("id");
    System.out.println(id);
%>
<div>
    <h3> Choose File to Upload in Server </h3>
    <form action="<%=id%>" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="Загрузить" />
    </form>
</div>

</body>
</html>
