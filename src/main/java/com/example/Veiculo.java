package com.example;

public record Veiculo(String proprietario, Double valor, String marca, String modelo, String categoria) {
    @Override
    public String toString(){
        return proprietario + " - " + "(" + marca + ") " + modelo + " - R$ " + valor + " / " + categoria;
    }
}
