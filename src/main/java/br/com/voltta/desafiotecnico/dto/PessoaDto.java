package br.com.voltta.desafiotecnico.dto;

import br.com.voltta.desafiotecnico.entity.PessoaEntity;
import br.com.voltta.desafiotecnico.util.enums.ContatoType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PessoaDto(
        @NotBlank
        @Schema(example = "Voltta")
        String nome,
        @NotBlank
        @Pattern(regexp = "^[0-9]{1,15}$")
        @Schema(example = "47944006000180")
        String cnpj,
        @Pattern(regexp = "^[0-9]*$")
        @Schema(example = "04551010")
        String cep,
        @Schema(example = "Rua Fidêncio Ramos")
        String logradouro,
        @Schema(example = "308")
        String numero,
        @Schema(example = "1 andar")
        String complemento,
        @Schema(example = "Vila Olímpia")
        String bairro,
        @Schema(example = "São Paulo")
        String cidade,
        @Schema(example = "São Paulo")
        String estado,
        @Schema(example = "Email")
        @NotBlank
        ContatoType contatoTipo,
        @Schema(example = "contato@voltta.com.br")
        @NotBlank
        String contato
) {
        public PessoaDto(PessoaEntity entity){
                this(entity.getNome(),
                     entity.getCnpj(),
                     entity.getCep(),
                     entity.getLogradouro(),
                     entity.getNumero(),
                     entity.getComplemento(),
                     entity.getBairro(),
                     entity.getCidade(),
                     entity.getEstado(),
                     entity.getContatoTipo(),
                     entity.getContato());
        }
}
