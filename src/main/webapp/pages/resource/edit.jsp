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
<script src="${ctx}/pages/resource/tree.js"></script>
<script src="${ctx}/pages/resource/edit.js"></script>
<script type="text/javascript">
	/**** 页面Validate国际化 ****/
	var Resource_Info_ValidateCode_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateCode.Blank'/>";
	var Resource_Info_ValidateName_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateName.Blank'/>";
	var Resource_Info_ValidateLink_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateLink.Blank'/>";
	var Resource_Info_ValidateType_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateType.Blank'/>";
	var Resource_Info_ValidateStatus_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateStatus.Blank'/>";
	var Resource_Info_ValidateOrder_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateOrder.Blank'/>";
	var Resource_Info_ValidateSystem_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateSystem.Blank'/>";
	var Resource_Info_ValidateComponent_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateComponent.Blank'/>";
	var Resource_Info_ValidateFull_Blank = "<ingenta-tag:LanguageTag sessionKey='lang' key='Resource.Info.ValidateFull.Blank'/>";
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
						<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Nav.Title"/>
						<small>
							<i class="icon-double-angle-right"></i>
							<c:if test="${form.id==null||form.id=='0'||form.id==''}">
					    		<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.New.Nav.Title"/>
					    	</c:if>
					    	<c:if test="${form.id!=null&&form.id!='0'&&form.id!=''}">
					    		<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Update.Nav.Title"/>
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
					<form:form id="ResourceForm" commandName="form" class="form-horizontal">
                    <div class="on-down">
						<!-- 
						<div id="codeDiv" class="control-group">
							<label class="control-label" for="form-field-1">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.Code"/>：
							</label>
							<div class="controls">
                 				<form:input path="obj.code" id="code" placeholder="资源编号" onblur="WebGate.Resource.validateCode();"/>
								<span id="codeSpan" class="help-inline"></span>
							</div>
						</div>
						 -->
						<div class="row-fluid">
							<div id="nameDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.Name"/>：
								</label>
								<div class="controls">
	                 				<form:input path="obj.name" id="name" placeholder="资源名称" onblur="WebGate.Resource.validateName();"/>
									<span id="nameSpan" class="help-inline"></span>
								</div>
							</div>
							<div id="linkDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.Link"/>：
								</label>
								<div class="controls">
	                 				<form:input path="obj.link" id="link" placeholder="资源链接" onblur="WebGate.Resource.validateLink();"/>
									<span id="linkSpan" class="help-inline"></span>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div id="typeDiv" class="control-group span6">
								<label class="control-label" for="form-field-1">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.Type"/>：
								</label>
								<div class="controls">
									<form:select path="obj.type" id="type" onblur="WebGate.Resource.validateType();">
										<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
										<form:options items="${form.typeMap}" />
									</form:select>
									<span id="typeSpan" class="help-inline"></span>
								</div>
							</div>
							<div id="statusDiv" class="control-group span6">
								<label class="control-label" for="form-field-2">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.Status"/>：
								</label>
								<div class="controls">
									<form:select path="obj.status" id="status" onblur="WebGate.Resource.validateStatus();">
										<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
										<form:options items="${form.statusMap}" />
									</form:select>
									<span id="statusSpan" class="help-inline"></span>
								</div>
							</div>
						</div>
						<%-- 
						<div id="orderDiv" class="control-group">
							<label class="control-label" for="form-field-1">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.Order"/>：
							</label>
							<div class="controls">
                 				<form:input path="obj.order" id="order" placeholder="资源次序" onblur="WebGate.Resource.validateOrder();"/>
								<span id="orderSpan" class="help-inline"></span>
							</div>
						</div>
						 --%>
						<%-- 
						<div id="sysIdDiv" class="control-group">
							<label class="control-label" for="form-field-2">
								<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.System"/>：
							</label>
							<div class="controls">
								<form:select path="obj.system.id" id="sysId" onblur="WebGate.Resource.validateSystem();">
									<form:option value="">-<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/>-</form:option>
									<c:forEach items="${form.systemList}" var="Resource">
										<form:option value="${Resource.id}">${Resource.name}</form:option>
									</c:forEach>
								</form:select>
								<span id="sysIdSpan" class="help-inline"></span>
							</div>
						</div>
						 --%>
						<div class="row-fluid">
							<div id="componentDiv" class="control-group span6">
								<label class="control-label" for="form-field-2">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.Component"/>：
								</label>
								<div class="controls">
									<form:select path="obj.component.id" id="component" onblur="WebGate.Resource.validateComponent();">
										<form:option value="">-<ingenta-tag:LanguageTag sessionKey="lang" key="Global.Prompt.Select"/>-</form:option>
										<c:forEach items="${form.componentList}" var="component">
											<form:option value="${component.id}">${component.name}</form:option>
										</c:forEach>
									</form:select>
									<span id="componentSpan" class="help-inline"></span>
								</div>
							</div>
							<div id="fullDiv" class="control-group span6">
								<label class="control-label" for="form-field-2">
									<ingenta-tag:LanguageTag sessionKey="lang" key="Resource.Edit.Form.Full"/>：
								</label>
								<div class="controls">
									<form:select path="obj.full" id="full" onblur="WebGate.Resource.validateFull();">
										<form:option value="">-<ingenta-tag:LanguageTag	sessionKey="lang" key="Global.Label.Select" />-</form:option>
										<form:options items="${form.fullMap}" />
									</form:select>
									<span id="fullSpan" class="help-inline"></span>
								</div>
							</div>
						</div>
					<form:hidden path="id"/>
					<form:hidden id="parentId" path="obj.parentResource.id"/>
					<form:hidden id="sysId" path="obj.system.id"/>
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