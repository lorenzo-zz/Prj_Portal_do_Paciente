document.addEventListener('DOMContentLoaded', function () {  // espera o html carregar todo

    // ✅ Preenche o campo nome automaticamente se houver no localStorage
    const nomeSalvo = localStorage.getItem('nome');
    if (nomeSalvo) {
        const campoNome = document.getElementById('nome');
        if (campoNome) {
            campoNome.value = nomeSalvo;

        }
    }

    document.getElementById('loginForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const cpf = document.getElementById('cpf').value;  
        const senha = document.getElementById('senha').value;

        // JSON com os dados
        const login = {
            nome: cpf,
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
            localStorage.setItem('nome', nome);
            window.location.href = '../Front_End/HTML/index.html';
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
