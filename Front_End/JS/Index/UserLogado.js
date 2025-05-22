document.addEventListener("DOMContentLoaded", function () {
    const cpfPaciente = localStorage.getItem('cpf');
    const register = document.getElementById('right_links');
    if (cpfPaciente) {
        register.style.display = 'none';
    } else {
        register.style.display = 'block';
    }
});
