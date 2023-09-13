package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnotacoesDao {
    private final String HOST = "oracle.fiap.com.br";
    private final String PORT = "1521";
    private final String DATABASE = "orcl";
    private final String USER = "rm99627";
    private final String PASS = "051298";
    private final String URL = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + DATABASE; // JDBC URL

    public void inserir(Anotacoes anotacao) throws SQLException {
        var con = DriverManager.getConnection(URL, USER, PASS);

        var sql = "INSERT INTO anotacoes (anotacao,id) VALUES (?,?)";
        var instrucao = con.prepareStatement(sql);
        instrucao.setString(1,anotacao.anotacoes());
        instrucao.setInt(2,anotacao.id());


        instrucao.executeUpdate();
        con.close();
    }


     public List<Anotacoes> buscarTodos() throws SQLException{
        var anotacao = new ArrayList<Anotacoes>();
        var con = DriverManager.getConnection(URL, USER, PASS);
        var rs = con.createStatement().executeQuery("SELECT * FROM anotacoes");

        while(rs.next()){
            anotacao.add(new Anotacoes(
                rs.getString("anotacao"), 
                rs.getInt("id") 

            ));
        }

        con.close();
        return anotacao;
    }
    
}
