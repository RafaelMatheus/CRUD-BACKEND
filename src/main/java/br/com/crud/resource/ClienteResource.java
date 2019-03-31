package br.com.crud.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@Api(value="Greeting")
@CrossOrigin(origins  = "http://localhost:8080")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation(
			value="Retorna um cilente por matricula", 
			response=ClienteEntity.class,
			notes="Essa operação não é necessário nenhuma premissa.",
			produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um cliente com o status code ok"
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
	
	
	@ApiOperation(
			value="Retorna uma lista de clientes", 
			response=ClienteEntity.class,
			notes="Essa operação não é necessário nenhuma premissa.",
			produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista paginada de clientes, com o status code ok"
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
	
	@ApiOperation(
			value="Insere um cliente", 
			response=ClienteEntity.class,
			notes="Essa operação não é necessário nenhuma premissa.",
			produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=201, 
					message="Retorna pela URL a matricula do cliente inserido, com o status code ok"
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
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ClienteEntity cliente){
		ClienteEntity newCliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCliente.getMatricula())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(
			value="Atualiza um cliente por sua matricula", 
			response=ClienteEntity.class,
			notes="Essa operação não é necessário nenhuma premissa.",
			produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Retorna no content"
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
	@RequestMapping(value = "/{matricula}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteEntity cliente, @PathVariable(name = "matricula") Integer matricula){
		cliente.setMatricula(matricula);
		clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(
			value="Deleta um cliente utilizando sua matricula", 
			response=ClienteEntity.class,
			notes="Essa operação não é necessário nenhuma premissa.",
			produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Retorna no content"
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
	@RequestMapping(value = "/{matricula}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delte( @PathVariable(name = "matricula") Integer matricula){
		clienteService.delete(matricula);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(
			value="Atualiza a senha de um cliente", 
			response=NewPasswordDto.class,
			notes="Essa operação não é necessário nenhuma premissa.",
			produces="application/json")
	@ApiResponses(value= {
			@ApiResponse(
					code=204, 
					message="Retorna no content"
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
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgotPassword(@RequestBody NewPasswordDto newPassword){
		clienteService.updatePassword(newPassword.getMatricula(), newPassword.getSenha());
		return ResponseEntity.noContent().build();
	}
	
	
	
}
