package br.com.voltta.desafiotecnico.dto;

import br.com.voltta.desafiotecnico.entity.ProdutoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProdutoInsertDto(
        @NotBlank
        @Schema(example = "Coca-Cola")
        String nome,
        @NotBlank
        @Schema(example = "Com sabor inconfundível e único, uma Coca-Cola Original é o refrigerante mais" +
                " tradicional econsumido no mundo inteiro! Toda Coca-Cola Original é especialmente concebido" +
                " para manter sempre a qualidade do melhor sabor de refrigerante! É perfeita para" +
                " compartilhar os melhores momentos da vida com amigos e familiares!")
        String descricao,
        @NotBlank
        @Schema(example = "7894900011715")
        @Pattern(regexp = "^[0-9]{1,14}$")
        String codigoBarras,
        @NotBlank
        Integer fabricanteID
) {
    public ProdutoInsertDto(ProdutoEntity entity){
        this(
                entity.getNome(),
                entity.getDescricao(),
                entity.getCodigoBarras(),
                entity.getFabricante().getId()
        );
    }
}
