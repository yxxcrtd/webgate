/******************************************* 验证开始 **************************************************************/
var codeValidateFlag = false;
var nameValidateFlag = false;
var addressValidateFlag = false;
var portValidateFlag = false;

WebGate.Component.validate = function() {
	var flag = true;
	if (!WebGate.Component.validateName()) {
		flag = false;
	}
	if (!WebGate.Component.validateCode()) {
		flag = false;
	}
	if (!WebGate.Component.validateAddress()){
		flag = false;
	}
	if (!WebGate.Component.validatePort()){
		flag = false;
	}
	if (!WebGate.Component.validateStatus()) {
		flag = false;
	}
	if (!WebGate.Component.validateSystem()){
		flag = false;
	}

	return flag;
};
/******************************************* 验证Code开始 **************************************************************/
WebGate.Component.validateCode = function() {
	if ($("#code").val().trim() == "") {
		$("#codeDiv").addClass("error");
		$("#codeSpan").html(Component_Info_ValidateCode_Blank);
		return false;
	} else {
		WebGate.Component.validateCodeUnique();
		if (codeValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.Component.validateCodeUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/component/form/validateCodeUnique",
		data : {
			code : $("#code").val(),
			id: $('#id').val(),
			pageId: $('#system').val()
		},
		success : function(response) {
			if (response.isSuccess == "true") {
				$("#codeDiv").removeClass("error").addClass("success");
				$("#codeSpan").html(response.msg);
				codeValidateFlag = true;
			} else {
				$("#codeDiv").removeClass("success").addClass("error");
				$("#codeSpan").html(response.msg);
				codeValidateFlag = false;
			}
		},
		error : function(response) {
			alert("error");
		}
	});
};
/******************************************* 验证Code结束 **************************************************************/
/******************************************* 验证Name开始 **************************************************************/
WebGate.Component.validateName = function() {
	if ($("#name").val().trim() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html(Component_Info_ValidateName_Blank);
		return false;
	} else {
		//名称暂时不需要唯一性验证
		/**
		WebGate.Component.validateNameUnique();
		if (nameValidateFlag == true) {
			return true;
		} else {
			return false;
		}
		**/
		$("#nameDiv").removeClass("error");
		$("#nameSpan").html("");
		return true;
	}
};

WebGate.Component.validateNameUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/component/form/validateNameUnique",
		data : {
			name : $("#name").val(),
			id: $('#id').val(),
			pageId: $('#system').val()
		},
		success : function(response) {
			if (response.isSuccess == "true") {
				$("#nameDiv").removeClass("error").addClass("success");
				$("#nameSpan").html(response.msg);
				nameValidateFlag = true;
			} else {
				$("#nameDiv").removeClass("success").addClass("error");
				$("#nameSpan").html(response.msg);
				nameValidateFlag = false;
			}
		},
		error : function(response) {
			alert("error");
		}
	});
};
/******************************************* 验证Name结束 **************************************************************/
/******************************************* 验证Port开始 **************************************************************/
/*WebGate.Component.validatePort = function() {
	if ($("#port").val() == "") {
		$("#portDiv").addClass("error");
		$("#portSpan").html(Component_Info_ValidatePort_Blank);
		return false;
	} else {
		WebGate.Component.validatePortUnique();
		if (portValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.Component.validatePortUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/component/form/validatePortUnique",
		data : {
			port : $("#port").val()
		},
		success : function(response) {
			if (response.isSuccess == "true") {
				$("#portDiv").removeClass("error").addClass("success");
				$("#portSpan").html(response.msg);
				portValidateFlag = true;
			} else {
				$("#portDiv").removeClass("success").addClass("error");
				$("#portSpan").html(response.msg);
				portValidateFlag = false;
			}
		},
		error : function(response) {
			alert("error");
		}
	});
};*/

