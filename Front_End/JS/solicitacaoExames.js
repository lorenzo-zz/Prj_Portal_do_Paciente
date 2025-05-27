const examesLista = [
    // 🩸 Exames de Sangue
 "Hemograma completo",
    "Contagem de plaquetas",
    "Contagem de leucócitos",
    "Contagem de hemácias",
    "Hematócrito",
    "Hemoglobina",
    "Volume corpuscular médio (VCM)",
    "Hemoglobina corpuscular média (HCM)",
    "Concentração de hemoglobina corpuscular média (CHCM)",
    "Índice de anisocitose",
    "Índice de macrocitose",
    "Índice de microcitose",
    "Índice de hipocromia",
    "Índice de policromasia",
    "Índice de reticulócitos",
    "Índice de plaquetas grandes",
    "Índice de plaquetas imaturas",
    "Índice de plaquetas jovens",

 "Glicemia de jejum",
 "Colesterol total",
 "Triglicerídeos",
 "Ácido úrico",
 "Creatinina",
 "Ureia",
 "TGO (AST)",
 "TGP (ALT)",
 "Gama GT",
 "Fosfatase alcalina",
 "Bilirrubina total e frações",
 "Albumina",
 "Globulina",
 "Relação A/G",
 "Tempo de protrombina (TP)",
 "Tempo de tromboplastina parcial ativada (TTPa)",
 "INR",
 "Fibrinogênio",
 "D-dímero",
 "Eletrólitos (sódio, potássio, cloro)",
 "Cálcio total",
 "Cálcio ionizado",
 "Magnésio",
 "Fósforo",
 "Ferro sérico",
 "Ferritina",
 "Transferrina",
 "Índice de saturação da transferrina",
 "Zinco",
 "Cobre",
 "Vitamina B12",
 "Ácido fólico",
 "Homocisteína",
 "Vitamina D",
 "Paratormônio (PTH)",
 "Testosterona total",
 "Testosterona livre",
 "DHEA-S",
 "FSH",
 "LH",
 "Estradiol",
 "Progesterona",
 "Prolactina",
 "Cortisol",
 "ACTH",
 "Insulina",
 "Peptídeo C",
 "Glicemia pós-prandial",
 "Hemoglobina glicada",
 "Anticorpos anti-TPO",
 "Anticorpos anti-tireoglobulina",
 "Tireoglobulina",
 "CEA",
 "CA 125",
 "CA 19-9",
 "CA 15-3",
 "Alfa-fetoproteína",
 "Beta-HCG",
 "PSA total",
 "PSA livre",
 "CK total",
 "CK-MB",
 "Troponina I",
 "Mioglobina",
 "BNP",
 "NT-proBNP",
 "Lipase",
 "Amilase",
 "Gastrina",
 "Calcitonina",
 "Proteína total",
 "Imunoglobulina A",
 "Imunoglobulina G",
 "Imunoglobulina M",
 "PCR ultrassensível",
 "Lipoproteína A",
 "Apolipoproteína A1",
 "Apolipoproteína B",
 "Colesterol HDL",
 "Colesterol LDL",
 "Colesterol VLDL",
 "Perfil lipídico",
 "Teste de função hepática",
 "Teste de função renal",
 "Teste de coagulação",
 "Exame toxicológico",
 "Anticorpos ANA",
 "Anticorpos anti-DNA",
 "Fator reumatoide",
 "Complemento C3",
 "Complemento C4",
 "Anticardiolipina IgG/IgM",
 "Lúpus anticoagulante",
 "Anti-CCP",
 "Teste de HIV",
 "Hepatite A IgM",
 "Hepatite B (HBsAg, anti-HBs, anti-HBc)",
 "Hepatite C (anti-HCV)",
 "Sífilis (VDRL, FTA-Abs)",
 "Citomegalovírus IgG/IgM",
 "Toxoplasmose IgG/IgM",
 "Rubéola IgG/IgM",
 "Herpes simples IgG/IgM",
 "Epstein-Barr IgG/IgM",
 "Zika vírus IgG/IgM",
 "Chikungunya IgG/IgM",

 // 🩺 Exames Cardiológicos
 "Eletrocardiograma",
    "Holter 24h",
    "Monitorização ambulatorial da pressão arterial (MAPA)",
    "Teste ergométrico",
    
 "Ecocardiograma",
 "Ecocardiograma com Doppler",
 "Teste ergométrico",
 "Holter 24h",
 "MAPA",
 "Cintilografia miocárdica",
 "Angiotomografia coronária",
 "Ressonância cardíaca",
 "Estudo eletrofisiológico",
 "Teste de troponina",
 "Teste de CK-MB",
 "ECG de esforço",
 "Teste de função ventricular",

 // 🫁 Exames Pulmonares
 "Espirometria",
 "Gasometria arterial",
 "Broncoscopia",
 "Tomografia de tórax",
 "Capnografia",
 "Polissonografia",
 "Teste de difusão de monóxido de carbono",
 "Prova de função pulmonar",
 "Radiografia de tórax",
 "Lavado broncoalveolar",

 // 🧠 Exames Neurológicos
 "Ressonância magnética cerebral",
 "Tomografia de crânio",
 "Eletroencefalograma",
 "Potenciais evocados",
 "Punção lombar",
 "Teste de líquidos cefalorraquidianos",
 "Angiorressonância cerebral",
 "Doppler transcraniano",
 "Teste de memória",
 "Teste neuropsicológico",
 "PET cerebral",
 "SPECT cerebral",

 // 🧬 Exames Genéticos
 "Teste do pezinho",
 "Cariotipo",
 "Array-CGH",
 "FISH",
 "Sequenciamento de DNA",
 "Teste para BRCA1/BRCA2",
 "Exoma completo",
 "Teste de doenças raras",
 "Teste de trombofilia",
 "Teste de fibrose cística",
 "Teste de intolerância à lactose",
 "Teste de sensibilidade a medicamentos",
 "Teste de ancestralidade",
 "Teste de predisposição genética ao câncer",

 // 🦴 Exames Ortopédicos
 "Densitometria óssea",
 "Ressonância magnética de joelho",
 "Ressonância da coluna lombar",
 "Tomografia de ombro",
 "Ultrassom de articulações",
 "Eletromiografia",
 "Artroscopia diagnóstica",

 // 🚻 Urológicos / Ginecológicos
 "Exame de urina tipo 1",
 "Urocultura",
 "Uretrocistografia",
 "Ultrassom de vias urinárias",
 "PSA",
 "Toque retal",
 "Cistoscopia",
 "Urodinâmica",
 "Papanicolau",
 "Colposcopia",
 "Ultrassom transvaginal",
 "Ultrassom pélvico",
 "Histerossalpingografia",
 "Biópsia de endométrio",
 "Dosagem hormonal",
 "Teste de ovulação",
 "CA-125",
 "CA-15.3",

 // 🦷 Exames Odontológicos
 "Radiografia panorâmica",
 "Periapical",
 "Tomografia odontológica",
 "Cefalometria",
 "Documentação ortodôntica",
 "Teste de sensibilidade dental",
 "PH salivar",
 "Microbiota oral",
 "Rastreamento de câncer bucal",
 "Biópsia oral",

 // 🧫 Outros exames laboratoriais
 "Coprocultura",
 "Parasitológico de fezes",
 "Exame de fezes",
 "Exame de escarro",
 "Swab nasal",
 "Swab anal",
 "Teste rápido de antígeno COVID",
 "RT-PCR COVID",
 "Sorologia COVID IgG/IgM",
 "Teste de dengue NS1",
 "Teste de Influenza A/B",
 "Teste de clamídia",
 "Teste de gonorreia",
 "Teste de HPV",
 "Teste de tuberculose (PPD)",
 "Teste de resistência antimicrobiana",
 "Cultura de secreção vaginal",
 "Cultura de secreção uretral",
 "Cultura de garganta",
 "Cultura de ferida",
 "Teste de contato alérgico",
 "Teste de intolerância alimentar",
 "Teste de respiração para H. pylori",
 "Teste de urease",
 "Cultura de fungos",
 "Exame micológico direto",

 // 🔎 Exames de imagem (diversos)
 "Ultrassonografia abdominal",
 "Ultrassom hepático",
 "Ultrassom renal",
 "Ultrassom da tireoide",
 "Mamografia",
 "Tomografia abdominal",
 "Tomografia pélvica",
 "Tomografia de coluna",
 "Ressonância magnética de abdome",
 "PET-CT",
 "Cintilografia óssea",
 "Densitometria corporal",
 "Angiografia",
 "Ecoendoscopia",
 "Enterotomografia" ,
 // Membros Superiores
 "Raio-X de dedo",
 "Raio-X de mão",
 "Raio-X de punho",
 "Raio-X de antebraço",
 "Raio-X de cotovelo",
 "Raio-X de braço",
 "Raio-X de ombro",
 "Raio-X de clavícula",
 "Raio-X da escápula",

 // Membros Inferiores
 "Raio-X de dedo do pé",
 "Raio-X de pé",
 "Raio-X de tornozelo",
 "Raio-X de perna",
 "Raio-X de joelho",
 "Raio-X de fêmur",
 "Raio-X de quadril",
 "Raio-X de bacia",

 // Coluna Vertebral
 "Raio-X da coluna cervical",
 "Raio-X da coluna torácica",
 "Raio-X da coluna lombar",
 "Raio-X da coluna lombossacra",
 "Raio-X da coluna sacrococcígea",
 "Raio-X panorâmico da coluna total",
 "Raio-X da coluna com flexão e extensão",
 "Raio-X da coluna para escoliose",

 // Tórax
 "Raio-X de tórax PA",
 "Raio-X de tórax perfil",
 "Raio-X de tórax AP leito",
 "Raio-X de costelas",
 "Raio-X de esterno",

 // Crânio e Face
 "Raio-X de crânio",
 "Raio-X da sela túrcica",
 "Raio-X dos ossos da face",
 "Raio-X dos seios da face",
 "Raio-X das cavidades nasais",
 "Raio-X de mastoides",
 "Raio-X da articulação temporomandibular",

 // Odontológicos
 "Raio-X periapical",
 "Raio-X bite-wing",
 "Raio-X oclusal",
 "Raio-X panorâmico odontológico",
 "Raio-X cefalométrico lateral",
 "Raio-X da articulação da mandíbula",

 // Abdômen e Pelve
 "Raio-X de abdome agudo",
 "Raio-X de abdome ortostático",
 "Raio-X de abdome em decúbito",
 "Raio-X de pelve",
 "Raio-X das articulações sacroilíacas",
 "Raio-X com preparo intestinal",

 // Pediátricos
 "Raio-X para idade óssea",
 "Raio-X de quadril infantil",
 "Raio-X de tórax infantil",
 "Raio-X para dismetria de membros",
 "Raio-X para escoliose infantil",
];

const selectExame = document.getElementById("tipo-exame");
examesLista.forEach(exame => {
 const option = document.createElement("option");
 option.value = exame.toLowerCase().replace(/ /g, "-");
 option.textContent = exame;
 selectExame.appendChild(option);
});

document.getElementById("exames-form").addEventListener("submit", function(event) {
 event.preventDefault();

 const tipoExame = document.getElementById("tipo-exame").value;
 const medicoSolicitante = document.getElementById("medico-solicitante").value;
 const dataSolicitacao = document.getElementById("data-solicitacao").value;

 alert(`Solicitação de exame enviada!\n\nTipo: ${tipoExame}\nMédico: ${medicoSolicitante}\nData: ${dataSolicitacao}`);
});

function limparFormulario() {
  document.getElementById("exames-form").reset();
}

// ------------------------------- Validação dos campos 

document.addEventListener("DOMContentLoaded", function () {
   const campoCPF = document.getElementById("cpf-paciente");
   const campoTelefone = document.getElementById("telefone");
   const campoNome = document.getElementById("nome-paciente");
 
   if (campoCPF) {
     campoCPF.addEventListener("input", function () {
       this.value = this.value.replace(/\D/g, "");
     });
   }
 
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
 