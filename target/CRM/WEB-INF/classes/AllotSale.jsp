<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/25
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        $(function () {
            //当前日期
            var date = new Date();
            this.year = date.getFullYear();
            this.month = date.getMonth() + 1;
            this.date = date.getDate();
            this.month = this.month < 10 ? ('0' + this.month) : this.month;
            this.date = this.date < 10 ? ('0' + this.date) : this.date;
            var currentTime =  this.year + "-" + this.month + "-" + this.date;
            var createDate = $("#createDate").val();
            $("#currentTime").text(currentTime);
            $("#cuTime").val(currentTime);
            //创建时间
            var dateStr=createDate.trim().split(" ");
            var strGMT = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";
            var date = new Date(Date.parse(strGMT));
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var str = y+"-"+m+"-"+d;
            $("#cDate").text(str);

        });
    </script>
</head>
<body>
<form method="post" action="/updateSaleChanceDueTo">
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">销售机会分配</td>
    </tr>
    <input type="text" name="chcId" value="${salChance.chcId}" style="display: none;" />
    </thead>
    <tr>
        <th>客户名称：</th>
        <td>${salChance.chcCustName}</td>
        <th>机会来源</th>
        <td>${salChance.chcSource}</td>
    </tr>
    <tr>
        <th>成功几率：</th>
        <td>${salChance.chcRate}</td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>联系人：</th>
        <td>${salChance.chcLinkman}</td>
        <th>联系电话：</th>
        <td>${salChance.chcTel}</td>
    </tr>
    <tr>
        <th>概要：</th>
        <td colspan="3">${salChance.chcTitle}</td>
    </tr>
    <tr style="height:100px">
        <th>机会描述：</th>
        <td colspan="3" style="vertical-align:top">${salChance.chcDesc}</td>
    </tr>
    <tr>
        <th>创建人：</th>
        <td>${sessionScope.uname}</td>
        <th>创建时间：</th>
        <td id="cDate"><input id="createDate" type="text" value="${salChance.chcCreateDate}" style="display:none;"/></td>
    </tr>
    <tr>
        <th>指派人：</th>
        <td>
            <c:forEach items="${sysUserList}" var="sysuser" varStatus="statu">
                <input style="display: none" name="userid" value="${sysuser.userId}"/>
            </c:forEach>
            <select name="userName">
                <c:forEach items="${sysUserList}" var="sysuser" varStatus="statu">
                    <option>${sysuser.userName}</option>
                </c:forEach>
            </select>

        </td>
        <th>指派时间：</th>
        <td id="currentTime"></td>
        <td style="display:none;"><input id="cuTime" name="cTime" type="text" value=""/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定" />&nbsp;&nbsp;
            <a href="/Sale/SaleChance.jsp"><input type="button" value="返回" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>