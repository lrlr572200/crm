

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
            location.href="/sys/err.html";
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
            location.href="/sys/err.html";
        }
    });
}

getRoles();




//返回
function togo() {
    location.href="/sys/userManage.html";
}

//表单验证
function checkEmpName() {  //姓名
    var empName = $("#empName");
    var divID1=$("#nameDiv");
    divID1.html("");
    if (empName.val()==""){
        divID1.html("姓名不能为空！");
        return false;
    }
    if (empName.val().length > 10) {
        divID1.html("姓名长度过长！")
        return false;
    }
    return true;
}

function checkSex() { //性别
    var sex = $("#sex");
    var divID2 = $("#sexDiv");
    divID2.html("");
    if (sex.val()==""){
        divID2.html("性别不能为空！");
        return false;
    }
    return true;
}

function checkEmpCode() { //编号
    var empCode = $("#empCode");
    var divID3 = $("#codeDiv");
    var regCode = /^[0-9a-zA-Z]{4,21}$/;
    divID3.html("");
    if (empCode.val()==""){
        divID3.html("编号不能为空！");
        return false;
    }
    if (empCode.val().length < 4) {
        divID3.html("编号长度不能小于4位！");
        return false;
    }
    if (empCode.val().length > 21) {
        divID3.html("编号长度不能大于21位！");
        return false;
    }
    if (!regCode.test(empCode.val())){
        divID3.html("编号只能是字母、数字，或字母数字组合！")
        return false;
    }
    return true;
}

function checkPassword() {  //密码验证
    var password = $("#password");
    var pwdDiv = $("#pwdDiv");
    var regPwd = /^[0-9a-zA-Z]{5,10}$/;
    pwdDiv.html("");
    if (password.val()==""){
        pwdDiv.html("密码不能为空！");
        return false;
    }
    if (!regPwd.test(password.val())){
        pwdDiv.html("密码长度只能为5到10位，只能是字母、数字，或字母数字的组合！");
        return false;
    }
    return true;
}

function checkDeptId() { //部门
    var deptId = $("#deptId");
    var deptDiv = $("#deptDiv");
    deptDiv.html("");
    if (deptId.val()==""){
        deptDiv.html("部门不能为空！");
        return false;
    }
    return true;
}

function checkRole() { //角色
    var rolesId = $("#rolesId");
    var roleDiv = $("#roleDiv");
    roleDiv.html("");
    if (rolesId.val()==""){
        roleDiv.html("职位不能为空！");
        return false;
    }
    return true;
}

function checkAlert() { //是否修改
    var alert = $("#alert");
    var alertDiv = $("#alertDiv");
    alertDiv.html("");
    if (alert.val()==""){
        alertDiv.html("此项标记是否修改，不能为空！")
        return false;
    }
    return  true;
}

function checkStates() { //状态
    var states = $("#states");
    var statesDiv = $("#statesDiv");
    statesDiv.html("");
    if (states.val()==""){
        statesDiv.html("状态不能为空！");
        return false;
    }
    return true;
}

function checkEmail() { //邮箱
    var email = $("#email");
    var emailDiv =$("#emailDiv");
    var regEmail = /^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,6}$/;
    emailDiv.html("");
    if (email.val()==""){
        emailDiv.html("邮箱不能为空！");
        return false;
    }
    if (!regEmail.test(email.val())){
        emailDiv.html("邮箱格式不正确！");
        return false;
    }
    return true;
}

//绑定失去焦点事件
$("#email").blur(checkEmail);
$("#states").blur(checkStates);
$("#alert").blur(checkAlert);
$("#rolesId").blur(checkRole);
$("#deptId").blur(checkDeptId);
$("#password").blur(checkPassword);
$("#empCode").blur(checkEmpCode);
$("#sex").blur(checkSex);
$("#empName").blur(checkEmpName);

//提交表单
$("#myForm").submit(function () {
    var flag = true;
    if (!checkEmpName()){flag=false};
    if (!checkSex()){flag=false};
    if (!checkEmpCode()){flag=false};
    if (!checkPassword()){flag=false};
    if (!checkDeptId()){flag=false};
    if (!checkRole()){flag=false};
    if (!checkAlert()){flag=false};
    if (!checkStates()){flag=false};
    if (!checkEmail()){flag=false};
    if (flag==true){
        $("#alert").removeAttr("disabled");
    }
    return flag;
});





