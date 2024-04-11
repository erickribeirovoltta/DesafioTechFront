package br.com.voltta.desafiotecnico.entity;

import br.com.voltta.desafiotecnico.dto.PessoaDto;
import br.com.voltta.desafiotecnico.util.enums.ContatoType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pessoa")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cnpj;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContatoType contatoTipo;
    @Column(nullable = false)
    private String contato;

    public PessoaEntity(PessoaDto dto){
        this.nome = dto.nome();
        this.cnpj = dto.cnpj();
        this.cep = dto.cep();
        this.logradouro = dto.logradouro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.contatoTipo = dto.contatoTipo();
        this.contato = dto.contato();
    }
}
