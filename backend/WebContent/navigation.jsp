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
	jQuery('#loadUserOverview').click(function(){
		jQuery('#page-wrapper').load('admin_user_overview.jsp');
	});
})

jQuery(function(){
	jQuery('#loadCreateUser').click(function(){
		jQuery('#page-wrapper').load('create_user.jsp');
	});
})

jQuery(function(){
	jQuery('.gameRow').click(function(){
		jQuery('#page-wrapper').load('game_overview.jsp' + $(this).data("href"));
	});
})

jQuery(function(){
	jQuery('.difficultyRow').click(function(){
		jQuery('#page-wrapper').load('game_page.jsp' + $(this).data("href"));
	});
})


</script>