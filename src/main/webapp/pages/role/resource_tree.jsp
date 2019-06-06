<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Title"/></title>
<link rel="stylesheet" href="${ctx }/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/ztree/js/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript" src="${ctx }/ztree/js/jquery.ztree.excheck-3.4.js"></script>
<script src="${ctx}/pages/role/resource_tree.js"></script>
<script src="${ctx}/pages/role/resource_tree_init.js"></script>
<style type="text/css">

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
	<%@ include file="/pages/common/ajaxMsg.jsp"%>
	<input type="hidden" id="roleId" value=<%=request.getParameter("roleId") %> />
	<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!--PAGE CONTENT BEGINS HERE-->
				<!-- InstanceBeginEditable name="EditRegion4" -->
				<div class="page-header position-relative">
					<h1>
						<ingenta-tag:LanguageTag sessionKey="lang" key="RoleResource.Nav.Title"/>
						<small>
						    <i class="icon-double-angle-right"></i>
							<ingenta-tag:LanguageTag sessionKey="lang" key="RoleResource.Nav.Title"/>
						</small>
					</h1>
				</div>
				<div class="row-fluid">
					<!-- ------------------功能页面部分开始----------------------------- -->
					<!-- ------------------功能按钮开始----------------------------- -->
				    <div class="ace-thumbnails">
						<button id="save" class="btn btn-small btn-primary" onclick="WebGate.RoleResource.saveResourceList();">
							<i class="icon-save bigger-125"></i>
							<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Save"/>
						</button>
					</div>
					<!-- ------------------功能按钮结束----------------------------- -->
					<!-- ------------------功能页面部分结束----------------------------- -->
				</div>
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>