package JavaNiO;

import java.time.LocalDate;

public class Cadastro {
    String nome;
    String sexo;
    Long telefone;
    LocalDate dtNascimento;
    double valorSugerido;
    boolean cliente;

    public Cadastro(String nome, String sexo, Long telefone, LocalDate dtNascimento, double valorSugerido, boolean cliente) {
        this.nome = nome;
        this.sexo = sexo;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.valorSugerido = valorSugerido;
        this.cliente = cliente;
    }
}
