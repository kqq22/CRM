<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/8/24
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="../CSS/Style1.css" rel="stylesheet" type="text/css" />
    <link href="../CSS/style2.css" rel="stylesheet" type="text/css" />
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
                url:"/getStorageAll",
                type: "get",//提交方式
                async: true,//是否异步发送
                data:{
                    "pageNum":index,
                    "name":$("#name").val(),
                    "warehouse":$("#warehouse").val()
                },
                success:function(StorageJson){
                    nav(StorageJson);
                }
            });

        }

        /**
         * 显示的数据
         */
        function nav(StorageJson) {
            console.log(StorageJson);
            //清空表格（除第一行数据外）
            $("#dataTable").find("tr:not(:first)").remove();
            //总记录数
            $("#total").html(StorageJson.total);
            //当前页
            $("#pageNum").html(StorageJson.pageNum);
            //总页数
            $("#totalNum").html(StorageJson.pages);
            $pageNum = StorageJson.pageNum;
            //是否有下一页
            $hasNextPage = StorageJson.hasNextPage;
            //上一页
            $perPage = StorageJson.prePage;
            //下一页
            $nextPage = StorageJson.nextPage;
            //最后一页（总页数）
            $lastPage = StorageJson.pages;

            /**
             * 循环读取数据，并将数据写入表格
             */
            $(StorageJson.list).each(function(index,storage){

                //创建一行
                var $tr=$("<tr></tr>");
                //创建一列
                var $prodNametd=$("<td></td>");
                var $stkWarehousetd=$("<td></td>");
                var $stkWaretd=$("<td></td>");
                var $stkCounttd=$("<td></td>");
                var $stkMemotd=$("<td></td>");

                //给td赋值
                $prodNametd.text(storage.prodName);
                $stkWarehousetd.text(storage.stkWarehouse);
                $stkWaretd.text(storage.stkWare);
                $stkCounttd.text(storage.stkCount);
                $stkMemotd.text(storage.stkMemo);

                //把列添加到行
                $tr.append($prodNametd);
                $tr.append($stkWarehousetd);
                $tr.append($stkWaretd);
                $tr.append($stkCounttd);
                $tr.append($stkMemotd);

                //把行添加到table
                $("#dataTable").append($tr);
            });
        }
    </script>
</head>
<body>
<div id="desDiv">
    <span>库存查询</span><br />
    查询产品库存信息
</div>
<table class="tableEdit">
    <tr>
        <th>产品名称：</th>
        <td><input type="text" id="name"/></td>
        <th>仓库：</th>
        <td><input type="text" id="warehouse"/></td>
    </tr>
    <tfoot>
    <tr>
        <td colspan="4"><input type="button" value="查询" onclick="global(1)"/></td>
    </tr>
    </tfoot>
</table>
<br />
<table class="dataTable" id="dataTable">
    <tr>
        <th>产品名称</th>
        <th>仓库</th>
        <th>货位</th>
        <th>件数</th>
        <th>备注</th>
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
</body>
</html>
