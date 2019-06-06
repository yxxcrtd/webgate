
WebGate.Resource = function() {
	this.zTree = null;
	this.rMenu = null;
	this.shadow = null;
};

WebGate.Resource.addObj = function() {
	var selectNode = WebGate.Resource.zTree.getSelectedNodes()[0];
	WebGate.Resource.hideRMenu();
	if (selectNode) {
		var fatherID;
		if(selectNode.level == 1){
			fatherID = 1;
		}else{
			fatherID = selectNode.id;
		}
		var url = $('#ctx').val() + "/pages/resource/form/edit?fatherId=" + fatherID + "&sysId=" + selectNode.sysId;
		var commonModalCss = {
			"width" : "900px",
            "height": "400px"
		};
		var commonModalBodyCss = {
			"max-height" : "800px"
		};
		openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
	} else {
		alert("请先选择节点!");
	}
};

WebGate.Resource.editObj = function() {
	WebGate.Resource.hideRMenu();
	if (WebGate.Resource.zTree.getSelectedNodes()[0]) {
		var selectNode = WebGate.Resource.zTree.getSelectedNodes()[0];
		var url = $('#ctx').val() + "/pages/resource/form/edit?eid=" + selectNode.id + "&sysId=" + selectNode.sysId;

		var commonModalCss = {
			"width" : "900px",
            "height": "400px"
		};
		var commonModalBodyCss = {
			"max-height" : "800px"
		};
		openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);

	} else {
		alert("请先选择节点!");
	}

};

WebGate.Resource.delObj = function(id) {
	WebGate.Resource.hideRMenu();
	openConfirmModalInFrame("确认删除？",function() {
		if (WebGate.Resource.zTree.getSelectedNodes()[0]) {
			var url = $('#ctx').val()+"/pages/resource/form/delete?id=" + WebGate.Resource.zTree.getSelectedNodes()[0].id;
			$.ajax({
				type : "get",
				url : url,
				beforeSend : function(XMLHttpRequest) {
					 //ShowLoading();
				},
				success : function(data, textStatus) {
					if (data.isSuccess == 'true') {
						WebGate.Resource.zTree.removeNode(WebGate.Resource.zTree.getSelectedNodes()[0]);
					}
					openSuccessAlertModalInFrame(data.msg);
				},
				complete : function(XMLHttpRequest, textStatus) {
					 //HideLoading();
				},
				error : function() {
					
				}
			});
		} else {
			alert("请先选择节点!");
		}
	},null,null);
};

WebGate.Resource.OnRightClick = function(event, treeId,treeNode) {
	WebGate.Resource.zTree.selectNode(treeNode);
	WebGate.Resource.showRMenu(treeNode, event.clientX, document.body.scrollTop + event.clientY);
};

WebGate.Resource.BeforeDrop = function(treeId, treeNodes, targetNode, moveType) {
	//alert(targetNode.name+'----'+treeNodes[0].name+'----'+treeNodes[0].id+moveType);
	//return false;
	if(targetNode.level==0){
		openErrorAlertModalInFrame('系统信息不能拖拽设置！');
		return false;//如果出现异常 就return false;js拖拽不会成功
	}
	var param,targetInfo,sourseInfo,targetType,targetId,sourceType,sourceId,targetSysId,sourceSysId;
	var targetN,prevN;
	if(moveType=='inner'){
		targetN=targetNode;
		type='last';
	}else if(moveType=='prev') {
		targetN=targetNode.getParentNode();
		if(targetNode.getPreNode()==null){
			type='first';
		}else{
			prevN = targetNode.getPreNode();
		}
	}else{
		targetN=targetNode.getParentNode();
		prevN = targetNode;
	}
	if(targetN.level>1){
		targetType = targetNode.type;
		targetSysId=targetNode.sysId;
		targetId=targetNode.id;
		targetInfo=targetNode.type+'|'+targetNode.name+targetNode.id+'|'+targetNode.sysId+';';
	}else {
		targetType = targetNode.type;
		targetSysId=targetNode.sysId;
		targetId=targetNode.id;
		targetInfo=targetNode.type+'|'+targetNode.name+':'+targetNode.id+';';
	}
	
	if(treeNodes[0].level>1){
		if(prevN == undefined){
			sourceType=treeNodes[0].type;
			sourceId=treeNodes[0].id;
			sourceSysId=treeNodes[0].sysId;
			sourseInfo=treeNodes[0].type+'|'+treeNodes[0].name+treeNodes[0].id+'|'+treeNodes[0].sysId+';';
		}else{
			sourceType=treeNodes[0].type;
			sourceId=treeNodes[0].id;
			sourceSysId=treeNodes[0].sysId;
			sourseInfo=treeNodes[0].type+'|'+treeNodes[0].name+treeNodes[0].id+'|'+treeNodes[0].sysId+';';
		}
	}else{
		sourceType=treeNodes[0].type;
		sourceId=treeNodes[0].id;
		sourceSysId=treeNodes[0].id;
		sourseInfo=treeNodes[0].type+'|'+treeNodes[0].id+';';
	}
	if(sourceType=='sys'&&targetType=='sys'){
		openErrorAlertModalInFrame('不能将该系统设置成其它系统的菜单！');
		return false;
	}
	if(sourceType=='menu'&&targetType=='sys'&&moveType!='inner'){
		openErrorAlertModalInFrame('不能将菜单信息设置成系统信息！');
		return false;
	}
	param=targetInfo+sourseInfo+type;
	
	var isSuccess = false;
	//alert(moveType+'|'+targetInfo);
	//alert(sourseInfo);
	var url = $('#ctx').val()+"/pages/resource/form/positionChange.json";
	$.ajax({
        "dataType": 'json',
		"type": 'POST',
		"url": url,
		"async" : false, //默认为 true-异步 false-同步	
		"data": {'targetType': targetType, 'targetId': targetId,'targetSysId':targetSysId, 'sourceType': sourceType,'sourceId':sourceId,'sourceSysId':sourceSysId,'moveType':moveType},
		"success": function(response) {
			if(response.sessionForm!=undefined){
				if(response.sessionForm.exist =='false'){
					openErrorAlertModalInFrame(response.sessionForm.msg);
					isSuccess = false;
				}
			}else{
				if(response.resourceForm.isSuccess == "true") {
					isSuccess = true;
				}else{
					openErrorAlertModalInFrame(response.resourceForm.msg);
					isSuccess = false;
				}
			}
		},
		"error": function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.responseText);
			alert(XMLHttpRequest.status);
			alert(textStatus);
			isSuccess = false;
		}
	});
		
	/**/
	
	return isSuccess;
		//alert(param);
	//$.post("${ctx}/pages/menu/menuPosChange", {data: param },function (data, textStatus){});
};

