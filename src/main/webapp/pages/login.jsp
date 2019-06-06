<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>登录</title>

		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!--basic styles-->

		<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${ctx}/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!--page specific plugin styles-->

		<!--fonts-->

		<!--ace styles-->

		<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-responsive.min.css" />

		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="css/ace-ie.min.css" />
		<![endif]-->
		
		<!-- modal -->
		<script src="${ctx}/js/bootstrap-modal.js"></script>
		<script type="text/javascript">
		var s = self;
		var p = parent;
		if(s == p){
		}else{
			while(s!= p){
				s = s.parent;
				p = parent.parent
			}
			p.location.href="${ctx}";
		}
		function reloadCheckCode(){
			document.getElementById("yzmPic").src = "${ctx}/CheckCode?r=" + new Date().getTime();
		}
		
		function alertMsg(modalId, msgId, msg) {
			$('#'+msgId).html(msg);
			$('#'+modalId).modal({
			    backdrop:true,
			    keyboard:true,
			    show:true
			});
		}

		function validation(){
			var name = $.trim(document.getElementById("name").value);
			var pass = document.getElementById("pass").value;
			var validateCode = $.trim(document.getElementById("validateCode").value);
            var remember = document.getElementById("remember").value;
            var chk_value =[];//定义一个数组
            $('input[name="remember"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
                chk_value.push($(this).val());//将选中的值添加到数组chk_value中
            });
            var rememberValue = null;
            if(chk_value[0] != null && chk_value[0] != ""){
                rememberValue = chk_value[0];
            }
			if(name.length == 0 || name==""){
				//name==""){
				document.getElementById("name").focus();
				alertMsg('alertModal', 'alertMsg', '用户名不能为空！');
				return false;
			}else if(pass.length == 0){
				//pass==""){
				alertMsg('alertModal', 'alertMsg', '密码不能为空！');
				document.getElementById("pass").focus();
				return false;
			}else if(validateCode.length == 0 || validateCode==""){
				//validateCode==""){
				alertMsg('alertModal', 'alertMsg', '验证码不能为空！');
				document.getElementById("validateCode").focus();
				return false;
			}else{
				$.ajax({
		            "dataType": 'json',
					"type": 'POST',
					"url": '${ctx}/pages/common/login',
					"data": {'name': name, 'pass': pass, 'validateCode': validateCode, 'remember': rememberValue},
					"success": function(response) {
						if(response.isSuccess == "true") {
							//alertMsg('alertModal', 'alertMsg', response.msg);
							window.location.href = "${ctx}" + response.url;
						}else{
							alertMsg('alertModal', 'alertMsg', response.msg);
							reloadCheckCode();
						}
					},
					"error": function(XMLHttpRequest, textStatus, errorThrown) {
						reloadCheckCode();
						alert(XMLHttpRequest.responseText);
						alert("error");
					}
				});
				//document.getElementById("accountForm").submit();
			}
		}
		
		function on_return(evt){
 
			evt = (evt) ? evt : ((window.event) ? window.event : ""); //兼容IE和Firefox获得keyBoardEvent对象
			var key = evt.keyCode?evt.keyCode:evt.which; //兼容IE和Firefox获得keyBoardEvent对象的键值 
			if(key == 13){
				validation();
			}
		}
		</script>
	</head>
	<body class="login-layout"  onkeydown="on_return(event);">
		<div class="container-fluid" id="main-container">
			<div id="main-content">
				<div class="row-fluid">
					<div class="span12">
						<div class="login-container">
							<div class="row-fluid">
								<div class="center">
									<h1>
										
										<span class="red"><img src="${ctx}/images/logo.png" width="226" height="80"></span>
										<!--<span class="white">法律出版社</span>-->
									</h1>
									<h4 class="blue">出版业务管理系统</h4>
								</div>
							</div>

							<div class="space-6"></div>

							<div class="row-fluid">
								<div class="position-relative">
									<div id="login-box" class="visible widget-box no-border">
										<div class="widget-body">
											<div class="widget-main">
												<h4 class="header blue lighter bigger">
													<i class="icon-coffee green"></i>
													请输入您的账户信息
												</h4>
										<!-- 
