function newTest(){
	var props = [];
	props.push('<p><h3> Libellé du test</h3></p><div class="form-row"><input type="text" class="form-control col col-lg-12 col-md-12 col-xs-12 " id="test" placeholder="Libellé du test"></div><br><br>');
	document.getElementById("results").innerHTML = props.join("");
	props.push('<div class="form-row"><input type="button" value="Valider le test" class="btn btn-success btn-mb btn-block" onClick="verifTest()"></div>');
}

function verifTest() {
	
}