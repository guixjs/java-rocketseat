import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroRepository {
  private Connection conexao;

  public CadastroRepository(){
    conexao = FabricaCadastro.getConexao();
  }

  public void salvar(CadastroModel cadastro){
    try{
      String instrucao = "INSERT INTO tb_cadastro(nome,idade) VALUES(?,?)";
      PreparedStatement pst = conexao.prepareStatement(instrucao);
      pst.setString(1,cadastro.getNome());
      pst.setInt(2,cadastro.getIdade());

      pst.execute();
      System.out.println("Cadastro inserido");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void alterar(CadastroModel cadastro  ){
    try{
      String instrucao = "UPDATE public.tb_cadastro SET nome=?, idade=? WHERE id=?";
      PreparedStatement pst = conexao.prepareStatement(instrucao);
      pst.setString(1,cadastro.getNome());
      pst.setInt(2,cadastro.getIdade());
      pst.setInt(3,cadastro.getId());

      pst.execute();
      System.out.println("Cadastro atualizado");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void excluir(int id){
    try{
      String instrucao = "DELETE FROM public.tb_cadastro WHERE id=?";
      PreparedStatement pst = conexao.prepareStatement(instrucao);
      pst.setInt(1,id);


      pst.execute();
      System.out.println("Cadastro excluido");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public List<CadastroModel> listar(){
    List<CadastroModel> lista = new ArrayList<>();
    String instrucao = "SELECT id, nome, idade FROM public.tb_cadastro";
    try {
      PreparedStatement pst = conexao.prepareStatement(instrucao);
      //pst.setInt(1,1);
      ResultSet resultado = pst.executeQuery();
      while (resultado.next()){
        int id = resultado.getInt("id");
        String nome = resultado.getString("nome");
        int idade = resultado.getInt("idade");

        CadastroModel cadastro = new CadastroModel();
        cadastro.setId(id);
        cadastro.setNome(nome);
        cadastro.setIdade(idade);

        lista.add(cadastro);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return lista;
  }
  public CadastroModel buscar(int id){
    CadastroModel cadastro = null;
    String instrucao = "SELECT id, nome, idade FROM public.tb_cadastro WHERE id=?";
    try {
      PreparedStatement pst = conexao.prepareStatement(instrucao);
      pst.setInt(1,id);
      ResultSet resultado = pst.executeQuery();
      if (resultado.next()){

        String nome = resultado.getString("nome");
        int idade = resultado.getInt("idade");

        cadastro = new CadastroModel();
        cadastro.setId(id);
        cadastro.setNome(nome);
        cadastro.setIdade(idade);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cadastro;
  }

}
