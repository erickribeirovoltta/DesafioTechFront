package br.com.voltta.desafiotecnico.repository;

import br.com.voltta.desafiotecnico.entity.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {
    Page<ProdutoEntity> findByCodigoBarrasEquals(String codigoBarras, Pageable pageable);
    Optional<ProdutoEntity> findByCodigoBarrasEquals(String codigoBarras);
}
