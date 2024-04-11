package br.com.voltta.desafiotecnico.controller;

import br.com.voltta.desafiotecnico.dto.ProdutoDto;
import br.com.voltta.desafiotecnico.entity.ProdutoEntity;
import br.com.voltta.desafiotecnico.repository.ProdutoRepository;
import br.com.voltta.desafiotecnico.service.ProdutoService;
import br.com.voltta.desafiotecnico.util.converter.ProdutoConverter;
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
@RequestMapping(value = "/produto")
@Tag(name = "Produto", description = "Métodos para manipular um Produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoConverter produtoConverter;

    @Operation(summary = "Criar um novo produto")
    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoEntity> createPessoa(@RequestBody @Valid ProdutoDto dto){
        return ResponseEntity.ok(service.insert(dto));
    }

    @Operation(summary = "Buscar todas os produtos cadastrados")
    @GetMapping
    public ResponseEntity list(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                               @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage){
        Pagination page = new Pagination(initialPage, pageSize);
        return ResponseEntity.ok(service.findAll(page));
    }

    @Operation(summary = "Buscar produtos pelo Codigo de Barras")
    @GetMapping(value = "/{codigoBarras}")
    public Page<ProdutoEntity> findByCodigoBarras (@Parameter(example = "7894900011715") @PathVariable String codigoBarras,
                                                   @ApiIgnore Pageable pageable,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        pageable = PageRequest.of(page,size);
        return repository.findByCodigoBarrasEquals(codigoBarras,pageable);
    }

    @Operation(summary = "Edita os dados de um produto")
    @PutMapping
    public ResponseEntity<ProdutoDto> update(@RequestBody @Valid ProdutoDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

}
