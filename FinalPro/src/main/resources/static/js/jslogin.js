function Loginbutton() {
	var usernameReg = document.getElementById("usernamejs").value;
	var passwordReg = document.getElementById("passjs").value;
	var count = 1;
	/*if (usernameReg != "" && passwordReg != "" && passwordcheckReg == passwordReg && emailReg.includes("@") && dataNascitaReg != "" && indirizzoReg != "")
		alert("Tutto ok ")
	else {
		alert("Compila tutti i campi come devono essere!!!")
	}*/
	
	if (usernameReg == "" || passwordReg == ""){
		count--;
		}
	if (count == 1){
		
	fetch('/logininfo', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ Username: usernameReg, Password: passwordReg})
	})
		.then(response => response.text())
		.then(data => {
			console.log("risposta dal server : " + data);
		})
		.catch(error => {
			console.error("Errore nella Richiesta : " + error);
		});
}
}
