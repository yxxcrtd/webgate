
/****** DataTable plugin API Start ************************************************************************************************************/
$.fn.dataTableExt.oApi.fnStandingRedraw = function(oSettings) {
    if(oSettings.oFeatures.bServerSide === false){
        var before = oSettings._iDisplayStart;
 
        oSettings.oApi._fnReDraw(oSettings);
 
        // iDisplayStart has been reset to zero - so lets change it back
        oSettings._iDisplayStart = before;
        oSettings.oApi._fnCalculateEnd(oSettings);
    }
      
    // draw the 'current' page
    oSettings.oApi._fnDraw(oSettings);
};
/****** DataTable plugin API End ************************************************************************************************************/

/****** Refresh DataTable Start ************************************************************************************************************/
function initPagingParamsInLayer(dataTable) {
	//alert('initPagingParamsInLayer : ' + dataTable);
	dataTable = "window.frames['iframe_main']." + dataTable;
	var oSettings = eval(dataTable).fnSettings();
	oSettings._iDisplayStart  = 0;
	oSettings._iDisplayLength  = 10;
}

function initPagingParamsInFrame(dataTable) {
	//alert('initPagingParamsInFrame : ' + dataTable);
	var oSettings = eval(dataTable).fnSettings();
	oSettings._iDisplayStart  = 0;
	oSettings._iDisplayLength  = 10;
}
/****** Refresh DataTable End ************************************************************************************************************/

/****** Refresh DataTable Start ************************************************************************************************************/
function refreshFrameDataTableInLayer(dataTable) {
	//alert('refreshDataTableInLayer : ' + dataTable);
	dataTable = "window.frames['iframe_main']." + dataTable;
	eval(dataTable).fnStandingRedraw();
}

function refreshFrameDataTableInFrame(dataTable) {
	//alert('refreshDataTableInFrame : ' + dataTable);
	eval(dataTable).fnStandingRedraw();
}
/****** Refresh DataTable End ************************************************************************************************************/
function updateFormInFrame(callBack, jsonObj){
	var context = "window.frames['iframe_main'].document";
	eval(callBack + "(jsonObj, eval(" + context + "))");
}
function updateFormInLayer(callBack, jsonObj){
//    alert("editorial common.js");
    var context = "window.document";
    eval(callBack + "(jsonObj, eval(" + context + "))");
}

/****** Refresh Tree Start ************************************************************************************************************/
//更新树节点信息
function updateTree(tree, nodeInfo){
	tree = "window.frames['iframe_main']." + tree;
	//alert(tree);
	var selectNode = eval(tree).getSelectedNodes()[0];
	if(nodeInfo.id ==undefined){
		eval(tree).getSelectedNodes()[0].name=nodeInfo.name;
		eval(tree).updateNode(eval(tree).getSelectedNodes()[0]);
	}else{
		if(selectNode.open == true || selectNode.isParent == false){
			var addNode=[{id:nodeInfo.id,name:nodeInfo.name,isParent:nodeInfo.isParent,sysId:nodeInfo.sysId}];
			eval(tree).addNodes(selectNode, addNode);
		}else{
			eval(tree).reAsyncChildNodes(selectNode, "refresh");
		}
		var node = eval(tree).getNodeByParam("id", nodeInfo.id, selectNode);
		eval(tree).selectNode(node,false);//添加完毕，新节点被选中
	}
}

//更新树节点信息
function updateTreeInFrame(tree, nodeInfo){
	var selectNode = eval(tree).getSelectedNodes()[0];
	if(nodeInfo.id ==undefined){
		eval(tree).getSelectedNodes()[0].name=nodeInfo.name;
		eval(tree).updateNode(eval(tree).getSelectedNodes()[0]);
	}else{
		if(selectNode.open == true || selectNode.isParent == false){
			var addNode=[{id:nodeInfo.id,name:nodeInfo.name,isParent:nodeInfo.isParent,sysId:nodeInfo.sysId}];
			eval(tree).addNodes(selectNode, addNode);
		}else{
			eval(tree).reAsyncChildNodes(selectNode, "refresh");
		}
		var node = eval(tree).getNodeByParam("id", nodeInfo.id, selectNode);
		eval(tree).selectNode(node,false);//添加完毕，新节点被选中
	}
}
/****** Refresh Tree End ************************************************************************************************************/

/****** Open CommonModal Start ************************************************************************************************************/
function openCommonModalInLayer(url, commonModalCss, commonModalBodyCss) {
	var commonModalWidth = 400;
	var commonModalHeight = 300;
	if (commonModalCss.width != null) {
		commonModalWidth = commonModalCss.width;
	}
	if (commonModalCss.height != null) {
		commonModalHeight = commonModalCss.height;
	}
	var margin={bottom:5,right:5};
	var commonDialog = new dialog({
		title: '',						// 标题
		//html: '',						// 加载html字符串
		ajax: url,						// 加载同域页面路径
		//iframe: '',					// iframe方式加载本域或跨域页面
		dragable: true,					// 是否可以拖动
		resizable: true,				// 是否可以更改弹出层窗体大小，双击标题栏最大化
		width: commonModalWidth,		// 弹出层宽度
		height: commonModalHeight,		// 弹出层高度
		modal: true,					// 是否有遮罩层
		outer: false,					// 是否可以将弹出层被拖到窗体可见区域外
		dragMargin: margin,				// 如果outer=false，设定弹窗与窗体四周的边距
		bootstrap: true,				// 是否用bootstrap样式
		maximize: commonModalCss.maximize
	}).show();
	//focusCore();
}

