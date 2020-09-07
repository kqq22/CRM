<html xmlns="http://www.w3.org/1999/xhtml" >
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath =   request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/page/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />

</head>
<body>
    <div id="desDiv">
        <span>数据字典管理</span><br />
        管理系统所需的数据字典内容
    </div>
    <form method="post" action="/findBaseDictByExample">
    <table class="tableEdit">
        <tr>
            <th>
                类别：
            </th>
            <td>
                <input name="dictType" type="text" />
            </td>
            <th>
                条目：
            </th>
            <td>
                <input name="dictItem" type="text" />
            </td>
            <th>
                值：
            </th>
            <td>
                <input name="dictValue" type="text" />
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
    <div id="dataDiv">
        <div id="headDiv">
            <img src="../images/22.gif" />&nbsp;&nbsp;<a href="/Manager/DataAdd.jsp">新建</a>
        </div>
        <table id="mytable" class="dataTable">
            <tr>
                <th>类别</th>
                <th>条目</th>
                <th>值</th>
                <th>是否可编辑</th>
                <th>操作</th>
            </tr>
            <!-- 循环开始 -->
            <c:forEach items="${list}" var="basedict" >
                <tr>
                    <td>${basedict.dictType}</td>
                    <td>${basedict.dictItem}</td>
                    <td>${basedict.dictValue}</td>
                    <td>
                        <c:if test="${basedict.dictIsEditable==0}">否</c:if>
                        <c:if test="${basedict.dictIsEditable==1}">是</c:if>
                    </td>
                    <c:if test="${basedict.dictIsEditable==1}">
                        <td>
                            <a href="/findBaseDictById?id=${basedict.dictId}"><img src="../images/33.gif" title="编辑" style="border:0px" /></a>
                            <a href="/delBaseDict?id=${basedict.dictId}"><img src="../images/bt_del.gif" title="删除" style="border:0px"/></a>
                        </td>
                    </c:if>
                    <c:if test="${basedict.dictIsEditable==0}">
                        <td></td>
                    </c:if>
                </tr>
            </c:forEach>
            <!-- 循环结束 -->
        </table>
    </div>
</body>
</html>
