<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/28
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>客户基本信息</title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="/updateCstCustomer" method="post">
    <input value="${cstCustomer.custNo}" name="custNo" style="display: none;">
    <input value="${cstCustomer.custManagerId}" name="custManagerId" style="display: none;">
    <input value="${cstCustomer.custManagerName}" name="custManagerName" style="display: none;">
    <input value="${cstCustomer.custStatus}" name="custStatus" style="display: none;">
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">客户基本信息</td>
    </tr>
    </thead>
    <tr>
        <th>客户编号：</th>
        <td>${cstCustomer.custNo}</td>
        <th>客户经理：</th>
        <td>${cstCustomer.custManagerName}</td>
    </tr>
    <tr>
        <th>客户名称：</th>
        <td>
            <input type="text" name="custName" value="${cstCustomer.custName}"/>
        </td>
        <th>地区：</th>
        <td>
            <select name="custRegion">
                <option value="华北" <c:if test="${cstCustomer.custRegion=='华北'}">selected="selected"</c:if>>华北</option>
                <option value="华南" <c:if test="${cstCustomer.custRegion=='华南'}">selected="selected"</c:if>>华南</option>
                <option value="中南" <c:if test="${cstCustomer.custRegion=='中南'}">selected="selected"</c:if>>中南</option>
                <option value="中东" <c:if test="${cstCustomer.custRegion=='中东'}">selected="selected"</c:if>>中东</option>
                <option value="西北" <c:if test="${cstCustomer.custRegion=='西北'}">selected="selected"</c:if>>西北</option>
            </select>
        </td>
    </tr>
    <tr>
        <th>客户等级：</th>
        <td>
            <select name="custLevel">
                <option value="1" <c:if test="${cstCustomer.custLevel==1}">selected="selected"</c:if>>战略合作伙伴</option>
                <option value="2" <c:if test="${cstCustomer.custLevel==2}">selected="selected"</c:if>>大客户</option>
                <option value="3" <c:if test="${cstCustomer.custLevel==3}">selected="selected"</c:if>>大客户</option>
                <option value="4" <c:if test="${cstCustomer.custLevel==4}">selected="selected"</c:if>>普通客户</option>
            </select>
        </td>
        <th></th><td></td>
    </tr>
    <tr>
        <th>客户满意度：</th>
        <td>
            <select name="custSatisfy">
                <option value="1" <c:if test="${cstCustomer.custSatisfy==1}"> selected="selected"</c:if>>☆</option>
                <option value="2" <c:if test="${cstCustomer.custSatisfy==2}"> selected="selected"</c:if>>☆☆</option>
                <option value="3" <c:if test="${cstCustomer.custSatisfy==3}"> selected="selected"</c:if>>☆☆☆</option>
                <option value="4" <c:if test="${cstCustomer.custSatisfy==4}"> selected="selected"</c:if>>☆☆☆☆</option>
                <option value="5" <c:if test="${cstCustomer.custSatisfy==5}"> selected="selected"</c:if>>☆☆☆☆☆</option>
            </select>
        </td>
        <th>客户信用度：</th>
        <td>
            <select name="custCredit">
                <option value="1" <c:if test="${cstCustomer.custCredit==1}"> selected="selected"</c:if>>☆</option>
                <option value="2" <c:if test="${cstCustomer.custCredit==2}"> selected="selected"</c:if>>☆☆</option>
                <option value="3" <c:if test="${cstCustomer.custCredit==3}"> selected="selected"</c:if>>☆☆☆</option>
                <option value="4" <c:if test="${cstCustomer.custCredit==4}"> selected="selected"</c:if>>☆☆☆☆</option>
                <option value="5" <c:if test="${cstCustomer.custCredit==5}"> selected="selected"</c:if>>☆☆☆☆☆</option>
            </select>
        </td>
    </tr>
</table>
<p></p>
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">客户联系信息</td>
    </tr>
    </thead>
    <tr>
        <th>地址：</th>
        <td>
            <input name="custAddr" type="text" value="${cstCustomer.custAddr}" style="width:300px" />
        </td>
        <th>邮编：</th>
        <td>
            <input name="custZip" type="text"value="${cstCustomer.custZip}"/>
        </td>
    </tr>
    <tr>
        <th>电话：</th>
        <td>
            <input name="custTel" type="text" value="${cstCustomer.custTel}"/>
        </td>
        <th>传真：</th>
        <td>
            <input name="custFax" type="text"value="${cstCustomer.custFax}" />
        </td>
    </tr>
    <tr>
        <th>网址：</th>
        <td>
            <input name="custWebsite" type="text" value="${cstCustomer.custWebsite}"/>
        </td>
        <th></th><td></td>
    </tr>
</table>
<p></p>
<table class="tableEdit">
    <thead>
    <tr>
        <td colspan="4">公司信息</td>
    </tr>
    </thead>
    <tr>
        <th>营业执照号：</th>
        <td>
            <input name="custLicenceNo" type="text" value="${cstCustomer.custLicenceNo}"/>
        </td>
        <th>法人：</th>
        <td>
            <input name="custChieftain" type="text" value="${cstCustomer.custChieftain}"/>
        </td>
    </tr>
    <tr>
        <th>注册资金(万元)：</th>
        <td>
            <input name="custBankroll" type="text"  value="${cstCustomer.custBankroll}"/>
        </td>
        <th>年营业额：</th>
        <td>
            <input name="custTurnover" type="text" value="${cstCustomer.custTurnover}"/>
        </td>
    </tr>
    <tr>
        <th>开户银行：</th>
        <td>
            <input name="custBank" type="text" value="${cstCustomer.custBank}"/>
        </td>
        <th>银行账号：</th>
        <td>
            <input name="custBankAccount" type="text" value="${cstCustomer.custBankAccount}"/>
        </td>
    </tr>
    <tr>
        <th>地税登记号：</th>
        <td>
            <input name="custLocalTaxNo" type="text" value="${cstCustomer.custLocalTaxNo}"/>
        </td>
        <th></th><td></td>
    </tr>
</table>
<p></p>
<input type="submit" value="保存" />&nbsp;&nbsp;
<a href="Customer/CustomerPage.jsp"><input type="button" value="返回" /></a>
</form>
</body>
</html>
