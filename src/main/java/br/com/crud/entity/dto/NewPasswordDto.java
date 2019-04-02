package br.com.crud.entity.dto;

public class NewPasswordDto {

	private Integer matricula;
	private String senhaAtual;
	private String novaSenha;

	public NewPasswordDto(Integer matricula, String senhaAtual, String novaSenha) {
		super();
		this.matricula = matricula;
		this.senhaAtual = senhaAtual;
		this.novaSenha = novaSenha;
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

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}
