WebGate.Function = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.Function.addObj = function() {
	var url = $('#ctx').val()+"/pages/function/form/edit";
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Function.modObj = function(id) {
	var url = $('#ctx').val()+"/pages/function/form/edit?eid="+id;
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Function.delObj = function(id) {
	openConfirmModalInFrame(Global_Prompt_Delete_Message, function() {
		var url = $('#ctx').val()+"/pages/function/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.Function.oTable1');
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

WebGate.Function.query = function() {
	initPagingParamsInFrame('WebGate.Function.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.Function.oTable1');
};

WebGate.Function.retrieveData = function(sSource, aoData, fnCallback) {
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