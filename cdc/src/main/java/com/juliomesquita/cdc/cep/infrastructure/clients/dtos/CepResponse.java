package com.juliomesquita.cdc.cep.infrastructure.clients.dtos;

public record CepResponse(
    String cep,
    String logradouro,
    String complemento,
    String unidade,
    String bairro,
    String localidade,
    String uf,
    String estado,
    String regiao,
    String ibge,
    String gia,
    String ddd,
    String siafi
) {}

