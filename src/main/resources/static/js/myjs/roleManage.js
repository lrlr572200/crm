//角色管理

//BootStrapTable方法体
$('#table').bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/sys/getroleRight.ajax",
    striped: true, //隔行换色。默认false，当设为true则每行表格的背景会显示灰白相间
    dataField:"data",  //返回的实体数据的键
    dataType: "json", //服务器获取的数据类型，默认为json格式字符串
    pagination: true, //分页条，设为true表示显示
    pageSize: 8, //页大小。默认每页显示10条记录。前提是启用了分页功能。
    pageList:[],
    //clickToSelect : true, //是否启用点击选中行
    uniqueId:"rolesId", //每一行的唯一标识
    sidePagination:"server",
    search: false, //搜索框。在搜索框内只要输入内容即开始搜索。默认false不显示表格右上方搜索框 ，可设为true，
    contentType: "application/x-www-form-urlencoded", //请求数据的contentType（内容类型）,默认application/json，用来告诉接收端从服务器发来的消息是序列化后的json字符串
    queryParams: function (params) {
        return {
            pageIndex: params.offset,
            pageSize: params.limit,
            pageNumber:params.pageNumber,
            main:$("#main").val()

        }
    }, //当请求数据时，向服务器发送其余的参数
    columns: [
        {
            title: "角色编号",
            field: 'rolesId',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '角色名称',
            field: 'rolesName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '权限等级',
            field: 'grade',
            align: 'center'
        },
        {
            title: '权限内容',
            field: 'main',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '操作',
            field: '',
            align: 'center',
            formatter: function (value, row) {  //数据转换函数
                var e = '<button button="#" mce_href="#" class="del" onclick="del(\'' + row.rolesId + '\')">删除</button> '
                var d = '<button button="#" mce_href="#" class="edit" onclick="edit(\'' + row.rolesId + '\')">编辑</button> ';
                return e + d;
            }
        }
    ],
    pagination:true,
    locale:'zh-CN',

});

//条件查询
function togo() {
    //刷新表格并回到第一页
    $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
    $('#table').bootstrapTable("refresh");
}

var sg;
//失去焦点验证是否有相同角色
$("#rolesName").blur(function () {
    var rolesName = $("#rolesName").val();
    if (rolesName == null || rolesName == '') {
        $("#arrInfor").html("不能为空！");
    }else {
        var data = {"rolesName":rolesName};
        $.post("/sys/roleName.json",data,function (sign) {
            sg = sign;
            if (sign > 0) {
                $("#arrInfor").html("不能出现同名角色名！");
            }
        },"json");
    }
});

//失去焦点验证赋权操作
$("#main2").blur(function () {
    var main = $("#main2").val();
    if (main==null || main=='' ){
        $("#mainInfor").html("不能为空！");
    }
});

function chename() {

}


//添加
function add() {
    $("#rolesName").val("");
    $("#main2").val("");
    $("#grade").val("");
    $("#arrInfor").html("");
    $("#mainInfor").html("");
    $("#myModalLabel").html("添加角色");
    $("#add").show();
    $("#upd").hide();
    $("#saveModal").modal('show');
}

//确定添加
function addRole() {
    $("#saveModal").modal('hide');
    var rolesName = $("#rolesName").val();
    var main = $("#main2").val();
    var grade = $("#grade").val();
    if (grade==100){
        $("#info-modal").html("不可添加管理员！")
        $("#alertModel").modal('show');
    }else if (sg>0){
        $("#info-modal").html("不可添加同名角色！")
        $("#alertModel").modal('show');
    }else if (main==null || rolesName==null || grade==null || main=='' || rolesName=='' ){
        $("#info-modal").html("不能为空！")
        $("#alertModel").modal('show');
    } else {
        var data= {"rolesName":rolesName,"main":main,"grade":grade};
        $.post("/sys/addRole.ajax",data,function (data) {
            if (data>0){
                //刷新表格并回到第一页
                $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                $('#table').bootstrapTable("refresh");
            }else{
                $("#info-modal").html("添加失败！")
                $("#alertModel").modal('show');
            }
        },"json");
    }

}

