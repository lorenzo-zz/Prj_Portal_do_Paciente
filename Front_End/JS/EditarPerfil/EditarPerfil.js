function showModal() {
    const modal = document.getElementById("modal");
    modal.style.display = "block";
}

function closeModal() {
    const modal = document.getElementById("modal");
    modal.style.display = "none";
}

const  cpfPaciente = new URLSearchParams({
    cpf: '10483027928'
});


fetch('https:/api/obter_dados_paciente?' + cpfPaciente)
  .then(respostaApi=> respostaApi.json())
  .then(dadosPaciente=> console.log(dadosPaciente))
  .catch(error => console.error('Error:', error));

  document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('formLogin').addEventListener('submit', function (event) {
        event.preventDefault();
 
        const nome = document.getElementById('nome').value;
        const telefone = document.getElementById('telefone').value;
        const email = document.getElementById('email').value;
        const dt_nascimento = document.getElementById('dt_nascimento').value
        const cep = document.getElementById('cep').value;
        const logradouro = document.getElementById('logradouro').value;
        const numero = document.getElementById('numero').value;
        const complemento = document.getElementById('complemento').value;
        const bairro = document.getElementById('bairro').value;
        const cidade = document.getElementById('cidade').value;
        const uf = document.getElementById('estado').value;        
 
        const mostrarDados = {
            nome: nome,
            telefone: telefone,
            email: email,
            dataNascimento: dt_nascimento,
            cep: cep,
            logradouro: logradouro,
            numero: numero,
            complemento: complemento,
            bairro: bairro,
            cidade: cidade,
            uf: uf

        };
 
        fetch('http://localhost:8080/autenticar/login', {
            method: 'GET',
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
                window.location.href = 'http://172.20.208.1:5500/Front_End/HTML/index.html';
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
 

// ----------------Validação dos campos


document.addEventListener("DOMContentLoaded", function () {
    const campoCPF = document.getElementById("cpf-paciente");
    const campoTelefone = document.getElementById("telefone");
    const campoNome = document.getElementById("nome");
    const campoCep = document.getElementById("cep");
  

    if (campoTelefone) {
      campoTelefone.addEventListener("input", function () {
        this.value = this.value.replace(/\D/g, "");
      });
    }
  
    if (campoNome) {
      campoNome.addEventListener("input", function () {
        this.value = this.value.replace(/[^a-zA-ZÀ-ÿ\s]/g, "");
      });
    }
  });
  
  document.addEventListener("DOMContentLoaded", function () {
    const campoCEP = document.getElementById("cep");
    const campoNumero = document.getElementById("numero");
  
    campoCEP.addEventListener("input", function () {
      this.value = this.value.replace(/\D/g, '');
    });
  

    campoNumero.addEventListener("input", function () {
      this.value = this.value.replace(/\D/g, '');
    });
  });
  