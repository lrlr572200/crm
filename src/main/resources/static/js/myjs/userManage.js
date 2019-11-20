
var overAllIds = new Array(); //全局数组  用来存所有复选框选中的ID

function examine(type, datas) {
    if (type.indexOf('uncheck') == -1) {
        $.each(datas,
            function(i, v) {
                // 添加时，判断一行或多行的 id 是否已经在数组里 不存则添加　
                overAllIds.indexOf(v.empCode) == -1 ? overAllIds
                    .push(v.empCode) : -1;
            });
    } else {
        $.each(datas, function(i, v) {
            overAllIds.splice(overAllIds.indexOf(v.empCode), 1); //删除取消选中行
        });
    }
}

$('#table')
    .on(
        'uncheck.bs.table check.bs.table check-all.bs.table uncheck-all.bs.table',
        function(e, rows) {
            var datas = $.isArray(rows) ? rows : [ rows ]; // 点击时获取选中的行或取消选中的行
            examine(e.type, datas); // 保存到全局 Array() 里
        });


//显示用户信息的方法
$('#table').bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/sys/empManage.ajax",
    striped: true, //隔行换色。默认false，当设为true则每行表格的背景会显示灰白相间
    dataField:"data",  //返回的实体数据的键
    dataType: "json", //服务器获取的数据类型，默认为json格式字符串
    pagination: true, //分页条，设为true表示显示
    pageSize: 10, //页大小。默认每页显示10条记录。前提是启用了分页功能。
    pageList:[],
    clickToSelect : true, //是否启用点击选中行
    uniqueId:"empCode", //每一行的唯一标识
    maintainSelected: true,  //设置为true可在翻页时保留选中
    sidePagination:"server",
    search: false, //搜索框。在搜索框内只要输入内容即开始搜索。默认false不显示表格右上方搜索框 ，可设为true，
    contentType: "application/x-www-form-urlencoded", //请求数据的contentType（内容类型）,默认application/json，用来告诉接收端从服务器发来的消息是序列化后的json字符串
    queryParams: function (params) {
        return {
            pageIndex: params.offset,
            pageSize: params.limit,
            pageNumber:params.pageNumber,
            empCode:$("#empCode").val(),
            deptId:$("#deptId").val(),
            rolesId:$("#rolesId").val(),
            states:$("#states").val(),
        }
    }, //当请求数据时，向服务器发送其余的参数
    columns: [
        {
            field: 'selectItem',
            checkbox: true,
            formatter: function (i, row) { // 每次加载 checkbox 时判断当前 row 的 id 是否已经存在全局 Set() 里
                if ($.inArray(row.empCode,
                    overAllIds) != -1) {// 因为 判断数组里有没有这个 id
                    return {
                        checked: true
                        // 存在则选中
                    }
                }
            }
        },
        {
            title: "员工编号",
            field: 'empCode',
            align: 'center',
            valign: 'middle',
        },
        {
            title: "姓名",
            field: 'empName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: "部门",
            field: 'deptName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: "职位",
            field: 'roleName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: "入职时间",
            field: 'entryTime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: "状态",
            field: 'states',
            align: 'center',
            valign: 'middle'
        },
        {
            title: "薪资",
            field: 'salary',
            align: 'center',
            valign: 'middle'
        },
        {
            title: "绩效分",
            field: 'performance',
            align: 'center',
            valign: 'middle'
        },
        {
            title: "邮箱",
            field: 'email',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '操作',
            field: '',
            align: 'center',
            formatter: function (value, row) {
                return '<button button="#" mce_href="#" class="edit" onclick="upd(\''+row.empCode+'\')">编辑</button> ';
            }
        }
    ],
    onDblClickRow: function (row) {  //行双击触发事件
        $("#head").attr("src","/images/image.jpg");
        //双击行，显示模态框。在模态框中显示详细资料
        var age = 0;
        if(row.birthday!=null && row.birthday!=''){
            var date = new Date();
            var strBirthdayArr=row.birthday.split("-");
            age = date.getFullYear()-strBirthdayArr[0];
        }
        var isn = "未修改";
        if (row.alert>0){
            isn="已修改";
        }
        $("#name").html(row.empName);
        $("#sex").html(row.strSex);
        $("#id").html(row.empCode);
        $("#dept").html(row.deptName);
        $("#pwd").html(row.password);
        $("#roles").html(row.roleName);
        $("#sta").html(row.states);
        $("#sal").html(row.salary);
        $("#bir").html(age);
        $("#per").html(row.performance);
        $("#isn").html(isn);
        $("#emai").html(row.email);
        $("#entt").html(row.entryTime);
        if (row.head!='' && row.head!=null ){
            $("#head").attr("src","../"+row.head);
        }
        $("#saveModal").modal('show');

    },
    pagination:true,
    locale:'zh-CN',
});




