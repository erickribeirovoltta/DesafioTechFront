package br.com.voltta.desafiotecnico.util.converter;

import br.com.voltta.desafiotecnico.dto.PessoaDto;
import br.com.voltta.desafiotecnico.entity.PessoaEntity;
import org.springframework.stereotype.Component;

@Component
public class PessoaConverter {

    public PessoaDto mapToDto(PessoaEntity entity){
        PessoaDto dto = new PessoaDto(entity);
        return dto;
    }

    public PessoaEntity mapToEntity (PessoaDto dto){
        PessoaEntity entity = new PessoaEntity();

        entity.setNome(dto.nome());
        entity.setCnpj(dto.cnpj());
        entity.setLogradouro(dto.logradouro());
        entity.setNumero(dto.numero());
        entity.setComplemento(dto.complemento());
        entity.setCep(dto.cep());
        entity.setBairro(dto.bairro());
        entity.setCidade(dto.cidade());
        entity.setEstado(dto.estado());
        entity.setContatoTipo(dto.contatoTipo());
        entity.setContato(dto.contato());

        return entity;
    }
}
