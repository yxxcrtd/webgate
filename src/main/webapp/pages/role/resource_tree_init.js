
var setting = {
	view:{
		selectedMulti: false
	},
	check:{
		enable: true
	},
	async: {
		enable: true,
		url:$('#ctx').val() + "/pages/role/form/getResourceZTree",
		autoParam:["id", "sysId", "roleId"],
		dataFilter: WebGate.RoleResource.filter
	},
	edit: {
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false
	},
	callback: {
	}
};

var root = [ {
	id : "root",
	name : "系统资源管理",
	isRoot : "true",
	isParent : true,
	icon : $('#ctx').val() + "/images/configration1.gif",
	open : true,
	nocheck : true
} ];

$(document).ready(function(){
	setting.async.url=$('#ctx').val() + "/pages/role/form/getResourceZTree?roleId=" + $('#roleId').val();
	$.fn.zTree.init($("#treeDemo"), setting, root);
	WebGate.RoleResource.zTree = $.fn.zTree.getZTreeObj("treeDemo");
});