//点击条件检索按钮
function but(){
    //刷新表格并回到第一页
    $('#table').bootstrapTable("refreshOptions",{pageNumber:1});
    $('#table').bootstrapTable("refresh");
}

//添加的方法
function add() {
    location.href="../../../templates/sys/add_emp.html";
}

//删除的方法
function del() {
    if (!confirm("是否确认删除？"))
        return;
    var rows = $("#table").bootstrapTable('getSelections');// 获得要删除的数据
    if (rows.length == 0) {// rows 主要是为了判断是否选中，下面的else内容才是主要
        $("#info-modal").html("请先选择要删除的记录!")
        $("#alertModel").modal('show');
        return;
    } else {
        var code = $("#code").val();
        var empCodeArr = new Array();// 声明一个数组
        /*var headArr = new Array();
        var roleArr = new Array();*/
        var stp = 0;
        $(rows).each(function() {// 通过获得别选中的来进行遍历
            if (this.empCode==code){
                empCodeArr=[];
                stp=1;
                return;
            }
            empCodeArr.push(this.empCode);// 获得到的整条数据中的主键列
        });
        if (stp==1){
            $("#info-modal").html("不能删除自己！")
            $("#alertModel").modal('show');
        } else if (stp == 0) {
            deleteMs(empCodeArr);
        }
    }
}

//删除
function deleteMs(code) {
    $.ajax({
        url : "/sys/del_emp.ajax",
        data : "empCodeArr="+code,
        type : "post",
        dataType : "json",
        success : function(sign) {
            if (sign>0){
                $("#info-modal").html("删除成功！")
                $("#alertModel").modal('show');
                //刷新表格并回到第一页
                $('#table').bootstrapTable("refreshOptions",{pageNumber:1});
                $('#table').bootstrapTable("refresh");
            }else{
                $("#info-modal").html("删除失败！")
                $("#alertModel").modal('show');
            }
        },
        error:function () {
            location.href="/sys/err.html"
        }
    });
}

//修改的方法
function upd(obj) {
    location.href="/sys/upd_emp.html?empCode="+obj;
}


//获取部门
function getDept() {
    var strA="<option value=\"\">全部</option>";
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getDept.ajax",//请求的url
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            for(var i=0;i<data.length;i++){
                strA+="<option value=\""+data[i].deptId+"\">"+data[i].deptName+"</option>";
            }
            $("#deptId").html(strA);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="../../../templates/sys/err.html";
        }
    });
}

getDept();

//获取角色
function getRoles() {
    var strA="<option value=\"\">全部</option>";
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getRoles.ajax",//请求的url
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            for(var i=0;i<data.length;i++){
                strA+="<option value=\""+data[i].rolesId+"\">"+data[i].rolesName+"</option>";
            }
            $("#rolesId").html(strA);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="../../../templates/sys/err.html";
        }
    });
}

getRoles();

//查找全部状态
function getEmpStates() {
    var strA="<option value=\"\">全部</option>";
    $.ajax({
        type:"GET",//请求类型
        url:"/sys/getEmpStates.ajax",//请求的url
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            for(var i=0;i<data.length;i++){
                strA+="<option value=\""+data[i]+"\">"+data[i]+"</option>";
            }
            $("#states").html(strA);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            location.href="../../../templates/sys/err.html";
        }
    });
}
getEmpStates();