document.addEventListener("DOMContentLoaded", function () {

    const cpfPaciente = localStorage.getItem('cpf');

    const login = document.getElementById('right_links');
    const logout = document.getElementById('logout_button');

    if (cpfPaciente) {
        login.style.display = 'none';
        logout.style.display = 'block';
    } else {
        login.style.display = 'block';
        logout.style.display = 'none';
    }
});
