
jQuery(document).ready(function() {
	jQuery("#formStep").validate({
		rules: {
			"computerName": {
				"required": true,
				"minlength": 2,
				"maxlength": 60000
			},
			"introduced": {
				"required": true,
				"maxlength": 255
			},
			"discontinued": {
				"required": true,
				"maxlength": 255
			},
			"companyId": {
				"required": true
			}
		},
		messages: {
      		computerName: {
				required: "Please enter the name of the computer",
				minlength:"The name must be at least 2 characters long",
				maxlength:"The name must be shorter"
			},
      		introduced: {
				required: "Please enter the date of its introduction",
				maxlength: "The date must be valid"
			},
      		discontinued: {
        		required: "Please enter the date of its discontinuity",
        		maxlength: "The date must be valid"
      		},
    	},
		submitHandler: function(form) {
      		form.submit();
    	}
	})
});