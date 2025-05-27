package com.example.oracleapi.Model;

public enum NomeAlergia {

    // Alergias alimentares
    ALERGIA_ALIMENTAR("Alergia Alimentar"),
    ALERGIA_LACTOSE("Alergia à Lactose"),
    ALERGIA_GLUTEN("Alergia ao Glúten"),
    ALERGIA_OVO("Alergia a Ovo"),
    ALERGIA_AMENDOIM("Alergia a Amendoim"),
    ALERGIA_SOJA("Alergia à Soja"),
    ALERGIA_TRIGO("Alergia ao Trigo"),
    ALERGIA_NOZES("Alergia a Nozes"),
    ALERGIA_CASTANHAS("Alergia a Castanhas"),
    ALERGIA_FRUTOS_DO_MAR("Alergia a Frutos do Mar"),
    ALERGIA_FRUTAS_CITRICAS("Alergia a Frutas Cítricas"),
    ALERGIA_CEREAIS("Alergia a Cereais"),
    ALERGIA_AZEITES("Alergia a Azeites"),

    // Alergias medicamentosas
    ALERGIA_MEDICAMENTOSA("Alergia Medicamentosa"),
    ALERGIA_FARMACOS_ANTIINFLAMATORIOS("Alergia a Fármacos Antiinflamatórios"),
    ALERGIA_ANTIBIOTICOS("Alergia a Antibióticos"),
    ALERGIA_ANALGESICOS("Alergia a Analgésicos"),
    ALERGIA_PENICILINA("Alergia à Penicilina"),
    ALERGIA_AAS_ACIDO_ACETILSALICILICO("Alergia a AAS (Ácido Acetilsalicílico)"),
    ALERGIA_INSULINA("Alergia à Insulina"),
    ALERGIA_ANTICONVULSIVANTES("Alergia a Anticonvulsivantes"),
    ALERGIA_ANESTESICOS("Alergia a Anestésicos"),
    ALERGIA_IEDES_INIBIDORES_ENZIMA_CONVERSORA("Alergia a IEDEs (Inibidores da Enzima de Conversão)"),
    ALERGIA_VACINAS("Alergia a Vacinas"),

    // Alergias respiratórias
    ALERGIA_RESPIRATORIA("Alergia Respiratória"),
    ALERGIA_POEIRA("Alergia a Poeira"),
    ALERGIA_POEIRA_DOMESTICA("Alergia a Poeira Doméstica"),
    ALERGIA_POLEN("Alergia a Pólen"),
    ALERGIA_MOFO("Alergia a Mofo"),
    ALERGIA_FUNGOS("Alergia a Fungos"),
    ALERGIA_ACAROS("Alergia a Ácaros"),

    // Alergias dermatológicas e de contato
    ALERGIA_DERMATOLOGICA("Alergia Dermatológica"),
    ALERGIA_CONTACTANTE("Alergia de Contato"),
    ALERGIA_LATEX("Alergia a Látex"),
    ALERGIA_NIQUEL("Alergia a Níquel"),
    ALERGIA_COBALTO("Alergia a Cobalto"),
    ALERGIA_FORMALDEIDO("Alergia a Formaldeído"),
    ALERGIA_METAL("Alergia a Metal"),
    ALERGIA_FIBRAS_SINTETICAS("Alergia a Fibras Sintéticas"),
    ALERGIA_COSMETICOS("Alergia a Cosméticos"),
    ALERGIA_DETERGENTES("Alergia a Detergentes"),
    ALERGIA_PRODUTOS_LIMPADORES("Alergia a Produtos de Limpeza"),
    ALERGIA_TINTAS("Alergia a Tintas"),
    ALERGIA_LUSTRADORES("Alergia a Lustradores"),
    ALERGIA_PRODUTOS_CAPILARES("Alergia a Produtos Capilares"),

    // Alergias oculares
    ALERGIA_OCULAR("Alergia Ocular"),

    // Alergias ambientais e animais
    ALERGIA_ANIMAIS("Alergia a Animais"),
    ALERGIA_PELOS("Alergia a Pelos"),
    ALERGIA_PLANTAS("Alergia a Plantas"),

    // Alergias a picadas de insetos
    ALERGIA_PICADA_INSETOS("Alergia a Picada de Insetos"),
    ALERGIA_ABELHA("Alergia a Abelha"),
    ALERGIA_VESPAS("Alergia a Vespas"),

    // Outras
    ALERGIA_PERFUMES("Alergia a Perfumes"),
    ALERGIA_BEBIDAS_ALCOOLICAS("Alergia a Bebidas Alcoólicas");

    private final String descricao;

    NomeAlergia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
