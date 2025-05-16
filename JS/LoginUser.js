document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('loginForm').addEventListener('submit', function (event) {
        event.preventDefault();

    const email = document.getElementById("email").value
    const password = document.getElementById("password").value

    // Form Json
    const data = {  
        email: email,
        password: password  
    }

    fetch('http://localhost:8080/api/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })

    // Verifica se o login foi bem-sucedido
        .then(async response => {
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.erro || 'Erro desconhecido');
            }
            return response.text();
        })

        // Se o login for bem-sucedido, redireciona para a página de boas-vindas
        .then(data => {
            localStorage.setItem('email', email);
            localStorage.setItem('password', password);

            window.location.href = 'http://localhost:8080/Welcome.html';
        }).catch(error => {
            
        });
    });
});

