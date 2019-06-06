<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
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
<script src="${ctx}/pages/account/edit.js"></script>
<script type="text/javascript">
	/**** 页面Validate国际化 ****/
	var Account_Info_ValidateName_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Info.ValidateName.Blank'/>";
	var Account_Info_ValidateStatus_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Info.ValidateStatus.Blank'/>";
	var Account_Info_ValidateType_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Info.ValidateType.Blank'/>";
	var Account_Info_ValidateLevel_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Info.ValidateLevel.Blank'/>";
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
						<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Nav.Title"/>
						<small>
							<i class="icon-double-angle-right"></i>
							<c:if test="${form.id==null||form.id=='0'||form.id==''}">
					    		<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.New.Nav.Title"/>
					    	</c:if>
					    	<c:if test="${form.id!=null&&form.id!='0'&&form.id!=''}">
					    		<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Update.Nav.Title"/>
					    	</c:if>
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
				<div class="row-fluid">
					<!-- ------------------表单部分开始----------------------------- -->
                    <div class="table-header on">
                        基本信息
                    </div>
					<form:form id="AccountForm" commandName="form" class="form-horizontal">
                    <div class="on-down">
						<div id="nameDiv" class="control-group">
							<label class="control-label" for="form-field-1">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Form.Name"/>：
							</label>
							<div class="controls">
                 				<form:input path="obj.name" id="name" placeholder="账户名称" onblur="WebGate.Account.validateName();"/>
								<span id="nameSpan" class="help-inline"></span>
							</div>
						</div>
						<div id="statusDiv" class="control-group">
							<label class="control-label" for="form-field-2">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Form.Status"/>：
							</label>
							<div class="controls">
								<form:select path="obj.status" id="status" onblur="WebGate.Account.validateStatus();">
									<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
									<form:options items="${form.statusMap}" />
								</form:select>
								<span id="statusSpan" class="help-inline"></span>
							</div>
						</div>
						<div id="typeDiv" class="control-group">
							<label class="control-label" for="form-field-2">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Form.Type"/>：
							</label>
							<div class="controls">
								<form:select path="obj.type" id="type" onblur="WebGate.Account.validateType();">
									<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
									<form:options items="${form.typeMap}" />
								</form:select>
								<span id="typeSpan" class="help-inline"></span>
							</div>
						</div>
						<div id="levelDiv" class="control-group">
							<label class="control-label" for="form-field-2">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Form.Level"/>：
							</label>
							<div class="controls">
								<form:select path="obj.level" id="level" onblur="WebGate.Account.validateLevel();">
									<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
									<form:options items="${form.levelMap}" />
								</form:select>
								<span id="levelSpan" class="help-inline"></span>
							</div>
						</div>
					    <form:hidden path="id"/>
                    </div>
					<!-- ------------------表单部分结束----------------------------- -->
					<!-- ------------------表单按钮部分开始----------------------------- -->
					<div class="form-actions" style="text-align: center;">
						<button id="save" class="btn btn-success" type="submit">
							<i class="icon-save bigger-110"></i>
							<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Save"/>
						</button>
						&nbsp; &nbsp; &nbsp;
						<button id="reset" class="btn btn-inverse" type="reset">
							<i class="icon-undo bigger-110"></i>
							<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Reset"/>
						</button>
					</div>
					<!-- ------------------表单按钮部分结束----------------------------- -->
                    </form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
