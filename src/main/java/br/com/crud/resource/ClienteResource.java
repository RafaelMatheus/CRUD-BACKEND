package br.com.crud.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.crud.entity.ClienteEntity;
import br.com.crud.entity.dto.ClienteDto;
import br.com.crud.entity.dto.NewPasswordDto;
import br.com.crud.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	
	

	@ApiOperation(
			value="Retorna um clente por id", 
			response=ClienteEntity.class,
			consumes="",
			notes="Essa operação não é necessário nenhuma premissa.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma cliente com o status code ok"
					),
			@ApiResponse(
					code=403,
					message="Acesso negado, existem alguns endpoints neste path que é necessário acesso de ADM"

					),
			@ApiResponse(
					code=401,
					message="Indica que você não possui as credencias de autenticação válida. "

					),
			@ApiResponse(
					code=404,
					message="Indica que o recurso que você está procurando não existe, ou não foi encontrado."

					)
			
 
	})
	@RequestMapping(value = "/{matricula}", method = RequestMethod.GET)
	public ResponseEntity<ClienteEntity> findByMatricula(@PathVariable(name = "matricula") Integer matricula){
		return ResponseEntity.ok(clienteService.findByMatricula(matricula));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDto>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "matricula") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<ClienteEntity> list = clienteService.search(page, linesPerPage, orderBy, direction);
		Page<ClienteDto> listDto = list.map(obj -> new ClienteDto(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ClienteEntity cliente){
		ClienteEntity newCliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCliente.getMatricula())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{matricula}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteEntity cliente, @PathVariable(name = "matricula") Integer matricula){
		cliente.setMatricula(matricula);
		clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{matricula}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delte(@RequestBody ClienteEntity cliente, @PathVariable(name = "matricula") Integer matricula){
		cliente.setMatricula(matricula);
		clienteService.delete(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgotPassword(@RequestBody NewPasswordDto newPassword){
		clienteService.updatePassword(newPassword.getMatricula(), newPassword.getSenha());
		return ResponseEntity.ok().build();
	}
	
}
