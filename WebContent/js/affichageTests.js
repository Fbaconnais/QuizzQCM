function afficherTestsPromo(id) {
	var xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200)
				afficherListeTestPromo(this.response);
			else
				echec(xhr.status, xhr.responseText);
		}
	};
	xhr.open("GET", path + "/rest/epreuve/promos/" + id, true);
	xhr.setRequestHeader("Accept", "application/json");
	xhr.send();

}

function afficherListeTestPromo(xml) {
	var json = JSON.parse(xml);
	var props = [];
	var props2 = [];
	var codePromo = '' + document.getElementById("codepromo").value;
	console.log(codePromo);
	props2.push('<h2>Résultats pour : ' + codePromo+'</h2>');
	var resultats = json.mapIdTestResultatPromo;

	props
			.push('<table class="table table-bordered"><thead><tr><th scope="col" style="width:200px;">Test</th><th scope="col" style="width:250px;">Nom</th>');
	props
			.push('<th scope="col" style="width:250px;">Prenom</th><th scope="col" style="width:80px;">Note</th> <th scope="col" style="width:150px;">Niveau</th></tr></thead><tbody>');
	$.each(resultats,function(k, v) {
	
		$.each(v,function(kk, vv) {
			props.push('<tr>');
			if (kk == 0) {
			
			props.push('<th scope="row" rowspan="'+ v.length+'" ><div class="card" style="width: 200px;"><img class="card-img-top" src="'
							+ path+ vv.test.logoLangage+'" alt="Logo du langage de programmation"><div class="card-body"><h5 class="card-title">');
			props.push(vv.test.libelle+'</div></div></th>');
			}
			console.log(vv);
			props.push('<td>'+vv.candidat.nom+'</td>');
			props.push('<td>'+vv.candidat.prenom+'</td>');
			props.push('<td>'+vv.noteCandidat+'/20</td>');
			if (vv.niveauCandidat == 'Non acquis'){
			props.push('<td class="table-danger">'+vv.niveauCandidat+'</td>');
			} else if (vv.niveauCandidat == 'Acquis'){
				props.push('<td class="table-success">'+vv.niveauCandidat+'</td>');
			} else {
				props.push('<td class="table-warning">'+vv.niveauCandidat+'</td>');
			}
			
			});
			props.push('</tr>');
					});
	props.push('</tbody></table>');
	document.getElementById("afficherinfos").innerHTML = props2.join("");
	document.getElementById("results").innerHTML = props.join("");
}

function afficherTestsUtil(id) {
	var xhr = createXHR();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200)
				afficherListeTestUtil(this.response);
			else
				echec(xhr.status, xhr.responseText);
		}
	};
	xhr.open("GET", path + "/rest/epreuve/candidat/" + id, true);
	xhr.setRequestHeader("Accept", "application/json");
	xhr.send();
}

function afficherListeTestUtil(xml) {
	var json = JSON.parse(xml);
	var props = [];
	var props2 = [];

	props2.push('<div class="row justify-content-center"><h3>'
			+ json.epreuves[0].candidat.prenom + ' '
			+ json.epreuves[0].candidat.nom + '</h3></div>');

	var x;
	for (x = 0; x < json.epreuves.length; x++) {

		if (x % 4 == 0) {
			props.push('<div class="row"');
		}
		props
				.push('<div class="col-lg-3 col-md-4 col-xs-6"><div class="card" style="width: 25%;"><img class="card-img-top"	src="'
						+ path
						+ json.epreuves[x].test.logoLangage
						+ '" alt="Logo du langage de programmation"><div class="card-body"><h5 class="card-title">'
						+ json.epreuves[x].niveauCandidat
						+ '</h5><p class="card-text"> Note : '
						+ json.epreuves[x].noteCandidat
						+ ' /20</p></div></div></div>');
		if (x % 4 == 3) {
			props.push('</div>');
		}
	}
	document.getElementById("tests").innerHTML = props.join("");
	document.getElementById("afficherinfos").innerHTML = props2.join("");
}