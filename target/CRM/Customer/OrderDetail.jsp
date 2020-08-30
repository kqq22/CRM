<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#orderId").text($("#oddOrderId").val());
            $("#date").text($("#odrDate").val());
            $("#address").text($("#odrAddr").val());
            $("#price").text($("#totalPrice").text());
            if($("#odrStatus")==1){
                $("#status").text("已回款");
            }else{
                $("#status").text("已回款");
            }
        });
    </script>
</head>
<body>
<div id="desDiv">
    <span>客户历史订单</span><br />
    记录客户的历史订单
</div>
<table class="tableEdit">
    <tr>
        <th>
            订单编号：
        </th>
        <td id="orderId">

        </td>
        <th>
            日期：
        </th>
        <td id="date">

        </td>
    </tr>
    <tr>
        <th>
            送货地址：
        </th>
        <td id="address">

        </td>
        <th>
            总金额(元)：
        </th>
        <td id="price">

        </td>
    </tr>
    <tr>
        <th>
            状态：
        </th>
        <td id="status">

        </td>
        <th></th><td></td>
    </tr>
</table>
<p></p>
<table class="dataTable">
    <tr>
        <th>
            商品名
        </th>
        <th>
            数量
        </th>
        <th>
            单位
        </th>
        <th>
            单价(元)
        </th>
        <th>
            金额(元)
        </th>
    </tr>
    <c:forEach items="${ordersLineList}" var="ordersLine" varStatus="statu">
        <input id="odrDate" type="text" value="<fmt:formatDate value='${ordersLine.odrDate}' pattern='yyyy-MM-dd'/>" style="display: none;"/>
        <input id="odrAddr" type="text" value="${ordersLine.odrAddr}" style="display: none;"/>
        <input id="odrStatus" type="text" value="${ordersLine.odrStatus}" style="display: none;"/>
        <input id="oddOrderId" type="text" value="${ordersLine.oddOrderId}" style="display: none;"/>
    <tr>
        <td>
            ${ordersLine.oddProdName}
        </td>
        <td>
            ${ordersLine.oddCount}
        </td>
        <td>
            ${ordersLine.oddUnit}
        </td>
        <td>
            ${ordersLine.oddPrice}
        </td>
        <td id="totalPrice">
            ${ordersLine.oddPrice*ordersLine.oddCount}
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
