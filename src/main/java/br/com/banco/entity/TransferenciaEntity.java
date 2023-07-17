package br.com.banco.entity;

import br.com.banco.enums.Tipo;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table(name = "transferencia")
public class TransferenciaEntity implements Serializable {
    private static final long serialVersionUID = 757222796541459308L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "data_transferencia")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @NotNull
    private LocalDate dataTransferencia;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
    private ContaEntity conta;

    public TransferenciaEntity(Integer id, LocalDate dataTransferencia, BigDecimal valor, Tipo tipo,
                               String nomeOperadorTransacao, ContaEntity conta) {
        this.id = id;
        this.dataTransferencia = dataTransferencia;
        this.valor = valor;
        this.tipo = tipo;
        this.nomeOperadorTransacao = nomeOperadorTransacao;
        this.conta = conta;
    }

    @Override
    public String toString() {
        return "TransferenciaEntity{" +
                "id=" + id +
                ", dataTransferencia=" + dataTransferencia +
                ", valor=" + valor +
                ", tipo=" + tipo +
                ", nomeOperadorTransacao='" + nomeOperadorTransacao + '\'' +
                ", conta=" + conta +
                '}';
    }
}