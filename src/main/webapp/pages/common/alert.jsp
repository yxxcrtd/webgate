<script type="text/javascript">
//alert('alert jsp : ' + '${form.isSuccess}');
//alert('alert jsp : ' + '${form.msg}');
<c:if test="${form.isSuccess == 'true' && form.msg != null && form.msg != ''}">
	//window.parent.alertMsg('successModal', 'successMsg', '${form.msg}');
	openSuccessAlertModalInFrame('${form.msg}');
</c:if>

<c:if test="${form.isSuccess == 'false'}">
	//window.parent.alertMsg('errorModal', 'errorMsg', '${form.msg}');
	openErrorAlertModalInFrame('${form.msg}');
</c:if>
</script>