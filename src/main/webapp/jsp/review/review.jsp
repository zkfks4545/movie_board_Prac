<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Review Page~~~~</h2>

    <c:forEach items="${reviews}" var="r">
        ${r.no}/${r.title}
    </c:forEach>

</body>
</html>
