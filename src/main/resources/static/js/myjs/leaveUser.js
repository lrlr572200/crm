
$(function () {

    var pageIndex=$("#pageIndex").val();
    page(pageIndex);

})

function button() {
    $("#pageIndex").val(1);
    var pageIndex=$("#pageIndex").val();
    page(pageIndex);
}

function page(pageIndex) {
    $("#pageIndex").val(pageIndex);
    var data="pageIndex="+pageIndex;
    $.post("/sys/leaveUsers.html",data,function (relData) {
        var list = relData.dataList;
        var str="<form>";
        for(var i=0;i<list.length;i++)
        {
            str+="<tr>\n" +
                /*"<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].userId+"</div><div class=\"fht-cell\"></div></th>\n" +*/
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \"><input type='checkbox' name='userIds' value='"+list[i].userId+"'></div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \" >"+list[i].userName+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"TITLE\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].strSex+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"PUBLISHER\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].phone+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].states+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].source+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].userType+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].creditGrade+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].addTime+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].empCode+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"\" tabindex=\"0\"><div class=\"th-inner \">" ;
            /*if($("#grade").val()>1)
            {
                str+=  "<a class='button1'  href='/sys/updateOrder.html?orderId="+list[i].orderId+"' >编辑</a>" ;
            }else{
                str+= "<a class='button1' href='#' onclick='del("+list[i].orderId+")' id='del' >删除</a>" ;
            }
            str+= "</div><div class=\"fht-cell\"></div></th>\n" +*/
                "</tr>"
        }
        $("#table tbody").html(str);
        var first=1;
        var last=eval($("#pageIndex").val()+"-"+1);
        var next=eval($("#pageIndex").val()+"+"+1);
        var final=relData.pageCount;
        var str2="";
        if($("#pageIndex").val()==1)
        {
            if(relData.pageCount>1)
            {
                str2+="当前页码:"+$("#pageIndex").val()+"/"+relData.pageCount+
                    "<a onclick='page("+next+")'>下一页</a>"+
                    "<a onclick='page("+final+")'>末页</a>";
            }
        }else if($("#pageIndex").val()==relData.pageCount){
            str2+="<a onclick='page("+first+")'>首页</a>"+
                "<a onclick='page("+last+")'>上一页</a>"+
                "当前页码:"+$("#pageIndex").val()+"/"+relData.pageCount;
        } else{
            str2+="<a onclick='page("+first+")'>首页</a>"+
                "<a onclick='page("+last+")'>上一页</a>"+
                "当前页码:"+$("#pageIndex").val()+"/"+relData.pageCount+
                "<a onclick='page("+next+")'>下一页</a>"+
                "<a onclick='page("+final+")'>末页</a>";
        }
        $("#page").html(str2);
    },"json");

}

function checks() {
    var element=document.getElementsByName("userIds");
    var checkList="";
    for(var i=0;i<element.length;i++)
    {
        if(element[i].checked==true)
        {
            if(checkList=="")
            {
                checkList=element[i].value;
            }else{
                checkList=checkList+","+element[i].value;
            }
        }
    }
    location.href="/sys/updateLeaveUsers.html?userIds="+checkList+"&empCode="+$("#emp").val();
}

/*************/
function add() {
    //显示模态框的方法
    $("#saveModal").modal().on("shown.bs.modal",function () {

    })
}
//获取所有员工编号
function empCode() {

}