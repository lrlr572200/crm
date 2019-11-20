
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
    var states=$("#demo").val();
    var userName=$("#noticeTit").val();
    var phone=$("#noticePuer").val();
    var data="pageIndex="+pageIndex+"&states="+states+
        "&userName="+userName
        + "&phone="+phone;
    $.post("/sys/userByEmp.html",data,function (relData) {
        var list = relData.dataList;

        var str="";
        for(var i=0;i<list.length;i++)
        {
            str+="<tr>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \" id='userId'>"+list[i].userId+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><a class=\"th-inner \" id='userName' href='/sys/care.html?userId="+list[i].userId+"'>"+list[i].userName+"</a><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"TITLE\" tabindex=\"0\"><div class=\"th-inner \" id='strSex'>"+list[i].strSex+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"PUBLISHER\" tabindex=\"0\"><div class=\"th-inner \" id='phone'>"+list[i].phone+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \" id='states'>"+list[i].states+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \" id='source'>"+list[i].source+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].userType+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].creditGrade+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].addTime+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \" id='remark'>"+list[i].remark+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"\" tabindex=\"0\"><div class=\"th-inner \">" +
                "<input type='button' value='编辑' onclick='edit("+list[i].userId+")' id='edit'>" +
                "<input type='button' value='删除' onclick='del("+list[i].userId+")' id='del'>" +
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

function edit(id) {
    var data="userId="+id;
    $("option[value]").removeAttr("selected");
    $.post("/sys/findUserName.html",data,function (relData) {
        $("#userId1").val(relData.userId);
        $("#userName1").val(relData.userName);
        $("#phone1").val(relData.phone);
        $("#remark1").html(relData.remark);
        $("option[value="+relData.states+"]").prop("selected","selected");
        $("option[value="+relData.sex+"]").prop("selected","selected");
        $("option[value="+relData.source+"]").prop("selected","selected");
    },"json")
    add();
}

function del(id) {
    if(confirm("是否删除？"))
    {
        var data="userId="+id;
        $.post("/sys/delUserByEmp.html",data,function (relData) {
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

function add() {
    //显示模态框的方法
    $("#saveModal").modal().on("shown.bs.modal",function () {

    })
}


//显示模态框
$("#saveModal").modal('show');
//隐藏模态框
$("#saveModal").modal('hide');