
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
    var userId=$("#userId").val();
    var careTime=$("#careTime").val();
    var data="pageIndex="+pageIndex+"&userId="+userId+
        "&careTime="+careTime;
    $.post("/sys/cares.html",data,function (relData) {
        var list = relData.dataList;
        var str="";
        for(var i=0;i<list.length;i++)
        {
            str+="<tr>\n" +
                /*"<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].noticeId+"</div><div class=\"fht-cell\"></div></th>\n" +*/
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><a class=\"th-inner \" >"+list[i].main+"</a><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"TITLE\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].careTime+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"PUBLISHER\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].result+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].states+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"\" tabindex=\"0\"><div class=\"th-inner \">" +
                "<input type='button' value='编辑' onclick='edit("+list[i].careId+")' id='edit'>" +
                "<input type='button' value='删除' onclick='del("+list[i].careId+")' id='del'>" +
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
        var data="careId="+id;
        $.post("/sys/delCare.html",data,function (relData) {
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

function edit(id) {
    var data="careId="+id;
    $("option[value]").removeAttr("selected");
    $.post("/sys/findCareById.html",data,function (relData) {
        $("#main").val(relData.main);
        $("#careTime1").val(relData.careTime);
        $("option[value="+relData.states+"]").prop("selected","selected");
        $("#result").val(relData.result);
        $("#careId").val(id);
    },"json")
    add();
}

/*<td>生日：</td>
<td  style="text-align: left">
    <input class="Wdate" type="text" onClick="WdatePicker()" name="birthday" id="birthday"   th:value="${#dates.format(emp.birthday,'yyyy/MM/dd')}" >
    </td>*/


function add() {
    //显示模态框的方法
    $("#saveModal").modal().on("shown.bs.modal",function () {

    })
}