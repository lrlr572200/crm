$(function () {
    day()
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

function day() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    $("#year").html(year+"年"+month+"月")
    $("#date").html(strDate)
    $("#year1").html(year+"年"+month+"月")
    $("#date1").html(strDate)
}