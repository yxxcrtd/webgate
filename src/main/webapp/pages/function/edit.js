/******************************************* 验证开始 **************************************************************/
var codeValidateFlag = false;
var nameValidateFlag = false;

WebGate.Function.validate = function() {
	var flag = true;
	if (!WebGate.Function.validateCode()) {
		flag = false;
	}
	if (!WebGate.Function.validateName()) {
		flag = false;
	}
	if (!WebGate.Function.validatePath()) {
		flag = false;
	}
	if (!WebGate.Function.validatePage()) {
		flag = false;
	}
	return flag;
};
/******************************************* 验证Code开始 **************************************************************/
WebGate.Function.validateCode = function() {
	if ($("#code").val() == "") {
		$("#codeDiv").addClass("error");
		$("#codeSpan").html(Function_Info_ValidateCode_Blank);
		return false;
	} else {
		WebGate.Function.validateCodeUnique();
		if (codeValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.Function.validateCodeUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/function/form/validateCodeUnique",
		data : {
			code : $("#code").val(),
			id: $('#id').val(),
			pageId: $('#page').val()
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
WebGate.Function.validateName = function() {
	if ($("#name").val() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html(Function_Info_ValidateName_Blank);
		return false;
	} else {
		//名称暂时不需要唯一性验证
		/**
		WebGate.Function.validateNameUnique();
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

WebGate.Function.validateNameUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/function/form/validateNameUnique",
		data : {
			name : $("#name").val(),
			id: $('#id').val(),
			pageId: $('#page').val()
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
WebGate.Function.validatePath = function() {
	if ($("#path").val() == "") {
		$("#pathDiv").addClass("error");
		$("#pathSpan").html(Function_Info_ValidatePath_Blank);
		return false;
	}
	$("#pathDiv").removeClass("error");
	$("#pathSpan").html("");
	return true;
};
/******************************************* 验证Path结束 **************************************************************/
/******************************************* 验证Page开始 **************************************************************/
WebGate.Function.validatePage = function() {
	if ($("#page").val() == "") {
		$("#pageDiv").addClass("error");
		$("#pageSpan").html(Function_Info_ValidatePage_Blank);
		return false;
	}
	$("#pageDiv").removeClass("error");
	$("#pageSpan").html("");
	return true;
};
/******************************************* 验证Page结束 **************************************************************/
/******************************************* 验证结束 **************************************************************/

WebGate.Function.save = function() {
	if (!WebGate.Function.validate()) {
		return;
	}
	WebGate.Function.disableAllButton();
	var url=$('#ctx').val() + "/pages/function/form/editSubmit";
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
			"obj.page.id": $('#page').val()
		},
		"success": function(response) {
			if (response.isSuccess == "true") {
				ajaxAlertSuccessMsg(response.msg, true);
				refreshFrameDataTableInLayer('WebGate.Function.oTable1');
				autoCloseCommonModal(5);
			} else {
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.Function.enableAllButton();
			}
		},
		"error": function(response) {
			alert("error");
		}
	} );
};

WebGate.Function.showResponse = function(response, statusText) {
    WebGate.Function.disableAllButton();
    if (response.isSuccess == "true") {
        ajaxAlertSuccessMsg(response.msg, true);
        refreshFrameDataTableInLayer('WebGate.Function.oTable1');
        autoCloseCommonModal(5);
    } else {
        ajaxAlertErrorMsg(response.msg, true);
        WebGate.Function.enableAllButton();
    }
};

$(function() {
    var options = {
        beforeSubmit : WebGate.Function.validate,
        success : WebGate.Function.showResponse,
        url : $('#ctx').val() + "/pages/function/form/editSubmit",
        type : 'post',
        clearForm : false,
        timeout : 3000
    };
    $('#FunctionForm').ajaxForm(options);
});

WebGate.Function.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.Function.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};