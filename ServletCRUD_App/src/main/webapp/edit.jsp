<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="edit" method="post">
    <input type="hidden" name="id" value="${id}">
    
    Name: <input type="text" name="name" value="${name}"><br><br>
    Email: <input type="text" name="email" value="${email}"><br><br>

    <button type="submit">Update</button>
</form>

</body>
</html>