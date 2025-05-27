document.getElementById("btnCancelarConsulta").addEventListener("click", function () {
    document.getElementById("modalConfirmacao").style.display = "flex";
});

document.getElementById("btnFechar").addEventListener("click", function () {
    document.getElementById("modalConfirmacao").style.display = "none";
});

document.getElementById("btnConfirmar").addEventListener("click", function () {
    alert("Consulta cancelada com sucesso.");
    document.getElementById("modalConfirmacao").style.display = "none";
    // Aqui pode-se adicionar a lógica para remover a consulta do banco de dados
});

//aqui o @Lorenzo vai adicionar a lógica para preencher os dados da consulta
////aqui o @Lorenzo vai adicionar a lógica para o botão de cancelar consulta
//aqui o @Lorenzo vai adicionar a lógica para o botão de confirmar cancelamento