document.addEventListener('DOMContentLoaded', function () {
    function LimparCPF() {
        localStorage.removeItem('cpf');
        localStorage.removeItem('nome');
    }

    LimparCPF();
});