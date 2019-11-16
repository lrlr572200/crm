

//查看我的机会
$('#table').bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/sys/myServe.ajax",
    striped: true, //隔行换色。默认false，当设为true则每行表格的背景会显示灰白相间
    dataField:"data",  //返回的实体数据的键
    dataType: "json", //服务器获取的数据类型，默认为json格式字符串
    pagination: true, //分页条，设为true表示显示
    pageSize: 5, //页大小。默认每页显示10条记录。前提是启用了分页功能。
    pageList:[],
    uniqueId:"serveId", //每一行的唯一标识
    sidePagination:"server",
    search: false, //搜索框。在搜索框内只要输入内容即开始搜索。默认false不显示表格右上方搜索框 ，可设为true，
    contentType: "application/x-www-form-urlencoded", //请求数据的contentType（内容类型）,默认application/json，用来告诉接收端从服务器发来的消息是序列化后的json字符串
    queryParams: function (params) {
        return {
            pageIndex: params.offset,
            pageSize: params.limit,
            pageNumber:params.pageNumber,
            states: "待处理",
            empCode: $("#empCode").val(),
            userName:$("#userName").val()

        }
    }, //当请求数据时，向服务器发送其余的参数
    columns: [ //在JS里面定义，field即data-field，title就是每列表头名等等。默认空数组
        {
            title: "编号",
            field: 'serveId',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '创建者',
            field: 'empName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '录入时间',
            field: 'addTime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '详情',
            field: 'details',
            align: 'center'
        },
        {
            title: '客户姓名',
            field: 'userName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '联系电话',
            field: 'phone',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '解决者',
            field: 'solvePeople',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '状态',
            field: 'states',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '所属部门',
            field: 'deptName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '操作',
            field: '',
            align: 'center',
            formatter: function (value, row) {
                return '<button button="#" mce_href="#" class="edit" onclick="edit(\'' +row.serveId+'\')">已处理</button>';
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

//已处理的方法
function edit(obj) {
    var empCode = $("#empCode").val();
    var data = {"empCode":empCode,"states":"已处理","serveId":obj};

    $.post("/sys/updServe.ajax",data,function (sign) {
        if (sign>0){
            $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
            $('#table').bootstrapTable("refresh");
            $("#info-modal").html("已经处理！");
            $("#alertModel").modal('show');
        }else {
            $("#info-modal").html("处理失败！");
            $("#alertModel").modal('show');
        }
    },"json");//异步结束
}