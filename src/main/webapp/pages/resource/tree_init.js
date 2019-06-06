
var setting = {
	async: {
		enable: true,
		url:$('#ctx').val() + "/pages/resource/form/getMenuZTree",
		autoParam:["id", "sysId"],
		dataFilter: WebGate.Resource.filter
	},
	edit: {
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false
	},
	callback: {
		onRightClick: WebGate.Resource.OnRightClick,
		beforeDrop: WebGate.Resource.BeforeDrop
	}
};

var root = [ {
	id : "root",
	name : "系统资源管理",
	isRoot : "true",
	isParent : true,
	icon : $('#ctx').val() + "/images/configration1.gif",
	open : true
} ];

$(document).ready(function(){

    $(".on").click(function(){
        $(".on-down").slideToggle();
    });

    setting.async.url=$('#ctx').val() + "/pages/resource/form/getMenuZTree?rootId=" + $('#resourceId').val();
	$.fn.zTree.init($("#treeDemo"), setting, root);
	WebGate.Resource.zTree = $.fn.zTree.getZTreeObj("treeDemo");
	WebGate.Resource.rMenu = $("#rMenu");
	WebGate.Resource.shadow = $("#shadow");
});
