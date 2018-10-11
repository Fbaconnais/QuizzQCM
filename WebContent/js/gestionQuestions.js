function newQuestion() {
	var props = [];
	var divaafficher = document.getElementById('themesAafficher');

	divaafficher.style.display = 'inline';
	props.push('</select></div></div>');
	props
			.push('<p><h3> Enoncé de la question</h3></p><div class="form-row"><input type="text" class="form-control col col-lg-12 col-md-12 col-xs-12 " id="enonce" placeholder="enoncé de la question"></div><br><br>');
	props
			.push('<div class="row"><h4> Propositions (vous n\'êtes pas obligé d\'en remplir 5, cocher la(les) bonne(s) réponses)</h4></div><br>');
	var x;
	for (x = 1; x < 6; x++) {
		props
				.push('<div class="form-row"><label for="prop'
						+ x
						+ '" class="col col-lg-2 col-md-2 col-xs-3 col-form-label"> Proposition '
						+ x + '</label>');
		props
				.push('<input type="checkbox" id="cbox'
						+ x
						+ '" class="form-control col col-lg-1 col-md-1 col-xs-1"><input type="text" name="prop'
						+ x + '" placeholder="proposition ' + x + '" id="prop'
						+ x
						+ '" class="col col-lg-9 col-md-9 col-xs-8"></div><br>');
	}
	props
			.push('<div class="form-row"><label for="points'
					+ x
					+ '" class="col col-lg-6 col-md-6 col-xs-8 col-form-label"> Points pour cette question(pas de chiffre à virgule)</label>');
	props
			.push('<input type="text" name="points" placeholder="points" id="points" class="col col-lg-3 col-md-3 col-xs-3"></div><br>');

	props
			.push('<div class="form-row" id="boutons"><input type="button" value="Valider la question" class="btn btn-success btn-mb btn-block" onClick="checkPropositions()"></div>');
	document.getElementById("results").innerHTML = props.join("");
}

function checkPropositions() {
	document.getElementById("echec").innerHTML = "";
	document.getElementById("succes").innerHTML = "";
	var enonce = document.getElementById('enonce').value;

	var prop1 = document.getElementById('prop1').value;
	var prop2 = document.getElementById('prop2').value;
	var prop3 = document.getElementById('prop3').value;
	var prop4 = document.getElementById('prop4').value;
	var prop5 = document.getElementById('prop5').value;
	var check1 = document.getElementById('cbox1').checked;
	var check2 = document.getElementById('cbox2').checked;
	var check3 = document.getElementById('cbox3').checked;
	var check4 = document.getElementById('cbox4').checked;
	var check5 = document.getElementById('cbox5').checked;
	var themesChoisis = document.getElementById('listeThemes').selectedOptions;

	var points = document.getElementById('points').value;

	var z;
	var propositions = '' + prop1 + '---' + prop2 + '---' + prop3 + '---'
			+ prop4 + '---' + prop5;
	var bonnesReponses = '' + check1 + '---' + check2 + '---' + check3 + '---'
			+ check4 + '---' + check5;
	var Themes = '';
	for (z = 0; z < themesChoisis.length; z++) {
		Themes = Themes + themesChoisis[z].value + '---';
	}
	data = "&enonce=" + encodeURIComponent(enonce) + "&propositions="
			+ encodeURIComponent(propositions) + "&bonnesReponses="
			+ encodeURIComponent(bonnesReponses) + "&themes="
			+ encodeURIComponent(Themes) + "&points="
			+ encodeURIComponent(points);
	var xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				succes("Requete exécutée");
				document.getElementById('themesAafficher').style.display = 'none';
				document.getElementById('results').innerHTML = "";
			}

			else {
				echec(xhr.status, "Echec de la création");
			}
		}
	};
	xhr.open("PUT", path + "/rest/question/add/", true);
	xhr.setRequestHeader("Accept", "application/json");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(data);
}
