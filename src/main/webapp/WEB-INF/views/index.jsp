<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Accident</title>
</head>
<body>
<div class="container">
    <div class="row">
        <a href="<c:url value='/create'/>">Добавить инцидент</a>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">гос. №</th>
                <th scope="col">Нарушение</th>
                <th scope="col">Город</th>
                <th scope="col">Статья</th>
                <th scope="col">Результат</th>
                <th scope="col">Действия</th>
             </tr>
            </thead>
            <tbody>
            <%--@elvariable id="accidents" type="java.util.List"--%>
            <c:forEach items="${accidents}" var="a">
            <tr>
                <td><c:out value="${a.id}"/></td>
                <td><c:out value="${a.name}"/></td>
                <td><c:out value="${a.text}"/></td>
                <td><c:out value="${a.address}"/></td>
                <td>
                    <c:forEach items="${a.rules}" var="rule">
                        <c:out value="${rule.name}"/>
                    </c:forEach>
                </td>
                <td><c:out value="${a.type.name}"/></td>
                <td>
                    <a href="<c:url value='/edit?id=${a.id}'/>">Редактировать</a>
                </td>
             </tr>
               </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>