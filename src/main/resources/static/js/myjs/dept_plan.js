//查看我部销售计划
$('#table').bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/sys/deptPlan.ajax",
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
            title: '部门',
            field: 'deptName',
            align: 'center',
            valign: 'middle'
        }
    ],
    pagination:true,
    locale:'zh-CN',

});

function deptName(){
    var deptId =$("#dept_id").val();
    var strA="<option value=\"\">全部</option>";
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getSuborDept.ajax",//请求的url
        data:{"deptId":deptId},
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            for(var i=0;i<data.length;i++){
                strA+="<option value=\""+data[i].deptId+"\">"+data[i].deptName+"</option>";
            }
            $("#deptId").html(strA);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="/sys/err.html";
        }
    })
}
//获取部门
deptName();

//点击条件检索按钮
function but(){
    //刷新表格并回到第一页
    $('#table').bootstrapTable("refresh",{ query: {pageIndex:0}});
}

//点击返回
function goOut() {
    history.go(-1);
}