document.addEventListener("DOMContentLoaded", function () {
    const links = document.querySelectorAll(".card a");

    links.forEach(link => {
        link.addEventListener("click", function (event) {
            const cpf = localStorage.getItem('cpf');

            if (!cpf) {
                event.preventDefault();
                setTimeout(function () {
                    window.location.href = "/Front_End/HTML/loginPage.html";
                }, 1000);
            }
        });
    });
});
