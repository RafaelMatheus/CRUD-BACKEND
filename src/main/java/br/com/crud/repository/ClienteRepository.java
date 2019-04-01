
package br.com.crud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.crud.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
	
	@Transactional(readOnly = true)
    ClienteEntity findByEmail(String email);
	
	@Transactional(readOnly=true)
	Page<ClienteEntity> findAll(Pageable pageRequest);
	
	@Transactional(readOnly = true)
	@Query("select c from cliente c where c.nome like %:nome")
	Page<ClienteEntity> findClienteEntityByNomeOrEmail(@Param("nome")String nome, Pageable pageRequest);
	
}
