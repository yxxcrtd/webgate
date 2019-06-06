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

<script src="${ctx}/js/jquery.cookie.js"></script>
<script src="${ctx}/js/popbaseball.js"></script>

<script type="text/javascript">




//保存布局
function saveLayer(){
	var left = [];
	var right = [];
	$("#left .baseball").each(function(){
		left.push($(this).attr("id"));
	})

	$("#right .baseball").each(function(){
		right.push($(this).attr("id"));
	})

	 $.cookie("left",left.toString(),{expires: 30});
	 $.cookie("right",right.toString(),{expires: 30});
	//alert(left.toString());
	//alert(right.toString());
}


//加载布局
function loadLayer(){
	if($.cookie("left")){
		var left = $.cookie("left").split(",");
		for(var i = 0 , j = left.length ; i < j ; i++){
			$("#left").append($("#" + left[i]));
			//document.getElementById("left").appendChild(document.getElementById(left[i]));
		}
	}

	if( $.cookie("right")){
		var right = $.cookie("right").split(",");
		for(var i = 0 , j = right.length ; i < j ; i++){
			$("#right").append($("#" + right[i]));
			//document.getElementById("right").appendChild(document.getElementById(right[i]));
		}
	}
}

function popWindow(url,width,height,formName){
	if(url.indexOf("?")!=-1){
		url = url+ "&r_=" + Math.random();
	}else {
		url = url+ "?r_" + Math.random();
	}
	var top=(parent.document.body.clientHeight-height)/2;
	var left=(parent.document.body.clientWidth-width)/2;
	openWindow(url,top,left,width,height,formName);
}

function openWindow(url,top,left,width,height,formName){
	var returnValue="";
	returnValue=window.showModalDialog(url,window,"dialogTop:"+top+"px;dialogLeft:"+left+"px;dialogWidth="+width+"px;dialogHeight="+height + "px");

	if(formName){
		document.getElementById(formName).submit();
	}
}

function cancelModule(moduleId,userId,moduleName){
	if(confirm("确定要删除【" + moduleName + "】这个模块么？")){
		//alert(moduleId);
		var sendData = "mid=" + moduleId + "&uid=" + userId + "&ajax=true";
		$.ajax({
		   type: "GET",
		   url: "module/removeModule",
		   cache: false,
		   data: sendData,
		   success: function(msg){
		     if(msg == "success"){
		     	//alert("模块删除成功!");
		     	$("#b_"+moduleId).remove();
				saveLayer();
		     }
		   }
		});
	}
}


$(document).ready(function(){
	//setTimeout(function(){
		loadLayer();
	//},500)
	
	$.baseball({
		accepter:"#left,#right",
		target:".baseball",	
		handle:".basebat"
	})
	
	//加载模块的关闭、打开状态
	$(".block").each(function(){
		var display = $.cookie($(this).parent().attr("id"));
		if(display){
			$("dd",this).css("display",display);
		};
	})
	
	
	//打开关闭模块
	$(".basebat span").click(function(){
		$("dd",$(this).parent().parent()).toggle();
		$.cookie($(this).parent().parent().parent().attr("id"),$("dd",$(this).parent().parent()).css("display"),{expires: 30});
	})
	
	
	
	
})
function cb(){
	document.getElementById("indexForm").submit();
}
function modiModule(){
	Thd.Win.openWin({
		title : "自定义首页模块",
		url : "module/makeMyModule",//地址
		callback : cb,//回调函数
		width:500,//宽
		height:400,//高
		overflow:"hidden",//益处隐藏
		hasBton : true//是否需要 确定 取消 按钮
	});
	
}


</script>


