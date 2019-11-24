function openlayer(id){
	layer.open({
		type: 2,
		title: '修改密码',
		shadeClose: false,
		shade: 0.5,
		skin: 'layui-layer-rim',
		closeBtn: 2,
		area: ['400px', '450px'],
		content: 'sys/upPwd.html'
	});
}
$(document).ready(function () {
	//绑定失去焦点事件
	$("#pass").blur(pword);
	$("#newpass").blur(newpwd);
	$("#repass").blur(renewpwd);
	//提交表单验证函数
	$("#myform").submit(function () {
		var flag=true;
		if(!pword())flag=false;
		if(!newpwd())flag=false;
		if(!renewpwd())flag=false;
		return flag;
	});
})
function pword(){
	var pword = $("#pass").val();
	var oldword = $("#oldPwd").val();
	$("#sppass").html("");
	if (pword == "" || pword.length < 1) {
		$("#sppass").html("密码不能为空");
		return false;
	}
	if (pword!=oldword){
		$("#sppass").html("密码输入错误，请重新输入！");
	}
	return true;
}
function newpwd(){
	var newpwd=$("#newpass").val();
	$("#spnewpass").html("");
	if (newpwd == "" || newpwd.length < 4) {
		$("#spnewpass").html("新密码至少4位数");
		return false;
	}
	return true;
}
function renewpwd(){
	var newpwd=$("#newpass").val();
	var renewpwd=$("#repass").val();
	$("#spRepass").html("");
	if (renewpwd !=newpwd) {
		$("#spRepass").html("两次密码不一致");
		return false;
	}
	return true;
}

function upPwd_btn(){
	var empCode=$("#user").val();
	var newpwd=$("#newpass").val();
	var data = {"empCode":empCode,"password":newpwd};
	$.post("/sys/upPwd.ajax",data,function (sign) {
		if (sign>0){
			window.confirm("是否确定更改！");
			window.parent.location.href="/login.html";
		} else{
			window.alert("修改失败，请重新操作！")
			location.href="sys/upPwd.html";
		}
	},"json");
}

