
document.addEventListener('DOMContentLoaded', function () {  //espera o html carragar todo
    document.getElementById('loginForm').addEventListener('submit', function (event) {
        event.preventDefault();


        const nome = document.getElementById('nome').value;
        const cpf = document.getElementById('cpf').value;
        const telefone = document.getElementById('telefone').value;
        const sexo = document.getElementById('radio_sexo').value;
        const dt_nascimento = document.getElementById('dt_nascimento').value;
        const documento = document.getElementById('documento').value;
        const senha = document.getElementById('senha').value;
        const confirmar_senha = document.getElementById('confirmar_senha').value;

        //json
        const cadastro = {
            nome: nome,
            cpf: cpf,
            telefone: telefone,
            sexo: sexo,
            dt_nascimento: dt_nascimento,
            documento: documento,
            senha: senha,
            confirmar_senha: confirmar_senha,


        };
        //manda a validação de dados para o back  
        fetch('http://localhost:8080/autenticar/cadastrar', { //mudar o caminho
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cadastro)
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
                localStorage.setItem('nome', nome) //armazenado para dar as boas vindas depois na tela de login
                window.location.href = '../Front_End/HTML/adressSignUp.html';
            })
            .catch(error => {
                const msg = error.message;
                erroLogin(msg);
            });
    });
});

function erroLogin(msg) {

    if (msg === 'Usuário ou senha inválidos!') {
        document.querySelector('.loginErro').style.display = 'block';
    }
}