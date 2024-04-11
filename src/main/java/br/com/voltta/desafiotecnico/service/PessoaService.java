package br.com.voltta.desafiotecnico.service;

import br.com.voltta.desafiotecnico.dto.PessoaDto;
import br.com.voltta.desafiotecnico.entity.PessoaEntity;
import br.com.voltta.desafiotecnico.repository.PessoaRepository;
import br.com.voltta.desafiotecnico.util.converter.PessoaConverter;
import br.com.voltta.desafiotecnico.util.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PessoaConverter pessoaConverter;
    public Page<PessoaEntity> findAll(Pagination page){
        Pageable pageable = PageRequest.of(page.getPage(), page.getPageSize(), Sort.by("nome"));

        return repository.findAll(pageable);
    }

    public PessoaDto update(PessoaDto dto){
        Optional<PessoaEntity> savedOption = repository.findByCnpjEquals(dto.cnpj());

        PessoaEntity pessoa = savedOption.get();
        pessoa.setNome(dto.nome());
        pessoa.setCnpj(dto.cnpj());
        pessoa.setLogradouro(dto.logradouro());
        pessoa.setNumero(dto.numero());
        pessoa.setComplemento(dto.complemento());
        pessoa.setCep(dto.cep());
        pessoa.setBairro(dto.bairro());
        pessoa.setCidade(dto.cidade());
        pessoa.setEstado(dto.estado());
        pessoa.setContatoTipo(dto.contatoTipo());
        pessoa.setContato(dto.contato());

        repository.save(pessoa);

        return pessoaConverter.mapToDto(pessoa);
    }
}
