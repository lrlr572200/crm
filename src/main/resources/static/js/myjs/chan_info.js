$('#table').bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/sys/chan_info.ajax",
    striped: true, //隔行换色。默认false，当设为true则每行表格的背景会显示灰白相间
    dataField:"data",  //返回的实体数据的键
    dataType: "json", //服务器获取的数据类型，默认为json格式字符串
    pagination: true, //分页条，设为true表示显示
    pageSize: 5, //页大小。默认每页显示10条记录。前提是启用了分页功能。
    pageList:[],
    uniqueId:"chanId", //每一行的唯一标识
    sidePagination:"server",
    search: false, //搜索框。在搜索框内只要输入内容即开始搜索。默认false不显示表格右上方搜索框 ，可设为true，
    contentType: "application/x-www-form-urlencoded", //请求数据的contentType（内容类型）,默认application/json，用来告诉接收端从服务器发来的消息是序列化后的json字符串
    queryParams: function (params) {
        return {
            pageIndex: params.offset,
            pageSize: params.limit/*,
            deptId:$("#deptId").val(),
            states:"未分配"*/
        }
    }, //当请求数据时，向服务器发送其余的参数
    columns: [ //在JS里面定义，field即data-field，title就是每列表头名等等。默认空数组

        {
            title: "用户名",
            field: 'userName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '性别',
            field: 'strSex',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '内容',
            field: 'chanName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '来源',
            field: 'source',
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
            title: '状态',
            field: 'states',
            align: 'center',
            valign: 'middle'
        },
        
        {
            title: '分配员工',
            field: 'empName',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row) {  //数据转换函数
                var e = '<button button="#" mce_href="#" onclick="alt(\'' + row.chanId + '\')">分配</button> ';
                return e;
            }
        }
    ],
    pagination:true,
    locale:'zh-CN',

});
var row;

//分配资源给员工
function alt(obj) {
    row = $("#table").bootstrapTable("getRowByUniqueId",obj);
    $("#updateModal").modal('show');
}

//修改时——模态框中的保存和提交按钮的方法
function updateChan() {
    $("#updateModal").modal('hide');
    var chanId = row.chanId;
    var empCode =$("#allotChan").val();
    var data = {"chanId":chanId,"empCode":empCode,"states":"已分配"};
    $.post("/sys/updateChan.ajax",data,function (sign) {
        if (sign>0){
            //刷新表格
            $('#table').bootstrapTable("refreshOptions",{pageNumber:1})
            $('#table').bootstrapTable("refresh");
            $("#info-modal").html("分配成功！");
            $("#alertModel").modal('show');
        } else{
            $("#info-modal").html("分配失败！");
            $("#alertModel").modal('show');
        }
    },"json");//异步添加
}



