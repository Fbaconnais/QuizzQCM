jQuery("input[name='promo']").on("keyup", function() {
	var xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200)
				afficherPromos(this.response);
			else {
				if (xhr.status != 404)
				echec(xhr.status, xhr.responseText);
			}
		}
	};
	var input = document.getElementById('promo');
	var promo = input.value;
	xhr.open("GET", path + "/rest/promos/" + promo, true);
	xhr.setRequestHeader("Accept", "application/json");
	xhr.send();

});

function afficherPromos(xml) {

	var json = JSON.parse(xml);
	var props = [];
	var x;
	for (x = 0; x < json.length; x++) {
		props
				.push('<button onClick="afficherTestsPromo(\'' 
						+ json[x].id 
						+ '\')" class="btn btn-primary btn-mb btn-block" name="codepromo" id="codepromo" value="'
						+ json[x].id + '">' + json[x].id + '</button><br>');
	}
	document.getElementById("results").innerHTML = props.join("");
}