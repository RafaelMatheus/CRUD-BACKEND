package br.com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.crud.entity.ClienteEntity;
import br.com.crud.repository.ClienteRepository;
import br.com.crud.service.exception.ObjectNotFoundException;



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
	
	public ClienteEntity insert(ClienteEntity cliente) { 
		cliente.setMatricula(null);
		cliente.setSenha(bcrypt.encode(cliente.getSenha()));
		return clienteRepository.save(cliente);
	}
	
	public ClienteEntity update(ClienteEntity cliente) {
		ClienteEntity clienteBd = this.findByMatricula(cliente.getMatricula());
		return clienteRepository.save(this.updateData(clienteBd, cliente));
	}
	
	public ClienteEntity updatePassword(Integer matricula, String novaSenha) {
		ClienteEntity clienteBd = this.findByMatricula(matricula);
		return clienteRepository.save(this.updatePassword(clienteBd, novaSenha));
	}
	
	public void delete(ClienteEntity cliente) {
		ClienteEntity clienteBd = this.findByMatricula(cliente.getMatricula());
		clienteRepository.delete(clienteBd);
	}
	
	private ClienteEntity updateData(ClienteEntity clienteBd, ClienteEntity cliente) {
		clienteBd.setNome(cliente.getNome());
		clienteBd.setEmail(cliente.getEmail());
		clienteBd.setDataNascimento(cliente.getDataNascimento());
		clienteBd.setSenha(cliente.getSenha());
		return clienteBd;
	}
	
	private ClienteEntity updatePassword(ClienteEntity clienteBd, String novaSenha) {
		clienteBd.setSenha(bcrypt.encode(novaSenha));
		return clienteBd;
	}
	
	
	
}
