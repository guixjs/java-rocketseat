import java.util.List;

public class SistemaCadastro {
  public static void main(String[] args) {
    FabricaCadastro.conectar();
    CadastroRepository repository = new CadastroRepository();
    CadastroModel cadastro = repository.buscar(3);
    if(cadastro != null){
      System.out.println(cadastro.toString());
    }else {
      System.out.println("cadastro não encontrado");
    }
  }
}
