package br.com.crud.entity.dto;

public class NewPasswordDto {

	private Integer matricula;
	private String senhaAtual;
	private String senhaNova;
	private String confirmsenha;

	public NewPasswordDto(Integer matricula, String senhaAtual, String senhaNova, String confirmsenha) {
		super();
		this.matricula = matricula;
		this.senhaAtual = senhaAtual;
		this.senhaNova = senhaNova;
		this.confirmsenha = confirmsenha;
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

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getConfirmsenha() {
		return confirmsenha;
	}

	public void setConfirmsenha(String confirmsenha) {
		this.confirmsenha = confirmsenha;
	}

}
