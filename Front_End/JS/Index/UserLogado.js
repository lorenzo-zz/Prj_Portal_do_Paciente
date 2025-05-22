document.addEventListener("DOMContentLoaded", function () {
    const cpfPaciente = localStorage.getItem('cpf');
    const login = document.getElementById('login_button');
    const register = document.getElementById('sign_button');
    const logout = document.getElementById('logout_button');
    if (cpfPaciente) {
        login.style.display = 'none';
        register.style.display = 'none';
        logout.style.display = 'block';
    } else {
        login.style.display = 'block';
        register.style.display = 'block';
        logout.style.display = 'none';
    }
});