//添加返回
function quit() {
    $("#rolesName").val("");
    $("#main2").val("");
    $("#grade").val("");
    $("#mainInfor").html("");
    $("#arrInfor").html("");
    $("#saveModal").modal('hide');
}

//删除
function del(obj) {
    var data = {"rolesId":obj};
    $.post("/sys/getGradeByRoleid.ajax",data,function (gra) {
        if (gra==100){
            $("#info-modal").html("不可删除管理员！")
            $("#alertModel").modal('show');
        }else {
            if (window.confirm("是否确认删除？")) {
                $.post("/sys/delRole.ajax",data,function (sign) {
                    if (sign > 0) {
                        //刷新表格并回到第一页
                        $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                        $('#table').bootstrapTable("refresh");
                        $("#info-modal").html("删除成功！")
                        $("#alertModel").modal('show');
                    }else {
                        $("#info-modal").html("删除失败！请重试！")
                        $("#alertModel").modal('show');
                    }
                },"json");
            }
        }
    },"json");
}

var row;
//编辑
function edit(obj) {
    $("#rolesName").val("");
    $("#main2").val("");
    $("#grade").val("");
    $("#arrInfor").html("");
    $("#mainInfor").html("");
    row = $("#table").bootstrapTable("getRowByUniqueId",obj);
    var rolesName = $("#rolesName").val(row.rolesName);
    $("#main2 option[value="+row.main+"]").prop("selected","selected");
    $("#grade").html("<option value=\""+row.grade+"\">"+row.grade+"</option>");
    $("#myModalLabel").html("编辑角色");
    $("#upd").show();
    $("#add").hide();
    $("#saveModal").modal("show");
}


//确认编辑
function updRole() {
    $("#saveModal").modal('hide');
    var rolesName = $("#rolesName").val();
    var main = $("#main2").val();
    var grade = $("#grade").val();
    if (row.grade==100){
        $("#info-modal").html("不可修改管理员！")
        $("#alertModel").modal('show');
    }else if (sg>0){
        $("#info-modal").html("已有同名角色，不可提交！")
        $("#alertModel").modal('show');
    }else if (main==null || rolesName==null || grade==null || main=='' || rolesName=='' ){
        $("#info-modal").html("不能为空！")
        $("#alertModel").modal('show');
    } else {
        var rolesId = row.rolesId;
        var data = {"rolesId":rolesId,"rolesName":rolesName,"main":main,"grade":grade};
        $.post("/sys/updRole.ajax",data,function (sign) {
            if (sign>0){
                //刷新表格并回到第一页
                $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                $('#table').bootstrapTable("refresh");
            }else {
                $("#info-modal").html("编辑失败！")
                $("#alertModel").modal('show');
            }
        },"json");
    }
}

//获得角色权限下拉框
function getGrade() {
    var strA="<option value=\"\">全部</option>";
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getRightMain.ajax",//请求的url
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            for(var i=0;i<data.length;i++){
                strA+="<option value=\""+data[i]+"\">"+data[i]+"</option>";
            }
            $("#main").html(strA);
            $("#main2").html(strA);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="/sys/err.html";
        }
    });
}
getGrade();

//权限等级和权限的级联
$("#main2").change(function(){
    var main = $("#main2").val();
    if(main!='' && main!=null ){
        $.ajax({
            type:"GET",//请求类型
            url:"/sys/getMain.json",//请求的url
            data:"main="+main,
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                var strB="<option value=\""+data.grade+"\">"+data.grade+"</option>";
                $("#grade").html(strB);
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                location.href="/sys/err.html";
            }
        });//异步结束
    }else {
        var strA="<option value=\"\">全部</option>";
        $("#grade").html(strA);
    }
});