WebGate.Component.validatePort = function() {
	if ($("#port").val().trim() == "") {
		$("#portDiv").addClass("error");
		$("#portSpan").html(Component_Info_ValidatePort_Blank);
		return false;
	}
	$("#portDiv").removeClass("error");
	$("#portSpan").html("");
	return true;
};
/******************************************* 验证Port结束 **************************************************************/
/******************************************* 验证Address开始 **************************************************************/
/*WebGate.Component.validateAddress = function() {
	if ($("#address").val() == "") {
		$("#addressDiv").addClass("error");
		$("#addressSpan").html(Component_Info_ValidateAddress_Blank);
		return false;
	} else {
		WebGate.Component.validateAddressUnique();
		if (addressValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.Component.validateAddressUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/component/form/validateAddressUnique",
		data : {
			address : $("#address").val()
		},
		success : function(response) {
			if (response.isSuccess == "true") {
				$("#addressDiv").removeClass("error").addClass("success");
				$("#addressSpan").html(response.msg);
				addressValidateFlag = true;
			} else {
				$("#addressDiv").removeClass("success").addClass("error");
				$("#addressSpan").html(response.msg);
				addressValidateFlag = false;
			}
		},
		error : function(response) {
			alert("error");
		}
	});
};*/

WebGate.Component.validateAddress = function() {
	if ($("#address").val().trim() == "") {
		$("#addressDiv").addClass("error");
		$("#addressSpan").html(Component_Info_ValidateAddress_Blank);
		return false;
	}
	$("#addressDiv").removeClass("error");
	$("#addressSpan").html("");
	return true;
};
/******************************************* 验证Port结束 **************************************************************/
/******************************************* 验证Status开始 **************************************************************/
WebGate.Component.validateStatus = function() {
	if ($("#status").val() == "") {
		$("#statusDiv").addClass("error");
		$("#statusSpan").html(Component_Info_ValidateStatus_Blank);
		return false;
	}
	$("#statusDiv").removeClass("error");
	$("#statusSpan").html("");
	return true;
};
/******************************************* 验证Status结束 **************************************************************/
/******************************************* 验证System开始 **************************************************************/
WebGate.Component.validateSystem = function() {
	if ($("#system").val() == "") {
		$("#systemDiv").addClass("error");
		$("#systemSpan").html(Component_Info_ValidateSystem_Blank);
		return false;
	}
	$("#systemDiv").removeClass("error");
	$("#systemSpan").html("");
	return true;
};
/******************************************* 验证System结束 **************************************************************/
/******************************************* 验证结束 **************************************************************/

WebGate.Component.save = function() {
	if (!WebGate.Component.validate()) {
		return;
	}
	WebGate.Component.disableAllButton();
	var url=$('#ctx').val() + "/pages/component/form/editSubmit";
	$.ajax( {
		"dataType": 'json',
		"type": "POST",
		"url": url,
		"cache": false,
		"data": {
			"id": $('#id').val(),
			"obj.id": $('#id').val(),
			"obj.name": $('#name').val(),
			"obj.code": $('#code').val(),
			"obj.address": $('#address').val(),
			"obj.port": $('#port').val(),
			"obj.status": $('#status').val(),
			"obj.system.id": $('#system').val(),
		},
		"success": function(response) {
			if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				refreshFrameDataTableInLayer('WebGate.Component.oTable1');
				autoCloseCommonModal(5);
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.Component.enableAllButton();
			}
		},
		"error": function(response) {
			alert("error");
		}
	} );
};

WebGate.Component.showResponse = function(response, statusText) {
    WebGate.Component.disableAllButton();
    if (response.isSuccess == "true") {
        ajaxAlertSuccessMsg(response.msg, true);
        refreshFrameDataTableInLayer('WebGate.Component.oTable1');
        autoCloseCommonModal(5);
    } else {
        ajaxAlertErrorMsg(response.msg, true);
        WebGate.Component.enableAllButton();
    }
};

$(function() {
    var options = {
        beforeSubmit : WebGate.Component.validate,
        success : WebGate.Component.showResponse,
        url : $('#ctx').val() + "/pages/component/form/editSubmit",
        type : 'post',
        clearForm : false,
        timeout : 3000
    };
    $('#ComponentForm').ajaxForm(options);
});

WebGate.Component.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.Component.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};