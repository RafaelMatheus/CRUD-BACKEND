package br.com.crud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @author Rafael Castro
 * @version 1.0
 */
@Entity(name = "cliente")
public class ClienteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer matricula;
	@NotEmpty(message = "O campo nome é requerido")
	@Size(max = 100, min = 3, message = "Informe um nome com a quantidade de caracteres entre 3 e 100")
	private String nome;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date dataNascimento;
	@NotEmpty(message = "O campo data é requerido")
	@Email
	@Column(unique=true, nullable = false)
	private String email;
	@NotEmpty(message = "O campo senha é requerido")
	@Size(max=100, min=6, message="senha deve ter entre 6 e 50 caracteres")
	private String senha;
	private Date dataCadast;
	

	public ClienteEntity(Integer matricula, String nome, Date dataNascimento, String email, String senha, Date dataCadast) {
		this.matricula = matricula;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
		this.dataCadast = dataCadast;
	}

	public ClienteEntity() {
		
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
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadast() {
		return dataCadast;
	}

	public void setDataCadast(Date dataCadast) {
		this.dataCadast = dataCadast;
	}
	
	
}
