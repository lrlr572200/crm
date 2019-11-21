//权限管理

$("#table").bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/sys/findAllRight.ajax",
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

        }
    }, //当请求数据时，向服务器发送其余的参数
    columns: [
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
            formatter: function (value, row) {  //数据转换函数
                var e = '<button button="#" mce_href="#" class="del" onclick="del(\'' + row.planId + '\')">删除</button> '
                var d = '<button button="#" mce_href="#" class="edit" onclick="edit(\'' + row.planId + '\')">编辑</button> ';
                return e + d;
            }
        }
    ],
    pagination:true,
    locale:'zh-CN',
});