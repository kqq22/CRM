<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/29
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        function showCustomer(){
            var returnValue = window.showModalDialog("../publicPage/SelectCustomer.htm?data=" + Math.random(), "参数", "dialogWidth:400px;dialogHeight:220px;help:no");

        }
    </script>
</head>
<body>
<form method="post" action="/addCstService">
<table class="tableEdit" style="width:600px;">
    <thead>
    <tr>
        <td colspan="4">创建服务</td>
    </tr>
    </thead>
    <tr>
        <th>客户姓名：</th>
        <td>
            <input name="svrCustName" type="text" ondblclick="showCustomer()" /></td>
        <th>服务类型：</th>
        <td>
            <select name="svrType" style="width:100px">
                <option>咨询</option>
                <option>投诉</option>
                <option>建议</option>
            </select>
        </td>
    </tr>
    <tr>
        <th>服务概要：</th>
        <td colspan="3">
            <input name="svrTitle" type="text"  style="width:300px"/>
        </td>
    </tr>
    <tr>
        <th>详细信息：</th>
        <td colspan="3">
            <textarea name="svrRequest"></textarea>
        </td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>
