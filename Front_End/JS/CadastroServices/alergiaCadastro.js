
document.addEventListener('DOMContentLoaded', function () {  //espera o html carragar todo
    document.getElementById('alergiaCadastro').addEventListener('submit', function (event) {
        event.preventDefault();


        const alergia = document.getElementById('alergiaSelect').value;
        
        //json
        const cadastrarAlergia = {
            alergia: alergia,

        };
        //manda a validação de dados para o back  
        fetch('http://localhost:8080/autenticar/cadastrarAlergia', { //mudar o caminho
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cadastrarAlergia)
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
                window.location.href = '../Front_End/HTML/loginPage.html';
            })
            .catch(error => {
                const msg = error.message;
                erroCadastrarAlergia(msg);
            });
    });
});

function erroCadastrarAlergia(msg) {

    if (msg === 'Erro ao cadastrar alergia!') {
        document.querySelector('.erroCadastrarAlergia').style.display = 'block';
    }
}