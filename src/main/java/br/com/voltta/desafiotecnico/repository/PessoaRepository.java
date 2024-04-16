package br.com.voltta.desafiotecnico.repository;

import br.com.voltta.desafiotecnico.entity.PessoaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
    Page<PessoaEntity> findByCnpjEquals(String cnpj, Pageable pageable);

    Optional<PessoaEntity> findByCnpjEquals(String cnpj);

    Page<PessoaEntity> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);

    Page<PessoaEntity> findAll(Pageable pageable);
}
