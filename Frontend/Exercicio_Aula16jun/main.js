// Criação de uma classe
class User {
    constructor(inputEmail, inputAddress, inputNome, inputNumber, inputPassword, inputGender, inputClubes, inputChecked) {
        this.inputEmail = inputEmail;
        this.inputAddress = inputAddress;
        this.inputNome = inputNome;
        this.inputNumber = inputNumber;
        this.inputPassword = inputPassword;
        this.inputGender = inputGender;
        this.inputClubes = inputClubes;
        this.inputChecked = inputChecked;
    }

    show() {
        console.log(this);
    }
}

function getFormElements() {
    let newUserByClass = new User(
        document.getElementById("inputEmail").value,
        document.getElementById("inputAddress").value,
        document.getElementById("inputNome").value,
        document.getElementById("inputNumber").value,
        document.getElementById("inputPassword").value, 
        genderHelper(document.querySelectorAll("[name=inputGender]")),
        document.getElementById("inputClubes").value,
        checkboxHelper()
    )

    newUserByClass.show(); 
}

function genderHelper(inputClubes) {
    for (const input of inputClubes) {
        if (input.checked)
            return input.value;
    }
    return "Não selecionado";
}

function checkboxHelper() {
    if (document.getElementById("flexCheckChecked").checked)
        return "Concorda"
    else
        return "Não concorda"
}