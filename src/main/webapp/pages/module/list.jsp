<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html>
<html lang="en">
<!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Title" /></title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!--[if IE 7]>
	<link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
<![endif]-->
<!--[if lte IE 8]>
	<link rel="stylesheet" href="css/ace-ie.min.css" />
<![endif]-->
<!--inline styles if any-->
<script src="${ctx}/js/common.js"></script>
<script src="${ctx}/pages/module/list.js"></script>
<script src="${ctx}/pages/module/list_init.js"></script>
<script type="text/javascript">
	/**** 页面Label国际化 ****/
	var Module_List_Table_Column_ID = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.ID'/>";
	var Module_List_Table_Column_Name = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.Name'/>";
	var Module_List_Table_Column_Link = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.Link'/>";
	var Module_List_Table_Column_More = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.More'/>";
	var Module_List_Table_Column_Icon = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.Icon'/>";
	var Module_List_Table_Column_Must = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.Must'/>";
	var Module_List_Table_Column_Height = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.Height'/>";
	var Module_List_Table_Column_Width = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.Width'/>";
	var Module_List_Table_Column_Component_Name = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.List.Table.Column.Component.Name'/>";
	/**** 页面Select国际化 ****/
	var Module_Must_Yes = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Must.Yes'/>";
	var Module_Must_No = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Must.No'/>";
	/**** 页面功能 ****/
	var Module_Button_Mod = '<ingenta-tag:BtnTag limit="${form.limit}" code="mod" iconStyle="icon-edit bigger-120" buttonStyle="btn btn-mini btn-warning" scopeName="functionMap" lang="lang" name="Module.List.Button.Mod" i18n="true" onClick="#mod#"/>';
	var Module_Button_Del = '<ingenta-tag:BtnTag limit="${form.limit}" code="del" iconStyle="icon-trash bigger-120" buttonStyle="btn btn-mini btn-danger" scopeName="functionMap" lang="lang" name="Module.List.Button.Del" i18n="true" onClick="#del#"/>';
</script>
</head>

<body>
	<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!-- ------------------导航页面部分开始----------------------------- -->
				<div class="page-header position-relative">
					<h1>
						<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Nav.Title" />
						<small> <i class="icon-double-angle-right"></i> <ingenta-tag:LanguageTag sessionKey="lang" key="Module.List.Nav.Title" />
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->

				<div class="row-fluid">

					<!-- ------------------功能页面部分开始----------------------------- -->
					<!-- ------------------功能按钮开始----------------------------- -->
					<div class="ace-thumbnails">
                        <button class="btn btn-small btn-primary" onclick="WebGate.Module.addObj();">
                            <i class="icon-plus-sign bigger-125"></i> <ingenta-tag:LanguageTag sessionKey="lang" key="Module.List.Button.New"/>
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
									<label class="control-label" for="form-field-1"> <ingenta-tag:LanguageTag sessionKey="lang" key="Module.List.Search.Name" />：
									</label>
									<div class="controls">
										<form:input path="name" id="query_module_name" class="span10" />
									</div>
								</div>
								<div class="control-group span3">
									<label class="control-label" for="form-field-2"> <ingenta-tag:LanguageTag sessionKey="lang" key="Module.List.Search.Must" />：
									</label>
									<div class="controls">
										<form:select id="query_module_must" path="must" class="span10">
											<form:option value="">-<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Select" />-</form:option>
											<form:options items="${form.mustMap}" />
										</form:select>
									</div>
								</div>
							</div>
							<div style="text-align: center;">
								<button class="btn btn-small btn-success" type="button" onclick="WebGate.Module.query();">
									<i class="icon-save bigger-110"></i>
									<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Inquiry" />
								</button>
								&nbsp;&nbsp;
								<button type="reset" class="btn btn-small btn-inverse">
									<i class="icon-repeat bigger-110"></i>
									<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Reset" />
								</button>
							</div>
						</div>
					</form:form>
                    </div>
					<!-- ------------------查询结束----------------------------- -->
					<!-- ------------------功能页面部分结束----------------------------- -->

					<!-- ------------------列表页面部分开始----------------------------- -->
					<div class="table-header">
                        <i class="icon-flag"></i>&nbsp;&nbsp;<ingenta-tag:LanguageTag sessionKey="lang" key="Module.List.Nav.Title" />
					</div>
					<table id="table_report" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th width='10%' align='center'></th>
								<th width='20%' align='center'></th>
								<th width='30%' align='center'></th>
								<th width='20%' align='center'></th>
								<th width='10%' align='center'></th>
								<th width='10%' align='center'></th>
							</tr>
						</thead>
						<tbody align='center' style="line-height: 18px; border: 1px solid #97bbdc;">

						</tbody>
						<tfoot>
							<tr>
								<th width='10%' align='center'></th>
								<th width='20%' align='center'></th>
								<th width='30%' align='center'></th>
								<th width='20%' align='center'></th>
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
