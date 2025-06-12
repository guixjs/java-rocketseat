package br.com.estudos.gestao_vagas.exceptions;

public class EmpresaNotFoundException extends RuntimeException{
  public EmpresaNotFoundException(){
    super("Empresa não encontrada");
  }
}
