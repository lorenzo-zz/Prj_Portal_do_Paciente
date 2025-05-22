document.addEventListener("DOMContentLoaded", function () {

    const cpf = localStorage.getItem('cpf');

    if(cpf == null){
        window.location.href = '/Front_End/HTML/loginPage.html';
    }
});