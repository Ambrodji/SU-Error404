<script type="text/javascript">
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

jQuery(function(){
	jQuery('.userRow').click(function(){
		jQuery('#page-wrapper').load('user_overview.jsp' + $(this).data("href"));
	});
})

jQuery(function(){
	jQuery('#editProfileBtn').click(function(){
		jQuery('#page-wrapper').load('user_edit.jsp' + $(this).data("href"));
	});
})
</script>