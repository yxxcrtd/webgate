/******************************************* 验证开始 **************************************************************/
var nameValidateFlag = false;

WebGate.Account.validate = function() {
	var flag = true;
	if (!WebGate.Account.validateName()) {
		flag = false;
	}
	if (!WebGate.Account.validateStatus()) {
		flag = false;
	}
	if (!WebGate.Account.validateType()) {
		flag = false;
	}
	if (!WebGate.Account.validateLevel()) {
		flag = false;
	}
	return flag;
};
/******************************************* 验证Name开始 **************************************************************/
WebGate.Account.validateName = function() {
	if ($("#name").val().trim() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html(Account_Info_ValidateName_Blank);
		return false;
	} else {
		WebGate.Account.validateNameUnique();
		if (nameValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.Account.validateNameUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/account/form/validateNameUnique",
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
WebGate.Account.validateStatus = function() {
	if ($("#status").val() == "") {
		$("#statusDiv").addClass("error");
		$("#statusSpan").html(Account_Info_ValidateStatus_Blank);
		return false;
	}
	$("#statusDiv").removeClass("error");
	$("#statusSpan").html("");
	return true;
};
/******************************************* 验证Status结束 **************************************************************/
/******************************************* 验证Type开始 **************************************************************/
WebGate.Account.validateType = function() {
	if ($("#type").val() == "") {
		$("#typeDiv").addClass("error");
		$("#typeSpan").html(Account_Info_ValidateType_Blank);
		return false;
	}
	$("#typeDiv").removeClass("error");
	$("#typeSpan").html("");
	return true;
};
/******************************************* 验证Type结束 **************************************************************/
/******************************************* 验证Level开始 **************************************************************/
WebGate.Account.validateLevel = function() {
	if ($("#level").val() == "") {
		$("#levelDiv").addClass("error");
		$("#levelSpan").html(Account_Info_ValidateLevel_Blank);
		return false;
	}
	$("#levelDiv").removeClass("error");
	$("#levelSpan").html("");
	return true;
};
/******************************************* 验证Level结束 **************************************************************/
/******************************************* 验证结束 **************************************************************/

WebGate.Account.save = function() {
	if (!WebGate.Account.validate()) {
		return;
	}
	WebGate.Account.disableAllButton();
	var url=$('#ctx').val() + "/pages/account/form/editSubmit";
	$.ajax( {
		"dataType": 'json',
		"type": "POST",
		"url": url,
		"cache": false,
		"data": {
			"id": $('#id').val(),
			"obj.id": $('#id').val(),
			"obj.name": $('#name').val(),
			"obj.status": $('#status').val(),
			"obj.type": $('#type').val(),
			"obj.level": $('#level').val()
		},
		"success": function(response) {
			if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				refreshFrameDataTableInLayer('WebGate.Account.oTable1');
				autoCloseCommonModal(5);
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.Account.enableAllButton();
			}
		},
		"error": function(response) {
			alert("error");
		}
	} );
};

WebGate.Account.showResponse = function(response, statusText) {
    WebGate.Account.disableAllButton();
    if (response.isSuccess == "true") {
        ajaxAlertSuccessMsg(response.msg, true);
        refreshFrameDataTableInLayer('WebGate.Account.oTable1');
        autoCloseCommonModal(5);
    } else {
        ajaxAlertErrorMsg(response.msg, true);
        WebGate.Account.enableAllButton();
    }
};

$(function() {
    var options = {
        beforeSubmit : WebGate.Account.validate,
        success : WebGate.Account.showResponse,
        url : $('#ctx').val() + "/pages/account/form/editSubmit",
        type : 'post',
        clearForm : false,
        timeout : 3000
    };
    $('#AccountForm').ajaxForm(options);
});

WebGate.Account.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.Account.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};