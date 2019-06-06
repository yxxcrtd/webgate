<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/common/common.jsp"%>
<%@ include file="/pages/common/context.jsp"%>
<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8" />
<title><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Expired"/></title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="${ctx}/js/common.js"></script>
<body>
<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!-- ------------------导航页面部分开始----------------------------- -->
				<div class="page-header position-relative">
					<h1>
						${form.code}&nbsp;&nbsp;<ingenta-tag:LanguageTag sessionKey="lang" key="${form.msg}"/>
						<i class="icon-double-angle-right"></i>
						<small>
							<c:if test="${form.url!=null&&form.url!=''}">
								<a href="${ctx}${form.url}"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Return.Home"/></a>
							</c:if>
							<c:if test="${form.url==null||form.url==''}">
								<a href="javascript::history.go(-1);"><ingenta-tag:LanguageTag sessionKey="lang" key="Global.Label.Come.Back"/></a>
							</c:if>
						</small>
					</h1>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
			</div>
		</div>
	</div>

</body>
</html>
