package br.com.crud.entity.dto;

/**
 * 
 * @author Rafael Castro
 * Classe responsavel por capturar solicitação de novas senhas
 * @version 1.0
 *
 */
public class NewPasswordDto {

	private Integer matricula;
	private String senha;

	public NewPasswordDto(Integer matricula, String senha) {
		super();
		this.matricula = matricula;
		this.senha = senha;
	}

	public NewPasswordDto() {
		super();
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
