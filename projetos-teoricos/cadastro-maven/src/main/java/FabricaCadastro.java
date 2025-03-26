import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class FabricaCadastro {
  private static Connection conexao;

  public static void conectar() {
    try {
      if (conexao == null) {
        String url = "jdbc:postgresql://localhost:5432/rocketseat-java-estudo";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "1011");
        conexao = DriverManager.getConnection(url, props);
      }
      System.out.println("conexao realizada");
    } catch (Exception e) {
      e.printStackTrace();

    }
  }

  public static Connection getConexao() {
    return conexao;
  }
}
