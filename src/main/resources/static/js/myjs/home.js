$(function () {
    day()
})

$(".title-list ul").on("click", "li", function () {
    var aIndex = $(this).index();
    $(this).addClass("current").siblings().removeClass("current");
    $(".matter-content").removeClass("current").eq(aIndex).addClass("current");
});

$(".duty").find("tbody").find("tr:even").css("backgroundColor", "#eff6fa");

function rili() {
    /*location.href="/sys/rili.html"*/
    $("#saveModal").modal('show')
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

function add() {
    //显示模态框的方法
    $("#saveModal").modal().on("shown.bs.modal",function () {

    })
}