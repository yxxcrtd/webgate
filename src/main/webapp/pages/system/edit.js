/******************************************* 验证开始 **************************************************************/
var codeValidateFlag = false;
var nameValidateFlag = false;

WebGate.System.validate = function() {
	var flag = true;
	if (!WebGate.System.validateCode()) {
		flag = false;
	}
	if (!WebGate.System.validateName()) {
		flag = false;
	}
	if (!WebGate.System.validateStatus()) {
		flag = false;
	}
	return flag;
};
/******************************************* 验证Code开始 **************************************************************/
WebGate.System.validateCode = function() {
	if ($("#code").val() == "") {
		$("#codeDiv").addClass("error");
		$("#codeSpan").html(System_Info_ValidateCode_Blank);
		return false;
	} else {
		WebGate.System.validateCodeUnique();
		if (codeValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.System.validateCodeUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',	
		url : $('#ctx').val() + "/pages/system/form/validateCodeUnique",
		data : {
			code : $("#code").val()
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
WebGate.System.validateName = function() {
	if ($("#name").val() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html(System_Info_ValidateName_Blank);
		return false;
	} else {
		WebGate.System.validateNameUnique();
		if (nameValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.System.validateNameUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/system/form/validateNameUnique",
		data : {
			name : $("#name").val()
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
/******************************************* 验证Status开始 **************************************************************/
WebGate.System.validateStatus = function() {
	if ($("#status").val() == "") {
		$("#statusDiv").addClass("error");
		$("#statusSpan").html(System_Info_ValidateStatus_Blank);
		return false;
	}
	$("#statusDiv").removeClass("error");
	$("#statusSpan").html("");
	return true;
};
/******************************************* 验证Status结束 **************************************************************/
/******************************************* 验证结束 **************************************************************/

WebGate.System.save = function() {
	if (!WebGate.System.validate()) {
		return;
	}
	WebGate.System.disableAllButton();
	var url=$('#ctx').val() + "/pages/system/form/editSubmit";
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
			"obj.status": $('#status').val()
		},
		"success": function(response) {
			if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				refreshFrameDataTableInLayer('WebGate.System.oTable1');
				autoCloseCommonModal(5);
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.System.enableAllButton();
			}
		},
		"error": function(response) {
			alert("error");
		}
	} );
};

WebGate.System.showResponse = function(response, statusText) {
    WebGate.System.disableAllButton();
    if (response.isSuccess == "true") {
        ajaxAlertSuccessMsg(response.msg, true);
        refreshFrameDataTableInLayer('WebGate.System.oTable1');
        autoCloseCommonModal(5);
    } else {
        ajaxAlertErrorMsg(response.msg, true);
        WebGate.System.enableAllButton();
    }
};

$(function() {
    var options = {
        beforeSubmit : WebGate.System.validate,
        success : WebGate.System.showResponse,
        url : $('#ctx').val() + "/pages/system/form/editSubmit",
        type : 'post',
        clearForm : false,
        timeout : 3000
    };
    $('#SystemForm').ajaxForm(options);
});

WebGate.System.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.System.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};