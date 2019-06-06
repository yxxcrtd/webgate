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
	<div id="infoMessage" class="alert alert-info hide">
		<span id="infoMessageContent"></span>
		<a class="close" onclick="$(this).parent().hide();">&times;</a>
	</div>
	<div id="successMessage" class="alert alert-success hide">
		<span id="successMessageContent"></span>
		<a class="close" onclick="$(this).parent().hide();">&times;</a>
	</div>
	<div id="errorMessage" class="alert alert-error hide">
		<span id="errorMessageContent"></span>
		<a class="close" onclick="$(this).parent().hide();">&times;</a>
	</div>
	<!-- Alert End ------------------------------------------------------------------------------- -->
	<script type="text/javascript">
		function ajaxAlertInfoMsg(msg, hide) {
			if (hide == true) {
				$(".alert").hide();
			}
			$("[id=infoMessageContent]:last").html(msg);
			$("[id=infoMessage]:last").show();
		}
		function ajaxAlertSuccessMsg(msg, hide) {
			if (hide == true) {
				$(".alert").hide();
			}
			$("[id=successMessageContent]:last").html(msg);
			$("[id=successMessage]:last").show();
		}
		function ajaxAlertErrorMsg(msg, hide) {
			if (hide == true) {
				$(".alert").hide();
			}
			$("[id=errorMessageContent]:last").html(msg);
			$("[id=errorMessage]:last").show();
		}
	</script>
</body>
</html>
