
WebGate.Resource.validate = function() {
	var flag = true;
	/*if (!WebGate.Resource.validateCode()) {
		flag = false;
	}*/
	if (!WebGate.Resource.validateName()) {
		flag = false;
	}
	if (!WebGate.Resource.validateLink()) {
		flag = false;
	}
	if (!WebGate.Resource.validateType()) {
		flag = false;
	}
	if (!WebGate.Resource.validateStatus()) {
		flag = false;
	}
	if (!WebGate.Resource.validateFull()) {
		flag = false;
	}
	/*if (!WebGate.Resource.validateOrder()) {
		flag = false;
	}
	if (!WebGate.Resource.validateSystem()) {
		flag = false;
	}*/
	if (!WebGate.Resource.validateComponent()) {
		flag = false;
	}
	return flag;
};

/******************************************* 验证Code开始 **************************************************************/
/*WebGate.Resource.validateCode = function() {
	if ($("#code").val() == "") {
		$("#codeDiv").addClass("error");
		$("#codeSpan").html(Resource_Info_ValidateCode_Blank);
		return false;
	}
	$("#codeDiv").removeClass("error");
	$("#codeSpan").html("");
	return true;
};*/
/******************************************* 验证Code结束 **************************************************************/
/******************************************* 验证Name开始 **************************************************************/
WebGate.Resource.validateName = function() {
	if ($("#name").val().trim() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html(Resource_Info_ValidateName_Blank);
		return false;
	} 
	$("#nameDiv").removeClass("error");
	$("#nameSpan").html("");
	return true;
};
/******************************************* 验证Name结束 **************************************************************/
/******************************************* 验证Link开始 **************************************************************/
WebGate.Resource.validateLink = function() {
	//链接可以为空
	/**
	if ($("#link").val() == "") {
		$("#linkDiv").addClass("error");
		$("#linkSpan").html(Resource_Info_ValidateLink_Blank);
		return false;
	}
	$("#linkDiv").removeClass("error");
	$("#linkSpan").html("");
	**/
	return true;
};
/******************************************* 验证Link结束 **************************************************************/
/******************************************* 验证Type开始 **************************************************************/
WebGate.Resource.validateType = function() {
	if ($("#type").val() == "") {
		$("#typeDiv").addClass("error");
		$("#typeSpan").html(Resource_Info_ValidateType_Blank);
		return false;
	}
	$("#typeDiv").removeClass("error");
	$("#typeSpan").html("");
	return true;
};
/******************************************* 验证Type结束 **************************************************************/
/******************************************* 验证Status开始 **************************************************************/
WebGate.Resource.validateStatus = function() {
	if ($("#status").val() == "") {
		$("#statusDiv").addClass("error");
		$("#statusSpan").html(Resource_Info_ValidateStatus_Blank);
		return false;
	}
	$("#statusDiv").removeClass("error");
	$("#statusSpan").html("");
	return true;
};
/******************************************* 验证Status结束 **************************************************************/
/******************************************* 验证Full开始 **************************************************************/
WebGate.Resource.validateFull = function() {
	if ($("#full").val() == "") {
		$("#fullDiv").addClass("error");
		$("#fullSpan").html(Resource_Info_ValidateFull_Blank);
		return false;
	}
	$("#fullDiv").removeClass("error");
	$("#fullSpan").html("");
	return true;
};
/******************************************* 验证Full结束 **************************************************************/
/******************************************* 验证Order开始 **************************************************************/
/*WebGate.Resource.validateOrder = function() {
	if ($("#order").val() == "") {
		$("#orderDiv").addClass("error");
		$("#orderSpan").html(Resource_Info_ValidateOrder_Blank);
		return false;
	}
	$("#orderDiv").removeClass("error");
	$("#orderSpan").html("");
	return true;
};*/
/******************************************* 验证Order结束 **************************************************************/
/******************************************* 验证Component开始 **************************************************************/
WebGate.Resource.validateComponent = function() {
	if ($("#component").val() == "") {
		$("#componentDiv").addClass("error");
		$("#componentSpan").html(Resource_Info_ValidateComponent_Blank);
		return false;
	}
	$("#componentDiv").removeClass("error");
	$("#componentSpan").html("");
	return true;
};
/******************************************* 验证Component开始 **************************************************************/
/******************************************* 验证System开始 **************************************************************/
/*WebGate.Resource.validateSystem = function() {
	if ($("#sysId").val() == "") {
		$("#sysIdDiv").addClass("error");
		$("#sysIdSpan").html(Resource_Info_ValidateSystem_Blank);
		return false;
	}
	$("#sysIdDiv").removeClass("error");
	$("#sysIdSpan").html("");
	return true;
};*/
/******************************************* 验证System结束 **************************************************************/

WebGate.Resource.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.Resource.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};


WebGate.Resource.save = function() {
	if (!WebGate.Resource.validate()) {
		return;
	}
	WebGate.Resource.disableAllButton();
	var url=$('#ctx').val() + "/pages/resource/form/editSubmit";
	$.ajax( {
		"dataType": 'json',
		"type": "POST",
		"url": url,
		"cache": false,
		"data": {
			"id": $('#id').val(),
			"obj.id": $('#id').val(),
			//"obj.code": $('#code').val(),
			"obj.name": $('#name').val(),
			"obj.link": $('#link').val(),
			"obj.type": parseInt($('#type').val()),
			"obj.status": $('#status').val(),
			"obj.full": $('#full').val(),
			//"obj.order":parseInt($('#order').val()),
			"obj.system.id": $('#sysId').val(),
			"obj.component.id": $('#component').val(),
			"obj.parentResource.id": $('#parentId').val()
		},
		"success": function(response) {
			if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				updateTree("WebGate.Resource.zTree", response.node);
				autoCloseCommonModal(5);
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.Resource.enableAllButton();
			}
		},
		"error": function(response) {
			alert("error");
		}
	} );
};

WebGate.Resource.showResponse = function(response, statusText) {
    WebGate.Resource.disableAllButton();
    if (response.isSuccess == "true") {
        ajaxAlertSuccessMsg(response.msg, true);
        updateTree("WebGate.Resource.zTree", response.node);
        autoCloseCommonModal(5);
    } else {
        ajaxAlertErrorMsg(response.msg, true);
        WebGate.Resource.enableAllButton();
    }
};

$(function() {
    var options = {
        beforeSubmit : WebGate.Resource.validate,
        success : WebGate.Resource.showResponse,
        url : $('#ctx').val() + "/pages/resource/form/editSubmit",
        type : 'post',
        clearForm : false,
        timeout : 3000
    };
    $('#ResourceForm').ajaxForm(options);
});