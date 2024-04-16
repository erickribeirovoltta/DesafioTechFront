package br.com.voltta.desafiotecnico.util.converter;

import br.com.voltta.desafiotecnico.dto.ProdutoDto;
import br.com.voltta.desafiotecnico.dto.ProdutoInsertDto;
import br.com.voltta.desafiotecnico.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    private PessoaConverter pessoaConverter;

    public ProdutoDto mapToDto(ProdutoEntity entity){
        ProdutoDto dto = new ProdutoDto(entity);
        return dto;
    }

    public ProdutoInsertDto mapToInsertDto(ProdutoEntity entity){
        return new ProdutoInsertDto(entity);
    }

    public ProdutoEntity mapToEntity(ProdutoDto dto){
        ProdutoEntity entity = new ProdutoEntity();

        entity.setNome(dto.nome());
        entity.setDescricao(dto.descricao());
        entity.setCodigoBarras(dto.codigoBarras());
        entity.setFabricante(pessoaConverter.mapToEntity(dto.fabricante()));

        return entity;
    }
}
