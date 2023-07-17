package br.com.banco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table(name = "conta")
public class ContaEntity implements Serializable {
    private static final long serialVersionUID = 3889666812883596650L;

    @Id
    @NotNull
    @Column(name = "id_conta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idConta;

    @Column(name = "nome_responsavel", nullable = false, length = 30)
    private String nomeResponsavel;

    @JsonIgnore
    @OneToMany(mappedBy = "conta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransferenciaEntity> transferencia;

    public ContaEntity(Integer idConta, String nomeResponsavel, List<TransferenciaEntity> transferencia) {
        this.idConta = idConta;
        this.nomeResponsavel = nomeResponsavel;
        this.transferencia = transferencia;
    }
}
