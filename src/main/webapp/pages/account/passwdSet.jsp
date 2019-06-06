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
<script src="${ctx}/pages/account/passwdSet.js"></script>
<script type="text/javascript">
	/**** 页面Validate国际化 ****/
	var Account_Info_Origin_Pass_Validate_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Info.Origin.Pass.Validate.Blank'/>";
	var Account_Info_New_Pass_Validate_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account。Info。New。Pass.Validate.Blank'/>";
	var Account_Info_Confirm_Pass_Validate_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Info.Confirm.Pass.Validate.Blank'/>";
	var Account_Info_New_Confirm_Pass_Not_Same = "<ingenta-tag:LanguageTag sessionKey='lang' key='Account.Info.New.Confirm.Pass.Not.Same'/>";
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
					    	<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Passwd.Set.Nav.Title"/>
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
				<div class="row-fluid">
					<!-- ------------------表单部分开始----------------------------- -->
                    <div class="table-header on">
                        基本信息
                    </div>
					<form:form id="form" commandName="form" action="editSubmit" class="form-horizontal">
                    <div class="on-down">
						<div id="opassDiv" class="control-group">
							<label class="control-label" for="form-field-1">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Form.Origin_Pass"/>：
							</label>
							<div class="controls">
                 				<form:password path="origin_pass" id="origin_pass" placeholder="原始密码" onblur="WebGate.Account.originPassValidate();"/>
								<span id="opassSpan" class="help-inline"></span>
							</div>
						</div>
						<div id="npassDiv" class="control-group">
							<label class="control-label" for="form-field-1">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Form.New_Pass"/>：
							</label>
							<div class="controls">
                 				<form:password path="new_pass" id="new_pass" placeholder="新密码" onblur="WebGate.Account.newPassValidate();"/>
								<span id="npassSpan" class="help-inline"></span>
							</div>
						</div>
						<div id="cpassDiv" class="control-group">
							<label class="control-label" for="form-field-1">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Account.Edit.Form.Confirm_Pass"/>：
							</label>
							<div class="controls">
                 				<form:password path="confirm_pass" id="confirm_pass" placeholder="确认密码" onblur="WebGate.Account.confirmPassValidate();"/>
								<span id="cpassSpan" class="help-inline"></span>
							</div>
						</div>
						<form:hidden path="id"/>
                    </div>
                    </form:form>
						<!-- ------------------表单部分结束----------------------------- -->
						<!-- ------------------表单按钮部分开始----------------------------- -->
						<div class="form-actions" style="text-align: center;">
							<button id="save" class="btn btn-success" onclick="WebGate.Account.passwdSave();">
								<i class="icon-save bigger-110"></i>
								<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Save"/>
							</button>
							&nbsp; &nbsp; &nbsp;
							<button id="reset" class="btn btn-inverse" type="reset" onclick = "clearMessage()">
								<i class="icon-undo bigger-110"></i>
								<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Button.Reset"/>
							</button>
						</div>
						<!-- ------------------表单按钮部分结束----------------------------- -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>
