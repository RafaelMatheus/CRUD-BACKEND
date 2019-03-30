package br.com.crud.entity.dto;

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
