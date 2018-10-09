function confirmerChoixEpreuve(idEpreuve) {
	if ( confirm( "Êtes-vous sûr de vouloir commencer cette épreuve?" ) ) {
		var link = ("/QuizzQCM/test?id="+idEpreuve);
		console.log(link);
		document.location.href = link;
	} else {
	     alert("Ok. Tu fais bien, la question 23 est genre méga chaude. Bisous.")
	}
}