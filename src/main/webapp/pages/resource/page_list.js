WebGate.ResourcePage = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.ResourcePage.addObj = function() {
	var resourceId = $('#query_page_resource').val();
	var url = $('#ctx').val()+"/pages/page/form/edit?resourceId=" + resourceId;
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.ResourcePage.modObj = function(id) {
	var url = $('#ctx').val()+"/pages/page/form/edit?eid="+id;
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.ResourcePage.showResource = function(id) {
	var url = $('#ctx').val()+"/pages/page/form/edit?eid="+id;
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.ResourcePage.delObj = function(id) {
	openConfirmModalInFrame(Global_Prompt_Delete_Message, function() {
		var url = $('#ctx').val()+"/pages/page/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.ResourcePage.oTable1');
				} else {
					openErrorAlertModalInFrame(response.msg);
				}
			},
			"error": function(response) {
				alert("error");
			}
		} );
	}, null, null);
};

WebGate.ResourcePage.query = function() {
	initPagingParamsInFrame('WebGate.ResourcePage.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.ResourcePage.oTable1');
};

WebGate.ResourcePage.retrieveData = function(sSource, aoData, fnCallback) {
       $.ajax( {
           "dataType": 'json',
           "type": "POST",
           "url": sSource,
           "cache": false,
           "data": aoData,
           "success": function(response) {
        	   fnCallback(response);
           },
           "error": function(response) {
        	   alert("error");
           }
       } );
};

WebGate.ResourcePage.initPageContentData = function(resourceId) {
    $("#query_page_resource").val(resourceId);
	WebGate.ResourcePage.createDataTable();
};

WebGate.ResourcePage.createDataTable = function() {
	if (WebGate.ResourcePage.oTable1 != null) {
		WebGate.ResourcePage.query();
	} else {
		WebGate.ResourcePage.oTable1 = $('#page_table_report').dataTable( {
	        "bFilter": false, //开关，是否启用客户端过滤器
	        "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
	        "bAutoWidth": false, //自适应宽度
	        "sPaginationType": "full_numbers", //分页样式
	        "bServerSide": true, //从服务器端取数据
	       	"sAjaxSource": $('#ctx').val()+"/pages/page/form/manager?now=" + new Date().getTime(), //mvc后台ajax调用接口。
	       	"fnServerParams": function(aoData) {
	       		aoData.push({"name": "code", "value": $("#query_page_code").val()});
	       		aoData.push({"name": "name", "value": $("#query_page_name").val()});
	       		aoData.push({"name": "resourceId", "value": $("#query_page_resource").val()});
	       	},
	        "fnServerData": WebGate.ResourcePage.retrieveData,
	        "fnDrawCallback": function(oSettings ) {
				for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )	{
					$('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+oSettings._iDisplayStart+1 );
				}
	        },
	        "aoColumns": [ { 
	    			"sTitle": ResourcePage_List_Table_Column_ID,
	        		"mDataProp": "id"
	            }, {
	    			"sTitle": ResourcePage_List_Table_Column_Code,
	            	"mDataProp": "code"
				}, {
	    			"sTitle": ResourcePage_List_Table_Column_Name,
					"mDataProp": "name"
				}, {
	    			"sTitle": ResourcePage_List_Table_Column_Path,
					"mDataProp": "path"
				}, {
	    			"sTitle": ResourcePage_List_Table_Column_Resource,
	    			"mData": null,
	              	//自定义列的样式
	                "fnRender": function (oObj) {
	                	var resource = oObj.aData.resource;
	                	if (resource == null) {
	                		return "未分配给任何资源";
	                	} else {
	                		return resource.name;
	                	}
	            	}
				}, {
	    			"sTitle": Global_Prompt_Operating,
	    			"mData": null,
	    	        "aTargets": [ -1 ],
	              	//自定义列的样式
	                "fnRender": function (oObj) {
	                    var start = '<div class="hidden-phone visible-desktop btn-group">';
	                    var edit = '<button class="btn btn-mini btn-warning" onclick="WebGate.ResourcePage.modObj(\'' + oObj.aData.id + '\')"><i class="icon-edit bigger-120"></i></button>';
	                    var trash = '<button class="btn btn-mini btn-danger" onclick="WebGate.ResourcePage.delObj(\'' + oObj.aData.id + '\')"><i class="icon-trash bigger-120"></i></button>';
	                    var end = '</div>';
	                    return start + edit + trash + end;
	            	}
				}
	        ],
	        
	      	//多语言配置
	        "oLanguage": {
	            "sProcessing": Global_Paging_Processing,
	            "sLengthMenu": Global_Paging_Length_Menu,
	            "sZeroRecords": Global_Paging_Zero_Records,
	            "sEmptyTable": Global_Paging_Empty_Table,
	            "sInfo": Global_Paging_Info,
	            "sInfoFiltered": Global_Paging_Info_Filtered,
	            "sSearch": Global_Paging_Search,
	            "oPaginate": {
	                "sFirst": Global_Paging_First,
	                "sPrevious": Global_Paging_Prev,
	                "sNext": Global_Paging_Next,
	                "sLast": Global_Paging_Last
	                }
	            }

	    } );

		$('[data-rel=tooltip]').tooltip();
	}
};