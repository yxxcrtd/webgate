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
#confirmModal {
	/* SET THE WIDTH OF THE MODAL 设置Modal的宽度*/
	width: 600px;
	/* CHANGE MARGINS TO ACCOMODATE THE NEW WIDTH (original = margin: -250px 0 0 -280px;) */
	margin: 100px 0 0 -450px;
}

#confirmModal .modal-body {
	max-height: 200px;
}
</style>
</head>
<body>

	<!-- ConfirmModal Start ------------------------------------------------------------------------------- -->
	<div id="confirmModal" class="modal hide fade" tabindex="-1">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a> <span
				class="text-success orange"><h3>
					<i class="icon-success-sign"></i>确认
				</h3></span>
		</div>
		<div id="confirmModalBody" class="modal-body">
			<p id="confirmModalContent"></p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">否</a>
			<a href="#" class="btn btn-primary" onclick="closeConfirmModalWithYes();">是</a>
		</div>
	</div>
	<!-- ConfirmModal End ------------------------------------------------------------------------------- -->
	
	<script type="text/javascript">
		var confirmed = false;
	
		function showConfirmModal(msg, callback, confirmModalCss, confirmModalBodyCss) {
			//alert("showConfirmModal:" + msg);
			//alert("confirmModalCss:" + confirmModalCss);
			//alert("confirmModalBodyCss:" + confirmModalBodyCss);
			//alert("callback:" + callback);
			//alert("confirmed:" + confirmed);
			if (callback != null) {
				$('#confirmModal').on('hide', function() {
					//alert("hidden callback executing " + new Date());
					//alert("confirmed:" + confirmed);
					if (confirmed == true) {
						//alert("callback function:" + callback);
						callback();
						confirmed = false;
					}
					$('#confirmModal').off('hide');
				});
			}
			if (confirmModalCss != null) {
				$('#confirmModal').css(confirmModalCss);
			}
			if (confirmModalBodyCss != null) {
				$('#confirmModalBody').css(confirmModalBodyCss);
			}
			$('#confirmModalContent').html(msg);
			$('#confirmModal').modal({
				backdrop : true,
				keyboard : true,
				show : true
			});
		}

		function closeConfirmModalWithYes() {
			confirmed = true;
			$('#confirmModal').modal('hide');
		}

		function closeConfirmModal() {
			$('#confirmModal').modal('hide');
		}
	</script>
</body>
</html>
