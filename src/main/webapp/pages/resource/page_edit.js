/******************************************* 验证开始 **************************************************************/
var codeValidateFlag = false;
var nameValidateFlag = false;

WebGate.ResourcePage.validate = function() {
	var flag = true;
	if (!WebGate.ResourcePage.validateCode()) {
		flag = false;
	}
	if (!WebGate.ResourcePage.validateName()) {
		flag = false;
	}
	if (!WebGate.ResourcePage.validatePath()) {
		flag = false;
	}
	return flag;
};
/******************************************* 验证Code开始 **************************************************************/
WebGate.ResourcePage.validateCode = function() {
	if ($("#code").val().trim() == "") {
		$("#codeDiv").addClass("error");
		$("#codeSpan").html(ResourcePage_Info_ValidateCode_Blank);
		return false;
	} else {
		WebGate.ResourcePage.validateCodeUnique();
		if (codeValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.ResourcePage.validateCodeUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',	
		url : $('#ctx').val() + "/pages/page/form/validateCodeUnique",
		data : {
			code : $("#code").val(),
			id: $('#id').val(),
			resourceId: $('#resource').val()
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
WebGate.ResourcePage.validateName = function() {
	if ($("#name").val().trim() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html(ResourcePage_Info_ValidateName_Blank);
		return false;
	} else {
		//名称暂时不需要唯一性验证
		/**
		WebGate.ResourcePage.validateNameUnique();
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

WebGate.ResourcePage.validateNameUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/page/form/validateNameUnique",
		data : {
			name : $("#name").val(),
			id: $('#id').val(),
			resourceId: $('#resource').val()
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
/******************************************* 验证Path开始 **************************************************************/
WebGate.ResourcePage.validatePath = function() {
	if ($("#path").val().trim() == "") {
		$("#pathDiv").addClass("error");
		$("#pathSpan").html(ResourcePage_Info_ValidatePath_Blank);
		return false;
	}
	$("#pathDiv").removeClass("error");
	$("#pathSpan").html("");
	return true;
};
/******************************************* 验证Path结束 **************************************************************/
/******************************************* 验证结束 **************************************************************/

WebGate.ResourcePage.save = function() {
	if (!WebGate.ResourcePage.validate()) {
		return;
	}
	WebGate.ResourcePage.disableAllButton();
	var url=$('#ctx').val() + "/pages/page/form/editSubmit";
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
			"obj.path": $('#path').val(),
			"obj.special": $('#special').val(),
			"obj.resource.id": $('#resource').val()
		},
		"success": function(response) {
			if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				refreshFrameDataTableInLayer('WebGate.ResourcePage.oTable1');
				autoCloseCommonModal(5);
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.ResourcePage.enableAllButton();
			}
		},
		"error": function(response) {
			alert("error");
		}
	} );
};

WebGate.ResourcePage.showResponse = function(response, statusText) {
    WebGate.ResourcePage.disableAllButton();
    if (response.isSuccess == "true") {
        ajaxAlertSuccessMsg(response.msg, true);
        refreshFrameDataTableInLayer('WebGate.ResourcePage.oTable1');
        autoCloseCommonModal(5);
    } else {
        ajaxAlertErrorMsg(response.msg, true);
        WebGate.ResourcePage.enableAllButton();
    }
};

$(function() {
    var options = {
        beforeSubmit : WebGate.ResourcePage.validate,
        success : WebGate.ResourcePage.showResponse,
        url : $('#ctx').val() + "/pages/page/form/editSubmit",
        type : 'post',
        clearForm : false,
        timeout : 3000
    };
    $('#ResourcePageForm').ajaxForm(options);
});

WebGate.ResourcePage.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.ResourcePage.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};