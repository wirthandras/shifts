function UpdateStatus(field, id, employee) {
	$.ajax({
				url : '/api/employees/',
				method : 'POST',
				dataType : 'json',
				async : true,
				cache : false,
				timeout : 5000,
				data : {
					"dayId" : id,
					"employee": employee
				},
				success : function(responses) {
					$(dayId).val(id);
					$(field).empty();
					responses.result.forEach(function(response) {
						$(field).append("<p>" + response.type + "</p>");
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console
							.log("/api/employees/ request failed ... HTTP status code: "
									+ XMLHttpRequest.status);
					$("#userInfo")
							.text(
									"We are sorry... We are unable to fetch you the list of courses");
					$(field).empty();
				}
			});
};
