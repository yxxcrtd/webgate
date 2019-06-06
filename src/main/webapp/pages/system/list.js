WebGate.System = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.System.addObj = function() {
	var url = $('#ctx').val()+"/pages/system/form/edit";
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.System.modObj = function(id) {
	var url = $('#ctx').val()+"/pages/system/form/edit?eid="+id;
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.System.delObj = function(id) {
	openConfirmModalInFrame(Global_Prompt_Delete_Message, function() {
		var url = $('#ctx').val()+"/pages/system/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.System.oTable1');
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

WebGate.System.query = function() {
	initPagingParamsInFrame('WebGate.System.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.System.oTable1');
};

WebGate.System.retrieveData = function(sSource, aoData, fnCallback) {
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