function openCommonModalInFrame(url, commonModalCss, commonModalBodyCss) {
	window.parent.openCommonModalInLayer(url, commonModalCss, commonModalBodyCss); // 全部遮罩
//	openCommonModalInLayer(url, commonModalCss, commonModalBodyCss); // 工作区  弹出多层
}

var time_over = null;
function autoCloseCommonModal(second) {
	ajaxAlertInfoMsg(second + "秒后自动关闭页面", false);
	second--;
    if(second == 0) {
    	if($("[id=commonDragModal]").children().size()>1) {
    		$("[id=commonDragModal]").children()[$("[id=commonDragModal]").children().size()-1].remove();
    	} else {
    		$("[id=commonDragModal]").empty();
    	}
    	$("[id=commonDragModalCover]:last").remove();
    } else {
    	time_over = setTimeout("autoCloseCommonModal(" + second + ")", 1000);
    }
}

function autoCloseCommonModalByFunction(second,callBack) {
	ajaxAlertInfoMsg(second + "秒后自动关闭页面", false);
	second--;
    if(second == 0) {
    	$("#commonDragModal").empty();
    	$("#commonDragModalCover").remove();
    	callBack();
    } else {  
        setTimeout("autoCloseCommonModalByFunction(" + second +","+callBack+ ")", 1000);
    }
}

function openOldCommonModalInLayer(url, commonModalCss, commonModalBodyCss) {
	//alert('openCommonModalInLayer : ' + url);
	showCommonModal(url, commonModalCss, commonModalBodyCss);
}

function openOldCommonModalInFrame(url, commonModalCss, commonModalBodyCss) {
	//alert("openCommonModalInFrame:" + JSON.stringify(commonModalCss));
	//alert("openCommonModalInFrame:" + JSON.stringify(commonModalBodyCss));
	//alert('openCommonModalInFrame : ' + url);
	window.parent.showCommonModal(url, commonModalCss, commonModalBodyCss);
}

function autoCloseOldCommonModal(second) {
	ajaxAlertInfoMsg(second + "秒后自动关闭页面", false);
	second--;
    if(second == 0) {
		window.parent.closeCommonModal();
    } else {  
        setTimeout("autoCloseOldCommonModal(" + second + ")", 1000);
    }
}

function autoCloseOldCommonModalByFunction(second,callBack) {
	ajaxAlertInfoMsg(second + "秒后自动关闭页面", false);
	second--;
    if(second == 0) {
		window.parent.closeCommonModalByFunction(callBack);
    } else {  
        setTimeout("autoCloseOldCommonModalByFunction(" + second +","+callBack+ ")", 1000);
    }
}

/****** Open CommonModal End ************************************************************************************************************/

/****** Open AlertModal Start ************************************************************************************************************/
function openSuccessAlertModalInFrame(msg, autoClose, autoCloseTime) {
	window.parent.openSuccessAlertModalInLayer(msg, autoClose, autoCloseTime);
}

function openErrorAlertModalInFrame(msg) {
	window.parent.openErrorAlertModalInLayer(msg);
}

function openConfirmModalInFrame(msg, callback, confirmModalCss, confirmModalBodyCss) {
	window.parent.openConfirmModalInLayer(msg, callback, confirmModalCss, confirmModalBodyCss);
}

function showCommonBarModalInFrame(msg) {
	window.parent.showCommonBarModalInLayer(msg);
}

function hideMaskDialogInFrame(milliSecond, callback) {
	window.parent.hideMaskDialogInLayer(milliSecond, callback);
}

function openSuccessAlertModalInLayer(msg, autoClose, autoCloseTime) {
	openMaskDialog({
		mode : "success",
		message : msg,
		autoClose : autoClose,
		autoCloseTime : autoCloseTime
	});
}

function openErrorAlertModalInLayer(msg) {
	openMaskDialog({
		mode : "error",
		message : msg
	});
}

function openConfirmModalInLayer(msg, callback, confirmModalCss, confirmModalBodyCss) {
	openMaskDialog({
		mode : "confirm",
		message : msg,
		confirmCallback : callback
	});
}

MASK_LOADING = "加载中，请稍候……";
MASK_PROCESS = "请求已提交，请稍候……";
MASK_TRY_AGAIN = "数据录入错误，请检查。";
MASK_ADD = "创建成功";
MASK_UPDATE = "修改成功";
MASK_REMOVE = "删除成功";
MASK_UPLOAD = "上传成功";
function showCommonBarModalInLayer(msg) {
	if (msg == null) {
		msg = MASK_LOADING;
	}
	openMaskDialog({
		mode : "loading",
		message : msg
	});
}

