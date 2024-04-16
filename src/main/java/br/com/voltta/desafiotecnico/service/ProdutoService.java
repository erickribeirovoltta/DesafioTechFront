package br.com.voltta.desafiotecnico.service;

import br.com.voltta.desafiotecnico.dto.ProdutoDto;
import br.com.voltta.desafiotecnico.dto.ProdutoInsertDto;
import br.com.voltta.desafiotecnico.entity.PessoaEntity;
import br.com.voltta.desafiotecnico.entity.ProdutoEntity;
import br.com.voltta.desafiotecnico.repository.PessoaRepository;
import br.com.voltta.desafiotecnico.repository.ProdutoRepository;
import br.com.voltta.desafiotecnico.util.converter.ProdutoConverter;
import br.com.voltta.desafiotecnico.util.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProdutoService {

    @Resource
    private ProdutoRepository repository;
    @Resource
    private PessoaRepository pessoaRepository;
    @Resource
    private ProdutoConverter converter;

    public Page<ProdutoEntity> findAll(Pagination page){
        Pageable pageable = PageRequest.of(page.getPage(), page.getPageSize(), Sort.by("nome"));

        return repository.findAll(pageable);
    }

    public ProdutoInsertDto insert(ProdutoInsertDto dto){
        PessoaEntity pessoa = pessoaRepository.findById(dto.fabricanteID()).get();

        ProdutoEntity produto =  new ProdutoEntity(
                null,
                dto.nome(),
                dto.descricao(),
                dto.codigoBarras(),
                pessoa
        );

        produto = repository.save(produto);

        return converter.mapToInsertDto(produto);
    }

    public PessoaEntity verifyPessoa(String cnpj){
       Optional<PessoaEntity> optionalPessoa = pessoaRepository.findByCnpjEquals(cnpj);

       return optionalPessoa.get();

    }

    public ProdutoInsertDto update(ProdutoInsertDto dto){
        ProdutoEntity produto = repository.findByCodigoBarrasEquals(dto.codigoBarras()).get();

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setCodigoBarras(dto.codigoBarras());
        produto.setFabricante(pessoaRepository.findById(dto.fabricanteID()).get());

        repository.save(produto);

        return converter.mapToInsertDto(produto);
    }

    public void delete(Integer id){
        repository.delete(repository.findById(id).get());
    }

}
