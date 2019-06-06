WebGate.RoleFunction = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.RoleFunction.saveFunctionList = function() {
	//alert("saveRoleList");
	WebGate.RoleFunction.disableAllButton();
	var sData = $('input', WebGate.RoleFunction.oTable1.fnGetNodes()).serialize();
	//alert(JSON.stringify(sData));
	//alert( "The following data would have been submitted to the server: \n\n"+sData );
	var url = $('#ctx').val()+"/pages/role/form/batchSaveFunction?id="+$('#roleId').val();
	$.ajax( {
        "dataType": 'json',
        "type": "POST",
        "url": url,
        "cache": false,
        "data": sData,
        "success": function(response) {
        	if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				WebGate.RoleFunction.enableAllButton();
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.RoleFunction.enableAllButton();
			}
        },
        "error": function(response) {
     	   alert("error");
        }
    } );
};

WebGate.RoleFunction.query = function() {
	initPagingParamsInFrame('WebGate.RoleFunction.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.RoleFunction.oTable1');
};

WebGate.RoleFunction.retrieveData = function(sSource, aoData, fnCallback) {
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

WebGate.RoleFunction.disableAllButton = function() {
	$("#save").attr('disabled', "true");
};

WebGate.RoleFunction.enableAllButton = function() {
	$("#save").removeAttr('disabled');
};