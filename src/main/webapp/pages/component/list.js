
WebGate.Component = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.Component.addObj = function() {
	var url = $('#ctx').val()+"/pages/component/form/edit";
	var commonModalCss = {
		"width": "1000px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Component.modObj = function(id) {
	var url = $('#ctx').val()+"/pages/component/form/edit?eid="+id;
	var commonModalCss = {
		"width": "1000px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Component.delObj = function(id) {
	openConfirmModalInFrame(Global_Prompt_Delete_Message, function() {
		var url = $('#ctx').val()+"/pages/component/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.Component.oTable1');
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

WebGate.Component.query = function() {
	initPagingParamsInFrame('WebGate.Component.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.Component.oTable1');
};

WebGate.Component.reset = function() {
	$("#form")[0].reset();
};

WebGate.Component.retrieveData = function(sSource, aoData, fnCallback) {
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
