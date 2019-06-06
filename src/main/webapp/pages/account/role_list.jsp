<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Title"/></title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript">
	/**** 页面Label国际化 ****/
	var AccountRole_List_Table_Column_ID = "<ingenta-tag:LanguageTag sessionKey='lang' key='AccountRole.List.Table.Column.ID'/>";
	var AccountRole_List_Table_Column_Name = "<ingenta-tag:LanguageTag sessionKey='lang' key='AccountRole.List.Table.Column.Name'/>";
	var AccountRole_List_Table_Column_System = "<ingenta-tag:LanguageTag sessionKey='lang' key='AccountRole.List.Table.Column.System'/>";
</script>
<script src="${ctx}/js/common.js"></script>
<script src="${ctx}/pages/account/role_list.js"></script>
<script src="${ctx}/pages/account/role_list_init.js"></script>
</head>
<body>
    <div class="clearfix">
        <div id="page-content" class="clearfix">
            <div class="row-fluid">
            <!-- ------------------导航页面部分开始----------------------------- -->
				<div class="page-header position-relative">
					<h1>
						<ingenta-tag:LanguageTag sessionKey="lang" key="AccountRole.Nav.Title"/>
						<small>
							<i class="icon-double-angle-right"></i>
							<ingenta-tag:LanguageTag sessionKey="lang" key="AccountRole.List.Nav.Title"/>
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
				<%@ include file="/pages/common/ajaxMsg.jsp"%>
				<input type="hidden" id="accountId" value="<%=request.getParameter("accountId")%>" ></input>
				<div class="row-fluid">
					<!-- ------------------功能页面部分开始----------------------------- -->
					<!-- ------------------功能按钮开始----------------------------- -->
				    <div class="ace-thumbnails">
						<button id="save" class="btn btn-small btn-primary" onclick="WebGate.AccountRole.saveRoleList();">
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
										<ingenta-tag:LanguageTag sessionKey="lang" key="AccountRole.List.Search.Name"/>：
									</label>
									<div class="controls">
										<form:input path="name" id="query_role_name" class="span10" />
									</div>
								</div>
								<div class="control-group span3">
									<label class="control-label" for="form-field-1">
										<ingenta-tag:LanguageTag sessionKey="lang" key="AccountRole.List.Search.System"/>：
									</label>
									<div class="controls">
										<form:select id="query_role_system" path="sysId" class="span10">
									        <form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
											     <c:forEach items="${form.systemList}" var="system">
													  <form:option value="${system.id}">${system.name}</form:option>
											     </c:forEach>
									    </form:select>
									</div>
								</div>
							</div>
							<div style="text-align: center;">
								<button class="btn btn-small btn-success" type="button" onclick="WebGate.AccountRole.query();">
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
                        <i class="icon-flag"></i>&nbsp;&nbsp;<ingenta-tag:LanguageTag sessionKey="lang" key="AccountRole.List.Nav.Title"/>
					</div>
					<table id="table_report" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th width='5%' class="center">
									<label>
										<input type="checkbox" />
										<span class="lbl"></span>
									</label>
								</th>
								<th width='15%' align='center'></th>
								<th width='40%' align='center'></th>
								<th width='40%' align='center'></th>
							</tr>
						</thead>
						<tbody align='center' style="line-height: 18px; border: 1px solid #97bbdc;">

						</tbody>
						<tfoot>
							<tr>
								<th width='5%' align='center'></th>
								<th width='15%' align='center'></th>
								<th width='40%' align='center'></th>
								<th width='40%' align='center'></th>
							</tr>
						</tfoot>
					</table>
					<!-- ------------------列表页面部分结束----------------------------- -->
				</div>
            </div>
        </div>
    </div>

</body>
</html>