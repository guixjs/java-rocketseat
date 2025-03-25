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

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public Long getTelefone() {
        return telefone;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public double getValorSugerido() {
        return valorSugerido;
    }

    public boolean isCliente() {
        return cliente;
    }
}