<div class="btn-group">
										<a data-toggle="dropdown" class="btn btn-primary btn-small dropdown-toggle">
											选择登陆用户类型
											<span class="caret"></span>
										</a>

										<ul class="dropdown-menu dropdown-info pull-right">
											<li> <a href="index.html">系统管理员</a> </li>
                                            <li> <a href="zrbj-index.html">责任编辑</a> </li>
                                            <li> <a href="fsz-index.html">分社长</a> </li>
                                            <li> <a href="fgld-index.html">分管领导</a> </li>
                                            <li> <a href="zbs-index.html">总编室</a> </li>
                                            <li> <a href="jd-index.html">校对</a> </li>
                                            <li> <a href="mb-index.html">美编</a> </li>
                                            <li> <a href="bq-index.html">版权经理</a> </li>
										</ul>
									</div>
									 -->
												<div class="space-6"></div>

												<form>
													<fieldset>
														<label>
															<span class="block input-icon input-icon-right">
																<input id="name" name="name" type="text" class="span12" placeholder="用户名" />
																<i class="icon-user"></i>
															</span>
														</label>

														<label>
															<span class="block input-icon input-icon-right">
																<input id="pass" name="pass" type="password" class="span12" placeholder="密码" />
																<i class="icon-lock"></i>
															</span>
														</label>

														<label>
															<span>
																<span class="lbl input-icon input-icon-right">
																	<input id="validateCode" name="validateCode" type="text" class="span12" maxlength="15" placeholder="验证码" />
																	<i class="icon-retweet"></i>
																</span>
																<span class="lbl">
																	<img src="${ctx}/CheckCode" id="yzmPic" onclick="reloadCheckCode()" style="cursor:pointer;" title="点击刷新验证码"/>
																	<a href="#" onclick="reloadCheckCode()" data-action="reload">
																		<i class="icon-refresh"></i>
																	</a>
																</span>
															</span>
														</label>

														<div class="space"></div>

														<div class="row-fluid">
															<label class="span8">
																<input id="remember" type="checkbox" name="remember" />
																<span class="lbl">记住账号</span>
															</label>

															<a href="#" onclick="validation()" class="span4 btn btn-small btn-primary">
																<i class="icon-key"></i>
																登录
															</a>
                                                            
														</div>
													</fieldset>
												</form>
											</div><!--/widget-main-->
											<!-- 
											<div class="toolbar clearfix">
												<div>
													<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
														<i class="icon-arrow-left"></i>
														忘记密码
													</a>
												</div>

												<div>
													<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
														注册
														<i class="icon-arrow-right"></i>
													</a>
												</div>
											</div>
											 -->
										</div><!--/widget-body-->
									</div><!--/login-box-->

									<div id="forgot-box" class="widget-box no-border">
										<div class="widget-body">
											<div class="widget-main">
												<h4 class="header red lighter bigger">
													<i class="icon-key"></i>
													找回密码
												</h4>

												<div class="space-6"></div>
												<p>
													输入您的电子邮件地址来接收信息
												</p>

												<form>
													<fieldset>
														<label>
															<span class="block input-icon input-icon-right">
																<input type="email" class="span12" placeholder="Email" />
																<i class="icon-envelope"></i>
															</span>
														</label>

														<div class="row-fluid">
															<button onclick="return false;" class="span5 offset7 btn btn-small btn-danger">
																<i class="icon-lightbulb"></i>
																发送!
															</button>
														</div>
													</fieldset>
												</form>
											</div><!--/widget-main-->

											<div class="toolbar center">
												<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
													返回登录
													<i class="icon-arrow-right"></i>
												</a>
											</div>
										</div><!--/widget-body-->
									</div><!--/forgot-box-->

									<div id="signup-box" class="widget-box no-border">
										<div class="widget-body">
											<div class="widget-main">
												<h4 class="header green lighter bigger">
													<i class="icon-group blue"></i>
													新用户注册
												</h4>

												<div class="space-6"></div>
												<p>
													输入您的详细资料:
												</p>

												<form>
													<fieldset>
														<label>
															<span class="block input-icon input-icon-right">
																<input type="email" class="span12" placeholder="Email" />
																<i class="icon-envelope"></i>
															</span>
														</label>

														<label>
															<span class="block input-icon input-icon-right">
																<input type="text" class="span12" placeholder="Username" />
																<i class="icon-user"></i>
															</span>
														</label>

														<label>
															<span class="block input-icon input-icon-right">
																<input type="password" class="span12" placeholder="Password" />
																<i class="icon-lock"></i>
															</span>
														</label>

														<label>
															<span class="block input-icon input-icon-right">
																<input type="password" class="span12" placeholder="Repeat password" />
																<i class="icon-retweet"></i>
															</span>
														</label>

														<label>
															<input type="checkbox" />
															<span class="lbl">
																我接受
																<a href="#">用户服务信息</a>
															</span>
														</label>

														<div class="space-24"></div>

														<div class="row-fluid">
															<button type="reset" class="span6 btn btn-small">
																<i class="icon-refresh"></i>
																重置
															</button>

															<button onclick="return false;" class="span6 btn btn-small btn-success">
																注册
																<i class="icon-arrow-right icon-on-right"></i>
															</button>
														</div>
													</fieldset>
												</form>
											</div>

											<div class="toolbar center">
												<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
													<i class="icon-arrow-left"></i>
													返回登录
												</a>
											</div>
										</div><!--/widget-body-->
									</div><!--/signup-box-->
								</div><!--/position-relative-->
							</div>
						</div>
					</div><!--/span-->
				</div><!--/row-->
			</div>
		</div><!--/.fluid-container-->

		<!--basic scripts-->
		<!-- 
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		 -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/js/jquery-1.9.1.min.js'>"+"<"+"/script>");
		</script>
<script src="${ctx}/js/bootstrap.min.js"></script>

		<!--page specific plugin scripts-->

		<!--ace scripts-->

		<script src="${ctx}/js/ace-elements.min.js"></script>
		<script src="${ctx}/js/ace.min.js"></script>

		<!--inline scripts related to this page-->

		<script type="text/javascript">
			function show_box(id) {
			 $('.widget-box.visible').removeClass('visible');
			 $('#'+id).addClass('visible');
			}
		</script>
        <script type="text/javascript">
            var cookieName = $('#cookieName').val();
            var cookieValue = $('#cookieValue').val();
            if (cookieValue != null && cookieValue != "") {
                document.getElementById("name").value = cookieValue;
            }
        </script>
		
		<!-- Alert Start ------------------------------------------------------------------------------- -->
		<div id="alertModal" class="modal hide fade">
		  <div class="modal-header">
		    <a class="close" data-dismiss="modal">×</a>
		    <span class="text-warning orange"><h3><i class="icon-warning-sign"></i>警告</h3></span>
		  </div>
		  <div class="modal-body">
		    <p id="alertMsg"></p>
		  </div>
		</div>
		<!-- Alert End ------------------------------------------------------------------------------- -->
	</body>
</html>
