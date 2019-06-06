/******************************************* 验证开始 **************************************************************/
var nameValidateFlag = false;

WebGate.Role.validate = function() {
	var flag = true;
	if (!WebGate.Role.validateName()) {
		flag = false;
	}
	if (!WebGate.Role.validateSystem()) {
		flag = false;
	}
	if (!WebGate.Role.validateStatus()) {
		flag = false;
	}
	return flag;
};
/******************************************* 验证Name开始 **************************************************************/
WebGate.Role.validateName = function() {
	if ($("#name").val().trim() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html(Role_Info_ValidateName_Blank);
		return false;
	} else {
		WebGate.Role.validateNameUnique();
		if (nameValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.Role.validateNameUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/role/form/validateNameUnique",
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
WebGate.Role.validateStatus = function() {
	if ($("#status").val() == "") {
		$("#statusDiv").addClass("error");
		$("#statusSpan").html(Role_Info_ValidateStatus_Blank);
		return false;
	}
	$("#statusDiv").removeClass("error");
	$("#statusSpan").html("");
	return true;
};
/******************************************* 验证Status结束 **************************************************************/
/******************************************* 验证System开始 **************************************************************/
WebGate.Role.validateSystem = function() {
	if ($("#sysId").val() == "") {
		$("#sysIdDiv").addClass("error");
		$("#sysIdSpan").html(Role_Info_ValidateSystem_Blank);
		return false;
	}
	$("#sysIdDiv").removeClass("error");
	$("#sysIdSpan").html("");
	return true;
};
/******************************************* 验证System结束 **************************************************************/
/******************************************* 验证结束 **************************************************************/

WebGate.Role.save = function() {
	if (!WebGate.Role.validate()) {
		return;
	}
	WebGate.Role.disableAllButton();
	var url=$('#ctx').val() + "/pages/role/form/editSubmit";
	$.ajax( {
		"dataType": 'json',
		"type": "POST",
		"url": url,
		"cache": false,
		"data": {
			"id": $('#id').val(),
			"obj.id": $('#id').val(),
			"obj.name": $('#name').val(),
			"obj.system.id": $('#sysId').val(),
			"obj.status": $('#status').val()
		},
		"success": function(response) {
			if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				refreshFrameDataTableInLayer('WebGate.Role.oTable1');
				autoCloseCommonModal(5);
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.Role.enableAllButton();
			}
		},
		"error": function(response) {
			alert("error");
		}
	} );
};

WebGate.Role.showResponse = function(response, statusText) {
    WebGate.Role.disableAllButton();
    if (response.isSuccess == "true") {
        ajaxAlertSuccessMsg(response.msg, true);
        refreshFrameDataTableInLayer('WebGate.Role.oTable1');
        autoCloseCommonModal(5);
    } else {
        ajaxAlertErrorMsg(response.msg, true);
        WebGate.Role.enableAllButton();
    }
};

$(function() {
    var options = {
        beforeSubmit : WebGate.Role.validate,
        success : WebGate.Role.showResponse,
        url : $('#ctx').val() + "/pages/role/form/editSubmit",
        type : 'post',
        clearForm : false,
        timeout : 3000
    };
    $('#RoleForm').ajaxForm(options);
});

WebGate.Role.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.Role.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};