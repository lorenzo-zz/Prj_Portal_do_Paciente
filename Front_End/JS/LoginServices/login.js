document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('formLogin').addEventListener('submit', function (event) {
        event.preventDefault();

        const cpf = document.getElementById('cpf').value;
        const senha = document.getElementById('senha').value;
        const erroLogin = document.querySelector('.erroLogin');
        const erroNulo = document.querySelector('.erroNulo');


        erroLogin.style.display = 'none';
        erroNulo.style.display = 'none';
        
        const login = {
            cpf: cpf,
            senha: senha,
        };

        if(cpf === "" || senha === "") {
            erroNulo.style.display = 'block';
            return;
        }

        fetch('http://localhost:8080/autenticar/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(login)
        })
            .then(async response => {
                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.erro || 'Erro desconhecido');
                }
                return response.text();
            })
            .then(data => {
                localStorage.setItem('cpf', cpf);
                localStorage.setItem('nome', data.nome);
                window.location.href = 'http://127.0.0.1:5500/Front_End/HTML/index.html';
            })
            .catch(error => {
                const msg = error.message;

                if(msg.includes("CPF ou senha inválidos.")) {
                    erroLogin.style.display = 'block';
                }
            });
    });
});