<body>
<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!-- ------------------导航页面部分开始----------------------------- -->
				<div class="page-header position-relative">
					<div style="float:left;width:48%;margin-right:10px;" id="left">
						<div class="widget-box baseball " id="1111">
							<div class="block widget-header basebat span12">
								<h5>Default Widget Box222</h5>
							</div>
							<div class="widget-body" >
								<table class="table table-striped table-bordered table-hover">
									<thead class="thin-border-bottom">
										<tr>
											<th>
												<i class="icon-user"></i>
												User
											</th>

											<th>
												<i>@</i>
												Email
											</th>
											<th class="hidden-480">Status</th>
										</tr>
									</thead>

									<tbody>
										<tr>
											<td class="">Alex</td>

											<td>
												<a href="#">alex@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-warning">Pending</span>
											</td>
										</tr>

										<tr>
											<td class="">Fred</td>

											<td>
												<a href="#">fred@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-success arrowed-in arrowed-in-right">Approved</span>
											</td>
										</tr>

										<tr>
											<td class="">Jack</td>

											<td>
												<a href="#">jack@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-warning">Pending</span>
											</td>
										</tr>

										<tr>
											<td class="">John</td>

											<td>
												<a href="#">john@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-inverse arrowed">Blocked</span>
											</td>
										</tr>

										<tr>
											<td class="">James</td>

											<td>
												<a href="#">james@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-info arrowed-in arrowed-in-right">Online</span>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						
						
						
						<div class="widget-box baseball " id="11112222">
							<div class="block widget-header basebat span12">
								<h5>Default Widget Box222</h5>
							</div>
							<div class="widget-body" >
								<table class="table table-striped table-bordered table-hover">
									<thead class="thin-border-bottom">
										<tr>
											<th>
												<i class="icon-user"></i>
												User
											</th>

											<th>
												<i>@</i>
												Email
											</th>
											<th class="hidden-480">Status</th>
										</tr>
									</thead>

									<tbody>
										<tr>
											<td class="">Alex</td>

											<td>
												<a href="#">alex@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-warning">Pending</span>
											</td>
										</tr>

										<tr>
											<td class="">Fred</td>

											<td>
												<a href="#">fred@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-success arrowed-in arrowed-in-right">Approved</span>
											</td>
										</tr>

										<tr>
											<td class="">Jack</td>

											<td>
												<a href="#">jack@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-warning">Pending</span>
											</td>
										</tr>

										<tr>
											<td class="">John</td>

											<td>
												<a href="#">john@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-inverse arrowed">Blocked</span>
											</td>
										</tr>

										<tr>
											<td class="">James</td>

											<td>
												<a href="#">james@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-info arrowed-in arrowed-in-right">Online</span>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
					<div style="float:left;width:48%;" id="right">
						<div class="widget-box baseball " id="22222">
							<div class="block widget-header basebat span12">
								<h5>Default Widget Box1111</h5>
							</div>
							<div class="widget-body" >
								<table class="table table-striped table-bordered table-hover">
										<thead class="thin-border-bottom">
											<tr>
												<th>
													<i class="icon-user"></i>
													User
												</th>

												<th>
													<i>@</i>
													Email
												</th>
												<th class="hidden-480">Status</th>
											</tr>
										</thead>

										<tbody>
											<tr>
												<td class="">Alex</td>

												<td>
													<a href="#">alex@email.com</a>
												</td>

												<td class="hidden-480">
													<span class="label label-warning">Pending</span>
												</td>
											</tr>

											<tr>
												<td class="">Fred</td>

												<td>
													<a href="#">fred@email.com</a>
												</td>

												<td class="hidden-480">
													<span class="label label-success arrowed-in arrowed-in-right">Approved</span>
												</td>
											</tr>

											<tr>
												<td class="">Jack</td>

												<td>
													<a href="#">jack@email.com</a>
												</td>

												<td class="hidden-480">
													<span class="label label-warning">Pending</span>
												</td>
											</tr>

											<tr>
												<td class="">John</td>

												<td>
													<a href="#">john@email.com</a>
												</td>

												<td class="hidden-480">
													<span class="label label-inverse arrowed">Blocked</span>
												</td>
											</tr>

											<tr>
												<td class="">James</td>

												<td>
													<a href="#">james@email.com</a>
												</td>

												<td class="hidden-480">
													<span class="label label-info arrowed-in arrowed-in-right">Online</span>
												</td>
											</tr>
										</tbody>
									</table>
							</div>
						</div>
						<div class="widget-box baseball " id="2222211111">
							<div class="block widget-header basebat span12">
								<h5>Default Widget Box1111</h5>
							</div>
							<div class="widget-body" >
								<table class="table table-striped table-bordered table-hover">
									<thead class="thin-border-bottom">
										<tr>
											<th>
												<i class="icon-user"></i>
												User
											</th>

											<th>
												<i>@</i>
												Email
											</th>
											<th class="hidden-480">Status</th>
										</tr>
									</thead>

									<tbody>
										<tr>
											<td class="">Alex</td>

											<td>
												<a href="#">alex@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-warning">Pending</span>
											</td>
										</tr>

										<tr>
											<td class="">Fred</td>

											<td>
												<a href="#">fred@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-success arrowed-in arrowed-in-right">Approved</span>
											</td>
										</tr>

										<tr>
											<td class="">Jack</td>

											<td>
												<a href="#">jack@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-warning">Pending</span>
											</td>
										</tr>

										<tr>
											<td class="">John</td>

											<td>
												<a href="#">john@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-inverse arrowed">Blocked</span>
											</td>
										</tr>

										<tr>
											<td class="">James</td>

											<td>
												<a href="#">james@email.com</a>
											</td>

											<td class="hidden-480">
												<span class="label label-info arrowed-in arrowed-in-right">Online</span>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
			</div>
		</div>
	</div>

</body>
</html>