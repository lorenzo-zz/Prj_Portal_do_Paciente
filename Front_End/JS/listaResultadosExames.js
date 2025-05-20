// Exemplo de dados dos exames. Em produção, esses dados podem vir de uma API.
const examResults = [
  {
    nome: "Hemograma Completo",
    data: "15/05/2025",
    hora: "09:00",
    resumo: "Exame com resultados dentro do esperado para hemácias e leucócitos.",
    resultado: "O hemograma completo apresentou valores normais para hemácias, leucócitos, plaquetas e demais parâmetros importantes, sem alterações significativas."
  },
  {
    nome: "Glicemia de Jejum",
    data: "15/05/2025",
    hora: "09:15",
    resumo: "Glicose dentro dos níveis ideais.",
    resultado: "O exame de glicemia de jejum indicou níveis de glicose normais, sem evidências de hiperglicemia ou hipoglicemia."
  },
  {
    nome: "Colesterol Total",
    data: "15/05/2025",
    hora: "09:30",
    resumo: "Valores levemente alterados, sugerindo cuidado alimentar.",
    resultado: "O exame de colesterol total apresentou níveis um pouco acima do recomendado. Recomenda-se acompanhamento nutricional e acompanhamento clínico."
  }
];

// Seleciona o container da lista
const examesListContainer = document.getElementById("exames-list");

// Cria os itens da lista dinamicamente
examResults.forEach((exame, index) => {
  const itemDiv = document.createElement("div");
  itemDiv.classList.add("exame-item");

  // Cria o conteúdo do item
  itemDiv.innerHTML = `
    <h2>${exame.nome}</h2>
    <p>Data: ${exame.data} | Hora: ${exame.hora}</p>
    <p>Resumo: ${exame.resumo}</p>
  `;

  // Ao clicar, redireciona para a página de detalhes com os parâmetros via query string
  itemDiv.addEventListener("click", () => {
    const url = new URL(window.location.origin + "/Front_End/HTML/detalhesExame.html");
    url.searchParams.append("nome", exame.nome);
    url.searchParams.append("data", exame.data);
    url.searchParams.append("hora", exame.hora);
    url.searchParams.append("resultado", exame.resultado);
    window.location.href = url.toString();
  });

  examesListContainer.appendChild(itemDiv);
});
