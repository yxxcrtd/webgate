
WebGate.Module.showModile = function(id,num) {
	if(num == "1")
	{
        $("button.btn-mini[buttonId="+id+"]").attr("title","隐藏该模块到首页");
		$("button.btn-mini[buttonId="+id+"]").attr("onclick","WebGate.Module.showModile('"+id+"','2')");
		showModuleInFrame("WebGate.Module.showModuleIndex(id)",id);
		saveModuleInFrame("saveLayer()");
	}
	if(num == "2")
	{
        $("button.btn-mini[buttonId="+id+"]").attr("title","添加该模块到首页");
		$("button.btn-mini[buttonId="+id+"]").attr("onclick","WebGate.Module.showModile('"+id+"','1')");
		showModuleInFrame("WebGate.Module.hideModuleIndex(id)",id);
		saveModuleInFrame("saveLayer()");
	}
	
};

WebGate.Module.retrieveData = function(sSource, aoData, fnCallback) {
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


$(function() {

    $(".on").click(function(){
        $(".on-down").slideToggle();
    });

    WebGate.Module.oTable1 = $('#table_report').dataTable( {
        "bFilter": false, //开关，是否启用客户端过滤器
        "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
        "bAutoWidth": false, //自适应宽度
        "sPaginationType": "full_numbers", //分页样式
        "bServerSide": true, //从服务器端取数据
        "sAjaxSource": $('#ctx').val()+"/pages/module/form/modiModuleManager?now=" + new Date().getTime(), //mvc后台ajax调用接口。
        "fnServerParams": function(aoData) {
            aoData.push({"name": "hideIds", "value": $("#hideIds").val()});
        },
        "fnServerData": WebGate.Module.retrieveData,
        "fnDrawCallback": function(oSettings ) {
            for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )	{
                $('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+oSettings._iDisplayStart+1 );
            }
        },
        "aoColumns": [ {
            "sTitle": "序号",
            "mDataProp": "id"
        }, {
            "sTitle": "模块名称",
            "mDataProp": "name"
        }, {
            "sTitle": "是否必选",
            "mData": null,
            "aTargets": [ -1 ],
            //自定义列的样式
            "fnRender": function (oObj) {
                var value = "";
                if (oObj.aData.must == "1") {
                    value = "必选模块";
                }
                if (oObj.aData.must == "2") {
                    value = "非必选模块";
                }
                return value;
            }
        }, {
            "sTitle": Global_Prompt_Operating,
            "mData": null,
            "aTargets": [ -1 ],
            //自定义列的样式
            "fnRender": function (oObj) {
                var start = '<div class="hidden-phone visible-desktop btn-group">';
                var add = '<button class= "btn btn-mini btn-danger" buttonId="'+ oObj.aData.id +'" title="添加该模块到首页" onclick="WebGate.Module.showModile(\'' + oObj.aData.id + '\', \'1\')"><i class="icon-refresh bigger-120"></i></button>';
                var remove = '<button class= "btn btn-mini btn-danger" buttonId="'+ oObj.aData.id +'" title="隐藏该模块到首页" onclick="WebGate.Module.showModile(\'' + oObj.aData.id + '\', \'2\')"><i class="icon-refresh bigger-120"></i></button>';
                var end = '</div>';
                console.log("oObj.aData.hideShow:" + oObj.aData.hideShow);
                if(oObj.aData.hideShow != null) {
                    if (oObj.aData.hideShow == 'trueShow' && oObj.aData.must == '2') {
                        return start + add + end;
                    } else if (oObj.aData.hideShow != 'trueShow' && oObj.aData.must == '2') {
                        return start + remove + end;
                    } else {
                        return start + end;
                    }
                } else {
                    return start + end;
                }
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
