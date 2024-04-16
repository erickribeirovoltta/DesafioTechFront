package br.com.voltta.desafiotecnico.controller;

import br.com.voltta.desafiotecnico.dto.PessoaDto;
import br.com.voltta.desafiotecnico.entity.PessoaEntity;
import br.com.voltta.desafiotecnico.repository.PessoaRepository;
import br.com.voltta.desafiotecnico.service.PessoaService;
import br.com.voltta.desafiotecnico.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/pessoa")
@Tag(name = "Pessoa", description = "Métodos para manipular uma Pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;
    @Autowired
    private PessoaService service;

    @Operation(summary = "Criar uma nova pessoa")
    @PostMapping
    @Transactional
    public ResponseEntity<PessoaEntity> createPessoa(@RequestBody @Valid PessoaDto dto){
        return ResponseEntity.ok(repository.save(new PessoaEntity(dto)));
    }
    @Operation(summary = "Buscar todas as pessoa cadastradas")
    @GetMapping
    public ResponseEntity list(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                               @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage){
        Pagination page = new Pagination(initialPage, pageSize);
        return ResponseEntity.ok(service.findAll(page));
    }
    @Operation(summary = "Buscar pessoa pelo CNPJ")
    @GetMapping(value = "/{cnpj}")
    public Page<PessoaEntity> findByCnpj(@Parameter(example = "47944006000180") @PathVariable String cnpj,
                                         @ApiIgnore Pageable pageable,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size){
        pageable = PageRequest.of(page,size);
        return repository.findByCnpjEquals(cnpj, pageable);
    }
    @Operation(summary = "Busca pessoa pelo nome")
    @GetMapping("/pessoas")
    public Page<PessoaEntity> findByName(@Parameter(example = "Voltta")@RequestParam String nome,
                                         @ApiIgnore Pageable pageable,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size){
        pageable = PageRequest.of(page,size);
        return repository.findByNomeIgnoreCaseContaining(nome, pageable);
    }


    @Operation(summary = "Edita os dados de uma pessoa")
    @PutMapping
    public ResponseEntity<PessoaDto> update(@RequestBody @Valid PessoaDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(summary = "Exclui dados de uma pessoa específica")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
