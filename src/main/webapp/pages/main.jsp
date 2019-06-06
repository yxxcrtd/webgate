<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en"><!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
	<head>
	<meta charset="utf-8" />
	<!-- InstanceBeginEditable name="doctitle" -->
	<title>后台管理</title>
	<!-- InstanceEndEditable -->
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!--basic styles-->
	<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${ctx}/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
	<!--[if IE 7]>
		  <link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
		<![endif]-->
	<!--page specific plugin styles-->
	<link rel="stylesheet" href="${ctx}/css/colorbox.css" type="text/css" />
	<link href="${ctx}/css/font-awesome.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${ctx}/css/jquery-ui-1.10.3.custom.min.css" />
    <link rel="stylesheet" href="${ctx}/css/chosen.css" />
    <link rel="stylesheet" href="${ctx}/css/datepicker.css" />
    <link rel="stylesheet" href="${ctx}/css/bootstrap-timepicker.css" />
    <link rel="stylesheet" href="${ctx}/css/daterangepicker.css" />
    <link rel="stylesheet" href="${ctx}/css/colorpicker.css" />    
	<!--fonts-->
	
	<!--ace styles-->
	<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
	<link rel="stylesheet" href="${ctx}/css/ace-responsive.min.css" />
	<link rel="stylesheet" href="${ctx}/css/ace-skins.min.css" />
	<!--[if lte IE 8]>
		  <link rel="stylesheet" href="css/ace-ie.min.css" />
		<![endif]-->
	<!--inline styles if any-->
	<!-- InstanceBeginEditable name="head" -->
	<!-- InstanceEndEditable -->
	<!-- InstanceParam name="内容" type="boolean" value="true" -->
	<!-- InstanceParam name="本页用到的脚本" type="boolean" value="true" -->
	
	<!--basic scripts-->
	<!-- 
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	 -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${ctx}/js/jquery-1.9.1.min.js'>"+"<"+"/script>");
	</script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<!--ace scripts-->

	<script src="${ctx}/js/ace-elements.min.js"></script>
	<script src="${ctx}/js/ace.min.js"></script>
	</head>
	<body>

			<div class="clearfix">
				<div id="page-content" class="clearfix">
					<div class="row-fluid">
						<!--PAGE CONTENT BEGINS HERE--><!-- InstanceBeginEditable name="EditRegion4" -->
					<div class="page-header position-relative">
						<h1>主页</h1>
					</div><!--/.page-header-->
                   
					<div class="row-fluid">
						<!--PAGE CONTENT BEGINS HERE-->

						<div class="alert alert-block alert-success">
							<button type="button" class="close" data-dismiss="alert">
								<i class="icon-remove"></i>
							</button>

							<i class="icon-ok green"></i>

							欢迎
							<strong class="green">
								<!--系统管理员-->
                                ${sessionScope.ccsfw_account.account.name}
								</strong>登陆&nbsp;法律出版社&nbsp;后台管理系统
						</div>

						<div class="space-2"></div>
                      <div class="row-fluid">
						<!--PAGE CONTENT BEGINS HERE-->

                        <!--
						<div class="row-fluid">
							<div class="span6">
								<div class="widget-box">
									<div class="widget-header">
										<h5><strong>最新邮件</strong></h5>

										<div class="widget-toolbar">
											<a href="#" data-action="settings">
												<i class="icon-cog"></i>
											</a>

											<a href="#" data-action="reload">
												<i class="icon-refresh"></i>
											</a>

											<a href="#" data-action="collapse">
												<i class="icon-chevron-up"></i>
											</a>

											<a href="#" data-action="close">
												<i class="icon-remove"></i>
											</a>
										</div>
									</div>

									<div class="widget-body">
										<table class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th width="20%">
															<i class="icon-user"></i>
															发件人
														</th>

														<th width="58%">
															<i class="icon-flag"></i>主题</th>
														<th width="22%" class="hidden-480"><i class="icon-time"></i>时间</th>
													</tr>
												</thead>

												<tbody>
													<tr>
														<td class="">Alex</td>

														<td>
															<a href="#">系统消息  </a>
														</td>

														<td>
															2013-05-03
														12:12</td>
													</tr>

													<tr>
														<td class="">Fred</td>

														<td>
															<a href="#">系统消息  </a>
														</td>

														<td>
															2013-05-03
														12:12</td>
													</tr>

													<tr>
														<td class="">Jack</td>

														<td>
															<a href="#">系统消息  </a>
														</td>

														<td>
															2013-05-03
														12:12</td>
													</tr>

													<tr>
														<td class="">John</td>

														<td>
															<a href="#">系统消息  </a>
														</td>

														<td>
															2013-05-03
														12:12</td>
													</tr>

													<tr>
														<td class="">James</td>

														<td>
															<a href="#">系统消息  </a>
														</td>

														<td>
															2013-05-03
														12:12</td>
													</tr>
                                                    
												</tbody>
											</table>
									</div>
								</div>
							</div>

							<div class="span6">
								<div class="widget-box">
									<div class="widget-header">
										<h5><strong>最近任务</strong></h5>

										<div class="widget-toolbar">
											<a href="#" data-action="settings">
												<i class="icon-cog"></i>
											</a>

											<a href="#" data-action="reload">
												<i class="icon-refresh"></i>
											</a>

											<a href="#" data-action="collapse">
												<i class="icon-chevron-up"></i>
											</a>

											<a href="#" data-action="close">
												<i class="icon-remove"></i>
											</a>
										</div>
									</div>

									<div class="widget-body">
										<div class="row-fluid">
												<div class="span12">
													<ul class="unstyled spaced pd5">
														<li>
															<i class="icon-bell red"></i>
															<a href="#">List with custom icons and more space</a>
														</li>

														<li>
															<i class="icon-bell purple"></i>
															<a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-bell purple"></i>
															<a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-ok purple"></i>
															<a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-bell purple"></i>
															<a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-bell purple"></i>
															<a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-bell purple"></i>
															<a href="#">List with custom icons and more space</a>
														</li>
                                                        
													</ul>

													
												</div>
											</div>
									</div>
								</div>
							</div>
						</div>

						<div class=" space-2"></div>

						<div class="row-fluid">
						<div class="span6">
								<div class="widget-box">
									<div class="widget-header">
										<h5><strong>最近日程</strong></h5>

										<div class="widget-toolbar">
											<a href="#" data-action="settings">
												<i class="icon-cog"></i>
											</a>

											<a href="#" data-action="reload">
												<i class="icon-refresh"></i>
											</a>

											<a href="#" data-action="collapse">
												<i class="icon-chevron-up"></i>
											</a>

											<a href="#" data-action="close">
												<i class="icon-remove"></i>
											</a>
										</div>
									</div>

									<div class="widget-body">
										<div class="row-fluid">
												<div class="span12">
													<ul class="unstyled spaced">
														<li>
															<i class="icon-bell purple"></i>
															List with custom icons and more space
														</li>

														<li>
															<i class="icon-star blue"></i>
															Unordered List Item # 2
														</li>

														<li>
															<i class="icon-remove red"></i>
															Unordered List Item # 3
														</li>

														<li>
															<i class="icon-ok green"></i>
															Unordered List Item # 4 which is a longer item and may break into more lines.
														</li>
													</ul>

													<ul class="unstyled spaced2">
														<li>
															<i class="icon-circle green"></i>
															Even more space
														</li>

														<li class="text-warning orange">
															<i class="icon-warning-sign"></i>
															<a href="#">Unordered List Item # 5</a>
														</li>

														<li class="muted">
															<i class="icon-angle-right"></i>
															Unordered List Item # 6
														</li>

														<li>
															<ul class="inline">
																<li>
																	<i class="icon-share-alt green"></i>
																	Inline List Item # 1
																</li>
																<li>List Item # 2</li>
																<li>List Item # 3</li>
															</ul>
														</li>
													</ul>
												</div>
											</div>
									</div>
								</div>
							</div>	
                        <div class="span6">
								<div class="widget-box">
									<div class="widget-header">
										<h5><strong>最新社讯</strong></h5>

										<div class="widget-toolbar">
											<a href="#" data-action="settings">
												<i class="icon-cog"></i>
											</a>

											<a href="#" data-action="reload">
												<i class="icon-refresh"></i>
											</a>

											<a href="#" data-action="collapse">
												<i class="icon-chevron-up"></i>
											</a>

											<a href="#" data-action="close">
												<i class="icon-remove"></i>
											</a>
										</div>
									</div>

									<div class="widget-body">
										<div class="row-fluid">
												<div class="span12">
													<ul class="unstyled spaced pd5">
														<li>
															<i class="icon-bell red"></i>
															<span class="fr">2013-04-03</span><a href="#">List with custom icons and more space</a>
														</li>

														<li>
															<i class="icon-bell purple"></i>
															<span class="fr">2013-04-03</span><a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-bell purple"></i>
															<span class="fr">2013-04-03</span><a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-ok purple"></i>
															<span class="fr">2013-04-03</span><a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-bell purple"></i>
															<span class="fr">2013-04-03</span><a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-bell purple"></i>
															<span class="fr">2013-04-03</span><a href="#">List with custom icons and more space</a>
														</li>
                                                        <li>
															<i class="icon-bell purple"></i>
															<span class="fr">2013-04-03</span><a href="#">List with custom icons and more space</a>
														</li>
                                                         <li>
															<i class="icon-bell purple"></i>
															<span class="fr">2013-04-03</span><a href="#">List with custom icons and more space</a>
														</li>
													</ul>

													
												</div>
											</div>
									</div>
								</div>
							</div>
						</div>
                        -->


						<!--PAGE CONTENT ENDS HERE-->
					</div>
						

						<!--PAGE CONTENT ENDS HERE-->
					</div><!--/row-->
				<!-- InstanceEndEditable -->
					  <!--PAGE CONTENT ENDS HERE-->
					</div><!--/row-->
				</div><!--/#page-content-->

				<div id="ace-settings-container">
					<div class="btn btn-app btn-mini btn-warning" id="ace-settings-btn">
						<i class="icon-cog"></i>
					</div>

					<div id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hidden">
									<option data-class="default" value="#438EB9">#438EB9</option>
									<option data-class="skin-1" value="#222A2D">#222A2D</option>
									<option data-class="skin-2" value="#C6487E">#C6487E</option>
									<option data-class="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; Choose Skin</span>
						</div>

						<div>
							<input type="checkbox" class="ace-checkbox-2" id="ace-settings-header" />
							<label class="lbl" for="ace-settings-header"> Fixed Header</label>
						</div>

						<div>
							<input type="checkbox" class="ace-checkbox-2" id="ace-settings-sidebar" />
							<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
						</div>
					</div>
				</div><!--/#ace-settings-container-->
			</div><!--/#main-content-->
</body>
</html>