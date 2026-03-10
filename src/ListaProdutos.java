
import java.util.ArrayList;
import java.util.List;

public class ListaProdutos {
    
    private static List<ProdutosDTO> DadosProdutos = new ArrayList<>();

    public static List<ProdutosDTO> Listar(){
        return DadosProdutos;
    }
    
    public static void setDadosProdutos (ProdutosDTO produtos){
        DadosProdutos.add(produtos);
    }
    
}
