<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/29
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>服务分配</title>
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/Style2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/JS/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        /**
         * 窗体加载时调用查询方法初始化
         */
        $(function(){
            global(1);
            //要显示的页码
            var index = 0;
            $("#in").click(
                function () {
                    index = $("#inPage").val();
                    //调用异步请求函数
                    global(index);
                }
            );
            //上一页
            $("#pre").click(
                function () {
                    global($perPage);
                }
            );
            //下一页
            $("#next").click(
                function () {
                    //如果没有下一页了，不做操作
                    if($hasNextPage){
                        global($nextPage);
                    }else{
                        global($pageNum);
                    }


                }
            );
            //最后一页
            $("#last").click(
                function () {
                    global($lastPage);
                }
            );
        });

        /**
         * 异步请求
         */
        function global (index) {
            $.ajax({
                url:"http://localhost:8080/findCstServiceAll",
                type: "get",//提交方式
                async: true,//是否异步发送
                data:{
                    "pageNum":index,
                    "svrCustName":$("#CustName").val(),
                    "svrTitle":$("#Title").val(),
                    "svrType":$("#Type").val(),
                    "svrStatus":$("#Status").val(),
                    "svrStartDate":$("#StartDate").val(),
                    "svrEndDate":$("#EndDate").val()
                },
                success:function(CstServiceJson){
                    nav(CstServiceJson);
                }
            });
        }

        /**
         * 显示的数据
         */
        function nav(CstServiceJson) {
            //清空表格（除第一行数据外）
            $("#dataTable").find("tr:not(:first)").remove();
            //总记录数
            $("#total").html(CstServiceJson.total);
            //当前页
            $("#pageNum").html(CstServiceJson.pageNum);
            //总页数
            $("#totalNum").html(CstServiceJson.pages);
            $pageNum = CstServiceJson.pageNum;
            //是否有下一页
            $hasNextPage = CstServiceJson.hasNextPage;
            //上一页
            $perPage = CstServiceJson.prePage;
            //下一页
            $nextPage = CstServiceJson.nextPage;
            //最后一页（总页数）
            $lastPage = CstServiceJson.pages;

            /**
             * 循环读取数据，并将数据写入表格
             */
            $(CstServiceJson.list).each(function(index,cstservice){
                console.log(CstServiceJson);
                //创建一行
                var $tr=$("<tr></tr>");
                //创建一列
                var $svrCustNametd=$("<td></td>");
                var $svrTitletd=$("<td></td>");
                var $svrTypetd=$("<td></td>");
                var $svrCreateBytd=$("<td></td>");
                var $svrCreateDatetd=$("<td ></td>");
                var $svrStatustd=$("<td></td>");
                var $cz=$("<td></td>");

                //给td赋值
                $svrCustNametd.text(cstservice.svrCustName);
                $svrTitletd.text(cstservice.svrTitle);
                $svrTypetd.text(cstservice.svrType);
                $svrCreateBytd.text(cstservice.svrCreateBy);
                $svrCreateDatetd.text(cstservice.svrCreateDate);
                //判断是否是服务状态新创建
                if(cstservice.svrStatus=="新创建"){
                    $svrStatustd.html("<input style='display: none;' name='svrCustName' value='"+cstservice.svrCustName+"'/>" +
                        "<input style='display: none;' name='svrTitle' value='"+cstservice.svrTitle+"'/>" +
                        "<input style='display: none;' name='svrType' value='"+cstservice.svrType+"'/>" +
                        "<input style='display: none;' name='createDate' value='"+cstservice.svrCreateDate+"'/>" +
                        "<input style='display: none;' name='svrId' value='"+cstservice.svrId+"'/>" +
                        "<input style='display: none;' name='svrRequest' value='"+cstservice.svrRequest+"'/>" +
                        "<input style='display: none;' name='svrCreateId' value='"+cstservice.svrCreateId+"'/>"+
                        "<input style='display: none;' name='svrCreateBy' value='"+cstservice.svrCreateBy+"'/><c:forEach items='${userList}' var='user' varStatus='statu'><input style='display: none;' name='svrDueId' value='${user.userId}'/></c:forEach><select name='svrDueTo'><c:forEach items='${userList}' var='user' varStatus='status'><option value='${user.userName}'>${user.userName}</option></c:forEach></select><input type='submit' value='确定' />");
                }else{
                    $svrStatustd.text(cstservice.svrStatus);
                }
                $cz.html("<a href='/delCstService?id="+cstservice.svrId+"'>" +
                    "<img src=\"../images/bt_del.gif\" title=\"删除\" style=\"border:0px\" />" +
                    "</a>");
                //把列添加到行
                $tr.append($svrCustNametd);
                $tr.append($svrTitletd);
                $tr.append($svrTypetd);
                $tr.append($svrCreateBytd);
                $tr.append($svrCreateDatetd);
                $tr.append($svrStatustd);
                $tr.append($cz);

                //把行添加到table
                $("#dataTable").append($tr);
            });
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>服务分配</span><br />
    对于客户提出的咨询、建议、投诉等服务分配专人进行处理
</div>

<table class="tableEdit">
    <tr>
        <th>
            客户名称：
        </th>
        <td>
            <input id="CustName" type="text" name/>
        </td>
        <th>
            概要：
        </th>
        <td>
            <input id="Title" type="text" />
        </td>
        <th>
            服务类型：
        </th>
        <td>
            <select id="Type">
                <option value="全部" selected="selected">全部</option>
                <option value="全部">咨询</option>
                <option value="投诉">投诉</option>
                <option value="建议">建议</option>
            </select>
        </td>
    </tr>
    <tr>
        <th>
            创建日期：
        </th>
        <td colspan="3">
            <input  id="StartDate" type="text" value=""/>&nbsp;-&nbsp;<input id="EndDate" type="text" value=""/>
        </td>
        <th>
            状态：
        </th>
        <td>
            <select id="Status">
                <option value="全部">全部</option>
                <option value="新创建" selected="selected">新创建</option>
                <option value="已分配">已分配</option>
                <option value="已处理">已处理</option>
                <option value="已反馈">已反馈</option>
                <option value="已归档">已归档</option>
            </select>
        </td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="8">
            <input type="button" value="查询" onclick="global(1)"/>
        </td>
    </tr>
    </tfoot>
</table>
<div id="dataDiv">
    <div id="headDiv">
    </div>
    <form method="post" action="/updateCstService">
    <table class="dataTable" id="dataTable">
        <tr>
            <th>客户名称</th>
            <th>概要</th>
            <th>服务类型</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>服务分配</th>
            <th>操作</th>
        </tr>
    </table>
</form>
    <div id="pageDiv">
        <div id="leftPage">
            共有<span id="total"></span>条记录，当前第<span id="pageNum"></span>/<span id="totalNum"></span>页
        </div>
        <div id="rightPage">
            <input type="image" src="../images/first.gif" onclick="global(1)"/>
            <input type="image" src="../images/back.gif" id="pre"/>
            <input type="image" src="../images/next.gif" id="next"/>&nbsp;&nbsp;
            <input type="image" src="../images/last.gif" id="last"/>&nbsp;&nbsp;
            转到第<input type="text" size="1" id="inPage" />&nbsp;&nbsp;<input type="image" src="../images/go.gif" id="in"/>
        </div>
    </div>
</div>
</body>
</html>
