
document.addEventListener('DOMContentLoaded', function () {  //espera o html carragar todo
    document.getElementById('enderecoCadastroForm').addEventListener('submit', function (event) {
        event.preventDefault();


        const cep = document.getElementById('cep').value;
        const uf = document.getElementById('estado').value;
        const cidade = document.getElementById('cidade').value;
        const bairro = document.getElementById('bairro').value;
        const logradouro = document.getElementById('logradouro').value;
        const numero = document.getElementById('numero').value;
        const complemento = document.getElementById('complemento').value;

        //json
        const cadastrarEndereco = {
            cep: cep,
            uf: uf,
            cidade: cidade,
            bairro: bairro,
            logradouro: logradouro,
            numero: numero,
            complemento: complemento,
            

        };

        console.log("Dados para cadastro de endereço:", cadastrarEndereco);

        //manda a validação de dados para o back  
        fetch('http://localhost:8080/autenticar/cadastrarEndereco', { //mudar o caminho
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cadastrarEndereco)
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
                window.location.href = '../Front_End/HTML/cadastroAlergias.html';
            })
            .catch(error => {
                const msg = error.message;
                erroCadastrarEndereco(msg);
            });
    });
});

function erroLogin(msg) {

    if (msg === 'Erro ao cadastrar endereço!') {
        document.querySelector('.erroCadastrarEndereco').style.display = 'block';
    }
}