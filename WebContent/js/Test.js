	var temps;
	
	window.onload = function(){
		verifTimer();
		var temps;
		console.log(temps);
		if (temps != undefined) {
			rebour(temps);
		} else {
			rebour(3600);	
		}
            }

    function recuperationQuestion(idQuestion) {
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
    				
    			}
    			else
    				echec(xhr.status, "Erreur de statut. Veuillez contacter l'administrateur. Merci. Vraiment. Ca me fait plaisir.");
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
    	setTimeout(updateTimer(restant), 5000);
    			}else{
    	alert("Fin du test, vos résultats ont été enregistrés et vous pourrez les consulter d'ici quelques minutes. Cordialement, la direction.");
    	
    			}
    	}
    
    function verifTimer() {
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
    				recupTimer(this.response);
    			}
    			else
    				echec(xhr.status, "Erreur de statut lors de la récupération du temps restant. Veuillez contacter l'administrateur. Merci. Vraiment. Ca me fait plaisir.");
    		}
    	};
	 
    	xhr.open("GET", path+'epreuve/'+idEpreuve+'/get', true);
    	xhr.send();
    }
    
    function recupTimer(xml) {
    	var json = JSON.parse(xml);
		console.log(json);
		if (json != undefined) {
	    	var temps = json.tempsEcoule;
	    	document.getElementById("timer").innerHTML = tps;
		} else {
			
		} 
    }
    
    function updateTimer(i) {
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
        				succes("Temps mis à jour.")
        			}
        			else
        				echec(xhr.status, "Erreur de statut. Veuillez contacter l'administrateur. Merci. Vraiment. Ca me fait plaisir.");
        		}
        	};
        	xhr.open("PUT", path+'epreuve/'+idEpreuve+'/'+i+'/timer', true);
        	xhr.send();
    	}

