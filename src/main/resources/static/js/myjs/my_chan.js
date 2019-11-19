
//查找机会来源
function findSource(){
    var str = "<option value=\"\">全部</option>";
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getSource.ajax",//请求的url
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            for(var i=0;i<data.length;i++){
                str+="<option value=\""+data[i]+"\">"+data[i]+"</option>";
            }
            $("#source").html(str);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="../../../templates/sys/err.html";
        }
    })
}

findSource();



//查看我的机会
$('#table').bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/sys/myChan.ajax",
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
            pageSize: params.limit,
            pageNumber:params.pageNumber,
            empCode:$("#empCode").val(),
            source:$("#source").val(),
            states:"已分配"
        }
    }, //当请求数据时，向服务器发送其余的参数
    columns: [ //在JS里面定义，field即data-field，title就是每列表头名等等。默认空数组
        {
            title: "编号",
            field: 'chanId',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '机会名称',
            field: 'chanName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '机会来源',
            field: 'source',
            align: 'center'
        },
        {
            title: '录入时间',
            field: 'addTime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '客户姓名',
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
            title: '联系电话',
            field: 'phone',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '维护者',
            field: 'vindicator',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '所属部门',
            field: 'deptName',
            align: 'center',
            valign: 'middle'
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