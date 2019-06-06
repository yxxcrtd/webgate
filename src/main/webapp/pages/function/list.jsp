<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Title"/></title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="${ctx}/js/common.js"></script>
<script src="${ctx}/pages/function/list.js"></script>
<script src="${ctx}/pages/function/list_init.js"></script>
<script type="text/javascript">
	/**** 页面Label国际化 ****/
	var Function_List_Table_Column_ID = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.List.Table.Column.ID'/>";
	var Function_List_Table_Column_Code = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.List.Table.Column.Code'/>";
	var Function_List_Table_Column_Name = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.List.Table.Column.Name'/>";
	var Function_List_Table_Column_Path = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.List.Table.Column.Path'/>";
	var Function_List_Table_Column_Page = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.List.Table.Column.Page'/>";
	/**** 页面功能 ****/
	var Function_Button_Mod = '<ingenta-tag:BtnTag limit="${form.limit}" code="mod" iconStyle="icon-edit bigger-120" buttonStyle="btn btn-mini btn-warning" scopeName="functionMap" lang="lang" name="Function.List.Button.Mod" i18n="true" onClick="#mod#"/>';
	var Function_Button_Del = '<ingenta-tag:BtnTag limit="${form.limit}" code="del" iconStyle="icon-trash bigger-120" buttonStyle="btn btn-mini btn-danger" scopeName="functionMap" lang="lang" name="Function.List.Button.Del" i18n="true" onClick="#del#"/>';
</script>
</head>

<body>
	<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!-- ------------------导航页面部分开始----------------------------- -->
				<div class="page-header position-relative">
					<h1>
						<ingenta-tag:LanguageTag sessionKey="lang" key="Function.Nav.Title"/>
						<small>
							<i class="icon-double-angle-right"></i>
							<ingenta-tag:LanguageTag sessionKey="lang" key="Function.List.Nav.Title"/>
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
				
				<div class="row-fluid">
					<!-- ------------------功能页面部分开始----------------------------- -->
					<!-- ------------------功能按钮开始----------------------------- -->
					<div class="ace-thumbnails">
						<ingenta-tag:BtnTag limit="${form.limit}" code="add" iconStyle="icon-plus-sign bigger-125" buttonStyle="btn btn-small btn-primary" scopeName="functionMap" lang="lang" name="Function.List.Button.New" i18n="true" onClick="WebGate.Function.addObj();" />
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
										<ingenta-tag:LanguageTag sessionKey="lang" key="Function.List.Search.Code"/>：
									</label>
									<div class="controls">
										<form:input path="code" id="query_function_code" class="span10" />
									</div>
								</div>
								<div class="control-group span3">
									<label class="control-label" for="form-field-1">
										<ingenta-tag:LanguageTag sessionKey="lang" key="Function.List.Search.Name"/>：
									</label>
									<div class="controls">
										<form:input path="name" id="query_function_name" class="span10" />
									</div>
								</div>
								<div class="control-group span3">
									<label class="control-label" for="form-field-1">
										<ingenta-tag:LanguageTag sessionKey="lang" key="Function.List.Search.Page"/>：
									</label>
									<div class="controls">
										<form:select id="query_function_page" path="pageId" class="span10">
											<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
											<c:forEach items="${form.pageList}" var="page">
												<form:option value="${page.id}">${page.name}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
							<div style="text-align: center;">
								<button class="btn btn-small btn-success" type="button" onclick="WebGate.Function.query();">
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
                        <i class="icon-flag"></i>&nbsp;&nbsp;<ingenta-tag:LanguageTag sessionKey="lang" key="Function.List.Nav.Title"/>
					</div>
					<table id="table_report" class="table table-striped table-bordered table-hover">
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

</body>
</html>
