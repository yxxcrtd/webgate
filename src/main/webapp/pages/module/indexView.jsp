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
<script src="${ctx}/pages/module/list.js"></script>
<script src="${ctx}/pages/module/indexView.js"></script>
<script type="text/javascript">




//保存布局
function saveLayer(){
	var left = [];
	var leftStatus = [];
	var right = [];
	var rightStatus = [];
	$("#left .baseball").each(function(){
		left.push($(this).attr("id"));
		leftStatus.push($(this).attr("style"));
	});

	$("#right .baseball").each(function(){
		right.push($(this).attr("id"));
		rightStatus.push($(this).attr("style"));
	});

	 $.cookie("left",left.toString(),{expires: 30});
	 $.cookie("right",right.toString(),{expires: 30});
	 $.cookie("leftStatus",leftStatus.toString(),{expires: 30});
	 $.cookie("rightStatus",rightStatus.toString(),{expires: 30});
	//alert(left.toString());
	//alert(right.toString());
}


//加载布局
function loadLayer(){
	if($.cookie("left")){
		var left = $.cookie("left").split(",");
		var leftStatus = $.cookie("leftStatus").split(",");
		for(var i = 0 , j = left.length ; i < j ; i++){
			$("#left").append($("#" + left[i]));
			$("#"+left[i]).attr("style",leftStatus[i]);
			//document.getElementById("left").appendChild(document.getElementById(left[i]));
		}
	}

	if( $.cookie("right")){
		var right = $.cookie("right").split(",");
		var rightStatus = $.cookie("rightStatus").split(",");
		for(var i = 0 , j = right.length ; i < j ; i++){
			$("#right").append($("#" + right[i]));
			$("#"+right[i]).attr("style",rightStatus[i]);
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
	});
	
	//加载模块的关闭、打开状态
	$(".block").each(function(){
		var display = $.cookie($(this).parent().attr("id"));
		if(display){
			$("dd",this).css("display",display);
		};
	});
	
	
	//打开关闭模块
	$(".basebat span").click(function(){
		$("dd",$(this).parent().parent()).toggle();
		$.cookie($(this).parent().parent().parent().attr("id"),$("dd",$(this).parent().parent()).css("display"),{expires: 30});
	});
	
	
	
	
});

	


</script>


<body>
<input type="hidden" id="tik" value="${sessionScope.ticket}">
<div class="clearfix">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!-- ------------------导航页面部分开始----------------------------- -->
				<button class="btn btn-primary" onclick="WebGate.Module.modiModule();">
						<i class="icon-edit bigger-125"></i>
						自定义首页模块
				</button>
                <div style="padding:10px;padding-top:5px;overflow:hidden;">
                    <div style="float:left;width:48%;margin-right:10px;" id="left">
                    </div>
                    <div style="float:left;width:48%;" id="right">
                    </div>
                </div>



                <div class="page-header position-relative">
					<div style="float:left;width:48%;margin-right:10px;" >
					</div>
					<div style="float:left;width:48%;" >
					</div>
				</div>
				<!-- ------------------导航页面部分结束----------------------------- -->
			</div>
		</div>
	</div>

</body>
</html>