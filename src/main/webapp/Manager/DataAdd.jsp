<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/25
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form method="post" action="/addBaseDict">
        <table class="tableEdit">
            <thead>
            <tr>
                <td colspan="4">新建数据字典条目</td>
            </tr>
            </thead>
            <tr>
                <th>类别：</th>
                <td>
                    <select name="type">
                        <option>客户等级</option>
                        <option>服务类型</option>
                    </select>
                </td>
                <th>条目：</th>
                <td>
                    <input type="text" name="item"/>
                </td>
            </tr>
            <tr>
                <th>值：</th>
                <td><input type="text" name="value"/></td>
                <th>是否编辑：</th>
                <td><input type="checkbox" name="checkbox"/></td>
            </tr>
            <tfoot>
            <tr>
                <td colspan="4">
                    <input type="submit" value="确定" />&nbsp;&nbsp;
                    <a href="/findBaseDictAll"><input type="button" value="返回" /></a>
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</body>
</html>