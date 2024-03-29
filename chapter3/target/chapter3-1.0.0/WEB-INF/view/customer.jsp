<%@page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>
            客户管理
        </title>
    </head>

    <body>
         <h1 align="center">客户</h1>
         <table border="1" align="center" bgcolor="#f0f8ff">
             <tr>
                 <th colspan="5"><a href="${pageContext.request.contextPath}/customer_create">新建</a> </th>
             <tr>
             <tr>
                 <th>客户名称</th>
                 <th>联系人</th>
                 <th>电话号码</th>
                 <th>邮箱地址</th>
                 <th>操作</th>
             </tr>
             <c:forEach var="customer" items="${customerList}">
                 <tr>
                     <td>${customer.name}</td>
                     <td>${customer.contact}</td>
                     <td>${customer.telephone}</td>
                     <td>${customer.email}</td>
                     <td>
                         <a href="${pageContext.request.contextPath}/customer_edit?id=${customer.id}">编辑</a>
                         <a href="${pageContext.request.contextPath}/customer_delete?id=${customer.id}">删除</a>
                     </td>
                 </tr>
             </c:forEach>
         </table>
    </body>

    <%-- TODO --%>
</html>