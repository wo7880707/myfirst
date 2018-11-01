//页面初始化


	$.ajax({
		type: "post",
		url: "admin/ifSessionHas.action"
	}).done(function(res) {
		if(res.userRemark != null){
			$('#info').text(res.userName);
			if(res.userRemark == "2"){
				$("#xxbl").hide();
				$("#xxbl2").hide();
				/*$("#scxx").hide();
				$("#scxx2").hide();*/
				$("#yjqc").hide();
				$("#yjqc2").hide();
				$("#zcsp").hide();
				$("#zcsp2").hide();
				$("#cxzh").hide();
				$("#cxzh2").hide();
			}
		}else{
			location.href = "login.html"
			alert("请您先登录")
			//还能显示
			/*//header 处理
			$("strong").first().text("");
			$("#updatePassword").text("请登陆").attr("href", "login.html");
			//全部关闭
			$(".sidebar-grouptitle").hide();
			$(".sidebar-menu").hide();*/
			
		}
	/*	if (res == null) {
			//没有登录
			//header 处理
			$("strong").first().text("");
			$("#updatePassword").text("请登陆").attr("href", "login.html");
			//sidebar 处理
			//关闭全部
			$(".sidebar-grouptitle").hide();
			$(".sidebar-menuitem").hide();
			//打开  人员管理
			$(".sidebar-grouptitle").eq(1).show();
			//打开 员工注册
			$(".sidebar-menuitem").eq(4).show();
			//退出登录 关闭
			$("#exitUser").hide()

		} else {
			//header 处理
			$("#exitUser").show();
			$("strong").first().text(user.employeeName);
			$("#visitedCount").text("您是第"+count+"位登录系统的用户");
			if (user.role == 2) {
				//普通员工
				//关闭  人员管理

				$(".sidebar-grouptitle").eq(1).hide();
				$(".sidebar-menu").eq(1).hide();
			}
		}*/
	});
	


	
