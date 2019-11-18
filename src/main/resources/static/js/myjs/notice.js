
$(function () {
    var pageIndex=$("#pageIndex").val();
    /*alert("加载页面"+pageIndex);*/
    page(pageIndex);

})

function button() {
    $("#pageIndex").val(1);
    var pageIndex=$("#pageIndex").val();
    page(pageIndex);
}

function page(pageIndex) {
    $("#pageIndex").val(pageIndex);
    var publishTime=$("#demo").val();
    var title=$("#noticeTit").val();
    var empName=$("#noticePuer").val();
    var data="pageIndex="+pageIndex+"&title="+title+
                "&empName="+empName
                + "&publishTime="+publishTime;
    $.post("/sys/notice.html",data,function (relData) {
        var list = relData.dataList;

        var str="";
        for(var i=0;i<list.length;i++)
        {
            str+="<tr>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].noticeId+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><a class=\"th-inner \" href="+list[i].noticeId+"'/templates/sys/anotice.html?noticeId='>"+list[i].theme+"</a><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"TITLE\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].title+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"PUBLISHER\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].empName+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].publishTime+"</div><div class=\"fht-cell\"></div></th>\n" +
                /*"<th style=\"text-align: center; \" data-field=\"PUBCONENT\" tabindex=\"0\"><div  class=\"th-inner mains\">"+list[i].mains+"</div><div class=\"fht-cell\"></div></th>\n" +*/
                "<th style=\"text-align: center; \" data-field=\"\" tabindex=\"0\"><div class=\"th-inner \">" +
                /*"<input type='button' value='编辑' onclick='edit("+list[i].noticeId+")' id='edit'>" +*/
                "<input type='button' value='删除' onclick='del("+list[i].noticeId+")' id='del'>" +
                "</div><div class=\"fht-cell\"></div></th>\n" +
                "</tr>"
        }
        $("#table tbody").html(str);
        //alert("当前页数："+pageIndex+",总页数:"+relData.pageCount);
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
                location.href="../../../templates/sys/err.html";
            }else{

            }
        },"json");
    }
}

