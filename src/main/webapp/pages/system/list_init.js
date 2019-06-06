
$(function() {

    $(".on").click(function(){
        $(".on-down").slideToggle();
    });

	WebGate.System.oTable1 = $('#table_report').dataTable( {
        "bFilter": false, //开关，是否启用客户端过滤器
        "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
        "bAutoWidth": false, //自适应宽度
        "sPaginationType": "full_numbers", //分页样式
        "bServerSide": true, //从服务器端取数据
       	"sAjaxSource": $('#ctx').val()+"/pages/system/form/manager?now=" + new Date().getTime(), //mvc后台ajax调用接口。
       	"fnServerParams": function(aoData) {
       		aoData.push({"name": "code", "value": $("#query_system_code").val()});
       		aoData.push({"name": "name", "value": $("#query_system_name").val()});
       		aoData.push({"name": "status", "value": $("#query_system_status").val()});
       	},
        "fnServerData": WebGate.System.retrieveData,
        "fnDrawCallback": function(oSettings ) {
			for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )	{
				$('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+oSettings._iDisplayStart+1 );
			}
        },
        "aoColumns": [ { 
    			"sTitle": System_List_Table_Column_ID,
        		"mDataProp": "id"
            }, {
    			"sTitle": System_List_Table_Column_Code,
            	"mDataProp": "code"
			}, {
    			"sTitle": System_List_Table_Column_Name,
				"mDataProp": "name"
			}, {
    			"sTitle": System_List_Table_Column_Status,
				"mDataProp": function(source, type, val){
					if(type === 'display' || type === 'filter'){
						var str = "";
						var type = source.status;
						if(type == 1){
							str = System_Status_Using;
						}else if(type == 2){
							str = System_Status_Useless;
						}else if(type == 3){
							str = System_Status_Deprecated;
						}
						return str;
					}
					return source.status;
				}
			}, {
    			"sTitle": Global_Prompt_Operating,
    			"mData": null,
    	        "aTargets": [ -1 ],
              	//自定义列的样式
                "fnRender": function (oObj) {
                    var start = '<div class="hidden-phone visible-desktop btn-group">';
                    var edit = System_Button_Mod.replace("#mod#", "WebGate.System.modObj(\'" + oObj.aData.id + "\')");
                    var trash = System_Button_Del.replace("#del#", "WebGate.System.delObj(\'" + oObj.aData.id + "\')");
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
});
