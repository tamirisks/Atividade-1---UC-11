import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;



public class ProdutosDAO {
    
    private conectaDAO conexao;
    private Connection conn;
    PreparedStatement st;
    
    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
           
        int status;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO produtos (nome, valor, status) "
                            + "VALUES(?,?,?)"
            );
                       
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            status = st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            //return ex.getErrorCode();
        }
    }
    
    public List<ProdutosDTO> listarProdutos (String tipo){
    String sql = "SELECT * FROM produtos WHERE id LIKE ?";

    try {                 
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1,"%" + tipo + "%");
        ResultSet rs = stmt.executeQuery();
        
        List<ProdutosDTO> ListaProdutos = new ArrayList<>();

        while (rs.next()) { 
        ProdutosDTO produtosdto = new ProdutosDTO();
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
        
        
    }
    


