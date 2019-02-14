<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>
            客户管理 - 修改客户
        </title>
    </head>

    <body>
        <h1 align="center">修改客户</h1>
        <form action="${pageContext.request.contextPath}/customer_show" method="post">
            <input hidden="true" name="id" value="${customer.id}">
            <table border="1" align="center">
                <tr>
                    <th>customerNmae</th>
                    <td><input type="text" name="name" value="${customer.name}"></td>
                </tr>
                <tr>
                    <th>Contact</th>
                    <td><input type="text" name="contact" value="${customer.contact}"></td>
                </tr>
                <tr>
                    <th>Telephone</th>
                    <td><input type="text" name="telephone" value="${customer.telephone}"></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><input type="email" name="email" value="${customer.email}"></td>
                </tr>
                <tr>
                    <th>Remark</th>
                    <td><input type="text" name="remark" value="${customer.remark}"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </body>

    <%-- TODO --%>
</html>