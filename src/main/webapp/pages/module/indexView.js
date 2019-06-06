WebGate.Module.modiModule = function() {
	var hideId = "";
	//alert($('#ctx').val());
	// create the backdrop and wait for next modal to be triggered
	//$('body').modalmanager('loading');
	var url = $('#ctx').val()+"/pages/module/form/modiModule";
	//window.parent.showCommonModal(url);
	$("div.baseball:hidden").each(function(){
		hideId +="hideId="+$(this).attr("id")+"&";
	});
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url+"?"+hideId, commonModalCss, commonModalBodyCss);
};

WebGate.Module.more = function(url) {
	//alert($('#ctx').val());
	// create the backdrop and wait for next modal to be triggered
	//$('body').modalmanager('loading');
	//window.parent.showCommonModal(url);
	var commonModalCss = {
		"width": "900px",
        "height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

WebGate.Module.collapseUp = function (id) {
		$("#"+id+"collapse").attr("onclick",'WebGate.Module.collapseDown("'+id+'")');
		$("#"+id+"iClass").attr("class","icon-chevron-down");
		$("#"+id+"body").hide();
};
WebGate.Module.collapseDown = function (id) {
	$("#"+id+"collapse").attr("onclick",'WebGate.Module.collapseUp("'+id+'")');
	$("#"+id+"iClass").attr("class","icon-chevron-up");
	$("#"+id+"body").show();
};

WebGate.Module.showModuleIndex = function(id){
	$("#"+id).show();
	$("#"+id).attr("hideClass","true");
};

WebGate.Module.hideModuleIndex = function(id){
	$("#"+id).hide();
	$("#"+id).removeAttr("hideClass");
};
WebGate.Module.reIframe = function (id,url) {
    $("#iframe"+id).attr("src",url);

};
WebGate.Module.reTable = function(id ,tableUrl){

	var table = WebGate.Module.getTable(tableUrl);
	$("#"+id+"body").empty();
	$("#"+id+"body").append(table);
};

WebGate.Module.getTable = function (tableUrl){
    alert("tableUrl:" + tableUrl);
	var  table ="";
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		url : $('#ctx').val()+tableUrl,
		data : {
		},
		success : function(response) {
			if (response.isSuccess == "true") {
				table += '<table class="table table-striped table-bordered table-hover">';
				table += '<thead class="thin-border-bottom">';
				table += '<tr>';
				var head = response.tableHead;
				var map = response.map;
                for (var i = 0; i < head.length; i++) {
					table += '<th>';
					table +=  head[i];
					table += '</th>';
				}
				table += '</tr>';
				table += '</thead>';
				table += '<tbody>';
                for (var key in map) {
					var tableTd  = map[key];
					table += "<tr>";
                    for (var j = 0; j < tableTd.length; j++) {

						
						table += '<td>';
						table += tableTd[j];
						table += '</td>';
						
					}
					table += "</tr>";
				}
				table += '</tbody>';
				table += '</table>';
			}
			
		},
		error : function(response) {
            alert("getTableError");
		}
	});
	return table;
};

$(function() {
	
	$.ajax({
		type : "POST",
		async : false,
		"dataType": 'json',
		timeout:99999,
		url : $('#ctx').val() + "/pages/module/form/getModule",
		data : {
		},
		success : function(response) {
			if (response.isSuccess == "true") {
                if (response.sysModules != null) {
					var list  = response.sysModules;
                    for (var i = 0; i < list.length; i++) {
						var left = "";
                        left += '<div class="widget-box baseball " id="' + list[i].id + '" style="display: block;">';
						left += '<div class="block widget-header basebat span12">';
						left += '<h5>'+list[i].name+'</h5>';
						left += '<div class="widget-toolbar">';
                        /*if (list[i].more != "" && list[i].more != null) {
							left += '<a href="#" onclick="WebGate.Module.more('+list[i].more+');" data-action="settings"><i class="icon-cog"></i></a>';
						}*/
                        left += '<a href="#" onclick="WebGate.Module.reIframe(\'' + list[i].id + '\',\'' + list[i].link + '\');" data-action="reload"><i class="icon-refresh"></i></a>';
						left += '<a href="#" onclick="WebGate.Module.collapseUp(\''+list[i].id+'\');" data-action="collapse" id="'+list[i].id+'collapse"><i class="icon-chevron-up" id="'+list[i].id+'iClass"></i></a>';
						if(list[i].must == 2){
							left += '<a href="#" onclick="WebGate.Module.hideModuleIndex(\''+list[i].id+'\');"  data-action="close"><i class="icon-remove"></i></a>';
						}
						left += '</div>';
						left += '</div>';
						left += '<div class="widget-body" id="'+list[i].id+'body" >';
                        left += '<iframe id = "iframe' + list[i].id +'" src="' + list[i].link + '?ticket=' + $('#tik').val() + '" scrolling="no" width="100%" height="500 px;" frameborder="0"></iframe>'
//						left += WebGate.Module.getTable(list[i].link);
						left += '</div>';
						left += '</div>';
                        if ((i % 2) == 0) {
							$("#left").append(left);
                        } else {
							$("#right").append(left);
						}
//                        if (list[i].must == 2) {
//                            $("#" + list[i].id).hide();
//                        }
//                        alert(left)
//                        alert($("#left").html())
//                        $("#"+list[i].id+"").show()
//                        $("#right")
                    }
                }

            }
        },
        error: function (response) {
            alert("initError");
        }
    });


});