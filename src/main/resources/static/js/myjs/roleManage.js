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


//添加按钮
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

//添加返回按钮
function quit() {
    $("#rolesName").val("");
    $("#main2").val("");
    $("#grade").val("");
    $("#mainInfor").html("");
    $("#arrInfor").html("");
    $("#saveModal").modal('hide');
}

var row;
//编辑按钮
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


//表单验证
function checkName() {
    //obj=0 表示添加  obj=1 表示修改
    var flg = true;
    var rolesName = $("#rolesName").val();
    if (rolesName == null || rolesName == '') {
        flg=false;
        $("#arrInfor").html("不能为空！");
    }
    return flg;
}

function checkRight() {
    var flg = true;
    var main = $("#main2").val();
    if (main == null || main=='') {
        flg=false;
        $("#mainInfor").html("不能为空！");
    }
    return flg;
}

//确定添加按钮
function addRole() {

    var rolesName = $("#rolesName").val();
    var main = $("#main2").val();
    if ((checkRight() == true) && (checkName() == true)) {
        $("#saveModal").modal('hide');
        var data = {"id":main};
        $.post("/sys/getsys.json",data,function (sys) {
            if (sys.value==100){
                $("#info-modal").html("不可添加管理员！")
                $("#alertModel").modal('show');
            }else {

                var dat = {"rolesName":rolesName,"main":main};
                $.post("/sys/addRole.ajax",dat,function (fig) {
                    if (fig>0){
                        //刷新表格并回到第一页
                        $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                        $('#table').bootstrapTable("refresh");
                    }else{
                        $("#info-modal").html("添加失败！")
                        $("#alertModel").modal('show');
                    }
                },"json");
            }
        },"json");
    }
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


//确认编辑
function updRole() {

    var rolesName = $("#rolesName").val();
    var main = $("#main2").val();
    if ((checkRight() == true) && (checkName() == true)) {
        $("#saveModal").modal('hide');
        var data = {"id":main};
        $.post("/sys/getsys.json",data,function (sys) {
            if (sys.value==100){
                $("#info-modal").html("不可编辑管理员！")
                $("#alertModel").modal('show');
            }else {
                var rolesId = row.rolesId;
                var dat = {"rolesId":rolesId,"rolesName":rolesName,"main":main};
                $.post("/sys/updRole.ajax",dat,function (fig) {
                    if (fig>0){
                        //刷新表格并回到第一页
                        $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                        $('#table').bootstrapTable("refresh");
                    }else{
                        $("#info-modal").html("编辑失败！")
                        $("#alertModel").modal('show');
                    }
                },"json");
            }
        },"json");
    }
}



//获得角色权限下拉框
function getGrade() {
    var declare = "ROLE_KEY";
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getRightMain.ajax",//请求的url
        data:"declare="+declare,
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            var strA;
            var strB="<option value=''>全部</option>";
            for(var i=0;i<data.length;i++){
                strA+="<option value=\""+data[i].id+"\">"+data[i].name+"</option>";
                strB+="<option value=\""+data[i].name+"\">"+data[i].name+"</option>";
            }
            $("#main").html(strB);
            $("#main2").html(strA);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="/sys/err.html";
        }
    });
}
getGrade();

