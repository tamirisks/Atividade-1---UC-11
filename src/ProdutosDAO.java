
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
    
    public ProdutosDAO() {
    this.conn = new conectaDAO().connectDB();
}
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);

        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getValor());
        stmt.setString(3, produto.getStatus());

        stmt.executeUpdate();

        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

    } catch (Exception e) {
        System.out.println("Erro ao cadastrar: " + e.getMessage());
    }
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
        
       
    } catch (Exception e) {
        System.out.println("erro: " + e.getMessage());
        return new ArrayList<>();      
    }      
    
    }
    
    public List<ProdutosDTO> listarProdutosVendidos(){
        
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        
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
        
 
