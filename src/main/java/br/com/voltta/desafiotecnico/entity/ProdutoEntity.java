package br.com.voltta.desafiotecnico.entity;

import br.com.voltta.desafiotecnico.entity.PessoaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="produto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private String codigoBarras;
    @ManyToOne
    private PessoaEntity fabricante;
}
