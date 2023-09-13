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

        mostrarAnotacoes();

        try {
            anotacoesDao.inserir(anotacao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void mostrarAnotacoes() {

        listAnotacoes.getItems().clear();
        
        for (var anotacao : anotacoes) {
            listAnotacoes.getItems().add(anotacao);
        }

        atualizarAnotacao();
    }

    public void atualizarAnotacao(){
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
        mostrarAnotacoes();

        Alert alert = new Alert(AlertType.INFORMATION, "Sua anotação foi apagada!");
        alert.setHeaderText("Removido");
        alert.show();
        

    }

    // public void editarAnotacao() {

    //     var anotacao = listAnotacoes.getSelectionModel().getSelectedItem();

    //     if (anotacao != null) {
    //         panelBotoes.getChildren().remove(botaoSalvar);
    //         panelBotoes.getChildren().add(botaoSalvarEdicao);
    //         botaoSalvarEdicao.setOnAction(event -> salvarEdicao(anotacao));
    //         botaoSalvarEdicao.setStyle("-fx-alignment: center_right; -fx-background-color: #8b4ab9;");

    //         txtAnotacoes.setText(anotacao.anotacoes());
    //         txtId.setText(Integer.toString(anotacao.id()));
    //     } else {
    //         Alert alert = new Alert(AlertType.WARNING, "Selecione um veículo para editar.");
    //         alert.setHeaderText("ERRO!");
    //         alert.show();
    //     } 
    // }
    
    public void salvarEdicao(Anotacoes anotacao) {
        int indice = anotacoes.indexOf(anotacao);
        
        var anotacaoAlterado = new Anotacoes (txtAnotacoes.getText(),
        Integer.parseInt(txtId.getText()));
        
        anotacoes.set(indice, anotacaoAlterado);
        
        mostrarAnotacoes();
        
        Alert alert = new Alert(AlertType.INFORMATION, "Edição salva!");
        alert.setHeaderText("Editado");
        alert.show();
        
        // try {
        //     anotacoesDao.atualizar(anotacao);
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        // panelBotoes.getChildren().remove(botaoSalvarEdicao);
        // panelBotoes.getChildren().add(botaoSalvar);
    }
}
