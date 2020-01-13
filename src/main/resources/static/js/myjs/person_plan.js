



//查看我的销售计划
$('#table').bootstrapTable({
        method: "post", //向服务器请求方式，默认为’get’，可选’post’
        url:"/sys/plan.ajax",
        striped: true, //隔行换色。默认false，当设为true则每行表格的背景会显示灰白相间
        dataField:"data",  //返回的实体数据的键
        dataType: "json", //服务器获取的数据类型，默认为json格式字符串
        pagination: true, //分页条，设为true表示显示
        pageSize: 5, //页大小。默认每页显示10条记录。前提是启用了分页功能。
        pageList:[],
        uniqueId:"planId", //每一行的唯一标识
        sidePagination:"server",
        search: false, //搜索框。在搜索框内只要输入内容即开始搜索。默认false不显示表格右上方搜索框 ，可设为true，
        contentType: "application/x-www-form-urlencoded", //请求数据的contentType（内容类型）,默认application/json，用来告诉接收端从服务器发来的消息是序列化后的json字符串
        queryParams: function (params) {
            return {
                pageIndex: params.offset,
                pageSize: params.limit,
                pageNumber:params.pageNumber,
                planYear: $("#planYear").val(),
                planMonth: $("#planMonth").val(),
                states: $("#states").val(),
                empCode: $("#empCode").val(),
                deptId:$("#deptId").val()
            }
        }, //当请求数据时，向服务器发送其余的参数
        columns: [ //在JS里面定义，field即data-field，title就是每列表头名等等。默认空数组
            {
                title: "编号",
                field: 'planId',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '提要',
                field: 'content',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '创建者',
                field: 'createEmpName',
                align: 'center'
            },
            {
                title: '年份',
                field: 'planYear',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '月份',
                field: 'planMonth',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '计划销售额',
                field: 'planMoney',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '计划发展客户数量',
                field: 'planAdd',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '审核者',
                field: 'auditEmpName',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '状态',
                field: 'states',
                align: 'center'
            },

            {
                title: '操作',
                field: '',
                align: 'center',
                formatter: function (value, row) {  //数据转换函数  \'' + row.planId + '\'
                    var e = '<button button="#" mce_href="#" class="del" onclick="del('+row.planId+',\''+row.states+'\')">删除</button> '
                    var d = '<button button="#" mce_href="#" class="edit" onclick="edit('+row.planId+',\''+row.states+'\')">编辑</button> ';
                    return e + d;
                }
            }
        ],
        pagination:true,
        locale:'zh-CN',

});



//点击条件检索按钮
function but(){
    //刷新表格并回到第一页
    $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
    $('#table').bootstrapTable("refresh");
}



//添加的方法
function add() {
        var date1=new Date();
        var data={"planYear":date1.getFullYear(),"planMonth":date1.getMonth()};
        $.post("/sys/findDynPlan.json",data,function (map) {
            if (map.length>0){
                $("#info-modal").html("已有当前月份的计划！");
                $("#alertModel").modal('show');
            }else{
                $("#myModalLabel").html("添加销售计划");
                $("#state").val("新创建");
                var date=new Date();
                var yyyy=date.getFullYear();//年
                var MM=date.getMonth();//月
                MM=eval(MM+"+"+"1");
                $("#plan_Year").val(yyyy);
                $("#plan_Month").val(MM);
                $("#updRefer").hide();
                $("#updRefer1").hide();
                $("#save").show();
                $("#refer").show();
                $("#planAdd").val("");
                $("#content").val("");
                $("#planMoney").val("");

                //显示模态框的方法
                $("#saveModal").modal('show');
            }
        },"json");


}





