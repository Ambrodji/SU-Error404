<script type="text/javascript">
jQuery(function(){
	jQuery('#loadDash').click(function(){
		jQuery('#page-wrapper').load('dashboard.jsp');
	});
})

jQuery(function(){
	jQuery('#loadUserOverview').click(function(){
		jQuery('#page-wrapper').load('user_list.jsp');
	});
})

jQuery(function(){
	jQuery('#loadCreateUser').click(function(){
		jQuery('#page-wrapper').load('create_user.jsp');
	});
})

jQuery(function(){
	jQuery('#profileBtn').click(function(){
		jQuery('#page-wrapper').load('user_overview.jsp' + $(this).data("href"));
	});
})

</script>