<script type="text/javascript">
jQuery(function(){
	jQuery('#loadDash').click(function(){
		jQuery('#page-wrapper').load('dashboard.jsp');
	});
})

jQuery(function(){
	jQuery('#loadHist').click(function(){
		jQuery('#page-wrapper').load('history.jsp');
	});
})

jQuery(function(){
	jQuery('#loadCreateUser').click(function(){
		jQuery('#page-wrapper').load('createUserNew.jsp');
	});
})

</script>