<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8" />
<title>后台管理</title>
<!-- InstanceEndEditable -->
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>

	<!-- Alert Start ------------------------------------------------------------------------------- -->
	<div id="successModal" class="modal hide fade" tabindex="-1">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a> <span
				class="text-success orange"><h3>
					<i class="icon-success-sign"></i>信息
				</h3></span>
		</div>
		<div class="modal-body">
			<p id="successModal_time"></p>
			<p id="successMsg"></p>
		</div>
	</div>

	<div id="errorModal" class="modal hide fade" tabindex="-1">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a> <span
				class="text-error orange"><h3>
					<i class="icon-error-sign"></i>错误
				</h3></span>
		</div>
		<div class="modal-body">
			<p id="errorModal_time"></p>
			<p id="errorMsg"></p>
		</div>
	</div>
	<!-- Alert End ------------------------------------------------------------------------------- -->
	<script type="text/javascript">
		function alertMsg(modalId, msgId, msg) {
			$('#' + msgId).html(msg);
			$('#' + modalId).on('shown', function() {
				//alert("show callback");
				//alert("shown callback:" + modalId);
				autoCloseMsg(modalId, 5);
			});
			$('#' + modalId).modal({
				backdrop : true,
				keyboard : true,
				show : true
			});
			//alert("before autoCloseMsg");
			//autoCloseMsg(modalId, 5);
		}
		
		function autoCloseMsg(modalId, second) {
			//alert("autoCloseMsg:" + modalId);
			//alert("autoCloseMsg:" + second);
			$('#' + modalId + '_time').html(second + "秒后自动关闭页面");
			second--;
		    if(second == 0) {
		    	//alert("second is zero");
				$('#' + modalId).modal("hide");
		    } else {
		    	//alert("setTimeout:" + modalId);
		        setTimeout(function(){
		        	autoCloseMsg(modalId, second);
		        }, 1000);
		    }
		}
	</script>
</body>
</html>
