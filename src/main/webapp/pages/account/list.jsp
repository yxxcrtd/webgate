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
<script src="${ctx}/pages/account/list.js"></script>
<script src="${ctx}/pages/account/list_init.js"></script>
<script type="text/javascript">
	/**** 页面Label国际化 ****/
	var Account_List_Table_Column_ID = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.List.Table.Column.ID'/>";
	var Account_List_Table_Column_Name = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.List.Table.Column.Name'/>";
	var Account_List_Table_Column_Status = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.List.Table.Column.Status'/>";
	var Account_List_Table_Column_Type = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.List.Table.Column.Type'/>";
	var Account_List_Table_Column_Level = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.List.Table.Column.Level'/>";
	/**** 页面Select国际化 ****/
	var Account_Status_Using = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Status.Using'/>";
	var Account_Status_Useless = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Status.Useless'/>";
	var Account_Type_Local = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Type.Local'/>";
	var Account_Type_Sina = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Type.Sina'/>";
	var Account_Level_Admin = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Level.Admin'/>";
	var Account_Level_User = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Level.User'/>";
</script>
</head>

<body>
	<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!-- ------------------导航页面部分开始----------------------------- -->
				<div class="page-header position-relative">
					<h1>
						<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Nav.Title"/>
						<small>
							<i class="icon-double-angle-right"></i>
							<ingenta-tag:LanguageTag sessionKey="lang" key="Account.List.Nav.Title"/>
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
				
				<div class="row-fluid">
					<!-- ------------------功能页面部分开始----------------------------- -->
					<!-- ------------------功能按钮开始----------------------------- -->
                    <div class="ace-thumbnails">
                        <button class="btn btn-small btn-primary" onclick="WebGate.Account.addObj();">
                            <i class="icon-plus-sign bigger-125"></i> <ingenta-tag:LanguageTag sessionKey="lang" key="Account.List.Button.New"/>
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
										<ingenta-tag:LanguageTag sessionKey="lang" key="Account.List.Search.Name"/>：
									</label>
									<div class="controls">
										<form:input path="name" id="query_account_name" class="span10" />
									</div>
								</div>
								<div class="control-group span3">
									<label class="control-label" for="form-field-2">
										<ingenta-tag:LanguageTag sessionKey="lang" key="Account.List.Search.Status"/>：
									</label>
									<div class="controls">
										<form:select id="query_account_status" path="status" class="span10">
											<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
											<form:options items="${form.statusMap}" />
										</form:select>
									</div>
								</div>
								<div class="control-group span3">
									<label class="control-label" for="form-field-2">
										<ingenta-tag:LanguageTag sessionKey="lang" key="Account.List.Search.Type"/>：
									</label>
									<div class="controls">
										<form:select id="query_account_type" path="status" class="span10">
											<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
											<form:options items="${form.typeMap}" />
										</form:select>
									</div>
								</div>
								<div class="control-group span3">
									<label class="control-label" for="form-field-2">
										<ingenta-tag:LanguageTag sessionKey="lang" key="Account.List.Search.Level"/>：
									</label>
									<div class="controls">
										<form:select id="query_account_level" path="status" class="span10">
											<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
											<form:options items="${form.levelMap}" />
										</form:select>
									</div>
								</div>
							</div>
							<div style="text-align: center;">
								<button class="btn btn-small btn-success" type="button" onclick="WebGate.Account.query();">
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
                        <i class="icon-flag"></i>&nbsp;&nbsp;<ingenta-tag:LanguageTag sessionKey="lang" key="Account.List.Nav.Title"/>
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
