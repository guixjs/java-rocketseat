package JavaNiO;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeCadastro {
    public static void main(String[] args) {
        List <Cadastro> cadastros = new ArrayList<>();

        cadastros.add(new Cadastro("José","M",81986888658L, LocalDate.of(2006,8,27),40.0,false));
        cadastros.add(new Cadastro("Maria","F",81986888638L, LocalDate.of(2006,8,4),30.0,true));
        cadastros.add(new Cadastro("Pedro","M",81986888558L, LocalDate.of(2006,8,1),20.0,false));
        cadastros.add(new Cadastro("Fernanda","F",81986288658L, LocalDate.of(2006,8,23),350.0,true));

        escreverLayoutDelimitado(cadastros);


    }
    public static void escreverLayoutDelimitado(List<Cadastro> cadastros){
        try{
            StringBuilder conteudo = new StringBuilder();

            for(Cadastro cadastro:cadastros){
                conteudo.append(cadastro.getNome() + ";"); //escrevo cada campo no string builder e adiciono ; para delimita
                conteudo.append(cadastro.getSexo() + ";"); //esse é o método delimitado, CSV
                conteudo.append(cadastro.getTelefone() + ";");
                conteudo.append(cadastro.getDtNascimento() + ";");
                conteudo.append(cadastro.getValorSugerido() + ";");
                conteudo.append(cadastro.isCliente());
                conteudo.append(System.lineSeparator());
            }

            System.out.println(conteudo.toString());

            //diretório de onde o arquivo csv será salvo
            Path arquivoDestino = Paths.get("C:\\Users\\guilh\\OneDrive\\Área de Trabalho\\Programação\\ESTUDOS\\FORMÇÃO JAVA - Rocketseat\\projetos-teoricos\\lista-contatos-modelo-delimitado.csv");
            //escreve no arquivo, passando o caminho e o conteudo convertido em string e depois em Bytes
            Files.write(arquivoDestino, conteudo.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
