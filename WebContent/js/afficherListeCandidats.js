jQuery("input[name='nom']").on("input", function() {
	var xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200)
				afficherCandidats(this.response);
			else {
				if (xhr.status != 404) {
					echec(xhr.status, xhr.responseText);
				}
			}
		}
	};
	var input = document.getElementById('nom');
	var nommail = input.value;
	console.log(nommail);
	xhr.open("GET", path + "/rest/users/recherche/" + nommail, true);
	xhr.setRequestHeader("Accept", "application/json");
	xhr.send();

});

function afficherCandidats(xml) {
	var json = JSON.parse(xml);
	var props = [];
	console.log(json);
	var x;
	props.push('<div class="col col-lg-9 offset-lg-3">')
	for (x = 0; x < json.length; x++) {
		props
				.push('<button onClick="afficherTestsUtil('
						+ json[x].idUtilisateur
						+ ')" class="form-row btn btn-primary btn-mb btn-block" name="idutil" id="idutil" value="'
						+ json[x].idUtilisateur + '">' + json[x].nom + '  '
						+ json[x].prenom + ' - ' + json[x].email
						+ ' </button><br>');
	}
	props.push('</div>')
	document.getElementById("results").innerHTML = props.join("");
}