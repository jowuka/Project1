function Registerbutton() {
	var usernameReg = document.getElementById("usernamejs").value;
	var passwordReg = document.getElementById("passjs").value;
	var passwordcheckReg = document.getElementById("passjs2").value;
	var emailReg = document.getElementById("email").value;
	var telReg = document.getElementById("Tel").value;
	var count = 1;
	/*if (usernameReg != "" && passwordReg != "" && passwordcheckReg == passwordReg && emailReg.includes("@") && dataNascitaReg != "" && indirizzoReg != "")
		alert("Tutto ok ")
	else {
		alert("Compila tutti i campi come devono essere!!!")
	}*/
	if (usernameReg == "" && passwordReg == "" && passwordReg != passwordcheckReg && !emailReg.includes("@") && telReg == ""){
		count--;
		}
	if (count == 1){
	fetch('/register', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ Username: usernameReg, Password: passwordReg, Password2: passwordcheckReg, Email: emailReg, Tel: telReg })
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
