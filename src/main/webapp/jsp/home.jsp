<%@ page import="ru.levchenko.servletsApp.dao.models.User" %>
<%@ page import="ru.levchenko.servletsApp.dao.models.UserRolesENUM" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%--
  Created by IntelliJ IDEA.
  User: NikolayLevchenko
  Date: 13.08.2019
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% List<User> userList = (List<User>) request.getAttribute("userList");
    Iterator<User> iterator = userList.iterator();
%>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>User-Name</th>
        <th>Password</th>
        <th>User</th>
        <th>Moder</th>
        <th>Admin</th>
    </tr>
    </thead>
    <tbody>
    <%
        while (iterator.hasNext()) {
            User user = iterator.next();
            String userName = user.getName();
            int id = user.getId();
            String password = user.getPassword();
            List<UserRolesENUM> userRoles = user.getRoles()
                    .stream()
                    .map((a) -> a.getRole())
                    .collect(Collectors.toList());
            String userChecked = "";
            String adminChecked = "";
            String moderatorChecked = "";
            if (userRoles.contains(UserRolesENUM.USER)) {
                userChecked = "checked";
            }
            if (userRoles.contains(UserRolesENUM.MODERATOR)) {
                moderatorChecked = "checked";
            }
            if (userRoles.contains(UserRolesENUM.ADMIN)) {
                adminChecked = "checked";
            }
    %>
    <tr>
        <form method="post" action="/home">
            <td><input type=hidden name="id" value="<%=id%>"/><%=id%>
            </td>
            <td><input type=text name="userName" value="<%=userName%>"/>
            </td>
            <td><input type=text name="password" value="<%=password%>"/>
            </td>
            <td>
                <input type="checkbox" name="user"  <%=userChecked %>/>
            </td>
            <td><input type="checkbox" name="moderator" <%=moderatorChecked%>/>
            </td>
            <td><input type="checkbox" name="admin" <%=adminChecked%>/>
            </td>
            <td>
                <button value="Изменить">Изменить</button>
            </td>
        </form>
        <form method="post" action="/delete">
            <input type=hidden name="id" value="<%=id%>"/>
            <td>
                <button value="Удалить">Удалить</button>
            </td>
        </form>
    </tr>
    <% }%>
    <tr>
        <form method="post" action="/home">
            <td>
                <input type=hidden value="new"/>
            </td>
            <td>
                <input type=text name="userName" id="newUserName" value=""/>
            </td>
            <td>
                <input type=text name="password" id="newPassword" value=""/>
            </td>
            <td>
                <input id="newUser" name="user" type="checkbox"/>
            </td>
            <td>
                <input id="newModerator" name="moderator" type="checkbox"/>
            </td>
            <td>
                <input id="newAdmin" name="admin" type="checkbox"/>
            </td>
            <td>
                <button value="Изменить">Добавить</button>
            </td>
        </form>

    </tr>
    </tbody>


</table>

</body>
</html>
