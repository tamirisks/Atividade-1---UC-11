/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        //conn = new conectaDAO().connectDB();
        
        
    }
    

    public List<ProdutosDTO> listarProdutos (){
    String sql = "SELECT * FROM produtos";

    try {                 
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<ProdutosDTO> ListaProdutos = new ArrayList<>();

        while (rs.next()) { 
        ProdutosDTO produtosdto = new ProdutosDTO();
        produtosdto.setId(rs.getInt("id"));
        produtosdto.setNome(rs.getString("nome"));
        produtosdto.setValor(rs.getInt("valor"));
        produtosdto.setStatus(rs.getString("status"));
        
        ListaProdutos.add(produtosdto);       
        }
        
        return ListaProdutos;
        
    //tratando o erro, caso ele ocorra     
    } catch (Exception e) {
        System.out.println("erro: " + e.getMessage());
        return null;
      }      
    
    }

    public void venderProduto(int id){

    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try {

        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setInt(1, id);

        int linhas = stmt.executeUpdate();

        if(linhas > 0){
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        }else{
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }

    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
}
        
 
