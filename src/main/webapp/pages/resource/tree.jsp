<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Title"/></title>
<link rel="stylesheet" href="${ctx }/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript" src="${ctx}/ztree/js/jquery.ztree.exedit-3.4.js"></script>
<script type="text/javascript">
	/**** 页面Label国际化 ****/
	var ResourceRole_List_Table_Column_ID = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourceRole.List.Table.Column.ID'/>";
	var ResourceRole_List_Table_Column_Name = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourceRole.List.Table.Column.Name'/>";
	var ResourceRole_List_Table_Column_System = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourceRole.List.Table.Column.System'/>";
	var ResourceRole_List_Table_Column_Status = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourceRole.List.Table.Column.Status'/>";

	var ResourcePage_List_Table_Column_ID = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourcePage.List.Table.Column.ID'/>";
	var ResourcePage_List_Table_Column_Code = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourcePage.List.Table.Column.Code'/>";
	var ResourcePage_List_Table_Column_Name = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourcePage.List.Table.Column.Name'/>";
	var ResourcePage_List_Table_Column_Path = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourcePage.List.Table.Column.Path'/>";
	var ResourcePage_List_Table_Column_Resource = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourcePage.List.Table.Column.Resource'/>";
	
	/**** 页面Select国际化 ****/
	var ResourceRole_Status_Using = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourceRole.Status.Using'/>";
	var ResourceRole_Status_Useless = "<ingenta-tag:LanguageTag sessionKey='lang' key='ResourceRole.Status.Useless'/>";
</script>
<script src="${ctx}/js/common.js"></script>
<script src="${ctx}/pages/resource/tree.js"></script>
<script src="${ctx}/pages/resource/tree_init.js"></script>
<script src="${ctx}/pages/resource/role_list.js"></script>
<script src="${ctx}/pages/resource/page_list.js"></script>
<style type="text/css">

#sidebar {
	width: 30%;
}

#sidebar:before {
	width: 30%;
}

#main-content {
	margin-left: 30%;
}

#sidebar-shortcuts {
	line-height: 0px;
	max-height: 0px;
}

div#rMenu {
	visibility: hidden;
	position: absolute;
	top: 0;
	text-align: left;
	padding: 2px;
	backgroundColor: #FFFFFF;
}

div#rMenu ul {
	listStyle: none;
	margin: 0px;
	background-color: #FFFFFF;
	border: 1px solid #999;
	width: 140px;
	padding: 1px;
}

div#rMenu ul li {
	margin: 0px;
	color: #000;
	display: block;
	cursor: default;
	padding: 1px;
	border: 1px solid #fff;
	background-color: transparent;
	font: normal 12px arial, tahoma, helvetica, sans-serif;
}

div#rMenu ul li img {
	vertical-align: middle;
}

