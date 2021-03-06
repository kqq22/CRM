<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/29
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>服务反馈</title>
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
                var $svrCreateDatetd=$("<td ></td>");
                var $svrCreateBytd=$("<td></td>");
                var $svrDealDatetd=$("<td ></td>");
                var $cz=$("<td></td>");

                //给td赋值
                $svrCustNametd.text(cstservice.svrCustName);
                $svrTitletd.text(cstservice.svrTitle);
                $svrTypetd.text(cstservice.svrType);
                $svrCreateDatetd.text(cstservice.svrCreateDate);
                $svrCreateBytd.text(cstservice.svrCreateBy);
                $svrDealDatetd.text(cstservice.svrDealDate);
                //判断服务条件是否为已处理
                if(cstservice.svrStatus=="已处理"){
                    $cz.html("<a href='/findCstServiceByIdResult?id="+cstservice.svrId+"'><img src='../images/bt_feedback.gif' style='border:0px' /></a>");
                }
                //把列添加到行
                $tr.append($svrCustNametd);
                $tr.append($svrTitletd);
                $tr.append($svrTypetd);
                $tr.append($svrCreateDatetd);
                $tr.append($svrCreateBytd);
                $tr.append($svrDealDatetd);
                $tr.append($cz);

                //把行添加到table
                $("#dataTable").append($tr);
            });
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>服务反馈</span><br />
    对于客户提出的咨询、建议、投诉等服务进行处理完毕后，记录客户对服务的结果的满意度等
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
                <option value="咨询">咨询</option>
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
                <option value="新创建">新创建</option>
                <option value="已分配">已分配</option>
                <option value="已处理">已处理</option>
                <option value="已反馈" selected="selected">已反馈</option>
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
    <table class="dataTable" id="dataTable">
        <tr>
            <th>客户名称</th>
            <th>概要</th>
            <th>服务类型</th>
            <th>创建时间</th>
            <th>分配人</th>
            <th>分配时间</th>
            <th>处理</th>
        </tr>
    </table>
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
</div>
</body>
</html>
