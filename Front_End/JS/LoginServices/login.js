document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('formLogin').addEventListener('submit', function (event) {
        event.preventDefault();

        const cpf = document.getElementById('cpf').value;
        const senha = document.getElementById('senha').value;

        // JSON com os dados
        const login = {
            cpf: cpf,
            senha: senha,
        };

        // Envia dados para o backend
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
                erroLogin(msg);
            });
    });
});

function erroLogin(msg) {
    if (msg === 'Erro na autenticação!') {
        document.querySelector('.erroLogin').style.display = 'block';
    }
}
