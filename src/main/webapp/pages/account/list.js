WebGate.Account = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.Account.addObj = function() {
	var url = $('#ctx').val()+"/pages/account/form/edit";
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Account.modObj = function(id) {
	var url = $('#ctx').val()+"/pages/account/form/edit?eid="+id;
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Account.delObj = function(id) {
	openConfirmModalInFrame(Global_Prompt_Delete_Message, function() {
		var url = $('#ctx').val()+"/pages/account/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.Account.oTable1');
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

WebGate.Account.manageRoleList = function(id) {
	//alert("manageRoleList :" + id);
	var url = $('#ctx').val()+"/pages/account/form/roleIndex?accountId="+id;
	var commonModalCss = {
		"width": "1200px",
        "height": "700px"
	};
	var commonModalBodyCss = {
		"max-height": "750px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Account.query = function() {
	initPagingParamsInFrame('WebGate.Account.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.Account.oTable1');
};

WebGate.Account.retrieveData = function(sSource, aoData, fnCallback) {
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
WebGate.Account.resetPassword = function(id) {
	openConfirmModalInFrame(Global_Prompt_Reset_Message, function() {
		var url = $('#ctx').val()+"/pages/account/form/resetPassword?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.Account.oTable1');
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