//添加时——模态框中的保存和提交按钮的方法
function addPan(obj) {
    $("#saveModal").modal('hide');
    var planYear =$("#plan_Year").val();
    var planMonth = $("#plan_Month").val();
    var states ;
    var planMoney =$("#planMoney").val();
    var planAdd = $("#planAdd").val();
    var content = $("#content").val();
    if (obj==0){
        states = $("#state").val();
    } else if (obj==1){
        states = "已提交";
    }
    var deptId = $("#deptId").val();
    var rolesId = $("#rolesId").val();
    var empCode = $("#empCode").val();
    if (planMoney==null || planAdd==null || content==null || content=='' || planAdd=='' || planMoney=='' ){
        $("#info-modal").html("不能有空值！");
        $("#alertModel").modal('show');
    }else {
        var data = {"empCode":empCode,"planYear":planYear,"planMonth":planMonth,"states":states,
            "planMoney":planMoney,"planAdd":planAdd,"content":content,"deptId":deptId,"rolesId":rolesId};
        $.post("/sys/addPlan.ajax",data,function (sign) {
            if (sign>0){
                //刷新表格
                $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                $('#table').bootstrapTable("refresh");
            } else{
                $("#info-modal").html("创建失败！");
                $("#alertModel").modal('show');
            }
        },"json");//异步添加
    }

}

var planid;

//修改计划
function edit(planId,states) {
    if (states=="已提交" || states=="已审核"){
        $("#info-modal").html("当前状态的计划单不可修改！");
        $("#alertModel").modal('show');
    }else{
        $("#save").hide();
        $("#refer").hide();
        $("#updRefer").show();
        $("#updRefer1").show();
        $("#myModalLabel").html("编辑我的计划");
        var row = $("#table").bootstrapTable("getRowByUniqueId",planId);
        $("#plan_Year").val(row.planYear);
        $("#plan_Month").val(row.planMonth);
        $("#planMoney").val(row.planMoney);
        $("#state").val(row.states);
        $("#planAdd").val(row.planAdd);
        $("#content").val(row.content);
        planid = row.planId;
        //显示模态框的方法
        $("#saveModal").modal('show');
    }

}

//修改时——模态框中的提交按钮方法
function updPann(obj) {
    $("#saveModal").modal('hide');
    var planMoney =$("#planMoney").val();
    var planAdd = $("#planAdd").val();
    var content = $("#content").val();
    var states ;
    if (obj==0){
        states = $("#state").val();
    } else if (obj==1){
        states = "已提交";
    }
    if (planMoney==null || planAdd==null || content==null || content=='' || planAdd=='' || planMoney=='' ){
        $("#info-modal").html("不能有空值！");
        $("#alertModel").modal('show');
    }else {
        var data = {"planMoney":planMoney,"planAdd":planAdd,"content":content,"planId":planid,"states":states};
        $.post("/sys/updPlan.ajax",data,function (sign) {
            if (sign>0){
                //刷新表格
                $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
                $('#table').bootstrapTable("refresh");
            } else{
                $("#info-modal").html("修改失败！");
                $("#alertModel").modal('show');
            }
        },"json");//异步修改
    }

}

var id ;

//删除
function del(planId,states) {
    if (states!="新创建"){
        $("#info-modal").html("当前状态不允许被删除");
        $("#alertModel").modal('show');
    }else{
        $("#yes").show();
        $("#no").show();
        $("#info-modal").html("是否确定删除当前销售计划？");
        $("#alertModel").modal('show');
        var row =$("#table").bootstrapTable("getRowByUniqueId",planId);
        id=row.planId;
    }
}


//确定删除
function theyes() {
    $("#alertModel").modal('hide');
    var data = "planId="+id;
    $.post("/sys/delPlan.ajax",data,function (sign) {
        if (sign>0){
            //刷新表格
            $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
            $('#table').bootstrapTable("refresh");
        } else{
            $("#info-modal").html("删除失败！");
            $("#alertModel").modal('hide');
            $("#alertModel").modal('show');
        }
    },"json");//异步删除
    //刷新表格
}

//不删除
function theno(){
    $("#alertModel").modal('hide');
}

//查看我部销售计划的方法
function getMeDept() {
    location.href="/sys/deptPlan.ajax";
}