WebGate.AccountRole = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.AccountRole.saveRoleList = function() {
	//alert("saveRoleList");
	WebGate.AccountRole.disableAllButton();
	var sData = $('input', WebGate.AccountRole.oTable1.fnGetNodes()).serialize();
	//alert(JSON.stringify(sData));
	//alert( "The following data would have been submitted to the server: \n\n"+sData );
	var url = $('#ctx').val()+"/pages/account/form/batchSaveRole?id="+$('#accountId').val();
	$.ajax( {
        "dataType": 'json',
        "type": "POST",
        "url": url,
        "cache": false,
        "data": sData,
        "success": function(response) {
        	//alert(JSON.stringify(response));
        	//alert(response.isSuccess);
        	//alert(response.msg);
        	if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				WebGate.AccountRole.enableAllButton();
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.AccountRole.enableAllButton();
			}
        },
        "error": function(response) {
     	   alert("error");
        }
    } );
};

WebGate.AccountRole.query = function() {
	initPagingParamsInFrame('WebGate.AccountRole.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.AccountRole.oTable1');
};

WebGate.AccountRole.retrieveData = function(sSource, aoData, fnCallback) {
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

WebGate.AccountRole.disableAllButton = function() {
	$("#save").attr('disabled', "true");
};

WebGate.AccountRole.enableAllButton = function() {
	$("#save").removeAttr('disabled');
};