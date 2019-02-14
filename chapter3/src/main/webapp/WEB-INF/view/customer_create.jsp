<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>
            客户管理 - 创建客户
        </title>

    </head>

    <body>
        <h1 align="center">创建客户界面</h1>
        <form id="customer_form" action="${pageContext.request.contextPath}/customer_create" enctype="multipart/form-data">
            <table border="1" align="center">
                <tr>
                    <th>ID:</th>
                    <td><input type="number" name="id"></td>
                </tr>
                <tr>
                    <th>customerName</th>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <th>Contact</th>
                    <td><input type="text" name="contact"></td>
                </tr>
                <tr>
                    <th>Telephone</th>
                    <td><input type="text" name="telephone"></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><input type="email" name="email"></td>
                </tr>
                <tr>
                    <th>Remark</th>
                    <td><input type="text" name="remark"></td>
                </tr>
                <tr>
                    <th>Phone</th>
                    <td><input type="file" name="phone"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
        <script src="${pageContext.request.contextPath}/asset/lib/jquery/jquery-1.4.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/asset/lib/jquery-form/jquery-form.js"></script>
        <script>
            $(function(){
                $('#customer_form').ajaxForm({
                    type:'post',
                    url:'${pageContext.request.contextPath}/customer_create',
                    success:function (data) {
                        if(data){
                            location.href='${pageContext.request.contextPath}/customer';
                        }
                    }
                });
            });
        </script>
    </body>
    <%-- TODO --%>

</html>