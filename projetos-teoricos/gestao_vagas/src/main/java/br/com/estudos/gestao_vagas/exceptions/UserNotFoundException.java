package br.com.estudos.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(){
    super("Usuário não encontrado");
  }
}
