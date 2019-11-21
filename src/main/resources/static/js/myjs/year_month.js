

//年份下拉框
function ye(){
    var strA="<option value=\"\">全部</option>";
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getYear.json",//请求的url
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            for(var i=0;i<data.length;i++){
                strA+="<option value=\""+data[i]+"\">"+data[i]+"</option>";
            }
            $("#planYear").html(strA);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="/templates/sys/err.html";
        }
    })
};
ye();



//如果获得年份则可以获得月份，否则不能选择月份
$("#planYear").change(function(){
    var year =  $("#planYear").val();
    var strB="<option value=\"\">全部</option>";
    if (year!='' && year!=null){
        $.ajax({
            type:"GET",//请求类型
            url:"/sys/getMonth.json",//请求的url
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                for(var i=0;i<data.length;i++){
                    strB+="<option value=\""+data[i]+"\">"+data[i]+"</option>";
                }
                $("#planMonth").html(strB);

            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                location.href="/sys/err.html";
            }
        })
    } else{
        var options = "<option value=\"\">--请先选择年份--</option>";
        $("#planMonth").html(options);
    }
});