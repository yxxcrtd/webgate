WebGate.ResourceRole = function() {
    this.editor = null;
    this.artDialog = null;
    this.oTable1 = null;
};

WebGate.ResourceRole.addObj = function() {
	var url = $('#ctx').val()+"/pages/role/form/edit";
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.ResourceRole.modObj = function(id) {
	var url = $('#ctx').val()+"/pages/role/form/edit?eid="+id;
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.ResourceRole.delObj = function(id) {
	openConfirmModalInFrame(Global_Prompt_Delete_Message, function() {
		var url = $('#ctx').val()+"/pages/role/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					openSuccessAlertModalInFrame(response.msg);
					refreshFrameDataTableInFrame('WebGate.ResourceRole.oTable1');
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

WebGate.ResourceRole.query = function() {
	initPagingParamsInFrame('WebGate.ResourceRole.oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('WebGate.ResourceRole.oTable1');
};

WebGate.ResourceRole.saveRoleList = function() {
	//alert("saveRoleList");
	WebGate.ResourceRole.disableAllButton();
	var sData = $('input', WebGate.ResourceRole.oTable1.fnGetNodes()).serialize();
	//alert(JSON.stringify(sData));
	//alert( "The following data would have been submitted to the server: \n\n"+sData );
	var url = $('#ctx').val()+"/pages/resource/form/batchSaveRole?id="+$('#query_role_resource').val();
	$.ajax( {
        "dataType": 'json',
        "type": "POST",
        "url": url,
        "cache": false,
        "data": sData,
        "success": function(response) {
        	//alert(JSON.stringify(response));
        	//alert(response.isSuccess);
        	//alert(response.msg);
        	if (response.isSuccess == "true") {
				//ajaxAlertSuccessMsg(response.msg, true);
        		openSuccessAlertModalInFrame(response.msg);
				WebGate.ResourceRole.enableAllButton();
			} else {
				//ajaxAlertErrorMsg(response.msg, true);
				openErrorAlertModalInFrame(response.msg);
				WebGate.ResourceRole.enableAllButton();
			}
        },
        "error": function(response) {
     	   alert("error");
        }
    } );
};

WebGate.ResourceRole.disableAllButton = function() {
	$("#save").attr('disabled', "true");
};

WebGate.ResourceRole.enableAllButton = function() {
	$("#save").removeAttr('disabled');
};

WebGate.ResourceRole.retrieveData = function(sSource, aoData, fnCallback) {
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

WebGate.ResourceRole.initRoleContentData = function(resourceId) {
	var url = $('#ctx').val()+"/pages/resource/form/loadRoleData?resourceId="+resourceId;
	$.ajax( {
        "dataType": 'json',
        "type": "POST",
        "url": url,
        "cache": false,
        //"data": aoData,
        "success": function(response) {
            $("#query_role_resource").val(resourceId);
            var system_options_str = "";
            $("#query_role_system").html("");
            $.each(response.systemList, function(i, system){
                 system_options_str += "<option value=\"" + system.id + "\" >" + system.name + "</option>";
            });
            $("#query_role_system").append(system_options_str);
            
            var status_options_str = "";
            $("#query_role_status").html("<option value='" + "'>-" + Global_Label_Select + "-</option>");
            $.each(response.statusMap, function(i, status){
            	status_options_str += "<option value=\"" + i + "\" >" + status + "</option>";
            });
            $("#query_role_status").append(status_options_str);
        	
            WebGate.ResourceRole.createDataTable();
        },
        "error": function(response) {
     	   alert("error");
        }
    } );
};

WebGate.ResourceRole.createDataTable = function() {
	if (WebGate.ResourceRole.oTable1 != null) {
		WebGate.ResourceRole.query();
	} else {
		WebGate.ResourceRole.oTable1 = $('#role_table_report').dataTable( {
	        "bFilter": false, //开关，是否启用客户端过滤器
	        "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
	        "bAutoWidth": false, //自适应宽度
	        "sPaginationType": "full_numbers", //分页样式
	        "bServerSide": true, //从服务器端取数据
	       	"sAjaxSource": $('#ctx').val()+"/pages/resource/form/roleManager?now=" + new Date().getTime(), //mvc后台ajax调用接口。
	       	"fnServerParams": function(aoData) {
	       		aoData.push({"name": "name", "value": $("#query_role_name").val()});
	       		aoData.push({"name": "sysId", "value": $("#query_role_system").val()});
	       		aoData.push({"name": "status", "value": $("#query_role_status").val()});
	       		aoData.push({"name": "resourceId", "value": $("#query_role_resource").val()});
	       	},
	        "fnServerData": WebGate.ResourceRole.retrieveData,
	        "fnDrawCallback": function(oSettings ) {
				for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )	{
					$('td:eq(1)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+oSettings._iDisplayStart+1 );
				}
	        },
	        "aoColumns": [ {
					"mData": null,
					"bSortable": false,
					"sClass": "center",
		    		"fnRender": function (oObj) {
		    			//openSuccessAlertModalInFrame("oObj:" + JSON.stringify(oObj));
		    			//openSuccessAlertModalInFrame("oObj.aData.id:" + JSON.stringify(oObj.aData.id));
		    			var sReturn = "";
		    			if (oObj.aData.resourceChooseType == 0) {
			            	sReturn = '<label><input type="checkbox" name="sela" value="' + oObj.aData.id + '" /><span class="lbl"></span></label><input type="hidden" name="allId" value="' + oObj.aData.id + '" />';
		    			} else {
		    				sReturn = '<label><input type="checkbox" name="sela" value="' + oObj.aData.id + '" checked /><span class="lbl"></span></label><input type="hidden" name="allId" value="' + oObj.aData.id + '" />';
		    			}
		                return sReturn;
		        	}
		        }, {
	    			"sTitle": ResourceRole_List_Table_Column_ID,
	        		"mDataProp": "id"
	            }, {
	    			"sTitle": ResourceRole_List_Table_Column_Name,
	            	"mDataProp": "name"
				}, {
	    			"sTitle": ResourceRole_List_Table_Column_System,
					"mDataProp": "system.name"
				}, {
	    			"sTitle": ResourceRole_List_Table_Column_Status,
					"mDataProp": function(source, type, val){
						if(type === 'display' || type === 'filter'){
							var str = "";
							var type = source.status;
							if(type == 1){
								str = ResourceRole_Status_Using;
							}else if(type == 2){
								str = ResourceRole_Status_Useless;
							}
							return str;
						}
						return source.status;
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
		
		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
			});
				
		});

		$('[data-rel=tooltip]').tooltip();
	}
};