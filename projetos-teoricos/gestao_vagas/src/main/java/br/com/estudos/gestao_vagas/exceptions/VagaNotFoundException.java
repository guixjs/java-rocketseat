package br.com.estudos.gestao_vagas.exceptions;

public class VagaNotFoundException extends RuntimeException{
  public VagaNotFoundException(){
    super("Vaga não encontrada");
  }
}
