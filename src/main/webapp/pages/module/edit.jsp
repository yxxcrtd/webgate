<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html>
<html lang="en">
<!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Title"/></title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="${ctx}/js/common.js"></script>
<script src="${ctx}/pages/module/list.js"></script>
<script src="${ctx}/pages/module/edit.js"></script>
<script type="text/javascript">
	/**** 页面Validate国际化 ****/
	var Module_Info_ValidateName_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateName.Blank'/>";
	var Module_Info_ValidateLink_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateLink.Blank'/>";
	var Module_Info_ValidateIcon_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateIcon.Blank'/>";
	var Module_Info_ValidateMust_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateMust.Blank'/>";
	var Module_Info_ValidateHeight_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateHeight.Blank'/>";
	var Module_Info_ValidateWidth_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateWidth.Blank'/>";
	var Module_Info_ValidateHeight_Number = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateHeight.Number'/>";
	var Module_Info_ValidateWidth_Number = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateWidth.Number'/>";
	var Module_Info_ValidateI18n_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Module.Info.ValidateI18n.Blank'/>";
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
						<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Nav.Title"/>
						<small>
							<i class="icon-double-angle-right"></i>
							<c:if test="${form.id==null||form.id=='0'||form.id==''}">
					    		<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.New.Nav.Title"/>
					    	</c:if>
					    	<c:if test="${form.id!=null&&form.id!='0'&&form.id!=''}">
					    		<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Update.Nav.Title"/>
					    	</c:if>
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
				<div class="row-fluid">
					<!-- ------------------表单部分开始----------------------------- -->
					<!-- <form class="form-horizontal"> -->
                    <div class="table-header on">
                        基本信息
                    </div>
					<form:form id="ModuleForm" commandName="form" class="form-horizontal">
                    <div class="on-down">
						<div class="row-fluid">
							<div id="nameDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.Name"/>：
								</label>
								<div class="controls">
									<!-- <input type="text" name="obj.name" id="name" value="${form.obj.name }" placeholder="模块名称" /> -->
	                 				<form:input path="obj.name" id="name" placeholder="模块名称" onblur="WebGate.Module.validateName();"/>
									<span id="nameSpan" class="help-inline"></span>
								</div>
							</div>
							<div id="i18nDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.I18n"/>：
								</label>
								<div class="controls">
									<form:select path="obj.i18n" id="i18n" onblur="WebGate.Module.validateI18n();">
										<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
										<form:options items="${form.i18nMap}" />
									</form:select>
									<span id="i18nSpan" class="help-inline"></span>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div id="linkDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.Link"/>：
								</label>
								<div class="controls">
	                 				<form:input path="obj.link" id="link" placeholder="模块连接" onblur="WebGate.Module.validateLink();"/>
									<span id="linkSpan" class="help-inline"></span>
								</div>
							</div>
							<div id="moreDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.More"/>：
								</label>
								<div class="controls">
	                 				<form:input path="obj.more" id="more" placeholder="模块更多链接"/>
									<span id="moreSpan" class="help-inline"></span>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div id="widthDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.Width"/>：
								</label>
								<div class="controls">
	                 				<form:input path="obj.width" id="width" placeholder="模块宽度" onblur="WebGate.Module.validateWidth();"/>
									<span id="widthSpan" class="help-inline"></span>
								</div>
							</div>
							<div id="heightDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.Height"/>：
								</label>
								<div class="controls">
	                 				<form:input path="obj.height" id="height" placeholder="模块高度" onblur="WebGate.Module.validateHeight();"/>
									<span id="heightSpan" class="help-inline"></span>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div id="componentDiv"class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.Component"/>：
								</label>
								<div class="controls">
									<form:select path="obj.component.id" id="component">
										<form:option value="">-<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/>-</form:option>
										<c:forEach items="${form.componentList}" var="c">
											<form:option value="${c.id}">${c.name}</form:option>
										</c:forEach>
									</form:select>
									<span id="componentSpan" class="help-inline"></span>
								</div>
							</div>
							<!--  
							<div id="iconDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.Icon"/>：
								</label>
								<div class="controls">
	                 				<form:input path="obj.icon" id="icon" placeholder="模块图标" onblur="WebGate.Module.validateIcon();"/>
									<span id="iconSpan" class="help-inline"></span>
								</div>
							</div>
							-->
							<div id="mustDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Module.Edit.Form.Must"/>：
								</label>
								<div class="controls">
									<form:select path="obj.must" id="must" onblur="WebGate.Module.validateMust();">
										<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
										<form:options items="${form.mustMap}" />
									</form:select>
									<span id="mustSpan" class="help-inline"></span>
								</div>
							</div>
						</div>
					<form:hidden path="id"/>
                    </div>
					<!-- </form> -->
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
