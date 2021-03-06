
$(function() {

    $(".on").click(function(){
        $(".on-down").slideToggle();
    });

	WebGate.Account.oTable1 = $('#table_report').dataTable( {
        "bFilter": false, //开关，是否启用客户端过滤器
        "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
        "bAutoWidth": false, //自适应宽度
        "sPaginationType": "full_numbers", //分页样式
        "bServerSide": true, //从服务器端取数据
       	"sAjaxSource": $('#ctx').val()+"/pages/account/form/manager?now=" + new Date().getTime(), //mvc后台ajax调用接口。
       	"fnServerParams": function(aoData) {
       		aoData.push({"name": "name", "value": $("#query_account_name").val()});
       		aoData.push({"name": "status", "value": $("#query_account_status").val()});
       		aoData.push({"name": "type", "value": $("#query_account_type").val()});
       		aoData.push({"name": "level", "value": $("#query_account_level").val()});
       	},
        "fnServerData": WebGate.Account.retrieveData,
        "fnDrawCallback": function(oSettings ) {
			for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )	{
				$('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+oSettings._iDisplayStart+1 );
			}
        },
        "aoColumns": [ { 
    			"sTitle": Account_List_Table_Column_ID,
        		"mDataProp": "id"
            }, {
    			"sTitle": Account_List_Table_Column_Name,
				"mDataProp": "name"
			}, {
    			"sTitle": Account_List_Table_Column_Status,
				"mDataProp": function(source, type, val){
					if(type === 'display' || type === 'filter'){
						var str = "";
						var type = source.status;
						if(type == 1){
							str = Account_Status_Using;
						}else if(type == 2){
							str = Account_Status_Useless;
						}
						return str;
					}
					return source.status;
				}
			}, {
    			"sTitle": Account_List_Table_Column_Type,
				"mDataProp": function(source, type, val){
					if(type === 'display' || type === 'filter'){
						var str = "";
						var type = source.type;
						if(type == 1){
							str = Account_Type_Local;
						}else if(type == 2){
							str = Account_Type_Sina;
						}
						return str;
					}
					return source.type;
				}
			}, {
    			"sTitle": Account_List_Table_Column_Level,
				"mDataProp": function(source, type, val){
					if(type === 'display' || type === 'filter'){
						var str = "";
						var type = source.level;
						if(type == 1){
							str = Account_Level_Admin;
						}else if(type == 2){
							str = Account_Level_User;
						}
						return str;
					}
					return source.level;
				}
			}, {
    			"sTitle": Global_Prompt_Operating,
    			"mData": null,
    	        "aTargets": [ -1 ],
              	//自定义列的样式
                "fnRender": function (oObj) {
                    var start = '<div class="hidden-phone visible-desktop btn-group">';
                    var edit = '<button class="btn btn-mini btn-warning" title="修改" onclick="WebGate.Account.modObj(\'' + oObj.aData.id + '\')"><i class="icon-edit bigger-120"></i></button>';
                    var trash = '<button class="btn btn-mini btn-danger" title="删除" onclick="WebGate.Account.delObj(\'' + oObj.aData.id + '\')"><i class="icon-trash bigger-120"></i></button>';
                    var roleList = '<button class="btn btn-mini btn-success" title="角色列表" onclick="WebGate.Account.manageRoleList(\'' + oObj.aData.id + '\')"><i class="icon-list bigger-120"></i></button>';
                    var reset = '<button class= "btn btn-mini btn-danger" title="重置密码" onclick="WebGate.Account.resetPassword(\'' + oObj.aData.id + '\')"><i class="icon-refresh bigger-120"></i></button>';
                    var end = '</div>';
                    return start + edit + trash + roleList + reset + end;
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
