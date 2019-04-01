
package br.com.crud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	@Query(value="select * from Cliente c where c.nome like %:nome%", nativeQuery=true)
	Page<ClienteEntity> findByNomeLikeStartingWith(String nome, Pageable pageRequest);
	
}
