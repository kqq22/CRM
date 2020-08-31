<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/30
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="desDiv">
    <span>客户贡献分析</span><br />
    查看客户的销售总金额（从高到低排序）
</div>
<form method="post" action="/findContributeReport">
<table class="tableEdit">
    <tr>
        <th>
            客户名称：
        </th>
        <td>
            <input name="odrCustomer" type="text" />
        </td>
        <th>
            年份：
        </th>
        <td>
            <select name="odrDate">
                <option value="2016" selected="selected">2016</option>
                <option value="2015">2015</option>
                <option value="2014">2014</option>
                <option value="2013">2013</option>
                <option value="2012">2012</option>
            </select>（说明：最近5年）
        </td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="8">
            <input type="submit" value="查询" />
        </td>
    </tr>
    </tfoot>
</table>
</form>
<table class="dataTable">
    <tr>
        <th>序号</th>
        <th>客户姓名</th>
        <th>订单金额(元)</th>
    </tr>
    <c:forEach items="${ordersList}" var="orders" varStatus="status">
    <tr>
        <td>${status.index+1}</td>
        <td>${orders.odrCustomer}</td>
        <td>${orders.orderAmount}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
