

//BootStrapTable方法体
$('#table').bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/sys/getTask.ajax",
    striped: true, //隔行换色。默认false，当设为true则每行表格的背景会显示灰白相间
    dataField:"data",  //返回的实体数据的键
    dataType: "json", //服务器获取的数据类型，默认为json格式字符串
    pagination: true, //分页条，设为true表示显示
    pageSize: 4, //页大小。默认每页显示10条记录。前提是启用了分页功能。
    pageList:[],
    uniqueId:"id", //每一行的唯一标识
    sidePagination:"server",
    search: false, //搜索框。在搜索框内只要输入内容即开始搜索。默认false不显示表格右上方搜索框 ，可设为true，
    contentType: "application/x-www-form-urlencoded", //请求数据的contentType（内容类型）,默认application/json，用来告诉接收端从服务器发来的消息是序列化后的json字符串
    queryParams: function (params) {
        return {
            pageIndex: params.offset,
            pageSize: params.limit,
            pageNumber:params.pageNumber,
            taskKey:"CRON"
        }
    }, //当请求数据时，向服务器发送其余的参数
    columns: [
        {
            title: "编号",
            field: 'id',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '任务',
            field: 'name',
            align: 'center',
            valign: 'middle'
        },
        {
            title: 'cron',
            field: 'value',
            align: 'center'
        },
        {
            title: '状态',
            field: 'declare',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '操作',
            field: '',
            align: 'center',
            formatter: function (value, row) {  //数据转换函数
                var e = '<button button="#" mce_href="#"  onclick="ope(\'' + row.id + '\')">开启</button> ';
                var d = '<button button="#" mce_href="#"  onclick="clo(\'' + row.id + '\')">关闭</button> ';
                var f = '<button button="#" mce_href="#"  onclick="updcron(\'' + row.id + '\')">修改cron</button> ';
                return e + d + f;
            }
        }
    ],
    pagination:true,
    locale:'zh-CN',

});

//开启任务
function ope(obj) {
    var row = $("#table").bootstrapTable("getRowByUniqueId",obj);
    if (row.declare == "CRON_OPEN") {
        $("#info-modal").html("不可重复开启！")
        $("#alertModel").modal("show");
    }else {
        var flg2 = false;
        if (row.id == 4) {
            flg2=confirm("在开启此项前建议先开启部门月报，如不开启可能会产生错误的公司月报！");
        }else {
            flg2 = true;
        }
        if (flg2) {
            var data = {"id":obj,"declare":"CRON_OPEN"};
            $.post("/sys/updTask.ajax",data,function (sign) {
                if (sign>0){
                    $("#info-modal").html("成功开启！")
                    $("#alertModel").modal("show");
                    //刷新表格并回到第一页
                    $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                    $('#table').bootstrapTable("refresh");
                }else {
                    $("#info-modal").html("开启失败！")
                    $("#alertModel").modal("show");
                }
            },"json");
        }

    }
}

//关闭任务
function clo(obj) {
    var row = $("#table").bootstrapTable("getRowByUniqueId",obj);
    if (row.declare == "CRON_CLOSE") {
        $("#info-modal").html("不可重复关闭！")
        $("#alertModel").modal("show");
    }else {
        var flg1 = confirm("是否确定关闭?");
        var flg2 = false;
        if (flg1){
            if (row.id == 3) {
                flg2=confirm("如果关闭该任务可能导致公司月报的生成产生问题。是否继续关闭?");
            }else {
                flg2 = true;
            }
        }
        if (flg1==true && flg2==true){
            var data = {"id":obj,"declare":"CRON_CLOSE"};
            $.post("/sys/updTask.ajax",data,function (sign) {
                if (sign>0){
                    $("#info-modal").html("成功关闭！")
                    $("#alertModel").modal("show");
                    //刷新表格并回到第一页
                    $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                    $('#table').bootstrapTable("refresh");
                }else {
                    $("#info-modal").html("关闭失败！")
                    $("#alertModel").modal("show");
                }
            },"json");
        }
    }

}


var wor;
//修改cron
function updcron(obj) {
    wor=$("#table").bootstrapTable("getRowByUniqueId",obj);
    //$("#cron option[value="+wor.value+"]").prop("selected","selected");
    $("#cronModel").modal("show");
}

//确定修改
function confir() {
    $("#cronModel").modal("hide");
    var cron = $("#cron").val();
    var id = wor.id;
    var other ;
    if (id==3){
        other=4;
    }else {
        other=3;
    }
    if (cron==wor.value){
        $("#info-modal").html("不能选择和当前相同的cron表达式！")
        $("#alertModel").modal("show");
    }else {
        $.ajax({
            type:"GET",//请求类型
            url:"/sys/getCron.json",//请求的url
            data:"id="+other,
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                if (data.value==cron){
                    $("#info-modal").html("不能选择与另一线程相同的cron表达式！")
                    $("#alertModel").modal("show");
                }else {  //开始修改
                    var dat = {"id":id,"value":cron};
                    $.post("/sys/updCron.ajax",dat,function (sign) {
                        if (sign>0){
                            $("#info-modal").html("修改成功！");
                            $("#alertModel").modal("show");
                            //刷新表格并回到第一页
                            $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                            $('#table').bootstrapTable("refresh");
                        }else {
                            $("#info-modal").html("修改失败！");
                            $("#alertModel").modal("show");
                        }
                    },"json");//异步
                }
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                location.href="/sys/err.html";
            }
        });
    }
}

//修改返回
function quit() {
    $("#cronModel").modal("hide");
}




//异步获取cron表达式
function getcron() {
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getCron.json",//请求的url
        data:"declare=KEY_CRON",
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            var strB;
            for (var i=0;i<data.length;i++){
                strB+="<option value=\""+data[i].value+"\">"+data[i].name+"</option>";
            }
            $("#cron").html(strB);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="/sys/err.html";
        }
    });//异步结束
}
getcron();