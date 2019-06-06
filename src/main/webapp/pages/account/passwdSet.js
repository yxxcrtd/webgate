/******************************************* 验证开始 **************************************************************/
var originPassValidateFlag = false;

WebGate.Account.setPasswdValidate = function() {
	var flag = true;
	if (!WebGate.Account.originPassValidate()) {
		flag = false;
	}
	if (!WebGate.Account.newPassValidate()) {
		flag = false;
	}
	if (!WebGate.Account.confirmPassValidate()) {
		flag = false;
	}
	return flag;
};
/******************************************* 验证原始密码开始 **************************************************************/
WebGate.Account.originPassValidate = function() {
	if ($("#origin_pass").val() == "") {
		$("#opassDiv").addClass("error");
		$("#opassSpan").html(Account_Info_Origin_Pass_Validate_Blank);
		return false;
	} else {
		WebGate.Account.validateOriginPassCorrect();
		if (originPassValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.Account.validateOriginPassCorrect = function() {
	$.ajax({
		type : "POST",
		async : false,
		dataType: 'json',
		url : $('#ctx').val() + "/pages/account/form/validatePassCorrect.json",
		data : {
			id : $("#id").val(),opass:$("#origin_pass").val()
		},
		success : function(response) {
			if(response.sessionForm!=undefined){
				if(response.sessionForm.exist =='false'){
					$("#opassDiv").removeClass("success").addClass("error");
					$("#opassSpan").html(response.sessionForm.msg);
					originPassValidateFlag = false;
				}
			}else{
				if (response.isSuccess == "true") {
					$("#opassDiv").removeClass("error").addClass("success");
					$("#opassSpan").html(response.msg);
					originPassValidateFlag = true;
				} else {
					$("#opassDiv").removeClass("success").addClass("error");
					$("#opassSpan").html(response.msg);
					originPassValidateFlag = false;
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.responseText);
			alert(XMLHttpRequest.status);
			alert(textStatus);
			isSuccess = false;
		}
	});
};
/******************************************* 验证原始密码结束 **************************************************************/
/******************************************* 验证新密码开始 **************************************************************/
WebGate.Account.newPassValidate = function() {
	if ($("#new_pass").val() == "") {
		$("#npassDiv").removeClass("success").addClass("error");
		$("#npassSpan").html(Account_Info_New_Pass_Validate_Blank);
		return false;
	}
	$("#npassDiv").removeClass("error").addClass("success");
	$("#npassSpan").html("");
	return true;
};
/******************************************* 验证新密码结束 **************************************************************/
/******************************************* 验证确认密码开始 **************************************************************/
WebGate.Account.confirmPassValidate = function() {
	if ($("#confirm_pass").val() == "") {
		$("#cpassDiv").removeClass("success").addClass("error");
		$("#cpassSpan").html(Account_Info_Confirm_Pass_Validate_Blank);
		return false;
	}else{
		var conpass=$("#confirm_pass").val();
		var newpass=$("#new_pass").val();
		if ($("#confirm_pass").val() != ""&&$("#new_pass").val() != ""&&conpass != newpass) {
			$("#cpassDiv").removeClass("success").addClass("error");
			$("#cpassSpan").html(Account_Info_New_Confirm_Pass_Not_Same);
			return false;
		}
	}
	$("#cpassDiv").removeClass("error").addClass("success");
	$("#cpassSpan").html("");
	return true;
};
/******************************************* 验证确认密码结束 **************************************************************/
/******************************************* 验证结束 **************************************************************/

WebGate.Account.passwdSave = function() {
	if (!WebGate.Account.setPasswdValidate()) {
		return;
	}
	WebGate.Account.disableAllButton();
	var url=$('#ctx').val() + "/pages/account/form/setPasswdSubmit.json";
	$.ajax( {
		"dataType": 'json',
		"type": "POST",
		"url": url,
		"cache": false,
		"data": {
			"id": $('#id').val(),
			"origin_pass": $('#origin_pass').val(),
			"new_pass": $('#new_pass').val(),
			"confirm_pass": $('#confirm_pass').val()
		},
		"success": function(response) {
			if(response.sessionForm!=undefined){
				if(response.sessionForm.exist =='false'){
					ajaxAlertErrorMsg(response.sessionForm.msg, true);
					WebGate.Account.enableAllButton();
				}
			}else{
				if (response.isSuccess == "true") {
					ajaxAlertSuccessMsg(response.msg, true);
					autoCloseCommonModal(5);
				} else {
					ajaxAlertErrorMsg(response.msg, true);
					WebGate.Account.enableAllButton();
				}
			}
		},
		"error": function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.responseText);
			alert(XMLHttpRequest.status);
			alert(textStatus);
			isSuccess = false;
		}
	} );
};

WebGate.Account.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.Account.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};