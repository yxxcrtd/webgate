/******************************************* 验证开始 **************************************************************/
var digitalExpression = /^(([0-9]+[\.]?[0-9]+)|[1-9])$/;
var nameValidateFlag = false;

WebGate.Module.validate = function() {
	var flag = true;
	if (!WebGate.Module.validateName()) {
		flag = false;
	}
	if(!WebGate.Module.validateI18n()) {
		flag = false;
	}
	if (!WebGate.Module.validateLink()) {
		flag = false;
	}
	if (!WebGate.Module.validateIcon()) {
		flag = false;
	}
	if (!WebGate.Module.validateHeight()) {
		flag = false;
	}
	if (!WebGate.Module.validateWidth()) {
		flag = false;
	}
	if (!WebGate.Module.validateMust()) {
		flag = false;
	}
	return flag;
};
/******************************************* 验证Name开始 **************************************************************/
WebGate.Module.validateName = function() {
	if ($("#name").val().trim() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html(Module_Info_ValidateName_Blank);
		return false;
	} else {
		WebGate.Module.validateNameUnique();
		if (nameValidateFlag == true) {
			return true;
		} else {
			return false;
		}
	}
};

WebGate.Module.validateNameUnique = function() {
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val() + "/pages/module/form/validateNameUnique",
		data : {
			name : $("#name").val(),
			id: $('#id').val()
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
/******************************************* 验证国际化开始 **************************************************************/
WebGate.Module.validateI18n = function() {
	if ($("#i18n").val() == "") {
		$("#i18nDiv").addClass("error");
		$("#i18nSpan").html(Module_Info_ValidateI18n_Blank);
		return false;
	}
	$("#i18nDiv").removeClass("error");
	$("#i18nSpan").html("");
	return true;
};
/******************************************* 验证国际化结束 **************************************************************/
/******************************************* 验证Link开始 **************************************************************/
WebGate.Module.validateLink = function() {
	if ($("#link").val().trim() == "") {
		$("#linkDiv").addClass("error");
		$("#linkSpan").html(Module_Info_ValidateLink_Blank);
		return false;
	}
	$("#linkDiv").removeClass("error");
	$("#linkSpan").html("");
	return true;
};
/******************************************* 验证Link结束 **************************************************************/
/******************************************* 验证Icon开始 **************************************************************/
WebGate.Module.validateIcon = function() {
	//icon可以为空
	/**
	if ($("#icon").val() == "") {
		$("#iconDiv").addClass("error");
		$("#iconSpan").html(Module_Info_ValidateIcon_Blank);
		return false;
	}
	$("#iconDiv").removeClass("error");
	$("#iconSpan").html("");
	**/
	return true;
};
/******************************************* 验证Icon结束 **************************************************************/
/******************************************* 验证Height开始 **************************************************************/
WebGate.Module.validateHeight = function() {
	if ($("#height").val() == "") {
		$("#heightDiv").addClass("error");
		$("#heightSpan").html(Module_Info_ValidateHeight_Blank);
		return false;
	}
	if (!digitalExpression.test($("#height").val())) {
		$("#heightDiv").addClass("error");
		$("#heightSpan").html(Module_Info_ValidateHeight_Number);
		return false;
	}
	$("#heightDiv").removeClass("error");
	$("#heightSpan").html("");
	return true;
};
/******************************************* 验证Height结束 **************************************************************/
/******************************************* 验证Width开始 **************************************************************/
WebGate.Module.validateWidth = function() {
	if ($("#width").val() == "") {
		$("#widthDiv").addClass("error");
		$("#widthSpan").html(Module_Info_ValidateWidth_Blank);
		return false;
	}
	if (!digitalExpression.test($("#width").val())) {
		$("#widthDiv").addClass("error");
		$("#widthSpan").html(Module_Info_ValidateWidth_Number);
		return false;
	}
	$("#widthDiv").removeClass("error");
	$("#widthSpan").html("");
	return true;
};
/******************************************* 验证Width结束 **************************************************************/
/******************************************* 验证Must开始 **************************************************************/
WebGate.Module.validateMust = function() {
	if ($("#must").val() == "") {
		$("#mustDiv").addClass("error");
		$("#mustSpan").html(Module_Info_ValidateMust_Blank);
		return false;
	}
	$("#mustDiv").removeClass("error");
	$("#mustSpan").html("");
	return true;
};
/******************************************* 验证Must结束 **************************************************************/
/******************************************* 验证结束 **************************************************************/

WebGate.Module.save = function() {
	//alert($('#ctx').val());
	if (!WebGate.Module.validate()) {
		return;
	}
	//alert('save');
	WebGate.Module.disableAllButton();
	var url=$('#ctx').val() + "/pages/module/form/editSubmit";
	//alert(url);
	$.ajax( {
		"dataType": 'json',
		//方式一
		//"contentType": "application/json",
		"type": "POST",
		"url": url,
		"cache": false,
		//方式二
		"data": {
			//"obj": {
				"id": $('#id').val(),
				"obj.id": $('#id').val(),
				"obj.name": $('#name').val(),
				"obj.link": $('#link').val(),
				"obj.more": $('#more').val(),
				"obj.height": $('#height').val(),
				"obj.width": $('#width').val(),
				"obj.must": $('#must').val(),
				"obj.i18n": $('#i18n').val(),
				"obj.component.id": $('#component').val()
			//}
		},
		//方式一
		//"data": JSON.stringify(param), //以json格式传递
		//"data": JSON.stringify(aoData), //以json格式传递
		//"data": "iDisplayStart=" + 1 + "&iDisplayLength=" + 5,
		"success": function(response) {
			//alert("success");
			//alert(JSON.stringify(response));
			//alert(JSON.stringify(response.aaData));
			//WebGate.Module.query(true);
			if (response.isSuccess == "true") {
				//window.parent.alertMsg('successModal', 'successMsg', response.msg);
				//$("#successMessage").html(response.msg);
				ajaxAlertSuccessMsg(response.msg, true);
				refreshFrameDataTableInLayer('WebGate.Module.oTable1');
				autoCloseCommonModal(5);
				//$(".alert").alert();
				/*var t = setTimeout(function() {
					window.parent.closeCommonModal();
				}, 5000);*/
			} else {
				//$(".alert").hide();
				//$("#errorMessageContent").html(response.msg);
				//$("#errorMessage").show();
				ajaxAlertErrorMsg(response.msg, true);
				WebGate.Module.enableAllButton();
				//window.parent.alertMsg('errorModal', 'errorMsg', response.msg);
			}
		},
		"error": function(response) {
			alert("error");
			alert(response);
			alert(response.responseText);
			for(i in response){
           		 alert(i);//i就是test的属性名
           		 alert(response.i);//test.i就是属性值
           	}
		}
	} );
};

WebGate.Module.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

WebGate.Module.enableAllButton = function() {
	$("#save").removeAttr('disabled');
	$("#reset").removeAttr('disabled');
};

WebGate.Module.showResponse = function(response, statusText) {
    WebGate.Module.disableAllButton();
    if (response.isSuccess == "true") {
        ajaxAlertSuccessMsg(response.msg, true);
        refreshFrameDataTableInLayer('WebGate.Module.oTable1');
        autoCloseCommonModal(5);
    } else {
        ajaxAlertErrorMsg(response.msg, true);
        WebGate.Module.enableAllButton();
    }
};

$(function() {
    var options = {
        beforeSubmit : WebGate.Module.validate,
        success : WebGate.Module.showResponse,
        url : $('#ctx').val() + "/pages/module/form/editSubmit",
        type : 'post',
        clearForm : false,
        timeout : 3000
    };
    $('#ModuleForm').ajaxForm(options);
});
