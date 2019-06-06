
$(function() {

    $(".on").click(function(){
        $(".on-down").slideToggle();
    });

    WebGate.RoleFunction.oTable1 = $('#table_report').dataTable( {
        "bFilter": false, //开关，是否启用客户端过滤器
        "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
        "bAutoWidth": false, //自适应宽度
        "sPaginationType": "full_numbers", //分页样式
        "bServerSide": true, //从服务器端取数据
       	"sAjaxSource": $('#ctx').val()+"/pages/role/form/functionManager?roleId=" + $('#roleId').val(),
       	"fnServerParams": function(aoData) {
       		aoData.push({"name": "code", "value": $("#query_function_code").val()});
       		aoData.push({"name": "name", "value": $("#query_function_name").val()});
       		aoData.push({"name": "pageId", "value": $("#query_function_page").val()});
       	},
        "fnServerData": WebGate.RoleFunction.retrieveData,
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
	    			if (oObj.aData.chooseType == 0) {
		            	sReturn = '<label><input type="checkbox" name="sela" value="' + oObj.aData.id + '" /><span class="lbl"></span></label><input type="hidden" name="allId" value="' + oObj.aData.id + '" />';
	    			} else {
	    				sReturn = '<label><input type="checkbox" name="sela" value="' + oObj.aData.id + '" checked /><span class="lbl"></span></label><input type="hidden" name="allId" value="' + oObj.aData.id + '" />';
	    			}
	                return sReturn;
	        	}
	        },{ 
    			"sTitle": RoleFunction_List_Table_Column_ID,
        		"mDataProp": "id"
            }, {
    			"sTitle": RoleFunction_List_Table_Column_Code,
            	"mDataProp": "code"
			}, {
    			"sTitle": RoleFunction_List_Table_Column_Name,
				"mDataProp": "name"
			}, {
    			"sTitle": RoleFunction_List_Table_Column_Path,
				"mDataProp": "path"
			}, {
    			"sTitle": RoleFunction_List_Table_Column_Page,
				"mDataProp": "page.name"
			}, 
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
});
