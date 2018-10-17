function UpdateCar(field, id) {
	$.ajax({
				url : '/api/cars/',
				method : 'POST',
				dataType : 'json',
				async : true,
				cache : false,
				timeout : 5000,
				data : {
					"dayId" : id
				},
				success : function(responses) {
					$(field).empty();
					responses.result.forEach(function(response) {
						$(field).append("<p>" + response + "</p>");
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console
							.log("/api/cars/ request failed ... HTTP status code: "
									+ XMLHttpRequest.status);
					$("#userInfo")
							.text(
									"We are sorry... We are unable to fetch you the list of days");
					$(field).empty();
				}
			});
};