package br.com.crud.entity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.crud.entity.ClienteEntity;

public class ClienteDto {
	private Integer matricula;
	private String nome;
	@JsonFormat(pattern = "dd-mm-yyyy")
	private Date dataNascimento;
	private String email;

	public ClienteDto(ClienteEntity c) {
		super();
		this.matricula = c.getMatricula();
		this.nome = c.getNome();
		this.dataNascimento = c.getDataNascimento();
		this.email = c.getEmail();
	}

	public ClienteDto() {
		super();
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
