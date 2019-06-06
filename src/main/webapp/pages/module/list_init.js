
$(function() {

    $(".on").click(function(){
        $(".on-down").slideToggle();
    });

    /*var oTable1 = $('#table_report').dataTable( {
    "aoColumns": [
      { "bSortable": false },
      null, null,null,
      { "bSortable": false }
    ] } );*/
	//alert('startup');
	if (WebGate.Module.oTable1 == null) {
		//alert('create DataTable');
	WebGate.Module.oTable1 = $('#table_report').dataTable( {
//	                "bPaginate": true, //开关，是否显示分页器
//	                "bInfo": true, //开关，是否显示表格的一些信息
            "bFilter": false, //开关，是否启用客户端过滤器
//	                "sDom": "<>lfrtip<>",
//	                "bAutoWith": false,
//	                "bDeferRender": false,
//	                "bJQueryUI": false, //开关，是否启用JQueryUI风格
//	                "bLengthChange": true, //开关，是否显示每页大小的下拉框，是否允许用户自定义每页显示条数。
            "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
//	                "bScrollInfinite": false,
//	                "sScrollY": "800px", //是否开启垂直滚动，以及指定滚动区域大小,可设值：'disabled','2000px'
//	                "bSort": true, //开关，是否启用各列具有按列排序的功能
//	                "bSortClasses": true,
//	                "bStateSave": true, //开关，是否打开客户端状态记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或重新打开浏览器，之前的状态都是保存下来的-------当值为true时aoColumnDefs不能隐藏列
//	                "sScrollX": "50%", //是否开启水平滚动，以及指定滚动区域大小,可设值：'disabled','2000%'
//	                "aaSorting": [[0, "asc"]],
            //"aaSorting": [[1, "asc"]], //排序
//	                "aoColumnDefs": [{ "bVisible": false, "aTargets": [0]}] //隐藏列
//	                "sDom": '<"H"if>t<"F"if>',
            "bAutoWidth": false, //自适应宽度
            "sPaginationType": "full_numbers", //分页样式
            "bServerSide": true, //从服务器端取数据
            //"iDisplayStart":5,//只是初始化好用，只初始化一次
            //"iDisplayLength":parseInt($("#iDisplayLength").val()),//只是初始化好用，只初始化一次
            //"sAjaxDataProp": "aaData",
           	"sAjaxSource": $('#ctx').val()+"/pages/module/form/manager?now=" + new Date().getTime(), //mvc后台ajax调用接口。
           	//"fnServerData": retrieveData, //获取数据的处理函数
           	//"iTotalRecords": "1000",
           	//"sServerMethod": "POST",
            /*如果加上下面这段内容，则使用post方式传递数据*/
            /*"fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) { 
				return iStart + " to " + iEnd + " total " + iTotal; 
			},*/
           	"fnServerParams": function(aoData) {
           		aoData.push({"name": "name", "value": $("#query_module_name").val()});
           		aoData.push({"name": "must", "value": $("#query_module_must").val()});
           	},
            "fnServerData": WebGate.Module.retrieveData,
            "fnDrawCallback": function(oSettings ) {
            	/* Need to redo the counters if filtered or sorted */
    			//if ( oSettings.bSorted || oSettings.bFiltered )	{
            		//alert(JSON.stringify(oSettings));
            		//alert(oSettings._iDisplayStart);
            		//alert(oSettings._iDisplayLength);
    				for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )	{
    					$('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+oSettings._iDisplayStart+1 );
    				}
    			//}
            },
            "aoColumns": [ { 
        			"sTitle": Module_List_Table_Column_ID,
            		"mDataProp": "id"
                    /*"bSearchable": false,
                    "bSortable": false,*/
                  	//自定义列的样式
                    /*"fnRender": function (oObj) {
                    	var sReturn = '<input type="checkbox" name = "checkRows"/>';
                        return sReturn;
                        return '<a href=\"Details/' + oObj.aData[0] + '\">View</a>';
                	}*/
                }, {
        			"sTitle": Module_List_Table_Column_Name,
                	"mDataProp": "name"
				}, {
        			"sTitle": Module_List_Table_Column_Link,
					"mDataProp": "link"
				}, 
				/**
				{
        			"sTitle": Module_List_Table_Column_More,
					"mDataProp": "more"
				}, 
				**/
				{
        			"sTitle": Module_List_Table_Column_Component_Name,
					"mDataProp": "component.name"
				}, {
        			"sTitle": Module_List_Table_Column_Must,
					"mDataProp": function(source, type, val){
						if(type === 'display' || type === 'filter'){
							var str = "";
							var type = source.must;
							if(type == 1){
								str = Module_Must_Yes;
							}else if(type == 2){
								str = Module_Must_No;
							}
							return str;
						}
						return source.must;
					}
				}, 
				/**
				{
        			"sTitle": Module_List_Table_Column_Height,
					"mDataProp": "height"
				}, {
        			"sTitle": Module_List_Table_Column_Width,
					"mDataProp": "width"
				},
				**/
				{
        			"sTitle": Global_Prompt_Operating,
        			"mData": null,
        	        "aTargets": [ -1 ],
                  	//自定义列的样式
                    "fnRender": function (oObj) {
                    	//alert(oObj.aData.id);
                        var start = '<div class="hidden-phone visible-desktop btn-group">';
                        //var ok = '<button class="btn btn-mini btn-success" onclick="WebGate.Module.modObj(\'' + oObj.aData.id + '\')"><i class="icon-ok bigger-120"></i></button>';
                        //var flag = '<button class="btn btn-mini btn-warning" onclick="WebGate.Module.modObj(\'' + oObj.aData.id + '\')"><i class="icon-flag bigger-120"></i></button>';
                        var edit = Module_Button_Mod.replace("#mod#", "WebGate.Module.modObj(\'" + oObj.aData.id + "\')");
                        var trash = Module_Button_Del.replace("#del#", "WebGate.Module.delObj(\'" + oObj.aData.id + "\')");
                        var end = '</div>';
                        //alert(oObj.aData.id);
                        //return start + end;
                        //alert(start + ok + edit + trash + flag + end);
                        //return start + ok + edit + trash + flag + end;
                        return start + edit + trash + end;
                        //'<a href=\"Details/' + oObj.aData[0] + '\">View</a>';
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
		
		// 重新刷新页面Table
	//oTable1.fnDraw();
	
	/*$('table th input:checkbox').on('click' , function(){
		var that = this;
		$(this).closest('table').find('tr > td:first-child input:checkbox')
		.each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});*/

	$('[data-rel=tooltip]').tooltip();
	}
});
