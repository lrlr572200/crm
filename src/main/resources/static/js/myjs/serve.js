
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
    var addTime=$("#demo").val();
    var phone=$("#noticePuer").val();
    var data="pageIndex="+pageIndex+"&addTime="+addTime+
        "&phone="+phone;
    $.post("/sys/serves.html",data,function (relData) {
        var list = relData.dataList;
        var str="";
        for(var i=0;i<list.length;i++)
        {
            str+="<tr>" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].details+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \" >"+list[i].empName+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"TITLE\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].addTime+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"PUBLISHER\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].states+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].deptName+"</div><div class=\"fht-cell\"></div></th>\n" +
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

function del(id) {
    if(confirm("是否删除？"))
    {
        var data="noticeId="+id;
        $.post("/sys/delNotice.html",data,function (relData) {
            if(relData=="1")
            {
                window.location.reload();
            }else if(relData=="-1"){
                location.href="/sys/err.html";
            }else{

            }
        },"json");
    }
}

