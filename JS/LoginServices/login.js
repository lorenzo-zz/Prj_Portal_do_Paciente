
document.addEventListener('DOMContentLoaded', function () {  //espera o html carragar todo
    document.getElementById('loginForm').addEventListener('submit', function (event) {
        event.preventDefault();

        
        const nome = document.getElementById('nome').value;  
        const senha = document.getElementById('senha').value;

        //json
        const login = {
            nome: nome,
            senha: senha,
        }; 
        //manda a validação de dados para o back
        fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(login)
        })
        //se a resposta for diferente de 200 = OK
         .then(async response => {  
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.erro || 'Erro desconhecido');
            }
            return response.text(); //retorna o texto da vairavel response 
        })
        .then(data => { //se o login der certo 
        localStorage.setItem('nome', nome)
            window.location.href = '../Front_End/HTML/index.html';
        })
        .catch(error => {
            const msg = error.message;
            erroLogin(msg);
        });
    });
});

function erroLogin(msg) {

    if(msg === 'Erro na autenticação!'){
        document.querySelector('.erroLogin').style.display = 'block';
    }
}