package com.example;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PrimaryController {

    @FXML
    private Button botaoSalvar;
    @FXML
    private ListView<Anotacoes> listAnotacoes;
    @FXML
    private TextArea txtAnotacoes;
    @FXML
    private Pane panelBotoes;
    @FXML
    private TextField txtId;
    @FXML
    Button botaoSalvarEdicao = new Button("Salvar edição");

    private AnotacoesDao anotacoesDao = new AnotacoesDao();
    private ArrayList<Anotacoes> anotacoes = new ArrayList<>();



    public void adicionarAnotacao() {
        
        var anotacao = new Anotacoes(txtAnotacoes.getText(),
        Integer.parseInt(txtId.getText()));
        anotacoes.add(anotacao);

        txtAnotacoes.clear();
        txtId.clear();

        atualizarAnotacao();

        try {
            anotacoesDao.inserir(anotacao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAnotacao(){
        
        listAnotacoes.getItems().clear();
        
        for (var anotacao : anotacoes) {
            listAnotacoes.getItems().add(anotacao);
        }

        try {
            anotacoesDao.buscarTodos().forEach(anotacao -> listAnotacoes.getItems().add(anotacao));
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.INFORMATION, "Erro ao buscar. ");
        alert.setHeaderText("Erro");
        alert.show();
        }
    }

    public void apagarAnotacoes() {
        var anotacao = listAnotacoes.getSelectionModel().getSelectedItem();

        anotacoes.remove(anotacao);

        Alert alert = new Alert(AlertType.INFORMATION, "Sua anotação foi apagada!");
        alert.setHeaderText("Removido");
        alert.show();
        

    }
}
