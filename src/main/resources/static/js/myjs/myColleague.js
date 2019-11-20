
$(function () {
    var pageIndex=$("#pageIndex").val();
    //查找所有部门
    depts();
    page(pageIndex);

})

function button() {
    $("#pageIndex").val(1);
    var pageIndex=$("#pageIndex").val();
    page(pageIndex);
}

function page(pageIndex) {
    $("#pageIndex").val(pageIndex);
    var deptId=$("#deptId").val();
    if(deptId==null)
    {
        deptId=$("#dept").val()
    }
    var data="pageIndex="+pageIndex+"&deptId="+deptId;
    $.post("/sys/Colleagues.html",data,function (relData) {
        var list = relData.dataList;
        var str="";
        for(var i=0;i<list.length;i++)
        {
            str+="<tr>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].empCode+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"ID\" tabindex=\"0\"><a class=\"th-inner \" >"+list[i].empName+"</a><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"TITLE\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].strSex+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; vertical-align: middle; \" data-field=\"PUBLISHER\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].deptName+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].roleName+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].birthday+"</div><div class=\"fht-cell\"></div></th>\n" +
                "<th style=\"text-align: center; \" data-field=\"PUBTIME\" tabindex=\"0\"><div class=\"th-inner \">"+list[i].email+"</div><div class=\"fht-cell\"></div></th>\n" +
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


function depts() {
    var str="";
    $.post("/sys/getDept.ajax",null,function (relData) {
        $("#deptId").html("");
        for(var i=0;i<relData.length;i++)
        {
            if(relData[i].parentId==null){

            }else{
                if(relData[i].deptId==$("#dept").val())
                {
                    str+="<option selected='selected' value='"+relData[i].deptId+"'>"+relData[i].deptName+"</option>"
                }else{
                    str+="<option value='"+relData[i].deptId+"'>"+relData[i].deptName+"</option>"
                }
            }
        }
        $("#deptId").html(str);
    },"json");
}
