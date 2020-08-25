<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/23
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form method="post" action="/updateBaseDict">
    <table class="tableEdit">
        <thead>
        <tr>
            <td colspan="4">数据字典条目编辑 <input type="text" name="id" value="${baseDict.dictId}" style="display: none"/></td>

        </tr>
        </thead>
        <tr>
            <th>类别：</th>
            <td>
                <select name="type">
                    <option value="客户等级" <c:if test="${baseDict.dictType=='客户等级'}">selected="selected"</c:if>>客户等级</option>
                    <option value="服务类型" <c:if test="${baseDict.dictType=='服务类型'}">selected="selected"</c:if>>服务类型</option>
                </select>
            </td>
            <th>条目：</th>
            <td>
                <input type="text" value="${baseDict.dictItem}" name="item"/>
            </td>
        </tr>
        <tr>
            <th>值：</th>
            <td><input type="text" value="${baseDict.dictValue}" name="value"/></td>
            <th>是否编辑：</th>
            <td>
                <c:if test="${baseDict.dictIsEditable==1}">
                    <input type="checkbox" checked name="checkbox"/>
                </c:if>
                <c:if test="${baseDict.dictIsEditable==0}">
                    <input type="checkbox" name="checkbox"/>
                </c:if>
            </td>
        </tr>
        <tfoot>
        <tr>
            <td colspan="4">
                <input type="submit" value="确定"/>&nbsp;
                <a href="/findBaseDictAll"><input type="button" value="返回" /></a>&nbsp;
            </td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
