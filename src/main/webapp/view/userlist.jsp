<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>show users</title>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.js"></script>
</head>
<body>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th>name</th>
                    <th>age</th>
                    <th>sex</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                        <td>${user.sex}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
    <script>

    </script>
</html>
