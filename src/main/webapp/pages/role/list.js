WebGate.Role = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.Role.addObj = function() {
	var url = $('#ctx').val()+"/pages/role/form/edit";
	var commonModalCss = {
		"width": "900px",
        "height": "700px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Role.modObj = function(id) {
	var url = $('#ctx').val()+"/pages/role/form/edit?eid="+id;
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Role.delObj = function(id) {
	openConfirmModalInFrame(Global_Prompt_Delete_Message, function() {
		var url = $('#ctx').val()+"/pages/role/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.Role.oTable1');
				} else {
					openErrorAlertModalInFrame(response.msg);
				}
			},
			"error": function(response) {
				alert("error");
			}
		} );
	}, null, null);
};

WebGate.Role.query = function() {
	initPagingParamsInFrame('WebGate.Role.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.Role.oTable1');
};

WebGate.Role.retrieveData = function(sSource, aoData, fnCallback) {
       $.ajax( {
           "dataType": 'json',
           "type": "POST",
           "url": sSource,
           "cache": false,
           "data": aoData,
           "success": function(response) {
        	   fnCallback(response);
           },
           "error": function(response) {
        	   alert("error");
           }
       } );
};
WebGate.Role.manageFunctionList = function(id) {
	//alert("manageFunctionList :" + id);
	var url = $('#ctx').val()+"/pages/role/form/functionIndex?roleId="+id;
	var commonModalCss = {
		"width": "1200px",
        "height": "700px"
	};
	var commonModalBodyCss = {
		"max-height": "750px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Role.showResourceTree = function(id) {
	var url = $('#ctx').val()+"/pages/role/form/resourceTreeIndex?roleId="+id;
	var commonModalCss = {
		"width": "1200px",
        "height": "700px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};