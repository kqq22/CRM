<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/25
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<html>
<head>
    <title>Title</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        function check() {
            if($("#custName").val()!=""&&$("#rate").val()!=""&&$("#title").val()!=""&&$("#desc").val()!=""){
                return true;
            }else{
                return false;
            }
        }
            function checkBlur() {
                if($("#custName").val()!=""&&$("#rate").val()!=""&&$("#title").val()!=""&&$("#desc").val()!=""){
                    return true;
                }else{
                    if($("#custName").val()==""){
                        $("#checkName").text("客户名称不能为空!");
                    }else{
                        $("#checkName").text("");
                    }

                    if($("#rate").val()==""){
                        $("#rate").append("客户名称不能为空!");
                    }else{
                        $("#checkName").text("");
                    }

                    if($("#title").val()==""){
                        $("#title").append("客户名称不能为空!");
                    }else{
                        $("#checkName").text("");
                    }

                    if($("#desc").val()==""){
                        $("#desc").append("客户名称不能为空!");
                    }else{
                        $("#checkName").text("");
                    }
                    return false;
                }
        }
    </script>
</head>
<body>
<form method="post" action="/addSalChance">
<%--    --%>
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">销售机会添加</td>
    </tr>
    </thead>
    <tr>
        <th>客户名称：</th>
        <td>
            <input type="text" name="chcCustName" id="custName"/><span id="checkName" style="color: red"></span>
        </td>
        <th>机会来源</th>
        <td><input type="text" name="chcSource"/></td>
    </tr>
    <tr>
        <th>成功几率：</th>
        <td>
            <input type="text" name="chcRate" id="rate"/><span id="checkRate" style="color: red"></span>
        </td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>联系人：</th>
        <td><input type="text" name="chcLinkman"/></td>
        <th>联系电话：</th>
        <td><input type="text" name="chcTel"/></td>
    </tr>
    <tr>
        <th>概要：</th>
        <td colspan="3"><input type="text" style="width:500px" name="chcTitle" id="title"/><span id="checkTitle" style="color: red"></span></td>
    </tr>
    <tr>
        <th>机会描述：</th>
        <td colspan="3"><textarea style="width:500px" name="chcDesc" id="desc"></textarea><span id="checkDesc" style="color: red"></span></td>
    </tr>
    <tr>
        <th>创建人</th>
        <td><input type="text" readonly="readonly" id="username" value="<%=session.getAttribute("uname") %>" name="chcCreateBy"/></td>
        <th>创建时间</th>
        <td >
            <input type="text" readonly="readonly" value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/>"/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4">
            <input type="submit" value="确定并返回" onclick="return check()"/>&nbsp;&nbsp;
            <a href="/Sale/SaleChance.jsp"><input type="button" value="返回" /></a>
        </td>
    </tr>
    </tfoot>
</table>
</form>
</body>
</html>