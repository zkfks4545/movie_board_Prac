<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="user-login" method="post">
        <input type="text" placeholder="id" name="id"> <br>
        <input type="text" placeholder="pw" name="pw"> <br>
        <button>Login</button>
        <button type="button" onclick="location.href='user-new'">Sign up</button>
    </form>
</body>
</html>
