var checkd=[false,false,false,false,false];
$("#employeename").blur(function(){
	if($(this).val()==""){
		$("#employeenamed").css("color","red").text("×姓名不能为空");
		checkd[0]=false;
	}else{
		$("#employeenamed").css("color","green").text("√");
		checkd[0]=true;
	}
})
$("#accountname").blur(function(){
	var str=$(this).val();
	if(str==""){
		$("#accountnamed").css("color","red").text("×账户名不能为空");
		checkd[1]=false;
	}else{
		$.ajax({
			type:"post",
			url:"admin/selectByID.action",
			async:true,
			data:{"userID":str}
		}).done(function(res){
			if(res.userID != null){
				$("#accountnamed").css("color","red").text("×账户名重复");
				checkd[1]=false;
			}else{
				$("#accountnamed").css("color","green").text("√");
				checkd[1]=true;
			}
		})
	}
});
$("#password").blur(function(){
	var check=/[0-9a-zA-Z]/;
	var str=$(this).val();
	if(str==""){
		$("#passwordd").css("color","red").text("×密码不能为空");
		checkd[2]=false;
	}else{
		if(check.test(str)){
			$("#passwordd").css("color","green").text("√");
			checkd[2]=true;
		}
		else{
			$("#passwordd").css("color","red").text("×密码只能为数字或字母");
			checkd[2]=false;
		}
	}
});
$("#confirm").blur(function(){
	var str=$(this).val();
	if(str==""){
		$("#confirmd").css("color","red").text("×密码不能为空");
		checkd[3]=false;
	}else{
		if(str==$("#password").val()){
			$("#confirmd").css("color","green").text("√");
			checkd[3]=true;
		}	
		else{
			$("#confirmd").css("color","red").text("×密码输入不一致");
			checkd[3]=false;
		}		
	}
});
$("#phone").blur(function(){
	var str=$(this).val();
	var check=/[0-9- ]/;
	if(str==""){
		$("#phoned").css("color","red").text("×性别不能为空");
		checkd[4]=false;
	}else{
		checkd[4]=true;
	}
});
	//<input type="button" class="clickbutton" id = "register" value="注册" />
	//<input type="button" class="clickbutton" id = "reset" value="重置" />" +
	//注册
		$("#register").click(function(){
			$.ajax({
				type:"post",
				url:"admin/register.action",
				data:{"userID":$("#accountnamed").val(),
					"userPass":$("#passwordd").val(),
					"userGender":$("#phoned").val(),
				 	 "userName":$("#employeenamed").val()
				}
			}).done(function(res){
				if(res == 0){
					alert("登录失败：账号或密码错误");
					
				}else if(res == 1){
					location.href = "main.html";
					
				}else if(res == 2){
					location.href = "main.html";
					
				} else{
					alert("账号未审批，请联系管理员");
				}
				
			})
	})
$(".command:eq(0)").mouseenter(function(){
	var flag=false;
	for(var i=0;i<checkd.length;i++){
		if(!checkd[i]){
			flag=true;
			break;
		}
	}
	if(flag){
		$(".clickbutton:eq(0)").attr("disabled",true);
	}else{
		$(".clickbutton:eq(0)").attr("disabled",false);
	}
})
/*
$(".clickbutton:eq(0)").click(function(){
	$.ajax({
		type:"post",
		url:"RegisterServletC",
		data:{"employeename":$("#employeename").val(),"accountname":$("#accountname").val(),"password":$("#password").val(),"phone":$("#phone").val(),"email":$("#email").val(),"deptid":$("#deptid").val()}
	}).done(function(res){
		var a=JSON.parse(res); 
		if(a=="1"){
			alert("注册成功！");
			window.location.href="index.html";
		}else{
			alert("注册失败！");
		} 
	});
});
$(".clickbutton:eq(1)").click(function(){
	for(var i=0;i<checkd.length;i++){
		if(!checkd[i]){
			$("input[maxlength='20']:eq("+i+")").val("");
		}
	}
})*/