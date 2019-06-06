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
<style type="text/css">
#commonModal {
	/* SET THE WIDTH OF THE MODAL 设置Modal的宽度*/
	width: 900px;
	/* CHANGE MARGINS TO ACCOMODATE THE NEW WIDTH (original = margin: -250px 0 0 -280px;) */
	margin: 100px 0 0 -450px;
}

#commonModal .modal-body {
	max-height: 800px;
}
</style>
</head>
<body>
	<div id="commonDragModal"></div>
	<!-- CommonModal Start ------------------------------------------------------------------------------- -->
	<div id="commonModal" class="modal container hide fade" tabindex="-1">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<span class="text-success orange">
				<h3>
					<i class="icon-success-sign"></i>
				</h3>
			</span>
		</div>
		<div id="commonModalBody" class="modal-body">
			<p id="commonModalContent"></p>
		</div>
	</div>
	<!-- CommonModal End ------------------------------------------------------------------------------- -->
	
	<script type="text/javascript">
		function showCommonModal(url, commonModalCss, commonModalBodyCss) {
			
			//alert(url);
			//$('body').modalmanager('loading');
			//alert("showCommonModal:" + JSON.stringify(commonModalCss));
			//alert("showCommonModal:" + JSON.stringify(commonModalBodyCss));
			if (commonModalCss != null) {
				$('#commonModal').css(commonModalCss);
			}
			if (commonModalBodyCss != null) {
				$('#commonModalBody').css(commonModalBodyCss);
			}
			$('#commonModal').on('shown', function() {
				focusCore();
				$('#commonModal').off('shown');
			});
			$('#commonModalContent').load(url, '', function(){
				//$.fn.modalmanager.defaults.resize = true;
				$('#commonModal').modal({
					backdrop : true,
					keyboard : true,
					show : true
				});
			});
			/*$('#commonModal').modal({
				backdrop : true,
				keyboard : true,
				show : true
			});*/
		}
		

		function closeCommonModal() {
			//$('body').modalmanager('loading');
			//alert('closeCommonModal');
			$('#commonModal').modal('hide');
			/*$('#commonModal').modal({
				backdrop : true,
				keyboard : true,
				show : true
			});*/
		}

		function closeCommonModalByFunction(callBack) {
			closeCommonModal();
			console.log('cccc');
			if (callBack != null) {
				console.log('bbbb');
				$('#commonModal').on('hidden', function() {
					callBack();
				});
			}
		}
	</script>
</body>
</html>
