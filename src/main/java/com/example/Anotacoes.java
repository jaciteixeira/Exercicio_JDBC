package com.example;

public record Anotacoes(String anotacoes, Integer id) {

    @Override
    public String toString(){
        return "Anotação: "+ id +"-"+ anotacoes;
    } 

}
