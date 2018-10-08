	window.onload = function(){
	rebour(3600);
            }

    function recuperationQuestion(idQuestion,idEpreuve) {
    	    var xhr; 
    	    try {  xhr = new ActiveXObject('Msxml2.XMLHTTP');   }
    	    catch (e) 
    	    {
    	        try {   xhr = new ActiveXObject('Microsoft.XMLHTTP'); }
    	        catch (e2) 
    	        {
    	           try {  xhr = new XMLHttpRequest();  }
    	           catch (e3) {  xhr = false;   }
    	         }
    	    }
    	  
    	    xhr.onreadystatechange  = function() 
    	    { 
    	       if(xhr.readyState  == 4)
    	       {
    	        if(xhr.status  == 200) 
    	        	traitementQuestion(this.response);
    	        else
    	        	console.log("Erreur de statut!");
    	        }
    	    }; 
    	 
    	   xhr.open("GET", path+'question/'+idQuestion+'/'+idEpreuve+'/get',  true); 
    	   xhr.send(); 
    }
    
    function traitementQuestion(xml) {
    	var json = JSON.parse(xml);
		var props = [];
		var txt;
		var idEpreuve;
		console.log(json);
		txt = json.question.enonce
    	document.getElementById("test").innerHTML = txt;
		idEpreuve = document.getElementById("epreuve");
		console.log(idEpreuve.value);
		for (let proposition of json.question.propositions) {
			if (json.reponsetirages[proposition.idProposition] != undefined ) {
					txt = '<input type="checkbox" id="'+proposition.idProposition+'" onClick="gestionPropositionCandidat('+proposition.idProposition+','+json.question.idQuestion+','+idEpreuve.value+')" checked> <p>'+proposition.enonce +'</p><br>';
			} else {
					txt = '<input type="checkbox" id="'+proposition.idProposition+'" onClick="gestionPropositionCandidat('+proposition.idProposition+','+json.question.idQuestion+','+idEpreuve.value+')"> <p>'+proposition.enonce +'</p><br>';
			}
			console.log(proposition);
			props.push(txt);
		}
		document.getElementById("propositions").innerHTML = props.join("");
    }
    
    function recapTest() {
    	var txt;
    	var nbQrep;
    	var boutonFin;
    	txt = 'Vous avez répondu à '+nbQrep+' questions. Êtes-vous sûr de vouloir valider votre test?';
    	boutonFin = '<button type=button onClick="">Terminer le test</button>';
    	document.getElementById("test").innerHTML = txt;
    	document.getElementById("propositions").innerHTML = boutonFin;
    	
    }
    
    function gestionPropositionCandidat(idProposition, idQuestion, idEpreuve){
    	var xhr;
    	
    	try {
    		xhr = new ActiveXObject('Msxml2.XMLHTTP');
    		}
    	catch (e)
    		{
    			try {
    				xhr = new ActiveXObject('Microsoft.XMLHTTP');
    				}
    				catch (e2)
    					{
    						try {
    							xhr = new XMLHttpRequest();
    							}
    						catch (e3) { xhr = false; }
    					}
    		}
	  
    	xhr.onreadystatechange = function() {
    		if(xhr.readyState == 4) {
    			if(xhr.status == 200){
    				succes("Reponse enregistrée!")
    			}
    			else
    				echec(xhr.status, "Erreur de statut!");
    		}
    	};
	 
    	xhr.open("GET", path+'reponse/'+idProposition+'/'+idQuestion+'/'+idEpreuve+'/gestion', true);
    	xhr.send();
    }
    
    function succes(reponse){
		document.getElementById("succes").innerHTML=reponse;
		document.getElementById("echec").innerHTML="";
	}
	function echec(codeReponse, reponse){
		document.getElementById("echec").innerHTML=reponse;
		document.getElementById("succes").innerHTML="";
	}
    
    function rebour(tps){
    	if (tps>0) {
    		var heure = Math.floor(tps/3600);
    		if(heure >= 24){
    		var jour = Math.floor(heure/24);
    		var moins = 86400*jour;
    		var heure = heure-(24*jour);
    		}else{
    		var jour = 0;
    		var moins = 0;
    		}
    	moins = moins+3600*heure;
    	var minutes = Math.floor((tps-moins)/60);
    	moins = moins + 60*minutes;
    	var secondes = tps-moins;
    	minutes = ((minutes < 10) ? "0" : "") + minutes;
    	secondes = ((secondes < 10) ? "0" : "") + secondes;
    	document.getElementById("compteRebour_affiche").innerHTML = '<button type="button" class="btn btn-primary">'+heure+':'+minutes+':'+secondes+'</button>';
    	var restant = tps-1;
    	setTimeout("rebour("+restant+")", 1000);
    			}else{
    	alert("Fin du test, merci de votre participation.");
    	}
    	}
