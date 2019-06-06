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
<script src="${ctx}/pages/function/list.js"></script>
<script src="${ctx}/pages/function/edit.js"></script>
<script type="text/javascript">
	/**** 页面Validate国际化 ****/
	var Function_Info_ValidateCode_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.Info.ValidateCode.Blank'/>";
	var Function_Info_ValidateName_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.Info.ValidateName.Blank'/>";
	var Function_Info_ValidatePath_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.Info.ValidatePath.Blank'/>";
	var Function_Info_ValidatePage_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Function.Info.ValidatePage.Blank'/>";
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
						<ingenta-tag:LanguageTag sessionKey="lang" key="Function.Nav.Title"/>
						<small>
							<i class="icon-double-angle-right"></i>
							<c:if test="${form.id==null||form.id=='0'||form.id==''}">
					    		<ingenta-tag:LanguageTag sessionKey="lang" key="Function.Edit.New.Nav.Title"/>
					    	</c:if>
					    	<c:if test="${form.id!=null&&form.id!='0'&&form.id!=''}">
					    		<ingenta-tag:LanguageTag sessionKey="lang" key="Function.Edit.Update.Nav.Title"/>
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
					<form:form id="FunctionForm" commandName="form" class="form-horizontal">
                    <div class="on-down">
						<div id="codeDiv" class="control-group">
							<label class="control-label" for="form-field-1">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Function.Edit.Form.Code"/>：
							</label>
							<div class="controls">
                 				<form:input path="obj.code" id="code" placeholder="功能编码" onblur="WebGate.Function.validateCode();"/>
								<span id="codeSpan" class="help-inline"></span>
							</div>
						</div>
						<div id="nameDiv" class="control-group">
							<label class="control-label" for="form-field-1">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Function.Edit.Form.Name"/>：
							</label>
							<div class="controls">
                 				<form:input path="obj.name" id="name" placeholder="功能名称" onblur="WebGate.Function.validateName();"/>
								<span id="nameSpan" class="help-inline"></span>
							</div>
						</div>
						<!-- 
						<div id="pathDiv" class="control-group">
							<label class="control-label" for="form-field-2">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Function.Edit.Form.Path"/>：
							</label>
							<div class="controls">
                 				<form:input path="obj.path" id="path" placeholder="功能路径" onblur="WebGate.Function.validatePath();"/>
								<span id="pathSpan" class="help-inline"></span>
							</div>
						</div>
						-->
						<div id="pageDiv" class="control-group">
							<label class="control-label" for="form-field-2">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Function.Edit.Form.Page"/>：
							</label>
							<div class="controls">
								<form:select path="obj.page.id" id="page" onblur="WebGate.Function.validatePage();">
									<form:option value="">-<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/>-</form:option>
									<c:forEach items="${form.pageList}" var="c">
										<form:option value="${c.id}">${c.name}</form:option>
									</c:forEach>
								</form:select>
								<span id="pageSpan" class="help-inline"></span>
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
