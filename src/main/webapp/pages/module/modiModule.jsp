<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html>
<html lang="en">
<!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Title" /></title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="${ctx}/js/common.js"></script>
<script src="${ctx}/js/jquery.cookie.js"></script>
<script src="${ctx}/js/popbaseball.js"></script>
<script src="${ctx}/pages/module/indexView.js"></script>
<script src="${ctx}/pages/module/modiModule.js"></script>

<script type="text/javascript">
	/**** 页面Validate国际化 ****/
	var Module_Info_ValidateName_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateName.Blank'/>";
</script>
</head>

<body>
	<%@ include file="/pages/common/ajaxMsg.jsp"%>
	<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!-- ------------------导航页面部分开始----------------------------- -->
				<div class="page-header position-relative">
					<h1>
						<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Nav.Title" />
						<small> <i class="icon-double-angle-right"></i> <c:if test="${form.id==null||form.id=='0'||form.id==''}">
                                自定义首页模块
							</c:if> <c:if test="${form.id!=null&&form.id!='0'&&form.id!=''}">
                                模块设置
							</c:if>
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
				<div class="row-fluid">
					<!-- ------------------表单部分开始----------------------------- -->
                    <%--
					<!-- <form class="form-horizontal"> -->
					<table id="table_report" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th width='10%' align='center'>序号</th>
								<th width='10%' align='center'>模块名称</th>
								<th width='10%' align='center'>必选</th>
								<th width='10%' align='center'>操作</th>
							</tr>
						</thead>
						<tbody align='center' style="line-height: 18px; border: 1px solid #97bbdc;">

						</tbody>
						<tfoot>
							<tr>
								<th>必选模块</th>
							</tr>
							<c:forEach items="${form.sysModules}" var="s" varStatus="sysModules">
								<c:if test="${s.must == 1}">
									<tr>
										<th width='10%' align='center'>${sysModules.index}</th>
										<th width='10%' align='center'>${s.name}</th>
										<th width='10%' align='center'>${s.must}</th>
										<th width='10%' align='center'>-</th>
									</tr>
								</c:if>
							</c:forEach>
							<tr>
								<th>未选模块</th>
							</tr>
							<c:forEach items="${form.sysModules}" var="s" varStatus="sysModules">
								<c:if test="${s.must == 2}">
									<tr>
										<th width='10%' align='center'>${sysModules.index}</th>
										<th width='10%' align='center'>${s.name}</th>
										<th width='10%' align='center'>${s.must}</th>
										<th width='10%' align='center'><c:if test="${s.hideShow != 'trueShow' }">
												<button class="btn btn-primary" buttonId="${s.id}" onclick="WebGate.Module.showModile('${s.id}','2');">
													<i class="icon-edit bigger-125" id="${s.id}iClass">隐藏该模块到首页</i>
												</button>
											</c:if> <c:if test="${s.hideShow == 'trueShow' }">
												<button class="btn btn-primary" buttonId="${s.id}" onclick="WebGate.Module.showModile('${s.id}','1');">
													<i class="icon-edit bigger-125" id="${s.id}iClass">添加该模块到首页</i>
												</button>
											</c:if>
										</th>
									</tr>
								</c:if>
							</c:forEach>
						</tfoot>
					</table>
					--%>

                    <form:form id="form" commandName="form" class="form-horizontal">
                        <form:hidden path="hideIds" id="hideIds" />
                    </form:form>
                    <div class="table-header">
                        <i class="icon-flag"></i>&nbsp;&nbsp;自定义模块列表
                    </div>
                    <table id="table_report" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width='10%' align='center'></th>
                            <th width='10%' align='center'></th>
                            <th width='10%' align='center'></th>
                            <th width='10%' align='center'></th>
                        </tr>
                        </thead>
                        <tbody align='center' style="line-height: 18px; border: 1px solid #97bbdc;">

                        </tbody>
                        <tfoot>
                        <tr>
                            <th width='10%' align='center'></th>
                            <th width='10%' align='center'></th>
                            <th width='10%' align='center'></th>
                            <th width='10%' align='center'></th>
                        </tr>
                        </tfoot>
                    </table>

					<!-- </form> -->
					<!-- ------------------表单部分结束----------------------------- -->
					<!-- ------------------表单按钮部分开始----------------------------- -->
					<!-- ------------------表单按钮部分结束----------------------------- -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>