WebGate.Resource.filter = function(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
};

WebGate.Resource.showRShadow = function(width, height, x, y) {
	WebGate.Resource.shadow.css({
		"width" : width + "px",
		"height" : height + "px",
		"left" : x + 4 + "px",
		"top" : y + 4 + "px",
		"visibility" : "visible"
	});
};

//隐藏右键菜单
WebGate.Resource.hideRMenu = function() {
	if (WebGate.Resource.rMenu)
		WebGate.Resource.rMenu.css({
			"visibility" : "hidden"
		});
	if (WebGate.Resource.shadow)
		WebGate.Resource.shadow.css({
			"visibility" : "hidden"
		});
	$("body").unbind("mousedown", WebGate.Resource.onBodyMouseDown);
};

WebGate.Resource.onBodyMouseDown = function(event) {
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
		WebGate.Resource.rMenu.css({
			"visibility" : "hidden"
		});
		WebGate.Resource.shadow.css({
			"visibility" : "hidden"
		});
	}
};

WebGate.Resource.showRMenu = function(node, x, y) {
	$("#rMenu ul").show();
	$("#shadow").show();
	
	if (node.level==1) {
		//系统节点
		$("#add").show();
		$("#edit").hide();
		$("#delete").hide();
		$("#page").hide();
		$("#role").hide();
	} else if(node.level==0) {
		//根节点
		$("#add").hide();
		$("#edit").hide();
		$("#delete").hide();
		$("#page").hide();
		$("#role").hide();
	} else if(node.isParent) {
		//菜单非叶子节点
		$("#add").show();
		$("#edit").show();
		$("#delete").hide();
		$("#page").show();
		$("#role").show();
	} else{
		//菜点叶子节点
		$("#add").show();
		$("#edit").show();
		$("#delete").show();
		$("#page").show();
		$("#role").show();
	}

	if(node.level==0){
		if (WebGate.Resource.rMenu) WebGate.Resource.rMenu.css({"visibility": "hidden"});
		if (WebGate.Resource.shadow) WebGate.Resource.shadow.css({"visibility": "hidden"});
	}else{
		WebGate.Resource.showRShadow(WebGate.Resource.rMenu.width(), WebGate.Resource.rMenu.height(), x, y);
		WebGate.Resource.rMenu.css({
			"top" : y + "px",
			"left" : x + "px",
			"visibility" : "visible"
		});
	}
	$("body").bind("mousedown", WebGate.Resource.onBodyMouseDown);
};

WebGate.Resource.hideTree = function() {
	$('#treeDemo').toggleClass("hide");
};

WebGate.Resource.showPageList = function() {
	WebGate.Resource.hideRMenu();
	if (WebGate.Resource.zTree.getSelectedNodes()[0]) {
		var selectNode = WebGate.Resource.zTree.getSelectedNodes()[0];
		WebGate.Resource.showPageContent();
		WebGate.ResourcePage.initPageContentData(selectNode.id);
	} else {
		alert("请先选择节点!");
	}
};

WebGate.Resource.showRoleList = function() {
	WebGate.Resource.hideRMenu();
	if (WebGate.Resource.zTree.getSelectedNodes()[0]) {
		var selectNode = WebGate.Resource.zTree.getSelectedNodes()[0];
		WebGate.Resource.showRoleContent();
		WebGate.ResourceRole.initRoleContentData(selectNode.id);
	} else {
		alert("请先选择节点!");
	}
};

WebGate.Resource.showPageContent = function() {
	$('#roleContent').addClass("hide");
	$('#pageContent').removeClass("hide");
};

WebGate.Resource.showRoleContent = function() {
	$('#pageContent').addClass("hide");
	$('#roleContent').removeClass("hide");
};