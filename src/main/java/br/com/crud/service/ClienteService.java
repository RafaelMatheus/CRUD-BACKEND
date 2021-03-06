package br.com.crud.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.crud.entity.ClienteEntity;
import br.com.crud.entity.dto.NewPasswordDto;
import br.com.crud.repository.ClienteRepository;
import br.com.crud.service.exception.ObjectNotFoundException;
import br.com.crud.service.exception.PasswordException;
import br.com.crud.service.exception.ValidationError;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public List<ClienteEntity> findAll() {
		return clienteRepository.findAll();
	}
	
	public ClienteEntity findByMatricula(Integer matricula) {
		Optional<ClienteEntity> cliente = clienteRepository.findById(matricula);
		
		return cliente.orElseThrow(
				()-> new ObjectNotFoundException("Não foi possivel encontrar o cliente com a matricula: "+ matricula+
				"por favor, entre em contato com o suporte"));
	}
	
	public Page<ClienteEntity> search(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Page<ClienteEntity> searchCliente(Integer page, Integer linesPerPage, String orderBy, String direction, String nome) {
		System.out.println(nome);
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findByNomeLikeStartingWith(nome, pageRequest);
	}
	
	public ClienteEntity insert(ClienteEntity cliente) { 
		if(clienteRepository.findByEmail(cliente.getEmail()) != null ) throw new ValidationError("Email já existe no cadasstro");
		cliente.setMatricula(null);
		cliente.setDataCadast(new Date());
		cliente.setSenha(bcrypt.encode(cliente.getSenha()));
		return clienteRepository.save(cliente);
	}
	
	public ClienteEntity update(ClienteEntity cliente) {
		ClienteEntity clienteBd = this.findByMatricula(cliente.getMatricula());
		return clienteRepository.save(this.updateData(clienteBd, cliente));
	}
	
	public ClienteEntity updatePassword(NewPasswordDto newPassword) {
		ClienteEntity clienteBd = this.findByMatricula(newPassword.getMatricula());
		if(this.isEqualsPassword(clienteBd.getSenha(), newPassword.getSenhaNova()))
			throw new PasswordException("Nova senha não pode ser igual a anterior.");
		
		return clienteRepository.save(this.updatePassword(clienteBd, newPassword.getSenhaNova()));
	}
	
	public void delete(Integer matricula) {
		ClienteEntity clienteBd = this.findByMatricula(matricula);
		clienteRepository.delete(clienteBd);
	}
	
	private ClienteEntity updateData(ClienteEntity clienteBd, ClienteEntity cliente) {
		clienteBd.setNome(cliente.getNome());
		clienteBd.setEmail(cliente.getEmail());
		clienteBd.setDataNascimento(cliente.getDataNascimento());
		return clienteBd;
	}
	
	private ClienteEntity updatePassword(ClienteEntity clienteBd, String novaSenha) {
		clienteBd.setSenha(bcrypt.encode(novaSenha));
		return clienteBd;
	}
	
	private boolean isEqualsPassword(String senhaAtual, String novaSenha) {
		if(senhaAtual.equalsIgnoreCase(bcrypt.encode(novaSenha))) return true;
		return false;
	}
	
	
}
