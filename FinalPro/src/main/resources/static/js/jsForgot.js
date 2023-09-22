function Forgotbutton() {
	var usernameReg = document.getElementById("usernamejs").value;
	var emailReg = document.getElementById("email").value;
	var count = 1;
	/*if (usernameReg != "" && passwordReg != "" && passwordcheckReg == passwordReg && emailReg.includes("@") && dataNascitaReg != "" && indirizzoReg != "")
		alert("Tutto ok ")
	else {
		alert("Compila tutti i campi come devono essere!!!")
	}*/
	if (usernameReg == "" && !emailReg.includes("@") && telReg == ""){
		count--;
		}
	if (count == 1){
	fetch('/jsForgot', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ Username: usernameReg, Email: emailReg})
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
