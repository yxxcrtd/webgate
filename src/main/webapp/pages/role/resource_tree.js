
WebGate.RoleResource = function() {
	this.zTree = null;
};

WebGate.RoleResource.filter = function(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
};

WebGate.RoleResource.saveResourceList = function() {
	WebGate.RoleResource.disableAllButton();
	//alert("selectedTreeNodeList:" + JSON.stringify(WebGate.RoleResource.zTree.getCheckedNodes()));
	var selectedTreeNodeList = WebGate.RoleResource.zTree.getCheckedNodes();
	var data = {
    	id : $("#roleId").val(),
    	sela : []
    };
	for (var i = 0; i < selectedTreeNodeList.length; i++) {
		treeNode = selectedTreeNodeList[i];
		//alert("treeNode:" + JSON.stringify(treeNode));
		if (treeNode.level > 1) {
			data.sela.push(treeNode.id);
		}
	}
	//alert("data:" + JSON.stringify(data));
	var url = $('#ctx').val()+"/pages/role/form/batchSaveResource";
	$.ajax( {
		"traditional": true,
        "dataType": 'json',
        "type": "POST",
        "url": url,
        "cache": false,
        "data": data,
        "success": function(response) {
        	if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				WebGate.RoleResource.enableAllButton();
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.RoleResource.enableAllButton();
			}
        },
        "error": function(response) {
     	   alert("error");
        }
    } );
};

WebGate.RoleResource.disableAllButton = function() {
	$("#save").attr('disabled', "true");
};

WebGate.RoleResource.enableAllButton = function() {
	$("#save").removeAttr('disabled');
};