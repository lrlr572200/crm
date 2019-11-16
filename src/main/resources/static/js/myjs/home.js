$(function () {
    $('#table').bootstrapTable({
        method: "get",
        striped: true,
        singleSelect: false,
        url: "json/person.json",
        dataType: "json",
        pagination: true, //分页
        pageSize: 10,
        pageNumber: 1,
        search: false, //显示搜索框
        contentType: "application/x-www-form-urlencoded",
        queryParams: null,
        columns: [
            {
                title: "任务名称",
                field: 'name',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '完成日期',
                field: 'sex',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '发布人',
                field: 'type',
                align: 'center'
            },

            {
                title: '备注',
                field: 'class',
                align: 'center'
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row) {
                    var e = '<a  href="#" onclick="openlayer(\'' + row.id + '\')">查看</a> ';
                    return e;
                }
            }
        ]
    });
})

$(".title-list ul").on("click", "li", function () {
    var aIndex = $(this).index();
    $(this).addClass("current").siblings().removeClass("current");
    $(".matter-content").removeClass("current").eq(aIndex).addClass("current");
});

$(".duty").find("tbody").find("tr:even").css("backgroundColor", "#eff6fa");

function openlayer(id) {
    layer.open({
        type: 2,
        title: '万年历',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['80%', '75%'],
        shadeClose: true,
        closeBtn: 2,
        content: 'calendar.html'
    });
}