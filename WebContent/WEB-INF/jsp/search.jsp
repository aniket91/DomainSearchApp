<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Domain Search Engine</title>
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" type="text/css" media="all" /> 
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>
</head>
<body>

<input type="text" id="domainFieldId" name="domainsearch" placeholder="Search domain names.."/>
<button onclick="processDomains()">Submit</button>

</body>
</html>


<script>
$( "#domainFieldId" ).autocomplete({
	minLength: 2,
	source: function(request,response) {
		var searchTerm = request.term;
		console.log(searchTerm);
		var result = getDomains(searchTerm, function(result) {
			response(result);
		});


	}
});

processDomains = function() {
	var searchTerm = $("#domainFieldId").val();
	console.log("Domain str: " + searchTerm);
	var result = getDomains(searchTerm, function(result){
		if(result.length == 1) {
			console.log("Single result: " + result[0])
		}
		else {
			console.log("Multiple results : " + result);
		}
	});

}

getDomains = function(searchTerm, callback) {
	$.ajax("/DomainSearchApp/search/" + searchTerm + "/end", {
		success: function(data){
			console.log("success: " + JSON.stringify(data));
			callback(JSON.parse(data));
		},
		error: function(data) {
			console.log("error: " + JSON.stringify(data));
			callback([]);
		}
	});
}
</script>