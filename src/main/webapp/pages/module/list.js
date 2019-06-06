WebGate.Module = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.Module.addObj = function() {
	//alert($('#ctx').val());
	// create the backdrop and wait for next modal to be triggered
	//$('body').modalmanager('loading');
	var url = $('#ctx').val()+"/pages/module/form/edit";
	//window.parent.showCommonModal(url);
	var commonModalCss = {
		"width": "1000px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Module.modObj = function(id) {
	//alert(id);
	var url = $('#ctx').val()+"/pages/module/form/edit?eid="+id;
	//window.location.href = url;
	//window.parent.showCommonModal(url);
	var commonModalCss = {
		"width": "1000px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Module.delObj = function(id) {
	//alert(id);
	//openConfirmModalInFrame("确实要删除此数据吗?", "WebGate.Module.delObjCallback("+id+")", null, null);
	openConfirmModalInFrame(Global_Prompt_Delete_Message, function() {
		//alert("WebGate.Module.delObjCallback:" + id);
		var url = $('#ctx').val()+"/pages/module/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				//alert("success");
				//alert(JSON.stringify(response));
				if (response.isSuccess == "true") {
					//window.parent.alertMsg('successModal', 'successMsg', response.msg);
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.Module.oTable1');
				} else {
					//window.parent.alertMsg('errorModal', 'errorMsg', response.msg);
					openErrorAlertModalInFrame(response.msg);
				}
			},
			"error": function(response) {
				alert("error");
			}
		} );
	}, null, null);
};

WebGate.Module.query = function() {
	//alert('query');
	// 重新刷新页面Table
	//WebGate.Module.oTable1.fnDraw();
	//window.frames['iframe_main'].WebGate.Module.oTable1.fnDraw();
	//refreshDataTable('WebGate.Module.oTable1', iframe);
	initPagingParamsInFrame('WebGate.Module.oTable1');
	refreshFrameDataTableInFrame('WebGate.Module.oTable1');
};

WebGate.Module.retrieveData = function(sSource, aoData, fnCallback) {
   	//aoData.push( {"form" : {"iDisplayLength":5, "iDisplayStart":1}} );
   	//aoData.push( {"name": "ddd"} );
    //alert(JSON.stringify(aoData));
   	//alert(sSource);
   	//var param = {"sEcho": 1};
       $.ajax( {
           "dataType": 'json',
           //方式一
           //"contentType": "application/json",
           "type": "POST",
           "url": sSource,
           "cache": false,
           //方式二
           "data": aoData,
           //方式一
           //"data": JSON.stringify(param), //以json格式传递
           //"data": JSON.stringify(aoData), //以json格式传递
           //"data": "iDisplayStart=" + 1 + "&iDisplayLength=" + 5,
           "success": function(response) {
           	//alert("success");
           	//alert(JSON.stringify(response));
           	//alert(JSON.stringify(response.aaData));
           	/*for(i in response){
           		 alert(i);//i就是test的属性名
           		 alert(response[i]);//test.i就是属性值
           		 for (j in response[i]) {
            		 alert(j);//i就是test的属性名
            		 alert(response[i][j]);//test.i就是属性值
            		 for (k in response[i][j]) {
                		 alert(k);//i就是test的属性名
                		 alert(response[i][j][k]);//test.i就是属性值
            		 }
           		 }
           	}*/
           	//服务器端返回的对象的returnObject部分是要求的格式
           	//json.iTotalRecords = jData.find("[nodeName=opensearch:totalResults]").text();
			//json.iTotalDisplayRecords = json.iTotalRecords;
			//response.iTotalRecords = 500;
			//response.iTotalDisplayRecords = 500;
           	fnCallback(response);
           },
           "error": function(response) {
           	alert("error");
           	alert(response);
           	alert(response.responseText);
           	/*for(i in response){
           		 alert(i);//i就是test的属性名
           		 alert(response.i);//test.i就是属性值
           	}*/
           }
       } );
};