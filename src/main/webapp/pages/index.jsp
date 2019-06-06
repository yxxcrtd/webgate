<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/commonModal.jsp"%>
<%@ include file="/pages/common/commonBarModal.jsp"%>
<%@ include file="/pages/common/confirmModal.jsp"%>
<%@ include file="/pages/common/msg.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html>
<html lang="en"><!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
	<head>
	<meta charset="utf-8" />
	<title>后台管理</title>
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<script src="${ctx}/js/common.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var height = $(window).height() - 93;
			//alert(height);
			$('#iframeid').css("height", height);
			$(window).resize(function() {
				//alert("resize");
				var height = $(window).height() - 93;
				$('#iframeid').css("height", height);
			});
			//alert('${sessionScope.ccsfw_role.id}');
			//alert('${sessionScope.ccsfw_role.name}');
			var ticket = '${sessionScope.ticket}';
			var roleId = '${form.roleId}';
			var sysId = '${form.sysId}';
			var accountId = '${form.accountId}';
			getMenuList(roleId, sysId ,ticket);
			getRoleList(accountId, roleId);
		});
						
		function autoReloadMenuList(second, url) {
			second--;
		    if(second == 0) {
		    	//alert(url);
				window.location.href = url;
		    } else {
		        setTimeout(function() {
					autoReloadMenuList(second, url);
		        }, 1000);
		    }
		}
		
		function switchRole(accountId, roleId) {
			//alert(roleId);
			$.ajax({
	            "dataType": 'json',
				"type": 'POST',
				"url": '${ctx}/pages/frame/switchRole',
				"data": {
					'id': roleId
				},
				"success": function(response) {
					if(response.isSuccess == "true") {
						//alert(response.msg);
						//alertMsg('alertModal', 'alertMsg', response.msg);
						//openSuccessAlertModalInLayer(response.msg);
						//autoReloadMenuList(5, "${ctx}" + response.url);
						//alert("accountId:" + '${sessionScope.ccsfw_account.id}');
						//alert("roleId:" + '${sessionScope.ccsfw_role.id}');
						//alert("systemId:" + '${sessionScope.ccsfw_sys.id}');
						//window.location.href = "${ctx}" + response.url;
						//getMenuList();
						//getRoleList();
						var ticket = '${sessionScope.ticket}';
						getMenuList(response.roleId, response.sysId,ticket);
						getRoleList(accountId, response.roleId);
					}else{
						alertMsg('alertModal', 'alertMsg', response.msg);
						//window.location.href = "${ctx}" + response.url;
					}
				},
				"error": function(response) {
					//alert("error");
					window.location.href = "${ctx}";
				}
			});
		}
		
		function getRoleList(accountId, roleId) {
			$.ajax({
	            "dataType": 'json',
				"type": 'POST',
				"url": '${ctx}/pages/frame/getRoleListByAccountId',
				"data": {
					'id': accountId
				},
				"success": function(response) {
					if(response.isSuccess == "true") {
						var html = "";
						//var resourceList = response.aaData;
						//alert(JSON.stringify(response.roleList));
						for (var i = 0; i < response.roleList.length; i ++) {
							var role = response.roleList[i];
							var id = role.id;
							var name = role.name;
							//alert(id);
							//alert(name);
							if (id == roleId) {
								html = html + "<li> <a> <i class='icon-user'></i>" + name + "</a> </li>";
							} else {
								html = html + "<li> <a href='#' onclick='switchRole(\"" + accountId + "\", \"" + id + "\");'> <i class='icon-user'></i>" + name + "</a> </li>";
							}
						}
						html = html + "<li class='divider'></li>";
						html = html + "<li> <a style='cursor:pointer' onclick='setPasswd()'> <i class='icon-user'></i> 密码设置 </a> </li>";
						html = html + "<li class='divider'></li>";
						html = html + "<li> <a style='cursor:pointer' onclick='logout()'> <i class='icon-off'></i> 退出 </a> </li>";
						//alert(html);
						$('#user_menu').html(html);
					}else{
						alertMsg('alertModal', 'alertMsg', response.msg);
						window.location.href = "${ctx}" + response.url;
					}
				},
				"error": function(response) {
					//alert("error");
					window.location.href = "${ctx}";
				}
			});
		}
		
		function logout() {
			openConfirmModalInFrame(Global_Prompt_Logout_Message, function() {
				var url = "${ctx}/pages/common/logout";
				$.ajax( {
					"dataType": 'json',
					"type": "POST",
					"url": url,
					"cache": false,
					"success": function(response) {
						if (response.isSuccess == "true") {
							window.location.href = "${ctx}" + response.url;
						} else {
							openErrorAlertModalInLayer(response.msg);
						}
					},
					"error": function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest.responseText);
						alert(XMLHttpRequest.status);
						alert(textStatus);
					}
				} );
			}, null, null);	
		};
		
		function setPasswd() {
			var url = "${ctx}/pages/account/form/setPasswd";
			var commonModalCss = {
				"width": "900px",
				"margin": "100px 0 0 -450px"
			};
			var commonModalBodyCss = {
				"max-height": "800px"
			};
			openCommonModalInLayer(url, commonModalCss, commonModalBodyCss);
		};
		
		function setPosition(menuId){
			var url = "${ctx}/pages/resource/form/getNavigation.json";
			$.ajax({
				"dataType": 'json',
				"type": 'POST',
				"url": url,
				"async" : true, //默认为 true-异步 false-同步	
				"data": {'menuId': menuId},
				"success": function(response) {
					if(response.sessionForm!=undefined){
						if(response.sessionForm.exist =='false'){
							openErrorAlertModalInLayer(response.sessionForm.msg);
						}
					}else{
						if(response.resourceForm.isSuccess == "true") {
							document.getElementById("navigation").innerHTML= response.resourceForm.msg;
						}else{
							openErrorAlertModalInLayer(response.resourceForm.msg);
						}
					}
				},
				"error": function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.responseText);
					alert(XMLHttpRequest.status);
					alert(textStatus);
				}
			});
		}
		
		function getMenuList(roleId, sysId,ticket) {
			$.ajax({
	            "dataType": 'json',
				"type": 'POST',
				"url": '${ctx}/pages/frame/getResourceZTreeByRoleId',
				"data": {
					'sysId': sysId,
					'roleId': roleId
				},
				"success": function(response) {
					if(response.isSuccess == "true") {
						//alertMsg('alertModal', 'alertMsg', response.msg);
						//window.location.href = "${ctx}" + response.url;
						var html = "";
						var defaultId='';
						var resourceList = response.aaData;
						if(resourceList!=null){
							for (var i = 0; i < resourceList.length; i ++) {
								var resource = resourceList[i];
								if(defaultId==''&&resource.link!=null&&resource.link!=''){
									defaultId = resource.id;
									setPosition(defaultId);
									if(resource.full==1){
										$("#iframeid").attr("src","http://" + resource.component.address + ":" + resource.component.port + "/" + resource.component.code+resource.link+"?ticket="+ticket+"&pt_rtype=m");
									}else{
										$("#iframeid").attr("src",resource.link+"?ticket="+ticket+"&pt_rtype=m");
									}
								}
								//alert("resource:" + JSON.stringify(resource));
								//alert("parentResource:" + JSON.stringify(resource.parentResource));
								if (resource.parentResource.id == null || resource.parentResource.id == "") {
									if (resource.parentResource == null || resource.parentResource.id == null || resource.parentResource.id == "") {
										var subMenuHtml = "";
										var count = 0;
										$.each(resourceList, function(i, subResource) {
									        //alert(i+":"+subResource);
									        if (subResource.parentResource != null && subResource.parentResource.id != null && subResource.parentResource.id != "") {
												if (resource.id == subResource.parentResource.id) {
											        //alert("resource.id:" + resource.id);
											        //alert("resource.name:" + resource.name);
											        //alert("subResource.id:" + subResource.id);
											        //alert("subResource.name:" + subResource.name);
											        //alert("subResource.link:" + subResource.link);
													if(defaultId==''&&subResource.link!=null&&subResource.link!=''){
														defaultId = subResource.id;
														setPosition(defaultId);
														if(subResource.full==1){
															$("#iframeid").attr("src","http://" + subResource.component.address + ":" + subResource.component.port + "/" + subResource.component.code+subResource.link+"?ticket="+ticket+"&pt_rtype=m");
														}else{
															$("#iframeid").attr("src",subResource.link+"?ticket="+ticket+"&pt_rtype=m");
														}
													}
											        count++;
											        if(subResource.full==1){
												        subMenuHtml = subMenuHtml + 
													       "<li><a href='" + "http://" + subResource.component.address + ":" + subResource.component.port + "/" + subResource.component.code + subResource.link +"?ticket="+ticket+"&pt_rtype=m" + "' target='iframe_main' onclick='setPosition(\""+subResource.id+"\")'><i class='icon-double-angle-right'></i>" + subResource.name + "</a></li>";
											        }else{
											        	subMenuHtml = subMenuHtml + 
													       "<li><a href='" + subResource.link +"?ticket="+ticket+"&pt_rtype=m" + "' target='iframe_main' onclick='setPosition(\""+subResource.id+"\")'><i class='icon-double-angle-right'></i>" + subResource.name + "</a></li>";
											        }
												}
									        }
									    });
										if (count > 0) {
											html = html + "<li>";
											html = html + "<a style='cursor:pointer' class='dropdown-toggle' onclick='setPosition(\""+resource.id+"\")'>";
											html = html + "<i class='icon-file'></i>";
											html = html + "<span>" + resource.name + "</span>";
											html = html + "<b class='arrow icon-angle-down'></b></a>";
											html = html + "<ul class='submenu'>";
											html = html + subMenuHtml;
											html = html + "</ul>";
										} else {
											html = html + "<li>";
											if(resource.link!=null&&resource.link!=''){
												if(resource.full==1){
													html = html + "<a href='" + "http://" + resource.component.address + ":" + resource.component.port + "/" + resource.component.code+resource.link+"?ticket="+ticket+"&pt_rtype=m" + "' onclick='setPosition(\""+resource.id+"\")' target='iframe_main'>";
												}else{
													html = html + "<a href='" + resource.link + "' onclick='setPosition(\""+resource.id+"\")' target='iframe_main'>";
												}
											}else{
												html = html + "<a style='cursor:pointer' onclick='setPosition(\""+resource.id+"\")'>";
											}
											html = html + "<i class='icon-file'></i>";
											html = html + "<span>" + resource.name + "</span>";
											html = html + "</a>";
										}
									}
									html = html + "</li>";
								}
							}
						}
						//alert(html);
						$('#menuList').html(html);
					}else{
						alertMsg('alertModal', 'alertMsg', response.msg);
						window.location.href = "${ctx}" + response.url;
					}
				},
				"error": function(response) {
					//alert("error");
					window.location.href = "${ctx}";
				}
			});
		}
	</script>
	</head>
	<body>
		<div class="navbar navbar-inverse">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a href="#" class="brand">
						<small>
							<img src="../images/logo-fls.png">
							法律出版社业务管理系统 	
						</small>
					</a><!--/.brand-->

					<ul class="nav ace-nav pull-right">
						<%-- <li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-tasks"></i>
								<span class="badge badge-grey">4</span>
							</a>

							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-closer">
								<li class="nav-header">
									<i class="icon-ok"></i>
									4 条待完成任务
							  </li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Software Update</span>
											<span class="pull-right">65%</span>
										</div>

										<div class="progress progress-mini ">
											<div style="width:65%" class="bar"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Hardware Upgrade</span>
											<span class="pull-right">35%</span>
										</div>

										<div class="progress progress-mini progress-danger">
											<div style="width:35%" class="bar"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Unit Testing</span>
											<span class="pull-right">15%</span>
										</div>

										<div class="progress progress-mini progress-warning">
											<div style="width:15%" class="bar"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Bug Fixes</span>
											<span class="pull-right">90%</span>
										</div>

										<div class="progress progress-mini progress-success progress-striped active">
											<div style="width:90%" class="bar"></div>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										查看全部待办任务
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-only icon-animated-bell"></i>
								<span class="badge badge-important">8</span>
							</a>

							<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-closer">
								<li class="nav-header">
									<i class="icon-warning-sign"></i>
									8 条通知
							  </li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-mini no-hover btn-pink icon-comment"></i>
												New Comments
											</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<i class="btn btn-mini btn-primary icon-user"></i>
										Bob just signed up as an editor ...
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-mini no-hover btn-success icon-shopping-cart"></i>
												New Orders
											</span>
											<span class="pull-right badge badge-success">+8</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-mini no-hover btn-info icon-twitter"></i>
												Followers
											</span>
											<span class="pull-right badge badge-info">+11</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										查看全部通知
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-envelope-alt icon-only icon-animated-vertical"></i>
								<span class="badge badge-success">5</span>
							</a>

							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-closer">
								<li class="nav-header">
									<i class="icon-envelope"></i>
									5 条消息
							  </li>

								<li>
									<a href="#">
										<img src="${ctx}/images/avatar.png" class="msg-photo" alt="Alex's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Alex:</span>
												Ciao sociis natoque penatibus et auctor ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>a moment ago</span>
											</span>
										</span>
									</a>
								</li>

								<li>
									<a href="#">
										<img src="${ctx}/images/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Susan:</span>
												Vestibulum id ligula porta felis euismod ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>20 minutes ago</span>
											</span>
										</span>
									</a>
								</li>

								<li>
									<a href="#">
										<img src="${ctx}/images/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Bob:</span>
												Nullam quis risus eget urna mollis ornare ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>3:15 pm</span>
											</span>
										</span>
									</a>
								</li>

								<li>
									<a href="#">
										查看全部消息
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li> --%>

						<li class="light-blue user-profile">
							<a data-toggle="dropdown" href="#" class="user-menu dropdown-toggle">
								<img class="nav-user-photo" src="${ctx}/images/user.jpg" alt="Jason's Photo" />
								<span id="user_info">
									<small>欢迎,</small>
									<!--系统管理员-->
                                    ${sessionScope.ccsfw_account.name}
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer" id="user_menu">
							<!-- 
                              <li> <a href="index.html"> <i class="icon-user"></i>系统管理员</a> </li>
                              <li> <a href="zrbj-index.html"> <i class="icon-user"></i>责任编辑</a> </li>
                              <li> <a href="fsz-index.html"> <i class="icon-user"></i>分社长</a> </li>
                              <li> <a href="fgld-index.html"> <i class="icon-user"></i>分管领导</a> </li>
                              <li> <a href="zbs-index.html"> <i class="icon-user"></i>总编室</a> </li>
                              <li> <a href="jd-index.html"> <i class="icon-user"></i>校对</a> </li>
                              <li> <a href="mb-index.html"> <i class="icon-user"></i>美编</a> </li>
                              <li> <a href="bq-index.html"> <i class="icon-user"></i>版权经理</a> </li>
                              <li class="divider"></li>
                              <li> <a href="#"> <i class="icon-cog"></i> 设置 </a> </li>
                              <li> <a href="#"> <i class="icon-user"></i> 账户 </a> </li>
                              <li class="divider"></li>
                              <li> <a href="login.html"> <i class="icon-off"></i> 退出 </a> </li>
                             -->
							</ul>
						</li>
					</ul><!--/.ace-nav-->
				</div><!--/.container-fluid-->
			</div><!--/.navbar-inner-->
		</div>

		<div class="container-fluid" id="main-container">
			<a id="menu-toggler" href="#">
				<span></span>
			</a>

			<div id="sidebar">
				<div id="sidebar-shortcuts">
					<!-- <div id="sidebar-shortcuts-large">
						<button class="btn btn-small btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-small btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-small btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-small btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div> -->

					<div id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!--#sidebar-shortcuts-->

				<ul class="nav nav-list" id="menuList">
					<!-- 
					<li>
						<a href="index.html">
							<i class="icon-dashboard"></i>
							<span>后台管理主页</span>
						</a>
					</li>
					
					<li>
					  <a href="${ctx}/pages/system/form/index" target="iframe_main">
							<i class="icon-globe"></i>
							<span>系统管理</span>
					  </a>
					</li>

					<li>
					  <a href="${ctx}/pages/component/form/index" target="iframe_main">
							<i class="icon-globe"></i>
							<span>组件管理</span>
					  </a>
					</li>

					<li>
					  <a href="${ctx}/pages/account/form/index" target="iframe_main">
							<i class="icon-globe"></i>
							<span>账户管理</span>
					  </a>
					</li>

					<li>
					  <a href="${ctx}/pages/module/form/index" target="iframe_main">
							<i class="icon-globe"></i>
							<span>模块管理</span>
					  </a>
					</li>
					
					<li>
					     <a href="${ctx}/pages/role/form/index" target="iframe_main">
					          <i class="icon-globe"></i>
					          <span>角色信息</span>
					     </a>
					</li>
					
					<li>
					     <a href="${ctx}/pages/resource/form/index" target="iframe_main">
					          <i class="icon-globe"></i>
					          <span>资源信息</span>
					     </a>
					</li>
					
					<li>
					     <a href="${ctx}/pages/function/form/index" target="iframe_main">
					          <i class="icon-globe"></i>
					          <span>功能信息</span>
					     </a>
					</li>
					 -->
					
				</ul><!--/.nav-list-->

				<div id="sidebar-collapse">
					<i class="icon-double-angle-left"></i>
				</div>
			</div>

			<div id="main-content" class="clearfix">
				<div class="clearfix">
					<div id="breadcrumbs">
						<!-- InstanceBeginEditable name="当前位置" -->
						<ul id="navigation" class="breadcrumb">
							<li><i class="icon-home"></i><a>首页</a></li>
						</ul>
						<!--.breadcrumb-->
						<!-- InstanceEndEditable -->
						<!--#nav-search
						<div id="nav-search">
							<form class="form-search">
								<span class="input-icon"> <input type="text"
									placeholder="Search ..." class="input-small search-query"
									id="nav-search-input" autocomplete="off" /> <i
									class="icon-search" id="nav-search-icon"></i>
								</span>
							</form>
						</div>
						-->
					</div>
					<iframe id="iframeid" src="${ctx}/pages/main.jsp" name="iframe_main" frameborder="0" overflow-y="scroll"; width="100%" />
				</div>
			</div><!--/#main-content-->
		</div><!--/.fluid-container#main-container-->

		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only bigger-110"></i>
		</a>

		<!--inline scripts related to this page-->
	</body>
<!-- InstanceEnd --></html>