function hideMaskDialogInLayer(milliSecond, callback) {
	hideMaskDialog(milliSecond, callback);
}

/****** Open AlertModal End ************************************************************************************************************/

/*******************   全键盘 操作***********************************************************************************************/
function enterEventHandler(e) {
    var event = $.event.fix(e); //修正event事件
    var element = event.target; //jQuery统一修正为target
    var buttons = "button,reset,submit"; //button格式
    if (element.nodeName == "INPUT" || element.nodeName == "SELECT") {
        //event.preventDefault(); //取消浏览器默认行为
        //event.stopPropagation(); //取消冒泡
        var inputs = $("input[type!='hidden'][type!='checkbox'][type!='radio'],select,button"); //获取缓存的页面input集合
        var index = inputs.index(element); //当前input位置      
        if (buttons.indexOf(inputs[index + 1].type) >= 0) {
            inputs[index + 1].focus();
            inputs[index + 1].click();
        }
        else {
            inputs[index + 1].focus();
        }
 
    }
}

function stopDefaultEvent(e) {
	//如果提供了事件对象，则这是一个非IE浏览器   
	if(e && e.preventDefault) {
		//阻止默认浏览器动作(W3C)
		e.preventDefault();
	} else {
		//IE中阻止函数器默认动作的方式
		window.event.returnValue = false;
	}
	return false;
}

function enterCore(){
	$(document).keydown(function(e){ 
	    var key = e.which;
	    if (key == 13) {
	//        e.preventDefault();
	//        var nxtIdx = $inp.index(this) + 1;
	//        $(":input:text:eq(" + nxtIdx + ")").focus();
	    	stopDefaultEvent(e);
	    	enterEventHandler(e);
	    }

	});
}

function focusCore(){
	alert("bmep aaa");
	var inputs = $("input[type!='hidden'][type!='checkbox'][type!='radio'],select"); 
	inputs[0].focus();
	enterCore();
}

function showModuleInFrame(functionName,id)
{
	eval("window.frames['iframe_main']." + functionName);
}

function saveModuleInFrame(functionName)
{
	eval("window.frames['iframe_main']." + functionName);
}

function clearMessage() {
	$("div[id$='Div']").removeClass("error");
	$("div[id$='Div']").removeClass("success");
	$("span[id$='Span']").html("");
};

/**
 * 刷新自定义属性控件DataTable
 * @param renderTo: 自定义属性空间ID
 */
function refreshCustomPropertyDataTable(renderTo) {
	var invoke = "$.customProperty.refreshDataTable('"+ renderTo +"');";
	try {
		eval("window.frames['iframe_main']." + invoke);
	} catch (e) {
		eval(invoke);
	}
}

/**
 *  在弹出层中重新绑定Tree的OnClick事件
 *  @author Ben
 */
function rebindingTreeOnClickInLayer() {
    $("#menu-toggler").on("click", function () {
        $("[id=sidebar]:first").toggleClass("display");
        $(this).toggleClass("display");
        return false;
    });
    var a = false;
    $("[id=sidebar-collapse]:first").on("click", function () {
        $("[id=sidebar]:first").toggleClass("menu-min");
        $(this.firstChild).toggleClass("icon-double-angle-right");
        a = $("[id=sidebar]:first").hasClass("menu-min");
        if (a) {
            $(".open > .submenu").removeClass("open");
        }
    });
}

/**
 * 获取弹出层上树右键菜单坐标
 * @param event
 * @param treeId
 * @returns {___anonymous12113_12161}
 */
function getTreeRightMenuPosition(event, treeId) {
	var tree = document.getElementById(treeId);
	var warp = tree.offsetParent;
	var isFind = true;
	while(isFind) {
		if($(warp).hasClass("modal-body")) {
			isFind = false;
		} else {
			warp = warp.offsetParent;
		}
	}
	var st = warp.scrollTop;
    var t = warp.offsetTop;
    var l = warp.offsetLeft;
    while (warp = warp.offsetParent) {
        t += warp.offsetTop;
        l += warp.offsetLeft;
    }
	return {x: event.clientX - l, y: event.clientY - t + st};
}

/**
 * @param methodName 方法名
 * @param params 要调用方法的参数，数组类型
 */
function invokeFrameMethod(methodName, arrayParams) {
	var context = "window.frames['iframe_main'].";
	eval(context + methodName).apply(this, arrayParams);
}

/**
 * 调用变量
 */
function invokeFrameVariables(varName) {
	var context = "window.frames['iframe_main'].";
	return eval(context + varName);
}

/**
 * 文本框只能输入整数数字,
 * @param event
 * @param obj
 * 调用方式：onkeypress="onlyInputIntegerNumber(event, this)"
 */
function onlyInputIntegerNumber(event, obj) {
	if (!((event.which > 47 && event.which < 58) || (event.which == 8))) {
		event.preventDefault();
	}
}