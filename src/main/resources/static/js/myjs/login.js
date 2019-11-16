$(function () {
    $("#login_btn").click(function () {

        var selectuser = $("#user").val();
        var pword = $("#pass").val();

        if (selectuser == "" || selectuser.length <= 1) {
            alert("用户名不能为空");
            $("#user").focus();
            return false;
        }
        if (pword == "" || pword.length < 1) {
            alert("密码不能为空");
            $("#pass").focus();
            return false;
        }
        getUserDep(selectuser, pword);


    });

    function getUserDep(v1, v2) {
        $.ajax({
            type: "post",
            url: "LoginServlet",
            data: {adid: v1, mima: v2},
            success: function (message) {
                if (message == "1") {
                    window.location.href = 'index.html';
                } else {
                    alter("密码错误");
                }
            }

        });
    }
});