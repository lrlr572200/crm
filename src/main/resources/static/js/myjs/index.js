



$(function () {
    alerts()
})

function alerts() {
        $.get("/sys/findCareByNow.html",null,function (relDate) {
            if(relDate>0)
            {
                alert("您近期有"+relDate+"条关怀计划未执行")
            }
        },"json")
}