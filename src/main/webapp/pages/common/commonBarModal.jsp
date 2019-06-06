<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta charset="utf-8" />
<!-- InstanceEndEditable -->
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
#commonBarModal {
	/* SET THE WIDTH OF THE MODAL 设置Modal的宽度*/
	width: 128px;
	background-color:transparent;
	border: 0px;
	box-shadow:0;
	/* CHANGE MARGINS TO ACCOMODATE THE NEW WIDTH (original = margin: -250px 0 0 -280px;) */
	margin: 350px 0 0 -10px;
}

#commonBarModal .modal-body {
	max-height: 400px;
}
</style>
</head>
<body>
	<div id="commonDragModal"></div>
	<!-- CommonModal Start ------------------------------------------------------------------------------- -->
	<div id="commonBarModal" class="modal container hide fade" tabindex="-1"  style="box-shadow:none;">
		<img alt="" height="50" width="50" src="../img/loading1.gif"/>
	</div>
	<!-- CommonModal End ------------------------------------------------------------------------------- -->

	<script type="text/javascript">
		function showCommonBarModal(commonModalCss, commonModalBodyCss) {
		
			//$('body').modalmanager('loading');
			//alert("showCommonModal:" + JSON.stringify(commonModalCss));
			//alert("showCommonModal:" + JSON.stringify(commonModalBodyCss));
		/*	if (commonModalCss != null) {
				$('#commonBarModal').css(commonModalCss);
			}
			if (commonModalBodyCss != null) {
				$('#commonBarModalBody').css(commonModalBodyCss);
			}
			*/
		//	$('#commonBarModal').on('show', function() {
			//	focusCore();
			//	$('#commonBarModal').off('show');
				$('#commonBarModal').modal({
					backdrop : true,
					keyboard : false,
					show : true
				});
			//});
		}

		function closeBarCommonModal() {
			//$('body').modalmanager('loading');
			$('#commonBarModal').modal('hide');
			/*$('#commonBarModal').modal({
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
				$('#commonBarModal').on('hidden', function() {
					callBack();
				});
			}
		}
	</script>
</body>
</html>
