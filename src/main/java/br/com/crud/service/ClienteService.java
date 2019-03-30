package br.com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.entity.ClienteEntity;
import br.com.crud.repository.ClienteRepository;
import br.com.crud.service.exception.ObjectNotFoundException;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<ClienteEntity> findAll() {
		return clienteRepository.findAll();
	}
	
	public ClienteEntity findByMatricula(Integer matricula) {
		Optional<ClienteEntity> clientes = clienteRepository.findById(matricula);
		
		return clientes.orElseThrow(
				()-> new ObjectNotFoundException("Não foi possivel encontrar o cliente com a matricula: "+ matricula+
				"por favor, entre em contato com o suporte"));
	}
	
	public ClienteEntity insert(ClienteEntity cliente) { 
		cliente.setMatricula(null);
		return clienteRepository.save(cliente);
	}
	
	public ClienteEntity update(ClienteEntity cliente) {
		ClienteEntity clienteBd = this.findByMatricula(cliente.getMatricula());
		return clienteRepository.save(this.updateData(clienteBd, cliente));
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
	
}