div#shadow {
	background-color: #b6bdd2;
	position: absolute;
	opacity: 0.2;
	zIndex: 499;
}
</style>
</head>
<body>
	<%-- 
	<input type="hidden" id="resourceId" value=<%=request.getParameter("id") %> />
	<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!--PAGE CONTENT BEGINS HERE-->
				<!-- InstanceBeginEditable name="EditRegion4" -->
				<div class="page-header position-relative">
					<h1>
						<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Nav.Title"/>
						<small>
						    <i class="icon-double-angle-right"></i>
							<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Nav.Title"/>
						</small>
					</h1>
				</div>
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
				<div id="shadow"></div>
				<div id="rMenu">
					<ul>
						<li id="add" onclick='WebGate.Resource.addObj();' onmouseover="this.style.backgroundColor='#b6bdd2';" onmouseout="this.style.backgroundColor='';">添加</li>
						<li id="edit" onclick='WebGate.Resource.editObj();' onmouseover="this.style.backgroundColor='#b6bdd2';" onmouseout="this.style.backgroundColor='';">修改</li>
						<li id="delete" onclick='WebGate.Resource.delObj();' onmouseover="this.style.backgroundColor='#b6bdd2';" onmouseout="this.style.backgroundColor='';">删除</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	 --%>
	<!-- 
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<div class="page-header position-relative">
				<h1>
					<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Nav.Title"/>
					<small>
					    <i class="icon-double-angle-right"></i>
						<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Nav.Title"/>
					</small>
				</h1>
			</div>
		</div>
	</div>
	 -->
	<div id="sidebar">
		<div id="sidebar-collapse" onclick="WebGate.Resource.hideTree();">
			<i class="icon-double-angle-left"></i>
		</div>
		<div id="sidebar-shortcuts">
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div id="shadow"></div>
			<div id="rMenu">
				<ul>
					<li id="add" onclick='WebGate.Resource.addObj();' onmouseover="this.style.backgroundColor='#b6bdd2';" onmouseout="this.style.backgroundColor='';">创建资源</li>
					<li id="edit" onclick='WebGate.Resource.editObj();' onmouseover="this.style.backgroundColor='#b6bdd2';" onmouseout="this.style.backgroundColor='';">修改资源</li>
					<li id="delete" onclick='WebGate.Resource.delObj();' onmouseover="this.style.backgroundColor='#b6bdd2';" onmouseout="this.style.backgroundColor='';">删除资源</li>
					<li id="page" onclick='WebGate.Resource.showPageList();' onmouseover="this.style.backgroundColor='#b6bdd2';" onmouseout="this.style.backgroundColor='';">页面管理</li>
					<li id="role" onclick='WebGate.Resource.showRoleList();' onmouseover="this.style.backgroundColor='#b6bdd2';" onmouseout="this.style.backgroundColor='';">角色管理</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="main-content" class="clearfix">
		<%@ include file="/pages/common/ajaxMsg.jsp"%>
		<div id="pageContent" class="hide">
			<div class="clearfix">
				<div id="page-content" class="clearfix">
					<div class="row-fluid">
						<!-- ------------------导航页面部分开始----------------------------- -->
						<div class="page-header position-relative">
							<h1>
								<ingenta-tag:LanguageTag sessionKey="lang" key="Page.Nav.Title"/>
								<small>
								<i class="icon-double-angle-right"></i>
								<ingenta-tag:LanguageTag sessionKey="lang" key="ResourcePage.List.Nav.Title"/>
								</small>
							</h1>
						</div>
						<!-- ------------------导航页面部分结束----------------------------- -->
						<div class="row-fluid">
							<!-- ------------------功能页面部分开始----------------------------- -->
							<!-- ------------------功能按钮开始----------------------------- -->
							<div class="ace-thumbnails">
								<button class="btn btn-small btn-primary" onclick="WebGate.ResourcePage.addObj();">
									<i class="icon-plus-sign bigger-125"></i>
									<ingenta-tag:LanguageTag sessionKey="lang" key="ResourcePage.List.Button.New"/>
								</button>
							</div>
							<!-- ------------------功能按钮结束----------------------------- -->
							
							<!-- ------------------查询开始----------------------------- -->
                            <div class="table-header on">
                                <i class="icon-caret-down"></i>&nbsp;&nbsp;查询条件
                            </div>
                            <div class="on-down">
							<form:form id="form" commandName="form" action="manager"
								class="form-horizontal">
								<div class="row-fluid">
									<div class="row-fluid">
										<div class="control-group span3">
											<label class="control-label" for="form-field-1">
												<ingenta-tag:LanguageTag sessionKey="lang" key="ResourcePage.List.Search.Code"/>：
											</label>
											<div class="controls">
												<form:input path="code" id="query_page_code" class="span10" />
											</div>
										</div>
										<div class="control-group span3">
											<label class="control-label" for="form-field-1">
												<ingenta-tag:LanguageTag sessionKey="lang" key="ResourcePage.List.Search.Name"/>：
											</label>
											<div class="controls">
												<form:input path="name" id="query_page_name" class="span10" />
											</div>
										</div>
									</div>
									<input type="hidden" id="query_page_resource" value=""></input>
									<div style="text-align: center;">
										<button class="btn btn-small btn-success" type="button" onclick="WebGate.ResourcePage.query();">
											<i class="icon-zoom-in bigger-110"></i>
											<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry"/>
										</button>
										&nbsp;&nbsp;
										<button type="reset" class="btn btn-small btn-inverse">
											<i class="icon-repeat bigger-110"></i>
											<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Reset"/>
										</button>
									</div>
								</div>
							</form:form>
                            </div>
							<!-- ------------------功能页面部分结束----------------------------- -->
		
							<!-- ------------------列表页面部分开始----------------------------- -->
							<div class="table-header">
                                <i class="icon-flag"></i>&nbsp;&nbsp;<ingenta-tag:LanguageTag sessionKey="lang" key="Page.List.Nav.Title"/>
							</div>
							<table id="page_table_report" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width='10%' align='center'></th>
										<th width='10%' align='center'></th>
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
										<th width='10%' align='center'></th>
										<th width='10%' align='center'></th>
									</tr>
								</tfoot>
							</table>
							<!-- ------------------列表页面部分结束----------------------------- -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="roleContent" class="hide">
			<div class="clearfix">
				<div id="page-content" class="clearfix">
					<div class="row-fluid">
						<!-- ------------------导航页面部分开始----------------------------- -->
						<div class="page-header position-relative">
							<h1>
								<ingenta-tag:LanguageTag sessionKey="lang" key="Role.Nav.Title"/>
								<small>
								<i class="icon-double-angle-right"></i>
								<ingenta-tag:LanguageTag sessionKey="lang" key="ResourceRole.List.Nav.Title"/>
								</small>
							</h1>
						</div>
						<!-- ------------------导航页面部分结束----------------------------- -->
						<div class="row-fluid">
							<!-- ------------------功能页面部分开始----------------------------- -->
							<!-- ------------------功能按钮开始----------------------------- -->
						    <div class="ace-thumbnails">
								<button id="save" class="btn btn-small btn-primary" onclick="WebGate.ResourceRole.saveRoleList();">
									<i class="icon-save bigger-125"></i>
									<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Save"/>
								</button>
							</div>
							<!-- ------------------功能按钮结束----------------------------- --> 
							<!-- ------------------查询开始----------------------------- -->
                            <div class="table-header on">
                                <i class="icon-caret-down"></i>&nbsp;&nbsp;查询条件
                            </div>
                            <div class="on-down">
							<form:form id="form" commandName="form" action="manager" class="form-horizontal">
							     <div class="row-fluid">
							         <div class="row-fluid">
							             <div class="control-group span3">
							                 <label class="control-label" for="form-field-1">
												<ingenta-tag:LanguageTag sessionKey="lang" key="ResourceRole.List.Search.Name"/>：
											</label>
											<div class="controls">
												<form:input path="name" id="query_role_name" class="span10" />
											</div>
										</div>
										<div class="control-group span3">
											<label class="control-label" for="form-field-1">
												<ingenta-tag:LanguageTag sessionKey="lang" key="ResourceRole.List.Search.System"/>：
											</label>
											<div class="controls">
												<select id="query_role_system" class="span10" disabled>
													<option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</option>
												</select>
											</div>
										</div>
										<div class="control-group span3">
											<label class="control-label" for="form-field-2">
												<ingenta-tag:LanguageTag sessionKey="lang" key="ResourceRole.List.Search.Status"/>：
											</label>
											<div class="controls">
												<select id="query_role_status" class="span10">
													<option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</option>
												</select>
											</div>
										</div>
									</div>
									<input type="hidden" id="query_role_resource" value=""></input>
									<div style="text-align: center;">
										<button class="btn btn-small btn-success" type="button" onclick="WebGate.ResourceRole.query();">
											<i class="icon-zoom-in bigger-110"></i>
											<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry"/>
										</button>
										&nbsp;&nbsp;
										<button type="reset" class="btn btn-small btn-inverse">
											<i class="icon-repeat bigger-110"></i>
											<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Reset"/>
										</button>
									</div>
								</div>
							</form:form>
                            </div>
							<!-- ------------------查询结束----------------------------- -->
							<!-- ------------------功能页面部分结束----------------------------- -->
		
							<!-- ------------------列表页面部分开始----------------------------- -->
							<div class="table-header">
                                <i class="icon-flag"></i>&nbsp;&nbsp;<ingenta-tag:LanguageTag sessionKey="lang" key="ResourceRole.List.Nav.Title"/>
							</div>
							<table id="role_table_report" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width='5%' align='center'>
											<label>
												<input type="checkbox" />
												<span class="lbl"></span>
											</label>
										</th>
										<th width='11%' align='center'></th>
										<th width='28%' align='center'></th>
										<th width='28%' align='center'></th>
										<th width='28%' align='center'></th>
									</tr>
								</thead>
								<tbody align='center' style="line-height: 18px; border: 1px solid #97bbdc;">
		
								</tbody>
								<tfoot>
									<tr>
										<th width='5%' align='center'></th>
										<th width='11%' align='center'></th>
										<th width='28%' align='center'></th>
										<th width='28%' align='center'></th>
										<th width='28%' align='center'></th>
									</tr>
								</tfoot>
							</table>
							<!-- ------------------列表页面部分结束----------------------------- -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>