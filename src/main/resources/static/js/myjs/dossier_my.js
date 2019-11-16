function change() {
    var height = $(document).height();
    $(".resource").css("height", height - 36 + "px");
}

$(function () {
    $("#addPup").click(function () {
        $("#pup01").show();


    })
    $("#close01").click(function () {
        $("#pup01").hide();

    })

})



