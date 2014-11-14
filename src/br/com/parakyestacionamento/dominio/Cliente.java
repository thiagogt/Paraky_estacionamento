package br.com.parakyestacionamento.dominio;

import java.util.Date;

public class Cliente {
	private int idCliente ;
	private String nome;
	private String sobrenome ;
	private String cpf ;
	private Date dataNascimento ;
	private String tel_1 ;
	private String tel_2 ;
	private String email ;
	private int idTitularVaga;
	
	public Cliente(String newCpf,int newIdTitular){
		this.cpf = newCpf;
		this.idTitularVaga = newIdTitular;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTel_1() {
		return tel_1;
	}

	public void setTel_1(String tel_1) {
		this.tel_1 = tel_1;
	}

	public String getTel_2() {
		return tel_2;
	}

	public void setTel_2(String tel_2) {
		this.tel_2 = tel_2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdTitularVaga() {
		return idTitularVaga;
	}

	public void setIdTitularVaga(int idTitularVaga) {
		this.idTitularVaga = idTitularVaga;
	}
	
	
	
}
