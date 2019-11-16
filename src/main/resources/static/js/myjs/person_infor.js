

//异步实现分页检索查询的方法
function getUserInfo(obj) {
    var empCode = document.getElementById("empCode").value;
    var states = document.getElementById("states").value;
    var userType = document.getElementById("userType").value;
    var source = document.getElementById("source").value;
    var creditGrade = document.getElementById("creditGrade").value;
    var beginTime = document.getElementById("beginTime").value;
    var endTime = document.getElementById("endTime").value;
    var pageIndex = obj;

    var data = {"empCode":empCode,"states":states,"userType":userType,"source":source,
        "creditGrade":creditGrade,"beginTime":beginTime,"endTime":endTime,"pageIndex":pageIndex};

    $.post("/sys/user.html",data,function (userPage) {
        $("#pageIndex").val(userPage.pageIndex);//给pageIndex隐藏域赋值
        var pageindex = $("#pageIndex").val();//取出pageIndex隐藏域赋中的值
        var infoTable = $("#info-list");  //获取展示客户信息的table标签
        var pageTable = $("#info-list1"); //获取分页表的table标签
        //将两个table的标签内容置空
        infoTable.html("");
        pageTable.html("");
        var info = userPage.dataList; //获取查出来的结果
        if(info.length<=0 || info==null || info==''){  //如果没有取到结果
            var str = "暂无...";
            infoTable.html(str);
        }else{  //如果取到结果循环拼接字符串
            var StrA = "<tr height=\"25px\"><td>编号</td><td>姓名</td><td>性别</td><td>联系方式</td><td>注册时间</td>"
                +"<td>状态</td><td>类型</td><td>客户来源</td><td>信誉等级</td></tr>";
            for (var i=0;i<info.length;i++){
                StrA +="<tr height=\"25px\">\n" +
                    "<td>"+info[i].userId+"</td>\n" +
                    "<td>"+info[i].userName+"</td>\n" +
                    "<td>"+info[i].strSex+"</td>\n" +
                    "<td>"+info[i].phone+"</td>\n" +
                    "<td>"+info[i].addTime+"</td>\n" +
                    "<td>"+info[i].states+"</td>\n" +
                    "<td>"+info[i].userType+"</td>\n" +
                    "<td>"+info[i].source+"</td>\n" +
                    "<td>"+info[i].creditGrade+"</td>\n" +
                    "</tr>";
            } //循环拼接字符串结束
            infoTable.html(StrA);
            var StrB = "<tr height=\"25px\"><td colspan=\"9\">";
            var previouspage  = pageindex-1; //当前页-1
            var nextpage = eval(pageindex+"+"+"1"); //当前页加1
            if(userPage.pageIndex>1){
                StrB +="<span class=\"bt1\" onclick=\"getUserInfo("+1+")\">首页</span>" +
                    "<span class=\"bt1\" onclick=\"getUserInfo("+previouspage+")\">上一页</span>";
            }
            StrB += "<span>第"+pageindex+"页——共"+userPage.pageCount+"页</span>";
            if (pageindex<userPage.pageCount){
                StrB +="<span class=\"bt1\" onclick=\"getUserInfo("+nextpage+")\">下一页</span>" +
                    "<span class=\"bt1\" onclick=\"getUserInfo("+userPage.pageCount+")\">末页</span>";
            }
            StrB +="</td></tr>";
            pageTable.html(StrB);
        }
    },"json");//异步结束
}//分页方法结束


//调用异步实现分页检索查询的方法
getUserInfo(1);








