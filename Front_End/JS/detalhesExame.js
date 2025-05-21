// Função para voltar à página de listagem
function voltarPagina() {
  window.history.back();
}

// Ao carregar a página, extrai os parâmetros da query string e preenche os dados
function preencherDetalhes() {
  const queryParams = new URLSearchParams(window.location.search);
  const nomeExame = queryParams.get("nome") || "Nome do Exame";
  const dataExame = queryParams.get("data") || "DD/MM/AAAA";
  const horaExame = queryParams.get("hora") || "HH:MM";
  const resultadoExame = queryParams.get("resultado") || "Detalhes completos do resultado do exame";

  document.getElementById("nome-exame").textContent = nomeExame;
  document.getElementById("data-exame").textContent = dataExame;
  document.getElementById("hora-exame").textContent = horaExame;
  document.getElementById("resultado-exame").textContent = resultadoExame;
}

window.addEventListener('load', preencherDetalhes);
// Adiciona o evento de clique ao botão de voltar