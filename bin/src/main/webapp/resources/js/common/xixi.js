jQuery(document).ready(function(){
	/* This code is executed after the DOM has been completely loaded */

	/* Changing thedefault easing effect - will affect the slideUp/slideDown methods: */
	jQuery.easing.def = "easeOutBounce";

	/* Binding a click event handler to the links: */
	jQuery('li.nav-top-item a').click(function(e){
	
		/* Finding the drop down list that corresponds to the current section: */
		var dropDown = $(this).parent().next();
		/* Closing all other drop down sections, except the current one */
		jQuery('.dropdown').not(dropDown).slideUp('fast');
		dropDown.slideToggle('fast');
		
		/* Preventing the default event (which would be to navigate the browser to the link's address) */
		e.preventDefault();
	})
	
});